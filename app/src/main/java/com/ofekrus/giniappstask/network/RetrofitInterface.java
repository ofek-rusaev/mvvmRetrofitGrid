package com.ofekrus.giniappstask.network;

import com.ofekrus.giniappstask.model.NumbersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("/raw/8wJzytQX")
    Call<NumbersResponse> executeGetNumbers();
}
