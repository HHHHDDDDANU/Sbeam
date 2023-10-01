package com.example.myapplication;

import android.app.AppComponentFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SignInAdapter extends FragmentStateAdapter {
    public SignInAdapter(AppCompatActivity activity){
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
                switch (position){
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
