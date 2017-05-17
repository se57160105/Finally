package com.example.lenovo.afinally.FinalService;

import com.example.lenovo.afinally.FinalService.FinalDAO.CheckLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ASUS on 4/25/2017.
 */

public interface FinalService {

    @FormUrlEncoded
    @POST("/TSP57/SEMs/index.php/UMS/Authen_service/checklogin")
    Call<CheckLogin> checklogin(@Field("username") String username, @Field("password") String password);

}
