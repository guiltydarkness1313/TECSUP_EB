package com.shibuyaxpress.tecsup_eb.Adapters;

/**
 * Created by paulf on 4/24/2017.
 */
import android.content.Context;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Holder.EventoHolder;
import com.shibuyaxpress.tecsup_eb.clases.EventosModelo;


public class EventosAdaptador extends FirebaseRecyclerAdapter<EventosModelo,EventoHolder> {
    private static final String TAG=EventosAdaptador.class.getSimpleName();
    private Context context;

    public EventosAdaptador(Class<EventosModelo>modelClass, int modelLayout, Class<EventoHolder> viewHolderClass, Query buscar, Context context){
        super(modelClass,modelLayout,viewHolderClass,buscar);
        this.context=context;
    }


    @Override
    protected void populateViewHolder(EventoHolder viewHolder, EventosModelo model, final int position){
        viewHolder.nombre.setText(model.getNombre());
        viewHolder.descripcion.setText(model.getDescripcion());
        Glide.with(context).load(model.getPhotoUrl()).into(viewHolder.photoUrl);
    }
}
