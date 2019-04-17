package com.lijo.signaturelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.link_login);
        btn_login.setOnClickListener(this);

        btn_register = (Button) findViewById(R.id.link_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.link_register:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }
    }
}
