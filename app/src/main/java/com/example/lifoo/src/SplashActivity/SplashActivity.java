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


        // 5초 후에 화면이 변함함
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Lottie Animation
                LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.splash_lottie);
                animationView.playAnimation();

                //  jwt 정보 확인하여
                TryAutoLogin();
            }
        },2500);



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
        Log.d("자동 로그인 통신 메세지 ",message);

        Intent intent = new Intent(SplashActivity.this, SocialLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void AutoLogInSuccess(String message, int code) {

        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "null token");
        Log.d("자동 로그인 jwt 토큰", jwtToken);
        Log.d("자동 로그인 통신 메세지",message);

        switch(code)
        {
            case 2000:
                // 요청 성공
                Toast.makeText(getApplicationContext(),"자동 로그인 입니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            case 3000:
                //jwt 입력해 주세요
                Intent intent0 = new Intent(SplashActivity.this, SocialLoginActivity.class);
                startActivity(intent0);
                finish();

            case 3200:
                // 유효하지 않은 jwt
                Intent intent1 = new Intent(SplashActivity.this, SocialLoginActivity.class);
                startActivity(intent1);
                finish();

            case 3201:
                // 존재하지 않는 회원
                Intent intent2 = new Intent(SplashActivity.this, SocialLoginActivity.class);
                startActivity(intent2);
                finish();

        }



    }


}

