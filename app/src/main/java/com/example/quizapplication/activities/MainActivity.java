package com.example.quizapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quizapplication.R;
import com.example.quizapplication.adapters.QuizAdapter;
import com.example.quizapplication.models.Quiz;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    QuizAdapter adapter;
    FirebaseFirestore firestore;
    private ArrayList<Quiz> quizlist = new ArrayList<>();


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public Context context;
    public RecyclerView rv;
    public NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.appBar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDrawer);
        rv=findViewById(R.id.quizRecyclerView);
        navigationView=findViewById(R.id.navigationView);
        //setting drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // int id = item.getItemId();
                Log.i("DRAWER", item.getTitle().toString());
                String id =item.getTitle().toString();
                String pid="Profile";
                if( id.equals(pid)) {
                    Intent intent = new Intent(MainActivity.this, profileActivity.class);
                    MainActivity.this.startActivity(intent);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        GridLayoutManager gm=new GridLayoutManager(this,2);
        rv.setAdapter(adapter);
        rv.setLayoutManager(gm);
        setUpViews();


    }


    private void setUpViews(){
        setUpFireStore();
        setUpDrawerLayout();
        setUpRecyclerView();
        setUpDatePicker();
    }

    private void setUpDatePicker() {
        FloatingActionButton btnDatePicker = findViewById(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().build();
                datePicker.show(getSupportFragmentManager(),"DatePicker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Log.d("DatePicker", datePicker.getHeaderText());
                        String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        String date = simpleDateFormat.format(selection);
                        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                        intent.putExtra("DATE", datePicker.getHeaderText());
                        startActivity(intent);
                        finish();
                    }
                });
                datePicker.addOnNegativeButtonClickListener(view1 -> {
                    Log.d("DatePicker", datePicker.getHeaderText());
//                    Log.d("DATE", datePicker.getSelection().toString());

                });
                datePicker.addOnCancelListener(dialog -> {
                    Log.d("DatePicker", "Date Picker cancelled");
                });


            }
        });
    }

    private void setUpFireStore() {
        firestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firestore.collection("quizzes");
        collectionReference.addSnapshotListener((value, error) -> {
            if(value==null || error != null){
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("Data",value.toObjects(Quiz.class).toString());
            quizlist.clear();
            quizlist.addAll(value.toObjects(Quiz.class));
            adapter.notifyDataSetChanged();
        });

    }

    private void setUpRecyclerView() {
        adapter = new QuizAdapter(this, quizlist);
        RecyclerView quizRecyclerView = findViewById(R.id.quizRecyclerView);
        quizRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        quizRecyclerView.setAdapter(adapter);
    }

    private void setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.appBar));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,findViewById(R.id.mainDrawer),R.string.app_name,R.string.app_name);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}