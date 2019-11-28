package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements ActionBottomDialogFragment.ItemClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
