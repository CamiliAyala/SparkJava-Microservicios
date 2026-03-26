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
public class CarritoDAO {
    
    public boolean agregarCarrito(Carrito c, ItemsCarrito i) {
        String insertItem = "INSERT INTO carrito_items (carrito_id, menu_id, cantidad, precio_unitario) VALUES (:carrito_id, :menu_id, :cantidad, :precio)";

        try (Connection con = Conexion.getConexion().open()) {
            con.createQuery(insertItem)
                    .addParameter("carrito_id", c.getId())
                    .addParameter("menu_id", i.getMenu_id())
                    .addParameter("cantidad", i.getCantidad())
                    .addParameter("precio", i.getPrecio_unitario())
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar al carrito: " + e.getMessage());
            return false;
        }
    }
    
    public Carrito obtenerOCrearCarritoActivo(String user) {
        String sql = "SELECT * FROM carrito WHERE user_name = :user AND estado = 'activo';";
        try (Connection con = Conexion.getConexion().open()) {
            List<Carrito> carritos = con.createQuery(sql)
                    .addParameter("user", user)
                    .executeAndFetch(Carrito.class);
            if (!carritos.isEmpty()) {
                return carritos.get(0);
            }else {
                String insert = "INSERT INTO carrito (user_name, estado) VALUES (:user, 'activo')";
                int id = con.createQuery(insert, true)
                            .addParameter("user", user)
                            .executeUpdate()
                            .getKey(int.class);
                Carrito nuevo = new Carrito();
                nuevo.setId(id);
                nuevo.setUser_name(user);
                nuevo.setEstado("activo");
                return nuevo;
            }
        }
    }
    
    public boolean marcarCarritoComoPedido(String userName) { //xq ya fue subido comop pedido
    String sql = "UPDATE carrito SET estado = 'pedido' WHERE user_name = :user AND estado = 'activo'";
    try (Connection con = Conexion.getConexion().open()) {
        int filasActualizadas = con.createQuery(sql)
            .addParameter("user", userName)
            .executeUpdate()
            .getResult();
        return filasActualizadas > 0;
    } catch (Exception e) {
        System.out.println("Error al marcar el carrito como pedido: " + e.getMessage());
        return false;
    }
}
    
    public List<ItemsCarrito> obtenerItemsDelCarrito(int id){
        String sql = "SELECT ci.* " +
                    "FROM carrito c " +
                    "JOIN carrito_items ci ON ci.carrito_id = c.id " +
                    "WHERE c.id = :id;";
        List<ItemsCarrito> items = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            items = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(ItemsCarrito.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<String> obtenerNbrePlatos(int id){
        String sql = "SELECT m.nombre_plato " +
                    "FROM carrito c " +
                    "JOIN carrito_items ci ON ci.carrito_id = c.id " +
                    "JOIN menu_restaurante m ON ci.menu_id = m.id " +
                    "JOIN restaurantes r ON m.restaurante_nombre = r.nombre " +
                    "WHERE c.id = :id;";
        List<String> nbres = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            nbres = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(String.class);
            }
        return nbres;
    }
    

}