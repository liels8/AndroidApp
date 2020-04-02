package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;


public class UserScreenActivity extends AppCompatActivity {
    private TextView textName, textDog, textType, textAge, textEmail, textUserName;
    private String name;
    private ImageView editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        textName = findViewById(R.id.profileName);
        textDog = findViewById(R.id.dogName);
        textType = findViewById(R.id.typeName);
        textAge = findViewById(R.id.profileAge);
        textEmail = findViewById(R.id.profileEmail);
        textUserName = findViewById(R.id.userName);
        editButton = findViewById(R.id.editProfile);
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


    }

    private void goToEditProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
        finish();
    }

}


