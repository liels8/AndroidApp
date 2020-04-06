package com.example.newproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserProfileActivity extends AppCompatActivity {
    private TextView userName,userAge,userDogName,userDogType,userAddress;
    private ImageView userImage,addFriend;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.friendName);
        userAge = findViewById(R.id.friendAge);
        userDogName = findViewById(R.id.friendDogName);
        userDogType = findViewById(R.id.friendDogType);
        userAddress = findViewById(R.id.friendAddress);
        userImage = findViewById(R.id.friendImage);
        addFriend = findViewById(R.id.add_friend);
        userName.setText(getIntent().getExtras().getString("name") + " " + getIntent().getExtras().getString("lastName"));
        userAge.setText(getIntent().getExtras().getString("age"));
        userDogName.setText(getIntent().getExtras().getString("dogName"));
        userDogType.setText(getIntent().getExtras().getString("dogType"));
        userAddress.setText(getIntent().getExtras().getString("address"));
        if(getIntent().getExtras().getBoolean("isFriend")){
            addFriend.setVisibility(View.INVISIBLE);
            addFriend.setEnabled(false);
        }
        else{
            addFriend.setVisibility(View.VISIBLE);
            addFriend.setEnabled(true);
        }
        storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference pref = storageRef.child(getIntent().getExtras().getString("image"));
        Glide.with(this)
                .load(pref)
                .into(userImage);

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
                builder.setMessage("האם לשלוח בקשת חברות?").setTitle("בקשת חברות");
                builder.setPositiveButton("שלח", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(UserProfileActivity.this,"הבקשה נשלחה בהצלחה",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
