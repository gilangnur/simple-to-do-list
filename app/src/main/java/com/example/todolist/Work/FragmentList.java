package com.example.todolist.Work;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;

import java.util.ArrayList;


public class FragmentList extends Fragment {
    private RecyclerView mRecyclerView;
    private WorkAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ListWork> listItems;


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
    }

    public void createList() {
        listItems = new ArrayList<>();
        listItems.add(new ListWork(R.drawable.ic_add, "Ubuntu"));
        //listItems.add(new ListWork(R.drawable.ic_android, "Android"));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new WorkAdapter(listItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkAdapter.OnItemClickListener() {
            fragmentManager = getActivity().getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            public void onDeleteClick(int position) {
                //Toast.makeText(getActivity(), "you deleted : ", Toast.LENGTH_LONG).show();
                FragmentDetail lmfragment = new FragmentDetail();
                //lmfragment.setNilai(getNilai());
                fragmentTransaction.replace(R.id.list_work, lmfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            public void onClick(View v) {
                FragmentDetail lmfragment = new FragmentDetail();
                lmfragment.setNilai(getNilai());
                fragmentTransaction.replace(R.id.ui_container, lmfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
