package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button buatWork;
    public TextView textView_jumlah;


    //@SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        String title =  getIntent().getStringExtra("CATEGORY");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(title);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        buatWork = (Button)findViewById(R.id.button_add);
        buatWork.setOnClickListener(this);

        fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentList newFragment = new FragmentList();

        fragmentTransaction.add(R.id.list_work, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_add) {
            Toast.makeText(WorkActivity.this,"buat pengingat", Toast.LENGTH_LONG).show();

            fragmentTransaction = getFragmentManager().beginTransaction();
            AddEvent addFragment = new AddEvent();

            fragmentTransaction.replace(R.id.list_work, addFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
