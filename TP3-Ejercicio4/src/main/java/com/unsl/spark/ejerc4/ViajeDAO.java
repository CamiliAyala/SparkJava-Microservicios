/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc4;

import java.util.List;
import org.sql2o.Connection;
import util.Conexion;
import static com.unsl.spark.ejerc4.ViajeControlador.registraLog;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Camil
 */
public class ViajeDAO {
    
    public List<Viaje> buscarViaje(String origen, String destino, String partida) {
        String sql = "SELECT empresa, origen, destino, partida, regreso, hora_salida, precio " +
                     "FROM viajes " +
                     "WHERE origen = :origen AND destino = :destino AND partida = :partida;";
        List<Viaje> viajes = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            viajes = con.createQuery(sql)
                        .addParameter("origen", origen)
                        .addParameter("destino", destino)
                        .addParameter("partida", partida)
                        .executeAndFetch(Viaje.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar viajes por origen, destino y partida: {}", e.getMessage());
        }
        return viajes;
    }
    
    public List<String> obtenerCiudadesOrigen(){
        String sql = "SELECT  DISTINCT origen " +
                    "FROM viajes;";
        List<String> ciudades = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            ciudades = con.createQuery(sql)
                        .executeAndFetch(String.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar viajes por origen, destino y partida: {}", e.getMessage());
        }
        return ciudades;
    }
    
    public List<String> obtenerCiudadesDestino(){
        String sql = "SELECT  DISTINCT destino " +
                    "FROM viajes;";
        List<String> ciudades = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            ciudades = con.createQuery(sql)
                        .executeAndFetch(String.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar viajes por origen, destino y partida: {}", e.getMessage());
        }
        return ciudades;
    }
}