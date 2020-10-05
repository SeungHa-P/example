package com.example.example.Fragment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<String> txtData = null;

    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.recycleTxt);

        }
    }


    public RecycleAdapter(ArrayList<String> list,Context context){
        txtData = list;
        this.context =context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view =
                LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
        RecycleAdapter.ViewHolder vh = new RecycleAdapter.ViewHolder(view);


        return vh;
    }

    @Override
    public int getItemCount() {
        return txtData.size();
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        String text = txtData.get(position);
        holder.textView.setText(text);

    }
}
