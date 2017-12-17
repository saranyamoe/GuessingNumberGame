package com.saranyamoellers.guessingnumbergame.model;

/**
 * Created by saranya on 12/9/2017.
 */

public class GamePage {
    private int answerNumber;
    private int textQuestionId;
    private int hintId;

    //    private int quitChoice1;
//    private int nextLevelChoice2;
//    private Choice choice1;
//    private Choice choice2;

    //constructor
//    public GamePage(int answerNumber, int textQuestionId,int hintId, Choice choice1, Choice choice2) {
//        this.answerNumber = answerNumber;
//        this.textQuestionId = textQuestionId;
//        this.hintId = hintId;
//        this.numberOfGuess = 0;
////        this.choice1 = choice1;
////        this.choice2 = choice2;
//    }
    //constructor with 3 params
    public GamePage(int answerNumberId, int textQuestionId,int hintId) {
        this.answerNumber = answerNumberId;
        this.textQuestionId = textQuestionId;
        this.hintId = hintId;


    }
    //getter and setter
    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public int getTextQuestionId() {
        return textQuestionId;
    }

    public void setTextQuestionId(int textQuestionId) {
        this.textQuestionId = textQuestionId;
    }

    public int getHintId() {
        return hintId;
    }

    public void setHintId(int hintId) {
        this.hintId = hintId;
    }


//    public Choice getChoice1() {
//        return choice1;
//    }
//
//    public void setChoice1(Choice choice1) {
//        this.choice1 = choice1;
//    }
//
//    public Choice getChoice2() {
//        return choice2;
//    }
//
//    public void setChoice2(Choice choice2) {
//        this.choice2 = choice2;
//    }
}
