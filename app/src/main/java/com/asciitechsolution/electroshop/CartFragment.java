package com.asciitechsolution.electroshop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asciitechsolution.electroshop.adapter.OfflineCartAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.product.ProductDetailitem;
import com.asciitechsolution.electroshop.model.product.ProductInfo2;
import com.asciitechsolution.electroshop.model.product.ProductModel2;
import com.asciitechsolution.electroshop.model.product.ProductRawdata;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    public static LinearLayout lytempty;
    public static RelativeLayout lytTotal;
   // public static ArrayList<Cart> carts;
    public static ArrayList<ProductDetailitem> offlineCarts;
    public static HashMap<String, String> values;
    public static boolean isSoldOut = false;
    static TextView txttotalamount, txttotalitems, tvConfirmOrder;
  //  static CartAdapter cartAdapter;
 //   static OfflineCartAdapter offlineCartAdapter;
    static Activity activity;
   // static Session session;
    static JSONObject objectbject;
    View root;
    RecyclerView cartrecycleview;
    NestedScrollView scrollView;
    double total;
    ProgressBar progressBar;
    Button btnShowNow;
    private DatabaseHelper databaseHelper;
    ArrayList<ProductInfo2> productInfo2s;

    @SuppressLint("SetTextI18n")
    public static void SetData() {
        txttotalamount.setText(Constant.formater.format(Constant.FLOAT_TOTAL_AMOUNT));
         txttotalitems.setText(Constant.TOTAL_CART_ITEM + " Items");
       // txttotalitems.setText("2"+"items");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_cart, container, false);


        activity = getActivity();
        progressBar = root.findViewById(R.id.progressBar);
        lytTotal = root.findViewById(R.id.lytTotal);
        lytempty = root.findViewById(R.id.lytempty);
        btnShowNow = root.findViewById(R.id.btnShowNow);
        txttotalamount = root.findViewById(R.id.txttotalamount);
        txttotalitems = root.findViewById(R.id.txttotalitems);
        scrollView = root.findViewById(R.id.scrollView);
        cartrecycleview = root.findViewById(R.id.cartrecycleview);
        tvConfirmOrder = root.findViewById(R.id.tvConfirmOrder);
        databaseHelper = new DatabaseHelper(activity);

        cartrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetOfflineCart();
        return root;
    }
    int i;
    private void GetOfflineCart() {
        progressBar.setVisibility(View.VISIBLE);
        if (databaseHelper.getTotalItemOfCart(activity) >= 1){
            Constant.FLOAT_TOTAL_AMOUNT=0;
            Log.d("cartfargment", "GetOfflineCart: "+databaseHelper.getCartList().toString().replace("[", "").replace("]", "").replace("\"", ""));
            ArrayList<String> pid=new ArrayList<String>();
                    pid=databaseHelper.getCartList();
            ProductRawdata productRawdata;
            String productid;

            for (i=0;i<pid.size();i++) {
                productid = pid.get(i);
                Log.d("cardpid", "GetOfflineCart: " + pid.get(i));

                productRawdata = new ProductRawdata(productid);

                offlineCarts = new ArrayList<ProductDetailitem>();
                ApiInterface apiInterface = ApiClient.getClient();
                Call<ProductModel2> call = apiInterface.callproductdetail(productRawdata);
//                int j = i;
                call.enqueue(new Callback<ProductModel2>() {
                    @Override
                    public void onResponse(Call<ProductModel2> call, Response<ProductModel2> response) {

                        try {
                            if (response.isSuccessful()) {
                                Log.d("cartresponse", "onResponse:" + response.body().getProductInfo());
                                productInfo2s = response.body().getProductInfo();
                                for (int j=0;j<productInfo2s.size();j++)
                                {
                                    Log.d("cartresponse", "onResponse:" + response.body().getProductInfo().get(j).getProducDetail());
                                    offlineCarts.add(productInfo2s.get(j).getProducDetail());
                                    OfflineCartAdapter offlineCartAdapter = new OfflineCartAdapter(activity, offlineCarts);
                                    cartrecycleview.setAdapter(offlineCartAdapter);
                                    SetData();
                                    progressBar.setVisibility(View.GONE);

                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductModel2> call, Throwable t) {

                    }
                });

            }


        }
    }


}