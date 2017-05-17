package com.example.lenovo.afinally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.afinally.FinalService.FinalDAO.CheckLogin;
import com.example.lenovo.afinally.FinalService.FinalService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 9/5/2560.
 */

public class Login extends AppCompatActivity {
    private Button Btnlogin;
    private EditText Username;
    private EditText Passsword;
    private FinalService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Btnlogin = (Button)findViewById(R.id.Btnlogin);
        Username = (EditText)findViewById(R.id.username);
        Passsword = (EditText)findViewById(R.id.password);
        String username = Username.getText().toString();
        String password = Passsword.getText().toString();
        Btnlogin.setOnClickListener(OnClickSubmitBtnlogin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.51.4.17")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FinalService.class);
        service.checklogin(username,password).enqueue(new Callback<CheckLogin>() {
            @Override
            public void onResponse(Call<CheckLogin> call, Response<CheckLogin> response) {
                Toast.makeText(getApplicationContext(), "Success"+response.body().UsName,
                        Toast.LENGTH_LONG).show();
                Log.d("LOGIN",response.message());
            }

            @Override
            public void onFailure(Call<CheckLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private View.OnClickListener OnClickSubmitBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Menu.class);
            startActivity(in);
        }
    };
}
