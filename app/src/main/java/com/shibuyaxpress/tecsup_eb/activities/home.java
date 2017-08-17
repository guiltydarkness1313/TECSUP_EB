package com.shibuyaxpress.tecsup_eb.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.shibuyaxpress.tecsup_eb.R;
import com.shibuyaxpress.tecsup_eb.clases.Usuarios;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String imagen_perfil, nombre_perfil, email_perfil;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    GoogleApiClient client;

    CarouselView carousel;

    DatabaseReference reference;

    int []images={R.drawable.news_uno, R.drawable.news_dos,R.drawable.new_tres,R.drawable.carousel_5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu principal");
        setSupportActionBar(toolbar);


        //entrada del carousel
        carousel=(CarouselView)findViewById(R.id.carouselView);
        carousel.setPageCount(images.length);
        carousel.setImageListener(imageListener);



        try {
            //obtener data de la cuenta de google obtenida del activity anterior
            imagen_perfil = getIntent().getExtras().getString("imagen");
            nombre_perfil = getIntent().getExtras().getString("nombre");
            email_perfil = getIntent().getExtras().getString("email");
            //mostrar data de la cuenta
            //Toast.makeText(getApplicationContext(),imagen_perfil+" "+nombre_perfil+" "+email_perfil,Toast.LENGTH_LONG).show();
        }catch (Exception error){
            Log.d("ERROR DE INGRESO","no se recibieron datos del perfil");
        }

        //busqueda del usuario en la base de datos
        //inclucion de usuarios a la base de datos firebase
        reference= FirebaseDatabase.getInstance().getReference();
        final FirebaseUser fireUser=FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference referencia= FirebaseDatabase.getInstance().getReference("Usuarios");
        Query buscarUsuario=referencia.orderByChild("email").equalTo(fireUser.getEmail());
        //Toast
        //Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
        buscarUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Toast.makeText(getApplicationContext(),"Existe",Toast.LENGTH_SHORT).show();
                }else{
                    //Username Does Not Exist
                    //Creación de datos para subir a la base de datos
                    Usuarios user = new Usuarios();
                    user.setEmail(fireUser.getEmail());
                    user.setIdUsuario(fireUser.getUid());
                    //push es para crear childs unicos
                    reference.child("Usuarios").push().setValue(user);
                    //Toast.makeText(getApplicationContext(),"se agrego a"+fireUser.getEmail()+"a la BD",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //FIN DE INTENTO DE ANALISIS

        //DRAWERLAYOUT
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        try {

            CircleImageView img_profile = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imagen_perfil);
            TextView nombre = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombretxt);
            TextView correo = (TextView) navigationView.getHeaderView(0).findViewById(R.id.correotxt);
            nombre.setText(nombre_perfil);
            correo.setText(email_perfil);
            Glide.with(getApplicationContext()).load(imagen_perfil).into(img_profile);
        }catch (Exception error1){
            Log.d("Ingreso de Perfil","Error en poner contenido del perfil");
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };

    protected void onStart() {
        //auth.signOut();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        client.connect();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            SalirApp();
        }
        //CerrarSesion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent launcher=new Intent(this,aboutUs.class);
            startActivity(launcher);
            overridePendingTransition(R.anim.animex_first,R.anim.animex_second);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eventos) {
            // Handle the gallery action
            Intent launcher=new Intent(this,eventos.class);
            startActivityForResult(launcher,13);
            overridePendingTransition(R.anim.animex_first,R.anim.animex_second);
        }else if (id == R.id.nav_beneficios) {
            //ingresar el menu beneficios
            Intent launcher=new Intent(this,beneficios.class);
            startActivity(launcher);
            overridePendingTransition(R.anim.animex_first,R.anim.animex_second);
        }else if (id==R.id.nav_entradaQR){
            Intent launcher=new Intent(this,EntradasQR.class);
            startActivity(launcher);
            overridePendingTransition(R.anim.animex_first,R.anim.animex_second);
        } else if (id == R.id.nav_ayuda) {

        } else if (id == R.id.nav_salir) {
            CerrarSesion();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void CerrarSesion(){
        final AlertDialog.Builder windows=new AlertDialog.Builder(this);
        windows.setTitle("Cerrar Sesión");
        windows.setMessage("¿Seguro desea salir de la aplicación?");
        windows.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Auth.GoogleSignInApi.signOut(client).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        //..
                        //Toast.makeText(getApplicationContext(),"Log out",Toast.LENGTH_LONG).show();
                        Intent rocket=new Intent(getApplicationContext(),login.class);
                        startActivity(rocket);
                        finish();
                        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
                    }
                });
            }
        });
        windows.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //hacer nada

            }
        });

        windows.show();

    }

    protected void SalirApp(){
        AlertDialog.Builder bob=new AlertDialog.Builder(this);
        bob.setTitle("Tecsup EB");
        bob.setMessage("¿Desea salir de la aplicación?");
        bob.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        bob.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        bob.show();
    }



}
