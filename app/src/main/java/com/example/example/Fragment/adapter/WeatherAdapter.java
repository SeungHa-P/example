package com.example.example.Fragment.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.example.Fragment.FragementNow;
import com.example.example.Fragment.FragmentDescription;
import com.example.example.Fragment.FragmentHumidity;
import com.example.example.Fragment.FragmentWind;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends FragmentPagerAdapter {

   // private List<Fragment> fgList = new ArrayList<>();
    private int count;
    public WeatherAdapter(FragmentManager fm,int count){
        super(fm);
        this.count = count;
    }
//
//    public void addFragment(Fragment fragment){
//        fgList.add(fragment);
//        Log.d("count",count+"");
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("position count",""+position%count);
        switch (position % count){
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
//        return fgList.get(position%count);
    }

    @Override
    public int getCount() {
        return count*3;
    }
}
