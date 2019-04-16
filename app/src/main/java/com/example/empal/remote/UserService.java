package com.example.empal.remote;

import com.example.empal.model.ResObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("LoginAndroid")
    Call<ResObj> login(@Query("username") String username, @Query("password") String password);

}
