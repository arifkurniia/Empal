package com.example.empal.remote;

import com.example.empal.model.CompetencyBook;
import com.example.empal.model.CompetencyGroup;
import com.example.empal.model.CompetencyRegister;
import com.example.empal.model.ResObj;
import com.example.empal.model.Silabus;
import com.example.empal.model.Student;

import java.util.List;

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

    @GET("WebService/GetCompetencyBookSilabus")
    Call<List<CompetencyBook>> getCompetencyBookSilabus(@Query("username") String username);

    @GET("WebService/GetCompetencyGroupSilabus")
    Call<List<CompetencyGroup>> getCompetencyGroupSilabus(@Query("username") String username, @Query("competencyBookId") String competencyBookId);

    @GET("WebService/GetCompetencyRegisterSilabus")
    Call<List<CompetencyRegister>> getCompetencyRegisterSilabus(@Query("username") String username, @Query("competencyGroupId") String competencyGroupId);

    @GET("WebService/GetStudentSilabus")
    Call<List<Silabus>> getStudentSilabus(@Query("username") String username, @Query("competencyRegisterId") String competencyRegisterId, @Query("flag") String flag);
}
