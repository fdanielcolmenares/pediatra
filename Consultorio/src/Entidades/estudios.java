package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class estudios {
	
	private int id;
	private String descripcion;
	private Autenticacion autenticar;
	
	public estudios(Autenticacion a){
		autenticar = a;
		id = 0;
		descripcion = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from estudios), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("estudios: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("estudios: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql="insert into estudios (id,descripcion) values ("+id+",'"+descripcion+"')";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar()){
			System.out.println("no se pudo conectar estudios");
			return false;
		}
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos estudios");
			return false;
		}
		con.desconectar();
		return true;//true OK
	}
	
	public boolean vacio(){
		ResultSet res = null;
		String sql = "select id from estudios";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios vacio");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudios, metodo vacio");
			System.out.println(sql);
		}
		
		try{
			while(res.next()){
				con.desconectar();
				return false;
			}
		}catch (Exception e) {
		}
		
		return true;
	}
	
	public ResultSet cargartipos(){
		ResultSet res = null;
		String sql;
		
		sql = "select id, descripcion from estudios";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios cargartipos");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudios, metodo cargartipos");
			System.out.println(sql);
		}
		//con.desconectar();
		return res;
	}
	
	public boolean actualizar(String cam,String des){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE estudios SET "
					+"descripcion='"+des+"'"
					+"WHERE descripcion='"+cam+"'";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean existe(String des){
		ResultSet res = null;
		String sql = "select id,descripcion" +
				" from estudios where descripcion='"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudios, metodo existe");
			System.out.println(sql);
		}
		
		try{
			res.next();
			setId(res.getInt("id"));
			con.desconectar();
			return true;
		}catch (Exception e) {
			con.desconectar();
			return false;
		}
	}
	
	public int contar(){
		ResultSet res = null;
		String sql = "select COUNT(*) as numero FROM estudios";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudios, metodo existe");
			System.out.println(sql);
		}
		
		try{
			res.next();
			return res.getInt("numero");
		}catch (Exception e) {
			con.desconectar();
			return 0;
		}
	}


}
