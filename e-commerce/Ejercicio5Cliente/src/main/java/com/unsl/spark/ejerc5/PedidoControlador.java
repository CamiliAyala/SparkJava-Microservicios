/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Camil
 */
public class PedidoControlador {
    
    public static Route confirmarPedido = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        PedidoDAO pDAO = new PedidoDAO();
        String user = request.session().attribute("user");
        Carrito c = cDAO.obtenerOCrearCarritoActivo(user);
        boolean res = pDAO.agregarPedido(c);
        res = cDAO.marcarCarritoComoPedido(user);
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "confirmarPedido");
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route detallesDelPedido = (Request request, Response response) -> {
        PedidoDAO pDAO = new PedidoDAO();
        String user = request.session().attribute("user");
        Pedido res = new Pedido();
        res = pDAO.obtenerPedido(user);
        
        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "detallesPedido");
        model.put("pedido", res);
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
}
