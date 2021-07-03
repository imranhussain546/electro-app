package com.asciitechsolution.electroshop;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asciitechsolution.electroshop.adapter.FilterItemListAdapter;
import com.asciitechsolution.electroshop.adapter.ProductAdapter;
import com.asciitechsolution.electroshop.adapter.SortItemListAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.BrandModel;
import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.asciitechsolution.electroshop.model.product.ProductInfo;
import com.asciitechsolution.electroshop.model.product.ProductModel;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GridProductFragment extends Fragment {
    TextView txtnodata;
    RecyclerView productrecycleview;
    SwipeRefreshLayout swipeLayout;
    ProgressBar progressBar;
    ArrayList<ProductInfo> arrayList;
    private ProductAdapter productAdapter;
    RelativeLayout sort, filter;
    TextView sortByText;

    String[] sortByArray = {"Newest First", "Price--Low to High", "Price--High to Low", "Offer Product"};
    int sortById = 0;
    List<String> sizeFilter = new ArrayList<>();
    List<String> colorFilter = new ArrayList<>();
    RangeSeekBar rangeSeekbar;
    Object minprice;
    Object maxprice;
    ArrayList<BrandInfo> brandlist;
    CheckBox rating5,rating4,rating3,rating2,rating1;
    String ratg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_grid_product, container, false);
        txtnodata = root.findViewById(R.id.txtnodata);
        swipeLayout = root.findViewById(R.id.swipeLayout);
        progressBar = root.findViewById(R.id.progressBar);
        productrecycleview = root.findViewById(R.id.productrecycleview);
        sort = root.findViewById(R.id.sortLay);
        filter = root.findViewById(R.id.filterLay);
        sortByText = root.findViewById(R.id.sortBy);
        GridLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        productrecycleview.setLayoutManager(linearLayoutManager);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        getproduct();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getproduct();
                swipeLayout.setRefreshing(false);
            }

        });
        setSortListener();
        setFilterListener();
        getbrandfilter();
        return root;
    }

    private void getbrandfilter() {
        ApiInterface apiInterface=ApiClient.getClient();
        Call<BrandModel> call=apiInterface.callbrand();
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                if (response.isSuccessful())
                {
                    try {
                        Log.d("Gridproduct", "onResponse: brandfilter"+response.body().getBrandInfo());
                        brandlist=response.body().getBrandInfo();

                    }catch (Exception e )
                    {}
                }
            }

            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }


    private void setSortListener() {
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.listview);

                ListView listView = dialog.findViewById(R.id.listview);
                listView.setAdapter(new SortItemListAdapter(getActivity(), sortByArray, sortById));
                listView.setDividerHeight(1);
                listView.setFocusable(true);
                listView.setClickable(true);
                listView.setFocusableInTouchMode(false);
                dialog.show();

                // ListView Click Listener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sortById = i;
                        sortByText.setText(sortByArray[sortById]);

                        // Reload Products List
                        pricefilterget(minprice,maxprice,sortByArray[sortById],"");
                        fillGridView();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void fillGridView() {
//        SessionManager sessionManager = new SessionManager(getActivity());
//        DB_Handler db_handler = new DB_Handler(getActivity());
//        productList = db_handler.getProductsList(sortById, sizeFilter, colorFilter, cat_id, sessionManager.getSessionData(Constants.SESSION_EMAIL));
//        productsGrid.setNumColumns(2);
//        productListAdapter = new ProductListAdapter(getActivity(), productList);
//        productsGrid.setAdapter(productListAdapter);
    }
    private void setFilterListener() {
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.filterlayout);
                //range bar
                rangeSeekbar = dialog.findViewById(R.id.rangeSeekbar);
                rangeSeekbar.setNotifyWhileDragging(true);
                rating5=dialog.findViewById(R.id.checkbox5);
                rating4=dialog.findViewById(R.id.checkbox4);
                rating3=dialog.findViewById(R.id.checkbox3);
                rating2=dialog.findViewById(R.id.checkbox2);
                rating1=dialog.findViewById(R.id.checkbox1);
                if (rating5.isChecked())
                {
                    ratg="5-5";
                    rating4.setChecked(false);
                    rating3.setChecked(false);
                    rating2.setChecked(false);
                    rating1.setChecked(false);
                }
                if (rating4.isChecked())
                {
                    ratg="4-5";
                    rating5.setChecked(false);
                    rating3.setChecked(false);
                    rating2.setChecked(false);
                    rating1.setChecked(false);
                }
                if (rating3.isChecked())
                {
                    ratg="3-5";
                    rating5.setChecked(false);
                    rating4.setChecked(false);
                    rating2.setChecked(false);
                    rating1.setChecked(false);
                }
                if (rating2.isChecked())
                {
                    ratg="2-5";
                    rating5.setChecked(false);
                    rating3.setChecked(false);
                    rating4.setChecked(false);
                    rating1.setChecked(false);
                }
                if (rating1.isChecked())
                {
                    ratg="1-5";
                    rating5.setChecked(false);
                    rating3.setChecked(false);
                    rating2.setChecked(false);
                    rating4.setChecked(false);
                }
                rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                    @Override
                    public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                        minprice=minValue;
                        maxprice=maxValue;

                        Toast.makeText(getActivity(), "Min Value- " + minValue + " & " + "Max Value- " + maxValue, Toast.LENGTH_LONG).show();
                    }
                });


                 List<String> brands = new ArrayList<>();
                for (int i=0;i<brandlist.size();i++) {
                   brands.add(brandlist.get(i).getBrandName());

                   // sizeFilter.add(brandlist.get(i).getBrandName());
                }
                Log.d("Gridproduct", "onClick: "+brands);
                // Get Colors and Get Sizes
              //  DB_Handler db_handler = new DB_Handler(getActivity());
                final List<String> colors = null;// db_handler.getAllColors();
                final List<String> sizes = brands;//db_handler.getAllSizes();

                // Add into hash map
                HashMap<String, List<String>> listHashMap = new HashMap<>();
                 listHashMap.put("Brands", sizes);
//                listHashMap.put("Color", colors);

                // Add Headers
                List<String> headers = new ArrayList<>();
                headers.add("Brands");


                final ExpandableListView listView = dialog.findViewById(R.id.expandableList);
                final FilterItemListAdapter filterItemListAdapter = new FilterItemListAdapter(getActivity(), headers, listHashMap, sizeFilter);
                listView.setAdapter(filterItemListAdapter);
                listView.setDividerHeight(1);
                listView.setFocusable(true);
                listView.setClickable(true);
                listView.setFocusableInTouchMode(false);
                dialog.show();

                // ListView Click Listener
                listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                        switch (groupPosition) {
                            case 0: // Size
                                if (!sizeFilter.contains(sizes.get(childPosition))) {
                                    sizeFilter.add(sizes.get(childPosition));


                                } else {
                                    sizeFilter.remove(sizes.get(childPosition));
                                }
                                break;


                        }
                        filterItemListAdapter.notifyDataSetChanged();
                        return false;
                    }
                });
                // Filter Apply Button Click
                Button apply = dialog.findViewById(R.id.apply);
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Reload Products List By Filter
                        pricefilterget(minprice,maxprice,"",ratg);
                        fillGridView();
                        dialog.dismiss();
                    }
                });
                // Clear All Button Click
                Button clear = dialog.findViewById(R.id.clear);
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            sizeFilter.clear();
                            rangeSeekbar.clearFocus();
                        } catch (NullPointerException ignore) {

                        }

                        try {
                            colorFilter.clear();

                        } catch (NullPointerException ignore) {

                        }
                        filterItemListAdapter.notifyDataSetChanged();
                    }
                });
                // Close Button
                final ImageView close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    
    private void pricefilterget(Object minValue, Object maxValue,String sort,String ratg) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface= ApiClient.getClient();
        Call<ProductModel> call=apiInterface.callcatproductlist("",minValue+"-"+maxValue,sort,ratg);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful())
                {
                    try {
                        Log.d("gridproduct", "onResponse: pricefilter"+response.body().getProductInfo());
                        productAdapter=new ProductAdapter(getContext(),response.body().getProductInfo());
                        productrecycleview.setAdapter(productAdapter);
                        progressBar.setVisibility(View.GONE);
                    }catch (Exception e)
                    {e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }
    private void getproduct() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface= ApiClient.getClient();
        Call<ProductModel> call=apiInterface.callcatproductlist(getArguments().getString(Constant.ID),"","","");
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response)
            {
                if (response.isSuccessful())
                {
                    ArrayList<ProductInfo> productInfoArrayList=response.body().getProductInfo();
                    for (int i=0;i<productInfoArrayList.size();i++)
                    {
                        String id=productInfoArrayList.get(i).getName();
                        Log.d("responseproduct",""+id);
                        productAdapter=new ProductAdapter(getContext(),productInfoArrayList);
                        productrecycleview.setAdapter(productAdapter);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }
}