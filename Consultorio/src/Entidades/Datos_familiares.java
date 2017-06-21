package Entidades;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class Datos_familiares {
	private int htra_id;
	private String asma;
	private String rinitis;
	private String dermatitis;
	private String diabetes;
	private String tiroides;
	private String hipertension;
	private String prurito;
	private String cancer;
	private String cardiopatias;
	private String otras;
	private String urticaria;
	private String observaciones; 
	
	private Autenticacion autenticacion;
	
	public Datos_familiares(Autenticacion a){
		autenticacion = a;
		
		asma = "";
		rinitis= "";
		dermatitis= "";
		diabetes= "";
		tiroides= "";
		hipertension= "";
		prurito= "";
		cancer= "";
		cardiopatias= "";
		otras = "";
		urticaria = "";
		observaciones = "";
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO datos_familiares"
					+"(htra_id, asma, rinitis, dermatitis, diabetes, tiroides, "
					+"hipertension, prurito, cancer ,cardiopatias, otras, urticaria, observaciones) "
					+ "VALUES ("+htra_id+", '"+asma+"', '"+rinitis+"', '"+dermatitis+"', '"+diabetes+"', '"+tiroides+"', "
					+ "'"+hipertension+"', '"+prurito+"', '"+cancer+"', '"+cardiopatias+"', '"+otras+"', '"+urticaria+"', '"+observaciones+"')";
				
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
			sql = "UPDATE datos_familiares SET "
					+"asma='"+asma+"', rinitis='"+rinitis+"', dermatitis='"+dermatitis+"', diabetes='"+diabetes+"', "
					+"tiroides='"+tiroides+"', hipertension='"+hipertension+"', prurito='"+prurito+"', cancer='"+cancer+"', "
					+"cardiopatias='"+cardiopatias+"', otras='"+otras+"', urticaria='"+urticaria+"', observaciones='"+observaciones+"' "
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
			sql = "SELECT htra_id, asma, rinitis, dermatitis, diabetes, "
					+"tiroides, hipertension, prurito, cancer, cardiopatias, otras, urticaria, observaciones "
					+"FROM datos_familiares WHERE htra_id="+id;
			
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					htra_id = Integer.parseInt(res.getString("htra_id").toString());
					asma = res.getString("asma").toString();
					rinitis = res.getString("rinitis").toString();
					dermatitis = res.getString("dermatitis").toString();
					diabetes = res.getString("diabetes").toString();
					tiroides = res.getString("tiroides").toString();
					hipertension = res.getString("hipertension").toString();
					prurito = res.getString("prurito").toString();
					cancer = res.getString("cancer").toString();
					cardiopatias = res.getString("cardiopatias").toString();
					otras = res.getString("otras").toString();
					urticaria = res.getString("urticaria").toString();
					observaciones = res.getString("observaciones").toString();
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
	
	public String getAsma() {
		return asma;
	}

	public void setAsma(String param) {
		this.asma = param;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String param) {
		this.cancer = param;
	}

	public String getCardiopatias() {
		return cardiopatias;
	}

	public void setCardiopatias(String param) {
		this.cardiopatias = param;
	}

	public String getDermatitis() {
		return dermatitis;
	}

	public void setDermatitis(String param) {
		this.dermatitis = param;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String param) {
		this.diabetes = param;
	}

	public String getHipertension() {
		return hipertension;
	}

	public void setHipertension(String param) {
		this.hipertension = param;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int param) {
		this.htra_id = param;
	}

	public String getOtras() {
		return otras;
	}

	public void setOtras(String param) {
		this.otras = param;
	}

	public String getPrurito() {
		return prurito;
	}

	public void setPrurito(String param) {
		this.prurito = param;
	}

	public String getRinitis() {
		return rinitis;
	}

	public void setRinitis(String param) {
		this.rinitis = param;
	}

	public String getTiroides() {
		return tiroides;
	}

	public void setTiroides(String param) {
		this.tiroides = param;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getUrticaria() {
		return urticaria;
	}

	public void setUrticaria(String urticaria) {
		this.urticaria = urticaria;
	}
}
