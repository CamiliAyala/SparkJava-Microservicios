/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.unsl.spark.ejercicio5;

import com.unsl.spark.ejerc5.RepartidorControlador;
import util.Path;
import static spark.Spark.post;
import static spark.Spark.get;
/**
 *
 * @author Camil
 */
public class Principal {

    public static void main(String[] args) {
        get(Path.Web.GRAL, RepartidorControlador.login);
        post(Path.Web.LOGIN, RepartidorControlador.procesarLogin);
        get(Path.Web.INICIO, RepartidorControlador.inicio);
        get(Path.Web.PEDIDOSVIGENTES, RepartidorControlador.pedidosVigentes);
        get(Path.Web.PEDIDOSACEPTADOS, RepartidorControlador.pedidosEnCamino);
        post(Path.Web.ACEPTARPEDIDO, RepartidorControlador.tomarPedido);
        post(Path.Web.RECHAZARPEDIDO, RepartidorControlador.rechazarPedido);
        post(Path.Web.PEDIDOENTREGADO, RepartidorControlador.pedidoEntregado);
        get(Path.Web.PEDIDOSENTREGADOS, RepartidorControlador.pedidosEntregados);
    }
}
