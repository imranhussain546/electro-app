package com.asciitechsolution.electroshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.asciitechsolution.electroshop.adapter.CartAdapter;
import com.asciitechsolution.electroshop.model.CartItemModel;

import java.util.ArrayList;
import java.util.List;


public class DeliveryFragment extends Fragment {
    private RecyclerView deliveryRecyclerView;
    private Button cont;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_delivery, container, false);

        deliveryRecyclerView = view.findViewById(R.id.delivery_recyclerview);
        cont=view.findViewById(R.id.cart_continue_btn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.macbookpro,"Macbook air",1,"Rs 65999","Rs 69999",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.voltas_15_ton,"Voltas 1.5t0n",0,"Rs 25999","Rs 29999",1,0,0));
        cartItemModelList.add(new CartItemModel(1,"2 item","Rs 89999","Rs 0","Rs 2000","Rs 8999"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new AddressListFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}