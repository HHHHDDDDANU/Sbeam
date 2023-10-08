package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FriendsListFragment extends Fragment {

    FriendsListAdapter adapter;
    private ArrayList<User> friendsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends_list,container,false);
        ListView listview = root.findViewById(R.id.friendslist);
        //DatabaseReference reference =
        friendsList = new ArrayList<>();
        friendsList.add(new User("Connor","1111",1234,true,null,null));
        friendsList.add(new User("Simon","2222",1234,true,null,null));
        friendsList.add(new User("aaaaa","3333",1234,true,null,null));

        adapter = new FriendsListAdapter(getContext(), friendsList);
        listview.setAdapter(adapter);
        return root;
    }
}