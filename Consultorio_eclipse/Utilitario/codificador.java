package Utilitario;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/*
 * Clase para encrictar un String
 * cuando se valla a usar la clase se tiene que llamar el metodo setTexto primero 
 * para establecer el texto que se va a desencriptar y despues se llama al 
 * metodo codificar
 */
public class codificador {
	
	private String texto;
	private Utils util;
	private String ruta;
	private String urlArchivos = null;
	public final int SERVER_KEY = 247;
	public final int INI_KEY = 634;
	public final int USER_KEY = 981;
	public final String SERVER_NAME = "ConfigServer.dll";
	public final String INI_NAME = "ConfigIni.dll";
	public final String USER_NAME = "ConfigUser.dll";
	
	/*
	 * Constructor. Establese la ruta la base donde se van a guardar
	 * los archivos. la ruta base es dentro de la carpeta donde esta el jar
	 * /Files
	 */
	public codificador(){
		util = new Utils();
        getRutaArchivos("");
        ruta = urlArchivos;
        //ruta = ruta + "Consultorio/";
        ruta = Path.getPath();
        ruta = ruta + "Files/";
        ruta = ruta.replaceAll("bin//", "");
        ruta = ruta.replaceAll("file:/", "");
        ruta = ruta.replaceAll("%20", " ");
        ruta = ruta.replaceAll("//", "/");	
	}
	
	public String getTexto(){
		return texto;
	}
	
	public void setTexto(String cadena){
		texto=cadena;
	}
	
	public String getRuta(){
		return ruta;
	}
	
	/*
	 * metodo codificar sirve para codificar el String
	 * crea un archivo temporal donde se guarda el String
	 * para despues ser codificado y guardado en el archivo final
	 */
	public void codificar(int llave,String nombre) throws IOException{
		try {
			File temp=new File("temp");
			DataOutputStream buf=new DataOutputStream(new FileOutputStream(temp));
			buf.writeBytes(texto);
			buf.close();
			util.encrypt(temp,new File(ruta+nombre),llave);
			temp.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		codificador a=new codificador();
		try {
			a.setTexto("Nombre = daniel \n cedula = 19234634");
			a.codificar(634,"Prueba.dll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
}
