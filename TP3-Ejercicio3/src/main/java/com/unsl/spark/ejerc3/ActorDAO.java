/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unsl.spark.ejerc3;

import static com.unsl.spark.ejerc3.PeliculaControlador.registraLog;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Conexion;

/**
 *
 * @author Camil
 */
public class ActorDAO {
    
    public List<Actor> allActores() {
        String sql = "SELECT * FROM actor;";
        List<Actor> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .executeAndFetch(Actor.class);

        } catch (Exception e) {
            System.out.println("ERROR ALL ACTORES");
        }
        return res;
    }
    
    public List<Pelicula> getPeliculasActorX(String actor) {
        String sql = "SELECT p.* " +
                    "FROM PELICULA p " +
                    "WHERE p.titulo IN ( " +
                    "    SELECT r.titulo " +
                    "    FROM REPARTO r " +
                    "    WHERE r.nombre = :nombre " +
                    ");";
        List<Pelicula> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res =   con.createQuery(sql)
                    .addParameter("nombre", actor)
                    .executeAndFetch(Pelicula.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar películas del actor: {}", actor, e);
        }
        return res;
    }
    
    public List<Actor> getNacionalidadActorPorDirectorX(String director) {
        String sql = "SELECT DISTINCT a.* " +
                        "FROM DIRECTOR d " +
                        "JOIN PELICULA p ON d.nombre = p.nombre " +
                        "JOIN REPARTO r ON p.titulo = r.titulo " +
                        "JOIN ACTOR a ON r.nombre = a.nombre " +
                        "WHERE d.nombre = :nombre;";
        List<Actor> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res =   con.createQuery(sql)
                    .addParameter("nombre", director)
                    .executeAndFetch(Actor.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar películas del director: {}", director, e);
        }
        return res;
    }
}





