package com.example.lenovo.afinally.FinalService.FinalDAO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 4/27/2017.
 */

public class CheckLogin {
    @SerializedName("status")
    public boolean status;
    @SerializedName("UsId")
    public int UsId;
    @SerializedName("UsName")
    public String UsName;
}
