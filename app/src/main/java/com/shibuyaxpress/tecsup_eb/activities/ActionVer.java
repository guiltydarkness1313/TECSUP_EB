package com.shibuyaxpress.tecsup_eb.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.Entradas;
import com.shibuyaxpress.tecsup_eb.clases.EventosModelo;


import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActionVer extends AppCompatActivity  {

    private DatabaseReference databaseReference;
    private TextView titulo;
    private ImageView imagen;
    private TextView descripcion;
    private TextView direccion;
    private TextView fechaInicio, fechaFin;
    int id=1;
    private Button btnParticipar,btnCancelar;
    private int idEvento;
    private FirebaseUser usuario;
    public String nombreEvento,direccionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_ver);
        final String valorClave=getIntent().getExtras().getString("llave");

        titulo=(TextView)findViewById(R.id.txtTitulo);
        imagen=(ImageView)findViewById(R.id.imgEvento);
        descripcion=(TextView)findViewById(R.id.txtDescripcion);
        direccion=(TextView)findViewById(R.id.txt_direccion);
        fechaInicio=(TextView)findViewById(R.id.txt_fecha_inicio_evento);
        fechaFin=(TextView)findViewById(R.id.txt_fecha_fin);
        btnParticipar=(Button)findViewById(R.id.btn_participar);
        btnCancelar=(Button)findViewById(R.id.btn_cancelar);
        //obtener al usuario actual
        usuario=FirebaseAuth.getInstance().getCurrentUser();
        //boton cancelar no visible
        btnCancelar.setVisibility(View.INVISIBLE);

        databaseReference= FirebaseDatabase.getInstance().getReference("eventos");

        databaseReference.orderByChild("nombre").equalTo(valorClave).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //de aca se obtienen los datos de Firebase
                EventosModelo evento=dataSnapshot.getValue(EventosModelo.class);
                //Obtener el child key en el cual se encuentra un valor

                //seteo de datos
                titulo.setText(evento.getNombre());
                Glide.with(getApplicationContext()).load(evento.getPhotoUrl()).into(imagen);
                descripcion.setText(evento.getDescripcion());
                direccion.setText(evento.getDireccion());
                nombreEvento=evento.getNombre();
                direccionEvento=evento.getDireccion();
                fechaInicio.setText(evento.getFechaInicio());
                fechaFin.setText(evento.getFechaFin());
                //intento de validacion para ver si esta registrado en el evento y evitar que se registre varias veces

                final DatabaseReference referencia=FirebaseDatabase.getInstance().getReference("Entradas");
                final FirebaseUser usuarioFire=FirebaseAuth.getInstance().getCurrentUser();

                Query buscarQR=referencia.orderByChild("id_entrada").equalTo(valorClave+"-"+usuarioFire.getEmail());

                buscarQR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {

                                    btnParticipar.setEnabled(false);
                                    btnParticipar.setText("Registrado");
                                //función del boton cancelar una vez que el se haya registrado al
                                //evento o se encuentre en este
                                btnCancelar.setVisibility(View.VISIBLE);
                                btnCancelar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        final DatabaseReference refer=FirebaseDatabase.getInstance().getReference("Entradas");
                                        Query buscaQR=refer.orderByChild("id_entrada").equalTo(valorClave+"-"+usuarioFire.getEmail());
                                        buscaQR.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                //String nodoDato=dataSnapshot.getKey();
                                                //Entradas eFake=new Entradas();
                                                dataSnapshot.getRef().setValue(null);
                                                finish();
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

                            } else {
                                //Toast.makeText(getApplicationContext(), "no existe el evento", Toast.LENGTH_SHORT).show();
                                btnParticipar.setEnabled(true);
                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Toast.makeText(getApplicationContext(), "No existe el registro", Toast.LENGTH_SHORT).show();
                    }
                });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
    }

    public void CrearQR(View view){
        //creacion del objeto bitmaps con forma de QR con el nombre de archivo
        Bitmap bits=QRCode.from(nombreEvento+usuario.getEmail()).bitmap();
        AlmacenarImagen(bits,nombreEvento);
        enviarNotificacion();
        finish();



    }

    //metodo para almacenar imagenes
    private void AlmacenarImagen(Bitmap imagen,String nombreEvento){
        String root= Environment.getExternalStorageDirectory().toString();
        File miDir=new File(root+"/entradas_QR");
        //crear direccion
        miDir.mkdirs();
        //creación de variable para dar nombre aleatorio
        Random r=new Random();
        int n=10000;
        n=r.nextInt(n);
        //creación del nombre de la entrada
        String filename="Entrada-"+nombreEvento+"-"+usuario.getEmail()+".jpg";
        File archivo=new File(miDir,filename);
    if(archivo.exists()){
            archivo.delete();
        }
        try {
            //creacion de archivos QR en la memoria
            FileOutputStream salida=new FileOutputStream(archivo);
            imagen.compress(Bitmap.CompressFormat.JPEG,90,salida);
            //Firebase Storage inicializacion
            StorageReference almacen=FirebaseStorage.getInstance().getReferenceFromUrl("gs://tecsup-eb.appspot.com/registro_entrada");
            SubirArchivo(almacen,imagen);

            salida.flush();
            salida.close();
        }catch (Exception e){
            Log.d("error de subida","se intento guardar una imagen");
        }
    }

    //enviar datos a Storage y a la base de datos
    private void SubirArchivo(StorageReference almacen,Bitmap imagen) {
        SweetAlertDialog dialog=new SweetAlertDialog(getApplicationContext(),SweetAlertDialog.PROGRESS_TYPE);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[]data=baos.toByteArray();
        UploadTask uploadTask=almacen.child(nombreEvento+"-"+usuario.getEmail()).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //manejo de error de subida
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                /*dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Registrandose al evento");
                dialog.setCancelable(false);
                dialog.show();*/
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //manejar evento despues de haber subido la entrada QR
                //dialog.dismissWithAnimation();
                String enlaceImagen=String.valueOf(taskSnapshot.getDownloadUrl());
                DatabaseReference referenciaEntradas=FirebaseDatabase.getInstance().getReference();
                Entradas e1=new Entradas();
                e1.setImagenQR(enlaceImagen);
                e1.setPropietario(usuario.getEmail());
                e1.setNombreEvento(nombreEvento);
                e1.setUbicacion(direccionEvento);
                e1.setId_entrada(nombreEvento+"-"+usuario.getEmail());
                referenciaEntradas.child("Entradas").push().setValue(e1);
                Toast.makeText(getApplicationContext(),"registro satisfactorio",Toast.LENGTH_SHORT).show();
            }
        });
    }


         //creacion de la notificacion
    private void enviarNotificacion(){
        NotificationCompat.Builder notice= new NotificationCompat.Builder(this);

        notice.setSmallIcon(R.mipmap.ic_launcher);
        notice.setContentTitle("Se te ha regisrado en un evento");
        notice.setContentText("no olvides ver tu entrada QR en el menu de entradas :)");
        notice.setPriority(Notification.PRIORITY_MAX);
        notice.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        notice.setDefaults(Notification.DEFAULT_SOUND);


        //obtener una isntancia del notificationManager



        PendingIntent pendingIntent=PendingIntent.getActivity(this,0, new Intent(this,EntradasQR.class),PendingIntent.FLAG_UPDATE_CURRENT);

        notice.setContentIntent(pendingIntent);

        notice.setAutoCancel(true);
        //obtener una instancia del servicio del notificationManager
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(id,notice.build());
        //When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification with this new information,
        // rather than immediately creating a new notification. If you want to update this notification
        // at a later date, you need to assign it an ID. You can then use this ID whenever
        // you issue a subsequent notification. If the previous notification is still visible,
        // the system will update this existing notification, rather than create a new one.
        // In this example, the notification’s ID is 001//


        id++;
    }


}
