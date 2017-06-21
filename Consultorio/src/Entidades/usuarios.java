package Entidades;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Encriptar;

public class usuarios {
	private String cedula;
	private String nombre;
	private String usuario;
	private String clave;
	private String valido;
	private int rol_id;	
	private String mdco_cedula;
	
	private Autenticacion autenticacion;
	
	public usuarios(Autenticacion a){
		autenticacion = a;
		cedula = "";
		nombre = "";
		usuario = "";
		clave = "";
		valido = "n";
		rol_id = 3;
		mdco_cedula = "";
	}
	
	public String buscarUsuario(String ced, String usu){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT cedula, nombre, usuario, clave, valido, rol_id, mdco_cedula "
					+"FROM usuarios "
					+"WHERE LOWER(cedula) = '"+ced.toLowerCase()+"' AND LOWER(usuario) = '"+usu.toLowerCase()+"'";
			
			//System.out.println(sql);
			
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					cedula = res.getString("cedula");
					nombre = res.getString("nombre");
					usuario = res.getString("usuario");
					clave = res.getString("clave");
					valido = res.getString("valido");
					rol_id = Integer.parseInt(res.getString("rol_id"));
					mdco_cedula = res.getString("mdco_cedula");
				}
				else{
					return "No se encontro el usuario";
				}
			}
			catch(Exception e){
				return "No se encontro el usuario";
			}
			con.desconectar();
		}
		else{
			return "No se pudo conectar con el servidor";
		}
		
		return "Ok";
	}

	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO usuarios "
					+"(cedula, nombre, usuario, clave, valido, rol_id, mdco_cedula) "
					+"VALUES('"+cedula+"', '"+nombre+"', '"+usuario.toLowerCase()+"', '"+clave+"', "
					+"'"+valido+"', "+rol_id+", '"+mdco_cedula+"')";
			
			if(con.actualizar(sql)){				
				con.desconectar();
				return "Ok";
			}
			else{
				con.desconectar();
				return "No se pudo guardar el usuario";
			}			
		}
		else{
			return "No se pudo conectar con el servidor";
		}
	}
	
	public String actualizar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "UPDATE usuarios SET "
					+"nombre='"+nombre+"', usuario='"+usuario+"', clave='"+clave+"', valido='"+valido+"', "
					+"rol_id="+rol_id+", mdco_cedula='"+mdco_cedula+"' "
					+"WHERE cedula='"+cedula+"' AND usuario='"+usuario+"'";
			
			if(con.actualizar(sql)){
				con.desconectar();
				return "Ok";
			}
			else{
				con.desconectar();
				return "No se pudo guardar el usuario";
			}			
		}
		else{
			return "No se pudo conectar con el servidor";
		}
	}
	
	public boolean validarUsuario(String usu){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT COUNT(*) "
					+"FROM usuarios "
					+"WHERE LOWER(usuario) = '"+usu.toLowerCase()+"'";
			java.sql.ResultSet res = con.consultar(sql);
			try{
				if(res.next()){
					int cont = Integer.parseInt(res.getString(1));
					if(cont == 0){
						return true;
					}
					else{
						return false;
					}
				}
			}
			catch(Exception e){}
		}
		return false;
	}
	
	public Autenticacion getAutenticacion() {
		return autenticacion;
	}

	public void setAutenticacion(Autenticacion autenticacion) {
		this.autenticacion = autenticacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = Encriptar.encryptMD5(clave);
	}

	public String getMdco_cedula() {
		return mdco_cedula;
	}

	public void setMdco_cedula(String mdco_cedula) {
		this.mdco_cedula = mdco_cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValido() {
		return valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}	
}
