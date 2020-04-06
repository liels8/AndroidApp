package com.example.newproj;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
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
        //
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(true,editActivity.updateValidation("nadav","cohen","27","beer sheva","choco777","Husky"));
    }

    @Test
    public void updateValidation_dogTypeLengthFailed() {
        EditProfileActivity editActivity = new EditProfileActivity();
        assertEquals(false,editActivity.updateValidation("nadav","cohen","27","beer sheva","dog","A"));
    }
}