package Entidades;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class Datos_antecedentes {
	private int htra_id;
	private String sintomas_respiratorios;
	private String primera_crisis;
	private String ultima_crisis;
	private String sintomas_intercrisis;
	private String frecuencia;
	private String respiracion_oral;
	private String ronca;
	private String burxismo;
	private String antecedentes_eruptivos;
	private String antecedentes_gi;
	private String antecedentes_gu;
	private String antecedentes_otros;
	private String otras_alergias;
	
	private Autenticacion autenticacion;
	
	public Datos_antecedentes(Autenticacion a){
		autenticacion = a;
		
		sintomas_respiratorios = "";
		primera_crisis = "";
		ultima_crisis = "";
		sintomas_intercrisis = "";
		frecuencia = "0";
		respiracion_oral = "";
		ronca = "";
		burxismo = "";
		antecedentes_eruptivos = "";
		antecedentes_gi = "";
		antecedentes_gu = "";
		antecedentes_otros = "";
		otras_alergias = "";
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO datos_antecedentes "
					+ "(htra_id, sintomas_respiratorios, primera_crisis, ultima_crisis, sintomas_intercrisis, frecuencia, "
					+ "respiracion_oral, ronca, burxismo, antecedentes_eruptivos, antecedentes_gi, antecedentes_gu, antecedentes_otros, otras_alergias) "
					+ "VALUES ("+htra_id+", '"+sintomas_respiratorios+"', '"+primera_crisis+"', '"+ultima_crisis+"', '"+sintomas_intercrisis+"', '"+frecuencia+"', "
					+ "'"+respiracion_oral+"', '"+ronca+"', '"+burxismo+"', '"+antecedentes_eruptivos+"', '"+antecedentes_gi+"', '"+antecedentes_gu+"', '"+antecedentes_otros+"', '"+otras_alergias+"')";
			
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
			sql = "UPDATE datos_antecedentes SET "
					+"sintomas_respiratorios='"+sintomas_respiratorios+"', primera_crisis='"+primera_crisis+"', ultima_crisis='"+ultima_crisis+"', "
					+"sintomas_intercrisis='"+sintomas_intercrisis+"', frecuencia='"+frecuencia+"', respiracion_oral='"+respiracion_oral+"', ronca='"+ronca+"', "
					+"burxismo='"+burxismo+"', antecedentes_eruptivos='"+antecedentes_eruptivos+"', antecedentes_gi='"+antecedentes_gi+"', "
					+"antecedentes_gu='"+antecedentes_gu+"', antecedentes_otros='"+antecedentes_otros+"', otras_alergias='"+otras_alergias+"' "
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
			sql = "SELECT htra_id, sintomas_respiratorios, primera_crisis, "
					+"ultima_crisis, sintomas_intercrisis, frecuencia, "
					+"respiracion_oral, ronca, burxismo, antecedentes_eruptivos, "
					+"antecedentes_gi, antecedentes_gu, antecedentes_otros, otras_alergias "
					+"FROM datos_antecedentes WHERE htra_id="+id;
			
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					htra_id = Integer.parseInt(res.getString("htra_id").toString());
					sintomas_respiratorios = res.getString("sintomas_respiratorios").toString();
					primera_crisis = res.getString("primera_crisis").toString();
					ultima_crisis = res.getString("ultima_crisis").toString();
					sintomas_intercrisis = res.getString("sintomas_intercrisis").toString();
					frecuencia = res.getString("frecuencia").toString();
					respiracion_oral = res.getString("respiracion_oral").toString();
					ronca = res.getString("ronca").toString();
					burxismo = res.getString("burxismo").toString();
					antecedentes_eruptivos = res.getString("antecedentes_eruptivos").toString();
					antecedentes_gi = res.getString("antecedentes_gi").toString();
					antecedentes_gu = res.getString("antecedentes_gu").toString();
					antecedentes_otros = res.getString("antecedentes_otros").toString();
					otras_alergias = res.getString("otras_alergias").toString();
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

	public String getAntecedentes_gi() {
		return antecedentes_gi;
	}

	public void setAntecedentes_gi(String param) {
		this.antecedentes_gi = param;
	}

	public String getAntecedentes_eruptivos() {
		return antecedentes_eruptivos;
	}

	public void setAntecedentes_eruptivos(String param) {
		this.antecedentes_eruptivos = param;
	}

	public String getAntecedentes_gu() {
		return antecedentes_gu;
	}

	public void setAntecedentes_gu(String param) {
		this.antecedentes_gu = param;
	}

	public String getAntecedentes_otros() {
		return antecedentes_otros;
	}

	public void setAntecedentes_otros(String param) {
		this.antecedentes_otros = param;
	}

	public String getBurxismo() {
		return burxismo;
	}

	public void setBurxismo(String param) {
		this.burxismo = param;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String param) {
		this.frecuencia = param;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int param) {
		this.htra_id = param;
	}

	public String getPrimera_crisis() {
		return primera_crisis;
	}

	public void setPrimera_crisis(String param) {
		this.primera_crisis = param;
	}

	public String getRespiracion_oral() {
		return respiracion_oral;
	}

	public void setRespiracion_oral(String param) {
		this.respiracion_oral = param;
	}

	public String getRonca() {
		return ronca;
	}

	public void setRonca(String param) {
		this.ronca = param;
	}

	public String getSintomas_intercrisis() {
		return sintomas_intercrisis;
	}

	public void setSintomas_intercrisis(String param) {
		this.sintomas_intercrisis = param;
	}

	public String getSintomas_respiratorios() {
		return sintomas_respiratorios;
	}

	public void setSintomas_respiratorios(String param) {
		this.sintomas_respiratorios = param;
	}

	public String getUltima_crisis() {
		return ultima_crisis;
	}

	public void setUltima_crisis(String param) {
		this.ultima_crisis = param;
	}

	public String getOtras_alergias() {
		return otras_alergias;
	}

	public void setOtras_alergias(String otras_alergias) {
		this.otras_alergias = otras_alergias;
	}	
}
