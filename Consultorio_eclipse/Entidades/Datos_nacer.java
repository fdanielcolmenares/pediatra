package Entidades;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class Datos_nacer {
	private int htra_id;
	private String gesta;
	private String peso;
	private String talla;
	private String tipo_parto;
	private String causas;
	private String enfermedades_embarazo;
	private String complicaciones;
	private String lactancia;
	private String tetero;
	private String ablactacion;
	private String condiciones;
	
	private Autenticacion autenticacion;
	
	public Datos_nacer(Autenticacion a){
		autenticacion = a;
		
		gesta = "";
		peso = "";
		talla = "";
		tipo_parto = "";
		causas = "";
		enfermedades_embarazo = "";
		complicaciones  = "";
		lactancia = "";
		tetero = "";
		ablactacion = "";
		condiciones = "";
}

	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO datos_nacer "
					+"(htra_id, gesta, peso, talla, tipo_parto, causas, "
					+ "enfermedades_embarazo, complicaciones, lactancia, tetero, ablactacion, condiciones) "
					+ "VALUES ("+htra_id+", '"+gesta+"', '"+peso+"', '"+talla+"', '"+tipo_parto+"', '"+causas+"', "
					+"'"+enfermedades_embarazo+"', '"+complicaciones+"', '"+lactancia+"', '"+tetero+"', " +
							"'"+ablactacion+"','"+condiciones+"')";
			
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
			sql = "UPDATE datos_nacer SET "
					+"gesta='"+gesta+"', peso='"+peso+"', talla='"+talla+"', tipo_parto='"+tipo_parto+"', "
					+"causas='"+causas+"', enfermedades_embarazo='"+enfermedades_embarazo+"', complicaciones='"+complicaciones+"', "
					+"lactancia='"+lactancia+"', tetero='"+tetero+"', ablactacion='"+ablactacion+"', condiciones= '"+condiciones+"' "
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
			sql = "SELECT htra_id, gesta, peso, talla, tipo_parto, "
					+"causas, enfermedades_embarazo, complicaciones, "
					+"lactancia, tetero, ablactacion "
					+"FROM datos_nacer WHERE htra_id="+id;
			
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					htra_id = Integer.parseInt(res.getString("htra_id").toString());
					gesta = res.getString("gesta").toString();
					peso = res.getString("peso").toString();
					talla = res.getString("talla").toString();
					tipo_parto = res.getString("tipo_parto").toString();
					causas = res.getString("causas").toString();
					enfermedades_embarazo = res.getString("enfermedades_embarazo").toString();
					complicaciones = res.getString("complicaciones").toString();
					lactancia = res.getString("lactancia").toString();
					tetero = res.getString("tetero").toString();
					ablactacion = res.getString("ablactacion").toString();
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
	
	public String getAblactacion() {
		return ablactacion;
	}

	public void setAblactacion(String param) {
		this.ablactacion = param;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String param) {
		this.causas = param;
	}

	public String getComplicaciones() {
		return complicaciones;
	}

	public void setComplicaciones(String param) {
		this.complicaciones = param;
	}

	public String getEnfermedades_embarazo() {
		return enfermedades_embarazo;
	}

	public void setEnfermedades_embarazo(String param) {
		this.enfermedades_embarazo = param;
	}

	public String getGesta() {
		return gesta;
	}

	public void setGesta(String param) {
		this.gesta = param;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int param) {
		this.htra_id = param;
	}

	public String getLactancia() {
		return lactancia;
	}

	public void setLactancia(String param) {
		this.lactancia = param;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String param) {
		this.peso = param;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String param) {
		this.talla = param;
	}

	public String getTetero() {
		return tetero;
	}

	public void setTetero(String param) {
		this.tetero = param;
	}

	public String getTipo_parto() {
		return tipo_parto;
	}

	public void setTipo_parto(String param) {
		this.tipo_parto = param;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	
	
}
