package com.nqsoftwaresolutions.javaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class CheatActivity extends AppCompatActivity {

    private Button mShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mShowButton = findViewById(R.id.id_btn_show_ans);
    }
}