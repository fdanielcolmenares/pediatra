package GestionImpresiones;

import Utilitario.Path;
import Utilitario.UtilFechas;

import java.awt.Desktop;
import java.io.File;
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
import GestionarRecetas.GestorPanelMedicamento;
import GestionarRecetas.jPanelMedicamento;

public class ImprimirReceta {
	private Process_SourceDataListRecetas source ;
	private Process_SourceDataListRecetas source2 ;
	private String name;
	private String Edad;
	private String fecha;
	private JInternalFrame frame;
	
	public ImprimirReceta(String name,String Edad, String fecha,JInternalFrame frame){
		this.name = name;
		this.Edad = Edad;
		this.fecha = fecha;
		this.frame = frame;
	}
	public void GenerarReceta(GestorPanelMedicamento gestor){
			SourceDataListRecetas a = null;
			source = new Process_SourceDataListRecetas();
			source2 = new Process_SourceDataListRecetas();
			for (int i = 0; i <gestor.getTamano(); i++) {
					a = new SourceDataListRecetas();
					jPanelMedicamento obj = (jPanelMedicamento)gestor.lista.get(i);
					a.setMedicamento(obj.getNameMedicina());
					a.setDosis(obj.getTexArea());
					a.setNumber(String.valueOf(i+1));
					source.addReceta(a);
					source2.addReceta(a);
			}//fin for
			
			Map parameter = new HashMap();
			parameter.put("Nombre", name);
			parameter.put("Fecha", UtilFechas.convertirFecha(fecha, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY));
			parameter.put("Edad", String.valueOf(Edad));
			
			JFileChooser ventana = new JFileChooser();		
			ventana.setSelectedFile(new File("Receta_"+name+".pdf"));
			int val = ventana.showSaveDialog(frame);
			String ruta = null;
	try{		
			if(val==0){
					ruta = ventana.getSelectedFile().toString();
					
					JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files//jasper//Receta.jasper");					
					JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parameter,source);
					JRExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta));
					exporter.exportReport();					
					///Segundo Archivo				
					source2.Depurar();
					String[] ruta2 = ruta.split(".pdf");
					ruta2[0] = ruta2[0]+"_F.pdf";
					JasperReport reporte2 = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files//jasper//RecetaFarma.jasper");
					JasperPrint jasperPrint2 = JasperFillManager.fillReport(reporte2,parameter,source2);
					JRExporter exporter2 = new JRPdfExporter();
					exporter2.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint2);
					exporter2.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta2[0]));
					exporter2.exportReport();
					//System.out.println("OK");
					JOptionPane.showMessageDialog(ventana, "Generación correcta ", "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
					Desktop.getDesktop().open(new File(ruta));	
					Desktop.getDesktop().open(new File(ruta2[0]));				
			}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error al generar Impresion");
			}
			
				
	}

}
