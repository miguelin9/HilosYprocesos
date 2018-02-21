package sincronizacionHilos;


public class HilosSincronizacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Contador contador = new Contador(); // Creamos un objeto tipo contador
        
        // Ejecutamos 150 hilos con el mismo objeto contador y 
        // podemos ver que ha veces se incrementa el valor con una referencia ya incrementada por otro hilo.
        // Para solucionar ese fallo se usa synchronized
        for (int i = 0; i < 150; i++) { 
            Thread thread = new IncrementarContador(contador, (int) (Math.random() * 8));
            thread.start();
        }
    }
    
}

class Contador {
    private int contador = 0;
    
    public synchronized void incrementarContador(int valor) { // al poner synchronized ya solo un hilo puede haceder al mismo tiempo a este método
        contador += valor;
        System.out.println("Incrementando: " + valor + " contador: " + contador);
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

    // synchronized: todos los métodos que tengan este modificador solo se puede ejecutar un método de los que tengan synchronized
    // la unica excepción es los metodos synchronized y los static synchronized que son grupos distintos.

// Para la lectura se puede usar "volatile" como modificador de la variable y funciona bien para solo lectura,
// si hay que escribir es mejor usar synchronized.