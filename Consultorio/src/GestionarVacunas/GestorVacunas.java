package GestionarVacunas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class GestorVacunas {
	
	private int iD_HisToria;	
	private List Vacun= new ArrayList();
	private Autenticacion a;
	private Conexion conexion;
	private ResultSet resultSet;
	private Vacunas v;

	public GestorVacunas(int ID, Autenticacion a){
			this.iD_HisToria = ID;			
			this.a = a;
			LoadData();
	}

	public int getLongList(){
			return Vacun.size();
	}
	
		
	public void LoadData(){
		conexion = new Conexion(a);
		///		String query = "SELECT * FROM `vacunas_historia` WHERE htra_id = "+iD_HisToria+" order by vcna_id ASC";
		String query = "SELECT id,nombre FROM vacunas where id IN (SELECT DISTINCT vcna_id FROM vacunas_historia where htra_id="+iD_HisToria+")";
		///SELECT id,nombre FROM vacunas where id IN (SELECT DISTINCT vcna_id FROM vacunas_historia where htra_id=440)
		
		conexion.conectar();
		resultSet = conexion.consultar(query);
		
		try {
			while(resultSet.next()){			 
				v = new Vacunas(a);
				v.setIDHistoria(iD_HisToria);
				v.setIDVacuna(Integer.parseInt(resultSet.getString(1)));
				v.setNameVacuna(resultSet.getString(2));
				v.setFech(v.getFechas(v.getIDVacuna(),iD_HisToria));
				Vacun.add(v);
			}
		conexion.desconectar();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		
	}
	
	public Vacunas getVacunas(int indice){
		Vacunas obj = (Vacunas)Vacun.get(indice);
		return obj;
	}
	
}
