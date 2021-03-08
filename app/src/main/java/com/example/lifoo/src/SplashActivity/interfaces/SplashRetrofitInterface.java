package com.example.lifoo.src.SplashActivity.interfaces;

import com.example.lifoo.src.SocialLoginActivity.models.SocialLoginBody;
import com.example.lifoo.src.SocialLoginActivity.models.SocialLoginResponse;
import com.example.lifoo.src.SplashActivity.models.Splash_AutoLogin_Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SplashRetrofitInterface {

    // 자동 로그
    @GET("/login/{jwt}")
    Call<Splash_AutoLogin_Response> Auto_Login_Test ();

}
