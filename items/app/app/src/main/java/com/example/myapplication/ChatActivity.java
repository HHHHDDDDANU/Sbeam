 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

 public class ChatActivity extends AppCompatActivity {
    private User currentUser=new User();
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatLog;
    private ListView chatListView;
    private User friend;
    private EditText inputText;
    private Button buttonSend;
    private TextView chat_name;
    private ArrayList<String> myPhotoUrl=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        friend = (User)getIntent().getSerializableExtra("friend");

        chatLog = new ArrayList<ChatMessage>();
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        inputText = findViewById(R.id.input_text);
        buttonSend = findViewById(R.id.chat_send_button);
        chat_name = findViewById(R.id.chat_name);
        chat_name.setText(uid);
        myPhotoUrl.add("");
        FirebaseDatabase.getInstance().getReference("users").child(uid).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUser=dataSnapshot.getValue(User.class);
                myPhotoUrl.clear();
                myPhotoUrl.add(currentUser.getProfileUrl());
                chatAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String messageText = inputText.getText().toString();
                if(!TextUtils.isEmpty(messageText)){
                    ChatMessage newMessage = new ChatMessage();
                    newMessage.setMessage(messageText);
                    newMessage.setSend_user_name(uid);
                    newMessage.setReceive_user_name(friend.getUsername());

                    DatabaseReference myChatReference = FirebaseDatabase.getInstance().getReference("friendsList").child(uid).child(friend.getUsername());
                    String messageId = myChatReference.push().getKey();
                    myChatReference.child(messageId).setValue(newMessage);

                    DatabaseReference friendChatReference = FirebaseDatabase.getInstance().getReference("friendsList").child(friend.getUsername()).child(uid);
                    messageId = myChatReference.push().getKey();
                    friendChatReference.child(messageId).setValue(newMessage);

                    inputText.setText("");
                    chatLog.clear();
                    chatAdapter.notifyDataSetChanged();
                }
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("friendsList").child(uid).child(friend.getUsername());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                    chatLog.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    chatLog.add(dataSnapshot.getValue(ChatMessage.class));
                    chatAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase", "loadPost:onCancelled", error.toException());
            }
        });

        chatAdapter = new ChatAdapter(this,chatLog, myPhotoUrl, friend);
        chatListView = findViewById(R.id.chat_log);
        chatListView.setAdapter(chatAdapter);
    }
}