package com.example.newproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.newproj.models.Users;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminEditUserProfileActivity extends AppCompatActivity {
    private EditText firstNameText,lastNameText,addressText,dogNameText,dogTypeText,ageText,passwordText;
    private Button update_btn;
    private FirebaseFirestore db;
    private DocumentReference user;
    private Users usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user_profile);
    }
}
