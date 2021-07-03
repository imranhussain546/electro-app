package com.asciitechsolution.electroshop.ui.brand;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.adapter.BrandProductAdapter;

import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.BrandModel;
import com.asciitechsolution.electroshop.model.product.BrandInfo;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrandProductFragment extends Fragment {
    TextView txtnodata;
    RecyclerView productrecycleview;
    SwipeRefreshLayout swipeLayout;
    ProgressBar progressBar;
    ArrayList<BrandInfo> arrayList;
    private BrandProductAdapter brandProductAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_brand_product, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        txtnodata = root.findViewById(R.id.txtnodata);
        swipeLayout = root.findViewById(R.id.swipeLayout);
        progressBar = root.findViewById(R.id.progressBar);
        productrecycleview = root.findViewById(R.id.brandproductrecycleview);
        GridLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        productrecycleview.setLayoutManager(linearLayoutManager);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        getbrandproduct();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getbrandproduct();
                swipeLayout.setRefreshing(false);
            }
        });
    }

    private void getbrandproduct()
    {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface= ApiClient.getClient();
        Call<BrandModel> call=apiInterface.callbrandproduct(getArguments().getString(Constant.ID));
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                if (response.isSuccessful())
                {
                    try {
                        Log.d("brandproduct", "onResponse: "+response.body().getBrandInfo().toString());
                        arrayList=response.body().getBrandInfo();
                        brandProductAdapter=new BrandProductAdapter(getContext(),arrayList);
                        productrecycleview.setAdapter(brandProductAdapter);
                        progressBar.setVisibility(View.GONE);
                    }catch (Exception e)
                    {e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }
}