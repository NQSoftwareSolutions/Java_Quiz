package com.nqsoftwaresolutions.javaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nqsoftwaresolutions.javaquiz.data_model.Questions;


public class MainActivity extends AppCompatActivity {

    //Todo All global variables
    private Button mFalseButton, mTrueButton;
    private TextView mQuestionTextView;
    private Button mShowButton;

    private static final String TAG = "MainActivity";

    //Global variable with key to store state or value, when activity recreated by android
    private static final String KEY_INDEX = "index";
    private static final String KEY_FALSE = "isFalse";
    private static final String KEY_TRUE = "isTrue";

    private int mCurrentIndex = 0;
    private boolean mTrueIsClicked, mFalseIsClicked;

    /**
     * Create a question array
     */
    private final Questions[] mQuestionsBank = new Questions[]{
            new Questions(R.string.java_fundamentals,false),
            new Questions(R.string.java_fundamentals2,true),
            new Questions(R.string.java_fundamentals3,false),
            new Questions(R.string.java_fundamentals4,false),
            new Questions(R.string.java_fundamentals5,false)
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Activity Created....");
        setContentView(R.layout.activity_main);

        //Todo set reference to widgets
        mFalseButton = findViewById(R.id.id_btn_false);
        mTrueButton = findViewById(R.id.id_btn_true);
        ImageButton nextImageButton = findViewById(R.id.id_img_btn_next);
        ImageButton prevImageButton = findViewById(R.id.id_img_btn_pev);
        mQuestionTextView = findViewById(R.id.id_txt_question);
        mShowButton = findViewById(R.id.id_btn_show_ans);


        // Todo Check last state of activity
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mTrueIsClicked = savedInstanceState.getBoolean(KEY_TRUE);
            mFalseIsClicked = savedInstanceState.getBoolean(KEY_FALSE);
            if (mTrueIsClicked || mFalseIsClicked){
                /*Todo Challenge 3.1 Prevent user from multiple answers
                  set clickable true
                 */
                mFalseButton.setClickable(true);
                mTrueButton.setClickable(true);
            }
        }

        updateQuestion();

        //Todo update question & index with click
        nextImageButton.setOnClickListener(v -> {
            //Todo Check user is on last question or not
            if (mCurrentIndex == 4){
                Toast toast = Toast.makeText(MainActivity.this, "Your are on last Question", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,1,80);
                toast.show();

            }else {
                // mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                mCurrentIndex++;
                updateQuestion();
                mFalseButton.setClickable(true);
                mTrueButton.setClickable(true);
            }
        });

        /*
          Todo Challenge 1.1 customize Toast
          show Toast on top of the screen
         */
        mTrueButton.setOnClickListener(v -> {
            checkAnswer(true);
            /*Todo Challenge 3.1 Prevent user from multiple answers
              set clickable false
             */
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        });

        mFalseButton.setOnClickListener(v -> {
            checkAnswer(false);
            /*Todo Challenge 3.1 Prevent user from multiple answers
             set clickable false
             */
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        });

        /*
          Todo Challenge 2.1 set listener on TextView
          we are going to create a event on Question text view so we can get next question
          if we click on it
         */
        mQuestionTextView.setOnClickListener(v -> {
            //Todo Check user is on last question or not
            if (mCurrentIndex == 4){
                Toast toast = Toast.makeText(MainActivity.this, "Your are on last Question", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,1,80);
                toast.show();
            }else {
                // mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                mCurrentIndex++;
                updateQuestion();
                /*Todo Challenge 3.1 Prevent user from multiple answers
                 set clickable true
                 */
                mFalseButton.setClickable(true);
                mTrueButton.setClickable(true);
            }
        });

        /*
          Todo Challenge 2.2 set previous button
          Create a new button & add listener on it
          Update index & question in backward when clicked
         */
        prevImageButton.setOnClickListener(v -> {
            if (mCurrentIndex == 0){
                Toast toast = Toast.makeText(MainActivity.this, "Your are on first Question", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,1,80);
                toast.show();
            }else {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
                updateQuestion();
                /*Todo Challenge 3.1 Prevent user from multiple answers
                 set clickable false
                 */
                mFalseButton.setClickable(false);
                mTrueButton.setClickable(false);
            }
        });
    }

    private void checkAnswer(boolean b) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
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
        //Log.d(TAG, "Updating Question Text", new Exception());
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**Todo saving the state of activity
     * override the onSaveInstanceState & onRestoreInstanceState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(KEY_FALSE,mFalseIsClicked);
        outState.putBoolean(KEY_TRUE,mTrueIsClicked);
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