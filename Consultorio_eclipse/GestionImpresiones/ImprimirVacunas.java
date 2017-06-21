package GestionImpresiones;

import java.awt.Desktop;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import Utilitario.Autenticacion;
import Utilitario.Path;
import Utilitario.UtilFechas;

import ConexionBD.Conexion;
import Entidades.Historias;

public class ImprimirVacunas {
	private Process_SourceDataVacunas proces;
	private int codigo_pac;
	private JInternalFrame frame;
	private Conexion conexion;
	private Autenticacion autenticacion;
	private String query;		
	private Map parameter; 
	private String name;
	
	public ImprimirVacunas(int codigo_pac,JInternalFrame frame, Autenticacion autenticacion){
		this.codigo_pac = codigo_pac;
		this.frame = frame;
		this.autenticacion = autenticacion;
		this.conexion =conexion;
		proces = new Process_SourceDataVacunas();
		this.GenerarReporteVacunas();
	}
	
	public void GenerarReporteVacunas(){
		conexion = new Conexion(autenticacion);
		conexion.conectar();
		query = "SELECT b.fecha,a.nombre,b.tipo,b.lote  FROM vacunas a," +
				" vacunas_historia b WHERE a.id = b.vcna_id AND b.htra_id =" + this.codigo_pac +
				" ORDER BY b.fecha,a.nombre";
				
		ResultSet resultSet = conexion.consultar(query);
	try{
	//crecaion de datos del proceso 
		while(resultSet.next()){
				SourceDataListVacunas a = new SourceDataListVacunas();
				a.setDate(resultSet.getString(1));
				a.setDescripcion(resultSet.getString(2));
				a.setDosis(resultSet.getString(3));
				a.setLote(resultSet.getString(4));
				proces.addVacunas(a);
		}
	///Crecaion de datos por parametro 
		parameter = new HashMap();
		Historias h = new Historias(autenticacion);
		h.buscarHistoriaNumero(this.codigo_pac,false);
		name = h.getNombre();
		Date fech = new Date();
		parameter.put("Nombre", name);
		//parameter.put("Fecha", UtilFechas.convertirFecha(fech,UtilFechas.DD_MM_YYYY));
		parameter.put("Fecha","");
		parameter.put("Edad", h.getFecha_nacimiento());		
	}catch(SQLException e){
			e.printStackTrace();
	}//fin try	
		
	///---------GENERAR REPORTE
	JFileChooser ventana = new JFileChooser();		
	ventana.setSelectedFile(new File("Vacunas_"+name+".pdf"));
	int val = ventana.showSaveDialog(frame);
	String ruta = null;
try{		
	if(val==0){
			ruta = ventana.getSelectedFile().toString();
			
			JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files//jasper//H_Vacunas.jasper");					
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parameter,proces);
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta));
			exporter.exportReport();		
			
			//System.out.println("OK");
			JOptionPane.showMessageDialog(ventana, "Generación correcta ", "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			Desktop.getDesktop().open(new File(ruta));	
			;				
	}
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("Error al generar Impresion");
	}
	
	
	}
}
