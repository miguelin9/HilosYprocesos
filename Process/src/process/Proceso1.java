package process;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proceso1 {

    /**
     * Clase para probar a lanzar procesos con Runtime y ProcessBuilder, se recomienda este último.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ************************* Runtime ***********************************
        // Se instancia Runtime
        Runtime runtime = Runtime.getRuntime();
        
        // Probando un método que devuelve el n de nucleos del procesador
        System.out.println("N de procesadores del equipo: " + runtime.availableProcessors());
        
        // Creando procesos
        String[] command = {"ping", "www.google.es", "-c2"};
        BufferedInputStream inBuffer = null;
        try {
            Process process = runtime.exec(command);// Crea el proceso con el comando del string[]
            System.out.println("Exito: " + process.waitFor());// mostramos el codigo de exit 0=bien 2=error
            // Recogemos los datos en un Buffer
            inBuffer = new BufferedInputStream(process.getInputStream());
            // El buffer lee byte a byte y son numeros devuelve entre 0 y 255 y un -1 cuando ya no haya byte.
            int c;
            while ((c = inBuffer.read()) != -1) {
                System.out.print((char) c);// hacemos casting a char para pasar el byte a caracter
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();// Muestra la traza del error, se hace en desarrollo nunca en producción
        } finally {
            try {
                inBuffer.close();
            } catch (IOException e) {
                System.out.println(e);// Muestra el error, se usa en producción.
            }           
        }
             
        
        // ************************ ProccesBuilder *****************************
        // Se crean los comandos
        List<String> commands = new ArrayList<>();
        commands.add("ls");
        commands.add("-la");
        // Creamos el processBuilder, no es el proceso
        ProcessBuilder pBuilder = new ProcessBuilder(commands);
        pBuilder.directory(new File("/home/matinal"));
        Process process;
        try {
            process = pBuilder.start();
            //System.out.println("Exito: " + process.exitValue());
            BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linea;
            while ((linea = bReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
