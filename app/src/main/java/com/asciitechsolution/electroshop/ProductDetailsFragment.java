package com.asciitechsolution.electroshop;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asciitechsolution.electroshop.adapter.ProductDetailsAdapter;
import com.asciitechsolution.electroshop.adapter.ProductImagesAdapter;
import com.asciitechsolution.electroshop.adapter.ReviewAdapter;
import com.asciitechsolution.electroshop.adapter.Similar_ProductAdapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.ImageInfo;
import com.asciitechsolution.electroshop.model.ImageModel;
import com.asciitechsolution.electroshop.model.product.ProductDetailitem;
import com.asciitechsolution.electroshop.model.product.ProductInfo2;
import com.asciitechsolution.electroshop.model.product.ProductModel2;
import com.asciitechsolution.electroshop.model.product.ProductRawdata;
import com.asciitechsolution.electroshop.model.ProductSpecificationModel;
import com.asciitechsolution.electroshop.model.product.RelatedProduct;
import com.asciitechsolution.electroshop.model.product.Review;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    RelativeLayout relativeLayout;
    private ViewPager productImagesViewPager;
    private TextView productTitle;
    private TextView productPrice;
    private TextView cuttedPrice;
    private TextView txtqty;
    private TabLayout viewPagerIndicator;
    private Button buyNowBtn;
    private Spinner spinner;
    private LinearLayout Addtocart;
    private List<Integer> productImages = new ArrayList<Integer>();
    /*********** PRODUCT DESCRIPTION ******/

    private ConstraintLayout productDetailsTabsContainer;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    DatabaseHelper databaseHelper;
    public List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
    public Spanned productDescription;
    public Spanned productOtherDetails;
    public Spanned productSpecification;
    /*********** Favourite button ******/
    private static Boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishListBtn;
    ImageButton imgAdd, imgMinus;
    /*********** RATINGS LAYOUT ******/

    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private TextView rating5,rating4,rating3,rating2,rating1;
    private LinearLayout ratingsNoContainer;
    private LinearLayout ratingsProgressBarContainer;
    private TextView totalRatingsFig;
    private TextView averageRating;

    ArrayList<ProductInfo2> productInfo2;
    ArrayList<ImageInfo> imageInfos;
    ArrayList<Review> reviews;
    ArrayList<RelatedProduct> relatedProducts;
    ReviewAdapter reviewAdapter;
    RecyclerView recyclerViewreview,recyclerViewrelated;
    ProductDetailitem product;
    ProgressBar progressBar;
    public static int  count=0;
    int totalitem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_product_details, container, false);
        productImagesViewPager = view.findViewById(R.id.product_images_viewpager);
        productTitle = view.findViewById(R.id.product_title);
        productPrice = view.findViewById(R.id.product_price);
        cuttedPrice = view.findViewById(R.id.cutted_price);
        progressBar = view.findViewById(R.id.progressBar);
        viewPagerIndicator = view.findViewById(R.id.viewpager_indicator);
        productDetailsTabsContainer=view.findViewById(R.id.product_details_tabs_container);
        productDetailsViewPager = view.findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = view.findViewById(R.id.product_details_tablayout);
        buyNowBtn=view.findViewById(R.id.buy_now_btn);
        Addtocart=view.findViewById(R.id.add_to_cart_btn);
        addToWishListBtn = view.findViewById(R.id.add_to_wishlist_btn);
        databaseHelper = new DatabaseHelper(getActivity());
        rateNowContainer = view.findViewById(R.id.rate_now_container);
        totalRatings=view.findViewById(R.id.total_ratings);
        viewPagerIndicator = view.findViewById(R.id.viewpager_indicator);
        totalRatingsFig=view.findViewById(R.id.total_ratings__figure);
        averageRating=view.findViewById(R.id.average_rating);
        spinner=view.findViewById(R.id.productspinner);
        rating5=view.findViewById(R.id.textView13);
        rating4=view.findViewById(R.id.textView14);
        rating3=view.findViewById(R.id.textView15);
        rating2=view.findViewById(R.id.textView16);
        rating1=view.findViewById(R.id.textView17);
        recyclerViewreview=view.findViewById(R.id.reviewrecycler);
        recyclerViewrelated = view.findViewById(R.id.relatedrecyclerView);
        imgAdd = view.findViewById(R.id.btnaddqty);
        imgMinus = view.findViewById(R.id.btnminusqty);
        txtqty = view.findViewById(R.id.txtqty);
        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);
        setHasOptionsMenu(true);
        recyclerViewreview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerViewrelated.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        variantspinner();

        for (int i = 0; i < rateNowContainer.getChildCount(); i++) {
            final int starPosition = i;
            rateNowContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }

        getdetail();
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ALREADY_ADDED_TO_WISHLIST = databaseHelper.getFavouriteById(product.getProductId());
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9E9E9E")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                }
                databaseHelper.AddOrRemoveFavorite(product.getProductId(), ALREADY_ADDED_TO_WISHLIST);
            }
        });

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


            Addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count==0){
                        Log.d("ProductDetails", "onCreateView: "+databaseHelper.getTotalItemOfCart(getActivity()));
//                Snackbar.make(relativeLayout), "Choose Quantity !!", Snackbar.LENGTH_LONG)
//                 .setAction("Action", null).show();
                      Toast.makeText(getActivity(), "Choose Quantity", Toast.LENGTH_SHORT).show();
                    }else{
                        databaseHelper.AddOrderData(product.getProductId(), "" + count);
                        Constant.TOTAL_CART_ITEM++;
                        getActivity().invalidateOptionsMenu();
                        Log.d("addtocart", "onClick: ");
                    }

                }
            });


        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new DeliveryFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        //txtqty.setText(0);
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ApiClient.isConnected(getActivity())) {
                    Constant.CLICK = true;
                 //   count = Integer.parseInt(txtqty.getText().toString());
                    if (!(count <= 0)) {
                        if (count != 0) {
                            count--;
                            txtqty.setText("" + count);

                        }

                    }
                }
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ApiClient.isConnected(getActivity())) {
                   // count = Integer.parseInt(txtqty.getText().toString());
                   // if (!(count >= (totalitem))) {
                        // if (count < Integer.parseInt(Constant.systemSettings.getMax_cart_items_count())) {
                        Constant.CLICK = true;
                        count++;
                        txtqty.setText("" + count);
                        Log.d("addbutton", "onClick: ");
                    //}
                }
            }
        });

        return view;
    }

    private void variantspinner() {
        spinner.setOnItemSelectedListener(this);

        List<String> variant = new ArrayList<String>();
        variant.add("black");
        variant.add("red");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,variant );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void getdetail() {
        progressBar.setVisibility(View.VISIBLE);
        ProductRawdata productRawdata=new ProductRawdata(getArguments().getString(Constant.ID));
        ApiInterface apiInterface=ApiClient.getClient();
        Call<ProductModel2> call=apiInterface.callproductdetail(productRawdata);
        call.enqueue(new Callback<ProductModel2>() {
            @Override
            public void onResponse(Call<ProductModel2> call, Response<ProductModel2> response) {
                if (response.isSuccessful())
                {
                    try {

                     product=new ProductDetailitem();
                     productInfo2 =response.body().getProductInfo();

                    for (int i = 0; i<=productInfo2.size(); i++){
                        product= productInfo2.get(i).getProducDetail();
                       totalitem= productInfo2.get(i).getTotalQuantity();
                        productTitle.setText(productInfo2.get(i).getProducDetail().getProductModel());
                        productPrice.setText(productInfo2.get(i).getProducDetail().getOnsalePrice());
                        cuttedPrice.setText(productInfo2.get(i).getProducDetail().getPrice());

                        Log.d("Productdesci", "onResponse:"+ productInfo2.get(i).getProducDetail().getDescription());
                        Log.d("Productdetail", "onResponse:"+ productInfo2.get(i).getProducDetail().getProductDetails());

                        productDescription = (Html.fromHtml(productInfo2.get(i).getProducDetail().getDescription()));

                        productOtherDetails = Html.fromHtml(productInfo2.get(i).getProducDetail().getProductDetails());
                        productSpecification=Html.fromHtml(productInfo2.get(i).getProducDetail().getSpecification());
                        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getActivity().getSupportFragmentManager(), productDetailsTabLayout.getTabCount(),productDescription,productSpecification,productOtherDetails));
                        totalRatings.setText(productInfo2.get(i).getProducDetail().getTotalRater().toString());
                        rating5.setText(productInfo2.get(i).getProducDetail().getTotalFiveStar().toString());
                        rating4.setText(productInfo2.get(i).getProducDetail().getTotalFourStar().toString());
                        rating3.setText(productInfo2.get(i).getProducDetail().getTotalThreeStar().toString());
                        rating2.setText(productInfo2.get(i).getProducDetail().getTotalTwoStar().toString());
                        rating1.setText(productInfo2.get(i).getProducDetail().getTotalOneStar().toString());
                        totalRatingsFig.setText(productInfo2.get(i).getProducDetail().getTotalRater().toString());
                        averageRating.setText(productInfo2.get(i).getProducDetail().getTotalRate().toString());
                        Log.d("ratingdetail",""+productInfo2.get(i).getProducDetail().getTotalRate());
                        Log.d("ratingdetail",""+productInfo2.get(i).getProducDetail().getTotalRater());


                        reviews=productInfo2.get(i).getReviewList();
                        reviewAdapter=new ReviewAdapter(reviews,getActivity());
                        recyclerViewreview.setAdapter(reviewAdapter);

                        relatedProducts=productInfo2.get(i).getRelatedProduct();
                        Similar_ProductAdapter adapter = new Similar_ProductAdapter(getContext(), getActivity(), relatedProducts, R.layout.similar_product_layout);
                        recyclerViewrelated.setAdapter(adapter);
                        Log.d("ratingdetail",""+relatedProducts.get(i).getProductName());
                        progressBar.setVisibility(View.GONE);
                    }
                }catch (Exception e)
                {e.printStackTrace();}


                }
            }

            @Override
            public void onFailure(Call<ProductModel2> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<String> productImages = new ArrayList<String>();
        ApiInterface apiInterface1=ApiClient.getClient();
        Call<ImageModel> call1=apiInterface1.callimages(getArguments().getString(Constant.ID));
        call1.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                try {
                    if (response.isSuccessful())
                    {
                        imageInfos=response.body().getImageInfo();


                        for (int i=0;i<imageInfos.size();i++)
                        {
                            Log.d("Imageresponse",""+"https://mangatradios.com/"+imageInfos.get(i).getImage());
                            productImages.add("https://mangatradios.com/"+imageInfos.get(i).getImage());
                            ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(getContext(),productImages);
                            productImagesViewPager.setAdapter(productImagesAdapter);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }catch (Exception e)
                {e.printStackTrace();}

            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }

    private void setRating(int starPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            }
            if (x <= starPosition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.main_search_icon).setVisible(true);
        menu.findItem(R.id.main_heart_icon).setVisible(true);
        menu.findItem(R.id.main_profile_icon).setVisible(true);
        menu.findItem(R.id.main_cart_icon).setIcon(ApiClient.buildCounterDrawable(Constant.TOTAL_CART_ITEM,R.drawable.ic_cart,getActivity()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}