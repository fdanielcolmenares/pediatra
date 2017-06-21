package Utilitario;

import java.io.File;
import java.net.URL;

public class Path {
	private static String path = "";
	
	public static String getPath(){
            //System.out.println("Pide: "+path);
		return path;
	}
	
	public static String getPath(String file){
            //System.out.println("Pide: "+path+file);
		return path + file;
	}
	
	public static void setPath(String newPath){
		path = newPath;
	}
	
	public static void buscarPath(){
		String urlArchivos = "";
        urlArchivos = Path.class.getProtectionDomain().getCodeSource().getLocation().toString();
        try {
            File aux = new File(new URL(urlArchivos).toURI());            
            urlArchivos = aux.getAbsolutePath().toString();
            
            String tipo = urlArchivos;
            tipo = tipo.substring(tipo.length()-4, tipo.length());            
            if(tipo.compareToIgnoreCase(".jar")==0){
                int index = -1;
                for(int i=urlArchivos.length()-1; i>=0; i--){
                    if(urlArchivos.charAt(i) == '\\'){
                        index = i;
                        break;
                    }                   
                }
                tipo = urlArchivos;                
                if(index>=0){
                    tipo = tipo.substring(0, index);
                }
                path = tipo;
            }
            else{
                path = urlArchivos;
            }
            
            path = path + "\\";
            //System.out.println("Path "+path);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
