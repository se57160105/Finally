package com.example.lenovo.afinally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lenovo on 9/5/2560.
 */

public class Login extends AppCompatActivity {
    private Button Btnlogin;
    private EditText Username;
    private EditText Passsword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Btnlogin = (Button)findViewById(R.id.Btnlogin);
        Username = (EditText)findViewById(R.id.username);
        Passsword = (EditText)findViewById(R.id.password);
        Btnlogin.setOnClickListener(OnClickSubmitBtnlogin);
    }
    private View.OnClickListener OnClickSubmitBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),Menu.class);
            startActivity(in);
        }
    };
}
