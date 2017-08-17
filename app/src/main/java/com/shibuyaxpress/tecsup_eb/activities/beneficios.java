package com.shibuyaxpress.tecsup_eb.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.fragments.BeneficioTabFragment;


public class beneficios extends AppCompatActivity
        /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    FragmentManager mFragmentManager;
    android.support.v4.app.FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_beneficios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //test de tabs
        //test de tabs
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView_beneficio,new BeneficioTabFragment()).commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
        }
    }

