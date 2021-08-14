package com.nqsoftwaresolutions.javaquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nqsoftwaresolutions.javaquiz.data_model.Questions;

import static com.nqsoftwaresolutions.javaquiz.CheatActivity.IS_CHEATED_KEY;


public class MainActivity extends AppCompatActivity {

    //Todo All global variables for views
    private Button mFalseButton, mTrueButton;
    private TextView mQuestionTextView;
    //Todo All java variables
    private int mCurrentIndex = 0;
    private boolean mTrueIsClicked, mFalseIsClicked;
    private boolean mIsCheater;

    //Todo All Java Constants
    private static final String TAG = "MainActivity";
    private static final String EXTRA = "answer";
    private static final int REQ_CODE_CHEAT = 0;
    private static final String KEY_INDEX = "index";
    private static final String KEY_FALSE = "isFalse";
    private static final String KEY_TRUE = "isTrue";

    //Todo Questions
    private final Questions[] mQuestionsBank = new Questions[]{
            new Questions(R.string.java_fundamentals,false),
            new Questions(R.string.java_fundamentals2,true),
            new Questions(R.string.java_fundamentals3,false),
            new Questions(R.string.java_fundamentals4,false),
            new Questions(R.string.java_fundamentals5,false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Activity Created....");
        setContentView(R.layout.activity_main);

        //Todo set reference to widgets
        mFalseButton = findViewById(R.id.id_btn_false);
        mTrueButton = findViewById(R.id.id_btn_true);
        mQuestionTextView = findViewById(R.id.id_txt_question);


        checkLastActivityState(savedInstanceState);
        Log.d(TAG,"Current Value of mIsCheated = "+mIsCheater);

        updateQuestion();
    }

    /**Todo Check last state of activity
     * @param savedInstanceState Bundle of values which saved
     *                           when previous activity was stopped.
     *                           Todo Challenge 3.1 Prevent user from multiple answers
     *                           set clickable true
     * This method will get the last saved data from previous activity.
     */
    private void checkLastActivityState(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mIsCheater = savedInstanceState.getBoolean(IS_CHEATED_KEY);
            mTrueIsClicked = savedInstanceState.getBoolean(KEY_TRUE);
            mFalseIsClicked = savedInstanceState.getBoolean(KEY_FALSE);
            if (mTrueIsClicked || mFalseIsClicked){
                mFalseButton.setClickable(true);
                mTrueButton.setClickable(true);
            }
        }

    }

    /**Todo Get results from Child Activity
     * @param requestCode of child activity from which parent activity is receiving data
     * @param resultCode send by chilled activity
     * @param data Intent send by chilled with data
     *             This method will get all of parameters & tell parent activity about chilled activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQ_CODE_CHEAT){
            if (data == null){
                return;
            }else {
                mIsCheater = CheatActivity.wasAnswerShown(data);
            }
        }
    }

    /**Todo Check Answer of question
     * @param b provided answer
     * this Method will check the answer or user that it is correct or not
     */
    private void checkAnswer(boolean b) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
        if (mIsCheater){
            messageResId = R.string.judgement_msg;
        }else{
            if (b == answerIsTrue){
                messageResId = R.string.correct;
            }else {
                messageResId = R.string.notCorrect;
            }
        }

        Toast mToast = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.TOP,1,80);
        mToast.show();
    }

    /**
     * This method will update question & set on text view
     */
    private void updateQuestion() {
        //Log.d(TAG, "Updating Question Text", new Exception());
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**Todo Save state of activity
     * @param outState state of application
     *      This method will save the state of activity so when activity recreate,
     *                it will assign previous values to it.
     *                Todo Challenge 5.1 saving value of mIsCheater
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(IS_CHEATED_KEY, mIsCheater);
        outState.putBoolean(KEY_FALSE,mFalseIsClicked);
        outState.putBoolean(KEY_TRUE,mTrueIsClicked);
    }

    /**
     * This Method will called when OS will start the activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Activity Started...");
    }

    /**
     * This method will called when OS will resume the Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity Resumed...");
        Log.d(TAG,"Current Value of mIsCheated = "+mIsCheater);
    }

    /**
     * This method will called when OS pause the activity
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Activity paused...");
    }

    /**
     * This method will called by OS when activity is stopped to work
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Activity Stopped....");
    }

    /**
     * This method will called by OS when activity will be destroyed from memory
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Activity Destroyed....");
    }

    /**Todo When True button is pressed
     * @param view button view
     *             this method will called when user clicks on true
     *             Todo Challenge 1.1 customize Toast
     *             show Toast on top of the screen
     *             Todo Challenge 3.1 Prevent user from multiple answers
     *             set clickable false
     *
     */
    public void trueButtonClicked(View view) {
        checkAnswer(true);
        mFalseButton.setClickable(false);
        mTrueButton.setClickable(false);
    }

    /**Todo When False button is pressed
     * @param view of button
     *             this method will be called when user pressed on false button
     *             Todo Challenge 3.1 Prevent user from multiple answers
     *             set clickable false
     */
    public void falseButtonClicked(View view) {
        checkAnswer(false);
        mFalseButton.setClickable(false);
        mTrueButton.setClickable(false);
    }

    /**Todo when prev button is pressed
     * @param view ImageButton
     *             Todo Challenge 2.2 set previous button
     *             Create a new button & add listener on it
     *             Update index & question in backward when clicked
     *             Todo Challenge 3.1 Prevent user from multiple answers
     *             set clickable false
     *             Todo Challenge 5.1 Closing loop holes for cheating
     *             Make mIsCheater value same as it was when activity was created
     */
    public void prevButtonClicked(View view) {
        if (mCurrentIndex == 0){
            Toast toast = Toast.makeText(MainActivity.this, "Your are on first Question", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,1,80);
            toast.show();
        }else {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
            updateQuestion();
                /*
                 */
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        }
    }

    /**Todo When Question Text view is pressed
     * @param view Text View
     *             Make mIsCheater = false b/c user has not see upcoming answer,
     *             he is cheated only current question
     *             Todo Check user is on last question or not
     *             Todo Challenge 2.1 set listener on TextView
     *             we are going to create a event on Question text view
     *             so we can get next question if we click on it
     *             Todo Challenge 3.1 Prevent user from multiple answers
     *             set clickable true
     */
    public void questionTextViewClicked(View view) {
        mIsCheater = false;
        if (mCurrentIndex == 4) {
            Toast toast = Toast.makeText(MainActivity.this,
                    "Your are on last Question", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 1, 80);
            toast.show();
        } else {
            mCurrentIndex++;
            updateQuestion();
            mFalseButton.setClickable(true);
            mTrueButton.setClickable(true);
        }
    }

    /**Todo When next button Clicked
     * @param view ImageButton
     *             Make mIsCheater = false b/c user has not see upcoming answer,
     *             he is cheated only current question
     *             Todo Check user is on last question or not
     *             Todo update question & index with click
     */
    public void nextButtonClicked(View view) {
        mIsCheater = false;
        if (mCurrentIndex == 4){
            Toast toast = Toast.makeText(MainActivity.this,
                    "Your are on last Question", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,1,80);
            toast.show();
        }else {
            mCurrentIndex++;
            updateQuestion();
            mFalseButton.setClickable(true);
            mTrueButton.setClickable(true);
        }
    }

    /**Todo when cheat button is clicked
     * @param view button
     *             Todo create a listener on show button to go on cheat activity
     *             Todo Get the answer of current indexed question &
     *               send as extra value with intent
     *             Todo not we want get result back from child activity that
     *             our user has cheated or not
     *             our user see the answer or not
     *
     */
    public void showAnswerButtonClicked(View view) {
        boolean correctAnswer = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        Intent startCheatActivity = new
                Intent(MainActivity.this, CheatActivity.class);
        startCheatActivity.putExtra(EXTRA, correctAnswer);
        startActivityForResult(startCheatActivity, REQ_CODE_CHEAT);
    }
}