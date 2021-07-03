package com.asciitechsolution.electroshop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.product.RelatedProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shree1 on 3/16/2017.
 */

public class Similar_ProductAdapter extends RecyclerView.Adapter<Similar_ProductAdapter.VideoHolder> {

    public ArrayList<RelatedProduct> relatedProducts;
    public Activity activity;
    public int itemResource;
    Context context;

    public Similar_ProductAdapter(Context context, Activity activity, ArrayList<RelatedProduct> relatedProducts, int itemResource) {
        this.context = context;
        this.activity = activity;
        this.relatedProducts = relatedProducts;
        this.itemResource = itemResource;

    }

    @Override
    public int getItemCount() {
        int product;
        if (relatedProducts.size() > 6) {
            product = 6;
        } else {
            product = relatedProducts.size();
        }
        return product;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(VideoHolder holder, final int position) {
        final RelatedProduct relatedProduct = relatedProducts.get(position);

        Picasso.with(context)
                .load("https://mangatradios.com/"+relatedProduct.getImageThumb())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);

        holder.tvTitle.setText(relatedProduct.getProductName());
        holder.tvPrice.setText(relatedProduct.getOnsalePrice());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity1 = (AppCompatActivity) context;
                Fragment fragment = new ProductDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID, relatedProduct.getProductId());
                fragment.setArguments(bundle);
                activity1.getSupportFragmentManager().beginTransaction().add(R.id.main, fragment).addToBackStack(null).commit();


            }
        });
        Log.d("similarproduct", "onBindViewHolder: "+relatedProduct.getImageThumb());
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(itemResource, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;
        public TextView tvTitle, tvPrice;
        public RelativeLayout relativeLayout;

        public VideoHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            relativeLayout = itemView.findViewById(R.id.play_layout);

        }


    }
}
