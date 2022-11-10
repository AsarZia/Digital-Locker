package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    TextView login, signup, scan_QR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login  = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        scan_QR = findViewById(R.id.scan_QR);


        scan_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CodeScanner.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                Toast.makeText(MainActivity.this, "Sign In", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                Toast.makeText(MainActivity.this, "Sign Up", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(),"press back again to exit",Toast.LENGTH_SHORT).show();
        }


        backPressedTime = System.currentTimeMillis();

    }
}
