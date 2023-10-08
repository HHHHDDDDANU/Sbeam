package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



public class FriendsListActivity extends AppCompatActivity {

    FriendsListAdapter adapter;
    private ArrayList<User> friendsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        RecyclerView recyclerView = findViewById(R.id.friendslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //获取好友列表等，放进friendsList
        friendsList = new ArrayList<>();
        friendsList.add(new User("Connor",null,1234,0,null,null,null));
        friendsList.add(new User("Simon",null,1234,0,null,null,null));
        friendsList.add(new User("aaaaa",null,1234,0,null,null,null));

        //Button chatButton = findViewById(R.id.friend_operate_button);

        adapter = new FriendsListAdapter(this, friendsList);
        recyclerView.setAdapter(adapter);


    }
}