package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproj.models.CurrentUser;
import com.example.newproj.models.FriendsAdapter;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRemoveUsersActivity extends AppCompatActivity {
    private ListView users;
    private TextView userCount,userLabel;
    private FirebaseFirestore db;
    private ArrayList<Users> usersList;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        users = findViewById(R.id.friends_listview);
        userCount=findViewById(R.id.friends_count);
        userLabel=findViewById(R.id.friends_label);
        userLabel.setText("משתמשים");
        db = FirebaseFirestore.getInstance();
        usersList = new ArrayList<Users>();


        CollectionReference allUsers = db.collection("users");
        allUsers.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        if(!(doc.get("UserType").toString().equals("admin") || doc.get("Email").toString().equals(CurrentUser.currentUserEmail))){
                            user = new Users();
                            user.setName(doc.get("Name").toString());
                            user.setLastName(doc.get("LastName").toString());
                            user.setAge(doc.get("Age").toString());
                            user.setAddress(doc.get("Address").toString());
                            user.setImage(doc.get("Image").toString());
                            user.setDogType(doc.get("DogType").toString());
                            user.setDogName(doc.get("DogName").toString());
                            user.setEmail(doc.get("Email").toString());
                            usersList.add(user);
                        }
                    }
                    showResults(usersList);
                    userCount.setText(""+usersList.size());
                }
            }
        });

        users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = (Users) parent.getItemAtPosition(position);
                /*
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(AdminRemoveUsersActivity.this);
                builder.setMessage("האם אתה בטוח שאתה רוצה למחוק את המשתמש"+user.getName()+" "+user.getLastName()+"?").setTitle("מחיקה");
                builder.setPositiveButton("מחק", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeUser();
                    }
                });
                builder.setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                */
                Intent intent = new Intent(AdminRemoveUsersActivity.this,AdminToUserProfileActivity.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("lastName",user.getLastName());
                intent.putExtra("address",user.getAddress());
                intent.putExtra("dogName",user.getDogName());
                intent.putExtra("dogType",user.getDogType());
                intent.putExtra("age",user.getAge());
                intent.putExtra("image",user.getImage());
                intent.putExtra("email",user.getEmail());
                startActivity(intent);
                finish();
            }
        });


    }

    private void removeUser() {
        db.collection("users").document(user.getEmail()).delete();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()) {
                        if (!(doc.get("UserType").toString().equals("admin") || doc.get("Email").toString().equals(CurrentUser.currentUserEmail))) {
                            Users user1 = new Users();
                            user1.setEmail(doc.get("Email").toString());
                            user1.setFriends((List<String>)(doc.get("Friends")));
                            user1.getFriends().remove(user.getEmail());

                            WriteBatch batch = db.batch();
                            DocumentReference sfRef= db.collection("users").document(user1.getEmail());
                            batch.update(sfRef, "Friends", user1.getFriends());
                            batch.commit();
                        }
                    }
                    db.collectionGroup("meetings").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                           for (QueryDocumentSnapshot doc : task.getResult()) {
                                                               if(doc.get("Owner").toString().equals(user.getEmail())) {
                                                                   WriteBatch batch = db.batch();
                                                                   String key=doc.getId();
                                                                   DocumentReference laRef = db.collection("meetings").document(doc.getId());
                                                                   batch.delete(laRef);
                                                                   batch.commit();
                                                               }
                                                           }
                                                           Toast.makeText(AdminRemoveUsersActivity.this,"משתמש נמחק בהצלחה",Toast.LENGTH_LONG).show();
                                                           Intent intent = new Intent(AdminRemoveUsersActivity.this, AdminRemoveUsersActivity.class);
                                                           startActivity(intent);
                                                           finish();
                                                       }

                                                   });


                }
            }
        });

    }


    private void showResults(ArrayList<Users> list) {
        FriendsAdapter arrayAdapter = new FriendsAdapter(this, list);
        users.setAdapter(arrayAdapter);
    }
}
