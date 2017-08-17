package com.shibuyaxpress.tecsup_eb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shibuyaxpress.tecsup_eb.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent launcher=new Intent(this,login.class);
        startActivity(launcher);

        finish();
    }

}
