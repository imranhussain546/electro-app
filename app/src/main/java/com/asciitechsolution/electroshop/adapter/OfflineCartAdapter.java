package com.asciitechsolution.electroshop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.CartFragment;
import com.asciitechsolution.electroshop.Constant;
import com.asciitechsolution.electroshop.DatabaseHelper;
import com.asciitechsolution.electroshop.ProductDetailsFragment;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.model.product.ProductDetail;
import com.asciitechsolution.electroshop.model.product.ProductDetailitem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OfflineCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // for load more
    public final int VIEW_TYPE_ITEM = 0;
    public final int VIEW_TYPE_LOADING = 1;
    public boolean isLoading;
    Activity activity;
    ArrayList<ProductDetailitem> items;
    DatabaseHelper databaseHelper;
    int price=0;


    public OfflineCartAdapter(Activity activity, ArrayList<ProductDetailitem> items) {
        this.activity = activity;
        this.items = items;
        databaseHelper = new DatabaseHelper(activity);
    }



    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.lyt_cartlist, parent, false);
            return new ProductHolderItems(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_progressbar, parent, false);
            return new ViewHolderLoading(view);
        }


        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderparent, final int position) {
        final ProductDetailitem cart = items.get(position);
        if (holderparent instanceof ProductHolderItems) {
            final ProductHolderItems holder = (ProductHolderItems) holderparent;


           price = Integer.parseInt(cart.getOnsalePrice());
           int count= Integer.parseInt(databaseHelper.CheckOrderExists(cart.getProductId()));
            Picasso.with(activity)
                    .load("https://mangatradios.com/"+cart.getImageThumb())
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imgproduct);

            holder.txtproductname.setText(cart.getProductName());
           // holder.txtmeasurement.setText(cart. + "\u0020" + cart.getItem().get(0).getUnit());
            //holder.txtprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(Double.parseDouble(cart.getOnsalePrice())));
          // holder.txtprice.setText("Rs: "+cart.getOnsalePrice());
            holder.txtquantity.setText(databaseHelper.CheckOrderExists(cart.getProductId()));
            holder.txttotalprice.setText("Rs: "+ price * Integer.parseInt(databaseHelper.CheckOrderExists(cart.getProductId())));
            Constant.finalitemprice = 0;
            Constant.FLOAT_TOTAL_AMOUNT = Constant.FLOAT_TOTAL_AMOUNT + (price *Integer.parseInt(databaseHelper.CheckOrderExists(cart.getProductId())) ) ;
            CartFragment.SetData();

            Log.d("countadapter",""+databaseHelper.CheckOrderExists(cart.getProductId()));
//            if (cart.getItem().get(0).getDiscounted_price().equals("0")) {
//                holder.txtprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(Double.parseDouble(cart.getItem().get(0).getPrice())));
//                price = Double.parseDouble(cart.getItem().get(0).getPrice());
//            } else if (!cart.getItem().get(0).getDiscounted_price().equalsIgnoreCase(cart.getItem().get(0).getPrice())) {
//                holder.txtoriginalprice.setPaintFlags(holder.txtoriginalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                holder.txtoriginalprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(Double.parseDouble(cart.getItem().get(0).getPrice())));
//            }

           // holder.txtQuantity.setText(databaseHelper.CheckOrderExists(cart.getId(), cart.getProduct_id()));

           // holder.txttotalprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(price * Integer.parseInt(databaseHelper.CheckOrderExists(cart.getId(), cart.getProduct_id()))));

           // Constant.FLOAT_TOTAL_AMOUNT = Constant.FLOAT_TOTAL_AMOUNT + (price * Integer.parseInt(databaseHelper.CheckOrderExists(cart.getId(), cart.getProduct_id())));
            //CartFragment.SetData();

//            final double finalPrice = price;
//            holder.btnaddqty.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (ApiClient.isConnected(activity)) {
                      //  if (!(Integer.parseInt(holder.txtQuantity.getText().toString()) >= Float.parseFloat(cart.))) {
                    //        if (!(Integer.parseInt(holder.txtQuantity.getText().toString()) + 1 > Integer.parseInt(Constant.systemSettings.getMax_cart_items_count()))) {
//                                int count = Integer.parseInt(holder.txtQuantity.getText().toString());
//                                count++;
//                                holder.txtQuantity.setText("" + count);
                  //              holder.txttotalprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(finalPrice * count));
                //                Constant.FLOAT_TOTAL_AMOUNT = Constant.FLOAT_TOTAL_AMOUNT + finalPrice;
//                                databaseHelper.AddOrderData( cart.getProductId(), "" + count);
              //                  CartFragment.SetData();
            //                } else {
          //                      Toast.makeText(activity, activity.getString(R.string.limit_alert), Toast.LENGTH_SHORT).show();
//                            }
        //                } else {
      //                      Toast.makeText(activity, activity.getString(R.string.stock_limit), Toast.LENGTH_SHORT).show();
    //                    }
                   // }

  //              }
//            });

//            holder.btnminusqty.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (ApiClient.isConnected(activity)) {
//                        if (Integer.parseInt(holder.txtQuantity.getText().toString()) > 1) {
//                            int count = Integer.parseInt(holder.txtQuantity.getText().toString());
//                            count--;
//                            holder.txtQuantity.setText("" + count);
//                            holder.txttotalprice.setText(Constant.systemSettings.getCurrency() + Constant.formater.format(finalPrice * count));
//                            Constant.FLOAT_TOTAL_AMOUNT = Constant.FLOAT_TOTAL_AMOUNT - finalPrice;
//                            databaseHelper.AddOrderData(cart.getProductId(), "" + count);
//                            CartFragment.SetData();
//                        }
//                    }
//                }
//            });

            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ApiClient.isConnected(activity)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Remove Product");
                        builder.setIcon(android.R.drawable.ic_delete);
                        builder.setMessage("Are you sure, you want to remove this product from cart ?");

                        builder.setCancelable(false);
                        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                CartFragment.offlineCarts.remove(cart);
                                notifyItemRemoved(position);
                                databaseHelper.DeleteOrderData(cart.getProductId());
                               Constant.FLOAT_TOTAL_AMOUNT = Double.parseDouble(Constant.formater.format(Constant.FLOAT_TOTAL_AMOUNT - (price * Integer.parseInt(databaseHelper.CheckOrderExists(cart.getProductId())))));
                               CartFragment.SetData();

                                items.remove(cart);
                                CartFragment.isSoldOut = false;
                                Constant.FLOAT_TOTAL_AMOUNT = 0.00;
                                notifyDataSetChanged();
                                databaseHelper.getTotalItemOfCart(activity);
                                Constant.TOTAL_CART_ITEM = getItemCount();
                                CartFragment.SetData();
                                activity.invalidateOptionsMenu();
                                if (getItemCount() == 0) {
                                    CartFragment.lytempty.setVisibility(View.VISIBLE);
                                    CartFragment.lytTotal.setVisibility(View.GONE);
                                }
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                }
            });

        } else if (holderparent instanceof ViewHolderLoading) {
            ViewHolderLoading loadingViewHolder = (ViewHolderLoading) holderparent;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
        Log.d("offlinecart", "onBindViewHolder: price"+price);
        Log.d("offlinecart", "onBindViewHolder: FLOAT_TOTAL_AMOUNT"+Constant.FLOAT_TOTAL_AMOUNT);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        ProductDetailitem cart = items.get(position);
        if (cart != null)
            return Integer.parseInt(cart.getId());
        else
            return position;
    }

    static class ViewHolderLoading extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ViewHolderLoading(View view) {
            super(view);
            progressBar = view.findViewById(R.id.itemProgressbar);
        }
    }

    public static class ProductHolderItems extends RecyclerView.ViewHolder {
        ImageView imgproduct, imgdelete, btnminusqty, btnaddqty;
        TextView txtproductname, txtquantity, txtprice, txtoriginalprice, txttotalprice;

        public ProductHolderItems(@NonNull View itemView) {
            super(itemView);
            imgproduct = itemView.findViewById(R.id.imgproduct);

            imgdelete = itemView.findViewById(R.id.imgdelete);
            btnminusqty = itemView.findViewById(R.id.btnminusqty);
            btnaddqty = itemView.findViewById(R.id.btnaddqty);

            txtproductname = itemView.findViewById(R.id.txtproductname);
            txtquantity = itemView.findViewById(R.id.quantity);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtoriginalprice = itemView.findViewById(R.id.txtoriginalprice);

            txttotalprice = itemView.findViewById(R.id.txttotalprice);
        }
    }
}