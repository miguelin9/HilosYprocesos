package sincronizacionProductorConsumidor;

public class Main {

    public static void main(String[] args) {
        Buffer b = new Buffer();
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();
        c.start();
    }
}

class Productor extends Thread {

    private Buffer buffer;
    private final String letras = "abcdefghijklmnopqrstuvxyz";

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            char c = letras.charAt((int) (Math.random() * letras.length()));
            buffer.poner(c);
            System.out.println(i + " Productor: " + c);
            try {
                sleep(400);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Consumidor extends Thread {

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        char valor;
        for (int i = 0; i < 10; i++) {
            valor = buffer.recoger();
            System.out.println(i + " Consumidor: " + valor);
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Buffer {

    // El problema es que el consumidor es más rápido que el productor entonces no puede recoger porque no hay producido.
//    private char contenido;
//    private boolean disponible = false;
//
//    public Buffer() {
//    }
//
//    public char recoger() {
//        if (disponible) {
//            disponible = false;
//            return contenido;
//        }
//        return ('\t');
//    }
//
//    public void poner(char c) {
//        contenido = c;
//        disponible = true;
//    }
    
    /** Solución 
     * Se usa wait() y notify() de la clase object
     * Se usa el synchronized para no poder recoger y poner a la vez.
     */
    private char contenido;
  private boolean disponible=false;
  public Buffer() {
  }

  public synchronized char recoger(){
    while(!disponible){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    disponible=false;
    notify();
    return contenido;
  }

  public synchronized void poner(char valor){
     while(disponible){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    contenido=valor;
    disponible=true;
    notify();
  }
}
