package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproj.models.CurrentUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
    private TextView helloLabel;
    private CardView profileButton;
    private CardView logOutButton;
    private CardView parksButton;
    private CardView friendsButton;
    //private FirebaseDatabase database;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        helloLabel=findViewById(R.id.hello_label);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        db = FirebaseFirestore.getInstance();
        profileButton=findViewById(R.id.myProfileHome);
        logOutButton=findViewById(R.id.LogoutButton);
        parksButton = findViewById(R.id.parks_btn);
        friendsButton = findViewById(R.id.friends_btn);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyProfile();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        parksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToParks();
            }
        });

        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFriends();
            }
        });


        DocumentReference user = db.collection("users").document(CurrentUser.currentUserEmail);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    helloLabel.setText("שלום"+" "+doc.get("Name").toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });




    }

    private void logOut() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }


    private void goToMyProfile(){
        Intent intent=new Intent(this,UserScreenActivity.class);
        startActivity(intent);
    }

    private void goToParks(){
        Intent intent=new Intent(this,ParksActivity.class);
        startActivity(intent);
    }

    private void goToFriends(){
        Intent intent=new Intent(this,MyFriendsActivity.class);
        startActivity(intent);
    }
}
