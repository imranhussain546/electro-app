package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductImagesAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> productImages;

    public ProductImagesAdapter(Context context,ArrayList<String> productImages) {
        this.context=context;
        this.productImages = productImages;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());
      //  Glide.with(container.getContext()).load(productImages.get(position)).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(productImage);
        Picasso.with(context)
                .load(productImages.get(position))
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(productImage);
        container.addView(productImage,0);
        return productImage;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
