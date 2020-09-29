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
     * Mensaje que recibe el cliente
     */
    private static String mensajeEntrada;
    /**
     * Mensaje que envía el cliente
     */
    private static String mensajeSalida;
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
                // Recibe la lista de archivos desde el servidor
                recibirMensajeYMostrar();
                break;

            case 2:
                teclado = new Scanner(System.in);

                System.out.println("Ingrese la ruta del archivo que quiere duplicar:");
                mensajeSalida = teclado.nextLine();

                // Envía la ruta al servidor
                enviarMensaje();

                // Muestra la respuesta del servidor
                recibirMensajeYMostrar();

                break;

            case 3:
                teclado = new Scanner(System.in);

                System.out.println("Ingrese la ruta del archivo que quiere borr+ar:");
                mensajeSalida = teclado.nextLine();

                // Envía la ruta al servidor
                enviarMensaje();

                // Muestra la respuesta del servidor
                recibirMensajeYMostrar();

                break;

            case 0:
                System.out.println("Ha escogido salir del programa");
                break;

            default:
                System.out.println("Opción no válida");
        }

    }

    public static void enviarMensaje(){
        try{
            // Envía el mensaje
            out.writeUTF(mensajeSalida);
        } catch(IOException e){
            System.out.println("No se ha podido enviar el mensaje");
        }
    }

    public static void recibirMensaje(){
        try {
            // Recibe el mensaje
            mensajeEntrada = in.readUTF();
        } catch(IOException e){
            System.out.println("No se ha podido recibir el mensaje");
        }
    }

    public static void recibirMensajeYMostrar(){
        try {
            // Recibe el mensaje
            mensajeEntrada = in.readUTF();

            // Muestra el mensaje
            System.out.println(mensajeEntrada);

        } catch(IOException e){
            System.out.println("No se ha podido recibir el mensaje");
        }
    }
}
