package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    RecycleAdapter(Context context,List<Note> notes) {
        this.inflater=LayoutInflater.from(context);
        this.notes=notes;
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             String title=notes.get(position).getTitle();
             String date=notes.get(position).getDate();
             String time=notes.get(position).getTime();
              holder.Title.setText(title);
              holder.Date.setText(date);
              holder.Time.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView Title,Details,Date,Time,id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.custom_title_name);
            Date=itemView.findViewById(R.id.custom_date);
            Time=itemView.findViewById(R.id.custom_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),Details.class);
                    intent.putExtra("ID",notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}
