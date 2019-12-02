package com.example.todolist.Work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.R;

public class WorkActivity extends AppCompatActivity {

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
        textView_jumlah = (TextView)findViewById(R.id.textView_jumlah);

        FragmentList fragmentList = new FragmentList();
        textView_jumlah.setText(fragmentList.getJumlah());


        buatWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkActivity.this,"buat pengingat", Toast.LENGTH_LONG).show();
            }
        });

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentList newFragment = new FragmentList();
        fragmentTransaction.add(R.id.list_work, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
