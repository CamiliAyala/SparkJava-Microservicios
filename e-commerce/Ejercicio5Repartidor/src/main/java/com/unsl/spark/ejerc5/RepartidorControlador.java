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
public class RepartidorControlador {
    
    public static Route login = (Request request, Response response) -> {
        HashMap model = new HashMap<>();
        List<Repartidor> usuarios;
        RepartidorDAO u = new RepartidorDAO();
        usuarios = u.getAllRepartidores();
        model.put("usuarios", usuarios);
        model.put("template", "templates/login.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route procesarLogin = (Request request, Response response) -> {
        String user = request.queryParams("usuario");
        String psw = request.queryParams("contraseña");
        RepartidorDAO u = new RepartidorDAO();
        boolean res = u.checkPassword(user, psw);
        if (res) {
            request.session(true).attribute("user", user);
            response.redirect("/api/inicio");
            return null;
        } else {
            HashMap<String, Object> model = new HashMap<>();
            List<Repartidor> usuarios = u.getAllRepartidores();
            model.put("usuarios", usuarios);
            model.put("error", "Usuario o contraseña incorrectos.");
            model.put("template", "templates/login.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
        }
    };
    
    public static Route inicio = (Request request, Response response) -> {
        HashMap<String, Object> model = new HashMap<>();
        String user = request.session().attribute("user");
        if (user == null) {
            response.redirect("/api/login");
            return null;
        }
        model.put("accion", "inicio");
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route pedidosVigentes = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");

        List<Map<String, Object>> pedidosvigentes = rDAO.getPedidosPorEstadoRestaurante("en preparacion");//o listos
        Map<Integer, List<Map<String, Object>>> pedidosVAgrupados = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosvigentes) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosVAgrupados.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosVAgrupados.get(pedidoId).add(pedido);
        }

        HashMap<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("accion", "getPedidosVigentes");
        model.put("pedidosAgrupados", pedidosVAgrupados);
        model.put("template", "templates/ejercicio5.vtl");

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route tomarPedido = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");
        int id = Integer.parseInt(request.queryParams("pedidoId"));
        rDAO.actualizarEstadoRepartidorPedido(id, "en camino", user);
        response.redirect("/api/repartidor/pedidosAceptados");
        return null;
    };
    
    public static Route rechazarPedido = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");
        int id = Integer.parseInt(request.queryParams("pedidoId"));
        rDAO.actualizarEstadoRepartidorPedido(id, null, user);
        response.redirect("/api/repartidor/pedidosVigentes");
        return null;
    };
    
    public static Route pedidosEnCamino = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");

        List<Map<String, Object>> pedidosencamino = rDAO.getPedidosPorEstadoRepartidor("en camino", user);
        Map<Integer, List<Map<String, Object>>> pedidosECAgrupados = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosencamino) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosECAgrupados.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosECAgrupados.get(pedidoId).add(pedido);
        }

        HashMap<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("accion", "getPedidosAceptados");
        model.put("pedidosAgrupados", pedidosECAgrupados);
        model.put("template", "templates/ejercicio5.vtl");

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
            
    public static Route pedidoEntregado = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");
        int id = Integer.parseInt(request.queryParams("pedidoId"));
        rDAO.actualizarEstadoRepartidorPedido(id, "entregado", user);
        response.redirect("/api/repartidor/pedidosEntregados");
        return null;
    };
    
    public static Route pedidosEntregados = (Request request, Response response) -> {
        RepartidorDAO rDAO = new RepartidorDAO();
        String user = request.session().attribute("user");

        List<Map<String, Object>> pedidosentregados = rDAO.getPedidosPorEstadoRepartidor("entregado", user);
        Map<Integer, List<Map<String, Object>>> pedidosEAgrupados = new LinkedHashMap<>();
        for (Map<String, Object> pedido : pedidosentregados) {
            Integer pedidoId = ((Number) pedido.get("pedido_id")).intValue();
            pedidosEAgrupados.putIfAbsent(pedidoId, new ArrayList<>());
            pedidosEAgrupados.get(pedidoId).add(pedido);
        }

        HashMap<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("accion", "getPedidosEntregados");
        model.put("pedidosAgrupados", pedidosEAgrupados);
        model.put("template", "templates/ejercicio5.vtl");

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
}
