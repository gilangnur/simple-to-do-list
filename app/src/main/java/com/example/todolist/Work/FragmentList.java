package com.example.todolist.Work;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FragmentList extends Fragment {
    private RecyclerView mRecyclerView;
    private WorkAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ListWork> listItems;
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
        listItems.add(new ListWork(R.drawable.ic_add, "Ubuntu"));
        listItems.add(new ListWork(R.drawable.ic_add, "Android"));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new WorkAdapter(listItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(new WorkAdapter.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void lihatItem(int position) {
                Toast.makeText(getContext(),"Test click "+listItems.get(position).getJudul(),Toast.LENGTH_LONG).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mview = getLayoutInflater().inflate(R.layout.dialog_item,null);
                TextView judul = (TextView)mview.findViewById(R.id.textView_judul);
                TextView deskripsi = (TextView)mview.findViewById(R.id.textView_deskripsi);
                ImageView gambar = (ImageView)mview.findViewById(R.id.image_work);

                //isi
                judul.setText(listItems.get(position).getJudul());
                gambar.setImageResource(listItems.get(position).getImageResource());


                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }


        });
    }
}
