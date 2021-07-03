package com.asciitechsolution.electroshop;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asciitechsolution.electroshop.adapter.SearchAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.product.ProductInfo;
import com.asciitechsolution.electroshop.model.product.ProductModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

   public static ArrayList<ProductInfo> productArrayList;
    public static SearchAdapter searchAdapter;
    public ProgressBar progressBar;
    View root;
    RecyclerView recyclerView;
    TextView noResult, msg;
   // Session session;
    Activity activity;
    SearchView searchview;
    boolean isGrid = false;
    int resource;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_search, container, false);
        activity = getActivity();
        recyclerView = root.findViewById(R.id.recyclerView);
       // productArrayList = new ArrayList<>();
        noResult = root.findViewById(R.id.noResult);
        msg = root.findViewById(R.id.msg);
        progressBar = root.findViewById(R.id.pBar);
        searchview = root.findViewById(R.id.searchview);
        progressBar.setVisibility(View.VISIBLE);
        productArrayList = new ArrayList<ProductInfo>();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    SearchRequest(newText);
                }else {
                    if (activity != null && productArrayList.size() > 0) {
                        productArrayList.clear();
                        searchAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
        return root;
    }

    private void SearchRequest(final String query) {

        ApiInterface apiInterface= ApiClient.getClient();
        Call<ProductModel> call=apiInterface.callsearch(query);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                try {
                    if (response.isSuccessful())
                    {
                        String ss=response.body().toString();
                        Log.d("searchresponse",""+ss);
                        if (response.body().getProductInfo()!=null){
                            productArrayList=response.body().getProductInfo();
                            searchAdapter = new SearchAdapter(productArrayList, R.layout.lyt_item_list, activity);
                            recyclerView.setAdapter(searchAdapter);
                            progressBar.setVisibility(View.GONE);
                            noResult.setVisibility(View.GONE);
                            msg.setVisibility(View.GONE);
                        }else
                        {
                            noResult.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            productArrayList.clear();
                            recyclerView.setAdapter(new SearchAdapter(productArrayList, resource, activity));
                        }


                    }
                }catch (Exception e)
                {e.printStackTrace();}


            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        searchview.setIconifiedByDefault(true);
        searchview.setFocusable(true);
        searchview.setIconified(false);
        searchview.requestFocusFromTouch();
    }
}