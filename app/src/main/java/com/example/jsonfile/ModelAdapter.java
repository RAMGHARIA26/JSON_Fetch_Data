package com.example.jsonfile;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewModel> {
    Context context;
    List<Model> list;

    public ModelAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ModelAdapter.MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_lyt,parent,false);
        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.MyViewModel holder, int position) {
        holder.id.setText(list.get(position).getId());
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.body.setText(list.get(position).getBody());
        Log.d("Tagging",list.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        TextView id,name,body,email;
        public MyViewModel(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idText);
            name = itemView.findViewById(R.id.name_text);
            body = itemView.findViewById(R.id.body_text);
            email = itemView.findViewById(R.id.email_text);
        }
    }
}
