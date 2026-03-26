/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

/**
 *
 * @author Camil
 */
public class Carrito {
    private int id;
    private String user_name;
    private String estado;

    public Carrito() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

/*CREATE TABLE carrito (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) REFERENCES usuarios(user_name),
    estado VARCHAR(20) DEFAULT 'activo' -- activo, pagado, cancelado, etc.
);*/