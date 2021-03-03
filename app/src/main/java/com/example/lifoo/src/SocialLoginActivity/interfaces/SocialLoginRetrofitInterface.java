package com.example.lifoo.src.SocialLoginActivity.interfaces;

import com.example.lifoo.src.SocialLoginActivity.models.SocialLoginBody;
import com.example.lifoo.src.SocialLoginActivity.models.SocialLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SocialLoginRetrofitInterface {

    //SNS 로그인
    @POST("/login/kakao")
    Call<SocialLoginResponse> socialLoginTest(
            @Body SocialLoginBody socialLoginBody
    );

}
