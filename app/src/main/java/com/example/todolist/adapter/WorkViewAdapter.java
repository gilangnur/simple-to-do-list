package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.Work.ListWork;
import com.example.todolist.model.TaskModel;
import com.example.todolist.viewHolder.CategoriesViewHolder;
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
        return mListItem.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }





//    public static class MyAdapterViewHolder extends RecyclerView.ViewHolder{
//
//        public ImageView mImageView;
//        public TextView mTextView;
//        public MyAdapterViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
//            super(itemView);
//            mImageView = itemView.findViewById(R.id.imageView);
//            mTextView = itemView.findViewById(R.id.textView_judul);
//
//            /*
//            mImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION){
//                            listener.onDeleteClick(position);
//                        }
//                    }
//                }
//            });
//
//             */
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener!=null){
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION){
//                            listener.lihatItem(position);
//                        }
//                    }
//                }
//            });
//        }
//    }
}
