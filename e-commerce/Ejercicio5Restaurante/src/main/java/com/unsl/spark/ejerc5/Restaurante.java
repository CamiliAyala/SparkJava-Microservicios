/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

/**
 *
 * @author Camil
 */
public class Restaurante {
    private String nombre;
    private String direccion;
    private String categoria;

    public Restaurante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
/*CREATE TABLE restaurantes (
    nombre VARCHAR(100) NOT NULL PRIMARY KEY,
    direccion VARCHAR(150),
    categoria VARCHAR(50) -- ej: pizza, sushi, hamburguesas, etc.
);*/