package sincronizacionSemaforo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sincronizacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Contador contador = new Contador(); // Creamos un objeto tipo contador
        System.out.println("SincronizacionSemaforo");
        for (int i = 0; i < 150; i++) {
            Thread thread = new IncrementarContador(contador, (int) (Math.random() * 3));
            thread.start();
        }
    }

}

class Contador {

    private int contador = 0;

    // Semaforo sirve para solo dejar pasar a un hilo o a los que pongamos
    Semaphore availabe = new Semaphore(1);

    public void incrementarContador(int valor) {
        try {
            availabe.acquire();// Usamos el semaforo al a hora de controlar lo que queramos que solo haga 1 hilo a la vez
            contador += valor;
            System.out.println("Incrementando: " + valor + " contador: " + contador);
            availabe.release();// cerramos el semaforo
        } catch (InterruptedException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class IncrementarContador extends Thread {

    private Contador contador;
    private int cantidad;

    public IncrementarContador(Contador contador, int cantidad) {
        this.contador = contador;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 50));
            contador.incrementarContador(cantidad);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}


