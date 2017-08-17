package com.shibuyaxpress.tecsup_eb.clases;

/**
 * Created by guiltydarkness1313 on 7/11/17.
 */

public class Entradas {
    private String nombreEvento;
    private String fecha;
    private String propietario;
    private String imagenQR;
    private String ubicacion;
    private String id_entrada;

    public Entradas() {
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagenQR() {
        return imagenQR;
    }

    public void setImagenQR(String imagenQR) {
        this.imagenQR = imagenQR;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(String id_entrada) {
        this.id_entrada = id_entrada;
    }
}
