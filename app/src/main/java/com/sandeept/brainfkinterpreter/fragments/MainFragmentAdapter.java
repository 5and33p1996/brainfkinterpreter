package com.sandeept.brainfkinterpreter.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainFragmentAdapter extends FragmentStateAdapter {

    public MainFragmentAdapter(FragmentActivity fragment){
        super(fragment);
    }

    @Override
    @NonNull
    public Fragment createFragment(int position){

        Fragment fragment;

        if(position == 0){

            fragment = new CodeFragment();
        }
        else{
            fragment = new OutputFragment();
        }

        return fragment;
    }

    @Override
    public int getItemCount(){

        return 2;
    }
}
