package com.asciitechsolution.electroshop.ui.brand;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.adapter.BrandAdapter;
import com.asciitechsolution.electroshop.adapter.ProductAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.BrandModel;
import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.asciitechsolution.electroshop.model.product.ProductInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsFragment extends Fragment {
    TextView txtnodata;
    RecyclerView brandrecycleview;
    SwipeRefreshLayout swipeLayout;
    ProgressBar progressBar;
    ArrayList<BrandInfo> arrayList;
    private ProductAdapter productAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        txtnodata = root.findViewById(R.id.txtnodata);
        swipeLayout = root.findViewById(R.id.swipeLayout);
        progressBar = root.findViewById(R.id.progressBar);
        brandrecycleview = root.findViewById(R.id.productrecycleview);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        brandrecycleview.setLayoutManager(layoutManager);

        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        getbrand();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getbrand();
                swipeLayout.setRefreshing(false);
            }

        });


        return root;
        
    }

    private void getbrand() {
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface= ApiClient.getClient();
        Call<BrandModel> call=apiInterface.callbrand();
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                if (response.isSuccessful()){
                    try {
                        Log.d("brand", "onResponse: "+response.body().toString());
                       arrayList=response.body().getBrandInfo();
                        BrandAdapter brandAdapter=new BrandAdapter(arrayList,getContext());
                        brandrecycleview.setAdapter(brandAdapter);
                        progressBar.setVisibility(View.GONE);
                    }catch (Exception e){e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }

}