package com.example.todolist.Work;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyAdapterViewHolder>{

    private ArrayList<ListWork> mListItem;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public WorkAdapter(ArrayList<ListWork> itemss_list){
        mListItem = itemss_list;

    }
    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_work,parent, false);
        MyAdapterViewHolder mavh = new MyAdapterViewHolder(v,mListener);
        return mavh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        ListWork currentItem = mListItem.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView.setText(currentItem.getText1());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class MyAdapterViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextView;
        public MyAdapterViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView_judul);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
