package Entidades;

import java.sql.ResultSet;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class Historias {
	private int id;
	private String nombre;
	private String fecha_nacimiento;
	private String fecha_creacion;
	private String usro_cedula;
	private String mdco_cedula;
	
	private Datos_antecedentes antecedentes;
	private Datos_familiares familiares;
	private Datos_nacer nacimiento;
	private Datos_padres padres;
	
	private Autenticacion autenticacion;
	
	public Historias(Autenticacion a){
		autenticacion = a;
		
		nombre= "";
		fecha_nacimiento= "";
		fecha_creacion = "";
		usro_cedula= "";
		mdco_cedula= "";
		
		padres = new Datos_padres(autenticacion);
		nacimiento = new Datos_nacer(autenticacion);
		antecedentes = new Datos_antecedentes(autenticacion);
		familiares = new Datos_familiares(autenticacion);				
	}
 
	public String buscarHistoriaNumero(int idHtra, boolean cascada){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT nombre, fecha_nacimiento, fecha_creacion, usro_cedula, mdco_cedula "
					+"FROM historias " 
					+"WHERE id ="+idHtra;
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					id = idHtra;
					nombre = res.getString("nombre").toString();
					
					fecha_nacimiento = res.getString("fecha_nacimiento").toString();
					fecha_nacimiento = UtilFechas.convertirFecha(fecha_nacimiento, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
					
					fecha_creacion = res.getString("fecha_creacion").toString();
					fecha_creacion = UtilFechas.convertirFecha(fecha_creacion, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
										
					usro_cedula = res.getString("usro_cedula").toString();
					mdco_cedula = res.getString("mdco_cedula").toString();
					
					if(cascada){
						padres.buscarHistoriaNumero(id);
						nacimiento.buscarHistoriaNumero(id);
						antecedentes.buscarHistoriaNumero(id);
						familiares.buscarHistoriaNumero(id);
					}
				}
				else{
					con.desconectar();
					return "No se encontro la historia";
				}
			}
			catch(Exception e){
				con.desconectar();
				return "No se pudo conectar";
			}
			con.desconectar();
		}
		else{
			return "No se pudo conectar con el servidor";
		}
		return "Ok";
	}	
	public String buscarNuevoID(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from historias where mdco_cedula='"+autenticacion.getCedulaMedico()+"'), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
					String idSt = "000000"+id;
					idSt = idSt.substring(idSt.length()-6, idSt.length());
					return String.valueOf(idSt);
				}
			}
			catch(Exception ex){
				System.out.println("Historias: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("Historias: No se pudo conectar a la BD");
		}
		return "";
	}
	
	public String guardar(){
		String valida = validar();
		if(valida.compareToIgnoreCase("Ok")==0){
			Conexion con = new Conexion(autenticacion);
			if(con.conectar()){
				String sql;
				sql = "INSERT INTO historias "
						+ "(id, nombre, fecha_nacimiento, fecha_creacion ,usro_cedula, mdco_cedula) "
						+ "VALUES ("+id+", '"+nombre+"', '"+fecha_nacimiento+"', curdate(), "+usro_cedula+", "+mdco_cedula+")";
				
				
				//System.out.println(sql);
				
				if(con.actualizar(sql)){
					padres.guardar();
					nacimiento.guardar();
					familiares.guardar();
					antecedentes.guardar();
					con.desconectar();
					return "Ok";
				}
				else{
					con.desconectar();
					return "Historia: No se pudo insertar";
				}
				
			}
			else{
				System.out.println("Historia: No se pudo conectar a la BD");
			}
		}
		return valida; 
	}
	
	public String validar(){
		if(nombre.length() == 0){
			return "Debe ingresar el nombre del paciente";
		}
		if(fecha_nacimiento.length() == 0){
			return "Debe ingresar la fecha de nacimiento";
		}
		return "Ok";
	}
	
	public void setAutenticacion(Autenticacion param){
		autenticacion = param;
		
		antecedentes.setAutenticacion(param);
		familiares.setAutenticacion(param);
		nacimiento.setAutenticacion(param);
		padres.setAutenticacion(param);
	}
	
	public String actualizar(){
		String valida = validar();
		if(valida.compareToIgnoreCase("Ok")==0){
			Conexion con = new Conexion(autenticacion);
			if(con.conectar()){
				String sql;
				sql = "UPDATE historias SET "
						+ "nombre='"+nombre+"', fecha_nacimiento='"+fecha_nacimiento+"' "
						+"WHERE id="+id;
				
				//System.out.println(sql);
				
				con.actualizar(sql);
				
				padres.actualizar();
				nacimiento.actualizar();
				familiares.actualizar();
				antecedentes.actualizar();
				
				con.desconectar();
			}
		}
		
		return valida;
	}

		
	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String param) {
		this.fecha_nacimiento = param;
	}

	public int getId() {
		return id;
	}

	public void setId(int param) {
		this.id = param;
	}

	public String getMdco_cedula() {
		return mdco_cedula;
	}

	public void setMdco_cedula(String param) {
		this.mdco_cedula = param;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}	
	
	public String getUsro_cedula() {
		return usro_cedula;
	}

	public void setUsro_cedula(String param) {
		this.usro_cedula = param;
	}
	
	public void setDatosPadres(Datos_padres param){
		padres = param;
	}
	
	public Datos_padres getDatosPadres(){
		return padres;
	}
	
	public void setDatosFamiliares(Datos_familiares param){
		familiares = param;
	}
	
	public Datos_familiares getDatosFamiliares(){
		return familiares;
	}
	
	public void setDatosNacimiento(Datos_nacer param){
		nacimiento = param;
	}
	
	public Datos_nacer getDatosNacimiento(){
		return nacimiento;
	}
	
	public void setAntecedentes(Datos_antecedentes param){
		antecedentes = param;
	}
	
	public Datos_antecedentes getAntecedentes(){
		return antecedentes;
	}
}
