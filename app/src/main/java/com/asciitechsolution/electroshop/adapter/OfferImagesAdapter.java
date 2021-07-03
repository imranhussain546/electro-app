package com.asciitechsolution.electroshop.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.asciitechsolution.electroshop.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class OfferImagesAdapter extends PagerAdapter {

    List<Integer> offerImages;

    public OfferImagesAdapter(List<Integer> offerImages) {
        this.offerImages = offerImages;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView offerImage = new ImageView(container.getContext());
        Glide.with(container.getContext()).load(offerImages.get(position)).apply(new RequestOptions().placeholder(R.mipmap.apple)).into(offerImage);
        container.addView(offerImage,0);
        return offerImage;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return offerImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
