package com.asciitechsolution.electroshop.ui.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.asciitechsolution.electroshop.R;

public class ServiceFragment extends Fragment implements AdapterView.OnItemSelectedListener {
private Spinner spin;
    String[] services = { "Choose Service","Air Conditioner", "Washing Machine", "Refrigetor", "Cooler", };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_service, container, false);
        spin=root.findViewById(R.id.serviceSpnr);
        spin.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,services);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}