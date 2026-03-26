/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.unsl.spark.ejercicio4;

import static spark.Spark.get;
import static spark.Spark.post;
import util.Path;
import com.unsl.spark.ejerc4.ViajeControlador;
/**
 *
 * @author Camil
 */
public class Principal {

    public static void main(String[] args) {
        get(Path.Web.GRAL, ViajeControlador.inicio);
        get(Path.Web.BUSCARVIAJES, ViajeControlador.buscarViaje);
    }
}
