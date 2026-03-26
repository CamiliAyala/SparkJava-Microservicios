/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package mvc;

import static spark.Spark.get;
/**
 *
 * @author Camil
 */
public class MainGrido {

    public static void main(String[] args) {
        get("/ClubGrido", GridoControlador.getProductosClubGrido);
        get("/ProductosCanjeables", GridoControlador.getProductosCanjeables);
    }
}
