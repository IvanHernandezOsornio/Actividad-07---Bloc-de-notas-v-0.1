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
    
    Modelos modelocnotas; // Crea un objeto para acceder al contenido del Model.
    Vistas vistanotas; // Crea un objeto para acceder al contenido de la View.
    
    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistanotas.jb_archivo) { // Comprueba si se selecciona el ítem "Leer".
                jmi_leer_action_performed();
            }
            else if (e.getSource() == vistanotas.jb_guardar) { // Comprueba si se selecciona el ítem "Guardar".
                jmi_guardar_action_performed();
            }
        }
    };
    
    /**
     * Método para integrar los componentes del módelo MVC.
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
     * Método para el ítem "Leer" del menú (ViewBlocNotasv1).
     */
    public void jmi_leer_action_performed() {
        this.readFile(modelocnotas.getPath()); // Invoca al método "readFile" y le da como parámetro el contenido de la variable "path" (Ubicada en el Model).
    }
    
    /**
     * Método para el ítem "Guardar" del menú (ViewBlocNotasv1).
     */
    public void jmi_guardar_action_performed() {
        modelocnotas.setMessage(vistanotas.j_bloc_notas.getText()); // Asigna el contenido del área de texto (interfaz) a la variable "message".
        this.writeFile(modelocnotas.getPath(), modelocnotas.getMessage()); // Invoca al método "writeFile" y le da como parámetros el contenido de la variable "path" y de la variable "message" (ubicadas en el Model).
    }
    
    
// Métodos para la lectura y escritura del archivo de texto...
    
    /**
     * Método para mostrar en el área de texto, el contenido del archivo.
     * @param path: Indica la ruta de almacenamiento del archivo a manipular.
     * @return 
     */
    public String readFile (String path) {
        try {
            String row; // Variable que indica una "fila".
            try (FileReader archivo = new FileReader(path)) { // Permite leer el contenido del archivo.
                BufferedReader bufferedreader = new BufferedReader(archivo); // Permite almacenar el contenido del archivo de texto de forma temporal.
                while ((row = bufferedreader.readLine()) != null ) {
                    vistanotas.j_bloc_notas.setText(row);
                }
            }
        }
        catch (FileNotFoundException err) { // Detecta un error en caso de no encontrar el archivo en el path indicado.
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Marca error en caso de no contar con los permisos para acceder al archivo indicado.
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    };
    
    /**
     * Método para escribir (guardar) nuevo contenido en el archivo.
     * @param path: Indica la ruta de almacenamiento del archivo a manipular.
     * @param message: Variable que almacena el contenido del área de texto.
     */
    public void writeFile (String path, String message) {
        try {
            File archivo = new File(path); // Abre el archivo de la ruta especificada, si no existe, lo crea (según el path o ruta).
            FileWriter filewriter = new FileWriter(archivo, false); // Permite escribir en el archivo especificado.
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) { // Permite guardar el nuevo contenido en del archivo especificado.
                printwriter.println(message);
                printwriter.close();
            }
        }
        catch (FileNotFoundException err) { // Detecta un error en caso de no encontrar el archivo en el path indicado.
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Marca error en caso de no contar con los permisos para acceder al archivo indicado.
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
    }
    
    /**
     * Método para acceder a los componentes de la interfaz "ViewBlocNotasv1".
     */
    public void initComponents() {
        vistanotas.setVisible(true);
    }
    
}