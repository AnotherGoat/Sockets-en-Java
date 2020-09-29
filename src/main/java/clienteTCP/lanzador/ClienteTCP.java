package clienteTCP.lanzador;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    //// Atributos
    /**
     * Socket del cliente
     */
    private static Socket cliente;
    /**
     * Datos que salen del cliente
     */
    private static DataOutputStream out;
    /**
     * Scanner para tomar entrada por teclado
     */
    private static Scanner teclado;
    /**
     * Elección del cliente
     */
    private static int eleccion = -1;

    //// Métodos
    public static void main(String[] args){

        // Inicia el Scanner
        teclado = new Scanner(System.in);

        System.out.println("Bienvenido al cliente TCP");

        try{
            // Inicia el socket
            cliente = new Socket("localhost", 80);

            // Inicia la salida del cliente
            out = new DataOutputStream(cliente.getOutputStream());

            do{
                menu();
            } while(eleccion!=0);

        } catch(Exception e){
            System.out.println("Error de conexión: "+e.getMessage());
        }

    }

    public static void menu(){
        System.out.println("\nOpciones:");
        System.out.println("0 - Salir");
        System.out.print("Escoja una opción: ");

        try {
            eleccion = teclado.nextInt();
        } catch(Exception e){
            System.out.println("Error de entrada: "+e.getMessage());

            // Vuelve a instanciar el Scanner
            teclado = new Scanner(System.in);
        }
    }

}
