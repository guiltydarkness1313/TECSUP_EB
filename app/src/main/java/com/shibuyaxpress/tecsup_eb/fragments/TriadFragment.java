package com.shibuyaxpress.tecsup_eb.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Adapters.EventosAdaptador;
import com.shibuyaxpress.tecsup_eb.Holder.EventoHolder;
import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.EventosModelo;


/**
 * Created by paulf on 5/4/2017.
 */

public class TriadFragment extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayoutManager;
    //private GridLayoutManager gridLayoutManager;
    //adaptador de eventos
    private EventosAdaptador eventosAdapter;
    private DatabaseReference referenciaDB;
    //constructor vacio

    public TriadFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater. inflate(R.layout.triad_layout,container,false);

        linearLayoutManager=new LinearLayoutManager(getActivity());
        //gridLayoutManager=new GridLayoutManager(getActivity(),4);


        reciclador= (RecyclerView) view.findViewById(R.id.reciclador_3);
        reciclador.setHasFixedSize(true);
        //se llama a la base de datos filtrando el tipo de dato a buscar
        referenciaDB= FirebaseDatabase.getInstance().getReference("eventos");
        Query buscar=referenciaDB.orderByChild("categoria").equalTo("Integracion");

        eventosAdapter=new EventosAdaptador(EventosModelo.class,R.layout.cardv,EventoHolder.class,buscar,getContext());

        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setAdapter(eventosAdapter);

        return view;
    }
}
