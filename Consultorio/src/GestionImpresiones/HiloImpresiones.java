package GestionImpresiones;

import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;

import GestionarCaja.VentanaAgregarRegistro;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class HiloImpresiones extends Thread{
	private int tipoReporte;
	public int REPORTE_CAJA = 1;
	public int REPORTE_INVENTARIO = 2;
	private Autenticacion autenticacion;
	private Object clase;
	
	public HiloImpresiones(Autenticacion a){
		super();
		autenticacion = a;
		tipoReporte = -1;
	}
	
	public void setTipoReporte(int tipo){
		tipoReporte = tipo;
	}
	
	public void setClase(Object c){
		clase = c;
	}
	
	public void run(){
		if(tipoReporte == REPORTE_CAJA){
			VentanaAgregarRegistro ventana = (VentanaAgregarRegistro)clase;
			PrintRegistrosCaja print = new PrintRegistrosCaja(autenticacion);
			print.cargarReporte(UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.YYYY_MM_DD));
			
			//print.mostrarReporte();
			
			JFileChooser buscador = new JFileChooser();		
	        buscador.setSelectedFile(new File("Control_"+UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.DD_MM_YYYY)+".pdf"));
	        String ruta;
	        int d = buscador.showSaveDialog(null);
	        if(d == JFileChooser.APPROVE_OPTION){
	            ruta = buscador.getSelectedFile().toString();
	            print.guardarReporte(ruta);
	        }
		}
		if(tipoReporte == REPORTE_INVENTARIO){
			PrintInventario print = new PrintInventario(autenticacion);
			print.cargarReporte();
			
			//print.mostrarReporte();
			
			JFileChooser buscador = new JFileChooser();		
	        buscador.setSelectedFile(new File("Inventario_"+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)+".pdf"));
	        String ruta;
	        int d = buscador.showSaveDialog(null);
	        if(d == JFileChooser.APPROVE_OPTION){
	            ruta = buscador.getSelectedFile().toString();
	            print.guardarReporte(ruta);
	        }
		}
		
	}

}
