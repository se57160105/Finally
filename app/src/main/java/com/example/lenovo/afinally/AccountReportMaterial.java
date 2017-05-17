package com.example.lenovo.afinally;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountReportMaterial extends Fragment {
    private AccountReportModels.Service service;
    private List<AccountReportModels.Data> responseData;
    private MaterialBetterSpinner nameSpinner;
    private List<String> spinnerData = new ArrayList<>();
//    private ArrayAdapter<String> spinnerData = new ArrayAdapter<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("projectandroid","created");
        Toast.makeText(getActivity(),"Created",Toast.LENGTH_SHORT).show();
        bindata();

        return inflater.inflate(R.layout.fragment_accout_material, container, false);
    }

    private void bindata() {


        Log.d("projectandroid","bindata");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.51.4.17/TSP57/SMEs/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient()) // แก้ error certificate
                .build();

        service = retrofit.create(AccountReportModels.Service.class);

        service.list().enqueue(new Callback<AccountReportModels.Response>() {
            @Override
            public void onResponse(Call<AccountReportModels.Response> call, Response<AccountReportModels.Response> response) {
                Log.d("projectandroid", response.body().error+"");
                responseData = response.body().data;
                for(int i = 0 ; i < response.body().data.size(); i++) {
                    Log.d("projectandroid", response.body().data.get(i).bacName);
                    spinnerData.add(response.body().data.get(i).bacName);
                }

                addSpinner();
            }

            @Override
            public void onFailure(Call<AccountReportModels.Response> call, Throwable t) {
                Log.d("projectandroid","Failed");
                Log.d("projectandroid",t.getMessage());
            }
        });
    }

    private void addSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, spinnerData);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Log.d("promotion",adapter.toString());
      //  nameSpinner = (MaterialBetterSpinner ) getActivity().findViewById(R.id.name_material);
        nameSpinner.setAdapter(adapter);

//        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),
//                        "Select : " + responseData.get(position).bacName,
//                        Toast.LENGTH_SHORT).show();
//                // ทำต่อจากตรงนี้นะ คิคิ
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    // Code ข้างล่างนี้เป็น code สำหรับแก้ error certificate

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
