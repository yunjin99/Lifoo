package com.example.lifoo.src.SocialLoginActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifoo.BaseActivity;
import com.example.lifoo.R;
import com.example.lifoo.src.MainActivity.MainActivity;
import com.example.lifoo.src.RegisterAgainActivity.RegisterAgainActivity;
import com.example.lifoo.src.SocialLoginActivity.interfaces.SocialLoginActivityView;
import com.example.lifoo.src.SocialLoginActivity.models.SocialLoginResponse;
import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import static com.example.lifoo.ApplicationClass.TAG;
import static com.example.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo.ApplicationClass.sSharedPreferences;

public class SocialLoginActivity extends BaseActivity implements SocialLoginActivityView {


    SocialLoginService socialLoginService = new SocialLoginService(this);

    private KakaoCallback kakaoCallback;

    LoginButton kakao_login_btn_invisible;
    Button  login_btn;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

        login_btn = findViewById(R.id.btnSocialLogin);
        kakao_login_btn_invisible = findViewById(R.id.login_btn_kakao_gone);

        // 카카오 로그
        kakaoCallback = new KakaoCallback();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakao_login_btn_invisible.callOnClick();

                Session.getCurrentSession().addCallback(kakaoCallback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        });

    }



    private void TrySocialLogin(String accessToken)
    {
        showProgressDialog();
        socialLoginService.postSocialLogIn(accessToken);
    }

    @Override
    public void SocialLoginFailure(String message, int code) {

        hideProgressDialog();

        // 통신이 실패 하지 않는 이상 이 구간은 없을 수 있음.

        // TrySocialLogin 실패시 (신규 회원이거나, 유효하지 않는 토큰이거나 , 통신 자체가 안될때)
        Log.d("코드", String.valueOf(code));
        Log.d("메세지", message);


    }

    @Override
    public void SocialLogInSuccess(SocialLoginResponse socialLoginResponse, String message, int code) {

        hideProgressDialog();


        if(code == 2000)
        {
            // 이미 가입되 있는 사람.

            Log.d("통신 성공", socialLoginResponse.getResult().getJwt() + " and " + socialLoginResponse.getResult().getUserIdx());
            // 소셜 로그인이 성공 한 부분.(유효한 토큰을 가지고 있는 유저임)

            sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            // 내부에 저장 되어 있는 jwt 값을 지움
            editor.remove(X_ACCESS_TOKEN);
            editor.commit();

            SharedPreferences.Editor editor2 = sSharedPreferences.edit();
            // 소셜 로그인을 통해 jwt 값을
            editor2.putString(X_ACCESS_TOKEN,socialLoginResponse.getResult().getJwt());
            editor2.putString("user_id",socialLoginResponse.getResult().getUserIdx());
            editor2.commit();

            String x_access = sSharedPreferences.getString(X_ACCESS_TOKEN,null);

            Log.d("소셜 로그인 jwt, idx: ",x_access + "하" + socialLoginResponse.getResult().getUserIdx());

            Intent intent = new Intent(SocialLoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else if(code == 3202) {
            // 신규

            Intent intent = new Intent(SocialLoginActivity.this, RegisterAgainActivity.class);
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();

        }else if(code ==3203 || code == 3016) {
            // 유효하지 않은 토큰
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    private class KakaoCallback implements ISessionCallback {

        /*
        onSessionOpened() : 로그인 세션 열렸을 때.       onSewwionOpenFailed() : 로그인 세션 정상적으로 열리지 않았을 때.
        * */
        @Override
        public void onSessionOpened() {

            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    // 로그인 실패시 (네트워크 문제 포함)
                    int result = errorResult.getErrorCode();
                    if(result == ApiErrorCode.CLIENT_ERROR_CODE)
                    {
                        Toast.makeText(getApplicationContext(), R.string.network_error,Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(),R.string.login_error,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    // 로그인 세션 도중 비정상적 이유로 닫힐때
                    Toast.makeText(getApplicationContext(), R.string.session_error,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {

                    // 로그인 성공 시 카카오 토큰 값을 받아서, 서버의 카카오 로그인 api 에 사용해야 한다!
                    AccessToken accessToken;
                    accessToken = Session.getCurrentSession().getTokenInfo();

                    long user_id;
                    user_id = result.getId();

                    Log.e("카카오 토큰",accessToken.getAccessToken());
                    Log.e("카카오 유저 아이디", String.valueOf(user_id));

                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove(X_ACCESS_TOKEN);
                    editor.putString(X_ACCESS_TOKEN,accessToken.getAccessToken());
                    editor.commit();

                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sSharedPreferences.edit();
                    editor2.remove("user_id");
                    editor2.putString("user_id",String.valueOf(user_id));
                    editor.commit();


                    TrySocialLogin(accessToken.getAccessToken());

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(getApplicationContext(),R.string.login_error+exception.toString(),Toast.LENGTH_SHORT).show();
        }

    }

}
