/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Conexion;

/**
 *
 * @author Camil
 */
public class UsuarioDAO {
    public List<Usuario> allUsuarios() {
        String sql = "SELECT * FROM usuario;";
        List<Usuario> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .executeAndFetch(Usuario.class);

        } catch (Exception e) {
            System.out.println("ERROR ALL USUARIOS");
        }
        return res;
    }
}
