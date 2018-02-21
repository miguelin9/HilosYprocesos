package practicaprimoshilos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PracticaPrimosHilos {

    public static boolean isPrime(long n) {
        if (n <= 3) {
            return n > 1;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        } else {
            for (long i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ArrayList<Long> numeros = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File("numeros.txt"))) {
            while (sc.hasNextLong()) {
                numeros.add(sc.nextLong());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println("Hay " + numeros.size() + " números.");
        
        Runtime runtime = Runtime.getRuntime();
        int nucleos = runtime.availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(nucleos);
        
        System.out.println("Creada una PoolThread con: " + nucleos + " hilos.");

        for (int i = 0; i < numeros.size(); i++) {
            Runnable hilo = new Hilo(numeros.get(i));
            executorService.execute(hilo);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Terminado el hilo main.");
    }

}

class Hilo implements Runnable {

    private final long atributo;

    public Hilo(long atributo) {
        this.atributo = atributo;
    }

    @Override
    public void run() {
        System.out.println(
                "Hilo: " + Thread.currentThread().getName() + "\n" +
                "Proceso: " + Thread.currentThread().getId()
        );
        if (PracticaPrimosHilos.isPrime(atributo)) {
            System.out.println("El número: " + this.atributo + " es primo.");
        } else {
            System.out.println("El número: " + this.atributo + " NO es primo.");
        }
        System.out.println("------------------------------------\n");
    }

}
