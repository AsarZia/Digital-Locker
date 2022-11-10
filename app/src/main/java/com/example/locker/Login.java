package com.example.locker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {
    TextView help_in, signup, tv_ForgotPassword;
    Button login_btn;
    TextInputEditText txtEmail,txtPass;
    private FirebaseAuth firebaseAuth;
    //ProgressDialog dialog;
    public AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //dialog = new ProgressDialog(Login.this,R.style.Custom);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        login_btn = findViewById(R.id.login_btn);
        help_in = findViewById(R.id.help_in);
        signup = findViewById(R.id.signup);
        tv_ForgotPassword = findViewById(R.id.tv_ForgotPassword);



        firebaseAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtEmail.getText().toString().trim();
                String Pass = txtPass.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Login.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Pass)){
                    Toast.makeText(Login.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Pass.length()<6){
                    Toast.makeText(Login.this, "length too short", Toast.LENGTH_SHORT).show();
                    return;
                }

//
//                dialog.setTitle("please wait!!");
//                dialog.setMessage("Loading....");
//                dialog.show();

                final android.app.AlertDialog alertDialog= new
                        SpotsDialog.Builder().setContext(Login.this).setTheme(R.style.Custom).build();
                alertDialog.setMessage("Please wait.....");
                alertDialog.show();





                firebaseAuth.signInWithEmailAndPassword(Email, Pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                    Toast.makeText(Login.this, "login successful", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(Login.this, "login failed or user not available", Toast.LENGTH_SHORT).show();

                                }
                                alertDialog.dismiss();
                            }
                        });/*.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login_Form.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/


            }
        });

        help_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,NeedHelp.class);
                Toast.makeText(Login.this, "Help", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        tv_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
