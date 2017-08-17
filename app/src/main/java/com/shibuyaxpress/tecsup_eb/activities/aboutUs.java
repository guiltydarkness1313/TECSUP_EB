package com.shibuyaxpress.tecsup_eb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shibuyaxpress.tecsup_eb.R;


/**
 * Created by paulf on 7/21/2017.
 */

public class aboutUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.about_us);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
    }
}
