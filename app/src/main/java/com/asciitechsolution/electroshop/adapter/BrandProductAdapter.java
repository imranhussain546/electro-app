package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.asciitechsolution.electroshop.ui.brand.BrandProductFragment;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrandProductAdapter extends RecyclerView.Adapter<BrandProductAdapter.ViewHolder> {
    public Context context;
    public ArrayList<BrandInfo> brandInfos;

    public BrandProductAdapter(Context context, ArrayList<BrandInfo> brandInfos) {
        this.context = context;
        this.brandInfos = brandInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_product_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BrandInfo brandInfo=brandInfos.get(position);
        Picasso.with(context)
                .load("https://mangatradios.com/"+brandInfo.getImageThumb())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.iv);

       String name=brandInfo.getProductName();
        String firsttenchar="";
        if (name.length() > 8)
        {
            firsttenchar = name.substring(0, 12)+"...";
        }
        else
        {
            firsttenchar = name;
        }
        holder.pname.setText(firsttenchar);
        holder.pprice.setText("Rs: "+brandInfo.getOnsalePrice());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ProductDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID, brandInfo.getProductId());
                fragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandInfos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView pname, pprice;
        SimpleRatingBar ratingBar;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.productgrid);
            iv=itemView.findViewById(R.id.trendingIV);
            pname = itemView.findViewById(R.id.trendingTV1);
            ratingBar= itemView.findViewById(R.id.trendingRB);
            pprice =itemView.findViewById(R.id.trendingTV2);

        }
    }
}
