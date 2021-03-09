package com.example.topquizz.model;
/*
    Created by Edghi Makol on 22/02/21 at 02:13
*/

import java.util.Collections;
import java.util.List;

/* Classe pour representer l'ensemble des questions */
public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        // Melange la liste
        Collections.shuffle(questionList);

        mNextQuestionIndex = 0;
    }

    public Question getQuestion(){
        if(mNextQuestionIndex == mQuestionList.size())
            mNextQuestionIndex = 0;
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
