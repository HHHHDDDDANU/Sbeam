package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void logIn(View view){
        TextView userName=findViewById(R.id.userName);
        TextView passWord=findViewById(R.id.passWord);
        String userName1="comp2100@anu.edu.au";
        String userName2="comp6442@anu.edu.au";
        String passWord1="comp2100";
        String passWord2="comp6442";
        if((userName.getText().toString().equals(userName1)&&passWord.getText().toString().equals(passWord1))||(userName.getText().toString().equals(userName2)&&passWord.getText().toString().equals(passWord2))){
            Intent intent=new Intent(getApplicationContext(),GameList.class);
            startActivity(intent);
        }else {
            // wrong password
            Toast toast=Toast.makeText(getApplicationContext(),"Invalid password, please try again!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}