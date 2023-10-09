package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    RecyclerView recommended;
    RecyclerView newgame;
    ArrayList<Game> recommendedGame=new ArrayList<>();
    ArrayList<Game> newGames=new ArrayList<>();
    NewsGameAdapter recommendedAdapter;
    NewsGameAdapter newGameAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.news_fragment,container,false);
        recommended=root.findViewById(R.id.news_recommended_recyclerview);
        newgame=root.findViewById(R.id.news_newgame_recyclerview);
        recommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newgame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recommendedAdapter=new NewsGameAdapter(recommendedGame,getContext());
        newGameAdapter=new NewsGameAdapter(newGames,getContext());
        recommended.setAdapter(recommendedAdapter);
        newgame.setAdapter(newGameAdapter);
        // read recommended games from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("recommended");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendedGame.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recommendedGame.add(snapshot.getValue(Game.class));
                }
                recommendedAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        // read new games from firebase
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("new");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newGames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    newGames.add(snapshot.getValue(Game.class));
                }
                newGameAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return root;
    }
}
