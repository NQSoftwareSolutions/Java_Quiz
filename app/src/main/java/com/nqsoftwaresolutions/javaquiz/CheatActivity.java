package com.nqsoftwaresolutions.javaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA = "answer";
    private static final String RESULT_EXTRA = "Cheating";
    private TextView mShowAnswerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mShowAnswerTv = findViewById(R.id.id_txt_show_answer);
    }

    /**Todo set data for parent activity that user cheated or not
     * @param isCheated It will be true it user cheat else false
     * This method will create an Intent & Send data that user has Cheated  or Not
     * we are using this method on Show Answer button so it mean user has cheated
     */
    private void setAnswerShownResult(boolean isCheated) {
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA, isCheated);
        setResult(RESULT_OK, data);
    }

    /**Todo get the result from received intent
     * @param receivedIntent Intent Received from Child Activity
     * @return true is user has cheated else false
     * This method will get Intent of child activity & also get data with it
     * this method will be called in parent activity
     */
    public static boolean wasAnswerShown (Intent receivedIntent){
        return receivedIntent.getBooleanExtra(RESULT_EXTRA, false);
    }

    /**Todo Cheat Button is Clicked
     * @param view Button
     *             Todo Get answer from intent & if it is true
     *              then set it on text view else false
     */
    public void cheatingButtonClicked(View view) {
        boolean answer = getIntent().getBooleanExtra(EXTRA, false);
        if (answer){
            mShowAnswerTv.setText(R.string.aTrue);
        }else {
            mShowAnswerTv.setText(R.string.aFalse);
        }
        setAnswerShownResult(true);
    }
}