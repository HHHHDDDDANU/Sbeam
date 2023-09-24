package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_game_details);
        TextView name=findViewById(R.id.detailName);
        TextView publisher=findViewById(R.id.detailPublisher);
        TextView year=findViewById(R.id.detailYear);
        TextView price=findViewById(R.id.detailPrice);
        name.setText(intent.getStringExtra("name"));
        publisher.setText(intent.getStringExtra("publisher"));
        year.setText(Integer.toString(intent.getIntExtra("year",0)));
        price.setText(Integer.toString(intent.getIntExtra("price",0)));
    }
}