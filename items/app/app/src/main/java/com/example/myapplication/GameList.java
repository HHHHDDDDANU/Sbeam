package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GameList extends AppCompatActivity {
    GameAdapter adapter;
    public ArrayList<Game> games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ListView listView=findViewById(R.id.gameList);
        games=JsonParser.parseJsonFromAssets(this,"data.json");
        adapter=new GameAdapter(this,games);
        listView.setAdapter(adapter);
    }
}