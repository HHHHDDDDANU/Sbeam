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


public class FriendsListAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> friendsList;

    public FriendsListAdapter(@NonNull Context context, ArrayList<User> friendsList) {
        super(context, R.layout.friends_list_item,friendsList);
        this.context = context;
        this.friendsList = friendsList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.friends_list_item, parent, false);
        }

        User friend = getItem(position);

        ImageView friendPhotoImageView = convertView.findViewById(R.id.friends_photo);
        TextView friendNameTextView = convertView.findViewById(R.id.friends_name);
        TextView friendStatusTextView = convertView.findViewById(R.id.friends_status);

        if (friend != null) {
            //friendPhotoImageView.setImageResource(friend.getPhotoResource());
            friendNameTextView.setText(friend.getUsername());
            if (friend.getStatus()==1) {
                friendStatusTextView.setText("on line");
                friendStatusTextView.setTextColor(context.getResources().getColor(R.color.green));
            } else {
                friendStatusTextView.setText("busy");
                friendStatusTextView.setTextColor(context.getResources().getColor(R.color.red));
            }
        }
        return convertView;
    }
}
