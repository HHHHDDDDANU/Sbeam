package com.example.myapplication.LibraryAndWishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.Game;
import com.example.myapplication.GameList.GameListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author u7574421 Simon Fu
 * This class defines the wishlist, containing a recyclerview,
 * used to display the contents of the user's wishlist.
 */
public class MyWishlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    User user;
    ArrayList<Game> games=new ArrayList<>();
    GameListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlist);
        recyclerView=findViewById(R.id.wishlist_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new GameListAdapter(games,this);
        recyclerView.setAdapter(adapter);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // read wishlist from database if the data changes
                user=dataSnapshot.getValue(User.class);
                games.clear();
                if(user.getWishlist()!=null) {
                    games.addAll(user.getWishlist());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}