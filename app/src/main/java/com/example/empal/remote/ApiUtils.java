package com.example.empal.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.43.173/Empal/WebService/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create((UserService.class));
    }
}
