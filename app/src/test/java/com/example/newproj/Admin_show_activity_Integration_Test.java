package com.example.newproj;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newproj.models.Meeting;
import com.example.newproj.models.Users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class Admin_show_activity_Integration_Test {
    @Test
    public void perform_click_to_show_user_Test() {
        AdminAllUsers activity= Robolectric.buildActivity(AdminAllUsers.class).create().get();
        ArrayList<Users> usersList = new ArrayList<Users>();
        Users newuser1=new Users();
        newuser1.setName("liel1");
        newuser1.setEmail("liel@gmail.com");
        usersList.add(newuser1);
        activity.showResults(usersList);
        ((ListView)activity.findViewById(R.id.friends_listview)).performItemClick(activity.users.getSelectedView(),0,0);
        assertEquals(activity.user.getName(),"liel1");
    }
    @Test
    public void set_text_right() {
        AdminAllUsers activity= Robolectric.buildActivity(AdminAllUsers.class).create().get();
        assertEquals(((TextView)activity.findViewById(R.id.friends_label)).getText().toString(),"משתמשים");
    }
}
