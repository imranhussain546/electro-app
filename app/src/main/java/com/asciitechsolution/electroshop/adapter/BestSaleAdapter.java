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
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.BestSaleInfo;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BestSaleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private ArrayList<BestSaleInfo> bestSaleInfos;
    private Context context;

    public BestSaleAdapter(ArrayList<BestSaleInfo> bestSaleInfos, Context context) {
        this.bestSaleInfos = bestSaleInfos;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_product_cardview, parent, false);
            return new ProductViewholder(view) {
            };
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ProductViewholder) {

            populateItemRows((ProductViewholder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
    }

    private void populateItemRows(ProductViewholder holder, int position) {
        final BestSaleInfo bestSaleInfo=bestSaleInfos.get(position);
        Picasso.with(context)
                .load("https://mangatradios.com/"+bestSaleInfo.getImageThumb())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.iv);
        String name= bestSaleInfo.getName();
        String firsttenchar="";
        if (name.length() > 7)
        {
            firsttenchar = name.substring(0, 12)+"...";
        }
        else
        {
            firsttenchar = name;
        }
        holder.name.setText(firsttenchar);
        holder.price.setText(bestSaleInfo.getOnsalePrice());
        holder.ratingBar.setRating(bestSaleInfo.getRating());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new ProductDetailsFragment();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID,bestSaleInfo.getId());
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
         return bestSaleInfos == null ? 0 : bestSaleInfos.size();
    }
    @Override
    public int getItemViewType(int position) {
        return bestSaleInfos.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView iv;
        TextView name, price;
        SimpleRatingBar ratingBar;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.trendingcard);
            iv=itemView.findViewById(R.id.trendingIV);
            name = itemView.findViewById(R.id.trendingTV1);
            ratingBar= itemView.findViewById(R.id.trendingRB);
            price =itemView.findViewById(R.id.trendingTV2);
            
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
