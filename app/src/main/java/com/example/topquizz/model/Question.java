package com.example.topquizz.model;
/*
    Created by Edghi Makol on 22/02/21 at 01:53
*/

import java.util.List;

/* Classe pour representer une question, ses choix possibles et la reponse
correcte */
public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setChoiceList(List<String> choiceList) {
        if(choiceList == null)
            throw new IllegalArgumentException("La liste ne peut etre nulle");
        mChoiceList = choiceList;
    }

    public void setAnswerIndex(int answerIndex) {
        if(answerIndex < 0 || answerIndex >= mChoiceList.size())
            throw  new IllegalArgumentException("L'index est hors des bornes");
        mAnswerIndex = answerIndex;
    }
}
