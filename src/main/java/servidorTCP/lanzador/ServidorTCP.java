package servidorTCP.lanzador;

import servidorTCP.datos.GestorArchivo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
     * Datos que salen del servidor
     */
    private static DataOutputStream out;
    /**
     * Mensaje que recibe el servidor
     */
    private static String mensajeEntrada;
    /**
     * Mensaje que envía el servidor
     */
    private static String mensajeSalida;
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
        System.out.println("Esperando que se conecte un cliente...");

        try{
            // Inicia los socket
            servidor = new ServerSocket(80);
            // Espera a que se conecte un cliente
            cliente = servidor.accept();

            // Inicia la entrada y salida del servidor
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());

            System.out.println("Se ha conectado un cliente");

            while(eleccion!=0) {

                // Recibe el siguiente int desde el cliente
                eleccion = in.readInt();
                System.out.println("\nEl cliente dice: " + eleccion);

                // Interpreta la elección del cliente
                switchEleccion();
            }

        } catch(Exception e){
            System.out.println("Error de conexión");
        }

    }

    public static void switchEleccion(){

        switch(eleccion){
            case 1:
                System.out.println("El cliente quiere ver la lista de archivos");

                // Envía la lista de archivos como un mensaje
                mensajeSalida = "\nLista de archivos:\n" + ga.listarArchivos("archivos_servidor");

                enviarMensaje();
                break;

            case 2:
                System.out.println("El cliente quiere duplicar un archivo");
                System.out.println("Esperando entrada...");

                try{
                    // Recibe el mensaje del cliente
                    mensajeEntrada = in.readUTF();

                    // Copia el archivo
                    ga.copiarArchivo("archivos_servidor/"+mensajeEntrada,
                            "archivos_servidor/"+mensajeEntrada+" - copia");

                    System.out.println("Operación exitosa");

                    mensajeSalida = "Operación exitosa";
                    enviarMensaje();

                } catch (IOException e){
                    System.out.println("Error al recibir la entrada del cliente");
                }
                break;

            case 3:
                System.out.println("El cliente quiere borrar un archivo");
                System.out.println("Esperando entrada...");

                try{
                    // Recibe el mensaje del cliente
                    mensajeEntrada = in.readUTF();

                    // Elimina el archivo
                    ga.eliminarArchivo("archivos_servidor/"+mensajeEntrada);

                    System.out.println("Operación exitosa");

                    mensajeSalida = "Operación exitosa";
                    enviarMensaje();

                } catch (IOException e){
                    System.out.println("Error al recibir la entrada del cliente");
                }
                break;

            case 0:
                System.out.println("El cliente se ha desconectado");
                System.out.println("Se apagará el servidor");
                break;

            default:
                System.out.println("No se reconoce la opción escogida");
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
}
