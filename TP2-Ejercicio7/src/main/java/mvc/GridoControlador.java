/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Camil
 */
public class GridoControlador {
         
    public static Route 
        getProductosClubGrido = (Request req, Response res) -> {
            GridoDAO gd = new GridoDAO();
            List<Grido> lista = new ArrayList<>();
            lista = gd.listaProductosGRIDO();
            HashMap model = new HashMap<>();
            model.put("listaProductos", lista);
            model.put("template", "templates/gridoInicio.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
        };
    
    public static Route 
        getProductosCanjeables = (Request req, Response res) -> {
            GridoDAO gd = new GridoDAO();
            List<Grido> lista = new ArrayList<>();
            int puntos = Integer.parseInt(req.queryParams("puntos"));
            lista = gd.productosAlcanzables(puntos);
            HashMap model = new HashMap<>();
            model.put("listaProductos", lista);
            model.put("template", "templates/gridoInicio.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vtl"));
        };
}
