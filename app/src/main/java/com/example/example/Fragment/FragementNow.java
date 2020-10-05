package com.example.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.example.R;

public class FragementNow extends Fragment {
    public static String now;

    public FragementNow(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_now,container,false);

        TextView textView = view.findViewById(R.id.nowtxt);
        textView.setText("현재 온도 : "+now+"º");
        return view;
    }
}
