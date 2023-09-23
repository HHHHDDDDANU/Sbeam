package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {

    private Context context;

    public GameAdapter(Context context, List<Game> games) {
        super(context, 0, games);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView nameTextView = convertView.findViewById(android.R.id.text1);
        Game game = getItem(position);

        // 设置要显示的属性
        if (game != null) {
            nameTextView.setText(game.getName());
        }

        return convertView;
    }
}
