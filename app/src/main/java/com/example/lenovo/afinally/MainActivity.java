package com.example.lenovo.afinally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button cycle;
    private Button bar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cycle = (Button) findViewById(R.id.cycle);
        cycle.setOnClickListener(onClickSubmitListener);
        bar = (Button) findViewById(R.id.bar);
        bar.setOnClickListener(onClickSubmitListener2);
    }
    private View.OnClickListener onClickSubmitListener = new View.OnClickListener()
    {
        public void onClick(View v){
            Intent i = new Intent(getApplicationContext(),Income.class);
            startActivity(i);
        }
    };
    private View.OnClickListener  onClickSubmitListener2 = new View.OnClickListener()
    {
        public void onClick(View v){
            Intent l = new Intent(getApplicationContext(),Cpay.class);
            startActivity(l);
        }
    };
}
