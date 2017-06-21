package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class vacunas {
	private int id;
	private String nombre;
	private String descripcion;
	private Autenticacion autenticacion;
	
	public vacunas(Autenticacion a){
		autenticacion = a;
		
		id = 0;
		nombre = "";
		descripcion = "";
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO vacunas "
					+"(id, nombre, descripcion) VALUES "
				+"("+id+", '"+nombre+"', '"+descripcion+"')";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "Ok";
			}
			else{
				return "No se pudo guardar la vacuna";
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
			sql = "UPDATE vacunas SET "
					+"nombre='"+nombre+"', descripcion='"+descripcion+"' "
					+"WHERE id="+id;
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "Ok";
			}
			else{
				return "No se pudo actualizar la vacuna";
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
			sql = "select ifnull((select max(ID)+1 from vacunas), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("vacunas: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("vacunas: No se pudo conectar a la BD");
		}
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
