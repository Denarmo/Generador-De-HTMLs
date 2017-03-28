package htmlfuncionalv3;

import java.io.*;

public class Principal {

    public static void main(String[] args) {        
        Etiqueta ec1 = generarHTML.genera();
               
        ec1.modificar();
        System.out.println(ec1.getEtiquetaCompleja());
        
        FileWriter fw = null;

        try{
            //Añadir true si el fichero ya existe y se quiere escribir en él
            fw = new FileWriter("./carpeta/index.html");
            //TODO Here
            fw.write(ec1.getEtiquetaCompleja());
        } catch (IOException e){
            System.out.println("Error de Excepción IO - Nivel 1");
        } finally {
            try {
                fw.close();
            } catch (IOException e){
                System.out.println("Error de Excepción IO - Nivel 2");
            }
        }
    }
}
