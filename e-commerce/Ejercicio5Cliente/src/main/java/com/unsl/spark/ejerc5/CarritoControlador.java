/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.HashMap;
import java.util.List;
import org.apache.velocity.tools.generic.MathTool;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Camil
 */
public class CarritoControlador {
    
    public static Route agregarAlCarrito = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        String user = request.session().attribute("user");
        Carrito c = cDAO.obtenerOCrearCarritoActivo(user);

        ItemsCarrito i = new ItemsCarrito();
        i.setMenu_id(Integer.parseInt(request.queryParams("menu_id")));
        i.setCantidad(Integer.parseInt(request.queryParams("cantidad")));
        i.setPrecio_unitario(Float.parseFloat(request.queryParams("precio")));
        boolean res = cDAO.agregarCarrito(c, i);

        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "agregadoACarrito");
        model.put("user", user);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };

    
    public static Route visualizarCarrito = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        String user = request.session().attribute("user");
        Carrito c = cDAO.obtenerOCrearCarritoActivo(user);

        List<ItemsCarrito> items = cDAO.obtenerItemsDelCarrito(c.getId());
        List<String> nbres = cDAO.obtenerNbrePlatos(c.getId());

        HashMap<String, Object> model = new HashMap<>();
        model.put("accion", "getCarrito");
        model.put("user", user);
        model.put("carritoItems", items);
        model.put("nombres", nbres);
        model.put("template", "templates/ejercicio5.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
}
