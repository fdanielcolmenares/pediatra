package GestionarVacunas;

import java.sql.ResultSet;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import ConexionBD.Conexion;

public class CargaVacunas extends Utilitario.Modelo_Tabla{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Autenticacion autenticacion;
	private Object[][] Datos;
	private Object[] encabezado={"Vacunas","Fecha de Vacunación","Dosis","Lote"};
	private int htra_id;
	private Conexion conexion;  //  @jve:decl-index=0:
	private ResultSet resultSet;  //  @jve:decl-index=0:
	private String query;  //  @jve:decl-index=0:
	private String[] dat;
	
	public CargaVacunas(Autenticacion a, int htra_id){
			super();			
			this.autenticacion = a;			
			this.htra_id = htra_id;
			loadData(0);
	}
	
	public String[] getIde(){
		loadData(1);
		return dat;
	}
	public void lookDat(){
		//System.out.println("-----------------------");
		for (int i = 0; i < dat.length; i++) {
			System.out.println(dat[i]);
		}
		//System.out.println("-----------------------");
			
	} 
	public void loadData(int val){
			conexion = new Conexion(autenticacion);
			conexion.conectar();
			query = "SELECT COUNT(*) FROM vacunas_historia WHERE htra_id="+htra_id;
			try {
			resultSet = conexion.consultar(query);
			resultSet.next();			
			int contador = Integer.parseInt(resultSet.getString(1));
			Datos = new Object[contador][4];
			///query = "SELECT vcna_id,fecha,proxima_dosis,lote FROM vacunas_historia WHERE htra_id="+htra_id+" ORDER BY vcna_id ASC";
			dat = new String[contador];
			query = "SELECT b.nombre,a.fecha,a.tipo,a.lote,a.id FROM vacunas_historia a,vacunas b WHERE b.id = a.vcna_id and a.htra_id = "+htra_id+" ORDER BY a.fecha, b.nombre ";
			///System.out.println(query);
			resultSet = conexion.consultar(query);		
			int con2 = 0;
			while(resultSet.next()){
				Datos[con2][0] = resultSet.getString(1);
				String fecha = resultSet.getString(2).toString();
				fecha = UtilFechas.convertirFecha(fecha, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
				//Datos[con2][1] = resultSet.getString(2);
				Datos[con2][1] = fecha;
				//fecha = resultSet.getString(3).toString();
				//fecha = UtilFechas.convertirFecha(fecha, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
				//Datos[con2][2] = resultSet.getString(3);
				
				//Validacion para mostrar datos de las dosis 1,2,3 Vacunas y 4 Refuerzo  R
				String dosis = "";
					if(Integer.parseInt(resultSet.getString(3).toString())==4){
						dosis = "R";
					}else{
						dosis =	resultSet.getString(3).toString();					
					}
//				Validacion para mostrar datos de las dosis
				Datos[con2][2] = dosis;
				Datos[con2][3] = resultSet.getString(4);	
				dat[con2++]=resultSet.getString(5);
			}
			if(val==0){
			this.setDataVector(Datos, encabezado);
			}
			conexion.desconectar();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exceptioni
			}
			
	}///fin load
	
	
	
}
