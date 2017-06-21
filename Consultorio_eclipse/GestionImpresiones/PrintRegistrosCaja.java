package GestionImpresiones;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Path;
import Utilitario.UtilFechas;

public class PrintRegistrosCaja {
	private Autenticacion autenticacion;
	private JasperPrint print;
	private boolean valido;
	
	public PrintRegistrosCaja(Autenticacion a){
		autenticacion = a;
		print = null;
		valido = false;
	}
	
	public boolean cargarReporte(String fecha){
		String sql;
		sql = "SELECT c.descripcion AS concepto, rc.detalles AS detalles, "
				+"tp.descripcion AS tipo_pago, rc.monto AS monto, rc.id,rc.htra_id AS htra_id "
				+"FROM conceptos c, registros_caja rc, tipo_pago tp "
				+"WHERE rc.ccto_id=c.id AND rc.tpgo_id=tp.id "
				+"AND rc.fecha='"+fecha+"' ORDER BY id ASC";
		
		//System.out.println(sql);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{
				Process_SourceDataListRegistrosCaja process = new Process_SourceDataListRegistrosCaja();
				while(res!=null && res.next()){
					SourceDataListRegistrosCaja dato = new SourceDataListRegistrosCaja();
					dato.setHtra_id(res.getString("htra_id").toString());
					dato.setConcepto(res.getString("concepto").toString());
					dato.setDetalles(res.getString("detalles").toString());
					dato.setDetalles(dato.getDetalles().replaceAll("\n", " "));
					dato.setTipo_pago(res.getString("tipo_pago").toString());
					dato.setMonto(res.getString("monto").toString());
					process.add(dato);
				}
				
				sql = "SELECT IFNULL((SELECT SUM(monto) FROM registros_caja WHERE fecha='"+fecha+"'), 0) AS total";
				res = con.consultar(sql);
				res.next();
			
				Map map = new Hashtable();
				map.put("fecha", UtilFechas.convertirFecha(fecha, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY));
				map.put("total", res.getString("total"));
				
				JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files/jasper/InformeCaja2.jasper");
				if(process.size()>0){
					print = JasperFillManager.fillReport(reporte, map, process);
				}
				else{	
					SourceDataListRegistrosCaja dato = new SourceDataListRegistrosCaja();
					dato.setConcepto("");
					dato.setDetalles("No hay registros para la fecha");
					dato.setTipo_pago("");
					dato.setMonto("");
					process.add(dato);
					print = JasperFillManager.fillReport(reporte, map, process);
				}
				valido = true;
			}
			catch(Exception e){				
				e.printStackTrace();
			}
			con.desconectar();
		}

		return true;
	}
	
	public void  mostrarReporte(){
		if(valido){
			JasperViewer visor = new JasperViewer(print, false);
			visor.setTitle("Informe de caja");
			visor.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Files/Imagenes/Image081.png")));
			visor.setVisible(true);
		}
	}
	
	public void guardarReporte(String ruta){
		if(valido){
			try{
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta));
				exporter.exportReport();
				JOptionPane.showMessageDialog(null, "Generación correcta ", "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));			
				Desktop.getDesktop().open(new File(ruta));
			}
			catch(Exception e){				
			}
		}
	}
}
