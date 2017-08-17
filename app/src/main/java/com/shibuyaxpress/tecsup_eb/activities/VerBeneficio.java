package com.shibuyaxpress.tecsup_eb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.BeneficiosModelo;

public class VerBeneficio extends AppCompatActivity {

    private DatabaseReference dbReference;
    private TextView entidad_ver,descripcion_ver,fecha_expiracion,categoria;
    private ImageView imagen_ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_beneficio);
        final String valorClave=getIntent().getExtras().getString("llave");

        entidad_ver=(TextView)findViewById(R.id.entidad_txts);
        descripcion_ver=(TextView)findViewById(R.id.descripcion_txt);
        imagen_ver=(ImageView) findViewById(R.id.imagen_beneficio);
        fecha_expiracion=(TextView)findViewById(R.id.fecha_fin_beneficio);

        dbReference= FirebaseDatabase.getInstance().getReference("beneficios");

        //Toast.makeText(getApplicationContext(),valorClave,Toast.LENGTH_LONG).show();

       dbReference.orderByChild("Entidad").equalTo(valorClave).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BeneficiosModelo beneficio=dataSnapshot.getValue(BeneficiosModelo.class);
                entidad_ver.setText(beneficio.getEntidad());
                Glide.with(getApplicationContext()).load(beneficio.getImagen()).into(imagen_ver);
                descripcion_ver.setText(beneficio.getDescripcion());
                fecha_expiracion.setText(beneficio.getFechaFin());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
    }
}
