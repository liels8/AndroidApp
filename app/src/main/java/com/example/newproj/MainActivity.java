package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.newproj.models.CurrentUser;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView emailText,passwordText,registerText;
    private Button loginButton;
    private String type;
    private FirebaseFirestore db;
    private DocumentReference user;
    private Users LoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText=findViewById(R.id.EmailText);
        passwordText=findViewById(R.id.PasswordText);
        registerText=findViewById(R.id.register_label);
        loginButton=findViewById(R.id.login_btn);
        db = FirebaseFirestore.getInstance();
        LoginUser = new Users();
        CurrentUser.currentUserEmail=null;


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegisterActivitiy();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();


            }
        });

    }

    //sign in method
    private void SignIn() {
        user = db.collection("users").document(emailText.getText().toString());
        user.get().addOnCompleteListener(new OnCompleteListener < DocumentSnapshot > () {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if(doc.exists()) {
                        LoginUser.setEmail(doc.get("Email").toString());
                        LoginUser.setPassword(doc.get("Password").toString());
                        LoginUser.setType(doc.get("UserType").toString());
                        CheckUserDetails();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Email is not exsits\n please try again",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    //check user type
    private void CheckUserDetails() {
        String password=passwordText.getText().toString();
        if(LoginUser.getPassword().equals(password)){
            CurrentUser.currentUserEmail=LoginUser.getEmail();
            if (LoginUser.getUserType().equals("user"))
                goToHomeScreen();
            //check if the user is an admin and go to homescreen admin
            else if (LoginUser.getUserType().equals("admin")){
                goToHomeAdminScreen();
            }
        }
        else{
            Toast.makeText(MainActivity.this,"Incorrect passowrd\n Try Again",Toast.LENGTH_SHORT).show();
        }
    }



    //switch to RegisterActivity
    private void switchToRegisterActivitiy(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    //switch to user screen
    private void goToHomeScreen(){
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    //switch to admin screen
    private void goToHomeAdminScreen() {
        Intent intent=new Intent(this,HomeAdminActivity.class);
        startActivity(intent);
    }

}
