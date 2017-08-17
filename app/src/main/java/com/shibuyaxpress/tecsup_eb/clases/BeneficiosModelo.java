package com.shibuyaxpress.tecsup_eb.clases;

/**
 * Created by paulf on 4/25/2017.
 */

public class BeneficiosModelo {
    private String CategoriaBeneficio;
    private String Descripcion;
    private String Entidad;
    private String FechaFin;
    private String Imagen;
    //private String id_beneficio;

    public BeneficiosModelo() {
    }


    public String getCategoriaBeneficio() {
        return CategoriaBeneficio;
    }

    public void setCategoriaBeneficio(String categoriaBeneficio) {
        CategoriaBeneficio = categoriaBeneficio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEntidad() {
        return Entidad;
    }

    public void setEntidad(String entidad) {
        Entidad = entidad;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
