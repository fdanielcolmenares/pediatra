package Utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/*
 * clase decodificador sirve para desencriptar un archivo
 */
public class decodificador {

    private String ruta;
    private String leido;
    private int llave;
    private Utils util;
    private String urlArchivos = null;

    /*
     * constructor de la clase recive el nombre del archivo que se va a
     * desencriptar los archivos tienen que estar dentro de la carpeta del
     * proyecto/File/
     */
    public decodificador(String arc) {
        if (arc.compareTo("ConfigServer.dll") == 0) {
            llave = 247;
        }
        if (arc.compareTo("ConfigIni.dll") == 0) {
            llave = 634;
        }
        if (arc.compareTo("ConfigUser.dll") == 0) {
            llave = 981;
        }
        getRutaArchivos("");
        util = new Utils();
        ruta = urlArchivos;
        //ruta = ruta + "Consultorio/";
        ruta = Path.getPath();
        ruta = ruta + "Files/";
        ruta += arc;
        ruta = ruta.replaceAll("bin//", "");
        ruta = ruta.replaceAll("file:/", "");
        ruta = ruta.replaceAll("%20", " ");
        ruta = ruta.replaceAll("//", "/");
        leido = abrirArchivo();
    }

    public String getRuta() {
        return ruta;
    }

    public String getLeido() {
        return leido;
    }

    public int getLLave() {
        return llave;
    }

    /*
     * metodo para abrir el archivo y guardarlo en un String
     */
    private String abrirArchivo() {
        String texto = "";
        String linea = null;
        BufferedReader fileIn = null;
        try {
            fileIn = new BufferedReader(new FileReader(new File(ruta)));
            while ((linea = fileIn.readLine()) != null) {
                texto += linea + "\n";
            }
        } catch (IOException ioe) {
            System.out.println("Error De Lectura\n" + "Posiblemente el disco esta lleno o protegido contra lectura");
            return ("");
        } finally {
            try {
                if (fileIn != null) {
                    fileIn.close();
                }
            } catch (IOException ioe) {
            }
        }
        return texto;
    }

    /*
     * metodo para desencriptar recive un entero que es la posicion de la linea
     * del archivo que queremos leer y la retorna
     */
    public String desencriptar(int pos) {
        String texto = "";
        try {
            texto = util.readEncrypted(new File(ruta), llave, pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        texto = texto.substring(texto.indexOf("=") + 2, texto.length());
        return texto;
    }

    public String getRutaArchivos(String path){
        if(urlArchivos == null || urlArchivos.compareTo("") == 0){
            URI n = null;
            urlArchivos = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
            try {
                File aux = new File(new URL(urlArchivos).toURI());
                n = aux.getParentFile().toURI();
            } catch (Exception ex) {
            }
            urlArchivos = n.toString();
            int val = urlArchivos.indexOf("build");
            if (val > 0) {
                urlArchivos = urlArchivos.replaceAll("build/", "");
            }
        }
        return urlArchivos + path;
    }
    /*public static void main(String[] args) {
    decodificador a=new decodificador("ConfigIni.dll");
    System.out.println(a.desencriptar(1));
    }*/
}
