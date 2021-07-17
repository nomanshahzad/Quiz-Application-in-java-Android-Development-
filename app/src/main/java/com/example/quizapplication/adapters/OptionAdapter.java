package com.example.quizapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.models.Question;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {
    public Context context;
    public ArrayList<String> options = new ArrayList<>();
    public Question question=new Question();
    public String[] option={question.option1,question.option2,question.option3,question.option4};

//    public OptionAdapter(){
//    }

    public OptionAdapter(Question que) {
        this.options.add(que.option1);
        this.options.add(que.option2);
        this.options.add(que.option3);
        this.options.add(que.option4);
        this.question = que;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.option_item,parent,false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String question_option = options.get(position);
        holder.optionView.setText(question_option+"");

        holder.optionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Noman", question_option);
                question.userAnswer = options.get(position);
                notifyDataSetChanged();

            }
        });

        if(question.userAnswer == question_option){
            holder.itemView.setBackgroundResource(R.drawable.selected_option_bg);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg);
        }
    }

    @Override
    public int getItemCount() {
        return option.length;
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {
        private TextView optionView;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionView=itemView.findViewById(R.id.quiz_option);
        }
    }
}
