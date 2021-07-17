package com.example.quizapplication.models;

import java.util.HashMap;
import java.util.Map;

public class Quiz {
    public String id = "";
    public String title = "";
    public Map<String, Question> questions;

    public Quiz() {
    }
//
    public Quiz(String id, String title,Map<String,Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }

}
