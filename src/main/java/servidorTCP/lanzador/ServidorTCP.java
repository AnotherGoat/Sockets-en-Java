package servidorTCP.lanzador;

import servidorTCP.datos.GestorArchivo;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    //// Atributos
    /**
     * Socket del servidor
     */
    private static ServerSocket servidor;
    /**
     * Socket para aceptar la conexión al servidor
     */
    private static Socket cliente;
    /**
     * Datos que ingresan al servidor
     */
    private static DataInputStream in;
    /**
     * Elección del cliente
     */
    private static int eleccion = -1;
    /**
     * Gestor de archivos del servidor
     */
    private static GestorArchivo ga;

    //// Métodos
    public static void main(String[] args){

        // Instancia el gestor de archivos
        ga = new GestorArchivo();
        // Crea el directorio si no existe
        ga.crearDirectorio("archivos_servidor");

        System.out.println("Bienvenido al servidor TCP");
        System.out.println("Esperando la conexión...");

        try{
            // Inicia los socket
            servidor = new ServerSocket(80);
            // Espera a que se conecte un cliente
            cliente = servidor.accept();

            // Inicia la entrada del servidor
            in = new DataInputStream(cliente.getInputStream());

            System.out.println("Se ha conectado un cliente");

            while(eleccion!=0) {

                // Recibe el siguiente int desde el cliente
                eleccion = in.readInt();
                System.out.println("\nEl cliente dice: " + eleccion);

                // Interpreta la elección del cliente
                switchEleccion();
            }

        } catch(Exception e){
            System.out.println("Error de conexión: "+e.getMessage());
        }

    }

    public static void switchEleccion(){

        switch(eleccion){
            case 1:
                System.out.println("El cliente quiere ver la lista de archivos");

                System.out.println(ga.listaArchivos("archivos_servidor"));
                break;

            case 2:
                System.out.println("El cliente quiere duplicar un archivo");
                break;

            case 3:
                System.out.println("El cliente quiere borrar un archivo");
                break;

            case 0:
                System.out.println("El cliente se ha desconectado");
                System.out.println("Se apagará el servidor");
                break;

            default:
                System.out.println("No se reconoce la opción escogida");
        }

    }
}
