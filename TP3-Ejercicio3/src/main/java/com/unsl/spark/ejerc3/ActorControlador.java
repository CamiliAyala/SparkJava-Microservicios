/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

import static com.unsl.spark.ejerc3.PeliculaControlador.registraLog;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Camil
 */
public class ActorControlador {
    final static Logger registraLog = LoggerFactory.getLogger(ActorControlador.class);
    
    public static Route getPeliculasActorX = (Request request, Response response) -> {
        List<Pelicula> res;
        List<Actor> actores;
        ActorDAO aDAO = new ActorDAO();
        String actor = request.queryParams("actor");
        res = aDAO.getPeliculasActorX(actor);
        actores = aDAO.allActores();
        registraLog.info("getPeliculasDirectorX RES {}", res); 
        
        HashMap model = new HashMap<>();
        model.put("accion", "PELICULAS PROTAGONIZADAS POR " + actor);
        model.put("lista", res);
        model.put("numero", 2);
        model.put("actores", actores);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route getNacionalidadActorPorDirectorX = (Request request, Response response) -> {
        List<Actor> res;
        List<Director> directores;
        DirectorDAO dDAO = new DirectorDAO();
        directores = dDAO.allDirectores();
        ActorDAO aDAO = new ActorDAO();
        String director = request.queryParams("dir");
        res = aDAO.getNacionalidadActorPorDirectorX(director);
        registraLog.info("getNacionalidadActorPorDirectorX RES {}", res); 
        
        HashMap model = new HashMap<>();
        model.put("accion", "NACIONALIDAD DE ACTORES DE PELICULAS DIRIGIDAS POR " + director);
        model.put("lista", res);
        model.put("numero", 3);
        model.put("directores", directores);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
}
