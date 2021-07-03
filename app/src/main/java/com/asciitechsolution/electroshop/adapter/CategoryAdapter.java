package com.asciitechsolution.electroshop.adapter;

import android.app.Activity;
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
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.GridProductFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.asciitechsolution.electroshop.ui.category.SubCategoryFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public ArrayList<CategoryInfo> categorylist;
    int layout;
    Activity activity;
    Context context;
    String from;
    int visibleNumber;


    public CategoryAdapter(Context context, Activity activity, ArrayList<CategoryInfo> categorylist, int layout, String from, int visibleNumber) {
        this.context = context;
        this.categorylist = categorylist;
        this.layout = layout;
        this.activity = activity;
        this.from = from;
        this.visibleNumber = visibleNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CategoryInfo categoryInfo = categorylist.get(position);
        holder.txttitle.setText(categoryInfo.getName());


            Picasso.with(context)
                    .load("https://mangatradios.com/"+categoryInfo.getImage())
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imgcategory);



        holder.lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryInfo.getCategorieslevel().equals("1")){
                  //  String name=categoryInfo.getCategorieslevelone().get(position).getName();
                   // Log.d("adapter",name);
                    Fragment fragment = new SubCategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.ID, categoryInfo.getId());
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
                }else{
                    Fragment fragment = new GridProductFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.ID, categoryInfo.getId());
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return categorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txttitle;
        ImageView imgcategory;
        RelativeLayout lytMain;

        public ViewHolder(View itemView) {
            super(itemView);
            lytMain = itemView.findViewById(R.id.lytMain);
            imgcategory = itemView.findViewById(R.id.imgcategory);
            txttitle = itemView.findViewById(R.id.txttitle);
        }

    }
}
