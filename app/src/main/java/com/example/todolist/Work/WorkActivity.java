package com.example.todolist.Work;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.todolist.R;

public class WorkActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button buatWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        getSupportActionBar().setTitle("Work");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buatWork = (Button)findViewById(R.id.button_add);

        buatWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkActivity.this,"buat pengingat", Toast.LENGTH_LONG).show();
            }
        });

        //fragment
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentList newFragment = new FragmentList();
        fragmentTransaction.add(R.id.list_work, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
