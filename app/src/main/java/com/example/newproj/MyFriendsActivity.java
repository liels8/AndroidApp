package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newproj.models.CurrentUser;
import com.example.newproj.models.FriendsAdapter;
import com.example.newproj.models.Parks;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyFriendsActivity extends AppCompatActivity {
    private ListView friends;
    private FirebaseFirestore db;
    private ArrayList<String> friendsList;
    private ArrayList<Users> usersList;
    private Users friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        friends = findViewById(R.id.friends_listview);
        db = FirebaseFirestore.getInstance();
        friendsList = new ArrayList<String>();
        usersList = new ArrayList<Users>();

        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                friend = (Users) parent.getItemAtPosition(position);
                showFriendProfile();
            }
        });


        DocumentReference user = db.collection("users").document(CurrentUser.currentUserEmail);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    friendsList = (ArrayList<String>) doc.get("Friends");
                    for(String friend : friendsList){
                        DocumentReference frnd = db.collection("users").document(friend);
                        frnd.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot doc = task.getResult();
                                    Users newUser = new Users();
                                    newUser.setName(doc.get("Name").toString());
                                    newUser.setLastName(doc.get("LastName").toString());
                                    newUser.setEmail(doc.get("Email").toString());
                                    newUser.setAddress(doc.get("Address").toString());
                                    newUser.setAge(doc.get("Age").toString());
                                    newUser.setDogName(doc.get("DogName").toString());
                                    newUser.setDogType(doc.get("DogType").toString());
                                    newUser.setImage(doc.get("Image").toString());
                                    usersList.add(newUser);
                                    fillList();
                                }
                            }
                        });

                    }

                }
            }
        });


    }

    private void fillList(){
        FriendsAdapter arrayAdapter = new FriendsAdapter(this, usersList);
        friends.setAdapter(arrayAdapter);
    }

    private void showFriendProfile(){
        Intent intent = new Intent(this,UserProfileActivity.class);
        intent.putExtra("name",friend.getName());
        intent.putExtra("lastName",friend.getLastName());
        intent.putExtra("email",friend.getEmail());
        intent.putExtra("address",friend.getAddress());
        intent.putExtra("dogName",friend.getDogName());
        intent.putExtra("dogType",friend.getDogType());
        intent.putExtra("age",friend.getAge());
        intent.putExtra("image",friend.getImage());
        intent.putExtra("isFriend",true);
        startActivity(intent);

    }

}
