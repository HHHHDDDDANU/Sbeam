package com.example.myapplication.GameList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Game;
import com.example.myapplication.GameDetail;
import com.example.myapplication.R;

import java.util.List;

/**
 * @author u7574421 Simon Fu
 * This is an adapter class, used to associate the custom item XML file with the recyclerview.
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder>{
    private List<Game> gameList;
    private Context context;

    public GameListAdapter(List<Game> gameList, Context context) {
        this.gameList = gameList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.gameName.setText(game.getName());
        holder.gamePrice.setText("$" + game.getPrice());
        Glide.with(context).load(game.getUrl()).into(holder.gameImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GameDetail.class);
                intent.putExtra("game",gameList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameName;
        public TextView gamePrice;
        public ImageView gameImage;

        public ViewHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.item_name);
            gamePrice = itemView.findViewById(R.id.item_price);
            gameImage = itemView.findViewById(R.id.item_imageview);
        }
    }
}
