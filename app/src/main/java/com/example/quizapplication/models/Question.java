package com.example.quizapplication.models;

public class Question {
    public String description = "";
    public String option1 = "";
    public String option2 = "";
    public String option3 = "";
    public String option4 = "";
    public String answer = "";
    public String userAnswer = "";


    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
    
}

