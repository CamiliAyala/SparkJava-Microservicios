/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

/**
 *
 * @author Camil
 */
public class ItemsCarrito {
    private int id;
    private int carrito_id;
    private int menu_id;
    private int cantidad;
    private float precio_unitario;

    public ItemsCarrito() {
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

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public float getPrecio_unitario() {
        return precio_unitario;
    }
    
    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    
    
}
/*CREATE TABLE carrito_items (
    id SERIAL PRIMARY KEY,
    carrito_id INT REFERENCES carrito(id),
    menu_id INT REFERENCES menu_restaurante(id),
    cantidad INT,
    precio_unitario DECIMAL(10,2) -- se guarda para mantener el precio original al momento del pedido
);*/