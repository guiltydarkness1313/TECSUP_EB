package com.shibuyaxpress.tecsup_eb.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.Adapters.EntradaAdaptador;
import com.shibuyaxpress.tecsup_eb.Holder.EntradaHolder;
import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.Entradas;

/**
 * Created by paulf on 7/19/2017.
 */

public class EntradaFragment extends Fragment {

    private RecyclerView reciclador;
    private GridLayoutManager manager;
    private EntradaAdaptador entradaAdaptador;
    private DatabaseReference reference;

    public EntradaFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.entrada_fragment,container,false);

        manager=new GridLayoutManager(getActivity(),2);

        reciclador= (RecyclerView) view.findViewById(R.id.reciclador_entrada);
        reciclador.setHasFixedSize(true);

        reference= FirebaseDatabase.getInstance().getReference("Entradas");
        Query buscar=reference.orderByChild("propietario").equalTo("paul.frankpc@gmail.com");

        entradaAdaptador=new EntradaAdaptador(Entradas.class,R.layout.carta_entrada, EntradaHolder.class,buscar,getContext());

        reciclador.setLayoutManager(manager);
        reciclador.setAdapter(entradaAdaptador);

        return view;
    }
}
