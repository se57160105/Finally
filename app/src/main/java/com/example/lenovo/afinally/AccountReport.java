package com.example.lenovo.afinally;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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


public class AccountReport extends Fragment {
    
    private AccountReportModels.Service service;
    private List<AccountReportModels.Data> responseData;
    private Spinner nameSpinner;
    private List<String> spinnerData = new ArrayList<>();
    private TextView balance;
    private TextView balance_r;
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

        return inflater.inflate(R.layout.fragment_account_report, container, false);
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
// ทำตรงนี้
    private void addSpinner() {
        nameSpinner = (Spinner) getActivity().findViewById(R.id.name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(adapter);

        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        "Select : " + responseData.get(position).bacId,
                        Toast.LENGTH_SHORT).show();
                // ทำต่อจากตรงนี้นะ คิคิ
                Log.d("ZXC","asd");
                service.sendData(responseData.get(position).bacId).enqueue(new Callback<AccountReportModels.SendQuick>() {
                    @Override
                    public void onResponse(Call<AccountReportModels.SendQuick> call, Response<AccountReportModels.SendQuick> response) {
//                        Toast.makeText(getActivity(),
//                        "Select : " + String.valueOf(response.body().data.get(0).bar_p),
//                        Toast.LENGTH_SHORT).show();
                        balance = (TextView) getActivity().findViewById(R.id.bal);
                        balance_r = (TextView) getActivity().findViewById(R.id.bal_r);
                        TextView balance_p = (TextView) getActivity().findViewById(R.id.bal_p);
                        TextView total_s = (TextView) getActivity().findViewById(R.id.total);
                        balance.setText("  ยอดคงเหลือตามใบแจ้งยอดธนาคาร      \n\n                                                            " + String.valueOf(response.body().data.get(0).totals)+ "  บาท");
                        balance_r.setText("  บวก เงินฝากระหว่างทาง                             \n\n                                                            " + String.valueOf(response.body().data.get(0).bar_r)+ "  บาท");
                        balance_p.setText("  หัก เช็คจ่ายที่ยังไม่มีผู้นำไปขึ้นเงิน                 \n\n                                                            " + String.valueOf(response.body().data.get(0).bar_p)+ "  บาท");
                        total_s.setText("  ยอดคงเหลือตามบัญชีเงินฝากธนาคาร    \n\n                                                            " + String.valueOf(response.body().data.get(0).total)+ "  บาท");

                    }

                    @Override
                    public void onFailure(Call<AccountReportModels.SendQuick> call, Throwable t) {
                        Toast.makeText(getActivity(),
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("ZXC","asd");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadData(int id) {
        Log.d("ZXC","ZXC");

        Log.d("ZXC","ZXC");
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
