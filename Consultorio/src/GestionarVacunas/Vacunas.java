package GestionarVacunas;

import java.sql.ResultSet;

import Utilitario.Autenticacion;

import ConexionBD.Conexion;

public class Vacunas {
		private int IDHistoria;
		private int IDVacuna;
		private String nameVacuna;
		private Object[][] fech;
		private Conexion conexion;
		private Autenticacion autenticacion;
		private int cantidad_v;
		
		public Vacunas(Autenticacion a){
			this.autenticacion = a;
		}
		
		public Object[][] getFech() {
			return fech;
		}
		public void setFech(Object[][] fech) {
			this.fech = fech;
		}
		public int getIDHistoria() {
			return IDHistoria;
		}
		public void setIDHistoria(int historia) {
			IDHistoria = historia;
		}
		public int getIDVacuna() {
			return IDVacuna;
		}
		public void setIDVacuna(int vacuna) {
			IDVacuna = vacuna;
		}
		public String getNameVacuna() {
			return nameVacuna;
		}
		public void setNameVacuna(String nameVacuna) {
			this.nameVacuna = nameVacuna;
		}
		public int getLengthFechV(){			
			return this.fech.length;
		}
		
		public Object[][] getFechas(int IDV,int IDH){
			//SELECT count(*) FROM `vacunas_historia` WHERE htra_id=440 AND vcna_id=7
		Object[][] valor = null;
		conexion = new Conexion(autenticacion);
			try {
				conexion.conectar();
				ResultSet auxSet;
				String query = "SELECT count(*) FROM `vacunas_historia` WHERE htra_id="+IDH+" AND vcna_id="+IDV;
				///System.out.println(query);
				auxSet = conexion.consultar(query);
				auxSet.next();
				int canti = Integer.parseInt(auxSet.getString(1));
				valor = new Object[canti][3];
				query = "SELECT fecha,tipo,lote FROM `vacunas_historia` WHERE htra_id="+IDH+" AND vcna_id="+IDV;
				auxSet = conexion.consultar(query);
				int i =0;
				while(auxSet.next()){
					valor[i][0]=auxSet.getString(1);
					valor[i][1]=auxSet.getString(2);
					valor[i][2]=auxSet.getString(3);
					i++;
				}
				cantidad_v=i;
				conexion.desconectar();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return valor;
		}
		
		public int getCantVacuna(){
				return cantidad_v;
		}
		
}
