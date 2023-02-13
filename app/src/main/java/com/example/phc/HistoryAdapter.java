package com.example.phc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private Context context;
    Activity activity2;
    private ArrayList title;

    HistoryAdapter(Activity activity, Context context, ArrayList title){
        this.activity2 = activity;
        this.context = context;
        this.title = title;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtItemTitle.setText(String.valueOf(title.get(position)));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemTitle = itemView.findViewById(R.id.txtItemTitle);
        }
    }
}
