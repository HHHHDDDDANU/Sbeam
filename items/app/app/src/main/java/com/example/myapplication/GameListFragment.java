package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author u7574421 Simon Fu
 * This class is for displaying a list of games,
 * containing a recycler view for showing the game list, and a search box for searching games.
 */
public class GameListFragment extends Fragment {
    GameListAdapter adapter;
    public ArrayList<Game> games=new ArrayList<>();
    public AVLTree gameTree = new AVLTree();
    Spinner spinner;
    ArrayList<Game> originalGames = new ArrayList<>();
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
                    gameTree.insert(snapshot.getValue(Game.class));
                }
                adapter.notifyDataSetChanged();
                originalGames.addAll(games); // This one works!
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        Button button=root.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                EditText searchEditText = root.findViewById(R.id.search_content);
                System.out.println(searchEditText);
                String criteria = searchEditText.getText().toString();

                Tokenizer tokenizer = new Tokenizer(criteria);
                System.out.println("valid??" + tokenizer.invalidInput);
                if (tokenizer.invalidInput) {
                    games.clear();
                    adapter.notifyDataSetChanged();
                    tokenizer.invalidInput = false;
                    return;
                }

                ArrayList<Game> matchedGames = new ArrayList<>();

                if (criteria.contains("price")) {
                    System.out.println("price : " + tokenizer.getPrice());
                    System.out.println("priceOperator: " + tokenizer.getPriceOperator());
                    matchedGames.addAll(gameTree.inorderToList(tokenizer.getPrice(),tokenizer.getPriceOperator()));
                    ArrayList<Game> toBeAddMatchedGames = new ArrayList<>();
                    for (Game game : matchedGames) {
                        if (tokenizer.matches(game)) {
                            toBeAddMatchedGames.add(game);
                        }
                    }
                    matchedGames.clear();
                    matchedGames.addAll(toBeAddMatchedGames);
                    System.out.println("game size " + games.size());
                    System.out.println("Orig size " + originalGames.size());
                    System.out.println("matched size " + matchedGames.size());
                }
                else {
                    System.out.println("Orig is emPty? 2! " + originalGames.isEmpty());
                    System.out.println("game size " + games.size());
                    System.out.println("Orig size " + originalGames.size());
                    System.out.println("matched size " + matchedGames.size());

                    for (Game game : originalGames) {
                        if (tokenizer.matches(game) && !matchedGames.contains(game)) {
                            matchedGames.add(game);
                        }
                    }
                    System.out.println("After searched, Orig size " + originalGames.size());
                    System.out.println("After searched, matched size " + matchedGames.size());
                    System.out.println("After searched, game size " + games.size());
                }

                games.clear();
                games.addAll(matchedGames);
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}
