package com.example.quizapplication.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.example.quizapplication.R;
import com.example.quizapplication.models.Question;
import com.example.quizapplication.models.Quiz;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class resultActivity extends AppCompatActivity {

    Quiz[] quiz = new Quiz[] {new Quiz()};
    Map<String, Question> questions = Map.of();
    TextView textscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d("SCORE", "working");
        textscore = findViewById(R.id.txtScore);
        setUpViews();
    }

    private void setUpViews() {
        String quizData = getIntent().getStringExtra("QUIZ");
        Gson gson = new Gson();
        quiz= gson.fromJson(quizData, Quiz[].class);
        questions = quiz[0].questions;
        Log.d("QUESTION", questions.toString());
        Log.i("SCORE", "working");
        calculatescore();
        setanwser();
    }

    private void setanwser() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        TextView ans;
        ans = findViewById(R.id.txtAnswer);
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get("question" + index);
            index++;
            stringBuilder.append("<font color '#18206f'><b>Question<br/>" + question.description + "</b></font><br/><br/>");
            stringBuilder.append("<font color '#18206f'>Answer<br/>" + question.answer + "</font><br/><br/><br/>");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ans.setText(Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            ans.setText(Html.fromHtml(stringBuilder.toString()));
        }
    }

    private void calculatescore() {
        int score = 0;
        int index = 1;
        Log.i("SCORE", "working");
        for (int i = 0; i < questions.size(); i++) {
            Question question= questions.get("question" + index);
            Log.i("GETING", questions.get("question" + index).toString());
            index++;
            Log.i("answer", question.answer+"");
            Log.i("answer", question.userAnswer+"");
            if(question.answer.equals(question.userAnswer)){
                score += 10;
                Log.i("SCORE", score+"");
            }
        }
        textscore.setText("Your Score: "+score);
    }
}