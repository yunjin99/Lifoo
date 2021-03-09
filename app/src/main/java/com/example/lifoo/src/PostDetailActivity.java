package com.example.lifoo.src;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.lifoo.R;

public class PostDetailActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent intent = getIntent();
    }
}
