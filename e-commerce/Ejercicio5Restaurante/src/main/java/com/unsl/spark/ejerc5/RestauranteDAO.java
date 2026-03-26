/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            System.out.println("Error al getAllRestaurantes: " + e.getMessage());
        }
        return res;
    }
    
    public Restaurante getInfoRestaurante(String nombre){
        String sql = "SELECT * FROM restaurantes " +
                    "WHERE nombre = :nombre;";
        List<Restaurante> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                  .addParameter("nombre", nombre)
                  .executeAndFetch(Restaurante.class);
        } catch(Exception e){
            System.out.println("Error al getInfoRestaurante: " + e.getMessage());
        }
        return res.get(0);
    }
    
    public int getCantPlatosPorRestaurante(String nombre){
        String sql = "SELECT COUNT(DISTINCT nombre_plato) " +
                    "FROM menu_restaurante " +
                    "WHERE restaurante_nombre = :nombre;";
        int res = 0;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                  .addParameter("nombre", nombre)
                  .executeScalar(Integer.class);
        } catch(Exception e){
            System.out.println("Error al getInfoRestaurante: " + e.getMessage());
        }
        return res;
    }
    
    public Map<String, Float> getPlatos(String nombre) {
        String sql = "SELECT nombre_plato, precio " +
                     "FROM menu_restaurante " +
                     "WHERE restaurante_nombre = :nombre " +
                     "ORDER BY nombre_plato ASC;";
        Map<String, Float> res = new HashMap<>();
        try (Connection con = Conexion.getConexion().open()) {
            List<Map<String, Object>> rows = con.createQuery(sql)
                                                .addParameter("nombre", nombre)
                                                .executeAndFetchTable()
                                                .asList();

            for (Map<String, Object> row : rows) {
                String plato = (String) row.get("nombre_plato");
                Float precio = ((Number) row.get("precio")).floatValue();
                res.put(plato, precio);
            }
        } catch(Exception e){
            System.out.println("Error al getPlatos: " + e.getMessage());
        }
        return res;
    }
    
    public List<Map<String, Object>> getPedidosPorRestauranteYEstado(String nombreRestaurante, String estadoRestaurante) {
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
                     "WHERE mr.restaurante_nombre = :nombreRestaurante " +
                     "AND p.restaurante_estado = :estadoRestaurante " +
                     "ORDER BY p.id;";

        try (Connection con = Conexion.getConexion().open()) {
            pedidos = con.createQuery(sql)
                         .addParameter("nombreRestaurante", nombreRestaurante)
                         .addParameter("estadoRestaurante", estadoRestaurante)
                         .executeAndFetchTable()
                         .asList();
        } catch (Exception e) {
            System.out.println("Error al obtener pedidos por restaurante y estado: " + e.getMessage());
        }
        return pedidos;
    }

    
    public void actualizarEstadoRestaurantePedido(int pedidoId, String nuevoEstado) {
        String sql;
        if(nuevoEstado.contentEquals("rechazado")){
            sql = "UPDATE pedidos " +
                     "SET restaurante_estado = :nuevoEstado, repartidor_estado = '-' " +
                     "WHERE id = :pedidoId;";
        }else{
            sql = "UPDATE pedidos " +
                     "SET restaurante_estado = :nuevoEstado " +
                     "WHERE id = :pedidoId;";
        }
        
        try (Connection con = Conexion.getConexion().open()) {
            con.createQuery(sql)
               .addParameter("nuevoEstado", nuevoEstado)
               .addParameter("pedidoId", pedidoId)
               .executeUpdate();
        } catch(Exception e){
            System.out.println("Error al actualizar estado del restaurante: " + e.getMessage());
        }
    }

}
