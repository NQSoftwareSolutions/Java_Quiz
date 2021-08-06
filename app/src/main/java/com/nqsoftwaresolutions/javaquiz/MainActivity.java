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
            new Questions(R.string.java_fundamentals5,false)
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
        updateQuestion();

        //Todo update question & index with click
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
               updateQuestion();
            }
        });

        /**
         * Todo Challenge customize Toast
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
         * Todo Challenge set listener on TextView
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
}