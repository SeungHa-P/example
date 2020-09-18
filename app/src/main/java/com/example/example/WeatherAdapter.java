package com.example.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class WeatherAdapter extends FragmentStatePagerAdapter {



    public WeatherAdapter(FragmentManager fm){
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragementNow fragementNow = new FragementNow();
                return fragementNow;
            case 1:
                FragmentDescription fragmentDescription = new FragmentDescription();
                return fragmentDescription;
            case 2:
                FragmentHumidity fragmentHumidity = new FragmentHumidity();
                return fragmentHumidity;
            case 3:
                FragmentWind fragmentWind = new FragmentWind();
                return fragmentWind;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
