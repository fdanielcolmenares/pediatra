package GestionarMedicamentos;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import ConexionBD.Conexion;
import Entidades.medicinas;
import Entidades.presentaciones;
import Utilitario.Autenticacion;

public class GestorEditarMedicamentos {
	private Autenticacion autenticacion;
	private EditarMedicamentos ventana;
	private List listaPresentaciones;
	private List listaMedicamentos;
	private List listaPresMed;
	private List listaVerDosis;
	private int idPresentacion;
	private int idMedicamento;
	
	public GestorEditarMedicamentos(Autenticacion a, EditarMedicamentos v){
		autenticacion = a;
		ventana = v;
		listaPresentaciones = new ArrayList();
		listaMedicamentos = new ArrayList();
		listaPresMed = new ArrayList();
		listaVerDosis = new ArrayList();
		idPresentacion = -1;
		idMedicamento = -1;
	}
	
	public void verDosis(int index){
		int tam = listaVerDosis.size();
		for(int i=0; i<tam; i++){
			verDosis l = (verDosis)listaVerDosis.get(0);
			l.setVisible(false);
			listaVerDosis.remove(0);
		}
		listaVerDosis.clear();
		
		int idPres = Integer.parseInt(listaPresMed.get(index).toString());
		String sql;
		sql = "SELECT id, descripcion, valida FROM dosis "
				+"WHERE mdna_id="+idPres;
		Conexion con = new Conexion(autenticacion);
		verDosis panel = new verDosis(this, autenticacion, idPres);
		listaVerDosis.add(panel);
		
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{
				while(res!=null && res.next()){
					panel = new verDosis(this, autenticacion, idPres); 
					int id = Integer.parseInt(res.getString("id").toString());
					String desc = res.getString("descripcion").toString();
					String val = res.getString("valida").toString();
					boolean valida = false;
					if(val.compareToIgnoreCase("s")==0){
						valida = true;
					}
					panel.setTexto(id, desc, valida);
					listaVerDosis.add(panel);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			con.desconectar();
		}	
		
		int height = 110*listaVerDosis.size();
		ventana.getPanelListaDosis().setPreferredSize(new Dimension(270, height));
		ventana.getPanelListaDosis().setSize(new Dimension(270, 288));
		
		for(int i=0; i<listaVerDosis.size(); i++){
			verDosis l = (verDosis)listaVerDosis.get(i);
			l.setLocation(0, 110*i);
			ventana.getPanelListaDosis().add(l);
			int x = ventana.getVentana().getX();
			ventana.getVentana().setLocation(x+1, ventana.getVentana().getY());
			ventana.getVentana().setLocation(x, ventana.getVentana().getY());
		}
		
		int indexM = ventana.getTablaMedicamentos().getSelectedRow();
		int indexP = ventana.getTablaPresMed().getSelectedRow();
		if(indexM >=0 && indexP>=0){
			ventana.lbl_31.setText("Medicamento: "+
					ventana.getTablaMedicamentos().getValueAt(indexM, 0)
					+"("+ventana.getTablaPresMed().getValueAt(indexP, 0)+")");
		}
	}
	
	public void guardarMedicamento(){
		if(idMedicamento == -1){
			medicinas med = new medicinas(autenticacion);
			med.buscarNuevoID();
			med.setNombre(ventana.getT_medicamento().getText());
			med.setDescripcion(ventana.getT_descripcion().getText());
			if(ventana.getChk_si().isSelected()){
				med.setValida("s");
			}
			else{
				med.setValida("n");
			}
			String retorna = med.guardar();
			if(retorna.compareToIgnoreCase("Ok")==0){
				idMedicamento = med.getId();
				actualizarMedicamentos();
				ventana.mostrarMensaje("Registro exitoso", ventana.MENSAJE);
			}
			else{
				ventana.mostrarMensaje("No se pudo guardar el medicamento", ventana.ERROR);
			}
		}
		else{
			medicinas med = new medicinas(autenticacion);
			med.cargar(String.valueOf(idMedicamento));
			med.setNombre(ventana.getT_medicamento().getText());
			med.setDescripcion(ventana.getT_descripcion().getText());
			if(ventana.getChk_si().isSelected()){
				med.setValida("s");
			}
			else{
				med.setValida("n");
			}
			String retorna = med.actualizar();
			if(retorna.compareToIgnoreCase("Ok")==0){
				idMedicamento = med.getId();
				actualizarMedicamentos();
				ventana.mostrarMensaje("Actualización exitosa", ventana.MENSAJE);
			}
			else{
				ventana.mostrarMensaje("No se pudo actualizar el medicamento", ventana.ERROR);
			}
		}
	}
	
	public void agregarPresentacionMedicamento(){
		String desc = ventana.getListaPresentaciones().getSelectedItem().toString();
		int index = ventana.getListaPresentaciones().getSelectedIndex();
		int found = 0;
		for(int i=0; i<ventana.getTablaPresMed().getRowCount(); i++){
			if(ventana.getTablaPresMed().getValueAt(i, 0).toString().compareToIgnoreCase(desc)==0){
				found = 1;
				break;
			}
		}
		if(found == 0){
			Conexion con = new Conexion(autenticacion);
			if(con.conectar()){
				String sql;
				String id = "";
				sql = "SELECT IFNULL((SELECT MAX(id)+1 FROM presentaciones_medicina), 1)";
				java.sql.ResultSet res = con.consultar(sql);
				try {
					res.next();
					id = res.getString(1).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				sql = "INSERT INTO presentaciones_medicina "
						+"(id, mdna_id, pscn_id) VALUES "
						+"("+id+", "+idMedicamento+", "+listaPresentaciones.get(index)+")";
				//System.out.println(sql);
				if(con.actualizar(sql)){
					int ind = ventana.getTablaMedicamentos().getSelectedRow();
					if(ind>=0){
						seleccionaMedicamento(ind);
					}
					ventana.mostrarMensaje("Registro exitoso", ventana.MENSAJE);
				}
				else{
					ventana.mostrarMensaje("No se pudo agregar la presentación al medicamento", ventana.ERROR);
				}
				con.desconectar();
			}
		}
		else{
			ventana.mostrarMensaje("El medicamento ya tiene la presentación seleccionada", ventana.ERROR);
		}
	}
	
	public void nuevoMedicamento(){
		idMedicamento = -1;
		ventana.getT_medicamento().setText("");
		ventana.getT_descripcion().setText("");
		ventana.getT_medicamento().setEditable(true);
		ventana.getT_descripcion().setEditable(true);
		ventana.getChk_si().setEnabled(true);
		ventana.getChk_si().setSelected(true);
		ventana.getChk_no().setEnabled(true);
	}	
	
	public void seleccionaMedicamento(int index){
		idMedicamento = Integer.parseInt(listaMedicamentos.get(index).toString());
		String sql;
		sql = "SELECT nombre, descripcion, LOWER(valida) AS valida FROM medicinas WHERE id="+idMedicamento;
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try {
				if(res != null && res.next()){
					ventana.getT_medicamento().setEditable(true);
					ventana.getT_descripcion().setEditable(true);
					ventana.getChk_si().setEnabled(true);
					ventana.getChk_no().setEnabled(true);
					ventana.getT_medicamento().setText(res.getString("nombre").toString());
					try{
						ventana.getT_descripcion().setText(res.getString("descripcion").toString());
					}
					catch (Exception e) {						
					}
					if(res.getString("valida").compareTo("s")==0){
						ventana.getChk_si().setSelected(true);
						ventana.getChk_no().setSelected(false);
					}
					else{
						ventana.getChk_si().setSelected(false);
						ventana.getChk_no().setSelected(true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		sql = "SELECT p.descripcion AS descripcion, pm.id AS id "
				+"FROM presentaciones p, presentaciones_medicina pm "
				+"WHERE pm.pscn_id=p.id AND pm.mdna_id="+idMedicamento+" "
				+"ORDER BY descripcion";
		ventana.modeloPresMed.setRowCount(0);
		listaPresMed.clear();
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try {
				int cont = 0;
				while(res != null && res.next()){
					ventana.modeloPresMed.setRowCount(cont+1);
					ventana.getTablaPresMed().setValueAt(res.getString("descripcion"), cont, 0);
					listaPresMed.add(res.getString("id").toString());
					cont++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.desconectar();
		}
	}
	
	public void actualizarMedicamentos(){
		buscarMedicamentos("1=1");
	}
	
	public void buscarMedicamentos(String where){
		listaMedicamentos.clear();
		ventana.modeloMedicamentos.setRowCount(0);
		ventana.modeloPresMed.setRowCount(0);
		idMedicamento = -1;
		ventana.getT_medicamento().setText("");
		ventana.getT_medicamento().setEditable(false);
		ventana.getT_descripcion().setText("");
		ventana.getT_descripcion().setEditable(false);
		ventana.getChk_si().setEnabled(false);
		ventana.getChk_no().setEnabled(false);
		ventana.getChk_si().setSelected(true);
		String sql;
		sql = "SELECT id, nombre "
				+"FROM medicinas "
				+"WHERE "+where;
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try {
				int cont = 0;
				while(res!=null && res.next()){
					listaMedicamentos.add(res.getString("id").toString());
					ventana.modeloMedicamentos.setRowCount(cont+1);
					ventana.getTablaMedicamentos().setValueAt(res.getString("nombre"), cont, 0);
					cont++;
				}
			} catch (Exception e) {
			}
			con.desconectar();
		}
	}
	
	public void actualizarPresentaciones(){
		ventana.modeloPresentaciones.setRowCount(0);
		ventana.getListaPresentaciones().removeAllItems();
		listaPresentaciones.clear();
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT id, descripcion FROM presentaciones ORDER BY descripcion ASC";
			java.sql.ResultSet res = con.consultar(sql);
			try{
				int cont = 0;
				while(res!=null && res.next()){
					listaPresentaciones.add(res.getString("id").toString());
					ventana.modeloPresentaciones.setRowCount(cont+1);
					ventana.getTablaPresentaciones().setValueAt(res.getString("descripcion").toString(), cont, 0);
					ventana.getListaPresentaciones().addItem(res.getString("descripcion").toString());
					cont++;
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			con.desconectar();
		}
	}
	
	public void nuevaPresentacion(){
		ventana.getT_presentacion().setEditable(true);
		ventana.getT_presentacion().setText("");
		idPresentacion = -1;
	}
	
	public void seleccionaPresentacion(int index){
		idPresentacion = Integer.parseInt(listaPresentaciones.get(index).toString());
		ventana.getT_presentacion().setText(ventana.getTablaPresentaciones().getValueAt(index, 0).toString());
		ventana.getT_presentacion().setEditable(true);
	}
	
	public void guardarPresentacion(){
		if(idPresentacion == -1){
			int found = 0;
			String desc = ventana.getT_presentacion().getText();
			for(int i=0; i<ventana.getTablaPresentaciones().getRowCount();i++){
				if(ventana.getTablaPresentaciones().getValueAt(i, 0).toString().compareToIgnoreCase(desc)==0){
					found = 1;
					break;
				}
			}
			if(found == 0){
				presentaciones pres = new presentaciones(autenticacion);
				pres.buscarNuevoID();
				pres.setDescripcion(desc);
				String ret = pres.guardar();
				if(ret.compareToIgnoreCase("Ok")==0){
					idPresentacion = pres.getId();
					actualizarPresentaciones();
					ventana.mostrarMensaje("Registro exitoso", ventana.MENSAJE);
				}
				else{
					ventana.mostrarMensaje("No se pudo guardar la presentación", ventana.ERROR);
				}
			}
			else{
				ventana.mostrarMensaje("La presentación ya existe", ventana.ERROR);
			}
		}
		else{
			int found = 0;
			String desc = ventana.getT_presentacion().getText();
			for(int i=0; i<ventana.getTablaPresentaciones().getRowCount();i++){
				if(ventana.getTablaPresentaciones().getValueAt(i, 0).toString().compareToIgnoreCase(desc)==0 && 
						Integer.parseInt(listaPresentaciones.get(i).toString())!= idPresentacion){
					found = 1;
					break;
				}
			}
			if(found == 0){
				presentaciones pres = new presentaciones(autenticacion);
				pres.setId(idPresentacion);
				pres.setDescripcion(desc);
				String ret = pres.actualizar(String.valueOf(pres.getId()), pres.getDescripcion());
				if(ret.compareToIgnoreCase("Ok")==0){
					idPresentacion = pres.getId();
					actualizarPresentaciones();
					ventana.mostrarMensaje("Actualización exitosa", ventana.MENSAJE);
				}
				else{
					ventana.mostrarMensaje("No se pudo actualizar la presentación", ventana.ERROR);
				}
			}
			else{
				ventana.mostrarMensaje("La presentación ya existe", ventana.ERROR);
			}
		}
	}
	
	public EditarMedicamentos getVentana() {
		return ventana;
	}

	public void cerrar(){
		ventana.getVentana().dispose();
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}
	
}
