package com.example.quizapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Questions {
    String questions;
    String[] answers;
    int rightAnswerIndex;

    public Questions(String questions, String[] answers, int rightAnswerIndex) {
        this.questions = questions;
        this.answers = answers;
        this.rightAnswerIndex = rightAnswerIndex;
    }

    // function will check answer is correct or not
    boolean validateAnswer(String givenAnswer)
    {
        return (givenAnswer.equals(getAnswer()));
    }


    // function will return questions from questionlist
     String getQuestionTitle()
    {
        return questions;
    }

    // function will return right answer
     String getAnswer()
    {
        return answers[rightAnswerIndex];
    }

    // function will return array of answers which will become options
     String[] getChoices()
    {
        return answers;
    }

    // function will get index of right answer
     String getAnswerAt(int index)
    {
        return answers[index];
    }
}
