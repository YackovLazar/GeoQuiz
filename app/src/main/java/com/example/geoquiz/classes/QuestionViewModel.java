package com.example.geoquiz.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class QuestionViewModel extends ViewModel
{
    private final Question[] quiz = {new Question("Rino is the capitol of the American state, Arizona", false),
            new Question("Germany has 14 federal states", false),
            new Question("The derisive term \"PIIGS\" refers to Porugal, Ireland, Italy, Greese, Spain", true),
            new Question("China has 11 zones", false),
            new Question("Ohio is the only state in the U.S. to have and flag without 4 edges", true)};
    private boolean naughty = false;
    private int index = 0;
    private Question current = quiz[index];

    public void setupQuiz()
    {
        index = 0;
        current = quiz[index];
    }

    public boolean cheatCheck()
    {
        return naughty;
    }

    public String getResult(boolean guess)
    {
        if (current.getIfTrue(guess))
            return "You are correct! ";
        return "WRONG!!!";
    }

    public String getQuestion()
    {
        return current.getQuestion();
    }

    public void nextQuestion()
    {
        current = quiz[++index];
    }

    public void setNaughty(boolean isNaughty)
    {
        naughty = isNaughty;
    }

    public boolean getAnswer()
    {
        return current.getIfTrue(true);
    }

    public boolean ifNext()
    {
        return index < quiz.length - 1;
    }
}
