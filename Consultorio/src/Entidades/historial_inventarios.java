package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class historial_inventarios {
	
	private int id;
	private String observacion; 
	private int usro_cedula;
	private int ftra_id;
	private int ivro_id;
	private Autenticacion autenticar;
	
	public historial_inventarios(Autenticacion a){
		autenticar = a;
		id = 0;
		observacion = "";
		usro_cedula = 0;
		ftra_id = 0;
		ivro_id = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getUsro_cedula() {
		return usro_cedula;
	}

	public void setUsro_cedula(int usro_cedula) {
		this.usro_cedula = usro_cedula;
	}

	public int getFtra_id() {
		return ftra_id;
	}

	public void setFtra_id(int ftra_id) {
		this.ftra_id = ftra_id;
	}

	public int getIvro_id() {
		return ivro_id;
	}

	public void setIvro_id(int ivro_id) {
		this.ivro_id = ivro_id;
	}	
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from historial_inventarios), 1)";
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
		sql= "insert into historial_inventarios (id,observacion,usro_cedula" +
				",ivro_id) values ("+id+",'"+observacion+"'" +
						","+usro_cedula+","+ivro_id+")";
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
	
	public ResultSet cargar(){
		ResultSet res = null;
		String sql;
		
		/*sql = "SELECT id, observacion, usro_cedula, ivro_id "
				+"FROM historial_inventarios";
		*/
		sql = "SELECT h.id AS id, CONCAT(h.observacion, ' de ', i.descripcion) AS observacion, "
			+"h.usro_cedula AS usro_cedula,h.ivro_id AS ivro_id, u.nombre AS nombre "
			+"FROM historial_inventarios h, usuarios u, inventario i "
			+"WHERE h.usro_cedula=u.cedula AND i.id=h.ivro_id ORDER BY h.id DESC";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar historial_inventarios cargar");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase historial_inventarios, metodo cargar");
			System.out.println(sql);
		}
		return res;
	}
	
	public ResultSet cargar(int id){
		ResultSet res = null;
		String sql;
		
		sql = "SELECT h.id AS id,h.observacion AS observacion, "
				+"h.usro_cedula AS usro_cedula,h.ivro_id AS ivro_id, u.nombre AS nombre "
				+"FROM historial_inventarios h, usuarios u "
				+"WHERE h.usro_cedula=u.cedula AND h.ivro_id = "+id+" ORDER BY h.id DESC";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar historial_inventarios cargar");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase historial_inventarios, metodo cargar");
			System.out.println(sql);
		}
		return res;
	}

}
