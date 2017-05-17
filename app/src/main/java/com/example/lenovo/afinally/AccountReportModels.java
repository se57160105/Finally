package com.example.lenovo.afinally;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by vincent van kok on 5/5/2017.
 */
public class AccountReportModels {
    public void list() {
    }

    public static class Data {
        public int bacId;
        public int bacNum;
        public String bacNo;
        public String ZBANK;
        public String bacName;
        public int batId;
        public int baId;
        public String bacBranch;
        public float bacBalance;
        public float bacCurBalance;
        public int acId;
        public String bacActive;
    }

    class Data1{
        public float totals;
        public float total;
        public float bar_r;
        public float bar_p;
    }

    public static class SendQuick {
        public ArrayList<Data1> data;
    }



    public static class Response {
        public int error;
        public String errmsg;
        public List<Data> data;
    }



    public interface Service {
        @GET("index.php/account/reports/api_android_jnan/get_user_name")
        Call<Response> list();

        @FormUrlEncoded
        @POST("index.php/account/reports/api_android_jnan/get_check")
        Call<SendQuick> sendData(@Field("id") int id);
    }
}
