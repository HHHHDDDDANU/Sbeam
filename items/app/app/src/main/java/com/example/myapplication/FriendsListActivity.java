package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class FriendsListActivity extends AppCompatActivity {

    private User user;
    FriendsListAdapter adapter;
    private ArrayList<User> friendsList;
    private ArrayList<String> friendsUserName;
    private TextView username;
    private ImageView userphoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        username = findViewById(R.id.friendslist_username);
        userphoto = findViewById(R.id.friendslist_userphoto);
        RecyclerView recyclerView = findViewById(R.id.friendslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        friendsList = new ArrayList<>();
        friendsUserName = new ArrayList<>();
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            username.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
        }
        FirebaseDatabase.getInstance().getReference("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                user.setEmailAddress(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                Glide.with(getApplicationContext()).load(user.getProfileUrl()).into(userphoto);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("friendsList").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendsUserName.clear();
                friendsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friendsUserName.add(snapshot.getKey());
                }
                for(String username : friendsUserName){
                    DatabaseReference user_reference = FirebaseDatabase.getInstance().getReference("users").child(username);
                    user_reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            friendsList.add(snapshot.getValue(User.class));
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w("Firebase", "loadPost:onCancelled", error.toException());
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        adapter = new FriendsListAdapter(this, friendsList);
        recyclerView.setAdapter(adapter);
    }
}