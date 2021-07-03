package com.asciitechsolution.electroshop;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.asciitechsolution.electroshop.adapter.OfflineFavoriteAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.product.ProductDetailitem;
import com.asciitechsolution.electroshop.model.product.ProductInfo2;
import com.asciitechsolution.electroshop.model.product.ProductModel2;
import com.asciitechsolution.electroshop.model.product.ProductRawdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoriteFragment extends Fragment {
//    public static ArrayList<Favorite> favoriteArrayList;
     public static ArrayList<ProductInfo2> productArrayList;
     public static ArrayList<ProductDetailitem> productdetail;
   //  public static FavoriteLoadMoreAdapter favoriteLoadMoreAdapter;
     public static OfflineFavoriteAdapter offlineFavoriteAdapter;
     public static RecyclerView recyclerView;
    public static RelativeLayout tvAlert;
//    View root;
//    Session session;
    int total;
    NestedScrollView nestedScrollView;
    Activity activity;
    boolean isLogin;
    DatabaseHelper databaseHelper;
    int offset = 0;
    SwipeRefreshLayout swipeLayout;
    boolean isLoadMore = false;
    boolean isGrid = false;
    int resource;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_favorite, container, false);
        databaseHelper = new DatabaseHelper(getActivity());

        swipeLayout = root.findViewById(R.id.swipeLayout);

        tvAlert = root.findViewById(R.id.tvAlert);
        nestedScrollView = root.findViewById(R.id.nestedScrollView);
        resource = R.layout.lyt_item_list;
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GetOfflineData();
        swipeLayout.setColorSchemeResources(R.color.purple_200);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetOfflineData();
                swipeLayout.setRefreshing(false);
            }

        });
        return root;
    }
    void GetOfflineData() {
        int i;
        if (databaseHelper.getFavourite().size() >= 1) {

            String data= databaseHelper.getFavourite().toString().replace("[", "").replace("]", "").replace("\"", "");
            ProductRawdata productRawdata = null;
            ArrayList<String> pid=databaseHelper.getFavourite();
            String pi;
            for ( i=0;i<pid.size();i++) {
                pi = pid.get(i);
                Log.d("favouritedata", "" + pi);
                productRawdata = new ProductRawdata(pi);

                productdetail=new ArrayList<ProductDetailitem>();
                ApiInterface apiInterface = ApiClient.getClient();
                Call<ProductModel2> call = apiInterface.callproductdetail(productRawdata);
                call.enqueue(new Callback<ProductModel2>() {
                    @Override
                    public void onResponse(Call<ProductModel2> call, Response<ProductModel2> response) {

                        if (response.isSuccessful()) {

                            try {
                                Log.d("favouriteresponse", "" + response.body().getProductInfo());
                                productArrayList = new ArrayList<>();
                                productArrayList = response.body().getProductInfo();
                                for (int i=0;i<productArrayList.size();i++){
                                    productdetail.add(productArrayList.get(i).getProducDetail());
                                    recyclerView.setVisibility(View.VISIBLE);
                                    tvAlert.setVisibility(View.GONE);
                                    offlineFavoriteAdapter = new OfflineFavoriteAdapter(getContext(), productdetail, resource);
                                    offlineFavoriteAdapter.setHasStableIds(true);
                                    recyclerView.setAdapter(offlineFavoriteAdapter);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductModel2> call, Throwable t) {
                        Log.e("responseerror", t.toString());
                    }
                });


            }
        }

    }
}