package com.example.lenovo.afinally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lenovo on 9/5/2560.
 */

public class Menu extends AppCompatActivity {
    private Button BtnCheckRub,BtnCheckJay,BtnRpCheckRub,BtnRpCheckJay,BtnRpPisud,BtnPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BtnCheckRub = (Button)findViewById(R.id.BtnCheckRub);
        BtnCheckJay = (Button)findViewById(R.id.BtnCheckJay);
        BtnRpCheckRub = (Button)findViewById(R.id.BtnRpCheckRub);
        BtnRpCheckJay = (Button)findViewById(R.id.BtnRpCheckJay);
        BtnRpPisud = (Button)findViewById(R.id.BtnRpPisud);
        BtnPower = (Button)findViewById(R.id.BtnPower);

        BtnCheckRub.setOnClickListener(OnClickSubmitCheckRub);
        BtnCheckJay.setOnClickListener(OnClickSubmitCheckJay);
        BtnRpCheckRub.setOnClickListener(OnClickSubmitRpCheckRub);
        BtnRpCheckJay.setOnClickListener(OnClickSubmitRpCheckJay);
        BtnRpPisud.setOnClickListener(OnClickSubmitRpPisud);
        BtnPower.setOnClickListener(OnClickSubmitPow);
    }
    private View.OnClickListener OnClickSubmitCheckRub = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Cincome.class);
            startActivity(in);
        }
    };
    private View.OnClickListener OnClickSubmitCheckJay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Cpay.class);
            startActivity(in);
        }
    };
    private View.OnClickListener OnClickSubmitRpCheckRub = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Income.class);
            startActivity(in);
        }
    };
    private View.OnClickListener OnClickSubmitRpCheckJay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Pay.class);
            startActivity(in);
        }
    };
    private View.OnClickListener OnClickSubmitRpPisud = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Pay.class);
            startActivity(in);
        }
    };
    private View.OnClickListener OnClickSubmitPow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Login.class);
            startActivity(in);
        }
    };
}
