package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.asciitechsolution.electroshop.ui.brand.BrandProductFragment;
import com.asciitechsolution.electroshop.ui.category.SubCategoryFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder>{
    public ArrayList<BrandInfo> brandInfos;
    public Context context;
    public BrandAdapter(ArrayList<BrandInfo> brandInfos,Context context) {
        this.brandInfos = brandInfos;
        this.context=context;
    }


    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brandcard, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        final BrandInfo brandInfo=brandInfos.get(position);
        Picasso.with(context)
                .load("https://mangatradios.com/"+brandInfo.getBrandImage())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image);
//        holder.bname.setText(brandInfo.getBrandName());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BrandProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID, brandInfo.getBrandId());
                fragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandInfos.size();
    }

    class BrandViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView bname;
        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.brandiv);
//            bname=itemView.findViewById(R.id.brandname);
        }
    }
}
