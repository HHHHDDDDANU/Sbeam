package com.example.myapplication.LibraryAndWishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Game;
import com.example.myapplication.LibraryAndWishlist.LibraryListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author u7574421 Simon Fu
 * This class defines the user's library, containing a recyclerview,
 * used to display the games currently owned by the user.
 */
public class MyLibrary extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Game> library=new ArrayList<>();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);
        recyclerView = findViewById(R.id.library_recyclerview);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LibraryListAdapter adapter = new LibraryListAdapter(library, this);
        recyclerView.setAdapter(adapter);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            // read library list from database if the data changes
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                library.clear();
                if(user.getLibrary()!=null) {
                    library.addAll(user.getLibrary());
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