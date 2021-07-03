package com.asciitechsolution.electroshop;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asciitechsolution.electroshop.model.Address;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddressListFragment extends Fragment {
    public static RecyclerView recyclerView;
    public static ArrayList<Address> addresses;
    //public static AddressAdapter addressAdapter;
    public static TextView tvAlert;
    public static String selectedAddress = "";
    public static Activity activity;
    public NestedScrollView nestedScrollView;
    public int total = 0;
    FloatingActionButton fabAddAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_address_list, container, false);
        return view;
    }
}