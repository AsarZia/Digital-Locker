package com.example.locker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dmax.dialog.SpotsDialog;

public class New_Register extends AppCompatActivity {

    TextInputEditText txtName, txtEmail, txtPass, txtConfPass, txtMobile, txtBirth, txtCity, txtPost, txtWork;
    Button register;
    //ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__register);

        dialog = new ProgressDialog(New_Register.this,R.style.Custom);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtConfPass = findViewById(R.id.txtConfPass);
       /* txtName = findViewById(R.id.txtName);
        txtMobile = findViewById(R.id.txtMobile);
        txtBirth = findViewById(R.id.txtBirth);
        txtCity = findViewById(R.id.txtCity);
        txtPost = findViewById(R.id.txtPost);
        txtBirth = findViewById(R.id.txtBirth);*/
        register = findViewById(R.id.register);
        //progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtEmail.getText().toString().trim();
                String Pass = txtPass.getText().toString().trim();
                String Confpass = txtConfPass.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(New_Register.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Pass)) {
                    Toast.makeText(New_Register.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Confpass)) {
                    Toast.makeText(New_Register.this, "please enter Confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Pass.length() < 6) {
                    Toast.makeText(New_Register.this, "length too short", Toast.LENGTH_SHORT).show();
                }


                //progressBar.setVisibility(View.VISIBLE);


                dialog.setTitle("please wait!!");
                dialog.setMessage("Registering....");
                dialog.show();



                if (Pass.equals(Confpass)) {

                    firebaseAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(New_Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                   // progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(New_Register.this, "Registration complete", Toast.LENGTH_SHORT).show();
                                        /* FirebaseAuth.getInstance().signOut();*/

                                    }
                                }
                            });/*.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Registration.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });*/
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(New_Register.this, SignUp.class);
        startActivity(intent);
        finish();
    }
}
