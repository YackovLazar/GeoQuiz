package com.example.geoquiz.classes;

import androidx.lifecycle.LiveData;

import java.util.Objects;

public class Question extends LiveData<Question> {

    private final String question;
    private final Boolean ifTrue;

    public Question(String question, Boolean ifTrue)
    {
        this.question = question;
        this.ifTrue = ifTrue;
    }

    public Boolean getIfTrue(boolean check)
    {
        return Objects.equals(check, ifTrue);
    }

    public String getQuestion()
    {
        return question;
    }
}
