package com.nqsoftwaresolutions.javaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nqsoftwaresolutions.javaquiz.DataModel.Questions;


public class MainActivity extends AppCompatActivity {

    private Button mFalseButton, mTrueButton, mNextButton;
    private TextView mQuestionTextView;
    /**
     * Create a question array
     */
    private Questions[] mQuestionsBank = new Questions[]{
            new Questions(R.string.java_fundamentals,false),
            new Questions(R.string.java_fundamentals2,true),
            new Questions(R.string.java_fundamentals3,false),
            new Questions(R.string.java_fundamentals4,false),
            new Questions(R.string.java_fundamentals5,true)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.id_btn_false);
        mTrueButton = findViewById(R.id.id_btn_true);
        mNextButton = findViewById(R.id.id_btn_next);

        //Todo Get reference of Question text view & assign it text on current index
        mQuestionTextView = findViewById(R.id.id_txt_question);
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

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

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}