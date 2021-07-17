package com.example.quizapplication.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.example.quizapplication.adapters.OptionAdapter;
import com.example.quizapplication.models.Question;
import com.example.quizapplication.models.Quiz;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class QuestionActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    Intent intent;
    Gson gson =new Gson();
    Map<String,Question> questions = Map.of();
    ArrayList<Quiz> quizzes = new ArrayList<>();
    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setUpFireStore();
        setUpEventListeners();
    }

    private void setUpEventListeners() {
        Button btnNext;
        Button btnPrevious;
        Button btnSubmit;

        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnprev", index+"");
                index = index - 1;
                bindViews();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnNext", index+"");
                index = index + 1;
                bindViews();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("QUE", questions + "");
                Intent intent = new Intent(QuestionActivity.this,resultActivity.class);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                String json=gson.toJson(quizzes);
                String extra=gson.toJson(quizzes);
                intent.putExtra("QUIZ",extra);
                startActivity(intent);
                finish();

            }
        });
    }

    private void setUpFireStore() {
        firestore = FirebaseFirestore.getInstance();
        String date = getIntent().getStringExtra("DATE");
        if(date!=null){
            firestore.collection("quizzes")
                    .whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()){
                                quizzes.addAll(queryDocumentSnapshots.toObjects(Quiz.class));
                                questions = quizzes.get(0).questions;
                                bindViews();
                            }

//


                        }
                    });

        }


    }

    private void bindViews() {
        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnPrevious.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        if (index == 1) {
            btnNext.setVisibility(View.VISIBLE);
        }
        else if (index == questions.size() || questions.size() == 1) {
            btnSubmit.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);
        }
        else
        {
            btnNext.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);

        }

//        Question question = new Question();
        Question question = questions.get("question" + index);
//        question.description = " Who Am i ?";
//        question.option1 = "Imran";
//        question.option2 = "noor";
//        question.option3 = "NOMAN";
//        question.option4 = "QAARI SAHB";
//        question.answer="NOMAN";

        if(question!=null){
            TextView description = findViewById(R.id.description);
            description.setText(question.description);

            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.optionList);
            OptionAdapter optionAdapter = new OptionAdapter(question);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(optionAdapter);


        }







//        onCreateDescription(question.description);
    }

}