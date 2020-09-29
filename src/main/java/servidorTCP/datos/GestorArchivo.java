package servidorTCP.datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorArchivo {

    /**
     * Retorna la lista de todos los archivos de la ruta ingresada en forma de String
     * @param ruta Ruta donde se va a crear la lista de archivos
     * @return Lista de archivos
     */
    public String listarArchivos(String ruta) {

        List<String> lista;

        try{
            Stream<Path> stream = Files.walk(Paths.get(ruta));
            lista = stream
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e){
            return "";
        }

        StringBuilder s = new StringBuilder();

        // Para cada ruta en el arreglo (excepto la primera, ya que es la ruta en sí)
        for(int i=1; i<lista.size(); i++){

            // La añade al StringBuilder, pero elimina la primera parte de la ruta
            s.append(lista.get(i).substring(ruta.length()+1));

            if(i < lista.size()-1){
                s.append("\n");
            }
        }

        return s.toString();
    }


    /**
     * Retorna la lista de archivos de la ruta ingresada en forma de String
     * @param ruta Ruta donde se va a crear la lista de archivos
     * @param profundidad Profundidad máxima en la que se listarán los archivos
     * @return Lista de archivos
     */
    public String listarArchivos(String ruta, int profundidad) {

        List<String> lista;

        try{
            Stream<Path> stream = Files.walk(Paths.get(ruta));
            lista = stream
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e){
            return "";
        }

        StringBuilder s = new StringBuilder();

        // Para cada ruta en el arreglo (excepto la primera, ya que es la ruta en sí)
        for(int i=1; i<lista.size(); i++){

            // La añade al StringBuilder, pero elimina la primera parte de la ruta
            s.append(lista.get(i).substring(ruta.length()+1));

            if(i < lista.size()-1){
                s.append("\n");
            }
        }

        return s.toString();
    }

    /**
     * Crea un directorio en la ruta específicada, sólo si no existe
     * @param ruta Ruta donde se va a crear el directorio
     */
    public void crearDirectorio(String ruta){
        Path directorio = Paths.get(ruta);

        if(Files.exists(directorio)){
        }

        else{
            try{
                Files.createDirectories(directorio);
            }catch(IOException e){
            }
        }

    }

    /**
     * Crea un archivo vacío en la ruta especificada
     * @param ruta Ruta donde se va a crear el archivo
     */
    public void crearArchivo(String ruta){
        Path archivo = Paths.get(ruta);
        try{
            Files.write(archivo, "".getBytes());
        }catch(IOException e){
        }
    }

    /**
     * Crea un archivo en la ruta especificada, y con el contenido ingresado
     * @param ruta Ruta donde se va a crear el archivo
     */
    public void crearArchivo(String ruta, String contenido){
        Path archivo = Paths.get(ruta);
        try{
            Files.write(archivo, contenido.getBytes());
        }catch(IOException e){
        }
    }

    /**
     * Método que retorna un String con el contenido de un archivo, o retorna un String vacío si no existe
     * @param ruta Ruta del archivo a leer
     * @return String con el contenido del archivo
     */
    public String leerArchivo(String ruta){
        Path archivo = Paths.get(ruta);
        String contenido="";

        try{
            contenido = new String(Files.readAllBytes(archivo));
        } catch(IOException e){
        }

        return contenido;
    }

    /**
     * Mueve el archivo desde la ruta especificada a su destino (no hace nada si ya existe)
     * @param ruta Ruta del archivo original
     * @param destino Destino al que se quiere mover el archivo
     */
    public void moverArchivo(String ruta, String destino){
        // Inicia los Paths al archivo original y al archivo nuevo
        Path archivoOriginal = Paths.get(ruta);
        Path archivoNuevo = Paths.get(destino);

        try{
            // Mueve el archivo
            Files.move(archivoOriginal, archivoNuevo, StandardCopyOption.COPY_ATTRIBUTES);
        } catch(IOException e){
        }
    }

    /**
     * Mueve el archivo desde la ruta especificada a su destino, y lo sobreescribe si es que ya existe
     * @param ruta Ruta del archivo original
     * @param destino Destino al que se quiere mover el archivo
     */
    public void moverArchivoYReemplazar(String ruta, String destino){
        // Inicia los Paths al archivo original y al archivo nuevo
        Path archivoOriginal = Paths.get(ruta);
        Path archivoNuevo = Paths.get(destino);

        try{
            // Mueve el archivo
            Files.move(archivoOriginal, archivoNuevo, StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e){
        }
    }

    /**
     * Copia el archivo desde la ruta especificada a su destino (no hace nada si ya existe)
     * @param ruta Ruta del archivo original
     * @param destino Destino al que se quiere copiar el archivo
     */
    public void copiarArchivo(String ruta, String destino){
        // Inicia los Paths al archivo original y al archivo nuevo
        Path archivoOriginal = Paths.get(ruta);
        Path archivoNuevo = Paths.get(destino);

        try{
            // Copia el archivo
            Files.copy(archivoOriginal, archivoNuevo, StandardCopyOption.COPY_ATTRIBUTES);
        } catch(IOException e){
        }
    }

    /**
     * Copia el archivo desde la ruta especificada a su destino, y lo sobreescribe si es que ya existe
     * @param ruta Ruta del archivo original
     * @param destino Destino al que se quiere copiar el archivo
     */
    public void copiarArchivoYReemplazar(String ruta, String destino){
        // Inicia los Paths al archivo original y al archivo nuevo
        Path archivoOriginal = Paths.get(ruta);
        Path archivoNuevo = Paths.get(destino);

        try{
            // Copia el archivo
            Files.copy(archivoOriginal, archivoNuevo, StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e){
        }
    }

    /**
     * Elimina el archivo de la ruta especificada, siempre que no ocurra alguna excepción
     * @param ruta Ruta del archivo que se quiere borrar
     */
    public void eliminarArchivo(String ruta){
        Path archivo = Paths.get(ruta);

        try{
            Files.deleteIfExists(archivo);
        } catch (IOException e){
        }
    }
}
