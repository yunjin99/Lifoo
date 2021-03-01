package com.example.lifoo.src.RegisterActivity;
import com.example.lifoo.R;
import com.example.lifoo.src.RegisterAgainActivity.RegisterAgainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity{

    Button btnDraw;
    TextView firstLetter;
    TextView secondLetter;
    TextView thirdLetter;
    TextView fourthLetter;
    TextView fifthLetter;
    TextView sixthLetter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnDraw = findViewById(R.id.btnDraw);
        firstLetter = findViewById(R.id.firstLetter);
        secondLetter = findViewById(R.id.secondLetter);
        thirdLetter = findViewById(R.id.thirdLetter);
        fourthLetter = findViewById(R.id.fourthLetter);
        fifthLetter = findViewById(R.id.fifthLetter);
        sixthLetter = findViewById(R.id.sixthLetter);

        //닉네임 뽑기 버튼
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //슬롯머신 작동


                //슬롯머신 작동이 멈추면 activity_register_again.xml으로 이동
                //생성된 랜덤 닉네임 텍스트는 가져가서 보여줌
                Intent intent = new Intent(getApplicationContext(), RegisterAgainActivity.class);
                intent.putExtra("FirstLetter", firstLetter.getText());
                intent.putExtra("SecondLetter", secondLetter.getText());
                intent.putExtra("ThirdLetter", thirdLetter.getText());
                intent.putExtra("FourthLetter", fourthLetter.getText());
                intent.putExtra("FifthLetter", fifthLetter.getText());
                intent.putExtra("SixthLetter", sixthLetter.getText());

                startActivity(intent);
            }

        });

    }

}
