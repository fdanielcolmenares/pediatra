package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class medicinas {

	private Autenticacion autenticar;	 
	private int id;
	private int id2;
	private String nombre;
	private String descripcion;
	private String valida;
	
	public medicinas(Autenticacion a) {
		autenticar = a;
		id = 0;
		nombre = "";
		descripcion = "";
		valida = "";
		id2 = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValida() {
		return valida;
	}

	public void setValida(String valida) {
		this.valida = valida;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from medicinas), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("medicinas: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("medicinas: No se pudo conectar a la BD");
		}
	}
	
	public void buscarNuevoID2(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from presentaciones_medicina), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id2 = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("medicinas: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("medicinas: No se pudo conectar a la BD");
		}
	}
	
	public String actualizar(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE medicinas SET "
					+"nombre='"+getNombre()+"', descripcion='"+getDescripcion()+"', valida='"+getValida()+"'"
					+"WHERE id='"+id+"'";
			
			if(con.actualizar(sql)){
				con.desconectar();
				return "Ok";
			}
			else{
				con.desconectar();
				return "No se pudo actualizar el medicamento";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public String guardar(){
		String sql;
		sql="INSERT INTO medicinas (id, nombre, descripcion, valida) VALUES "
				+"("+id+", '"+nombre+"', '"+descripcion+"', '"+valida+"')";
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
	
	public String guardar(String pscn_id){
		String sql;
		sql="insert into medicinas (id,nombre,descripcion,valida) values ("+id+",'"+nombre+"'," +
				"'"+descripcion+"','"+valida+"')";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar medicinas");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos medicinas");
			return "FALLO";
		}
		
		sql="insert into presentaciones_medicina (id,mdna_id,pscn_id) values ("+id2+"," +
				""+id+","+pscn_id+")";
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos medicinas");
			return "FALLO";
		}
		
		con.desconectar();
		
		return "OK";
	}
	
	public String cargar(String id){
		String sql = "select id,nombre,descripcion,valida from medicinas where id='"+id+"'";
		
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase medicinas, metodo cargar");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();
				setId(rs.getInt("id"));
				setNombre(rs.getString("nombre"));
				setDescripcion(rs.getString("descripcion"));
				setValida(rs.getString("valida"));
			} catch (SQLException e) {
				return "FALLO";
			}
		}
		con.desconectar();
		return "OK";
	}
	
	public int idpresentacion(){
		String sql = "select mdna_id, pscn_id from presentaciones_medicina where mdna_id="+getId()+"";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase medicinas, metodo idpresentacion");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();
				return rs.getInt("pscn_id");
			} catch (SQLException e) {
				return 0;
			}
		}
		con.desconectar();
		return 0;
	}
	
	public String actuliazarMedica(int id, String pcsn){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE medicinas SET "
					+"nombre='"+getNombre()+"', descripcion='"+getDescripcion()+"', valida='"+getValida()+"'"
					+"WHERE id='"+id+"'";
			
			if(con.actualizar(sql)){
				//presentaciones_medicina (id,mdna_id,pscn_id)
				sql = "UPDATE presentaciones_medicina SET "
					+"pscn_id="+pcsn+""
					+" WHERE mdna_id='"+id+"'";
				if(con.actualizar(sql))
					return "OK";
				else
					return "FALLO";
			}
			else{
				return "FALLO";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}

}
