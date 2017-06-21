package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class tipo_inventario {
	
	private int id;
	private String desc;
	private Autenticacion autenticar;
	
	public tipo_inventario(Autenticacion a){
		autenticar = a;
		id = 0;
		desc = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from tipo_inventario), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("tipo_inventario: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("tipo_inventario: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql="insert into tipo_inventario (id,descripcion) values ("+id+",'"+desc+"')";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar presentaciones");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos tipo_inventario");
			return false;
		}
		con.desconectar();
		return true;//true OK
	}
	
	public boolean vacio(){
		ResultSet res = null;
		String sql = "select id from tipo_inventario";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar tipo_inventario vacio");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase tipo_inventario, metodo vacio");
			System.out.println(sql);
		}
		
		try{
			while(res.next()){
				return false;
			}
		}catch (Exception e) {
		}
		
		return true;
	}
	
	public ResultSet cargartipos(){
		ResultSet res = null;
		String sql;
		
		sql = "select id, descripcion from tipo_inventario";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar presentaciones cargartipos");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase tipo_inventario, metodo cargartipos");
			System.out.println(sql);
		}
		//con.desconectar();
		return res;
	}
	
	public boolean actualizar(String cam,String des){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE tipo_inventario SET "
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
				" from tipo_inventario where descripcion='"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar tipo_inventario existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase tipo_inventario, metodo existe");
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

}
