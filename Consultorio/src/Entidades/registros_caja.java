package Entidades;

import java.sql.ResultSet;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class registros_caja {
	private int id;
	private String htra_id;
	private String fecha;
	private String hora;
	private int ccto_id;
	private String detalles;
	private float monto;
	private int tpgo_id;
	private String usro_cedula;
	private Autenticacion autenticacion;
	
	public registros_caja(Autenticacion a){
		autenticacion = a;
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO registros_caja "
					+"(id, htra_id, fecha, hora, ccto_id, "
					+"detalles, monto, tpgo_id, usro_cedula) VALUES "
					+"("+id+", '"+htra_id+"', '"+fecha+"', '"+hora+"', "+ccto_id+", "
					+"'"+detalles+"', "+monto+", "+tpgo_id+",'"+usro_cedula+"')";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				con.desconectar();
				return "Ok";
			}
			else{
				con.desconectar();
				return "No se pudo guardar el registro";
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
			sql = "select ifnull((select max(ID)+1 from registros_caja), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("registros_caja: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("registros_caja: No se pudo conectar a la BD");
		}
	}
	
	public String getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(String htra_id) {
		this.htra_id = htra_id;
	}

	public int getCcto_id() {
		return ccto_id;
	}
	public void setCcto_id(int ccto_id) {
		this.ccto_id = ccto_id;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getUsro_cedula() {
		return usro_cedula;
	}

	public void setUsro_cedula(String usro_cedula) {
		this.usro_cedula = usro_cedula;
	}

	public int getTpgo_id() {
		return tpgo_id;
	}

	public void setTpgo_id(int tpgo_id) {
		this.tpgo_id = tpgo_id;
	}
}
