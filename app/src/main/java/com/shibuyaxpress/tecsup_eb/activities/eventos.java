package com.shibuyaxpress.tecsup_eb.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.fragments.TabFragment;


public class eventos extends AppCompatActivity {

    FragmentManager mFragmentManager;
    android.support.v4.app.FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_eventos);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //test de tabs
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView_eventos,new TabFragment()).commit();



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
    }
}
