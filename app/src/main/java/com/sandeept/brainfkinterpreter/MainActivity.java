package com.sandeept.brainfkinterpreter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sandeept.brainfkinterpreter.fragments.MainFragmentAdapter;

public class MainActivity extends FragmentActivity {

    MainFragmentAdapter adapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainFragmentAdapter(this);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {

            if(position == 0){
                tab.setText("Code");
            }
            else{
                tab.setText("Output");
            }
        }).attach();
    }
}