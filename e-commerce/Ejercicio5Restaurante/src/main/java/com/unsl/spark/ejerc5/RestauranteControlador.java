/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    
    public static Route login = (Request request, Response response) -> {
        HashMap<String, Object> model = new HashMap<>();
        List<Restaurante> restaurantes;
        RestauranteDAO u = new RestauranteDAO();
        restaurantes = u.getAllRestaurantes();
        model.put("restaurantes", restaurantes);
        model.put("template", "templates/login.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };

    public static Route procesarLogin = (Request request, Response response) -> {
        // Obtener el restaurante seleccionado
        String nombreRestaurante = request.queryParams("usuario");

        if (nombreRestaurante != null && !nombreRestaurante.isEmpty()) {
            // Guardar el restaurante seleccionado en la sesión
            request.session().attribute("user", nombreRestaurante);

            // Redirigir al inicio
            response.redirect("/api/inicio");
        } else {
            // Si no seleccionaron restaurante, volver al login
            response.redirect("/api/login");
        }

        return null;
    };

    public static Route inicio = (Request request, Response response) -> {
        HashMap<String, Object> model = new HashMap<>();

        String user = request.session().attribute("user");
        if (user == null) {
            // Si no hay usuario en sesión, redirigir al login
            response.redirect("/api/login");
            return null;
        }

        model.put("accion", "inicio");
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl"); // tu template de inicio
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route datosRestaurante = (Request request, Response response) -> {
        RestauranteDAO rDAO = new RestauranteDAO();
        String user = request.session().attribute("user");
        Restaurante r = rDAO.getInfoRestaurante(user);
        int cant = rDAO.getCantPlatosPorRestaurante(user);
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "getDatosRestaurante");
        model.put("user", user);
        model.put("restaurante", r);
        model.put("cantidad", cant);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route platosRestaurante = (Request request, Response response) -> {
        RestauranteDAO rDAO = new RestauranteDAO();
        String user = request.session().attribute("user");
        Map<String, Float> res = rDAO.getPlatos(user);
        
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "getPlatosRestaurante");
        model.put("user", user);
        model.put("mapa", res);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route pedidosRestaurante = (Request request, Response response) -> {
        RestauranteDAO rDAO = new RestauranteDAO();
        String user = request.session().attribute("user");

        List<Map<String, Object>> pedidosPendientes = rDAO.getPedidosPorRestauranteYEstado(user,"pendiente");
        Map<Integer, List<Map<String, Object>>> pedidosAgrupados = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosPendientes) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosAgrupados.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosAgrupados.get(pedidoId).add(pedido);
        }

        List<Map<String, Object>> pedidosPreparacion = rDAO.getPedidosPorRestauranteYEstado(user, "en preparacion");
        Map<Integer, List<Map<String, Object>>> pedidosEnPreparacion = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosPreparacion) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosEnPreparacion.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosEnPreparacion.get(pedidoId).add(pedido);
        }
        
        List<Map<String, Object>> pedidosrechazado = rDAO.getPedidosPorRestauranteYEstado(user, "rechazado");
        Map<Integer, List<Map<String, Object>>> pedidosRechazados = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosrechazado) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosRechazados.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosRechazados.get(pedidoId).add(pedido);
        }

        HashMap<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("accion", "getPedidosRestaurante");
        model.put("pedidosAgrupados", pedidosAgrupados);
        model.put("pedidosEnPreparacion", pedidosEnPreparacion);
        model.put("pedidosRechazados",pedidosRechazados);
        model.put("template", "templates/ejercicio5.vtl");

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };

    
    public static Route AceptaPedido = (Request request, Response response) -> {
        RestauranteDAO rDAO = new RestauranteDAO();
        String user = request.session().attribute("user");
        int id = Integer.parseInt(request.queryParams("pedidoId"));
        rDAO.actualizarEstadoRestaurantePedido(id, "en preparacion");
        response.redirect("/api/restaurante/pedidos");
        return null;
    };
    
    public static Route rechazaPedido = (Request request, Response response) -> {
        RestauranteDAO rDAO = new RestauranteDAO();
        String user = request.session().attribute("user");
        int id = Integer.parseInt(request.queryParams("pedidoId"));
        rDAO.actualizarEstadoRestaurantePedido(id, "rechazado");
        response.redirect("/api/restaurante/pedidos");
        return null;
    };

}
