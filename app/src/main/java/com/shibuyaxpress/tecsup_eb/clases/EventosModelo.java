package com.shibuyaxpress.tecsup_eb.clases;

/**
 * Created by paulf on 4/17/2017.
 */

public class EventosModelo {
    private String id_evento;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String photoUrl;
    private String direccion;
    private String descripcion;
    private String categoria;

    public EventosModelo() {
    }

    public EventosModelo(String id_evento, String nombre, String fechaInicio, String fechaFin, String photoUrl, String direccion, String descripcion, String categoria) {
        this.id_evento = id_evento;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.photoUrl = photoUrl;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }
}
