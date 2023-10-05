package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GameListFragment extends Fragment {
    GameAdapter adapter;
    public ArrayList<Game> games;
    public AVLTree gameTree;
    ArrayList<Game> originalGames = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.gamelist_fragment,container,false);
        ListView listView = root.findViewById(R.id.game_list);
        games = JsonParser.parseJsonFromAssets(getContext(),"data.json");
        gameTree = JsonParser.parseJsonToAVLTree(getContext(),"data.json");
        originalGames = new ArrayList<>(games);
        adapter=new GameAdapter(getContext(),games);
        listView.setAdapter(adapter);
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
                System.out.println("Clicked");
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
                    originalGames.clear();
                    originalGames.addAll(gameTree.inorderToList(tokenizer.getPrice(),tokenizer.getPriceOperator()));
//                    matchedGames.addAll(originalGames);
                    for (Game game : originalGames) {
                        if (tokenizer.matches(game)) {
                            matchedGames.add(game);
                        }
                    }
                }
                else {
                    originalGames = new ArrayList<>(games);
                    for (Game game : originalGames) {
                        if (tokenizer.matches(game)) {
                            matchedGames.add(game);
                        }
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
