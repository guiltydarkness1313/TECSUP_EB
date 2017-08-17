package com.shibuyaxpress.tecsup_eb.Holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.activities.VerBeneficio;


/**
 * Created by paulf on 4/28/2017.
 */

public class BeneficioHolder extends RecyclerView.ViewHolder {
    private static final String TAG = BeneficioHolder.class.getSimpleName();
    public TextView entidad;
    public TextView descripcion;
    public ImageView photoUrl;
    Button verBeneficios;
    public BeneficioHolder(View itemView) {
        super(itemView);
        entidad = (TextView) itemView.findViewById(R.id.entidadtxt);
        photoUrl = (ImageView) itemView.findViewById(R.id.imagen_beneficio);
        descripcion = (TextView) itemView.findViewById(R.id.descripcion_beneficio);
        verBeneficios= (Button) itemView.findViewById(R.id.ver_beneficio);

        verBeneficios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recien agregado para ver si salen las posiciones de cada recycler view

                int position=getAdapterPosition();
                String key=entidad.getText().toString();
                //Toast.makeText(itemView.getContext(),key,Toast.LENGTH_SHORT).show();
                Intent launcher=new Intent(v.getContext(),VerBeneficio.class);

                launcher.putExtra("llave",key);

                v.getContext().startActivity(launcher);
                //Bundle pack=new Bundle();
                //Toast.makeText(v.getContext(),String.valueOf(elements),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
