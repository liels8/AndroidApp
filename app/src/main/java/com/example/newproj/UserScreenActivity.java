package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private TextView textName, textLastName, textAge, textEmail, textUserName;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        textName = findViewById(R.id.profileName);
        //textLastName = findViewById(R.id.profileLastName);
        //textAge = findViewById(R.id.profileAge);
        textEmail = findViewById(R.id.profileEmail);
        textUserName = findViewById(R.id.userName);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference user = db.collection("users").document(CurrentUser.currentUserEmail);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    textName.setText(doc.get("Name").toString() + " " + doc.get("LastName").toString());
                    textUserName.setText(textName.getText());
                    //textLastName.setText(doc.get("LastName").toString());
                    //textAge.setText(doc.get("Age").toString());
                    textEmail.setText(doc.get("Email").toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


    }

    private void initProfileData() {
    }


}


