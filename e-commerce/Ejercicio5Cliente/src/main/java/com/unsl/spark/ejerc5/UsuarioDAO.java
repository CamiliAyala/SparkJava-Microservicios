/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.List;
import org.sql2o.Connection;
import util.Conexion;
import static com.unsl.spark.ejerc5.UsuarioControlador.registraLog;
import java.util.ArrayList;

/**
 *
 * @author Camil
 */
public class UsuarioDAO {
          
    public List<Usuario> getAllUsuarios(){
        String sql = "SELECT * FROM usuarios;";
        List<Usuario> res = null;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql).executeAndFetch(Usuario.class);
        } catch(Exception e){
            registraLog.error("Error al obtener todos los usuarios con {}", sql, e);
        }
            registraLog.info("todos los usuarios RES {}", res); 
        return res;
    }
    
    public boolean checkPassword(String user, String psw){
        String sql = "SELECT user_password FROM usuarios " +
                    "WHERE user_name = :user;";
        List<String> aux = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            aux = con.createQuery(sql)
                    .addParameter("user", user)
                    .executeAndFetch(String.class);
        } catch(Exception e){
            registraLog.error("Error al checking psw{}", sql, e);
        }
        boolean res = false;
        if(aux.get(0).contentEquals(psw)){
            res = true;
        }
        return res;
    }
}
