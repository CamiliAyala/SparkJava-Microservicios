/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import lombok.Getter;

/**
 *
 * @author Camil
 */
public class Path {
    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {
        @Getter public static final String GRAL = "/api/inicio";
        @Getter public static final String PELICULASPORDIRECTOR = "/api/pelicula/dirigidasPor";
        @Getter public static final String PELICULASPORACTOR = "/api/pelicula/actuadasPor";
        @Getter public static final String NACIONALIDADACTOR = "/api/actor/nacionalidad/PeliculasDirigidasPor";
        @Getter public static final String AGREGARVISUALIZACION = "/api/pelicula/regristroVisualizacion";
        @Getter public static final String RECOMENDACIONPORNACION = "/api/pelicula/recomendacionPorNacionMasVisualizada";
        @Getter public static final String RECOMENDACIONPORDIRECTOR = "/api/pelicula/recomendacionPorDirectorMasVisualizado";

    }
    
    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
    }

}