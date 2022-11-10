package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public  class CodeScanner extends AppCompatActivity  {
    private long backPressedTime;
    public static TextView result_scan;
    Button btn_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);

        result_scan = findViewById(R.id.result_scan);
        btn_scan = findViewById(R.id.btn_scan);


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CodeScanner.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
