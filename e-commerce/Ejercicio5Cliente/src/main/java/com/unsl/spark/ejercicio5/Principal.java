/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.unsl.spark.ejercicio5;

import static spark.Spark.get;
import static spark.Spark.post;
import util.Path;
import com.unsl.spark.ejerc5.UsuarioControlador;
import com.unsl.spark.ejerc5.RestauranteControlador;
import com.unsl.spark.ejerc5.CarritoControlador;
import com.unsl.spark.ejerc5.PedidoControlador;
/**
 *
 * @author Camil
 */
public class Principal {

    public static void main(String[] args) {
        get(Path.Web.GRAL, UsuarioControlador.login);
        post(Path.Web.LOGIN, UsuarioControlador.procesarLogin);
        get(Path.Web.INICIO, UsuarioControlador.inicio);
        get(Path.Web.SHOWRESTAURANTES, RestauranteControlador.showRestaurantes);
        get(Path.Web.MENURESTAURANTE, RestauranteControlador.showRestauranteDetails);
        post(Path.Web.CARGARCARRITO, CarritoControlador.agregarAlCarrito);
        get(Path.Web.VERCARRITO, CarritoControlador.visualizarCarrito);
        post(Path.Web.CONFIRMACIONPEDIDO, PedidoControlador.confirmarPedido);
        get(Path.Web.DETALLESPEDIDO, PedidoControlador.detallesDelPedido);
    }
}
