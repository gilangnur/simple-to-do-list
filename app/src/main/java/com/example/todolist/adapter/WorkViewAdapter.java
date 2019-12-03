package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.model.TaskModel;
import com.example.todolist.viewHolder.WorkViewHolder;

import java.util.ArrayList;

public class WorkViewAdapter extends RecyclerView.Adapter<WorkViewHolder>{

    private ArrayList<TaskModel> mListItem;
    private OnItemClickListener mListener;
    private LayoutInflater layoutInflater;

    public interface OnItemClickListener{
        void lihatItem(int position);
    }

    public WorkViewAdapter(ArrayList<TaskModel> itemss_list){
        mListItem = itemss_list;

    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        layoutInflater = LayoutInflater.from(context);
        View worksListItem = layoutInflater.inflate(R.layout.work_item, parent, false);
        WorkViewHolder result = new WorkViewHolder(worksListItem);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkViewHolder holder, int position) {
        if (mListItem != null) {
            TaskModel taskModel = mListItem.get(position);
            if (taskModel != null) {
                holder.getTitle().setText(taskModel.getTitle());
                holder.getDescription().setText(taskModel.getDescription());
                holder.getDeadline().setText(taskModel.getDeadLine());
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.lihatItem(position);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mListItem == null) {
            return 0;
        }
        return mListItem.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
