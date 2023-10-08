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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GameListFragment extends Fragment {
    GameAdapter adapter;
    public ArrayList<Game> games = new ArrayList<>();
    public AVLTree gameTree;
    ArrayList<Game> originalGames = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.gamelist_fragment,container,false);
        ListView listView = root.findViewById(R.id.game_list);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("games");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(Game.class));
//                    gameTree.insert(snapshot.getValue(Game.class)); // not working.
                }
                adapter=new GameAdapter(getContext(),games);
                listView.setAdapter(adapter);
                originalGames.addAll(games); // This one works!
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
//        originalGames = new ArrayList<>(games); // This one seems to be useless.
        adapter=new GameAdapter(getContext(),games);
        listView.setAdapter(adapter);
//        for (Game game : games) {
//            gameTree.insert(game);
//        }
        System.out.println("here print tree struct");
        gameTree.printTreeStructure();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
            }
        });
        Button button=root.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("now try tree");
                for (Game game : games) {
                    gameTree.insert(game);
                }
                System.out.println("Clicked");
                System.out.println("First attempt: Orig is emPty? " + originalGames.isEmpty());
                EditText searchEditText = root.findViewById(R.id.search_content);
                System.out.println(searchEditText);
                String criteria = searchEditText.getText().toString();

                Tokenizer tokenizer = new Tokenizer(criteria);

                ArrayList<Game> matchedGames = new ArrayList<>();

                if (criteria.contains("price")) {
                    // For "<=" and ">=" later.
//                     Should be in format "price<=5;"
//                     Set up price string
//                    int indexOfPrice = criteria.indexOf("price");
//                    String priceString = criteria.substring(indexOfPrice,criteria.indexOf(";", indexOfPrice));
//                    priceString.replaceAll(" ", "");
//                    String operator = "";
//                    Pattern pattern = Pattern.compile("([<>]=?|=)");
//                    Matcher matcher = pattern.matcher(priceString);
//                    // Check if any match is found and return the first one
//                    if (matcher.find()) {
//                        operator = matcher.group(1);
//                    }

//                    originalGames.clear();
                    System.out.println("price : "+ tokenizer.getPrice());
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
                        if (tokenizer.matches(game)) {
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