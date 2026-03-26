/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

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
public class UsuarioControlador {
    final static Logger registraLog = LoggerFactory.getLogger(UsuarioControlador.class);
    
    public static Route login = (Request request, Response response) -> {
        HashMap model = new HashMap<>();
        List<Usuario> usuarios;
        UsuarioDAO u = new UsuarioDAO();
        usuarios = u.getAllUsuarios();
        model.put("usuarios", usuarios);
        model.put("template", "templates/login.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
    };
    
    public static Route procesarLogin = (Request request, Response response) -> {
        String user = request.queryParams("usuario");
        String psw = request.queryParams("contraseña");

        UsuarioDAO u = new UsuarioDAO();
        boolean res = u.checkPassword(user, psw);

        if (res) {
            request.session(true).attribute("user", user);
            response.redirect("/api/inicio");
            return null;
        } else {
            HashMap<String, Object> model = new HashMap<>();
            List<Usuario> usuarios = u.getAllUsuarios();
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

}
