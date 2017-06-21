package GestionImpresiones;

import Utilitario.Path;
import java.awt.Desktop;
import java.io.File;
import java.sql.ResultSet;

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

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class PrintHistoriasAplication {
		private Autenticacion auten;
		private ResultSet resul = null;
		private Conexion conex;
		private JInternalFrame jFrame;
		private String query = null;
		
		public PrintHistoriasAplication(Autenticacion a, JInternalFrame iFrame){
				this.auten = a;
				this.jFrame = iFrame;
		}
		
		public void setQuery(String consul){
			query = "SELECT id, UPPER(nombre) AS nombre FROM historias WHERE "+consul;
			query = query +" AND LENGTH(nombre)>5 ORDER BY nombre";
		}
	
		void GeneraReporte(boolean close){
			conex = new Conexion(auten);
			Process_SourceDataListHistorias process = new Process_SourceDataListHistorias();
			conex.conectar();
			//System.out.println(query);
			///String query = "SELECT id,UPPER(nombre) FROM historias";
			resul = conex.consultar(query);
			
			try {
				while(resul.next()){
					SourceDataListHistorias h = new SourceDataListHistorias(Integer.parseInt(resul.getString(1)),resul.getString(2));
					process.addHistoria(h);
				}//fin while
			conex.desconectar();
			//System.out.println(process.getIndice());
//			/OPERACIONES JASPER REPORT
			///RUTA
			JFileChooser ventana = new JFileChooser();
			ventana.setSelectedFile(new File("Historias.pdf"));
			int val = ventana.showSaveDialog(jFrame);
			String ruta = null;
			if(val==0){
					ruta = ventana.getSelectedFile().toString();
					JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files//jasper//Historia.jasper");
					JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,process);
					JRExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta));
					exporter.exportReport();
					//System.out.println("OK");
					JOptionPane.showMessageDialog(ventana, "Generación correcta ", "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
					Desktop.getDesktop().open(new File(ruta));
					if(close){
						jFrame.dispose();
					}
			}
			///RUTA
			
			//OPERACIONES JASPER REPORT
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
