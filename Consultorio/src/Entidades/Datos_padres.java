package Entidades;


import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class Datos_padres {
	private int htra_id;
	private String nombre_madre;
	private String profesion_madre;
	private String nombre_padre;
	private String profesion_padre;
	private String direccion;
	private String telefono;
	
	private Autenticacion autenticacion;
	
	public Datos_padres(Autenticacion a){
		autenticacion = a;
		
		nombre_madre = "";
		profesion_madre = "";
		nombre_padre = "";
		profesion_padre = "";
		direccion = "";
		telefono = "";
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO datos_padres "
				+ "(htra_id, nombre_madre, profesion_madre, nombre_padre, "
				+ "profesion_padre, direccion, telefono) "
				+ "VALUES ("+htra_id+", '"+nombre_madre+"', '"+profesion_madre+"', '"+nombre_padre+"', "
				+ "'"+profesion_madre+"', '"+direccion+"', '"+telefono+"')";
		
			//System.out.println(sql);
			
			con.actualizar(sql);
			
			con.desconectar();			
		}
		return "Ok";
	}
	
	public String actualizar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "UPDATE datos_padres SET "
					+"nombre_madre='"+nombre_madre+"', profesion_madre='"+profesion_madre+"', nombre_padre='"+nombre_padre+"', "
					+"profesion_padre='"+profesion_padre+"', direccion='"+direccion+"', telefono='"+telefono+"' "
					+"WHERE htra_id="+htra_id;
			
			//System.out.println(sql);
			
			con.actualizar(sql);
			
			con.desconectar();
		}
		
		return "Ok";
	}
	
	
	public void buscarHistoriaNumero(int id){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT htra_id, nombre_madre, profesion_madre, "
					+"nombre_padre, profesion_padre, direccion, telefono "
					+"FROM datos_padres WHERE htra_id="+id;
			
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					htra_id = Integer.parseInt(res.getString("htra_id").toString());
					nombre_madre = res.getString("nombre_madre").toString();
					profesion_madre = res.getString("profesion_madre").toString();
					nombre_padre = res.getString("nombre_padre").toString();
					profesion_padre = res.getString("profesion_padre").toString();
					direccion = res.getString("direccion").toString();
					telefono = res.getString("telefono").toString();
				}
			}
			catch(Exception e){
			}
			
			con.desconectar();
		}
	}

	public void setAutenticacion(Autenticacion param){
		autenticacion = param;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String param) {
		this.direccion = param;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int param) {
		this.htra_id = param;
	}

	public String getNombre_madre() {
		return nombre_madre;
	}

	public void setNombre_madre(String param) {
		this.nombre_madre = param;
	}

	public String getNombre_padre() {
		return nombre_padre;
	}

	public void setNombre_padre(String param) {
		this.nombre_padre = param;
	}

	public String getProfesion_madre() {
		return profesion_madre;
	}

	public void setProfesion_madre(String param) {
		this.profesion_madre = param;
	}

	public String getProfesion_padre() {
		return profesion_padre;
	}

	public void setProfesion_padre(String param) {
		this.profesion_padre = param;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String param) {
		this.telefono = param;
	}
	
	
}
