/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import lombok.Getter;

/**
 *
 * @author Camil
 */
public class Path {
    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {
        @Getter public static final String GRAL = "/api/login";
        @Getter public static final String LOGIN = "/api/procesarLogin";
        @Getter public static final String INICIO = "/api/inicio";
        @Getter public static final String SHOWRESTAURANTES = "/api/restaurantes";
        @Getter public static final String MENURESTAURANTE = "/restaurante/:nombre";
        @Getter public static final String CARGARCARRITO = "/api/agregarAlCarrito";
        @Getter public static final String VERCARRITO = "/api/verCarrito";
        @Getter public static final String CONFIRMACIONPEDIDO = "/api/confirmarPedido";
        @Getter public static final String DETALLESPEDIDO = "/api/detallesPedido";
    }
    
    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
    }

}