/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Conexion;

/**
 *
 * @author Camil
 */
public class PedidoDAO {
    
    public boolean agregarPedido(Carrito c) {
        String sql = "INSERT INTO pedidos (carrito_id, user_name, user_estado, restaurante_estado, repartidor_estado) "
                + "VALUES (:carrito_id, :user_name, :estado, :estado1, :estado2);";
        try (Connection con = Conexion.getConexion().open()) {
            System.out.println("entra");
            con.createQuery(sql)
                    .addParameter("carrito_id", c.getId())
                    .addParameter("user_name", c.getUser_name())
                    .addParameter("estado", "activo")
                    .addParameter("estado1", "pendiente")
                    .addParameter("estado2", "pendiente")
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar pedido: " + e.getMessage());
            return false;
        }
    }
    
    public Pedido obtenerPedido(String user) {
        String sql = "SELECT * FROM pedidos WHERE user_name = :user;";
        try (Connection con = Conexion.getConexion().open()) {
            List<Pedido> res = con.createQuery(sql)
                                  .addParameter("user", user)
                                  .executeAndFetch(Pedido.class);
            if (!res.isEmpty()) {
                return res.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pedido: " + e.getMessage());
        }
        return null; // Si no hay pedido o hay error
    }
}
