package com.nqsoftwaresolutions.javaquiz.data_model;

public class Questions {
    private int mTextResId;
    private final boolean mAnswerTrue;

    public Questions(int mTextResId, boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

// --Commented out by Inspection START (8/11/2021 11:39 AM):
//    public void setTextResId(int mTextResId) {
//        this.mTextResId = mTextResId;
//    }
// --Commented out by Inspection STOP (8/11/2021 11:39 AM)

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

// --Commented out by Inspection START (8/11/2021 11:38 AM):
//    public void setAnswerTrue(boolean mAnswerTrue) {
//        this.mAnswerTrue = mAnswerTrue;
//    }
// --Commented out by Inspection STOP (8/11/2021 11:38 AM)
}
