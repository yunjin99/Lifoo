package com.example.lifoo.src.SocialLoginActivity.interfaces;

public interface SocialLoginActivityView {

    // SocialLogin 실패시
    void SocialLoginFailure(String message, int code);

    //로그인 시 jwt 토큰 값 넘겨주며, 성공
    void SocialLogInSuccess(String jwt, String user_idx);

}
