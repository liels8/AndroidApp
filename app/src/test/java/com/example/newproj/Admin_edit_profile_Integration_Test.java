package com.example.newproj;
import android.content.Intent;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.testng.Assert.assertEquals;
@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class Admin_edit_profile_Integration_Test {


    @Test
    public void name() {
        AdminEditUserProfileActivity activity= Robolectric.buildActivity(AdminEditUserProfileActivity.class).create().get();
        Intent intent=new Intent(activity,AdminToUserProfileActivity.class);
        ((EditText)activity.findViewById(R.id.editFirstName)).setText("Liel");
        try {
            activity.putextras(intent);
        }
        catch(Exception e){

        }
        assertEquals(intent.getExtras().getString("name").toString(),"Liel");
    }

    @Test
    public void yes() {
        AdminEditUserProfileActivity activity= Robolectric.buildActivity(AdminEditUserProfileActivity.class).create().get();
        activity.getIntent().putExtra("name","Liel");
        activity.setTextsToButtons();
        assertEquals(activity.firstNameText.getText().toString(),"Liel");
    }
}
