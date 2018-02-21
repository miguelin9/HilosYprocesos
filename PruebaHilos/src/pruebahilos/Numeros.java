/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebahilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matinal
 */
public class Numeros extends Thread {

    private int numero;

    public Numeros(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        for (int i = 0; i <= numero; i++) {
            try {
                Thread.sleep((long) (Math.random() * 2000));
                System.out.println(i + " " + this.getName());
            } catch (InterruptedException ex) {
                Logger.getLogger(Numeros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
