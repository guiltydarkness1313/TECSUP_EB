package com.shibuyaxpress.tecsup_eb.Adapters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Holder.EntradaHolder;
import com.shibuyaxpress.tecsup_eb.clases.Entradas;


/**
 * Created by guiltydarkness1313 on 7/11/17.
 */

public class EntradaAdaptador extends FirebaseRecyclerAdapter<Entradas,EntradaHolder> {

    private Context context;

    public EntradaAdaptador(Class<Entradas>modelClass, int modelLayout, Class<EntradaHolder>viewHolder, Query busqueda, Context context){
        super(modelClass,modelLayout,viewHolder,busqueda);
        this.context=context;
    }



    @Override
    protected void populateViewHolder(EntradaHolder viewHolder, Entradas model, int position) {
        viewHolder.nombreEntrada.setText(model.getNombreEvento());
        Glide.with(context).load(model.getImagenQR()).into(viewHolder.imagenQR);
        viewHolder.fechaEntrada.setText(model.getFecha());
        viewHolder.ubicacionEntrada.setText(model.getUbicacion());
        //seteo de valores del usuario filtrado segun los eventos a los cuales este esta registrado
    }
}
