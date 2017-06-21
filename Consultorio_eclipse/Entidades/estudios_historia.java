package Entidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class estudios_historia {
	
	private int id;
	private String fecha;
	private String observacion;
	private int htra_id;
	private int etdo_id;
	private String imagen;
	private Autenticacion autenticar;
	
	public estudios_historia(Autenticacion a){
		autenticar = a;
		id = 0;
		fecha = "";
		observacion = "";
		htra_id = 0;
		etdo_id = 0;
		imagen = "";
	}	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(int htra_id) {
		this.htra_id = htra_id;
	}

	public int getEtdo_id() {
		return etdo_id;
	}

	public void setEtdo_id(int etdo_id) {
		this.etdo_id = etdo_id;
	}

	public Autenticacion getAutenticar() {
		return autenticar;
	}

	public void setAutenticar(Autenticacion autenticar) {
		this.autenticar = autenticar;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from estudios_historia), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("estudios_historia: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("estudios_historia: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		/*String sql="insert into estudios_historia (id,fecha,observacion,htra_id,etdo_id)" +
				" values ("+id+",'"+fecha+"','"+observacion+"',"+htra_id+","+etdo_id+")";*/
		//Blob ima = 
		Conexion con = new Conexion(autenticar);
		
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios_historia");

		try {
			File fichero = new File(imagen);
	        FileInputStream streamEntrada = null;
			try {
				streamEntrada = new FileInputStream(fichero);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
	        PreparedStatement pstmt;
			pstmt = con.getConexion().prepareStatement("insert into estudios_historia " +
					"(id,fecha,observacion,htra_id,etdo_id,imagen) values (?,'"
					+UtilFechas.convertirFecha(new Date(), UtilFechas.YYYY_MM_DD)+"',?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2,observacion);
			pstmt.setInt(3, htra_id);
			pstmt.setInt(4, etdo_id);
		    pstmt.setBinaryStream(5,streamEntrada,(int)fichero.length());
		    pstmt.executeUpdate();
		    pstmt.close();
		    try {
				streamEntrada.close();
			} catch (IOException e) {
				//e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}   
		con.desconectar();
		return true;//true OK
	}
	
	public int contar(){
		ResultSet res = null;
		String sql = "select COUNT(*) as numero FROM estudios_historia";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios_historia existe");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudios_historia, metodo existe");
			System.out.println(sql);
		}
		
		try{
			res.next();
			return res.getInt("numero");
		}catch (Exception e) {
			con.desconectar();
			return 0;
		}
	}
	
	public ResultSet cargartipos(int et, int his){
		ResultSet res = null;
		String sql;
		
		sql = "select id,fecha,observacion,htra_id,etdo_id,imagen from estudios_historia " +
				"where htra_id = "+his+" and etdo_id = "+et+"";
		//System.out.println(sql);
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar estudios cargartipos");
		
		res = con.consultar(sql);
		if(res==null){ 
			System.out.println("error sql. clase estudiosestudios_historia, metodo cargartipos");
			System.out.println(sql);
		}
		//con.desconectar();
		return res;
	}

}
