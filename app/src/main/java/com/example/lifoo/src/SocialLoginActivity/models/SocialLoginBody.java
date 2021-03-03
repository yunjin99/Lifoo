package com.example.lifoo.src.SocialLoginActivity.models;

import com.google.gson.annotations.SerializedName;

public class SocialLoginBody {

    @SerializedName("accessToken")
    private String accessToken;

    public SocialLoginBody(String accessToken) {
        this.accessToken = accessToken;
    }
}
