package GestionImpresiones;

import java.awt.Dimension;
import java.io.File;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class GestorGenerarReportes {
	private VentanaGenerarReportes ventana;
	private int panelActual;
	private int PANEL_NOMBRE_FECHA = 1;
	private int PANEL_CONSTANCIA = 2;
	private int tipoInforme;
	
	public GestorGenerarReportes(Autenticacion a){
		panelActual = PANEL_NOMBRE_FECHA;
		tipoInforme = 0;
	}
	
	public void setVentana(VentanaGenerarReportes v){
		ventana = v;
		establecerPanel(0);
	}
	
	public void establecerPanel(int index){
		tipoInforme = index;
		if(index >=0 && index <=3){
			panelActual = PANEL_NOMBRE_FECHA;
		}
		if(index == 4){
			panelActual = PANEL_CONSTANCIA;
		}
		
		ventana.t_campo1.setText("");
		ventana.getT_campo2().setDate(new Date());
		ventana.t_campo3.setText("");
		ventana.t_campo4.setText("");
		ventana.t_campo5.setText("");
		ventana.t_campo6.setText("");
		ventana.t_campo7.setText("");
		ventana.t_campo8.setText("");
		
		if(panelActual == PANEL_NOMBRE_FECHA){
			ventana.lbl_campo1.setText("Nombre:");
			ventana.getPanelNombreFecha().setPreferredSize(new Dimension(393, 136));
			ventana.lbl_campo3.setVisible(false);
			ventana.lbl_campo4.setVisible(false);
			ventana.lbl_campo5.setVisible(false);
			ventana.lbl_campo6.setVisible(false);
			ventana.lbl_campo7.setVisible(false);
			ventana.lbl_campo8.setVisible(false);
			ventana.t_campo3.setVisible(false);
			ventana.t_campo4.setVisible(false);
			ventana.t_campo5.setVisible(false);
			ventana.t_campo6.setVisible(false);
			ventana.t_campo7.setVisible(false);
			ventana.t_campo8.setVisible(false);
			//
		}
		if(panelActual == PANEL_CONSTANCIA){
			ventana.lbl_campo1.setText("Paciente:");
			ventana.lbl_campo3.setVisible(true);
			ventana.lbl_campo4.setVisible(true);
			ventana.lbl_campo5.setVisible(true);
			ventana.lbl_campo6.setVisible(true);
			ventana.lbl_campo7.setVisible(true);
			ventana.lbl_campo8.setVisible(true);
			ventana.t_campo3.setVisible(true);
			ventana.t_campo4.setVisible(true);
			ventana.t_campo5.setVisible(true);
			ventana.t_campo6.setVisible(true);
			ventana.t_campo7.setVisible(true);
			ventana.t_campo8.setVisible(true);
			ventana.getPanelNombreFecha().setPreferredSize(new Dimension(390,270));
		}
	}
	
	public void exportar(String ruta){
		GenerarReportesDocx exp = new GenerarReportesDocx();
		exp.setRuta(ruta);
		switch(tipoInforme){
			case 0:	exp.setTipoReporte(exp.ALIMENTACION_6_8_MESES);
					break;
			case 1:	exp.setTipoReporte(exp.ALIMENTACION_8_12_MESES);
					break;
			case 2:	exp.setTipoReporte(exp.ALIMENTACION_12_MESES);
					break;
			case 3:	exp.setTipoReporte(exp.VITAMINAS_MADRE);
					break;
			case 4:	exp.setTipoReporte(exp.INFORME_MEDICO);
					break;
		}
		
		if(tipoInforme>=0 && tipoInforme <=3){
			exp.setNombre(ventana.getT_campo1().getText());
			exp.setFecha(UtilFechas.convertirFecha(ventana.getT_campo2().getDate(), UtilFechas.DD_MM_YYYY));
		}
		if(tipoInforme==4){
			exp.setNombre(ventana.getT_campo1().getText());
			exp.setEdad(ventana.getT_campo8().getText());
			exp.setRepresentante(ventana.getT_campo3().getText());
			exp.setCIRepresentante(ventana.getT_campo4().getText());
			exp.setCuida(ventana.getT_campo5().getText());
			exp.setEnfermedad(ventana.getT_campo6().getText());
			exp.setDias(ventana.getT_campo7().getText());
			exp.setFecha(UtilFechas.convertirFecha(ventana.getT_campo2().getDate(), UtilFechas.DD_MM_YYYY));
		}
		
		int x = exp.generarReporte();
		if(x == 0){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Operación exitosa", "Mensaje", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			exp.abrir();
		}
	}
	
	public void generarReporte(){
		String ruta = "";
		JFileChooser buscador = new JFileChooser();		
        buscador.setSelectedFile(new File("Informe_"));
		int d = buscador.showSaveDialog(null);
        if(d == JFileChooser.APPROVE_OPTION){
            ruta = buscador.getSelectedFile().toString();
            exportar(ruta);
        }
	}
}
