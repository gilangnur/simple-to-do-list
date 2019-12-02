package com.example.todolist.Work;

import android.app.AlertDialog;
import android.app.Fragment;
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
import com.example.todolist.model.TaskModel;

import java.util.ArrayList;


public class FragmentList extends Fragment {
    private RecyclerView mRecyclerView;
    private WorkViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<TaskModel> listItems;
    public String jumlah;

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public FragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        createList();
        buildRecyclerView();
        setJumlah(String.valueOf(listItems.size()));
    }

    public void createList() {
        listItems = new ArrayList<>();
        listItems.add(new TaskModel("Gilang", "Makan"));
        listItems.add(new TaskModel("Alfis", "Minum"));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mAdapter = new WorkViewAdapter(listItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkViewAdapter.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void lihatItem(int position) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Test click " + listItems.get(position).getTitle(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity().getApplicationContext());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_item ,null);

                TextView judul = mView.findViewById(R.id.textView_judul);
                judul.setText(listItems.get(position).getTitle());

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                Log.d("TAG_CHECK", "lihatItem: " + dialog);
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
//                  AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity().getApplicationContext());
//                  View mview = getActivity().getLayoutInflater().inflate(R.layout.dialog_item,null);
//
//                  TextView judul = mview.findViewById(R.id.textView_judul);
//                  judul.setText(listItems.get(position).getTitle());
//
//
//                  mBuilder.setView(mview);
//                  AlertDialog dialog = mBuilder.create();
//                  dialog.show();
            }


        });
    }
}
