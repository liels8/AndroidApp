package com.example.newproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserProfileActivity extends AppCompatActivity {
    private TextView userName,userAge,userDogName,userDogType,userAddress;
    private ImageView userImage;
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
        userName.setText(getIntent().getExtras().getString("name") + " " + getIntent().getExtras().getString("lastName"));
        userAge.setText(getIntent().getExtras().getString("age"));
        userDogName.setText(getIntent().getExtras().getString("dogName"));
        userDogType.setText(getIntent().getExtras().getString("dogType"));
        userAddress.setText(getIntent().getExtras().getString("address"));
        storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference pref = storageRef.child(getIntent().getExtras().getString("image"));
        Glide.with(this)
                .load(pref)
                .into(userImage);
    }
}
