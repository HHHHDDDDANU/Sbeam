package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
                    // 设置选项卡标题
                    switch (position) {
                        case 0:
                            tab.setText("Login");
                            break;
                        case 1:
                            tab.setText("SingUp");
                            break;
                    }
                }
        ).attach();
//        tabLayout.setTranslationY(300);
//        tabLayout.setAlpha(0);
//        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }
}