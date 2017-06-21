package CaracteristicasPagos;

import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import Utilitario.decodificador;

import ConexionBD.Conexion;

public class GestorPagos {
			private Conexion conector;
		    private ResultSet resul = null;
		    private Autenticacion a = null;
		    private decodificador dec = null;
		    private String query = null;
		    public GestorPagos(){
		    	dec = new decodificador("ConfigServer.dll");
		    	String DirIp = dec.desencriptar(0);
		    	String Port = dec.desencriptar(1);
		    	dec = new decodificador("ConfigUser.dll");		    	
		    	a = new Autenticacion();
		        a.setUsuarioBD(dec.desencriptar(0));
		        a.setClaveBD(dec.desencriptar(1));
		        a.setIpServidor(DirIp);
		        a.setPuerto(Port);
		        conector = new Conexion(a);
		    };
			
 void Load_table(Modelo_Tabla date, String Tipe,String Tabla, String campo){
	 	///System.out.println(a.getIpServidor()+","+a.getClaveBD()+","+a.getPuerto()+","+a.getUsuario());
	    
	  	conector.conectar();	  	
	  	
	  	query = "SELECT count(*) FROM "+Tabla+" WHERE 1 ";
	  	
	  	try{
	  	resul = conector.consultar(query);
	  	resul.next();
	  	int numobjec = Integer.parseInt(resul.getString(1));
	  		numobjec=numobjec+1;
	  	System.out.println(numobjec);	
	  		Object[][] datos = null; 
	  	query = "SELECT * FROM `"+Tabla+"` WHERE 1 ORDER BY("+campo+") ASC";
	  	
	  	resul = conector.consultar(query);
	  	int cont = 1;	  	
	  	datos = new Object[numobjec][1];
	  	datos[0][0]="NUEVO...";
	  	while(resul.next()){
	  		datos[cont][0] = resul.getString(2);
	  		cont++;
	  	}	
	  	date.setDataVector(datos, new String [] {
        Tipe});
	  	
	  	conector.desconectar();
	  	}catch(Exception e1){
	  		e1.printStackTrace();	  		
	  	}// fin try catch
 }///fin void Load_table		
 
 void actualizar(String name,String nuevo,String Tabla,String campo){
	nuevo = nuevo.toUpperCase();
	if(valida_repit_dato(Tabla, nuevo,campo)){
	conector.conectar(); 
	query = "SELECT ID FROM `"+Tabla+"` WHERE "+campo+" = '"+name+"'";
	resul = conector.consultar(query);
	try {
		resul.next();
		int id = Integer.parseInt(resul.getString(1));
		query = "UPDATE "+Tabla+" SET "+campo+"='"+nuevo+"' WHERE ID="+id+"";
		///System.out.println(query);
		boolean a = conector.actualizar(query);
		if(a){System.out.println("ERROR");}
		conector.desconectar();
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	}else{
		
		JOptionPane.showMessageDialog(new JFrame(),"El dato introducido esta repetido intente de nuevo.","Advertencia",0);
	}
	
 }	//fin void actualizar

 void insertar(String nuevo,String Tabla,String campo){
	 nuevo = nuevo.toUpperCase();
	 boolean ban = false;
	 conector.conectar(); 
	 query = "SELECT MAX(ID)+1 FROM "+Tabla+"";
	 ///System.out.println(query);
	 resul = conector.consultar(query);
	 try {
		 int id;
		resul.next();
		if(resul.getString(1)==null){
			id = 1;			
		}else{
		 id = Integer.parseInt(resul.getString(1).toString());
		}
		
		if(valida_repit_dato(Tabla, nuevo,campo)|| id==1){
			ban = true;
		query = "INSERT INTO "+Tabla+" VALUES('"+id+"','"+nuevo+"')";
		System.out.println(query);
		conector.actualizar(query);
		}
		
		conector.desconectar();
	}catch (Exception e3) {
		// TODO Auto-generated catch block
		//e3.printStackTrace();
	}
	 if(!ban){
		 JOptionPane.showMessageDialog(new JFrame(),"El dato introducido esta repetido intente de nuevo.","Advertencia",0);
	 }
		 
 }///fin insertar

boolean valida_repit_dato(String Tabla, String dato, String campo){
	 conector.conectar(); 
	 query = "SELECT ID FROM "+Tabla+" where "+campo+"='"+dato+"'";
	 System.out.println(query);
	 resul = conector.consultar(query);	
	 try {
		
		if(!resul.next()){
			return true;
			
		}
	} catch (Exception e4) {
		// TODO Auto-generated catch block
		e4.printStackTrace();
	}
	return false;
 }/// fin valida_repit_dato
}// fin GestorPagos
