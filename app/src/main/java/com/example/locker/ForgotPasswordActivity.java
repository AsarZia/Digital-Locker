package com.example.locker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextInputEditText etPasswordEmail;
    private Button btnPasswordReset;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etPasswordEmail = findViewById(R.id.etPasswordEmail);
        btnPasswordReset = findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = etPasswordEmail.getText().toString().trim();
                
                if(useremail.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this,"Please enter your registered email Id",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this,"password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordActivity.this,Login.class));
                            }else {
                                Toast.makeText(ForgotPasswordActivity.this,"Error !! please enter valid email ",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPasswordActivity.this,Login.class);
        startActivity(intent);
        finish();
    }
}