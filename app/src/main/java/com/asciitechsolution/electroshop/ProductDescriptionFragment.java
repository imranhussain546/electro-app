package com.asciitechsolution.electroshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProductDescriptionFragment extends Fragment {

    public ProductDescriptionFragment() {
        // Required empty public constructor
    }
    private TextView descriptionBody;
    public Spanned body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_product_description, container, false);
        descriptionBody=view.findViewById(R.id.tv_product_description);
        descriptionBody.setText(body);
       return view;
    }
}