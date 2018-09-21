
package main;

import Controlador.Controladores;
import Modelo.Modelos;
import Vista.Vistas;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // IComponentes MVC.
        Modelos modelblocnotas = new Modelos();
        Vistas viewblocnotas = new Vistas();
        Controladores ontrollerblocnotas = new Controladores(modelblocnotas, viewblocnotas); 
        
    }
    
}
