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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        RecyclerView recyclerView = findViewById(R.id.friendslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        friendsList = new ArrayList<>();
        friendsUserName = new ArrayList<>();
//        friendsList.add(new User("Connor",null,1234,0,null,null,null));
//        friendsList.add(new User("Simon",null,1234,0,null,null,null));
//        friendsList.add(new User("aaaaa",null,1234,0,null,null,null));

        //Button chatButton = findViewById(R.id.friend_operate_button);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
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