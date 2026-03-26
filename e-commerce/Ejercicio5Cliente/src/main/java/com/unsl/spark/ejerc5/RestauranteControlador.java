/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

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
public class RestauranteControlador {
    final static Logger registraLog = LoggerFactory.getLogger(RestauranteControlador.class);
    
    public static Route showRestaurantes = (Request request, Response response) -> {
        List<Restaurante> restaurantes = new ArrayList<>();
        RestauranteDAO rDAO = new RestauranteDAO();
        restaurantes = rDAO.getAllRestaurantes();
        String user = request.session().attribute("user");
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "getRestaurantes");
        model.put("restaurantes", restaurantes);
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route showRestauranteDetails = (Request request, Response response) -> {
        String restauranteNombre = request.params(":nombre");
        RestauranteDAO restauranteDAO = new RestauranteDAO();
        List<Menu> platos = restauranteDAO.getMenuRestaurante(restauranteNombre);
        
        String user = request.session().attribute("user");
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "getMenu");
        model.put("menu", platos);
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };

}
