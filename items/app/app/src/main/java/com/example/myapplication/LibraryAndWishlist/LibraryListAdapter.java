package com.example.myapplication.LibraryAndWishlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Game;
import com.example.myapplication.GameDetail;
import com.example.myapplication.R;

import java.util.List;

/**
 * @author u7574421 Simon Fu
 * This is an adapter class, used to associate the recyclerview displaying the library with the custom item XML file,
 * while also defining the click event for each item.
 */
public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.ViewHolder>{
    private List<Game> gamelist;
    private Context context;

    public LibraryListAdapter(List<Game> gamelist, Context context) {
        this.gamelist = gamelist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gamelist.get(position);
        Glide.with(context).load(game.getUrl_l()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GameDetail.class);
                intent.putExtra("game",gamelist.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gamelist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.library_img);
        }
    }
}
