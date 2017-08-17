package com.shibuyaxpress.tecsup_eb.Adapters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Holder.BeneficioHolder;
import com.shibuyaxpress.tecsup_eb.clases.BeneficiosModelo;

/**
 * Created by paulf on 4/28/2017.
 */

public class BeneficiosAdaptador extends FirebaseRecyclerAdapter<BeneficiosModelo,BeneficioHolder> {
    private static final String TAG=BeneficiosAdaptador.class.getSimpleName();
    private Context context;
    public BeneficiosAdaptador(Class<BeneficiosModelo>modelClass, int modelLayout, Class<BeneficioHolder> viewHolderClass, Query buscar, Context context){
        super(modelClass,modelLayout,viewHolderClass,buscar);
        this.context=context;
    }
    @Override
    protected void populateViewHolder(BeneficioHolder viewHolder, BeneficiosModelo model, int position) {
        viewHolder.entidad.setText(model.getEntidad());
        viewHolder.descripcion.setText(model.getDescripcion());
        Glide.with(context).load(model.getImagen()).into(viewHolder.photoUrl);
    }
}
