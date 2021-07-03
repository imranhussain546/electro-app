package com.asciitechsolution.electroshop.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.CartItemModel;
import com.asciitechsolution.electroshop.model.product.ProductInfo;
import com.asciitechsolution.electroshop.model.product.Review;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewholder> {
    public ArrayList<Review> reviews;
    public Activity activity;

    public ReviewAdapter(ArrayList<Review> reviews,  Activity activity) {
        this.reviews = reviews;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ReviewViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewcard, null);
        return new ReviewViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewholder holder, int position) {

        final Review review = reviews.get(position);

        holder.cname.setText(review.getCustomerName());
        holder.date.setText(review.getDateTime());
        holder.ratingBar.setRating(Float.parseFloat(review.getRate()));
        holder.comment.setText(review.getComments());
        Log.d("review", "onBindViewHolder:"+review.getCustomerName());


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewholder extends RecyclerView.ViewHolder{
        private CircleImageView iv;
        private TextView cname,date,comment;
        private SimpleRatingBar ratingBar;

        public ReviewViewholder(@NonNull View itemView) {
            super(itemView);

            cname=itemView.findViewById(R.id.reviewcname);
            date=itemView.findViewById(R.id.reviewdate);
            comment=itemView.findViewById(R.id.reviewcomment);
            ratingBar=itemView.findViewById(R.id.reviewrating);
        }
    }
}
