package com.example.myapplication.News;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.Game;
import com.example.myapplication.News.NewsGameAdapter;
import com.example.myapplication.PreLoadedData;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * @author u7574421 Simon Fu
 * This class defines the news fragment, containing two recyclerviews,
 * used to display the latest games and recommended games.
 */
public class NewsFragment extends Fragment {
    int currentIndex = 0;
    ImageView slide;
    RecyclerView recommended;
    RecyclerView newgame;
    ArrayList<Game> recommendedGame=new ArrayList<>();
    ArrayList<Game> newGames=new ArrayList<>();
    NewsGameAdapter recommendedAdapter;
    NewsGameAdapter newGameAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.news_fragment,container,false);
        slide=root.findViewById(R.id.news_slide);
        recommended=root.findViewById(R.id.news_recommended_recyclerview);
        newgame=root.findViewById(R.id.news_newgame_recyclerview);
        recommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newgame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recommendedAdapter=new NewsGameAdapter(recommendedGame,getContext());
        newGameAdapter=new NewsGameAdapter(newGames,getContext());
        recommended.setAdapter(recommendedAdapter);
        newgame.setAdapter(newGameAdapter);

        // set handler for slide imageview
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // set event listener for glide, start animation when load an url into imageview
                Glide.with(getActivity())
                        .load(PreLoadedData.urls.get(currentIndex))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }
                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                // fade in animation
                                Animation in = AnimationUtils.makeInAnimation(getActivity(), true);
                                slide.startAnimation(in);
                                return false;
                            }
                        })
                        .into(slide);
                currentIndex = (currentIndex + 1) % PreLoadedData.urls.size();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);

        // read recommended games from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("recommended");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendedGame.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recommendedGame.add(snapshot.getValue(Game.class));
                }
                recommendedAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });

        // read new games from firebase
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("new");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newGames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    newGames.add(snapshot.getValue(Game.class));
                }
                newGameAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return root;
    }
}
