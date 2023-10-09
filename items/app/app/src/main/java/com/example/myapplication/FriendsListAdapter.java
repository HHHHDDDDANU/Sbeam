package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User> friendsList;

    public FriendsListAdapter(@NonNull Context context, ArrayList<User> friendsList) {
        //super(context, R.layout.friends_list_item,friendsList);
        this.context = context;
        this.friendsList = friendsList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull FriendsListAdapter.ViewHolder holder, int position) {
        User friend = friendsList.get(position);
        holder.friendNameTextView.setText(friend.getUsername());
        holder.friendStatusTextView.setText(friend.getStatus());
        if(friend.getProfileUrl()!=null){
            Glide.with(context).load(friend.getProfileUrl()).into(holder.friendPhotoImageView);
        }

        Button chatWithFriend = holder.friendSendButton;
        chatWithFriend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
            Intent intent=new Intent(context, ChatActivity.class);
            intent.putExtra("friend",friend);
            context.startActivity(intent);
        }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, ChatActivity.class);
//                intent.putExtra("friend",friend);
//                context.startActivity(intent);
//            }
//        });
    }
    public int getItemCount(){
        return friendsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendNameTextView;
        public TextView friendStatusTextView;
        public ImageView friendPhotoImageView;
        public Button friendSendButton;

        public ViewHolder(View itemView) {
            super(itemView);
            friendNameTextView = itemView.findViewById(R.id.friends_name);
            friendStatusTextView = itemView.findViewById(R.id.friends_status);
            friendPhotoImageView = itemView.findViewById(R.id.friends_photo);
            friendSendButton = itemView.findViewById(R.id.friend_operate_button);
        }
    }
}
