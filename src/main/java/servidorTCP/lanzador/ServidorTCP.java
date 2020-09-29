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
    private static Socket aceptarConexion;
    /**
     * Datos que ingresan al servidor
     */
    private static DataInputStream entrada;

    //// Métodos
    public static void main(String[] args){

        System.out.println("Bienvenido al servidor TCP");

        try{
            // Inicia los socket
            servidor = new ServerSocket(80);
            aceptarConexion = servidor.accept();

            // Inicia la entrada del servidor
            entrada = new DataInputStream(aceptarConexion.getInputStream());

        } catch(Exception e){
            System.out.println("Error de conexión: "+e.getMessage());
        }

    }

}
