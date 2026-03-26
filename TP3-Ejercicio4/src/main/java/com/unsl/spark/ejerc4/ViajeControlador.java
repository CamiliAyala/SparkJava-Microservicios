/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc4;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ViajeControlador {
    final static Logger registraLog = LoggerFactory.getLogger(ViajeControlador.class);
    
    public static Route inicio = (Request request, Response response) -> {
        HashMap model = new HashMap<>();
        ViajeDAO rDAO = new ViajeDAO();
        model.put("ciudadesOrigen", rDAO.obtenerCiudadesOrigen());
        model.put("ciudadesDestino", rDAO.obtenerCiudadesDestino());
        model.put("template", "templates/ejercicio4.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
   public static Route buscarViaje = (Request request, Response response) -> {
        List<Viaje> viajesIda;
        List<Viaje> viajesVuelta = null;
        HashMap<String, Object> model = new HashMap<>();
        ViajeDAO rDAO = new ViajeDAO();
        String origen = request.queryParams("origen");
        String destino = request.queryParams("destino");
        String partida = request.queryParams("partida");
        String regreso = request.queryParams("regreso");
        if(regreso.isBlank()){
            viajesIda = rDAO.buscarViaje(origen, destino, partida);
            model.put("accion", "1");
            registraLog.info("buscarViajes RES: {}", viajesIda);
        }else{
            viajesIda = rDAO.buscarViaje(origen, destino, partida);
            viajesVuelta = rDAO.buscarViaje(destino, origen, regreso);
            model.put("accion", "2");
        }

             
        model.put("ciudadesOrigen", rDAO.obtenerCiudadesOrigen());
        model.put("ciudadesDestino", rDAO.obtenerCiudadesDestino());
        model.put("viajes", viajesIda);
        model.put("viajesOpc", viajesVuelta);
        model.put("template", "templates/ejercicio4.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };

}
