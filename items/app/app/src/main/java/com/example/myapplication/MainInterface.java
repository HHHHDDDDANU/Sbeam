package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * @author u7574421 Simon Fu
 * This class defines the main interface, containing a pageviewer and a tablayout,
 * used for switching and displaying three fragments: news, gamelist, and profile.
 */
public class MainInterface extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        tabLayout=findViewById(R.id.menu);
        viewPager=findViewById(R.id.main_viewpager);
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        MenuAdapter adapter=new MenuAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("News");
                            break;
                        case 1:
                            tab.setText("Game List");
                            break;
                        case 2:
                            tab.setText("Profile");
                            break;
                    }
                }
        ).attach();
    }
}