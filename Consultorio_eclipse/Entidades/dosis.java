package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class dosis {
	private int id;
	private String descripcion;
	private String valida;
	private int mdna_id;
	private Autenticacion autenticacion;
	
	public dosis(Autenticacion a){
		id = 0;
		descripcion = "";
		valida = "n";
		mdna_id = 0;
		autenticacion = a;
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO dosis "
					+"(id, descripcion, valida, mdna_id) VALUES "
					+"("+id+", '"+descripcion+"', '"+valida+"', "+mdna_id+")";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "OK";
			}
			else{
				return "FALLO";
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
			sql = "UPDATE dosis SET "
					+"descripcion='"+descripcion+"', valida='"+valida+"', mdna_id="+mdna_id+" "
					+"WHERE id="+id;
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "Ok";
			}
			else{
				return "No se pudo actualizar la dosis";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public boolean consultarstatus(String id){
		String sql = "select valida from dosis where id = '"+id+"'";
		Conexion con = new Conexion(autenticacion);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase dosis, metodo consultarstatus");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();
				if(rs.getString("valida").compareTo("s")==0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				return false;
			}
		}
		con.desconectar();
		return true;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from dosis), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("dosis: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("dosis: No se pudo conectar a la BD");
		}
	}
	
	public String actualizarstatus(String id,String v){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "UPDATE dosis SET "
					+"valida='"+v+"'"
					+"WHERE id='"+id+"'";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				return "OK";
			}
			else{
				return "FALLO";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public ResultSet cargarDosis(int id){
		ResultSet res = null;
		String sql;		
		sql = "select id, descripcion, valida from dosis where mdna_id = "+id+"";
		Conexion con = new Conexion(autenticacion);
		if(!con.conectar())
			System.out.println("no se pudo conectar presentaciones cargarDosis");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase dosis, metodo cargarDosis");
			System.out.println(sql);
		}
		return res;
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
	public int getMdna_id() {
		return mdna_id;
	}
	public void setMdna_id(int mdna_id) {
		this.mdna_id = mdna_id;
	}
	public String getValida() {
		return valida;
	}
	public void setValida(String valida) {
		this.valida = valida;
	}
	
	
	
}
