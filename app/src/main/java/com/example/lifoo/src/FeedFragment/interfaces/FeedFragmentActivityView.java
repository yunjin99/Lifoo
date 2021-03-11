package com.example.lifoo.src.FeedFragment.interfaces;

import com.example.lifoo.src.FeedFragment.models.FeedFragmentResponse;

public interface FeedFragmentActivityView {

    // 게시물 조
    void GetPostsBasicFailure(String message, int code);

    void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code);
}
