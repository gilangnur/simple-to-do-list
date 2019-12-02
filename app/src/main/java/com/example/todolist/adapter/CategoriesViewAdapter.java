package com.example.todolist.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.todolist.R;
import com.example.todolist.Work.WorkActivity;
import com.example.todolist.model.CategoriesModel;
import com.example.todolist.model.TaskModel;
import com.example.todolist.viewHolder.CategoriesViewHolder;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesViewAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    public static final int REQUEST_CODE = 400;
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
    public void onBindViewHolder(@NonNull final CategoriesViewHolder holder, final int position) {
        if (holder != null) {
            CategoriesModel model = list.get(position);
            if (model != null) {
                holder.getTvTitle().setText(model.getCategoryName());
                holder.getTvTitle().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<TaskModel> taskModels = list.get(position).getTasks();
                        Intent intent = new Intent(v.getContext(), WorkActivity.class);
                        intent.putExtra("CATEGORY", String.valueOf(holder.getTvTitle().getText()));
                        intent.putExtra("WORK_LIST", taskModels);
                        v.getContext().startActivity(intent);
                        Toast toast = Toast.makeText(v.getContext(), holder.getTvTitle().getText().toString(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
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
