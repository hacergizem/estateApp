package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList input_id, input_title, input_town, input_pager;

    CustomAdapter(Context context,
                  ArrayList input_id,
                  ArrayList input_title,
                  ArrayList input_town,
                  ArrayList input_pager) {

        this.context = context;
        this.input_id = input_id;
        this.input_title = input_title;
        this.input_town = input_town;
        this.input_pager = input_pager;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.input_id.setText(String.valueOf(input_id.get(position)));
        holder.input_title.setText(String.valueOf(input_title.get(position)));
        holder.input_town.setText(String.valueOf(input_town.get(position)));
        holder.input_pager.setText(String.valueOf(input_pager.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id", String.valueOf(input_id.get(position)));
                intent.putExtra("title", String.valueOf(input_title.get(position)));
                intent.putExtra("town", String.valueOf(input_town.get(position)));
                intent.putExtra("pager", String.valueOf(input_pager.get(position)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return input_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView input_id, input_title, input_town, input_pager;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            input_id = itemView.findViewById(R.id.textCount);
            input_title = itemView.findViewById(R.id.textStatus);
            input_town = itemView.findViewById(R.id.textCity);
            input_pager = itemView.findViewById(R.id.textAdress);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
