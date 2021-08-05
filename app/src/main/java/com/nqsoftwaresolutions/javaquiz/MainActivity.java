package com.nqsoftwaresolutions.javaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mFalseButton, mTrueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.id_btn_false);
        mTrueButton = findViewById(R.id.id_btn_true);

        /**
         * Todo Challenge customize Toast
         * show Toast on top of the screen
         */
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.notCorrect, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,1,80);
                toast.show();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,1,80);
                toast.show();
            }
        });

    }
}