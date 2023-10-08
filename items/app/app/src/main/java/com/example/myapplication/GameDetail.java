package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameDetail extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView publisher;
    TextView year;
    TextView type;
    TextView description;
    Button addToWishlist;
    TextView buyName;
    TextView price;
    Button buy;
    User user;
    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        // read current user data from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        // initiate widget
        setContentView(R.layout.activity_game_detail);
        imageView=findViewById(R.id.detail_img);
        name=findViewById(R.id.detail_name);
        publisher=findViewById(R.id.detail_publisher);
        year=findViewById(R.id.detail_year);
        type=findViewById(R.id.detail_type);
        description=findViewById(R.id.detail_description);
        addToWishlist=findViewById(R.id.detail_addtowishlist);
        price=findViewById(R.id.detail_price);
        buyName=findViewById(R.id.detail_buy_name);
        buy=findViewById(R.id.detail_buy);
        // receive game detail from intent
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game",Game.class);
        // set widget value to corresponding game
        name.setText(game.getName());
        publisher.setText(game.getProducer());
        year.setText(Integer.toString(game.getYear()));
        type.setText(game.getType());
        description.setText(game.getDescription());
        price.setText("$"+game.getPrice());
        buyName.setText("Buy "+game.getName());

        // set add to wishlist button
        addToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addToWishlist()){
                    // update user data in firebase
                    FirebaseDatabase.getInstance().getReference().child("users").child(user.getUsername()).setValue(user);
                    Toast.makeText(getApplicationContext(), "Add to wishlist successfully",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Already in wishlist!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void buyGame(){

    }
    // add game to the wishlist of the current user
    public boolean addToWishlist(){
        ArrayList<Game> wishlist;
        // wishlist is not empty
        if(user.getWishlist()!=null){
            // game is already in wish list
            wishlist=user.getWishlist();
            if(wishlist.contains(game)){
                return false;
            }
        // wishlist is empty
        }else {
            wishlist=new ArrayList<>();
        }
        wishlist.add(game);
        user.setWishlist(wishlist);
        return true;
    }
}