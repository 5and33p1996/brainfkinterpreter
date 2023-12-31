package com.sandeept.brainfkinterpreter;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sandeept.brainfkinterpreter.fragments.MainFragmentAdapter;

public class MainActivity extends FragmentActivity implements Toolbar.OnMenuItemClickListener {

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

        MaterialToolbar toolbar = findViewById(R.id.material_tool_bar);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }
}