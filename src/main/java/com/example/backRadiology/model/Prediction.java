package com.example.backRadiology.model;

public class Prediction {
    
    private String imagen;
    private String nombre;
    private String porcentaje;

    public Prediction(String imagen, String nombre, String porcentaje) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Prediction() {
    }
    
    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getPorcentaje() {
        return porcentaje;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Prediction{" + "imagen=" + imagen + ", nombre=" + nombre + ", porcentaje=" + porcentaje + '}';
    }

    
}
