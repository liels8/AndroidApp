package com.example.newproj.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.newproj.R;
import com.example.newproj.UserScreenActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MeetingsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<Meeting> meetings;
    StorageReference storageRef;
    ImageView Parkimage;
    ImageView usrimage;
    FirebaseFirestore db;

    public MeetingsAdapter(Context applicationContext, ArrayList<Meeting> meetingsList) {
        this.context = context;
        this.meetings = meetingsList;
        inflter = (LayoutInflater.from(applicationContext));
        storageRef = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public int getCount() {
        return meetings.size();
    }

    @Override
    public Object getItem(int position) {
        return meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.activity_meetings_listview,null);
        TextView date = (TextView)  view.findViewById(R.id.meeting_date);
        TextView location = (TextView)  view.findViewById(R.id.meeting_park);
        TextView hour = (TextView)  view.findViewById(R.id.meeting_time);
        TextView count = (TextView)  view.findViewById(R.id.participants_count);
        TextView type = (TextView)  view.findViewById(R.id.meeting_type);
        final TextView creator = (TextView)  view.findViewById(R.id.meeting_creator);
        Parkimage = (ImageView) view.findViewById(R.id.meeting_img);
        usrimage = (ImageView) view.findViewById(R.id.creator_img);
        date.setText(meetings.get(i).getDate());
        location.setText(meetings.get(i).getLocation());
        hour.setText(meetings.get(i).getHour());
        count.setText(Integer.toString(meetings.get(i).getParticipants().size()));
        type.setText(meetings.get(i).getDogType());
        DocumentReference user = db.collection("users").document(meetings.get(i).getOwner());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    creator.setText(doc.get("Name").toString() + " " + doc.get("LastName").toString());
                }
            }
        });

        StorageReference pref;
        //park image
        pref = storageRef.child(meetings.get(i).getParkImage());
        Glide.with(view.getContext())
                .load(pref)
                .into(Parkimage);
        //user image
        pref = storageRef.child(meetings.get(i).getUserImage());
        Glide.with(view.getContext())
                .load(pref)
                .into(usrimage);

        return view;
    }
}
