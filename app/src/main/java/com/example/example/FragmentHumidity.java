package com.example.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHumidity extends Fragment {

    public static String txtHumidity;
    TextView textView;
    public FragmentHumidity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_humidity,container,false);

        textView=view.findViewById(R.id.txtHumidity);
        textView.setText(txtHumidity+"%");


        return view;

    }
}
