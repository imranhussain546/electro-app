package com.asciitechsolution.electroshop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asciitechsolution.electroshop.adapter.BrandAdapter;
import com.asciitechsolution.electroshop.model.BrandModel;
import com.asciitechsolution.electroshop.model.PopularCategory;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.asciitechsolution.electroshop.model.category.CategoryModel;
import com.asciitechsolution.electroshop.R;
import com.asciitechsolution.electroshop.SearchFragment;
import com.asciitechsolution.electroshop.adapter.BestSaleAdapter;
import com.asciitechsolution.electroshop.adapter.CategoryAdapterHome;
import com.asciitechsolution.electroshop.adapter.Shop_by_cat_Adapter;
import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.api.ApiInterface;
import com.asciitechsolution.electroshop.model.BestSaleInfo;
import com.asciitechsolution.electroshop.model.BestSaleModel;
import com.asciitechsolution.electroshop.model.SliderInfo;
import com.asciitechsolution.electroshop.model.SliderModel;
import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private RecyclerView categoryRecyclerView,shop_bycategoryRecyclerView,trendingProductRecyclerView;
    private List<PopularCategory> categoryModelFakeList = new ArrayList<>();
    private LinearLayout lytSearchview;
    private SearchView searchView;
    private ArrayList<CategoryInfo> shop_by_category_modelsList;
    private CategoryAdapterHome categoryAdapter;
    private Shop_by_cat_Adapter shop_by_cat_adapter;
    private BestSaleAdapter bestSaleAdapter;
    private SliderLayout sliderLayout;
    private HashMap<String,String> Hash_file_maps;
    private CircleImageView im1,im2,im3,im4;
    private ArrayList<SliderInfo> sliderInfos;
    private ArrayList<BestSaleInfo> bestSaleInfos;
    public static ArrayList<BrandInfo> brandInfos;
    private TextView showmore,showless;
    int currentSize;
    int totalsize;
    boolean isLoading = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        shop_bycategoryRecyclerView=view.findViewById(R.id.home_shop_by_cat);
        trendingProductRecyclerView=view.findViewById(R.id.home_trendingProduct);
        lytSearchview = view.findViewById(R.id.lytSearchview);
        searchView=view.findViewById(R.id.search_view);
        im1=view.findViewById(R.id.circularIV1);
        im2=view.findViewById(R.id.circularIV2);
        im3=view.findViewById(R.id.circularIV3);
        im4=view.findViewById(R.id.circularIV4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        showmore =view.findViewById(R.id.showmore);
        showless=view.findViewById(R.id.showless);
        setHasOptionsMenu(true);
        categoryModelFakeList.add(new PopularCategory(R.drawable.category, "Categories"));
        categoryModelFakeList.add(new PopularCategory(R.drawable.brnd, "Brands"));
        categoryModelFakeList.add(new PopularCategory(R.drawable.flash, "Flash sale"));
        categoryModelFakeList.add(new PopularCategory(R.drawable.popular, "Popular"));

        categoryAdapter = new CategoryAdapterHome(getContext(),categoryModelFakeList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        sliderLayout = view.findViewById(R.id.slider);
        if (ApiClient.isConnected(getActivity()))
        {
            getapidata();
        }else{
            Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "no_internet_connection_try_later", Snackbar.LENGTH_LONG);
            snackBar.show();
        }






        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(true);
                searchView.onActionViewCollapsed();
                loadFragment(new SearchFragment());
            }
        });
        lytSearchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SearchFragment());
            }
        });

        return view;
    }

    private void getapidata() {
        shopbycatog();
        branddata();
        getBesstsaledata();
        ApiInterface apiInterface=ApiClient.getClient();
        Call<SliderModel> call=apiInterface.callslider();
        call.enqueue(new Callback<SliderModel>() {
            @Override
            public void onResponse(Call<SliderModel> call, Response<SliderModel> response) {
                sliderInfos=new ArrayList<SliderInfo>();
                if (response.isSuccessful()){


                    sliderInfos=response.body().getSliderInfo();
                    mySliderImages();
//                        for (int i=0;i<sliderInfos.size();i++)
//                        {
//                            Log.d("homefragment", "onResponse:"+"https://mangatradios.com/"+sliderInfos.get(i).getSliderImage(
//
//                            ));
//                            TextSliderView textSliderView = new TextSliderView(getActivity());
//                            textSliderView
//                                    .description(sliderInfos.get(i).getSliderLink())
//                                    .image(R.drawable.image1)
//                                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) getActivity());
//                            textSliderView.bundle(new Bundle());
//                            textSliderView.getBundle()
//                                    .putString("extra", sliderInfos.get(i).getSliderLink());
//                            sliderLayout.addSlider(textSliderView);
//                        }
//                        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//                        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//                        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//                        sliderLayout.setDuration(3000);
//                        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) getActivity());

                }
            }

            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }

    private void branddata() {
        ApiInterface apiInterface=ApiClient.getClient();
        Call<BrandModel> call=apiInterface.callbrand();
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                if (response.isSuccessful())
                {
                    try {
                        Log.d("homefragment", "onResponse: brand "+response.body().toString());
                        brandInfos=response.body().getBrandInfo();
                        for (int i=0;i<4;i++)
                        {
                            Picasso.with(getContext())
                                    .load("https://mangatradios.com/"+brandInfos.get(i).getBrandImage())
                                    .fit()
                                    .centerInside()
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(im1);

                            Picasso.with(getContext())
                                    .load("https://mangatradios.com/"+brandInfos.get(i).getBrandImage())
                                    .fit()
                                    .centerInside()
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(im2);
                            Picasso.with(getContext())
                                    .load("https://mangatradios.com/"+brandInfos.get(i).getBrandImage())
                                    .fit()
                                    .centerInside()
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(im3);
                            Picasso.with(getContext())
                                    .load("https://mangatradios.com/"+brandInfos.get(i).getBrandImage())
                                    .fit()
                                    .centerInside()
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(im4);


                        }
                    }catch (Exception e)
                    {e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }

    private void getBesstsaledata() {

        ApiInterface apiInterface =ApiClient.getClient();
        Call<BestSaleModel> call=apiInterface.callbestsale();
        call.enqueue(new Callback<BestSaleModel>() {
            @Override
            public void onResponse(Call<BestSaleModel> call, Response<BestSaleModel> response) {
                if (response.isSuccessful())
                {
                    totalsize= response.body().getBestSaleInfo().size();
                    bestSaleInfos=new ArrayList<BestSaleInfo>();
                    try {
                        Log.d("homefragment", "onResponse:BESTsale "+response.body().toString());
                        int i = 0;
                        while (i < 10) {
                            bestSaleInfos.add(response.body().getBestSaleInfo().get(i));
                            i++;
                        }
                        trendingproduct();

                    }catch (Exception e)
                    {e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<BestSaleModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
    }


    private void mySliderImages()
    {

        Hash_file_maps = new HashMap();
        for (int i=0;i<sliderInfos.size();i++) {
            Hash_file_maps.put(sliderInfos.get(i).getSliderLink(), "https://mangatradios.com/"+sliderInfos.get(i).getSliderImage());
        }


//        Hash_file_maps.put("There must not be litter on the ground.", R.drawable.image2);
//        Hash_file_maps.put("Keep Parking clean to make it disease free.", R.drawable.image3);
//        Hash_file_maps.put("Come, join and pledge together to park.", R.drawable.image4);
//

        for (String name : Hash_file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }

    private void shopbycatog() {
        GridLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        shop_bycategoryRecyclerView.setLayoutManager(linearLayoutManager);

        ApiInterface apiInterface=ApiClient.getClient();
        Call<CategoryModel> call=apiInterface.callcategory();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.isSuccessful())
                {
                 try {
                     Log.d("homefragment", "onResponse: parentcategory"+response.body().toString());
                    shop_by_category_modelsList=new ArrayList<CategoryInfo>();
                     int i = 0;
                     while (i < 4) {
                        shop_by_category_modelsList.add(response.body().getCategoryInfo().get(i));
                         i++;
                     }
                     shop_by_cat_adapter = new Shop_by_cat_Adapter(shop_by_category_modelsList,getContext());
                     shop_bycategoryRecyclerView.setAdapter(shop_by_cat_adapter);
                 }catch (Exception e)
                 {e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("responseerror",t.toString());
            }
        });
//        shop_by_category_modelsList.add(new Shop_by_category_model(R.drawable.mobiles,"Mobiles","Rs7000"));
//        shop_by_category_modelsList.add(new Shop_by_category_model(R.drawable.camera,"Camera","Rs18000"));
//        shop_by_category_modelsList.add(new Shop_by_category_model(R.drawable.laptop,"Laptop","Rs20000"));
//        shop_by_category_modelsList.add(new Shop_by_category_model(R.drawable.television,"Television","Rs15000"));


    }
    private void trendingproduct() {
        showmore.setVisibility(View.VISIBLE);
        GridLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        trendingProductRecyclerView.setLayoutManager(linearLayoutManager);

//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.macbookpro,"Macbook Pro",5,"Rs 121000"));
//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.hp_core_i5,"Hp core i5", (float) 3.5,"Rs 41000"));
//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.mi_10,"Mi 10",4,"Rs 25999"));
//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.voltas_15_ton,"Voltas 1.5ton",3,"Rs 24000"));
//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.mi_led_tv_4a,"Mi led 4A", (float) 4.5,"Rs 34999"));
//        trendingProduct_modelsList.add(new TrendingProduct_model(R.drawable.whirpool_360,"Whirpool 360",5,"Rs 37000"));

       // trendingAdapter=new TrendingAdapter(bestSaleInfos,getContext());
        bestSaleAdapter=new BestSaleAdapter(bestSaleInfos,getContext());
        trendingProductRecyclerView.setAdapter(bestSaleAdapter);
        trendingProductRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == bestSaleInfos.size() - 1) {
                        //bottom of list!
                        showmore.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (totalsize<=currentSize){
                                    showmore.setVisibility(View.GONE);
                                    showless.setVisibility(View.VISIBLE);
                                    showless.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            showless.setVisibility(View.GONE);
                                            isLoading = false;
                                            getBesstsaledata();
                                        }
                                    });
                                }else{
                                    loadMore();
//                                shomore.setVisibility(View.GONE);
                                }

                            }
                        });

                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        //showless.setVisibility(View.VISIBLE);
        bestSaleInfos.add(null);
     bestSaleAdapter.notifyItemInserted(bestSaleInfos.size() - 1);




                ApiInterface apiInterface=ApiClient.getClient();
                Call<BestSaleModel> call=apiInterface.callbestsale();
                call.enqueue(new Callback<BestSaleModel>() {
                    @Override
                    public void onResponse(Call<BestSaleModel> call, Response<BestSaleModel> response) {
                        if (response.isSuccessful())
                        {
                            bestSaleInfos.remove(bestSaleInfos.size() - 1);
                            int scrollPosition = bestSaleInfos.size();
                            bestSaleAdapter.notifyItemRemoved(scrollPosition);
                            currentSize = scrollPosition;
                            int nextLimit = currentSize + 10;

                            try {
                                while (currentSize - 1 < nextLimit) {
                                    bestSaleInfos.add(response.body().getBestSaleInfo().get(currentSize)  );
                                    currentSize++;
                                }
                                Log.d("homefragment", "run: scrollsize"+scrollPosition+"currentsize"+currentSize+"total"+totalsize);

                            }catch (Exception e)
                            {e.printStackTrace();}
                        }
                    }

                    @Override
                    public void onFailure(Call<BestSaleModel> call, Throwable t) {

                    }
                });

                bestSaleAdapter.notifyDataSetChanged();
                isLoading = false;


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}