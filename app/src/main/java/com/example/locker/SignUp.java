package com.example.locker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {
    TextView help_up;
    Button btn_GetVerificationCode, btn_signin;
    TextInputEditText Enter_phone, CodeReceived;
    ProgressBar progressBar1;
    String codeSent,cd ;
    ProgressDialog dialog;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_sign_up);


        dialog = new ProgressDialog(SignUp.this,R.style.Custom);


        btn_GetVerificationCode = findViewById(R.id.btn_GetVerificationCode);
        btn_signin =  findViewById(R.id.btn_signin);
        Enter_phone =  findViewById(R.id.Enter_phone);
        CodeReceived =  findViewById(R.id.CodeReceived);
        //progressBar1 = findViewById(R.id.progressBar1);

        mAuth = FirebaseAuth.getInstance();

        btn_GetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();

            }
        });

        cd = Objects.requireNonNull(CodeReceived.getText()).toString().trim();


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(cd == null){
                    Toast.makeText(MainActivity.this, "No code to signin", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                verifySignInCode();
               // progressBar1.setVisibility(View.VISIBLE);

                dialog.setTitle("please wait!!");
                dialog.setMessage("Verifying....");
                dialog.show();

            }
        });

        help_up = findViewById(R.id.help_up);



        help_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,NeedHelp.class);
                Toast.makeText(SignUp.this, "Help", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void verifySignInCode(){

            String code = Objects.requireNonNull(CodeReceived.getText()).toString().trim();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
            signInWithPhoneAuthCredential(credential);



    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       // progressBar1.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignUp.this,New_Register.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),"verification successful",Toast.LENGTH_SHORT).show();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),"Invalid verification code",Toast.LENGTH_SHORT).show();

                            }

                        }
                    }

                });
    }


    private void sendVerificationCode(){

        String phone = Objects.requireNonNull(Enter_phone.getText()).toString();

        if(phone.isEmpty()){
            Enter_phone.setError("phone number is required");
            Enter_phone.requestFocus();
            return;
        }

        if(phone.length() < 10){
            Enter_phone.setError("phone length must have 10 digits ");
            Enter_phone.requestFocus();
            return;

        }

       /* PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                SignUp.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks*/
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String otp=phoneAuthCredential.getSmsCode();
            if (otp!=null)
            {
                Toast.makeText(SignUp.this, ""+otp, Toast.LENGTH_SHORT).show();
                CodeReceived.setText(otp);
                verifySignInCode();
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };
}
