package clienteTCP.lanzador;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.net.Socket;

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

    //// Métodos
    public static void main(String[] args){

        System.out.println("Bienvenido al cliente TCP");

        try{
            // Inicia el socket
            cliente = new Socket("localhost", 80);

            // Inicia la salida del cliente
            salida = new DataOutputStream(cliente.getOutputStream());

        } catch(Exception e){
            System.out.println("Error de conexión: "+e);
        }

    }

}
