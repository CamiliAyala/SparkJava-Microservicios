/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.sql2o.Sql2o;
/**
 *
 * @author Camil
 */
public class Conexion {
    protected static Sql2o conexion;

    public static Sql2o getConexion() {
        if (conexion == null) {
            String url = "jdbc:postgresql://localhost:5432/Ejercicio4";
            String usuario = "postgres";
            String contraseña = "admin";
            
            conexion = new Sql2o(url, usuario, contraseña);
        }
        return conexion;
    }
}
