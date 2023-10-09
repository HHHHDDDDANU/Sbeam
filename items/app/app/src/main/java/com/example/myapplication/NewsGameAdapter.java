package com.example.myapplication;

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

import java.util.List;

/**
 * @author u7574421 Simon Fu
 * This is an adapter class,
 * used to associate the displaying of latest games and recommended games with the recyclerview and custom item XML.
 */
public class NewsGameAdapter extends RecyclerView.Adapter<NewsGameAdapter.MyViewHolder> {

    private List<Game> gameList;
    private Context context;

    public NewsGameAdapter(List<Game> gameList,Context context) {
        this.gameList=gameList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Game game = gameList.get(position);
        Glide.with(context).load(game.getUrl()).into(holder.imageView);
        holder.name.setText(game.getName());
        holder.price.setText("$"+game.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GameDetail.class);
                intent.putExtra("game",gameList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recommended_img);
            name = itemView.findViewById(R.id.recommended_name);
            price = itemView.findViewById(R.id.recommended_price);
        }
    }
}
