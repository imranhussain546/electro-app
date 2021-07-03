package com.asciitechsolution.electroshop.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.OfferModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.Viewholder> {


    private List<OfferModel> offer_modelList;

    public OfferAdapter(List<OfferModel> offer_modelList) {
        this.offer_modelList = offer_modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_cardview, parent, false);


        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int image = offer_modelList.get(position).getOffer_image();
        String des=offer_modelList.get(position).getOffer_des();

        holder.setData(image,des);
    }

    @Override
    public int getItemCount() {
        return offer_modelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView textView1,textView2;
        SimpleRatingBar ratingBar;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.offerIV);
            textView1= itemView.findViewById(R.id.offerTV);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        }

        private void  setData(int image,String des){
            Glide.with(itemView.getContext()).load(image).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(iv);
            textView1.setText(""+des);


        }
    }
}
