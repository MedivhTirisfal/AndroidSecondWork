package com.medivh20.androidsecondwork.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.medivh20.androidsecondwork.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.StringViewHolder> {
    private ArrayList<String> list;
    private int index;
    public RvAdapter(ArrayList<String> list,int index) {
        this.list = list;
        this.index = index;
    }

    class StringViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView materialTextView = itemView.findViewById(R.id.home_string);
        ShapeableImageView remove = itemView.findViewById(R.id.home_remove);

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RvAdapter.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_string,
                parent,
                false
        );
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        holder.materialTextView.setText(list.get(position));
        Set<String> set = new HashSet<>();
        set.add((String )holder.materialTextView.getText());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerItem.list.get(index).remove(position);
                notifyItemRemoved(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItem(String t){
        notifyItemInserted(getItemCount()-1);
    }
}
