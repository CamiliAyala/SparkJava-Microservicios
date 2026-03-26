/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.unsl.spark.ejercicio5;
import static spark.Spark.post;
import static spark.Spark.get;
import util.Path;
import com.unsl.spark.ejerc5.RestauranteControlador;
/**
 *
 * @author Camil
 */
public class Principal {

    public static void main(String[] args) {
        get(Path.Web.GRAL, RestauranteControlador.login);
        post(Path.Web.PROCESAR, RestauranteControlador.procesarLogin);
        get(Path.Web.INICIO, RestauranteControlador.inicio);
        get(Path.Web.INFORESTAURANTE, RestauranteControlador.datosRestaurante);
        get(Path.Web.PLATOSRESTAURANTE, RestauranteControlador.platosRestaurante);
        get(Path.Web.INFOPEDIDOS, RestauranteControlador.pedidosRestaurante);
        post(Path.Web.ACEPTARPEDIDO, RestauranteControlador.AceptaPedido);
        post(Path.Web.RECHAZARPEDIDO, RestauranteControlador.rechazaPedido);
    }
}
