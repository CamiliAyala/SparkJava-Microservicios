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
public class RegistroDAO {
    
    public boolean registrarVista(String usuario, String tituloPelicula) {
        String sql = "INSERT INTO VISUALIZACIONES (nombre_usuario, titulo_pelicula) " +
                     "VALUES (:usuario, :titulo);";
        try (Connection con = Conexion.getConexion().open()) {
            con.createQuery(sql)
                .addParameter("usuario", usuario)
                .addParameter("titulo", tituloPelicula)
                .executeUpdate();
            return true;
        } catch (Exception e) {
            registraLog.error("Error al registrarVista: {}", usuario, tituloPelicula, e);
            return false;
        }
    }
    
    public List<Pelicula> visualizacionesDelUsuario(String usuario) {
       String sql = "SELECT p.* " +
                "FROM VISUALIZACIONES v " +
                "JOIN PELICULA p ON v.titulo_pelicula = p.titulo " +
                "WHERE v.nombre_usuario = :usuario;";
        List<Pelicula> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                  .addParameter("usuario", usuario)
                  .executeAndFetch(Pelicula.class);
        } catch(Exception e){
            registraLog.error("Error al obtener todas las visualizaciones con {}", sql, e);
        }
            registraLog.info("todas las visualizaciones RES {}", res); 
        return res;
    }
    
    public String nacionDePeliculasMasVistas(String usuario){
            String sql = "SELECT p.nacion " +
                 "FROM VISUALIZACIONES v " +
                 "JOIN PELICULA p ON v.titulo_pelicula = p.titulo " +
                 "WHERE v.nombre_usuario = :usuario " +
                 "GROUP BY p.nacion " +
                 "ORDER BY COUNT(*) DESC " +
                 "LIMIT 1;";
        List<String>  res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                .addParameter("usuario", usuario)
                .executeAndFetch(String.class);
            return res.get(0);
        } catch(Exception e){
            registraLog.error("Error al obtener recomendacionPorNacion {}", sql, e);
            return null;
        }
    }
    
    public List<Pelicula> getRecomendacionPorNacion(String usuario) {
        String nacion = nacionDePeliculasMasVistas(usuario);
        System.out.println(nacion);
        String sql = "SELECT p.* " +
                 " FROM PELICULA p " +
                 " WHERE p.nacion = :nacion " +
                 " AND p.titulo NOT IN ( " +
                 "    SELECT titulo_pelicula " +
                 "    FROM VISUALIZACIONES " +
                 "    WHERE nombre_usuario = :usuario " +
                 ");";
        List<Pelicula> res = new ArrayList<>();
        try (Connection con = Conexion.getConexion().open()) {
            res =   con.createQuery(sql)
                    .addParameter("nacion", nacion)
                    .addParameter("usuario", usuario)
                    .executeAndFetch(Pelicula.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar películas del director: {}", nacion, e);
        }
        return res;
    }
    
    public String directorDePeliculasMasVistas(String usuario) {
        String sql = "SELECT p.nombre " +
                     "FROM VISUALIZACIONES v " +
                     "JOIN PELICULA p ON v.titulo_pelicula = p.titulo " +
                     "WHERE v.nombre_usuario = :usuario " +
                     "GROUP BY p.nombre " +
                     "ORDER BY COUNT(*) DESC " +
                     "LIMIT 1;";
        List<String> res = null;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .addParameter("usuario", usuario)
                    .executeAndFetch(String.class);
            return res.get(0);
        } catch (Exception e) {
            registraLog.error("Error al obtener director más visto {}", sql, e);
            return null;
        }
    }
    
    public List<Pelicula> getRecomendacionPorDirector(String usuario) {
        String director = directorDePeliculasMasVistas(usuario);
        String sql = "SELECT p.* " +
                     "FROM PELICULA p " +
                     "WHERE p.nombre = :director " +
                     "AND p.titulo NOT IN ( " +
                     "    SELECT titulo_pelicula " +
                     "    FROM VISUALIZACIONES " +
                     "    WHERE nombre_usuario = :usuario " +
                     ");";
        List<Pelicula> res = null;
        try (Connection con = Conexion.getConexion().open()) {
            res = con.createQuery(sql)
                    .addParameter("director", director)
                    .addParameter("usuario", usuario)
                    .executeAndFetch(Pelicula.class);
        } catch (Exception e) {
            registraLog.error("Error al buscar películas del director: {}", director, e);
        }
        return res;
    }
    
}