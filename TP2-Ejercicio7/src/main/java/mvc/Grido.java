/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

/**
 *
 * @author Camil
 */
public class Grido {
    
    private String producto;
    private int puntos;

    public Grido() {
    }
    
    public Grido(String producto, int puntos) {
        this.producto = producto;
        this.puntos = puntos;
    }

    public String getProducto() {
        return producto;
    }

    public int getPuntos() {
        return puntos;
    }
    
}
