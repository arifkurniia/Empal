package com.mtsindonesia.empal.remote;

import com.mtsindonesia.empal.model.CompetencyBook;
import com.mtsindonesia.empal.model.CompetencyGroup;
import com.mtsindonesia.empal.model.CompetencyRegister;
import com.mtsindonesia.empal.model.ResObj;
import com.mtsindonesia.empal.model.Schedule;
import com.mtsindonesia.empal.model.Silabus;
import com.mtsindonesia.empal.model.Student;

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

    @GET("WebService/GetStudentSchedule")
    Call<List<Schedule>> getSchedule(@Query("username") String username);
}
