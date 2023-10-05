package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MenuAdapter extends FragmentStateAdapter {
    public MenuAdapter(AppCompatActivity activity){
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NewsFragment();
            case 1:
                return new GameListFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}