package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.product.ProductInfo;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder> {

    public ArrayList<ProductInfo> productlist;
    Context context;

    public ProductAdapter(Context context, ArrayList<ProductInfo> productlist) {
        this.context=context;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_product_cardview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Picasso.with(context)
                .load("https://mangatradios.com/"+productlist.get(position).getImageThumb())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.iv);
        //String image = productlist.get(position).getImageThumb();
        String name= productlist.get(position).getName();
        String firsttenchar="";
        if (name.length() > 7)
        {
            firsttenchar = name.substring(0, 12)+"...";
        }
         else
        {
            firsttenchar = name;
        }
        int rating= productlist.get(position).getRating();
        String price= (String) productlist.get(position).getOnsalePrice();
        holder.setData(firsttenchar,rating,price);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String nae= productlist.get(position).getName();
                 Log.d("onclick",""+name);
                Fragment fragment= new ProductDetailsFragment();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID,productlist.get(position).getId());
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
        return productlist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView textView1,textView2;
        SimpleRatingBar ratingBar;
        RelativeLayout relativeLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.productgrid);
            iv=itemView.findViewById(R.id.trendingIV);
            textView1= itemView.findViewById(R.id.trendingTV1);
            ratingBar= itemView.findViewById(R.id.trendingRB);
            textView2=itemView.findViewById(R.id.trendingTV2);

        }

        private void  setData(String name,int rating,String price){
            ratingBar.setRating(rating);
            textView1.setText(""+name);
            textView2.setText("Rs: "+price);

        }
    }
}
