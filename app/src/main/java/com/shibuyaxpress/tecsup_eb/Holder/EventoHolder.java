package com.shibuyaxpress.tecsup_eb.Holder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.activities.ActionVer;

/**
 * Created by paulf on 4/24/2017.
 */

public class EventoHolder extends RecyclerView.ViewHolder {

    private static final String TAG = EventoHolder.class.getSimpleName();
    public TextView nombre;
    public TextView descripcion;
    public ImageView photoUrl;
    public Button btnLeer;

    public EventoHolder(final View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.titulo_carta);
        photoUrl = (ImageView) itemView.findViewById(R.id.card_image);
        descripcion = (TextView) itemView.findViewById(R.id.description);
        btnLeer= (Button) itemView.findViewById(R.id.buttonleer);
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recien agregado para ver si salen las posiciones de cada recycler view

                int position=getAdapterPosition();
                String key=nombre.getText().toString();
                //Toast.makeText(itemView.getContext(),key,Toast.LENGTH_SHORT).show();
                Intent launcher=new Intent(v.getContext(),ActionVer.class);
                Bundle pack=new Bundle();
                launcher.putExtra("llave",key);
                v.getContext().startActivity(launcher);
                //Bundle pack=new Bundle();
                //Toast.makeText(v.getContext(),String.valueOf(elements),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //public void overridePendingTransition(int enterAnim,int exitAnim){


}