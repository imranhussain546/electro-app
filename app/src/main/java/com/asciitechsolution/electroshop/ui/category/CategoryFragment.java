package com.asciitechsolution.electroshop.ui.category;

import android.app.Activity;
import android.os.Bundle;

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

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.adapter.CategoryAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.asciitechsolution.electroshop.model.category.CategoryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {
    public static ArrayList<CategoryInfo> categoryArrayList;
    TextView txtnodata;
    RecyclerView categoryrecycleview;
    SwipeRefreshLayout swipeLayout;
    View root;
    Activity activity;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_category, container, false);

        activity = getActivity();



        txtnodata = root.findViewById(R.id.txtnodata);
        swipeLayout = root.findViewById(R.id.swipeLayout);
        progressBar = root.findViewById(R.id.progressBar);
        categoryrecycleview = root.findViewById(R.id.categoryrecycleview);
        categoryrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        GetCategory();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (ApiClient.isConnected(activity))
                {
                    GetCategory();
                }
                swipeLayout.setRefreshing(false);
            }
        });
        return root;
    }

    private void GetCategory() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient();
        Call<CategoryModel> call = apiInterface.callcategory();
                call.enqueue(new Callback<CategoryModel>() {
                    @Override
                    public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                        CategoryInfo categoryInfo;
                        if (response.isSuccessful())
                        {
                            try {
                                categoryArrayList = new ArrayList<CategoryInfo>();
                                categoryArrayList=response.body().getCategoryInfo();
                                categoryrecycleview.setAdapter(new CategoryAdapter(getContext(),
                                        activity, categoryArrayList, R.layout.lyt_category_grid,
                                        "category_info", 0));
                                progressBar.setVisibility(View.GONE);
                            }catch (Exception e)
                            {e.printStackTrace();}

                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryModel> call, Throwable t) {
                        Log.e("responseerror",t.toString());
                    }
                });
    }
}