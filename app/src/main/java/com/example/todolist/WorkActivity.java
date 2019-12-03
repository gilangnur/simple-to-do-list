package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.model.CategoriesModel;
import com.example.todolist.model.TaskModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences sharedPreferences;

    private int position;
    private int jumlah;
    private String tmp;

    private Button buatWork;
    private ArrayList<TaskModel> taskModels;
    private ArrayList<CategoriesModel> categoriesModels;
    public TextView textView_jumlah;



    //@SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        loadData();

        //get intent
        position = getIntent().getIntExtra("POSITION", 0);
        tmp = getIntent().getStringExtra("WORK_LIST");

        taskModels = categoriesModels.get(position).getTasks();

        //set AppBar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        String title =  getIntent().getStringExtra("CATEGORY");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buatWork = (Button)findViewById(R.id.button_add);
        buatWork.setOnClickListener(this);
        textView_jumlah = findViewById(R.id.textView_jumlah);
        setTextView_jumlah();

        fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentList newFragment = new FragmentList();
        newFragment.setPosition(position);
        newFragment.setTaskModels(taskModels);


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
            addFragment.setPostion(position);
            fragmentTransaction.replace(R.id.list_work, addFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void loadData() {
        sharedPreferences = getSharedPreferences(getApplication().toString(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("LIST", null);
        Type type = new TypeToken<ArrayList<CategoriesModel>>() {}.getType();
        categoriesModels = gson.fromJson(json, type);
        if (categoriesModels == null) {
            categoriesModels = new ArrayList<>();
        }
    }

    public void setTextView_jumlah() {
        this.jumlah = categoriesModels.get(position).getTasks().size();
        textView_jumlah.setText(String.valueOf(jumlah));
    }
}
