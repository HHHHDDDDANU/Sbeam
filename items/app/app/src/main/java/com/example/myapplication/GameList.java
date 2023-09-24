package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class GameList extends AppCompatActivity {
    GameAdapter adapter;
    public ArrayList<Game> games;
    ArrayList<Game> originalGames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ListView listView=findViewById(R.id.gameList);
        games=JsonParser.parseJsonFromAssets(this,"data.json");
        originalGames = new ArrayList<>(games);
        adapter=new GameAdapter(this,games);
        listView.setAdapter(adapter);
    }
    public void performSearch(View view) {
        System.out.println("Clicked");
        EditText searchEditText = findViewById(R.id.serachContent);
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
}