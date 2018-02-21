/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebahilos;

/**
 *
 * @author matinal
 */
public class PruebaHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread hilo1 = new Numeros(500);
        hilo1.start();
        
        Thread hilo2 = new Numeros(500);
        hilo2.start();
    }
    
}
