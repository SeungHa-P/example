package com.example.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.Fragment.adapter.RecycleAdapter;
import com.example.example.R;

import java.util.ArrayList;

public class TabFragmentTwo extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_two, container, false);


        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<100 ;i++){
            list.add(String.format("List %d",i));
        }

        RecyclerView recyclerView = view.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecycleAdapter adapter = new RecycleAdapter(list,getActivity());

        recyclerView.setAdapter(adapter);


        return view;
    }
}
