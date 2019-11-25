package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todolist.Work.WorkActivity;

public class MainActivity extends AppCompatActivity {
    private Button Button_work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_work = (Button)findViewById(R.id.pindah_work);

        Button_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkActivity.class);
                startActivity(intent);
            }
        });
    }
}
