package com.shibuyaxpress.tecsup_eb.fragments;

/**
 * Created by paulf on 4/23/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Adapters.EventosAdaptador;
import com.shibuyaxpress.tecsup_eb.Holder.EventoHolder;
import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.EventosModelo;


public class PrimaryFragment extends Fragment {
    //creacion de los list
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayoutManager;
    //adaptador de eventos
    private EventosAdaptador eventosAdapter;
    private DatabaseReference referenciaDB;
    private DatabaseReference childRef;
    ProgressBar bar;
    public PrimaryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
    View view=inflater.inflate(R.layout.primary_layout,container,false);

        bar= (ProgressBar) view.findViewById(R.id.barra_educacion);
        bar.setVisibility(ProgressBar.VISIBLE);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        reciclador= (RecyclerView) view.findViewById(R.id.recicladorVista);
        reciclador.setHasFixedSize(true);

        referenciaDB=FirebaseDatabase.getInstance().getReference("eventos");
        Query buscar=referenciaDB.orderByChild("categoria").equalTo("Educacion");

       // childRef=referenciaDB.child("eventos");

        eventosAdapter=new EventosAdaptador(EventosModelo.class,R.layout.cardv,EventoHolder.class,buscar,getContext());
        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setAdapter(eventosAdapter);

        return view;
    }


}