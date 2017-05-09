package com.example.lenovo.afinally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Income extends AppCompatActivity {
    RequestQueue requestQueue;
    String JSON_STRING;
    TextView Income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        new NukeSSLCerts().nuke();
        //Date = (TextView)rootView.findViewById(R.id.Date);
        Income = (TextView)findViewById(R.id.Income);

        //ID = (TextView)findViewById(R.id.code);
        //Issue = (TextView)findViewById(R.id.sub);
        requestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://10.51.4.17/TSP57/SMEs/index.php/account/reports/Api_android_sun/get_income_new",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("rs_bank");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Hds_request = jsonArray.getJSONObject(i);

                                String bacNum = Hds_request.getString("bacNum");
                                String bacName = Hds_request.getString("bacName");
                                String baName = Hds_request.getString("baName");
                                String bacBranch = Hds_request.getString("bacBranch");
                                String bacNo = Hds_request.getString("bacNo");
                                String baCode = Hds_request.getString("baCode");
                                String cNo = Hds_request.getString("cNo");
                                String cDate = Hds_request.getString("cDate");
                                String deta_code = Hds_request.getString("deta_code");
                                String cAmount = Hds_request.getString("cAmount");
                                //ar.add(rq_code);
                                //String age = student.getString("age");

                                Income.append(bacNum + " " +  bacName + "\n"
                                        + baName + " " + bacBranch + " \n" + bacNo +
                                        " " + baCode+"/"+cNo +"\n"
                                        + cDate +" " + deta_code +"\n" + cAmount+" THB\n"+"\n"
                                );

                                //textView.append(rq_subject + " " + rq_code + " " + " \n ");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY", "ERROR");
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private View.OnClickListener onClickSubmitListener = new View.OnClickListener()
    {
        public void onClick(View v){
            Intent i = new Intent(getApplicationContext(),Income.class);
            startActivity(i);
        }
    };
}

