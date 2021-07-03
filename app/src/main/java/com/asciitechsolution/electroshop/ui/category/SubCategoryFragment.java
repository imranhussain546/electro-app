package com.asciitechsolution.electroshop.ui.category;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
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
import com.asciitechsolution.electroshop.adapter.CategoryAdapter;
import com.asciitechsolution.electroshop.adapter.CategorySubAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.category.Categorieslevelone;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.asciitechsolution.electroshop.model.category.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubCategoryFragment extends Fragment {

   // public static ArrayList<Product> productArrayList;
    public static ArrayList<CategoryInfo> categoryArrayList;
    public static ArrayList<Categorieslevelone> categorieslevelones;

   // public static ProductLoadMoreAdapter mAdapter;
    Activity activity;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView, subCategoryrecycleview;
    SwipeRefreshLayout swipeLayout;
    TextView tvAlert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sub_category, container, false);

        activity = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        progressBar=view.findViewById(R.id.progressBar);
        swipeLayout = view.findViewById(R.id.swipeLayout);
        tvAlert = view.findViewById(R.id.tvAlert);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        subCategoryrecycleview = view.findViewById(R.id.subCategoryrecycleview);
        subCategoryrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        GetCategory();
        swipeLayout.setColorSchemeResources(R.color.purple_200);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        return view;
    }

    private void GetCategory()
    {
        progressBar.setVisibility(View.VISIBLE);
        String currentID=getArguments().getString(Constant.ID);
        Log.d("currentID--->",currentID.toString());
        ApiInterface apiInterface=ApiClient.getClient();
        Call<CategoryModel> call=apiInterface.callsubcategory(getArguments().getString(Constant.ID));
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                try {

                    if (response.isSuccessful()){
                        categoryArrayList = new ArrayList<CategoryInfo>();
                        categoryArrayList=response.body().getCategoryInfo();
                        subCategoryrecycleview.setAdapter(new CategoryAdapter(getContext(),
                                activity, categoryArrayList, R.layout.lyt_category_grid,
                                "category_info", 0));
                                //setAdapter(new CategorySubAdapter(getContext(),
                               // activity, categoryArrayList, R.layout.lyt_category_grid,
                               // "category_info", 0));
                        progressBar.setVisibility(View.GONE);
                    }

                    int i;
                    for (i=0;i<categoryArrayList.size();i++){
                        String cateInfoID=response.body().getCategoryInfo().get(i).getId();
                        Log.d("cateInfoID",""+cateInfoID);
                        if (currentID.equals(cateInfoID))
                        {
                            categorieslevelones=new ArrayList<Categorieslevelone>();
                            //categorieslevelones=categoryArrayList.get(i).getCategorieslevelone();


                        }
                    }


                }catch (Exception e)
                {e.printStackTrace();}
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }
//    categoryArrayList=new ArrayList<Categorieslevelone>();
//                    categoryArrayList=response.body().getCategorieslevelone();
//
//

}