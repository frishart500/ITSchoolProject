package com.example.itschoolproject;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Item> parseItems;
    private Context context;

    public Adapter(ArrayList<Item> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item parseItem = parseItems.get(position);
        holder.textUser.setText(parseItem.getUser());
        holder.textSubject.setText(parseItem.getSubject());
    }

    @Override
    public int getItemCount(){
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textUser, textSubject;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageOfUser);
            textSubject = itemView.findViewById(R.id.subjectOfUser);
            textUser = itemView.findViewById(R.id.textOfUser);


        }


    }
}
