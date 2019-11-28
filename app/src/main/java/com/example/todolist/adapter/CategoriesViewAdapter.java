package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.R;
import com.example.todolist.model.CategoriesModel;
import com.example.todolist.viewHolder.CategoriesViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesViewAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<CategoriesModel> list;

    public CategoriesViewAdapter(ArrayList<CategoriesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        layoutInflater = LayoutInflater.from(context);
        View categorisListItem = layoutInflater.inflate(R.layout.categories_item, parent, false);
        CategoriesViewHolder result = new CategoriesViewHolder(categorisListItem);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        if (holder != null) {
            CategoriesModel model = list.get(position);
            if (model != null) {
                holder.getTvTitle().setText(model.getCategoryName());
            }
        }
    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (list != null) {
            result = list.size();
        }
        return result;
    }
}
