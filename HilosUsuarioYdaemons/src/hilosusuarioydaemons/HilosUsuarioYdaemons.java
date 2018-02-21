/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilosusuarioydaemons;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matinal
 */
public class HilosUsuarioYdaemons {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread hilo = new Thread(new HiloUsuario());
        hilo.setDaemon(true);
        hilo.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HilosUsuarioYdaemons.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Nombre: " + Thread.currentThread().getName() + " Prioridad: " + Thread.currentThread().getPriority());
        }

        System.out.println("FIN DEL HILO PRINCIPAL");
    }

}

class HiloUsuario implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Nombre: " + Thread.currentThread().getName() + " Prioridad: " + Thread.currentThread().getPriority());
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
