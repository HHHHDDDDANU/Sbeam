package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.GameList.GameListFragment;
import com.example.myapplication.News.NewsFragment;
import com.example.myapplication.Profile.ProfileFragment;

/**
 * @author u7574421 Simon Fu
 * This is an adapter class, used to adapt the tablayout and viewpager of the main interface to each other.
 */
public class MenuAdapter extends FragmentStateAdapter {
    public MenuAdapter(AppCompatActivity activity){
        super(activity);
    }

    /**
     * Three fragments are attached to this adapter, which are news, game list and profile.
     * @param position
     * @return
     */
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