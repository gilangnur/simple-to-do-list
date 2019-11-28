package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.todolist.adapter.CategoriesViewAdapter;
import com.example.todolist.model.CategoriesModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements ActionBottomDialogFragment.ItemClickListener
{
    //private ArrayList<CategoriesModel> categoriesModels;

    private RecyclerView recyclerView;
    private CategoriesViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ArrayList<CategoriesModel> categoriesModels = new ArrayList<>();
        categoriesModels.add(new CategoriesModel("Work"));
        categoriesModels.add(new CategoriesModel("College"));

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
                Log.d("LOG_TEST", "onOptionsItemSelected: ");
                break;
            }
        }
        return true;
    }

    public void showBottomSheet(MenuItem view) {
        ActionBottomDialogFragment addPhotoBottomDialogFragment =
                ActionBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "TES");
    }

    @Override
    public void onItemClick(String item) {
        Log.d("LOG", "onItemClick: ");
    }
}
