package com.example.locker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {
    private TextInputEditText etName, etEmail, etMobile, etBirth, etCity, etPost, etWork;
    private Button save;
    CircleImageView profile_img;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_update_profile);


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etBirth = findViewById(R.id.etBirth);
        etCity = findViewById(R.id.etCity);
        etPost = findViewById(R.id.etPost);
        etWork = findViewById(R.id.etWork);
        save = findViewById(R.id.btnSave);

        profile_img = findViewById(R.id.profile_img);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                etName.setText(userProfile.getUserName());
                etEmail.setText(userProfile.getUserEmail());
                etMobile.setText( userProfile.getUserMobile());
                etBirth.setText(userProfile.getUserBirth());
                etCity.setText(userProfile.getUserCity());
                etPost.setText(userProfile.getUserPo());
                etWork.setText(userProfile.getUserWork());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String birth = etBirth.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String po = etPost.getText().toString().trim();
                String work = etWork.getText().toString().trim();

                UserProfile userProfile = new UserProfile(birth, city, email, mobile, name, po, work);

                HashMap<String, Object> map = new HashMap<>();
                map.put("userBirth", birth);
                map.put("userCity", city);
                map.put("userEmail", email);
                map.put("userMobile", mobile);
                map.put("userName", name);
                map.put("userPo", po);
                map.put("userWork", work);

                databaseReference.updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(UpdateProfile.this, "Details Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateProfile.this, "Error Uploading user details", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }


        });


       /*  profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);

            }
        });
*/

    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 123) ;{
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            profile_img.setImageBitmap(bitmap);
        }

    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateProfile.this,Profile.class);
        startActivity(intent);
        fileList();
    }
}
