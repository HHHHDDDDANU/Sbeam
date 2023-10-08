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

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter<ChatMessage>{
    private Context context;
    private ArrayList<ChatMessage> chat_log;
    private User current_user;
    public ChatAdapter(Context context, ArrayList<ChatMessage> chat_log, User current_user){
        super(context, 0, chat_log);
        this.context = context;
        this.chat_log = chat_log;
        this.current_user = current_user;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ChatMessage message = chat_log.get(position);

        if (convertView == null){
            if(message.getRecieve_user() == current_user){
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_log_receive_item, parent, false);
            }else if(message.getSend_user() == current_user){
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_log_send_item, parent, false);
            }else return null;
        }
        return convertView;
    }

}
