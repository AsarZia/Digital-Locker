 package com.example.locker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private TextView tvName, tvEmail, tvMobile, tvBirth, tvCity, tvPost, tvWork;
    public Button btnEdit;
    public CircleImageView profile_img;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    ProgressBar  progressbar_pic;
    public FirebaseAuth firebaseAuth;
   // public FirebaseDatabase firebaseDatabase;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseApp.initializeApp(this);

        profile_img = findViewById(R.id.profile_img);
        btnEdit = findViewById(R.id.btnEdit);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvMobile = findViewById(R.id.tvMobile);
        tvBirth = findViewById(R.id.tvBirth);
        tvCity = findViewById(R.id.tvCity);
        tvPost = findViewById(R.id.tvPost);
        tvWork = findViewById(R.id.tvWork);
        //progressbar_pic = findViewById(R.id.progressbar_pic);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        //progressbar_pic.setVisibility(View.GONE);

        //DatabaseReference databaseReference = firebaseDatabase.getReference();


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,UpdateProfile.class);
                startActivity(intent);
                finish();
            }
        });

         profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String birth = dataSnapshot.child("userBirth").getValue().toString();
                String city = dataSnapshot.child("userCity").getValue().toString();
                String email = dataSnapshot.child("userEmail").getValue().toString();
                String mobile = dataSnapshot.child("userMobile").getValue().toString();
                String name = dataSnapshot.child("userName").getValue().toString();
                String po = dataSnapshot.child("userPo").getValue().toString();
                String work = dataSnapshot.child("userWork").getValue().toString();
                tvBirth.setText("Birth:        " + birth);
                tvCity.setText("City:          " + city);
                tvEmail.setText("Email:        " + email);
                tvMobile.setText("Mobile:      " + mobile);
                tvName.setText("Name:          " + name);
                tvPost.setText("post:          " + po);
                tvWork.setText("Work:          " + work);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 123) ;{
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            profile_img.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Profile.this,Dashboard.class);
        startActivity(intent);
        fileList();
    }

}
