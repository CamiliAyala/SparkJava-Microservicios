/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

/**
 *
 * @author Camil
 */
public class Repartidor {
    private String user_repartidor;
    private String password_repartidor;

    public Repartidor() {
    }

    public String getUser_repartidor() {
        return user_repartidor;
    }

    public void setUser_repartidor(String user_repartidor) {
        this.user_repartidor = user_repartidor;
    }

    public String getPassword_repartidor() {
        return password_repartidor;
    }

    public void setPassword_repartidor(String password_repartidor) {
        this.password_repartidor = password_repartidor;
    }
    
    
}
/*CREATE TABLE repartidores (
    user_repartidor VARCHAR(100) NOT NULL PRIMARY KEY UNIQUE,
   password_repartidor VARCHAR(100)
);*/