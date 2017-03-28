package htmlfuncionalv3;


import java.util.*;

public class generarHTML {
    
    public static Etiqueta genera(){
        Etiqueta ec1 = new Etiqueta("html", 0);
        ec1.crearEtiquetaComp("head");
        ec1.contenido.get(0).crearEtiquetaSimp("title", "");
        ec1.crearEtiquetaComp("body");
        
        return ec1;
    }
    
}
