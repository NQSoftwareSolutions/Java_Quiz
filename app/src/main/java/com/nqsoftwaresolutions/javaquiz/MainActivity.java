package com.nqsoftwaresolutions.javaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nqsoftwaresolutions.javaquiz.DataModel.Questions;


public class MainActivity extends AppCompatActivity {

    private Button mFalseButton, mTrueButton;
    private ImageButton mNextImageButton, mPrevImageButton;
    private TextView mQuestionTextView;
    private static final String TAG = "MainActivity";
    /**
     * Create a question array
     */
    private Questions[] mQuestionsBank = new Questions[]{
            new Questions(R.string.java_fundamentals,false),
            new Questions(R.string.java_fundamentals2,true),
            new Questions(R.string.java_fundamentals3,false),
            new Questions(R.string.java_fundamentals4,false),
            new Questions(R.string.java_fundamentals5,false)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Activity Created....");
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.id_btn_false);
        mTrueButton = findViewById(R.id.id_btn_true);
        mNextImageButton = findViewById(R.id.id_img_btn_next);
        mPrevImageButton = findViewById(R.id.id_img_btn_pev);

        //Todo Get reference of Question text view & assign it text on current index
        mQuestionTextView = findViewById(R.id.id_txt_question);
        updateQuestion();

        //Todo update question & index with click
        mNextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
               updateQuestion();
            }
        });

        /**
         * Todo Challenge 1.1 customize Toast
         * show Toast on top of the screen
         */
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        /**
         * Todo Challenge 2.1 set listener on TextView
         * we are going to create a event on Question text view so we can get next question
         * if we click on it
         */
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

        /**
         * Todo Challenge 2.2 set previous button
         * Create a new button & add listener on it
         * Update index & question in backward when clicked
         */
        mPrevImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0){
                    Toast toast = Toast.makeText(MainActivity.this, "Your are on first Question", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,1,80);
                    toast.show();
                }else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
                    updateQuestion();
                }
            }
        });

    }

    private void checkAnswer(boolean b) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (b == answerIsTrue){
            messageResId = R.string.correct;
        }else {
            messageResId = R.string.notCorrect;
        }
        Toast toast = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,1,80);
        toast.show();
    }

    private void updateQuestion() {
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Activity Started...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity Resumed...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Activity paused...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Activity Stopped....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Activity Destroyed....");
    }
}