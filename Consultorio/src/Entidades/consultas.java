package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class consultas {
	
	private int id;
	private String fecha;
	private String peso;
	private String talla;
	private String motivo;
	private String diagnostico;
	private String observaciones;
	private int estado;
	private String saturacion;
	private String masa;
	private String hora;// agregado a la base de datos nuevo
	private int htra_id;
	private int tcta_id;
	private String temperatura;
	private String fc;
	private String fr;
	private String tas;
	private String tad;
	private String pf;
	
	private Autenticacion autenticar;
	
	public consultas(Autenticacion a){
		autenticar = a;
		id = 0;
		fecha = "";
		peso = "";
		talla = "";
		motivo = "";
		diagnostico = "";
		observaciones = "";
		masa = "";
		saturacion = "";
		estado = 0;
		hora = "";
		htra_id = 0;
		tcta_id = 0;
		temperatura = "";
		fc = "";
		fr = "";
		tas = "";
		tad = "";
		pf = "";
	}
	
	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getSaturacion() {
		return saturacion;
	}	

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setSaturacion(String saturacion) {
		this.saturacion = saturacion;
	}

	public String getMasa() {
		return masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from consultas), 1)";
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
	
	public String guardar(){
		String sql;
		try{
			java.util.Calendar c = GregorianCalendar.getInstance();
			int hh=c.get(GregorianCalendar.HOUR_OF_DAY);
			int mm=c.get(GregorianCalendar.MINUTE);
			int ss=c.get(GregorianCalendar.SECOND);
			hora = String.valueOf(hh+":"+mm+":"+ss);
		}
		catch(Exception e){}
		setFecha(UtilFechas.convertirFecha(new Date(), UtilFechas.YYYY_MM_DD));
		sql = "insert into consultas (id,fecha,peso,talla,motivo," +
			"diagnostico,observaciones,estado,masa,saturacion,hora,htra_id,tcta_id, temperatura, fc, fr, tas, tad, pf) " +
			"values ("+id+", '"+fecha+"','"+peso+"','"+talla+"','"+motivo+"','"+diagnostico+"',"+
			"'"+observaciones+"',"+estado+",'"+masa+"','"+saturacion+"','"+UtilFechas.getHoraActual()+"',"+htra_id+","+tcta_id+", "
			+"'"+temperatura+"', '"+fc+"', '"+fr+"', '"+tas+"', '"+tad+"', '"+pf+"')";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos");
			return "FALLO";
		}
		con.desconectar();
		return "OK";
	}
	
	public String actualiza(){
		String sql;
		sql = "UPDATE consultas SET peso='"+peso+"', talla='"+talla+"', motivo='"+motivo+"', " +
				"diagnostico='"+diagnostico+"', observaciones='"+observaciones+"', " +
				"estado='"+estado+"', masa='"+masa+"', saturacion='"+saturacion+"', " +
				"temperatura='"+temperatura+"', fc='"+fc+"', fr='"+fr+"', tas='"+tas+"', tad='"+tad+"'," +
				" pf='"+pf+"' WHERE id='"+id+"'";
		Conexion con = new Conexion(autenticar);
		//System.out.println(sql);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos");
			return "FALLO";
		}
		con.desconectar();
		return "OK";
	}
	
	public String primera(String numhis){
		String sql;
		sql = "select id, fecha, peso, talla, motivo," +
				"diagnostico, observaciones, estado, masa," +
				"saturacion, hora, htra_id, tcta_id, temperatura, fc, fr, tas, tad, pf "
				+"from consultas where " +
				"htra_id = "+numhis+" order by fecha, hora";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase consultas, metodo primera");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();
				setFecha("Fecha: "+rs.getDate("fecha").toString());
				setPeso(rs.getString("peso"));
				setTalla(rs.getString("talla"));
				setMotivo(rs.getString("motivo"));
				setDiagnostico(rs.getString("diagnostico"));
				setObservaciones(rs.getString("observaciones"));
				setEstado(rs.getInt("estado"));
				setMasa(rs.getString("masa"));
				setSaturacion(rs.getString("saturacion"));
				setHtra_id(rs.getInt("htra_id"));
				setTcta_id(rs.getInt("tcta_id"));
				setHora(rs.getString("hora"));
				setId(Integer.parseInt(rs.getString("id")));
				setTemperatura(rs.getString("temperatura"));
				setFc(rs.getString("fc"));
				setFr(rs.getString("fr"));
				setTas(rs.getString("tas"));
				setTad(rs.getString("tad"));
				setPf(rs.getString("pf"));
			} catch (SQLException e) {
				setMotivo("No disponible");
				setDiagnostico("No disponible");
				setFecha("No disponible");
				//System.out.println(sql);
				return "FALLO";
			}
		}
		con.desconectar();
		return "OK";
	}
	
	public String ultima(String numhis){
		String sql;
		sql = "select id, fecha, peso, talla, motivo," +
				"diagnostico, observaciones, estado, masa," +
				" saturacion, hora, htra_id, tcta_id, temperatura, fc, fr, tas, tad, pf "
				+"from consultas where " +
				"htra_id = "+numhis+" order by fecha desc, hora desc";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase consultas, metodo ultima");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();					
				//System.out.println("esto: "+rs.getString("motivo"));
				setFecha("Fecha: "+rs.getDate("fecha").toString());
				setPeso(rs.getString("peso"));
				setTalla(rs.getString("talla"));
				setMotivo(rs.getString("motivo"));
				setDiagnostico(rs.getString("diagnostico"));
				setObservaciones(rs.getString("observaciones"));
				setEstado(rs.getInt("estado"));
				setMasa(rs.getString("masa"));
				setSaturacion(rs.getString("saturacion"));
				setHora(rs.getString("hora"));
				setId(Integer.parseInt(rs.getString("id")));
				setHtra_id(rs.getInt("htra_id"));
				setTcta_id(rs.getInt("tcta_id"));
				setTemperatura(rs.getString("temperatura"));
				setFc(rs.getString("fc"));
				setFr(rs.getString("fr"));
				setTas(rs.getString("tas"));
				setTad(rs.getString("tad"));
				setPf(rs.getString("pf"));
			} catch (SQLException e) {
				setMotivo("No disponible");
				setDiagnostico("No disponible");
				setFecha("No disponible");
				//System.out.println(sql);
				return "FALLO";
			}
		}
		con.desconectar();
		return "OK";
	}
	
	public ResultSet buscarlista(String numhis){
		ResultSet res;
		String sql;
		sql="select id, fecha, peso, talla, motivo," +
		"diagnostico, observaciones, estado, masa," +
		"saturacion, hora, htra_id, tcta_id from consultas where " +
		"htra_id = "+numhis+" ORDER BY id DESC";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase consultas, metodo buscarlista");
			System.out.println(sql);
		}
		return res;
	}
	
	public String buscar(String numhis, String fecha, String hora){
		String sql;
		sql = "select id, fecha, peso, talla, motivo," +
				"diagnostico, observaciones, estado, masa," +
				"saturacion, hora, htra_id, tcta_id, temperatura, fc, fr, tas, tad, pf "
				+"from consultas where " +
				"htra_id = "+numhis+" and fecha = '"+fecha+"' and hora = '"+hora+"'";
		
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		
		ResultSet rs = con.consultar(sql);
		if(rs==null){ 
			System.out.println("error sql. clase consultas, metodo guadar");
			System.out.println(sql);
		}
		else{
			try {
				rs.next();
				setFecha("Fecha: "+rs.getDate("fecha").toString());
				setPeso(rs.getString("peso"));
				setTalla(rs.getString("talla"));
				setMotivo(rs.getString("motivo"));
				setDiagnostico(rs.getString("diagnostico"));
				setObservaciones(rs.getString("observaciones"));
				setEstado(rs.getInt("estado"));
				setMasa(rs.getString("masa"));
				setSaturacion(rs.getString("saturacion"));
				setHtra_id(rs.getInt("htra_id"));
				setTcta_id(rs.getInt("tcta_id"));
				setId(Integer.parseInt(rs.getString("id")));
				setTemperatura(rs.getString("temperatura"));
				setFc(rs.getString("fc"));
				setFr(rs.getString("fr"));
				setTas(rs.getString("tas"));
				setTad(rs.getString("tad"));
				setPf(rs.getString("pf"));
			} catch (SQLException e) {
				setMotivo("No disponible");
				setDiagnostico("No disponible");
				setFecha("No disponible");
				setPeso("No disponible");
				setTalla("No disponible");
				setObservaciones("No disponible");
				setMasa("No disponible");
				setSaturacion("No disponible");
				System.out.println(sql);
				//setId(0);
				//System.out.println(sql);
				return "FALLO";
			}
		}
		con.desconectar();
		return "OK";
	}
	
	public boolean existe(int numhis){
		ResultSet res = null;
		String sql = "select id, htra_id" +
				" from consultas where htra_id="+numhis+"";//System.out.println("sql: "+sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar consultas existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase consultas, metodo existe");
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
	
	public int estado(int numhis){
		ResultSet res = null;
		String sql = "select id, htra_id, estado" +
				" from consultas where htra_id="+numhis+"";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar consultas existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase consultas, metodo existe");
			System.out.println(sql);
		}
		
		try{
			res.next();
			return res.getInt("estado");
		}catch (Exception e) {
			con.desconectar();
			return -1;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int htra_id) {
		this.htra_id = htra_id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getTcta_id() {
		return tcta_id;
	}

	public void setTcta_id(int tcta_id) {
		this.tcta_id = tcta_id;
	}

	public void setAutenticacion(Autenticacion param){
		autenticar = param;
	}
	
	public void setFecha(String f){
		fecha = f;
	}
	
	public String getFecha(){
		return fecha;
	}
	
	public void setPeso(String p){
		peso = p;
	}
	
	public String getPeso(){
		return peso;
	}
	
	public void setTalla(String t){
		talla = t;
	}
	
	public String getTalla(){
		return talla;
	}
	
	public void setMotivo(String m){
		motivo = m;
	}
	
	public String getMotivo(){
		return motivo;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getTad() {
		return tad;
	}

	public void setTad(String tad) {
		this.tad = tad;
	}

	public String getTas() {
		return tas;
	}

	public void setTas(String tas) {
		this.tas = tas;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

}
