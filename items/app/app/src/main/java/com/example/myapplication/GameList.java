package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),GameDetails.class);
                intent.putExtra("name", games.get(position).getName());
                intent.putExtra("publisher", games.get(position).getProducer());
                intent.putExtra("year", games.get(position).getYear());
                intent.putExtra("price", games.get(position).getPrice());
                startActivity(intent);
            }
        });
    }

}