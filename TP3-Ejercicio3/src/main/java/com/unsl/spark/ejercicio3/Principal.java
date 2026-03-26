/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.unsl.spark.ejercicio3;

import com.unsl.spark.ejerc3.ActorControlador;
import static spark.Spark.get;
import static spark.Spark.post;
import util.Path;
import com.unsl.spark.ejerc3.PeliculaControlador;
import com.unsl.spark.ejerc3.RegistroControlador;

/**
 *
 * @author Camil
 */
public class Principal {

    public static void main(String[] args) {
        get(Path.Web.GRAL, PeliculaControlador.inicio);
        get(Path.Web.PELICULASPORDIRECTOR, PeliculaControlador.getPeliculasDirectorX);
        get(Path.Web.PELICULASPORACTOR, ActorControlador.getPeliculasActorX);
        get(Path.Web.NACIONALIDADACTOR, ActorControlador.getNacionalidadActorPorDirectorX);
        post(Path.Web.AGREGARVISUALIZACION, RegistroControlador.registrarPelicula);
        get(Path.Web.RECOMENDACIONPORNACION, RegistroControlador.getRecomendacionPeliculasPorNacionMasVista);
        get(Path.Web.RECOMENDACIONPORDIRECTOR, RegistroControlador.getRecomendacionPeliculasPorDirectorMasVista);

    }
}
