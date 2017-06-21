package GestionarFacturas;

import java.util.ArrayList;
import java.util.List;

import Entidades.Historias;
import Entidades.items;
import GestionarHistorias.BuscarHistorias;
import Utilitario.Autenticacion;
import VentanaPrincipal.VentanaPrincipal;

public class GestorFactura {
	private Autenticacion autenticacion;
	private VentanaFactura ventana;
	private VentanaPrincipal principal;
	private Historias historia;
	private List lstPagos;
	private List lstItems;
	private int minEditablePagos;
	private int minEditableItems;
	
	public GestorFactura(Autenticacion a, VentanaFactura v, VentanaPrincipal p){
		autenticacion = a;
		ventana = v;
		principal = p;
		historia = new Historias(autenticacion);
		lstPagos = new ArrayList();
		lstItems = new ArrayList();
		minEditableItems = 0;
		minEditablePagos = 0;
		ventana.getModeloPagos().setMinFilaEditable(minEditablePagos);
	}
	
	public void agregarItem(){
		
		
	}
	
	public void eliminarItem(int index){
		if(index >= minEditableItems){
			lstItems.remove(index);
			pintarTablaItems();
		}
		else{
			ventana.mostrarMensaje("La fila ya esta previamente guardada", ventana.ERROR);
		}
	}
	
	public void pintarTablaItems(){
		ventana.getModeloTabla().setRowCount(0);
		int c = 0;
		float total = 0;
		for(int i=0; i<lstItems.size(); i++){			
			items itm = (items)lstItems.get(i);
			ventana.getModeloTabla().setRowCount(c+1);
			ventana.getModeloTabla().setValueAt(itm.getConcepto().getDescripcion(), c, 0);
			ventana.getModeloTabla().setValueAt(itm.getDetalles(), c, 1);
			ventana.getModeloTabla().setValueAt(String.valueOf(itm.getPrecio()), c, 2);
			ventana.getModeloTabla().setValueAt(String.valueOf(itm.getCantidad()), c, 3);
			total = total + (itm.getPrecio()*itm.getCantidad());
			ventana.getModeloTabla().setValueAt(String.valueOf(total), c, 4);
		}
		ventana.getT_total().setText(String.valueOf(total));
	}
	
	public void mostrarDialogPagos(){
		DialogPagos d = new DialogPagos(autenticacion, this);
		d.setVisible(true);
		
		d.setVisible(false);
		d.dispose();
	}
	
	public void agregarPago(String fecha, String monto, String formaPago, int idFormaPago){
		ventana.getModeloPagos().setRowCount(ventana.getModeloPagos().getRowCount() + 1);
		ventana.getTablaPagos().setValueAt(fecha, ventana.getTablaPagos().getRowCount()- 1, 0);
		ventana.getTablaPagos().setValueAt(formaPago, ventana.getTablaPagos().getRowCount()- 1, 1);
		ventana.getTablaPagos().setValueAt(monto, ventana.getTablaPagos().getRowCount()- 1, 2);
	}
	
	public void eliminarPago(int index){		
		
	}
	
	public void pintarTablaPagos(){
		ventana.getModeloPagos().setRowCount(0);
		int c = 0;
		for(int i=0; i<lstPagos.size(); i++){
			
		}
	}
	
	public void buscarHistoria(){
		BuscarHistorias a = new BuscarHistorias(principal.getVentana(), true);
		a.setAutenticacion(autenticacion);	
		a.ChangeOptionChange(true);
		a.setVisibleDialog(true);
		if(a.getIDTable()>0){
			historia.buscarHistoriaNumero(a.getIDTable(), false);
			String id = "000000"+String.valueOf(historia.getId());
			id = id.substring(id.length()-6, id.length());
			ventana.getT_historia().setText(id);
			ventana.getT_nombre().setText(historia.getNombre());
		}
		
		
	}

	public int getMinEditableItems() {
		return minEditableItems;
	}

	public void setMinEditableItems(int minEditableItems) {
		this.minEditableItems = minEditableItems;
	}

	public int getMinEditablePagos() {
		return minEditablePagos;
	}

	public void setMinEditablePagos(int minEditablePagos) {
		this.minEditablePagos = minEditablePagos;
	}
	
	
}
