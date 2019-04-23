package com.example.empal.remote;

import com.example.empal.model.ResObj;
import com.example.empal.model.Student;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("WebService/LoginAndroid")
    Call<ResObj> login(@Query("username") String username, @Query("password") String password);

    @GET("WebService/GetImageUrl")
    Call<ResObj> getImage(@Query("username") String username);

    @GET("WebService/GetStudentProfile")
    Call<Student> getUserProfile(@Query("username") String username);
}
