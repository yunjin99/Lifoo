package com.example.lifoo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.lifoo.config.XAccessTokenInterceptor;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass  extends Application {

    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // 테스트 서버 주소
    public static String BASE_URL = "http://lifoo.ga";

    // 실서버 주소
//    public static String BASE_URL = "";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "TEMPLATE_APP";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";



    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit 인스턴스
    public static Retrofit retrofit;


    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {

            sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);

            SharedPreferences.Editor editor= sSharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
            editor.putString("temp_temp","temp_temp"); // key,value 형식으로 저장
            editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
        }
    }

    public static Retrofit getRetrofit() {
        // 여기에서 위에서 쓰였던
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
