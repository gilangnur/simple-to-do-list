package com.example.todolist.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolist.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkViewHolder extends RecyclerView.ViewHolder {

    private ImageView workImage;
    private TextView title, description, deadline;

    public WorkViewHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView != null) {
            workImage = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView_judul);
            description = itemView.findViewById(R.id.textView_deskripsi);
            deadline = itemView.findViewById(R.id.textView_deadline);

        }
    }

    public ImageView getWorkImage() {
        return workImage;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getDeadline() {
        return deadline;
    }
}
