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
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.category.Categorieslevelone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CategorySubAdapter extends RecyclerView.Adapter<CategorySubAdapter.ViewHolder> {
    public ArrayList<Categorieslevelone> categoriesleveloneslist;
    int layout;
    Activity activity;
    Context context;
    String from;
    int visibleNumber;


    public CategorySubAdapter(Context context, Activity activity, ArrayList<Categorieslevelone> categoriesleveloneslist, int layout, String from, int visibleNumber) {
        this.context = context;
        this.categoriesleveloneslist = categoriesleveloneslist;
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
        final Categorieslevelone categorieslevelone = categoriesleveloneslist.get(position);
        holder.txttitle.setText(categorieslevelone.getName());

        Picasso.with(context)
                .load(categorieslevelone.getImage())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgcategory);
        Log.d("subcat",categorieslevelone.getId());
        holder.lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=categorieslevelone.getId();
                Log.d("catid",""+id);
                Fragment fragment = new GridProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ID, categorieslevelone.getId());
                fragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.main, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoriesleveloneslist.size();
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
