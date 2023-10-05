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


public class GameListFragment extends Fragment {
    GameAdapter adapter;
    public ArrayList<Game> games;
    public AVLTree gameTree;
    ArrayList<Game> originalGames;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.gamelist_fragment,container,false);
        ListView listView = root.findViewById(R.id.game_list);
        games = JsonParser.parseJsonFromAssets(getContext(),"data.json");
        //gameTree = JsonParser.parseJsonToAVLTree(this,"data.json");
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
