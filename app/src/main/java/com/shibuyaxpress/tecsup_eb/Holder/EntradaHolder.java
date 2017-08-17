package com.shibuyaxpress.tecsup_eb.Holder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.tecsup_eb.R;

/**
 * Created by guiltydarkness1313 on 7/11/17.
 */

public class EntradaHolder extends RecyclerView.ViewHolder {

    private static final String TAG= EntradaHolder.class.getSimpleName();

    public TextView nombreEntrada;
    public TextView fechaEntrada;
    public ImageView imagenQR;
    public TextView ubicacionEntrada;
    public CardView modeloEntrada;


    public EntradaHolder(View itemView) {
        super(itemView);
        nombreEntrada= (TextView) itemView.findViewById(R.id.nombre_entrada);
        fechaEntrada= (TextView) itemView.findViewById(R.id.fecha_entrada);
        imagenQR= (ImageView) itemView.findViewById(R.id.photo_entrada);
        ubicacionEntrada= (TextView) itemView.findViewById(R.id.ubicacion_entrada);
        modeloEntrada= (CardView) itemView.findViewById(R.id.carta_entrada_view);


        modeloEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.makeText(v.getContext(),nombreEntrada.getText(),Toast.LENGTH_SHORT).show();
                MostrarImagen(v);

            }
        });

        modeloEntrada.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final CharSequence[] items = {"Ver QR", "Cancelar Registro", "Salir"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Opciones");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                MostrarImagen(v);
                                break;
                            case 1:
                                AlertDialog.Builder constructor=new AlertDialog.Builder(v.getContext());
                                constructor.setMessage("¿Esta seguro de eliminar su entrada?");
                                constructor.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Entradas");
                                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                                        Query query=reference.orderByChild("id_entrada").
                                                equalTo(nombreEntrada.getText()+"-"+
                                                user.getEmail());
                                        query.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                dataSnapshot.getRef().setValue(null);
                                                Toast.makeText(v.getContext(),"exito",Toast.LENGTH_SHORT).show();
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
                                });
                                constructor.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                constructor.show();
                                break;
                            case 2:
                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public void MostrarImagen(View v){
        final Dialog nagDialog = new Dialog(v.getContext(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.layout_entrada_imagen);
        Button btnClose = (Button) nagDialog.findViewById(R.id.btnIvClose);
        ImageView ivPreview = (ImageView) nagDialog.findViewById(R.id.iv_preview_image);
        ivPreview.setImageDrawable(imagenQR.getDrawable());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                nagDialog.dismiss();
            }
        });
        nagDialog.show();
    }
}
//realizar codigo si es que se realiza alguna accion para las entradas por ahora solo se ve las entradas