package com.example.collegeapp_amenahussain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    Context parentContext;
    ArrayList<String> list;

    public RecyclerAdapter(Context context,ArrayList<String> list){
        parentContext = context;
        this.list=list;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parentContext).inflate(R.layout.activity_adapterview,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        //holder.textView.setText("Position # "+position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        //Create this class first and link widgets - xml will be inflated in a different spot
        //This is where all of the widgets will go and will be attached to their IDs in R
        Button favbutton;
        TextView rvrecipeName, rvcals, rvprotein, rvfats, rvlink;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("TAG","RecyclerViewHolder");
            favbutton = itemView.findViewById(R.id.adapter_favbutton);
            rvrecipeName = itemView.findViewById(R.id.adapter_recipename);
            rvcals = itemView.findViewById(R.id.adapter_cal);
            rvfats = itemView.findViewById(R.id.adapter_fat);
            rvprotein = itemView.findViewById(R.id.adapter_prot);
            rvlink = itemView.findViewById(R.id.adapter_recipelink);
        }


    }
}
