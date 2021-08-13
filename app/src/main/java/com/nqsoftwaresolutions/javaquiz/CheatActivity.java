package com.nqsoftwaresolutions.javaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Button mShowAnswerButton = findViewById(R.id.id_btn_show_ans);
        TextView mShowAnswerTv = findViewById(R.id.id_txt_show_answer);

        mShowAnswerButton.setOnClickListener(v -> {
            // Todo Get answer from intent & if it is true then set it on text view else false
            boolean answer = getIntent().getBooleanExtra(EXTRA, false);
            if (answer){
                mShowAnswerTv.setText(R.string.aTrue);
            }else {
                mShowAnswerTv.setText(R.string.aFalse);
            }
        });
    }
}