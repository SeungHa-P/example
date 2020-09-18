package com.example.example.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.example.R;

import java.net.URL;

public class FragmentDescription extends Fragment {


    public static String imgUri;
    public static String description;

    public FragmentDescription(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_description,container,false);

        ImageView imageView = view.findViewById(R.id.descriptionImg);
        TextView textView = view.findViewById(R.id.txtDescription);


        Glide.with(this).load(imgUri).into(imageView);


        Log.d("img",imgUri);
        textView.setText(description);

        return view;
    }
}
