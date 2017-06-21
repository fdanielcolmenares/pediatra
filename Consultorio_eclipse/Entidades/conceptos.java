package Entidades;

import java.sql.ResultSet;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class conceptos {
	private int id;
	private String descripcion;
	private Autenticacion autenticacion;
	
	public conceptos(Autenticacion a){
		id = 0;
		descripcion = "";
		autenticacion = a;
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO conceptos "
					+"(id, descripcion) VALUES "
					+"("+id+", '"+descripcion+"')";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				con.desconectar();
				return "OK";
			}
			else{
				con.desconectar();
				return "No se pudo guardar el concepto";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public String actualizar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "UPDATE conceptos SET "
					+"descripcion='"+descripcion+"' "
					+"WHERE id="+id;
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "Ok";
			}
			else{
				return "No se pudo actualizar el concepto";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}

	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from conceptos), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("conceptos: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("conceptos: No se pudo conectar a la BD");
		}
	}
	
	public String buscarConcepto(int idConcepto){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT id, descripcion "
					+"FROM conceptos " 
					+"WHERE id ="+idConcepto;
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					id = idConcepto;
					descripcion = res.getString("descripcion").toString();
				}
				else{
					con.desconectar();
					return "No se encontro el concepto";
				}
			}
			catch(Exception e){
				con.desconectar();
				return "No se pudo conectar";
			}
			con.desconectar();
		}
		else{
			return "No se pudo conectar con el servidor";
		}
		return "Ok";
	}	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
