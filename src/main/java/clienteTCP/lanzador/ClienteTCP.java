package clienteTCP.lanzador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    //// Atributos
    /**
     * Socket del cliente
     */
    private static Socket cliente;
    /**
     * Datos que entran al cliente
     */
    private static DataInputStream in;
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
    /**
     * String que entra al cliente
     */
    private static String entrada;

    //// Métodos
    public static void main(String[] args){

        // Inicia el Scanner
        teclado = new Scanner(System.in);

        System.out.println("Bienvenido al cliente TCP");

        try{
            // Inicia el socket
            cliente = new Socket("localhost", 80);

            // Inicia la entrada y salida del cliente
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());

            do{
                menu();

            } while(eleccion!=0);

        } catch(Exception e){
            System.out.println("Error de conexión");
        }

    }

    public static void menu(){
        System.out.println("\nOpciones:");
        System.out.println("1 - Ver listado de archivos");
        System.out.println("2 - Duplicar un archivo");
        System.out.println("3 - Eliminar un archivo");
        System.out.println("0 - Salir");
        System.out.print("Escoja una opción: ");

        try {
            // Almacena la elección
            eleccion = teclado.nextInt();

            // Envía la elección al servidor
            out.writeInt(eleccion);

            // Interpreta la elección
            switchEleccion();

        } catch(Exception e){
            System.out.println("Error de entrada: "+e.getMessage());

            // Vuelve a instanciar el Scanner
            teclado = new Scanner(System.in);
        }
    }

    public static void switchEleccion() {

        switch(eleccion) {
            case 1:
                try {
                    // Recibe la lista de archivos desde el servidor
                    entrada = in.readUTF();

                    System.out.println("\nLista de archivos:");
                    System.out.println(entrada);

                } catch(IOException e){
                    System.out.println("No se ha podido recibir la lista de archivos");
                }

                break;

            case 2:
                break;

            case 3:
                break;

            case 0:
                System.out.println("Ha escogido salir del programa");
                break;

            default:
                System.out.println("Opción no válida");
        }

    }
}
