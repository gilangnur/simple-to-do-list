package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.BottomSheet.AddCategoriesBottomSheet;
import com.example.todolist.BottomSheet.EditNameBottomSheet;
import com.example.todolist.adapter.CategoriesViewAdapter;
import com.example.todolist.model.CategoriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements EditNameBottomSheet.ItemClickListener, View.OnClickListener
{
    //private ArrayList<CategoriesModel> categoriesModels;

    private RecyclerView recyclerView;
    private CategoriesViewAdapter adapter;
    private Button addCategories;

    private SharedPreferences sharedPreferences;

    final String PERSON_NAME = "PERSON_NAME";
    private String DEFAULT_NAME = "Buddy";
    final String LIST = "LIST";
    private int workToDo = 0;

    private ArrayList<CategoriesModel> categoriesModels;
    private TextView ownerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addCategories = findViewById(R.id.btn_addCategories);
        addCategories.setOnClickListener(this);

        ownerName = findViewById(R.id.tv_greetName);
        sharedPreferences = getSharedPreferences(getApplication().toString(), Context.MODE_PRIVATE);
        String greetName = sharedPreferences.getString(PERSON_NAME, DEFAULT_NAME);
        ownerName.setText(greetName);

        loadData();
        recyclerView = this.findViewById(R.id.rv_categoriesFill);
        adapter = new CategoriesViewAdapter(categoriesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_preference: {
                showBottomSheet(item);
                break;
            }
            case R.id.action_refresh: {
                loadData();
                recyclerView = this.findViewById(R.id.rv_categoriesFill);
                adapter = new CategoriesViewAdapter(categoriesModels);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            }
        }
        return true;
    }

    public void showBottomSheet(MenuItem view) {
        EditNameBottomSheet addPhotoBottomDialogFragment = EditNameBottomSheet.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "TEST");
    }

    @Override
    public void onItemClick(String item) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addCategories) {
            AddCategoriesBottomSheet addCategoriesBottomSheet = AddCategoriesBottomSheet.newInstance();
            addCategoriesBottomSheet.show(getSupportFragmentManager(), "TEST");
        }
    }

    private void loadData() {
        sharedPreferences = getSharedPreferences(getApplication().toString(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIST, null);
        Type type = new TypeToken<ArrayList<CategoriesModel>>() {}.getType();
        categoriesModels = gson.fromJson(json, type);
        if (categoriesModels == null) {
            categoriesModels = new ArrayList<>();
        }
    }

    private int countWork() {
        loadData();
        for (int i = 0; i < categoriesModels.size(); i++) {
            workToDo += categoriesModels.get(i).getTasks().size();
        }
        return workToDo;
    }

}
