package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.GridProductFragment;
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Shop_by_cat_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<CategoryInfo> shop_by_category_models;
    private Context context;
    public Shop_by_cat_Adapter(ArrayList<CategoryInfo> shop_by_category_models,Context context) {
        this.shop_by_category_models = shop_by_category_models;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.shop_by_catog_cardview, parent, false);
            return new ShopbycatView(view) {
            };
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ShopbycatView) {

            populateItemRows((ShopbycatView) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
    }

    private void populateItemRows(ShopbycatView viewHolder, int position) {
        final CategoryInfo shop_by_category_model=shop_by_category_models.get(position);
        Picasso.with(context)
                .load("https://mangatradios.com/"+shop_by_category_model.getImage())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.noimageavilable)
                .into(viewHolder.iv);
        viewHolder.name.setText(shop_by_category_model.getName());
        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new GridProductFragment();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID,shop_by_category_model.getId());
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return shop_by_category_models == null ? 0 : shop_by_category_models.size();
    }
    @Override
    public int getItemViewType(int position) {
        return shop_by_category_models.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    class ShopbycatView extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView iv;
        TextView name;
        public ShopbycatView(@NonNull View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.shopbycatCARD);
            iv=itemView.findViewById(R.id.shopIV);
            name = itemView.findViewById(R.id.shopTV1);

        }


    }
    class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
