/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 *
 * @author Camil
 */
public class Filters {
    /* Agregar la barra al final de cada solicitud para evitar si es llamado como "path" solo o "path/" */
     public static Filter agregarBarraFinal = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };
}

