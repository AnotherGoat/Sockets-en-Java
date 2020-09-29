package clienteTCP.lanzador;

import java.net.Socket;

public class ClienteTCP {

    //// Atributos
    /**
     * Socket del cliente
     */
    private static Socket cliente;

    //// Métodos
    public static void main(String[] args){

        System.out.println("Bienvenido al cliente TCP");

        try{
            cliente = new Socket("localhost", 80);
        } catch(Exception e){
            System.out.println("Error de conexión");
        }

    }

}
