package servidorTCP.lanzador;

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

    private static int eleccion = -1;

    //// Métodos
    public static void main(String[] args){

        System.out.println("Bienvenido al servidor TCP");
        System.out.println("Esperando la conexión...");

        try{
            // Inicia los socket
            servidor = new ServerSocket(80);
            cliente = servidor.accept();

            // Inicia la entrada del servidor
            in = new DataInputStream(cliente.getInputStream());

            System.out.println("Se ha conectado un cliente");

            while(eleccion!=0){

                eleccion = in.readInt();
                System.out.println("El cliente dice: "+eleccion);

            }

        } catch(Exception e){
            System.out.println("Error de conexión: "+e.getMessage());
        }

    }

}
