package GestionarExamenes;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Entidades.estudios;
import Utilitario.Autenticacion;

public class GestorTipoExamen {
	
	private Autenticacion autenticar;
	private TipoExamen ventana;
	private estudios estu;
	
	public GestorTipoExamen(Autenticacion a, TipoExamen v){
		autenticar = a;
		ventana = v;
		estu = new estudios(autenticar);
	}
	
	public void validar_tipo(){
		if(estu.vacio()){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Debe tener almenos un tipo de examen", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			ventana.getJTabbedPane().setSelectedIndex(0);
		}
	}
	
	public boolean validarTextField (JTextField campo){
		if(campo.getText().compareTo("")==0){
			campo.setText("Sin Informacion");
			return false;
		}
		return true;
	} 
	
	public void guardar(){
		if(!validarTextField(ventana.getNombreTipo())){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Debe ingresar el nombre del tipo de examen", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		if(estu.existe(ventana.getNombreTipo().getText())){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Ya existe este examen", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			return;
		}
		else{
			estu.buscarNuevoID();
			estu.setDescripcion(ventana.getNombreTipo().getText());
			if(estu.guardar()){
				JOptionPane.showMessageDialog(ventana.getVentana(), "Guardado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
				ventana.getNombreTipo().setText("");
			}
			else{
				JOptionPane.showMessageDialog(ventana.getVentana(), "No se pudo guardar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}
	
	public void llenarTipos(){
		int cont=0;
		ResultSet res = null;
		
		res = estu.cargartipos();
		ventana.modelo.removeAllRows();
		ventana.modelo.setRowCount(cont);
		
		try{
			while(res.next()){
				ventana.modelo.setRowCount(cont+1);
				ventana.getTabla().setValueAt(String.valueOf(cont+1),cont,0);
				ventana.getTabla().setValueAt(res.getString("descripcion"),cont,1);
				cont++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificar(){
		ventana.getEditTipo().setText(ventana.modelo.getValueAt(ventana.getTabla().getSelectedRow(),1).toString());
	}
	
	public void actualizarTipo(){
		if(!validarTextField(ventana.getEditTipo())){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Debe ingresar el nombre del tipo de medicamento", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		else{
			if(estu.actualizar(ventana.modelo.getValueAt(ventana.getTabla().getSelectedRow(),1).toString(),ventana.getEditTipo().getText())){
				JOptionPane.showMessageDialog(ventana.getVentana(), "Guardado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
				llenarTipos();
				ventana.getEditTipo().setText("");
			}
			else{
				JOptionPane.showMessageDialog(ventana.getVentana(), "No se pudo guardar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}

}
