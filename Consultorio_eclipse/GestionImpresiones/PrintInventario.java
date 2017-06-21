package GestionImpresiones;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.util.Date;
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

public class PrintInventario {
	private Autenticacion autenticacion;
	private JasperPrint print;
	private boolean valido;
	
	public PrintInventario(Autenticacion a){
		autenticacion = a;
		print = null;
		valido = false;
	}
	
	public boolean cargarReporte(){
		String sql;
		sql = "SELECT i.descripcion AS nombre, i.cantidad AS cantidad, ti.descripcion AS tipo "
				+"FROM inventario i, tipo_inventario ti "
				+"WHERE i.tiro_id=ti.id AND i.valido='s' "
				+"ORDER BY nombre ASC";
		
		//System.out.println(sql);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{
				Process_SourceDataListInventario process = new Process_SourceDataListInventario();
				while(res!=null && res.next()){
					SourceDataListInventario dato = new SourceDataListInventario();
					dato.setNombre(res.getString("nombre").toString());
					dato.setCantidad(res.getString("cantidad").toString());
					dato.setTipo(res.getString("tipo").toString());
					process.add(dato);
				}				
			
				Map map = new Hashtable();
				map.put("fecha", UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY));
				
				JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files/jasper/InformeInventario.jasper");
				if(process.size()>0){
					print = JasperFillManager.fillReport(reporte, map,  process);
				}
				else{	
					SourceDataListInventario dato = new SourceDataListInventario();
					dato.setNombre("No hay productos");
					dato.setCantidad("");
					dato.setTipo("");
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
			visor.setTitle("Informe de inventario");
			visor.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Files/Imagenes/Image072.png")));
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
