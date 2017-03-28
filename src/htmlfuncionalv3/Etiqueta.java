package htmlfuncionalv3;


import java.util.*;

public class Etiqueta {
    String etiqApertura, etiqCerrar;
    int tabulacion;
    ArrayList<Etiqueta> contenido = new ArrayList();
    String contenidoSimple = null;
    
    public Etiqueta(){}
    
    //Crea una etiqueta compleja vacía
    public Etiqueta(String nombreEtiq, int tabulacion){
        this.etiqApertura = ("<"+nombreEtiq+">");
        this.etiqCerrar = ("</"+nombreEtiq+">");
        this.tabulacion = tabulacion;
    }
    
    //Crea una etiqueta simple con contenido (tanto vacío como con contenido en su interior)
    public Etiqueta(String nombreEtiq, int tabulacion, String contenidoSimple){
        this.etiqApertura = ("<"+nombreEtiq+">");
        this.etiqCerrar = ("</"+nombreEtiq+">");
        this.tabulacion = tabulacion;
        this.contenidoSimple = contenidoSimple;
    }
    
    public String getEtiquetaSimple(){
        String tab = "";
        for (int i = 0; i < this.tabulacion; i++) {
            tab += "\t";
        }
        String cadena = (tab+this.etiqApertura+this.contenidoSimple+this.etiqCerrar);
        return cadena;
    }
    
    public String getEtiquetaCompleja(){
        String contenidoX = "";
        String tab = "";
        if (this.tabulacion!=0){
            for (int i = 0; i < this.tabulacion; i++) {
                tab += "\t";
            }
        }
        for (int i = 0; i < this.contenido.size(); i++) {
            if (this.contenido.get(i).contenidoSimple!=null) {
                contenidoX += (this.contenido.get(i).getEtiquetaSimple()+"\n");
            }else{
                contenidoX += (this.contenido.get(i).getEtiquetaCompleja()+"\n");
            }
        }
        
        
        
        String cadena = (tab+this.etiqApertura+"\n"+contenidoX+""+tab+this.etiqCerrar);
        return cadena;
    }
    
    public void crearEtiquetaSimp(String etiqueta, String contenido){        
        this.contenido.add(new Etiqueta(etiqueta, this.tabulacion+1, contenido));        
    }
    
    public void crearEtiquetaComp(String etiqueta){        
        this.contenido.add(new Etiqueta(etiqueta, this.tabulacion+1));        
    }
    
    public void modificar(){
        Scanner scan = new Scanner(System.in);
        int opcionEtiqueta = 0;
        String opt = "";
        
        do {
            if (this.contenidoSimple!=null) {
                System.out.println(this.getEtiquetaSimple());
            }else{
                System.out.println(this.getEtiquetaCompleja());
            }
            
            System.out.println("1) Modificar el nombre de la etiqueta y/o sus atributos: "+this.etiqApertura.split("[<>]")[1]);
            System.out.println("2) Modificar el contenido:");
            System.out.println("3) Dejar de modificar esta etiqueta:");
            if (this.contenidoSimple==null) {
                System.out.println("4) Añadir etiqueta:");
                System.out.println("5) Borrar etiqueta:");
            }
            System.out.println("Introduce la opción:");
            opt = scan.nextLine();

            switch(opt){
                case "1":
                    System.out.println("Introduce la nueva etiqueta:");
                    String etiqueta = scan.nextLine();
                    this.etiqApertura = this.etiqApertura.replaceAll("([^<>])+", etiqueta);
                    this.etiqCerrar = this.etiqCerrar.replaceAll("([^</>])+", etiqueta);
                    break;
                case "2":
                    if (this.contenidoSimple!=null) {
                        System.out.println("Introduce el nuevo contenido:");
                        String contenidoSimple = scan.nextLine();
                        this.contenidoSimple = contenidoSimple;
                    }else{
                        for (int i = 0; i < this.contenido.size(); i++) {
                            System.out.println("-------");
                            System.out.println((i+1)+") "+this.contenido.get(i).getEtiquetaCompleja());
                            System.out.println("-------");
                        }
                        System.out.println("Elige la etiqueta que quieres modificar:");
                        opcionEtiqueta = Integer.parseInt(scan.nextLine());
                        this.contenido.get(opcionEtiqueta-1).modificar();
                    }
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Introduce el nombre de la nueva etiqueta:");
                    String nomEtiqueta = scan.nextLine();
                    do {
                        System.out.println("¿Es una etiqueta simple? Y/N");
                        opt = scan.nextLine();
                        switch (opt){
                            case "Y": case "y":
                                System.out.println("Introduce el contenido:");
                                String contenidoSimple = scan.nextLine();
                                this.crearEtiquetaSimp(nomEtiqueta, contenidoSimple);
                                break;
                            case "N": case "n":
                                this.crearEtiquetaComp(nomEtiqueta);
                                break;
                        }
                    } while (!opt.equalsIgnoreCase("Y") && !opt.equalsIgnoreCase("N"));
                    
                    
                    break;
                case "5":
                    for (int i = 0; i < this.contenido.size(); i++) {
                        System.out.println("-------");
                        System.out.println((i+1)+") "+this.contenido.get(i).getEtiquetaCompleja());
                        System.out.println("-------");
                    }
                    System.out.println("Introduce la etiqueta que quieres borrar:");
                    opcionEtiqueta = Integer.parseInt(scan.nextLine());
                    this.contenido.remove(opcionEtiqueta-1);
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            System.out.println("\n\n");
        } while(!opt.equalsIgnoreCase("3"));
    }
}
