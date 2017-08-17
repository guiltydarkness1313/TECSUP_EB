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
import com.shibuyaxpress.tecsup_eb.Adapters.BeneficiosAdaptador;
import com.shibuyaxpress.tecsup_eb.Holder.BeneficioHolder;
import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.BeneficiosModelo;

/**
 * Created by paulf on 4/28/2017.
 */

public class FirstFragmentBeneficio extends Fragment {
    //creacion de los list
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayoutManager;
    //adaptador de eventos
    private BeneficiosAdaptador beneficiosAdapter;
    private DatabaseReference BDreference;
    private DatabaseReference refChild;
    public FirstFragmentBeneficio(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.first_fragment_layout,container,false);

        linearLayoutManager=new LinearLayoutManager(getActivity());

        reciclador= (RecyclerView) view.findViewById(R.id.recicladorVista_beneficio);
        reciclador.setHasFixedSize(true);

        BDreference= FirebaseDatabase.getInstance().getReference("beneficios");

        //refChild=BDreference.child("beneficios");
        Query buscar = BDreference.orderByChild("CategoriaBeneficio").equalTo("Educacion");

        beneficiosAdapter=new BeneficiosAdaptador(BeneficiosModelo.class,R.layout.cardbeneficios,BeneficioHolder.class,buscar,getContext());

        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setAdapter(beneficiosAdapter);

       // referenciaDB.goOffline();
        return view;
    }

}
