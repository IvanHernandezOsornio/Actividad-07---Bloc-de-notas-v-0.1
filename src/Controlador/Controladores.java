/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Modelos;
import Vista.Vistas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Controladores {
    
    Modelos modelocnotas; // Crea un objeto para modelos
    Vistas vistanotas; // Crea un objeto para vistas
    
    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistanotas.jb_archivo) { 
                jb_archivo_action_performed();
            }
            else if (e.getSource() == vistanotas.jb_guardar) { 
                jb_guardar_action_performed();
            }
        }
    };
    
    /**
     * Métodologia MVC.
     * @param modelBloc
     * @param viewBloc 
     */
    public Controladores(Modelos modelBloc, Vistas viewBloc) {
        this.modelocnotas = modelBloc;
        this.vistanotas = viewBloc;
        
       this.vistanotas.jb_archivo.addActionListener(actionlistener);
        this.vistanotas.jb_guardar.addActionListener(actionlistener);
        initComponents();
    }
    
    /**
     * Método para el boton archivo
     */
    public void jb_archivo_action_performed() {
        this.readFile(modelocnotas.getPath()); // Usa el motodo readFile y combinado con la variable PATH
    }
    
    /**
     * Método para el boton guardar
     */
    public void jb_guardar_action_performed() {
        modelocnotas.setMessage(vistanotas.j_bloc_notas.getText()); //lleva el contenido a la pantalla
        this.writeFile(modelocnotas.getPath(), modelocnotas.getMessage()); 
    }
    
    
    // Métodos de ESCRITURA Y BUSQUEDA
    /**
     * Método para mostrar  contenido del archivo.
     * @param path: Indica la ruta de almacenamiento del archivo.
     * @return 
     */
    public String readFile (String path) {
        try {
            String row; // Se utiliza para ver filas.
            try (FileReader archivo = new FileReader(path)) { // Ver el contenido del archivo
                BufferedReader bufferedreader = new BufferedReader(archivo); // Almacena el contenido hasta que sea guardado
                while ((row = bufferedreader.readLine()) != null ) {
                    vistanotas.j_bloc_notas.setText(row);
                }
            }
        }
        catch (FileNotFoundException err) { // Muestra errores 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Muestra error de no poder ver el archivo inticado
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    };
    
    /**
     * Método para escribir (guardar) nuevo contenido en el archivo.
     * @param path: Ruta del archivo
     * @param message: Es en donde se almacena el texto escrito
     */
    public void writeFile (String path, String message) {
        try {
            File archivo = new File(path); // abre el archivo y si no existe crea uno nuevo
            FileWriter filewriter = new FileWriter(archivo, false);
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) {
                printwriter.println(message);
                printwriter.close();
            }
        }
        //Verifica si no hay errores
        catch (FileNotFoundException err) { 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { 
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
    }
    
    /**
     * Método de conponentes
     */
    public void initComponents() {
        vistanotas.setVisible(true);
    }
    
}