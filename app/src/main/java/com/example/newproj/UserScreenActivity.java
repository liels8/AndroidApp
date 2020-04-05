package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproj.models.CurrentUser;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class UserScreenActivity extends AppCompatActivity {
   // private static final int CAMERA_REQUEST_CODE = 100;
    //private static final int IMAGE_PICK_CAMERA_CODE = 400;
    private TextView textName, textDog, textType, textAge, textEmail, textUserName,textAddress;
    private String name;
    private ImageView editButton,profilePic;
    private Uri image_uri;
    String cameraPermissions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        textName = findViewById(R.id.profileName);
        textDog = findViewById(R.id.dogName);
        textType = findViewById(R.id.typeName);
        textAge = findViewById(R.id.profileAge);
        textEmail = findViewById(R.id.profileEmail);
        textAddress=findViewById(R.id.myAddress);
        textUserName = findViewById(R.id.userName);
        editButton = findViewById(R.id.editProfile);
        profilePic = findViewById(R.id.userImage);

        //cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference user = db.collection("users").document(CurrentUser.currentUserEmail);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    textName.setText(doc.get("Name").toString() + " " + doc.get("LastName").toString());
                    textUserName.setText(textName.getText());
                    textAge.setText(doc.get("Age").toString());
                    textEmail.setText(doc.get("Email").toString());
                    textDog.setText(doc.get("DogName").toString());
                    textAddress.setText(doc.get("Address").toString());
                    textType.setText(doc.get("DogType").toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });

      /*  profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromCamera();
            }
        });*/


    }

   /* private void pickFromCamera() {
        ActivityCompat.requestPermissions(UserScreenActivity.this,cameraPermissions,CAMERA_REQUEST_CODE);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Temp pic");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Temp Description");
        image_uri = UserScreenActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);

    }*/

    private void goToEditProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
        finish();
    }

}


