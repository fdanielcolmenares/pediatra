package GestionarInventario;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.historial_inventarios;
import Entidades.inventario;
import Entidades.tipo_inventario;
import Utilitario.Autenticacion;

public class GestorGestionarInventario {
	
	private Autenticacion autenticacion;
	private inventario inventario;
	private nueCate ventana;
	private tipo_inventario tipoInv;
	private List idTipo;
	private int idMod;
	private List listaEventos;
	private historial_inventarios historial;
	
	public GestorGestionarInventario(nueCate v, Autenticacion a){
		ventana = v;
		autenticacion = a;
		inventario = new inventario(autenticacion);
		tipoInv = new tipo_inventario(autenticacion);
		historial = new historial_inventarios(autenticacion);
		idTipo = new ArrayList();
		idMod = 0;
		listaEventos = new ArrayList();
	}
	
	public boolean validarTipos(){
		if(tipoInv.vacio()){
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "Debe tener almenos un tipo de producto", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			ventana.getFrmnueCate().dispose();
			return false;
		}
		else
			return true;
	}
	
	public void llenartipoProductos(){
		ResultSet res = null;		
		
		ventana.getTiposPro().removeAll();
		idTipo.clear();
		res = tipoInv.cargartipos();
		
		try{
			while(res.next()){
				ventana.getTiposPro().addItem(res.getString("descripcion"));
				idTipo.add(res.getString("id"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validarTextField (JTextField campo){
		if(campo.getText().compareTo("")==0){
			campo.setText("Sin Informacion");
			return false;
		}
		return true;
	} 
	
	public void agregarEvento(String ev){
		listaEventos.add(ev);
	}
	
	public void guardarEventos(int idInv){
		for(int i=0; i<listaEventos.size(); i++){
			//System.out.println(autenticacion.getCedulaUsuario()
			//		+" "+listaEventos.get(i).toString()+" "+idInv);
			historial.setObservacion(listaEventos.get(i).toString());
			historial.setUsro_cedula(Integer.parseInt(autenticacion.getCedulaUsuario()));
			historial.setIvro_id(idInv);
			historial.buscarNuevoID();
			if(historial.guardar()){
				//System.out.println("historial guardado");
				
			}
		}
		listaEventos.clear();
	}
	
	public void guardarEvento(String ev){
		historial.setObservacion(ev);
		historial.setUsro_cedula(Integer.parseInt(autenticacion.getCedulaUsuario()));
		historial.setIvro_id(idMod);
		historial.buscarNuevoID();
		if(historial.guardar()){
			//System.out.println("historial guardado");
			inventario.setCantidad(Integer.parseInt(ventana.getCantidad().getText()));
			inventario.actualizar(idMod);
		}
		//System.out.println(autenticacion.getCedulaUsuario()+" "+ev+" "+idMod);
	}
	
	public void guardar(){
		boolean ban = false;
		if(!validarTextField(ventana.getTxtnomCate()))
			ban = true;
		if(!validarTextField(ventana.getCantidad()))
			ban = true;
		if(!validarTextField(ventana.getCtdmin()))
			ban = true;
		if(ban){
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "Debe ingresar el nombre del tipo de medicamento", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		if(inventario.existe(ventana.getTxtnomCate().getText())){
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "Ya existe este producto", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			return;
		}
		inventario.buscarNuevoID();
		inventario.setValido("s");
		inventario.setDescripcion(ventana.getTxtnomCate().getText());
		inventario.setCantidad(Integer.parseInt(ventana.getCantidad().getText()));
		inventario.setCantidad_minima(Integer.parseInt(ventana.getCtdmin().getText()));
		inventario.setTiro_id(Integer.parseInt(idTipo.get(ventana.getTiposPro().getSelectedIndex()).toString()));
		if(inventario.guardar()){
			if (JOptionPane.showConfirmDialog(ventana.getFrmnueCate(), "Guardado Exitosamente. Desea agregar otro?", "Inventario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
				guardarEventos(inventario.getId());
				ventana.getTxtnomCate().setText("");
				ventana.getCantidad().setText("0");
				ventana.getCtdmin().setText("");
			}
			else{
				guardarEventos(inventario.getId());
				ventana.setNuevo(false);
				ventana.getHistorial().setEnabled(true);
				idMod = inventario.getId();
			}			
		}
		else{
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "No se pudo guardar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
	}
	
	public void cargar(int id){
		idMod=id;
		inventario.cargar(id);
		ventana.getTxtnomCate().setText(inventario.getDescripcion());
		ventana.getCantidad().setText(String.valueOf(inventario.getCantidad()));
		ventana.getCtdmin().setText(String.valueOf(inventario.getCantidad_minima()));
		ventana.getTiposPro().setSelectedIndex(inventario.getTiro_id()-1);
	}
	
	public void actualizar(){
		boolean ban = false;
		if(!validarTextField(ventana.getTxtnomCate()))
			ban = true;
		if(!validarTextField(ventana.getCantidad()))
			ban = true;
		if(!validarTextField(ventana.getCtdmin()))
			ban = true;
		if(ban){
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "Debe ingresar el nombre del tipo de medicamento", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));	
			return;
		}
		inventario.setValido("s");
		inventario.setDescripcion(ventana.getTxtnomCate().getText());
		inventario.setCantidad(Integer.parseInt(ventana.getCantidad().getText()));
		inventario.setCantidad_minima(Integer.parseInt(ventana.getCtdmin().getText()));
		inventario.setTiro_id(Integer.parseInt(idTipo.get(ventana.getTiposPro().getSelectedIndex()).toString()));
		if(inventario.actualizar(idMod)){
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "Guardado Exitosamente", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		}
		else{
			JOptionPane.showMessageDialog(ventana.getFrmnueCate(), "No se pudo guardar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
	}
	
	public void eventos(){
		new tabla_eventos(ventana.pan,idMod,autenticacion,ventana.getTxtnomCate().getText());
	}

}
