package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter<ChatMessage>{
    private Context context;
    private ArrayList<ChatMessage> chat_log;
    private ArrayList<String> myPhotoUrl;
    private User friend;
    public ChatAdapter(Context context, ArrayList<ChatMessage> chat_log, ArrayList<String> myPhotoUrl, User friend){
        super(context, 0, chat_log);
        this.context = context;
        this.chat_log = chat_log;
        this.myPhotoUrl = myPhotoUrl;
        this.friend = friend;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ChatMessage message = chat_log.get(position);
        convertView = null;
        if (convertView == null){
            if(message.getSend_user_name().equals(friend.getUsername())){
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_log_receive_item, parent, false);
            }else if(message.getReceive_user_name().equals(friend.getUsername())){
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_log_send_item, parent, false);
            }else return null;
        }

        if(message.getSend_user_name().equals(friend.getUsername())){
            ImageView friendPhoto = convertView.findViewById(R.id.chat_log_friendphoto);
            TextView friendMessage = convertView.findViewById(R.id.chat_log_recieve_message);

            friendMessage.setText(message.getMessage());
            if(friend.getProfileUrl()!=null){
                Glide.with(context).load(friend.getProfileUrl()).into(friendPhoto);
            }
        }
        else if(message.getReceive_user_name().equals(friend.getUsername())){
            ImageView myPhoto = convertView.findViewById(R.id.chat_log_myphoto);
            TextView myMessage = convertView.findViewById(R.id.chat_log_send_message);

            myMessage.setText(message.getMessage());
            if(myPhotoUrl!=null){
                Glide.with(context).load(myPhotoUrl.get(0)).into(myPhoto);
            }
        }


        return convertView;
    }

}
