package sincronizacionColecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Colecciones {

    public static void main(String[] args) {
        Coleccion coleccion = new Coleccion();
        for (int i = 0; i < 10; i++) {
//            coleccion.quitarPrimerElemento(); // Para la clase Coleccion (sin usar hilos)
            Thread hilo = new Thread(new OperacionColeccion(coleccion)); // Para la clase OperacionColeccion (colección sin sincronizar)
            hilo.start();
        }
    }

}

class Coleccion {

//    private List<Integer> listaEnteros = new ArrayList<>();
    private List<Integer> listaEnteros = Collections.synchronizedList(new ArrayList<>());
//    private Vector<Integer> listaEnteros = new Vector<>(); // Una solución es usar un tipo de lista antiguo como Vector que tiene menos rendimiento pero esta sincronizado. si ejecutamos con este podemos ver que el orden no esta bien pero al final se queda el array vacio.

    public Coleccion() {
        for (int i = 0; i < 10; i++) {
            listaEnteros.add(i);
        }
    }

    public void quitarPrimerElemento() {
        listaEnteros.remove(0);
        System.out.println(listaEnteros);
    }
}

class OperacionColeccion implements Runnable {

    private Coleccion coleccion;

    public OperacionColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
            coleccion.quitarPrimerElemento();
        } catch (InterruptedException ex) {
            Logger.getLogger(OperacionColeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
