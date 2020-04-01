package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private TextView regName,regLastName,regAge,regEmail,regPassword,regBackToLogin,regAdderss;
    private Button regButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get fields instances
        regName=findViewById(R.id.regName);
        regLastName=findViewById(R.id.regLastName);
        regAge=findViewById(R.id.regAge);
        regEmail=findViewById(R.id.regEmail);
        regPassword=findViewById(R.id.regPassword);
        regButton=findViewById(R.id.reg_btn);
        regBackToLogin=findViewById(R.id.login_label);
        regAdderss=findViewById(R.id.regAddress);
        db = FirebaseFirestore.getInstance();

        //set sing-up button clicked-function
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signUpValidation()) {
                    signUp();
                }
           }
        });

        regBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //sign up users to the authtiction firebase
    private void signUp(){
        final String email=regEmail.getText().toString();
        final String password=regPassword.getText().toString();

        DocumentReference user = db.collection("users").document(regEmail.getText().toString());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    if(!doc.exists()) {
                        Users usr=new Users(regName.getText().toString(),regLastName.getText().toString(),regAge.getText().toString(),regEmail.getText().toString(),regPassword.getText().toString(),regAdderss.getText().toString());
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", usr.getName());
                        user.put("LastName", usr.getLastName());
                        user.put("Age", usr.getAge());
                        user.put("Email", usr.getEmail());
                        user.put("Password", usr.getPassword());
                        user.put("UserType", usr.getUserType());
                        user.put("DogName",usr.getDogName());
                        user.put("DogType",usr.getDogName());
                        user.put("Image",usr.getImage());
                        user.put("Friends",usr.getFriends());

                        db.collection("users")
                                .document(usr.getEmail()).set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, regEmail.getText().toString()+" already exsits \nplease try other email or Login ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    //validate the fields in the register-page
    private Boolean signUpValidation(){

        boolean flag=true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        String namePattern="[A-Za-z]+";
        String passwordPattern="[A-Za-z0-9]";

        //name field validation
        if (!regName.getText().toString().isEmpty()){
            if(!(regName.getText().toString().trim().matches(namePattern))){
                regName.setError("Name field letters Must A-Z or a-z!");
                flag=false;
            }
            if(regName.getText().toString().length()<2) {
                regName.setError("Name field must be at least 2 letters!");
                flag=false;
            }
        } else{
            regName.setError("Please enter your name!");
            flag=false;
        }

        //last name field validation
        if (!regLastName.getText().toString().isEmpty()){
            if(!(regLastName.getText().toString().trim().matches(namePattern))){
                regLastName.setError("Last-Name field letters Must A-Z or a-z!");
                flag=false;
            }
            if(regLastName.getText().toString().length()<2) {
                regLastName.setError("Last-Name field must be at least 2 letters!");
                flag=false;
            }
        }
        else{
            regLastName.setError("Please enter your last name!");
            flag=false;
        }

        //age field validation
        if (regAge.getText().toString().isEmpty()){
            regAge.setError("Please enter your age");
            flag=false;
        }

        //email field validation
        if (!regEmail.getText().toString().isEmpty()){
            if(!regEmail.getText().toString().trim().matches(emailPattern)){
                regEmail.setError("Please enter a valid email");
                flag=false;
            }
        }
        else {
            regEmail.setError("Please enter your email");
            flag=false;
        }

        //password field validation
        if (!regPassword.getText().toString().isEmpty()){
            if (regPassword.getText().toString().trim().matches(passwordPattern)){
                regPassword.setError("Passwrod must contain lower or upper or number letters");
                flag=false;
            }
            if (regPassword.getText().toString().length()<5){
                regPassword.setError("Password length must be at least 6");
                flag=false;
            }
        }
        else{
            regPassword.setError("Please enter password");
            flag=false;
        }

        return flag;
    }
}



/*
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Users usr=new Users(regName.getText().toString(),regLastName.getText().toString(),regAge.getText().toString(),regEmail.getText().toString(),mAuth.getUid(),regPassword.getText().toString());
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", usr.getName());
                    user.put("LastName", usr.getLastName());
                    user.put("Age", usr.getAge());
                    user.put("Id", usr.getId());
                    user.put("Email", usr.getEmail());
                    user.put("Password", usr.getPassword());
                    user.put("Type", usr.getType());

                    // Add a new document with a generated ID
                    db.collection("users")
                            .document(usr.getEmail()).set(user)
                            .addOnSuccessListener(new OnSuccessListener < Void > () {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "ERROR" + e.toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Faild Regsitration",Toast.LENGTH_SHORT).show();
                }
            }
        });*/