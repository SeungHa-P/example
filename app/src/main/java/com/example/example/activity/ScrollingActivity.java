package com.example.example.activity;

import android.os.Bundle;

import com.example.example.Fragment.adapter.InterPageAdapter;
import com.example.example.Fragment.adapter.RecycleAdapter;
import com.example.example.R;
import com.example.example.databinding.ActivityScrollingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    private ActivityScrollingBinding binding;
    InterPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ScrollingActivity.this, R.layout.activity_scrolling);
        binding.setActivity(this);

        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.listTab.addTab(binding.listTab.newTab().setText("리스트 1"));
        binding.listTab.addTab(binding.listTab.newTab().setText("리스트 2"));

        adapter=new InterPageAdapter(getSupportFragmentManager(),binding.listTab.getTabCount());
        binding.tapVP.setAdapter(adapter);
        binding.tapVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.listTab));

        binding.listTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.d("포지션 2", tab.getPosition()+"");

                        binding.tapVP.setCurrentItem(tab.getPosition());


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();

        }
        return true;
    }
}