package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class GameListFragment extends Fragment {
    GameListAdapter adapter;
    public ArrayList<Game> games=new ArrayList<>();
    public AVLTree gameTree;
    Spinner spinner;
    ArrayList<Game> originalGames;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.gamelist_fragment,container,false);
        // initiate sort
        spinner=root.findViewById(R.id.detail_spinner);
        ArrayList<String> sort = new ArrayList<>(Arrays.asList("","NAME", "PRICE"));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, sort);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = sort.get(position);
                if(selectedItem.equals("PRICE")){
                    Collections.sort(games, new Comparator<Game>() {
                        @Override
                        public int compare(Game game1, Game game2) {
                            return Double.compare(game1.getPrice(), game2.getPrice());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }else if(selectedItem.equals("NAME")){
                    Collections.sort(games, new Comparator<Game>() {
                        @Override
                        public int compare(Game game1, Game game2) {
                            return game1.getName().compareTo(game2.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // set recyclerview
        RecyclerView recyclerView=root.findViewById(R.id.game_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new GameListAdapter(games,getContext());
        recyclerView.setAdapter(adapter);
        // read games from firebase database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("games");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(Game.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        originalGames = new ArrayList<>(games);
        Button button=root.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                EditText searchEditText = root.findViewById(R.id.search_content);
                System.out.println(searchEditText);
                String criteria = searchEditText.getText().toString();

                Tokenizer tokenizer = new Tokenizer(criteria);

                ArrayList<Game> matchedGames = new ArrayList<>();
                for (Game game : originalGames) {
                    if (tokenizer.matches(game)) {
                        matchedGames.add(game);
                    }
                }

                games.clear();
                games.addAll(matchedGames);
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}
