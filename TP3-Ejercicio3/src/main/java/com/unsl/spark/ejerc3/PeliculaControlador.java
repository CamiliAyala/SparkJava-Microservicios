/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

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
public class PeliculaControlador {
    final static Logger registraLog = LoggerFactory.getLogger(PeliculaControlador.class);
    
    public static Route inicio = (Request request, Response response) -> {
        HashMap model = new HashMap<>();
        List<Director> directores;
        List<Actor> actores;
        List<Usuario> usuarios;
        List<Pelicula> peliculas;
        ActorDAO aDAO = new ActorDAO();
        DirectorDAO dDAO = new DirectorDAO();
        UsuarioDAO uDAO = new UsuarioDAO();
        PeliculaDAO pDAO = new PeliculaDAO();
        directores = dDAO.allDirectores();
        actores = aDAO.allActores();
        usuarios = uDAO.allUsuarios();
        peliculas = pDAO.allPeliculas();
        model.put("directores", directores);
        model.put("actores", actores);
        model.put("usuarios", usuarios);
        model.put("peliculas", peliculas);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route getPeliculasDirectorX = (Request request, Response response) -> {
        List<Pelicula> res;
        List<Director> lista;
        PeliculaDAO pDAO = new PeliculaDAO();
        DirectorDAO dDAO = new DirectorDAO();
        String director = request.queryParams("director");
        res = pDAO.getPeliculasDirectorX(director);
        registraLog.info("getPeliculasDirectorX RES {}", res); 
        lista = dDAO.allDirectores();
        
        HashMap model = new HashMap<>();
        model.put("accion", "PELICULAS DIRIGIDAS POR " + director);
        model.put("numero", 1);
        model.put("directores", lista);
        model.put("lista", res);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
}
