/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

/**
 *
 * @author Camil
 */
public class Pedido {
    private int id;
    private int carrito_id;
    private String user_name;
    private String user_estado;
    private String restaurante_estado;
    private String repartidor_estado;
    private String repartidor_nombre;
    
    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(int carrito_id) {
        this.carrito_id = carrito_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_estado() {
        return user_estado;
    }

    public void setUser_estado(String user_estado) {
        this.user_estado = user_estado;
    }

    public String getRestaurante_estado() {
        return restaurante_estado;
    }

    public void setRestaurante_estado(String restaurante_estado) {
        this.restaurante_estado = restaurante_estado;
    }

    public String getRepartidor_estado() {
        return repartidor_estado;
    }

    public void setRepartidor_estado(String repartidor_estado) {
        this.repartidor_estado = repartidor_estado;
    }

    public String getRepartidor_nombre() {
        return repartidor_nombre;
    }

    public void setRepartidor_nombre(String repartidor_nombre) {
        this.repartidor_nombre = repartidor_nombre;
    }
    
}
/*
CREATE TABLE pedidos (
    id SERIAL  NOT NULL,
    carrito_id INT REFERENCES carrito(id),
    user_name VARCHAR(100) REFERENCES usuarios(user_name),
    user_estado VARCHAR(30),
   restaurante_estado VARCHAR(30),
   repartidor_estado VARCHAR(30),
    repartidor_nombre VARCHAR(100) REFERENCES repartidores(user_repartidor),
    PRIMARY KEY(id,carrito_id)
);
*/