package com.example.lenovo.afinally.OPPMSService;

import com.example.lenovo.afinally.OPPMSService.OPPMSDAO.OPPMSDAO;
import com.example.lenovo.afinally.OPPMSService.OPPMSDAO.SendQuick;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 17/3/2560.
 */

public interface OPPMSService {

    @POST("final_service/graph_bar.php")
    Call<OPPMSDAO> getOPPMSData();
    @FormUrlEncoded
    @POST("index.php/OPPMS/service_android/send_quick")
    Call<SendQuick> sendData(@Field("id") String id, @Field("name") String name);

}
