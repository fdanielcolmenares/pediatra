package Entidades;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class items {
	private int id;
	private int cantidad;
	private float precio;
	private String detalles;
	private int ccto_id;
	private int ftra_id;
	private conceptos concepto;
	private Autenticacion autenticacion;
	
	public items(Autenticacion a) {
		autenticacion = a;
		concepto = new conceptos(autenticacion);
	}
	
	public String guardar(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "INSERT INTO items "
					+"(id, cantidad, precio, detalles, ccto_id, ftra_id) VALUES "
					+"("+id+", "+cantidad+", "+precio+", '"+detalles+"', "+ccto_id+", "+ftra_id+")";
			
			//System.out.println(sql);
			
			if(con.actualizar(sql)){
				con.desconectar();
				return "OK";
			}
			else{
				con.desconectar();
				return "No se pudo guardar el item";
			}
		}
		else{
			return "No se pudo conectar al servidor";
		}
	}
	
	public List buscarItemsFactura(int idFtra){
		List lst = new ArrayList();
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT i.id AS id, i.cantidad aS cantidad, i.precio AS precio, i.detalles AS detalles, i.ccto_id AS ccto_id, c.descripcion AS concepto "
					+"FROM items i, conceptos c "
					+"WHERE i.ccto_id=c.id AND  ftra_id ="+idFtra;
			java.sql.ResultSet res = con.consultar(sql);
			try{
				while(res.next()){
					items itm = new items(autenticacion);
					itm.setId(Integer.parseInt(res.getString("id").toString()));
					itm.setCantidad(Integer.parseInt(res.getString("cantidad").toString()));
					itm.setPrecio(Float.parseFloat(res.getString("precio").toString()));
					itm.setDetalles(res.getString("detalles").toString());
					itm.setCcto_id(Integer.parseInt(res.getString("ccto_id").toString()));
					itm.getConcepto().setId(itm.getCcto_id());
					itm.getConcepto().setDescripcion(res.getString("descripcion").toString());
					lst.add(lst);
				}
			}
			catch(Exception e){
				con.desconectar();
				return null;
			}
			con.desconectar();
		}
		else{
			return null;
		}
		return lst;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(ID)+1 from items), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("items: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("items: No se pudo conectar a la BD");
		}
	}
	

	public conceptos getConcepto() {
		return concepto;
	}

	public void setConcepto(conceptos concepto) {
		this.concepto = concepto;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCcto_id() {
		return ccto_id;
	}

	public void setCcto_id(int ccto_id) {
		this.ccto_id = ccto_id;
	}

	public int getFtra_id() {
		return ftra_id;
	}

	public void setFtra_id(int ftra_id) {
		this.ftra_id = ftra_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}		
}
