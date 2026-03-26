/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.sql2o.Connection;
import util.Conexion;

/**
 *
 * @author Camil
 */
public class RepartidorDAO {
    
    public List<Repartidor> getAllRepartidores(){
        String sql = "SELECT * FROM repartidores;";
        List<Repartidor> res = null;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql).executeAndFetch(Repartidor.class);
        } catch(Exception e){
            System.out.println("Error al obtener repartidores: " + e.getMessage());
        }
        return res;
    }
    
    public boolean checkPassword(String user, String psw){
        String sql = "SELECT password_repartidor FROM repartidores " +
                    "WHERE user_repartidor = :user;";
        List<String> aux = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            aux = con.createQuery(sql)
                    .addParameter("user", user)
                    .executeAndFetch(String.class);
        } catch(Exception e){
            System.out.println("Error al verificar contraseña: " + e.getMessage());
        }
        boolean res = false;
        if(aux.get(0).contentEquals(psw)){
            res = true;
        }
        return res;
    }
    
    public List<Map<String, Object>> getPedidosPorEstadoRestaurante(String estadoRestaurante) {
        List<Map<String, Object>> pedidos = new ArrayList<>();
        String sql = "SELECT p.id AS pedido_id, " +
                     "u.user_name, " +
                     "p.user_estado, " +
                     "p.restaurante_estado, " +
                     "p.repartidor_estado, " +
                     "mr.nombre_plato, " +
                     "ci.cantidad, " +
                     "(ci.cantidad * ci.precio_unitario) AS subtotal, " +
                     "SUM(ci.cantidad * ci.precio_unitario) OVER (PARTITION BY p.id) AS total_pedido " +
                     "FROM pedidos p " +
                     "JOIN carrito c ON p.carrito_id = c.id " +
                     "JOIN usuarios u ON c.user_name = u.user_name " +
                     "JOIN carrito_items ci ON ci.carrito_id = c.id " +
                     "JOIN menu_restaurante mr ON ci.menu_id = mr.id " +
                     "WHERE p.restaurante_estado = :estadoRestaurante AND (p.repartidor_estado != 'en camino' AND p.repartidor_estado != 'entregado' ) " + 
                     "ORDER BY p.id;";

        try (Connection con = Conexion.getConexion().open()) {
            pedidos = con.createQuery(sql)
                         .addParameter("estadoRestaurante", estadoRestaurante)
                         .executeAndFetchTable()
                         .asList();
        } catch (Exception e) {
            System.out.println("Error al obtener pedidos por estado: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<Map<String, Object>> getPedidosPorEstadoRepartidor(String estadoRepartidor, String user) {
        List<Map<String, Object>> pedidos = new ArrayList<>();
        String sql = "SELECT p.id AS pedido_id, " +
                     "u.user_name, " +
                     "p.user_estado, " +
                     "p.restaurante_estado, " +
                     "p.repartidor_estado, " +
                     "mr.nombre_plato, " +
                     "ci.cantidad, " +
                     "(ci.cantidad * ci.precio_unitario) AS subtotal, " +
                     "SUM(ci.cantidad * ci.precio_unitario) OVER (PARTITION BY p.id) AS total_pedido " +
                     "FROM pedidos p " +
                     "JOIN carrito c ON p.carrito_id = c.id " +
                     "JOIN usuarios u ON c.user_name = u.user_name " +
                     "JOIN carrito_items ci ON ci.carrito_id = c.id " +
                     "JOIN menu_restaurante mr ON ci.menu_id = mr.id " +
                     "WHERE p.repartidor_estado = :estadoRepartidor AND p.repartidor_nombre = :user " + 
                     "ORDER BY p.id;";

        try (Connection con = Conexion.getConexion().open()) {
            pedidos = con.createQuery(sql)
                         .addParameter("estadoRepartidor", estadoRepartidor)
                         .addParameter("user", user)
                         .executeAndFetchTable()
                         .asList();
        } catch (Exception e) {
            System.out.println("Error al obtener pedidos por estado: " + e.getMessage());
        }
        return pedidos;
    }
    
    public List<Map<String, Object>> getPedidosEntregados(String user) {
        List<Map<String, Object>> pedidos = new ArrayList<>();
        String sql = "SELECT p.id AS pedido_id, " +
                     "u.user_name, " +
                     "p.user_estado, " +
                     "p.restaurante_estado, " +
                     "p.repartidor_estado, " +
                     "mr.nombre_plato, " +
                     "ci.cantidad, " +
                     "(ci.cantidad * ci.precio_unitario) AS subtotal, " +
                     "SUM(ci.cantidad * ci.precio_unitario) OVER (PARTITION BY p.id) AS total_pedido " +
                     "FROM pedidos p " +
                     "JOIN carrito c ON p.carrito_id = c.id " +
                     "JOIN usuarios u ON c.user_name = u.user_name " +
                     "JOIN carrito_items ci ON ci.carrito_id = c.id " +
                     "JOIN menu_restaurante mr ON ci.menu_id = mr.id " +
                     "WHERE p.repartidor_estado = 'entregado', p.repartidor_nombre = :user " + 
                     "ORDER BY p.id;";

        try (Connection con = Conexion.getConexion().open()) {
            pedidos = con.createQuery(sql)
                         .addParameter("user", user)
                         .executeAndFetchTable()
                         .asList();
        } catch (Exception e) {
            System.out.println("Error al obtener pedidos entregados: " + e.getMessage());
        }
        return pedidos;
    }
    
    public void actualizarEstadoRepartidorPedido(int pedidoId, String nuevoEstado, String user) {
        String sql = "UPDATE pedidos " +
                     "SET repartidor_estado = :nuevoEstado, repartidor_nombre = :user " +
                     "WHERE id = :pedidoId;";
        
        try (Connection con = Conexion.getConexion().open()) {
            con.createQuery(sql)
               .addParameter("nuevoEstado", nuevoEstado)
               .addParameter("user", user)
               .addParameter("pedidoId", pedidoId)
               .executeUpdate();
        } catch(Exception e){
            System.out.println("Error al actualizar estado del restaurante: " + e.getMessage());
        }
    }
    
}
