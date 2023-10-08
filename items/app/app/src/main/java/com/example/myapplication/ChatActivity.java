 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import android.os.Bundle;

public class ChatActivity extends AppCompatActivity {
    private User currentUser;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatLog;
    private ListView chatListView;
    private User friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        friend = getIntent().getParcelableExtra("friend");
        //获取chatLog
        //获取currentUser
        chatAdapter = new ChatAdapter(this,chatLog, currentUser);
        chatListView = findViewById(R.id.chat_log);
        chatListView.setAdapter(chatAdapter);
    }
}