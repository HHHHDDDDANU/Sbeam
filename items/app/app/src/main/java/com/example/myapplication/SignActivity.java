package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * @author u7574421 Simon Fu
 * This class defines the login and registration activity, containing a tablayout and a viewpager,
 * used for displaying and switching between the login and registration fragments.
 */
public class SignActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        tabLayout=findViewById(R.id.signin_tab);
        viewPager=findViewById(R.id.signin_viewpager);
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        SignInAdapter adapter=new SignInAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Log In");
                            break;
                        case 1:
                            tab.setText("Sign Up");
                            break;
                    }
                }
        ).attach();
    }
}