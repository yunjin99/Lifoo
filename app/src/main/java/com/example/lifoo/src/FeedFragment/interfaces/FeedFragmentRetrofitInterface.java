package com.example.lifoo.src.FeedFragment.interfaces;

import com.example.lifoo.src.FeedFragment.models.FeedFragmentResponse;
import com.example.lifoo.src.RegisterAgainActivity.models.GetNicknameResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FeedFragmentRetrofitInterface {

    // 게시물 조회
    @GET("/posts")
    Call<FeedFragmentResponse> GetFeedTest(
            @Query("type") String type,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num
    );


    @GET("/posts")
    Call<FeedFragmentResponse> GetFeedTest(
            @QueryMap Map< String, String> parameters
    );

}
