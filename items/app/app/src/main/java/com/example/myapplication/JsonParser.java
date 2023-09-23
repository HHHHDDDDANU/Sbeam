package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class JsonParser {
    public static ArrayList<Game> parseJsonFromAssets(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String jsonString = stringBuilder.toString();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Game>>() {}.getType();
            ArrayList<Game> gameList = gson.fromJson(jsonString, listType);

            return gameList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
