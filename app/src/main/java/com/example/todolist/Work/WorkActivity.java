package com.example.todolist.Work;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.todolist.R;

public class WorkActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        getSupportActionBar().setTitle("Work");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentList newFragment = new FragmentList();
        fragmentTransaction.add(R.id.list_work, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
