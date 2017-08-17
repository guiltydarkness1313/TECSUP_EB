package com.shibuyaxpress.tecsup_eb.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.fragments.EntradaFragment;


public class EntradasQR extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        EntradaFragment fragment=new EntradaFragment();
        fragmentTransaction.add(R.id.content_frame_entradas,fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
        return true;
    }
}
