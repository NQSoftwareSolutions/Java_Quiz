package com.nqsoftwaresolutions.javaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA = "answer";
    private static final String RESULT_EXTRA = "Cheating";

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
            setAnswerShownResult(true);
        });
    }

    /**
     * @param isCheated It will be true it user cheat else false
     * This method will create an Intent & Send data that user has Cheated  or Not
     * we are using this method on Show Answer button so it mean user has cheated
     */
    private void setAnswerShownResult(boolean isCheated) {
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA, isCheated);
        setResult(RESULT_OK, data);
    }

    /**
     * @param receivedIntent Intent Received from Child Activity
     * @return true is user has cheated else false
     * This method will get Intent of child activity & also get data with it
     */
    public static boolean wasAnswerShown (Intent receivedIntent){
        return receivedIntent.getBooleanExtra(RESULT_EXTRA, false);
    }
}