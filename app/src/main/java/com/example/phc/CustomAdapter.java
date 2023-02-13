package com.example.phc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity1;
    private ArrayList username, rating, comment;

    CustomAdapter(Activity activity, Context context, ArrayList username, ArrayList rating, ArrayList comment){
        this.activity1 = activity;
        this.context = context;
        this.username = username;
        this.rating = rating;
        this.comment = comment;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtUsername.setText(String.valueOf(username.get(position)));
        holder.rbRating.setRating((Float) rating.get(position));
        holder.txtReviewComment.setText(String.valueOf(comment.get(position)));
    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtUsername, txtReviewComment;
        RatingBar rbRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtReviewComment = itemView.findViewById(R.id.txtReviewComment);
            rbRating = itemView.findViewById(R.id.rbRating);
        }
    }
}
