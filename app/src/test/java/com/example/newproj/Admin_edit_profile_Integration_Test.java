package com.example.newproj;
import android.content.Intent;

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

    }
}
