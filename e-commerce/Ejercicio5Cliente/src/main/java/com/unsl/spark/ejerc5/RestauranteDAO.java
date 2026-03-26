/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import static com.unsl.spark.ejerc5.UsuarioControlador.registraLog;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Conexion;

/**
 *
 * @author Camil
 */
public class RestauranteDAO {
    
    public List<Restaurante> getAllRestaurantes(){
        String sql = "SELECT * FROM restaurantes;";
        List<Restaurante> res = null;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql).executeAndFetch(Restaurante.class);
        } catch(Exception e){
            registraLog.error("Error al obtener todos los restaurantes con {}", sql, e);
        }
            registraLog.info("todos los restaurantes RES {}", res); 
        return res;
    }
    
    public List<Menu> getMenuRestaurante(String nombre){
        String sql = "SELECT * FROM menu_restaurante "
                + "WHERE restaurante_nombre = :nombre;";
        List<Menu> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .addParameter("nombre", nombre)
                    .executeAndFetch(Menu.class);
        } catch(Exception e){
            registraLog.error("Error al obtener todos los platos con {}", sql, e);
        }
            registraLog.info("todos los platos RES {}", res);
        return res;
    }
}
