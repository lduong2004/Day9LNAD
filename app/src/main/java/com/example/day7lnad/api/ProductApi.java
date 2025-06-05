package com.example.day7lnad.api;

import com.example.day7lnad.constants.ConstantApi;
import com.example.day7lnad.models.AllProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET(ConstantApi.GET_ALL_PRODUCTS)
    Call<AllProductResponse> getAllProducts();

    @GET(ConstantApi.SEARCH_PRODUCTS)
    Call<AllProductResponse> searchProducts(@Query("q") String prodcutName);
}
