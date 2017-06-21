package Utilitario;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class DecodificadorDLL {
	
	public DecodificadorDLL() {
	}

	public String leer(String ruta) {
		String texto = "";
		String linea = null;
		BufferedReader fileIn = null;
		try {
			fileIn = new BufferedReader(new FileReader(new File(ruta)));
			while ((linea = fileIn.readLine()) != null) {
				texto += linea + "\n";
			}
		}
		catch (IOException ioe) {
			System.out.println("Error De Lectura");
			return ("");
		}
		try {
			if (fileIn != null) {
				fileIn.close();
			}
		}
		catch (IOException ioe) {
		}

		return texto;
	}

	public String decodificar(String ruta, int clave){
		Utils util = new Utils();
		String texto = "";
        try {
            texto = util.readEncrypted(new File(ruta), clave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
	}
	
	public boolean codificar(String ruta, String texto, int pass){
		try {
			Utils util = new Utils();
			File temp=new File("temp");
			DataOutputStream buf=new DataOutputStream(new FileOutputStream(temp));
			buf.writeBytes(texto);
			buf.close();
			util.encrypt(temp,new File(ruta), pass);
			temp.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
