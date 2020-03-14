package com.dodemy.android.workingwithlivedata.service;

import com.dodemy.android.workingwithlivedata.model.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestApiService {


    @GET("feed.json")
    Call<BlogWrapper> getPopularBlog();

}
