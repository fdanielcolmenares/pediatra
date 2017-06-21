package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class presentaciones {
	
	private int id;
	private String descripcion;
	private Autenticacion autenticar;
	
	public presentaciones(Autenticacion a){
		id = 0;
		descripcion = "";
		autenticar = a;
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

	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from presentaciones), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("presentaciones: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("presentaciones: No se pudo conectar a la BD");
		}
	}
	
	public String guardar(){
		String sql;
		sql="insert into presentaciones (id,descripcion) values ("+id+",'"+descripcion+"')";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return "Ok";
			}
			else{
				con.desconectar();
				return "No se pudo guardar la presentación";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public ResultSet cargarpresent(){
		ResultSet res = null;
		String sql;
		
		sql = "SELECT id, descripcion FROM presentaciones";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar presentaciones cargarpresent");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase presentaciones, metodo cargarpresent");
			System.out.println(sql);
		}
		return res;
	}
	

	public boolean BuscarPresenta(String pre){
		ResultSet res = null;
		
		String sql = "select id, descripcion from presentaciones where descripcion ='"+pre+"'";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar presentaciones cargarpresent");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase presentaciones, metodo buscarPresenta");
			System.out.println(sql);
			return false;
		}
		try {
			res.next();
			res.getString("descripcion");
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public String actualizar(String id2,String des){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE presentaciones SET "
					+"descripcion='"+des+"'"
					+"WHERE id="+id2;
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "OK";
			}
			else{
				return "FALLO";
			}
		}
		else{
			return "FALLO";
		}
	}

}
