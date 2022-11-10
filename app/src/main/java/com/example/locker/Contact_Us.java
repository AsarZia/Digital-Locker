 package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class Contact_Us extends AppCompatActivity {
    Button send;
    TextView textView1, textView2, textView3, textView4;
     private Context context;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        send = findViewById(R.id.send);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(textView1.getText());
                Toast.makeText(Contact_Us.this,"Copied to clipboard",Toast.LENGTH_SHORT).show();
            }
        });

         textView2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                 cm.setText(textView2.getText());
                 Toast.makeText(Contact_Us.this,"Copied to clipboard",Toast.LENGTH_SHORT).show();
             }
         });


         textView3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                 cm.setText(textView3.getText());
                 Toast.makeText(Contact_Us.this,"Copied to clipboard",Toast.LENGTH_SHORT).show();
             }
         });


         textView4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                 cm.setText(textView4.getText());
                 Toast.makeText(Contact_Us.this,"Copied to clipboard",Toast.LENGTH_SHORT).show();
             }
         });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_Us.this,CustomPopup.class);
                startActivity(intent);
                finish();
            }
        });

    }



     @Override
     public void onBackPressed() {
         Intent intent = new Intent(Contact_Us.this,Dashboard.class);
         startActivity(intent);
         finish();
     }
 }
