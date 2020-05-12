package com.example.newproj;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class LoginIntegrationTest {
    public  Users LoginUser= new Users();
    @Test
    public void User_Login_For_Integration_Test() {
        final MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)activity.findViewById(R.id.EmailText)).setText("liel@gmail.com");
        ((TextView)activity.findViewById(R.id.PasswordText)).setText("123456");
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("users").document("liel@gmail.com");

        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        LoginUser.setEmail(doc.get("Email").toString());
                        LoginUser.setPassword(doc.get("Password").toString());
                        LoginUser.setType(doc.get("UserType").toString());
                        LoginUser.setFriends((List<String>) doc.get("Friends"));
                        LoginUser.setDogType((String)doc.get("DogType"));

                    }

                }
                activity.setLoginUser(LoginUser);
                activity.CheckUserDetails();
                assertEquals(activity.loginLoged ,true);
            }

        });

    }

    @Test
    public void test2() {
        assertEquals(true ,true);
    }
}
