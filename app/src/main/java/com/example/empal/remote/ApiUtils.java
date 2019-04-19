package com.example.empal.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.1.13/Empal/";
//    public static final String BASE_URL = "https://bolen.tiaravib.com/empal/WebService/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create((UserService.class));
    }
}
