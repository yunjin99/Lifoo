package com.example.lifoo.config;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.lifoo.src.SocialLoginActivity.KakaoApp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo.ApplicationClass.sSharedPreferences;
import static com.example.lifoo.ApplicationClass.TAG;


public class XAccessTokenInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();

        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(KakaoApp.getContext());
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "null token");


        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }

        return chain.proceed(builder.build());
    }
}
