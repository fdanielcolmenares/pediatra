package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class inventario {
	
	private int id;
	private String descripcion;
	private int cantidad;
	private int cantidad_minima;
	private String valido;
	private int tiro_id;
	
	private Autenticacion autenticar;
	
	public inventario(Autenticacion a){
		autenticar = a;
		id = 0;
		descripcion = "";
		cantidad = 0;
		cantidad_minima = 0;
		valido = "";
		tiro_id = 0;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad_minima() {
		return cantidad_minima;
	}

	public void setCantidad_minima(int cantidad_minima) {
		this.cantidad_minima = cantidad_minima;
	}

	public String getValido() {
		return valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	public int getTiro_id() {
		return tiro_id;
	}

	public void setTiro_id(int tiro_id) {
		this.tiro_id = tiro_id;
	}

	public Autenticacion getAutenticar() {
		return autenticar;
	}

	public void setAutenticar(Autenticacion autenticar) {
		this.autenticar = autenticar;
	}

	public int getId() {
		return id;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from inventario), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("Consultas: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("Consultas: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql;
		sql= "insert into inventario (id,descripcion,cantidad,cantidad_minima" +
				",valido,tiro_id) values ("+id+",'"+descripcion+"'" +
						","+cantidad+","+cantidad_minima+",'"+valido+"',"+tiro_id+")";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar inventario");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos inventario");
			return false;
		}
		con.desconectar();
		return true;//true OK
	}
	
	public boolean existe(String des){
		ResultSet res = null;
		String sql = "select id,descripcion" +
				" from inventario where descripcion='"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar inventario existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase inventario, metodo existe");
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
	
	public void cargar(int id){
		ResultSet res = null;
		String sql = "select id,descripcion,cantidad,cantidad_minima,valido,tiro_id" +
				" from inventario where id="+id+"";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar inventario cargar");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase inventario, metodo cargar");
			System.out.println(sql);
		}
		
		try{
			res.next();
			setId(res.getInt("id"));
			setDescripcion(res.getString("descripcion"));
			setCantidad(res.getInt("cantidad"));
			setCantidad_minima(res.getInt("cantidad_minima"));
			setValido(res.getString("valido"));
			setTiro_id(res.getInt("tiro_id"));
		}catch (Exception e) {
		}
		con.desconectar();
	}
	
	public boolean actualizar(int id){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE inventario SET "
					+"descripcion='"+descripcion+"', cantidad='"+cantidad+"', " +
							"cantidad_minima='"+cantidad_minima+"'," +
							"tiro_id='"+tiro_id+"'"
					+"WHERE id='"+id+"'";
			
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
	
	public boolean cambiarEstado(int id,String val){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "UPDATE inventario SET "
					+"valido='"+val+"' "
					+"WHERE id="+id;
			
			//System.out.println(sql);			
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				con.desconectar();
				return false;
			}
		}
		else{
			return false;
		}
	}
	
}
