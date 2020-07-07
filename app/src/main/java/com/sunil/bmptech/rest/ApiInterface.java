package com.sunil.bmptech.rest;

import com.sunil.bmptech.model.Album;
import com.sunil.bmptech.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("photos")
    Call<List<Album>>getPhotos();

}
