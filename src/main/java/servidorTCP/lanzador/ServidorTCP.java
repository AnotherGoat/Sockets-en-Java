package servidorTCP.lanzador;

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

    //// Métodos
    public static void main(String[] args){

        System.out.println("Bienvenido al servidor TCP");

        try{
            servidor = new ServerSocket(80);
            aceptarConexion = servidor.accept();
        }catch(Exception e){
            System.out.println("Error de conexión");
        }

    }

}
