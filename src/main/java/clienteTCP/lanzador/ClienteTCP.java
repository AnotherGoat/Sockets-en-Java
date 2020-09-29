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
    private static DataOutputStream salida;
    /**
     * Scanner para tomar entrada por teclado
     */
    private static Scanner teclado;

    //// Métodos
    public static void main(String[] args){

        // Inicia el Scanner
        teclado = new Scanner(System.in);

        System.out.println("Bienvenido al cliente TCP");

        try{
            // Inicia el socket
            cliente = new Socket("localhost", 80);

            // Inicia la salida del cliente
            salida = new DataOutputStream(cliente.getOutputStream());

        } catch(Exception e){
            System.out.println("Error de conexión: "+e.getMessage());
        }

    }

}
