package com.example.quizapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.activities.MainActivity;
import com.example.quizapplication.activities.QuestionActivity;
import com.example.quizapplication.models.Quiz;
import com.example.quizapplication.utils.ColorPicker;
import com.example.quizapplication.utils.IconPicker;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context context;
    private ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();

    public QuizAdapter(Context context, ArrayList<Quiz> quizlist) {
        this.context = context;
        this.quizArrayList = quizlist;
    }

    @NonNull
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item,parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        ColorPicker colorPicker = new ColorPicker();
        IconPicker iconPicker = new IconPicker();
        holder.textTitleView.setText(this.quizArrayList.get(position).title);
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.getColor()));
        holder.iconView.setImageResource(iconPicker.getIcons());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,quizArrayList.get(position).title,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("DATE",quizArrayList.get(position).title);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView textTitleView;
        ImageView iconView;
        CardView cardContainer;
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitleView = itemView.findViewById(R.id.quizTitle);
            iconView = itemView.findViewById(R.id.quizIcon);
            cardContainer = itemView.findViewById(R.id.cardContainer);
        }
    }
}
