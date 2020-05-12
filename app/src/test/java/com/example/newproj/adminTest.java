package com.example.newproj;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.testng.Assert.assertEquals;
@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class adminTest {
    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)activity.findViewById(R.id.EmailText)).setText("liel");
        assertEquals(((TextView)activity.findViewById(R.id.EmailText)).getText().toString() ,"liel");
    }
}
