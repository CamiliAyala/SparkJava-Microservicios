/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

import static com.unsl.spark.ejerc3.PeliculaControlador.registraLog;
import java.util.ArrayList;
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
public class RegistroControlador {
    final static Logger registraLog = LoggerFactory.getLogger(RegistroControlador.class);
    
    public static Route registrarPelicula = (Request request, Response response) -> {
        RegistroDAO rDAO = new RegistroDAO();
        List<Usuario> usuarios;
        List<Pelicula> peliculas;
        UsuarioDAO uDAO = new UsuarioDAO();
        PeliculaDAO pDAO = new PeliculaDAO();
        usuarios = uDAO.allUsuarios();
        peliculas = pDAO.allPeliculas();
        
        String usuario = request.queryParams("usuario");
        String pelicula = request.queryParams("pelicula");
        
        boolean res1 = rDAO.registrarVista(usuario, pelicula);
        registraLog.info("registarPelicula RES {}", res1); 
        
        List<Pelicula> res;
        res = rDAO.visualizacionesDelUsuario(usuario);
        
        HashMap model = new HashMap<>();
        model.put("accion", "PELICULAS VISUALIZADAS POR user = " + usuario);
        model.put("numero", 4);
        model.put("lista", res);
        model.put("usuarios", usuarios);
        model.put("peliculas", peliculas);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route getRecomendacionPeliculasPorNacionMasVista = (Request request, Response response) -> {
        List<Pelicula> res;
        List<Usuario> usuarios;
        RegistroDAO rDAO = new RegistroDAO();
        UsuarioDAO uDAO = new UsuarioDAO();
        String usuario = request.queryParams("user");
        res = rDAO.getRecomendacionPorNacion(usuario);
        registraLog.info("getRecomendacionPorNacion RES {}", res); 
        usuarios = uDAO.allUsuarios();
        
        HashMap model = new HashMap<>();
        model.put("accion", "PELICULAS RECOMENDADAS POR NACION");
        model.put("lista", res);
        model.put("numero", 4);
        model.put("usuarios", usuarios);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route getRecomendacionPeliculasPorDirectorMasVista = (Request request, Response response) -> {
        List<Pelicula> res;
        RegistroDAO rDAO = new RegistroDAO();
        String usuario = request.queryParams("user");
        res = rDAO.getRecomendacionPorDirector(usuario);
        registraLog.info("getRecomendacionPorNacion RES {}", res); 
        
        HashMap model = new HashMap<>();
        model.put("accion", "PELICULAS RECOMENDADAS POR DIRECTOR");
        model.put("lista", res);
        model.put("numero", 4);
        model.put("template", "templates/ejercicio3.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
}
