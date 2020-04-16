package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newproj.models.CustomAdapter;
import com.example.newproj.models.Meeting;
import com.example.newproj.models.MeetingsAdapter;
import com.example.newproj.models.Parks;
import com.example.newproj.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UpcomingMeetingsActivity extends AppCompatActivity {
    private ListView meetingsListView;
    private TextView meetingsCount;
    private FirebaseFirestore db;
    private ArrayList<Meeting> meetingsList;
    private ArrayList<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_meetings);

        meetingsListView = findViewById(R.id.meetings_listview);
        meetingsCount = findViewById(R.id.meetings_count);
        meetingsList = new ArrayList<Meeting>();

        db = FirebaseFirestore.getInstance();

        CollectionReference user = db.collection("meetings");
        user.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task< QuerySnapshot > task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        Meeting meeting = new Meeting();
                        meeting.setDate(doc.get("Date").toString());
                        meeting.setLocation(doc.get("Location").toString());
                        meeting.setHour(doc.get("Hour").toString());
                        meeting.setDogType(doc.get("DogType").toString());
                        meeting.setDiscription(doc.get("Discription").toString());
                        meeting.setOwner(doc.get("Owner").toString());
                        meeting.setParticipants((ArrayList<String>)doc.get("Participants"));
                        meeting.setParkImage(doc.get("ParkImage").toString());
                        meeting.setUserImage(doc.get("UserImage").toString());
                        meetingsList.add(meeting);
                    }
                    fillList();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    private void fillList(){
        MeetingsAdapter arrayAdapter = new MeetingsAdapter(this, meetingsList);
        meetingsListView.setAdapter(arrayAdapter);
    }
}
