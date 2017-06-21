package GestionarRecetas;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class GestorPanelMedicamento {
	
	public List lista = new ArrayList();
	private int tamaño=0;
	private Conexion conexion = null;
	private ResultSet resultSet = null;
	
	public void AddPanel(String nameMedicina, String DescripMedica,int ID_dosis){
			jPanelMedicamento aux = new jPanelMedicamento();
			aux.setNameMedicina(nameMedicina);
			aux.setTexArea(DescripMedica);
			aux.setBounds(0,tamaño*135 , 350, 135);
			aux.setDoubleBuffered(true);
			aux.setID_dosis(ID_dosis);
			lista.add(aux);			
			tamaño=lista.size();
			///Actualizar();
			
	}
	
	public int getIdDosis(int index){
		jPanelMedicamento obj = (jPanelMedicamento)lista.get(index);
		return obj.getID_dosis();
	}
	public void RemovePanel(JPanel panel){
		///int menos = 0;
				for (int i = 0; i < lista.size(); i++) {
					jPanelMedicamento obj = (jPanelMedicamento)lista.get(i);
						if(obj.getEliminar()){
							panel.remove(obj);
							lista.remove(i);
							i=0;
							///menos++;
					
						}
				}
				
				/*int aux = 0;
			
				//while(aux<menos){
					for (int i = 0; i < lista.size(); i++) {
						if(lista.get(i).getEliminar()){
							lista.remove(i);
							i=0;
							///panel.remove(lista.get(i));
							
					
						}
				}
				//}*/
				
			
				
				for (int i = 0; i < getTamano(); i++) {
					///lista.get(i).setBounds(0, 135*tamaño, 350,135);	
					jPanelMedicamento obj = (jPanelMedicamento)lista.get(i);
					obj.setposY(i*135);			
					obj.setVisible(true);
					//System.out.println(obj.getPosy());
					}
				
				System.out.println(getTamano());
				///Actualizar();
	}
	
	public int getTamano(){
		return lista.size();
	}
	public void Actualizar(){
		for (int i = 0; i < getTamano(); i++) {
				///lista.get(i).setBounds(0, 135*tamaño, 350,135);	
			jPanelMedicamento obj = (jPanelMedicamento)lista.get(i);
				obj.setposY(tamaño*135);			
				obj.setVisible(true);
				///System.out.println(lista.get(i).getPosy());
		}
		
	}
	
	public JPanel getPanel(int index){
		jPanelMedicamento obj = (jPanelMedicamento)lista.get(index);
		return obj;
	}
	
	public void loadMedicinas(int id_consulta,Autenticacion autenticacion){
		String query = "SELECT d.nombre as nombre,e.descripcion as presentacion" +
				",b.descripcion as dosis,b.id as id FROM medicinas_consulta a," +
				" dosis b,presentaciones_medicina c,medicinas d,presentaciones e " +
				"WHERE a.dsis_id=b.id AND b.mdna_id=c.id AND c.mdna_id=d.id " +
				"AND c.pscn_id=e.id AND a.csta_id="+id_consulta;
		conexion = new Conexion(autenticacion);
		conexion.conectar();
			resultSet = conexion.consultar(query);
					try {
						
						while(resultSet.next()){
							this.AddPanel(resultSet.getString("nombre")+"-"+resultSet.getString("presentacion"),resultSet.getString("dosis"),Integer.parseInt(resultSet.getString("id")));
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error en Consulta de Paneles de Recetas");
					}
		
	}//loadMedicinas
	
}
