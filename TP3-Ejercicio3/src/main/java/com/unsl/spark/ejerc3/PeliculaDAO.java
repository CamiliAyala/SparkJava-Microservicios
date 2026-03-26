/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

import java.util.List;
import org.sql2o.Connection;
import util.Conexion;
import static com.unsl.spark.ejerc3.PeliculaControlador.registraLog;
import java.util.ArrayList;

/**
 *
 * @author Camil
 */
public class PeliculaDAO {
    
    public List<Pelicula> allPeliculas() {
        String sql = "SELECT * FROM pelicula;";
        List<Pelicula> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .executeAndFetch(Pelicula.class);
        } catch (Exception e) {
            System.out.println("ERROR ALL PELICULAS");
        }
        return res;
    }
    
    public List<Pelicula> getPeliculasDirectorX(String director) {
        String sql = "SELECT *" +
                        "FROM PELICULA " +
                        "WHERE nombre = :nombre;";
        List<Pelicula> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .addParameter("nombre", director)
                    .executeAndFetch(Pelicula.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar películas del director: {}", director, e);
        }
        return res;
    }
}
