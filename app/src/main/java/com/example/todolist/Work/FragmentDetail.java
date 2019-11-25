package com.example.todolist.Work;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.R;


public class FragmentDetail extends Fragment {

    public FragmentDetail() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_detail, container, false);
    }


}
