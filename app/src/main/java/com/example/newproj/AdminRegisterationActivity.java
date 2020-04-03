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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminRegisterationActivity extends AppCompatActivity {
    private TextView regName,regLastName,regAge,regEmail,regPassword,regBackToLogin,regAdderss;
    private Button regButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registeration);
        regName=findViewById(R.id.AddUserFirstName);
        regLastName=findViewById(R.id.AdduserLastName);
        regAge=findViewById(R.id.AddUserAge);
        regEmail=findViewById(R.id.AddUserEmail);
        regPassword=findViewById(R.id.addPasswordUser);
        regButton=findViewById(R.id.adminAddUser);
        regAdderss=findViewById(R.id.addUserAddress);
        db = FirebaseFirestore.getInstance();



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signUp();
                }

        });

    }

    private void signUp(){
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
                        user.put("DogName","");
                        user.put("DogType","");
                        user.put("Image",usr.getImage());
                        user.put("Friends",usr.getFriends());
                        user.put("Address",usr.getAddress());

                        db.collection("users")
                                .document(usr.getEmail()).set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(AdminRegisterationActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(AdminRegisterationActivity.this, regEmail.getText().toString()+" already exsits \nplease try other email or Login ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


}
