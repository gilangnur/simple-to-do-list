package com.example.todolist;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.adapter.WorkViewAdapter;
import com.example.todolist.model.CategoriesModel;
import com.example.todolist.model.TaskModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FragmentList extends Fragment {

    private RecyclerView mRecyclerView;
    private WorkViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<CategoriesModel> categoriesModels;

    private int position;

    private String jumlah;
    private ArrayList<TaskModel> taskModels;

    public void setTaskModels(ArrayList<TaskModel> taskModels) {
        this.taskModels = taskModels;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FragmentList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        loadData();
        buildRecyclerView(categoriesModels);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void buildRecyclerView(ArrayList<CategoriesModel> categoriesModels) {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mAdapter = new WorkViewAdapter(categoriesModels.get(position).getTasks());
        taskModels = categoriesModels.get(position).getTasks();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkViewAdapter.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void lihatItem(int position) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity().getApplicationContext());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_item ,null);

                TextView judul = mView.findViewById(R.id.textView_judul);
                TextView deadLine = mView.findViewById(R.id.textView_deadline);
                TextView description = mView.findViewById(R.id.textView_deskripsi);
                ImageView imageView = mView.findViewById(R.id.image_work);

                judul.setText(taskModels.get(position).getTitle());
                deadLine.setText(taskModels.get(position).getDeadLine());
                description.setText(taskModels.get(position).getDescription());
                imageView.setImageURI(Uri.parse(taskModels.get(position).getImage()));

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                Log.d("TAG_CHECK", "lihatItem: " + dialog);
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();

            }


        });
    }

    private void loadData() {
        sharedPreferences = getActivity().getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("LIST", null);
        Type type = new TypeToken<ArrayList<CategoriesModel>>() {}.getType();
        categoriesModels = gson.fromJson(json, type);
        if (categoriesModels == null) {
            categoriesModels = new ArrayList<>();
        }
    }
}
