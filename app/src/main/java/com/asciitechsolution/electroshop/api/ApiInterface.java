package com.asciitechsolution.electroshop.api;

import com.asciitechsolution.electroshop.model.BestSaleModel;
import com.asciitechsolution.electroshop.model.BrandModel;
import com.asciitechsolution.electroshop.model.ImageModel;
import com.asciitechsolution.electroshop.model.SliderModel;
import com.asciitechsolution.electroshop.model.product.ProductModel2;
import com.asciitechsolution.electroshop.model.product.ProductRawdata;
import com.asciitechsolution.electroshop.model.category.CategoryInfo;
import com.asciitechsolution.electroshop.model.category.CategoryModel;

import com.asciitechsolution.electroshop.model.product.ProductModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("parentcategorylist")
    Call<CategoryModel> callcategory();


    @GET("Subcategorylist")
    Call<CategoryModel> callsubcategory(
            @Query("cat_id") String id
    );


    @GET("productlist")
    Call<ProductModel> callproductlist();

    @GET("productlist")
    Call<ProductModel> callcatproductlist(
            @Query("category") String id,
            @Query("price") String price,
            @Query("sort") String sort,
            @Query("rate") String rating
    );

    @GET("search")
    Call<ProductModel> callsearch(
            @Query("keyword") String keyword
    );

    @POST("productDetail")
    Call<ProductModel2> callproductdetail(
            @Body ProductRawdata productRawdata
            );
    @GET("image_gallery")
    Call<ImageModel> callimages(
            @Query("product_id") String id
    );

    @GET("brand_list")
    Call<BrandModel> callbrand();

    @FormUrlEncoded
    @POST("retrieve_brand_product")
    Call<BrandModel> callbrandproduct(
            @Field("brand_id") String id
    );

    @GET("slider_list")
    Call<SliderModel> callslider();

    @GET("best_sales_category")
    Call<BestSaleModel> callbestsale();
}
