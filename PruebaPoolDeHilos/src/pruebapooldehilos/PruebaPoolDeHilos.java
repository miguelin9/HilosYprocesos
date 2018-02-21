package pruebapooldehilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para probar la agrupación de hilos.
 * Creamos una pool de hilos con los núcleos del procesador.
 * Creamos 20 hilos y vemos que se van ejecutando a la vez los mismos que núcleos haya.
 * 
 * @author matinal
 */
public class PruebaPoolDeHilos {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();// Te dice el número de procesadores que tienes
        System.out.println("Nº de núcleos: " + processors);
        
        ExecutorService executor = Executors.newFixedThreadPool(processors);// Crea una pool de hilos, en este caso tantos hilos como núcleos
        
        for (int i = 0; i < 20; i++) {
            Runnable tarea = new HiloDelPool(i);
            executor.execute(tarea);
        }
        
        executor.shutdown();// No acepta más hilos y espera a que terminen los que haya ejecutandose, luego se detiene.
        while (!executor.isTerminated()) {}// bucle que se ejecuta hasta que termine el pool de hilos.
        System.out.println("Terminado hilo main.");
    }

}

class HiloDelPool implements Runnable {

    private int atributo;

    public HiloDelPool(int atributo) {
        this.atributo = atributo;
    }
    
    @Override
    public void run() {
        System.out.println("Inicio del hilo: " + Thread.currentThread().getName() + " --- atributo: " + this.atributo);
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
