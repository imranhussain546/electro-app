package com.asciitechsolution.electroshop.adapter;


import android.text.Spanned;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.asciitechsolution.electroshop.ProductDescriptionFragment;
import com.asciitechsolution.electroshop.ProductSpecificationFragment;
import com.asciitechsolution.electroshop.model.ProductSpecificationModel;

import java.util.List;


public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    private Spanned productDescription;
    private Spanned  productOtherDetails;
    private Spanned productspecification;
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs, Spanned productDescription,Spanned productspecification, Spanned productOtherDetails) {
        super(fm);
        this.totalTabs = totalTabs;
        this.productDescription = productDescription;
        this.productspecification=productspecification;
        this.productOtherDetails = productOtherDetails;

    }



    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                   ProductDescriptionFragment productDescriptionFragment1 = new ProductDescriptionFragment();
                   productDescriptionFragment1.body = productDescription;
                   String descr=productDescription.toString();
                Log.d("product",""+productDescription);
                   return  productDescriptionFragment1;
                case 1:
                        ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                        productSpecificationFragment.body=productspecification;
                        return productSpecificationFragment;
                    case 2:
                        ProductDescriptionFragment productDescriptionFragment2 = new ProductDescriptionFragment();
                        productDescriptionFragment2.body=productOtherDetails;
                        return  productDescriptionFragment2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
