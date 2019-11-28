package com.example.todolist.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolist.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivIcon;
    private TextView tvTitle;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView != null) {
            ivIcon = itemView.findViewById(R.id.iv_categoryIcon);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public TextView getTvTitle() {
        return this.tvTitle;
    }

    public ImageView getIvIcon() {
        return this.ivIcon;
    }
}
