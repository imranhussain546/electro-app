package com.asciitechsolution.electroshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.model.PopularCategory;
import com.asciitechsolution.electroshop.ui.category.CategoryFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CategoryAdapterHome extends RecyclerView.Adapter<CategoryAdapterHome.ViewHolder> {
    private List<PopularCategory> categoryModelList;
    Context context;
    public CategoryAdapterHome(Context context,List<PopularCategory> categoryModelList) {
        this.context=context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapterHome.ViewHolder viewHolder, int position) {
        int icon = categoryModelList.get(position).getCategoryIconLink();
        String name= categoryModelList.get(position).getCategoryName();
        viewHolder.setCategory(name,position);
        viewHolder.setCategoryIcon(icon);
        viewHolder.categoryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryModelList.get(position).getCategoryName().equals("Categories"))
                {
                    Log.d("catclick",""+position);
                    Fragment fragment= new CategoryFragment();
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView categoryIcon;
        private TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon=itemView.findViewById(R.id.category_icon);
            categoryName=itemView.findViewById(R.id.category_name);
        }
        private void setCategoryIcon(int iconUrl){
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(categoryIcon);
        }
        private void setCategory(final String name,final int position){
            categoryName.setText(name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != 0) {

                        }
                    }
                });

        }
    }
}
