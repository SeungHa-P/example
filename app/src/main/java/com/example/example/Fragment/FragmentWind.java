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

public class FragmentWind extends Fragment {

    public static String txtWind;
    TextView textView;

    public FragmentWind(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_wind,container,false);

        textView=view.findViewById(R.id.txtWind);
        textView.setText("풍속  :  "+txtWind+"m/s");

        return view;
    }
}
