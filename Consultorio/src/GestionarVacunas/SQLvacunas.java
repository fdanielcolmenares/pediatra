package GestionarVacunas;

import java.sql.ResultSet;

import javax.swing.JComboBox;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class SQLvacunas {

	private Autenticacion autenticacion;
	private Conexion conexion;
	private ResultSet resultSet;
	private String query;
	
		public SQLvacunas(Autenticacion autenticacion){
			this.autenticacion = autenticacion;
		}
		
		public void getVacunas(JComboBox box){
			conexion  = new Conexion(autenticacion);
				conexion.conectar();
				query = "SELECT nombre FROM vacunas WHERE 1 ORDER BY nombre ASC";
			
				try {
					resultSet = conexion.consultar(query);
					
					while(resultSet.next()){
						box.addItem(resultSet.getString(1));
					}
					conexion.desconectar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
						}
		
		
}
