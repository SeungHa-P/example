package com.example.example.Fragment.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.example.Fragment.TabFragmentOne;
import com.example.example.Fragment.TabFragmentTwo;

public class InterPageAdapter extends FragmentStatePagerAdapter {
    int num;
    public InterPageAdapter(FragmentManager fm, int num){
        super(fm);
        this.num = num;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("포지션",position+"");

        switch (position)
        {
            case 0:
                TabFragmentOne tab1 = new TabFragmentOne();
                return tab1;
            case 1:
                TabFragmentTwo tab2 = new TabFragmentTwo();
                return tab2;
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return 2;
    }
}
