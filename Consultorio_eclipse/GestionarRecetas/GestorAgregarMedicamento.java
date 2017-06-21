package GestionarRecetas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.dosis;
import Entidades.medicinas;
import Entidades.presentaciones;
import Utilitario.Autenticacion;

public class GestorAgregarMedicamento {
	
	private AgregarMedicamento medicamento;
	private presentaciones presenta;
	private medicinas medicina;
	private dosis dosis;
	private int cont;
	private String [][] mat;
	private int contdo,contpe;
	private List listadosis,listaPre;
	
	public GestorAgregarMedicamento(Autenticacion a,AgregarMedicamento m){
		medicamento = m;
		presenta = new presentaciones(a);
		medicina = new medicinas(a);
		dosis = new dosis(a);
		cont = 0;
		contdo = 0;
		contpe = 0;
		listadosis = new ArrayList();
		listaPre = new ArrayList();
	}
	
	public String validarTextField (JTextField campo){
		if(campo.getText().compareTo("")==0){
			campo.setText("Sin Informacion");
			return "FALLO";
		}
		return "OK";
	}
	
	public void guardarPresenta(){
		String res;
		
		if(validarTextField(medicamento.getPresenta()).compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe ingresar una presentacion", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		if(presenta.BuscarPresenta(medicamento.getPresenta().getText())){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Presentacio ya registrada", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		else{
			presenta.buscarNuevoID();
			presenta.setDescripcion(medicamento.getPresenta().getText());
			res = presenta.guardar();
			if(res.compareTo("OK")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Guardado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			}
			if(res.compareTo("FALLO")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo guardar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}
	
	public void cargarPresenta(int pos) {
		boolean ban=false;
		ResultSet res = null;
		int i=0;
		
		res = presenta.cargarpresent();
		
		try {
			while(res.next()){			
				cont++;
				ban=true;
			}
			mat = new String[cont][2];
			res = presenta.cargarpresent();
			medicamento.getPresenta1().removeAllItems();
			while(res.next()){			
				mat[i][0] = String.valueOf(res.getInt("id"));
				mat[i][1] = res.getString("descripcion");
				medicamento.getPresenta1().addItem(mat[i][1]);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(ban){
			medicamento.getGuarMedi().setEnabled(true);
		}
		else{
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe tener almenos una presentacion", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		medicamento.getPresenta1().setSelectedItem(mat[pos][1]);
	}
	
	public boolean guardarMedica(){
		String res;
		
		if(validarTextField(medicamento.getNomMedi()).compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe ingresar un nombre", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));			
		}		
		else{
			medicina.buscarNuevoID();
			medicina.buscarNuevoID2();
			if(medicamento.getNo_medi().isSelected())
				medicina.setValida("n");
			else
				medicina.setValida("s");
			medicina.setDescripcion(medicamento.getDesc().getText());
			medicina.setNombre(medicamento.getNomMedi().getText());			
			res = medicina.guardar(mat[medicamento.getPresenta1().getSelectedIndex()][0]);
			//System.out.println("algo: "+res);
			if(res.compareTo("OK")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Guardado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
				medicamento.getDesc().setText("");
				medicamento.getNomMedi().setText("");
				return true;
			}
			if(res.compareTo("FALLO")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo guardar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
				return false;
			}
		}
		return false;
	}
	
	public void guardarDosis(){
		String res;
		if(validarTextField(medicamento.getTxt_dosis()).compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe ingresar una dosis", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));			
		}		
		else{
			dosis.buscarNuevoID();
			dosis.setDescripcion(medicamento.getTxt_dosis().getText());
			dosis.setValida("s");
			dosis.setMdna_id(medicina.getId());
			res = dosis.guardar();
			if(res.compareTo("OK")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Guardado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
				medicamento.getTxt_dosis().setText("");
				medicamento.modelo.setRowCount(contdo+1);
				medicamento.getJTable().setValueAt(String.valueOf(contdo+1), contdo, 0);
				medicamento.getJTable().setValueAt(dosis.getDescripcion(), contdo, 1);
				listadosis.add(String.valueOf(dosis.getId()));
				contdo++;
			}
			if(res.compareTo("FALLO")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo guardar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}
	
	public void statusDosis(){
		//System.out.println("numero: "+listadosis.get(medicamento.getJTable().getSelectedRow()));
		if(dosis.consultarstatus(listadosis.get(medicamento.getJTable().getSelectedRow()).toString()))
			medicamento.getSi_dosis().setSelected(true);
		else
			medicamento.getNo_dosis().setSelected(true);
	}
	
	public void actualizar(){
		String res;
		if(medicamento.getSi_dosis().isSelected())
			res="s";
		else
			res="n";
		res = dosis.actualizarstatus(listadosis.get(medicamento.getJTable().getSelectedRow()).toString(), res);
		if(res.compareTo("OK")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Actualizado Exitosamente", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		}
		if(res.compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo Actualizar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
	}
	
	public void cargar(String id){
		ResultSet res = null;
		//System.out.println("id: "+id);
		medicamento.getJScrollPane2().setVisible(true);
		medicina.cargar(id);
		medicamento.getNomMedi().setText(medicina.getNombre());
		medicamento.getDesc().setText(medicina.getDescripcion());
		if(medicina.getValida().compareTo("s")==0)
			medicamento.getSi_medi().setSelected(true);
		else
			medicamento.getNo_medi().setSelected(true);		
		
		cargarPresenta(medicina.idpresentacion()-1);
		
		res = dosis.cargarDosis(medicina.getId());
		try {
			while(res.next()){
				medicamento.modelo.setRowCount(contdo+1);
				medicamento.getJTable().setValueAt(String.valueOf(contdo+1), contdo, 0);
				medicamento.getJTable().setValueAt(res.getString("descripcion"), contdo, 1);
				listadosis.add(String.valueOf(res.getInt("id")));
				contdo++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		res = presenta.cargarpresent();
		try {
			while(res.next()){
				medicamento.modelo2.setRowCount(contpe+1);
				medicamento.getTablaP().setValueAt(String.valueOf(contpe+1),contpe,0);
				medicamento.getTablaP().setValueAt(res.getString("descripcion"),contpe,1);
				listaPre.add(String.valueOf(res.getInt("id")));
				contpe++;
			}
		} catch (Exception e) {
		}
	}
        
        public void presenta(){
            ResultSet res = null;
            res = presenta.cargarpresent();
		try {
			while(res.next()){
				medicamento.modelo2.setRowCount(contpe+1);
				medicamento.getTablaP().setValueAt(String.valueOf(contpe+1),contpe,0);
				medicamento.getTablaP().setValueAt(res.getString("descripcion"),contpe,1);
				listaPre.add(String.valueOf(res.getInt("id")));
				contpe++;
			}
		} catch (Exception e) {
		}
        }
	
	public void actualizarMedica(){
		String res;
		
		if(validarTextField(medicamento.getNomMedi()).compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe ingresar un nombre", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));			
		}			
		else{
			if(medicamento.getNo_medi().isSelected())
				medicina.setValida("n");
			else
				medicina.setValida("s");
			medicina.setDescripcion(medicamento.getDesc().getText());
			medicina.setNombre(medicamento.getNomMedi().getText());			
			res = medicina.actuliazarMedica(medicina.getId(),mat[medicamento.getPresenta1().getSelectedIndex()][0]);
			//System.out.println("algo: "+res);
			if(res.compareTo("OK")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Actualizado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			}
			if(res.compareTo("FALLO")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo Actualizar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}
	
	public void editPre(){
		medicamento.getPresenta().setText(medicamento.getTablaP().getValueAt(medicamento.getTablaP().getSelectedRow(),1).toString());
	}
	
	public void actualizarPre(){
		String res;
		if(validarTextField(medicamento.getPresenta()).compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Debe ingresar una presentacion", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		else{
			res = presenta.actualizar(listaPre.get(medicamento.getTablaP().getSelectedRow()).toString(), medicamento.getPresenta().getText());
			if(res.compareTo("OK")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "Actualizado Exitosamente", "Mensaje",
	                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
				medicamento.getTablaP().setValueAt(medicamento.getPresenta().getText(), medicamento.getTablaP().getSelectedRow(), 1);
			}
			if(res.compareTo("FALLO")==0){
				JOptionPane.showMessageDialog(medicamento.getVentanaNewMedica(), "No se pudo Actualizar", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}

}
