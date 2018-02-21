package calculosuma;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculoSuma {

    public static void main(String[] args) {
        Suma hilo1 = new Suma(100_000_000);
        Suma hilo2 = new Suma(100_000_000);
        Suma hilo3 = new Suma(100_000_000);
        Suma hilo4 = new Suma(100_000_000);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        // Se duerme el hilo main para que de tiempo a que terminen todos los hilos y sumar bien.
        // no es la opción correcta. Probaremos el join().
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CalculoSuma.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CalculoSuma.class.getName()).log(Level.SEVERE, null, ex);
        }

        long suma1 = hilo1.getResultado();
        long suma2 = hilo2.getResultado();
        long suma3 = hilo3.getResultado();
        long suma4 = hilo4.getResultado();
        long total = suma1 + suma2 + suma3 + suma4;
        System.out.println("Resultado: " + total);
    }

}

class Suma extends Thread {

    private final long valor;
    private long resultado = 0;

    public Suma(long valor) {
        this.valor = valor;
    }

    public long getResultado() {
        return resultado;
    }

    @Override
    public void run() {
        for (long i = 0; i < valor; i++) {
            resultado++;
        }
    }

}
