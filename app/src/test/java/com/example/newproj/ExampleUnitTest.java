package com.example.newproj;

import androidx.annotation.NonNull;

import com.example.newproj.models.CurrentUser;
import com.example.newproj.models.Parks;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ArrayList<Parks> parksList;
    //Registeration
    @Test
    public void signUpValidation_nameLengthFaild() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(false,regActivity.signUpValidation("n","cohen","27","nadavc@gmail.com","123456"));
    }

    @Test
    public void signUpValidation_lastNamePatternFaild() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(false,regActivity.signUpValidation("nadav","123","27","nadavc@gmail.com","123456"));
    }

    @Test
    public void signUpValidation_ageEmptyFailed() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(false,regActivity.signUpValidation("nadav","cohen","","nadavc@gmail.com","123456"));
    }

    @Test
    public void signUpValidation_emailPatternFailed() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(false,regActivity.signUpValidation("nadav","cohen","","nadavc#gmail.com","123456"));
    }
    @Test
    public void signUpValidation_passwordLengthFailed() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(false,regActivity.signUpValidation("nadav","cohen","27","nadavc@gmail.com","1234"));
    }

    @Test
    public void signUpValidation_correct() {
        RegisterActivity regActivity = new RegisterActivity();
        assertEquals(true,regActivity.signUpValidation("nadav","cohen","27","nadavc@gmail.com","123456"));
    }

    //Edit profile
    @Test
    public void updateValidation_correct() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(true,editActivity.updateValidation("nadav","cohen","27","beer sheva","dog","Husky"));
    }

    @Test
    public void updateValidation_namePatternFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nad3av","cohen","27","beer sheva","dog","Husky"));
    }

    @Test
    public void updateValidation_lastNameLengthFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nadav","c","27","beer sheva","dog","Husky"));
    }

    @Test
    public void updateValidation_ageEmptyFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nadav","cohen","","beer sheva","dog","Husky"));
    }

    @Test
    public void updateValidation_dogNamePatternFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nadav","cohen","27","beer sheva","choco777","Husky"));
    }

    @Test
    public void updateValidation_dogTypeLengthFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nadav","cohen","27","beer sheva","dog","A"));
    }

    //login
    @Test
    public void loginValidation_emptyEmail() {
        MainActivity main = new MainActivity();
        assertEquals(false,main.existInput("","123456"));
    }
    @Test
    public void loginValidation_emptyPassword() {
        MainActivity main = new MainActivity();
        assertEquals(false,main.existInput("nadav@gmail.com",""));
    }

    //search friends
    @Test
    public void isFriends_Pass(){
        SearchUsersActivity search = new SearchUsersActivity();
        CurrentUser.currentUserFriends = new ArrayList<String>();
        CurrentUser.currentUserFriends.add("liel@gmail.com");
        CurrentUser.currentUserFriends.add("or@gmail.com");
        assertEquals(true,search.isFriend("liel@gmail.com"));
    }

    @Test
    public void isFriendsValidation(){
        SearchUsersActivity search = new SearchUsersActivity();
        CurrentUser.currentUserFriends = new ArrayList<String>();
        CurrentUser.currentUserFriends.add("liel@gmail.com");
        CurrentUser.currentUserFriends.add("or@gmail.com");
        assertEquals(false,search.isFriend("aviram@gmail.com"));
    }
    //logout
    @Test
    public void logoutTest(){
        HomeActivity home = new HomeActivity();
        MainActivity main = new MainActivity();
        home.logOut();
        assertEquals(true,main.getIntent().getExtras().getBoolean("EXIT"));
    }
    //
}