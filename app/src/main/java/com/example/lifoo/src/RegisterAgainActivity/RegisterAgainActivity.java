package com.example.lifoo.src.RegisterAgainActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifoo.R;

public class RegisterAgainActivity extends AppCompatActivity{

    Button btnAgain;
    Button btnSignUp;
    TextView firstLetter;
    TextView secondLetter;
    TextView thirdLetter;
    TextView fourthLetter;
    TextView fifthLetter;
    TextView sixthLetter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_again);


        btnAgain = findViewById(R.id.btnAgain);
        btnSignUp = findViewById(R.id.btnSignUp);
        firstLetter = findViewById(R.id.firstLetter);
        secondLetter = findViewById(R.id.secondLetter);
        thirdLetter = findViewById(R.id.thirdLetter);
        fourthLetter = findViewById(R.id.fourthLetter);
        fifthLetter = findViewById(R.id.fifthLetter);
        sixthLetter = findViewById(R.id.sixthLetter);

        //다시뽑기 버튼
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //슬롯머신 작동

            }

        });

        //가입하기 버튼
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인페이지로 이동
                Intent intent = new Intent(getApplicationContext(), RegisterAgainActivity.class);

                startActivity(intent);
            }

        });

    }

}
