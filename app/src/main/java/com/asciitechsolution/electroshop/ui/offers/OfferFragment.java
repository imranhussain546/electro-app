package com.asciitechsolution.electroshop.ui.offers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.adapter.OfferAdapter;
import com.asciitechsolution.electroshop.adapter.OfferImagesAdapter;
import com.asciitechsolution.electroshop.model.OfferModel;

import java.util.ArrayList;
import java.util.List;


public class OfferFragment extends Fragment {
    private ViewPager offerImagesViewPager;
    private RecyclerView offerRecy;
private List<OfferModel> offerModel_modelsList=new ArrayList<>();
private OfferAdapter offerAdapter;
    private List<Integer> offerImages = new ArrayList<Integer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_offer, container, false);
        offerImagesViewPager = view.findViewById(R.id.offer_imageView);
        offerRecy=view.findViewById(R.id.offer_recycler);

        offerImages.add(R.drawable.image4);
        OfferImagesAdapter productImagesAdapter = new OfferImagesAdapter(offerImages);
        offerImagesViewPager.setAdapter(productImagesAdapter);

        GridLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        offerRecy.setLayoutManager(linearLayoutManager);

        offerModel_modelsList.add(new OfferModel(R.drawable.macbookpro,"Flat 30% off"));
        offerModel_modelsList.add(new OfferModel(R.drawable.voltas_15_ton,"Flat 70% off"));
        offerModel_modelsList.add(new OfferModel(R.drawable.mi_led_tv_4a,"Flat 30% off with no cost of EMI"));
        offerModel_modelsList.add(new OfferModel(R.drawable.hp_core_i5,"Flat 10% off"));

        offerAdapter=new OfferAdapter(offerModel_modelsList);
        offerRecy.setAdapter(offerAdapter);
        return view;
    }
}