package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.content.ContextCompat;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User> friendsList;
    private void deleteConfirmation(Context context, User friend) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context) context);
        builder.setMessage("Are you sure you want to delete this friend?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase.getInstance().getReference().child("friendsList").child(uid).child(friend.getUsername()).setValue(null);
                FirebaseDatabase.getInstance().getReference().child("friendsList").child(friend.getUsername()).child(uid).setValue(null);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public FriendsListAdapter(@NonNull Context context, ArrayList<User> friendsList) {
        //super(context, R.layout.friends_list_item,friendsList);
        this.context = context;
        this.friendsList = friendsList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull FriendsListAdapter.ViewHolder holder, int position) {
        final User friend = friendsList.get(position);
        String friend_name = friend.getEmailAddress().split("@")[0];
        holder.friendNameTextView.setText(friend_name);
        int redColor = ContextCompat.getColor(context, R.color.red);
        int greenColor = ContextCompat.getColor(context, R.color.green);
        if(friend.getStatus() == 0) {
            holder.friendStatusTextView.setText("Off line");
            holder.friendStatusTextView.setTextColor(redColor);
        }else if(friend.getStatus() == 1) {
            holder.friendStatusTextView.setText("On line");
            holder.friendStatusTextView.setTextColor(greenColor);
        }
        if(friend.getProfileUrl()!=null){
            Glide.with(context).load(friend.getProfileUrl()).into(holder.friendPhotoImageView);
        }

        LinearLayout linearLayout = holder.friendLinearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("friend",friend);
                context.startActivity(intent);
            }
        });

        Button deleteFriend = holder.friendDeleteButton;
        deleteFriend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                deleteConfirmation(context, friend);
            }
        });
    }
    public int getItemCount(){
        return friendsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendNameTextView;
        public TextView friendStatusTextView;
        public ImageView friendPhotoImageView;
        public Button friendDeleteButton;
        public LinearLayout friendLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            friendNameTextView = itemView.findViewById(R.id.friends_name);
            friendStatusTextView = itemView.findViewById(R.id.friends_status);
            friendPhotoImageView = itemView.findViewById(R.id.friends_photo);
            friendDeleteButton = itemView.findViewById(R.id.friend_delete_button);
            friendLinearLayout = itemView.findViewById(R.id.friendsList_linearLayout);
        }
    }
}
