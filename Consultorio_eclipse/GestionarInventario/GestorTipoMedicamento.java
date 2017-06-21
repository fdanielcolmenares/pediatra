package GestionarInventario;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.tipo_inventario;
import Utilitario.Autenticacion;

public class GestorTipoMedicamento {
	
	private Autenticacion autenticar;
	private TipoMedicamento ventana;
	private tipo_inventario tipoInv;
	
	public GestorTipoMedicamento(Autenticacion a, TipoMedicamento v){
		autenticar = a;
		ventana = v;
		tipoInv = new tipo_inventario(autenticar);
	}
	
	public void validar_tipo(){
		if(tipoInv.vacio()){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Debe tener almenos un tipo de producto", "Mensaje",
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
			JOptionPane.showMessageDialog(ventana.getVentana(), "Debe ingresar el nombre del tipo de medicamento", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		if(tipoInv.existe(ventana.getNombreTipo().getText())){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Ya existe este producto", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			return;
		}
		else{
			tipoInv.buscarNuevoID();
			tipoInv.setDesc(ventana.getNombreTipo().getText());
			if(tipoInv.guardar()){
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
		
		res = tipoInv.cargartipos();
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
			if(tipoInv.actualizar(ventana.modelo.getValueAt(ventana.getTabla().getSelectedRow(),1).toString(),ventana.getEditTipo().getText())){
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
