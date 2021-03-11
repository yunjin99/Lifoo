package com.example.lifoo.src.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.lifoo.BaseActivity;
import com.example.lifoo.R;
import com.example.lifoo.src.MainActivity.MainActivity;
import com.example.lifoo.src.SocialLoginActivity.SocialLoginActivity;
import com.example.lifoo.src.SocialLoginActivity.SocialLoginService;
import com.example.lifoo.src.SocialLoginActivity.interfaces.SocialLoginActivityView;
import com.example.lifoo.src.SplashActivity.interfaces.SplashActivityView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo.ApplicationClass.sSharedPreferences;
import static com.example.lifoo.ApplicationClass.TAG;


public class SplashActivity extends BaseActivity implements SplashActivityView {

    // 자동 로그인 api

    SplashService splashService = new SplashService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //  jwt 정보 확인하여
        TryAutoLogin();
    }


    private void TryAutoLogin()
    {
        showProgressDialog();
        splashService.getAutoLogIn();
    }

    @Override
    public void AutoLoginFailure(String message, int code) {

        sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "null token");

        Log.d("자동 로그인 jwt 토큰 ", jwtToken);
        Log.d("자동 로그인 통신 메세지 ", message);

        Intent intent = new Intent(SplashActivity.this, SocialLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void AutoLogInSuccess(String message, int code) {

        if(code == 2000)
        {
            // 요청 성공
            Log.d("자동로그인","성공");
            Toast.makeText(getApplicationContext(),"자동 로그인 입니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if(code == 3000){
            //jwt 입력해 주세요
            Log.d("자동로그인","jwt 없음");
            Intent intent0 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent0);
            finish();
        }else if(code == 3200){
            Log.d("자동로그인","jwt 유효 x");
            sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
            String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "null token");

            Log.d("자동 로그인 jwt 토큰 ", jwtToken);
            // 유효하지 않은 jwt
            Intent intent1 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent1);
            finish();
        }else if(code == 3201){
            // 존재하지 않는 회원함
            Log.d("자동로그인","존재 안");
            Intent intent2 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent2);
            finish();

        }

    }


}

