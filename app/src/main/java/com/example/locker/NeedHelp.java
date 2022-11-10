package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class NeedHelp extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);

        toolbar  = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NeedHelp.this,MainActivity.class);
                Toast.makeText(NeedHelp.this, "You pressed back Button", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NeedHelp.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
