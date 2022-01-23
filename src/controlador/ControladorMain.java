/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package controlador;

import vista.fMain;


public class ControladorMain {
    public static fMain ventana = new fMain();
    public static void mostrar() { ventana.setVisible(true);}
    public static void ocultar() { ventana.setVisible(false);}

    public static void btnSalir() {
        ocultar();
        ControladorLogin.mostrar();
    }
    
    public static void btnVentas() {
        ocultar(); 
        ControladorVentas.mostrar();
    }
    
    public static void btnMaestros() {
        ocultar();
        ControladorArticulos.mostrar();
    }

    public static void btnCompras() {
        ocultar();
        //ControladorCompras.mostrar();
    }
}
