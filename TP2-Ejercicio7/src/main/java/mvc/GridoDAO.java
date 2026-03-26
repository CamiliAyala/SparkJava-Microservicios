/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camil
 */
public class GridoDAO {
    
    private List<Grido> listaProductosGrido;
    
    public void init(){
        listaProductosGrido = new ArrayList<>();
        listaProductosGrido.add(new Grido("1 Kilo", 5000));
        listaProductosGrido.add(new Grido("1/2 Kg", 3000));
        listaProductosGrido.add(new Grido("1/4 Kg", 1500));
        listaProductosGrido.add(new Grido("Alfajor Shot Caja X 6u", 5000));
        listaProductosGrido.add(new Grido("Alfajor Shot X Un", 1000));
        listaProductosGrido.add(new Grido("Almendrado Caja X 8 U", 4500));
        listaProductosGrido.add(new Grido("Almendrado X U", 700));
        listaProductosGrido.add(new Grido("Baño De Chocolate", 150));
        listaProductosGrido.add(new Grido("Bastoncitos De Mozzarella", 2500));
        listaProductosGrido.add(new Grido("Batido", 1500));
        listaProductosGrido.add(new Grido("Bombon Crocante X 8 U", 4000));
        listaProductosGrido.add(new Grido("Bombón Crocante X U", 700));
        listaProductosGrido.add(new Grido("Bombón Escocés X 8 U", 5500));
        listaProductosGrido.add(new Grido("Bombón Escocés X U", 900));
        listaProductosGrido.add(new Grido("Bombón Suizo X 8 U", 5000));
        listaProductosGrido.add(new Grido("Bombón Suizo X U", 800));
        listaProductosGrido.add(new Grido("Bombón V. Split X 8 U", 4000));
        listaProductosGrido.add(new Grido("Bombón V. Split X U", 700));
        listaProductosGrido.add(new Grido("Capelina", 1000));
        listaProductosGrido.add(new Grido("Capuchino", 1500));
        listaProductosGrido.add(new Grido("Casatta Caja X 8 U", 4500));
        listaProductosGrido.add(new Grido("Casatta X U", 700));
        listaProductosGrido.add(new Grido("Crocantino", 4500));
        listaProductosGrido.add(new Grido("Cucurucho Grido", 1000));
        listaProductosGrido.add(new Grido("Cups Black Por 3 Unidades", 500));
        listaProductosGrido.add(new Grido("Cups Clásico X 3 Unidades", 500));
        listaProductosGrido.add(new Grido("Cups Vasito Por 5 Unidades", 300));
        listaProductosGrido.add(new Grido("Delicia", 4500));
        listaProductosGrido.add(new Grido("Empanadas X 6 Sabor A Elección", 2500));
        listaProductosGrido.add(new Grido("Gigante 2 Bochas", 1000));
        listaProductosGrido.add(new Grido("Gigante 3 Bochas", 1500));
        listaProductosGrido.add(new Grido("Grido Toy 1 Bocha", 500));
        listaProductosGrido.add(new Grido("Grido Toy 2 Bochas", 1000));
        listaProductosGrido.add(new Grido("Maxi Grido", 1500));
        listaProductosGrido.add(new Grido("Mini Gigante", 500));
        listaProductosGrido.add(new Grido("Mini Pizza", 1500));
        listaProductosGrido.add(new Grido("Palito Bombón Caja X 20 U", 5500));
        listaProductosGrido.add(new Grido("Palito Bombón X U", 350));
        listaProductosGrido.add(new Grido("Palito Cremoso Caja X 20 U", 5000));
        listaProductosGrido.add(new Grido("Palito Cremoso X U", 300));
        listaProductosGrido.add(new Grido("Palito Frutal X 20 Unid", 4000));
        listaProductosGrido.add(new Grido("Pechuguitas De Pollo", 2500));
        listaProductosGrido.add(new Grido("Pizza Cebolla", 2500));
        listaProductosGrido.add(new Grido("Pizza Integral", 2500));
        listaProductosGrido.add(new Grido("Pizza Jamón Y Queso", 2500));
        listaProductosGrido.add(new Grido("Pizza Mozzarella", 2500));
        listaProductosGrido.add(new Grido("Pizza Tipo Casera", 3000));
        listaProductosGrido.add(new Grido("Pote Familiar X 3 LT.", 5000));
        listaProductosGrido.add(new Grido("Pote Tentación X 1 LT.", 3000));
        listaProductosGrido.add(new Grido("Sundae Frutal", 1500));
        listaProductosGrido.add(new Grido("Supergridito", 500));
        listaProductosGrido.add(new Grido("Tacita 1 Bocha", 500));
        listaProductosGrido.add(new Grido("Tacita 2 Bochas", 1000));
        listaProductosGrido.add(new Grido("Tacita 3 Bochas", 1500));
        listaProductosGrido.add(new Grido("Torta", 5500));
        listaProductosGrido.add(new Grido("Torta Cookies And Cream", 5500));
        listaProductosGrido.add(new Grido("Torta Cookies Mousse", 5500));

        
    }
    
    public List<Grido> listaProductosGRIDO() {
        init();
        return listaProductosGrido;
    }
    
    public List<Grido> productosAlcanzables(int puntos){
        init();
        List<Grido> productosAlcanzables = new ArrayList<>();
        for (Grido producto : listaProductosGrido) {
            if (producto.getPuntos() <= puntos){
                productosAlcanzables.add(producto);
            }
        }                               
        return productosAlcanzables;
    }

}
