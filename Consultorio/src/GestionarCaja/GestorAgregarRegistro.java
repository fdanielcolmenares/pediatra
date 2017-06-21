package GestionarCaja;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ConexionBD.Conexion;
import Entidades.Historias;
import Entidades.historial_inventarios;
import Entidades.inventario;
import Entidades.registros_caja;
import GestionImpresiones.HiloImpresiones;
import GestionarHistorias.BuscarHistorias;
import GestionarInventario.BuscarInventarioDialog;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import VentanaPrincipal.VentanaPrincipal;

public class GestorAgregarRegistro {
	private VentanaAgregarRegistro ventana;
	private Autenticacion autenticacion;
	private VentanaAgregarConceptos vcctos;
	private VentanaAgregarTipoPago vtpgos;
	private List listaConceptos;
	private List listaTipoPagos;
	private String pendienteHistorial;
	private int pendienteIdProd;
	private int pendienteCantidad;
	private int tipoOperacion;
	private VentanaPrincipal vp;
	public int AGREGA = 0;
	public int DESCUENTA = 1;
	
	public GestorAgregarRegistro(VentanaAgregarRegistro v,Autenticacion a, VentanaPrincipal vprin){
		ventana = v;
		autenticacion = a;
		pendienteHistorial = "";
		pendienteIdProd = -1;
		pendienteCantidad = -1;
		tipoOperacion = -1;
		vp = vprin;
		
		vcctos = new VentanaAgregarConceptos(ventana.panel, autenticacion);
		vcctos.setDispose(false);
		vcctos.setOtraVentana(this);
		vcctos.actualizarConceptos();
		ventana.panel.add(vcctos.getVentana());
		
		vtpgos = new VentanaAgregarTipoPago(ventana.panel, autenticacion);
		vtpgos.setDispose(false);
		vtpgos.setOtraVentana(this);
		vtpgos.actualizarTipoPagos();
		vtpgos.panel.add(vtpgos.getVentana());
		
		listaConceptos = new ArrayList();
		listaTipoPagos = new ArrayList();
	}
	
	public void limpiarPendientes(){
		if(pendienteCantidad != -1 || pendienteIdProd != -1){
			ventana.getT_detalles().setText("");
		}
		pendienteCantidad = -1;
		pendienteHistorial = "";
		pendienteIdProd = -1;
		tipoOperacion = -1;
	}
	
	public void descuentaProducto(){
		BuscarInventarioDialog v = new BuscarInventarioDialog(BuscarInventarioDialog.DESCONTAR);
		v.setAutenticacion(autenticacion);
		v.setVisible(true);
		if(v.getCantidad()!=-1){
			ventana.getT_detalles().setText("Venta de "+v.getCantidad()+" unidad(es) de "+v.getSeleccionado());
			pendienteHistorial = "Descontado "+v.getCantidad()+" unidad(es) el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
								+" a las "+UtilFechas.getHoraActual();
			pendienteIdProd = v.getIdProd();
			pendienteCantidad = v.getCantidad();
			v.getVentana().dispose();
			tipoOperacion = DESCUENTA;
			//System.out.println("Descuenta "+v.getCantidad()+" unidades de "+v.getSeleccionado());
		}
	}
	
	public void agregarProducto(){
		BuscarInventarioDialog v = new BuscarInventarioDialog(BuscarInventarioDialog.AGREGAR);
		v.setAutenticacion(autenticacion);
		v.setVisible(true);
		if(v.getCantidad()!=-1){
			ventana.getT_detalles().setText("Compra de "+v.getCantidad()+" unidad(es) de "+v.getSeleccionado());
			pendienteHistorial = "Agrega "+v.getCantidad()+" unidad(es) el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
								+" a las "+UtilFechas.getHoraActual();
			pendienteIdProd = v.getIdProd();
			pendienteCantidad = v.getCantidad();
			v.getVentana().dispose();
			tipoOperacion = AGREGA;
			//System.out.println("Descuenta "+v.getCantidad()+" unidades de "+v.getSeleccionado());
		}
	}
	
	public void guardar(){
		if(listaConceptos.size()==0){
			ventana.mostrarMensaje("Debe agregar y seleccionar un concepto", ventana.ERROR);
		}
		else{
			if(listaTipoPagos.size()==0){
				ventana.mostrarMensaje("Debe agregar y seleccionar un tipo de pago", ventana.ERROR);
			}
			else{
				int error = 0;
				if(ventana.getT_detalles().getText().length()==0){
					ventana.mostrarMensaje("Debe ingresar los detalles", ventana.ERROR);
					error++;
				}
				else{
					if(ventana.getT_monto().getText().length()==0){
						ventana.mostrarMensaje("Debe ingresar el monto", ventana.ERROR);
						error++;
					}
					else{
						try{
							Float.parseFloat(ventana.getT_monto().getText());
						}
						catch(Exception e){
							ventana.mostrarMensaje("Debe ingresar un monto válido", ventana.ERROR);
							error++;
						}
						if(error == 0){
							int index = ventana.getT_conceptos().getSelectedIndex();
							int index2 = ventana.getT_tipoPago().getSelectedIndex();
							registros_caja rc = new registros_caja(autenticacion);
							rc.buscarNuevoID();
							rc.setHtra_id(ventana.getT_historia().getText());
							rc.setFecha(UtilFechas.convertirFecha(new Date(), UtilFechas.YYYY_MM_DD));
							rc.setHora(UtilFechas.getHoraActual());
							rc.setCcto_id(Integer.parseInt(listaConceptos.get(index).toString()));
							rc.setDetalles(ventana.getT_detalles().getText());
							rc.setMonto(Float.parseFloat(ventana.getT_monto().getText()));
							rc.setTpgo_id(Integer.parseInt(listaTipoPagos.get(index2).toString()));
							rc.setUsro_cedula(autenticacion.getCedulaUsuario());
							String retorna = rc.guardar();
							
							if(retorna.compareToIgnoreCase("Ok")==0){
								if(pendienteCantidad != -1 && pendienteIdProd !=-1){
									inventario inv = new inventario(autenticacion);
									inv.cargar(pendienteIdProd);
									if(tipoOperacion == AGREGA){
										inv.setCantidad(inv.getCantidad() + pendienteCantidad);
									}
									if(tipoOperacion == DESCUENTA){
										inv.setCantidad(inv.getCantidad() - pendienteCantidad);
									}
									inv.actualizar(pendienteIdProd);
										
									historial_inventarios historial = new historial_inventarios(autenticacion);
									historial.setObservacion(pendienteHistorial);
									historial.setUsro_cedula(Integer.parseInt(autenticacion.getCedulaUsuario()));
									historial.setIvro_id(pendienteIdProd);
									historial.buscarNuevoID();
									historial.guardar();
										
									limpiarPendientes();
								}
								else{
									System.out.println("No hay inventario");
									System.out.println(pendienteCantidad+"<->"+pendienteIdProd);
								}
								
								ventana.getT_fecha().setDate(new Date());
								ventana.getT_conceptos().setSelectedIndex(0);
								ventana.getT_detalles().setText("");
								ventana.getT_monto().setText("");
									
								ventana.mostrarMensaje("Registro Exitoso", ventana.MENSAJE);
								actualizarTabla();
							}
							else{
								ventana.mostrarMensaje(retorna, ventana.ERROR);
							}							
						}
					}
				}
			}
		}
	}
	
	public void actualizarTabla(){
		ventana.modeloTabla.setRowCount(0);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			String fecha = UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.YYYY_MM_DD);
			sql = "SELECT rc.id AS id, rc.htra_id AS htra_id, c.descripcion AS concepto, rc.detalles AS detalles, rc.monto AS monto, tc.descripcion AS tipo_pago "
					+"FROM registros_caja rc, conceptos c, tipo_pago tc "
					+"WHERE rc.ccto_id=c.id AND rc.tpgo_id=tc.id AND "
					+"fecha='"+fecha+"' " 
					+"ORDER BY id";
			java.sql.ResultSet res = con.consultar(sql);
			float total = 0;
			try {
				int cont = 0;
				while(res!=null && res.next()){
					ventana.modeloTabla.setRowCount(cont+1);
					ventana.getTabla().setValueAt(res.getString("htra_id").toString(), cont, 0);
					ventana.getTabla().setValueAt(res.getString("concepto").toString(), cont, 1);
					ventana.getTabla().setValueAt(res.getString("detalles").toString(), cont, 2);
					ventana.getTabla().setValueAt(res.getString("tipo_pago").toString(), cont, 3);
					ventana.getTabla().setValueAt(res.getString("monto").toString(), cont, 4);					
					total = total + Float.parseFloat(res.getString("monto").toString());
					cont++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ventana.getT_total().setText(String.valueOf(total));
			con.desconectar();
		}
	}
	
	
	public void actualizarConceptos(){
		ventana.getT_conceptos().removeAllItems();
		listaConceptos.clear();
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql = "SELECT id, descripcion FROM conceptos ORDER BY descripcion ASC";
			java.sql.ResultSet res = con.consultar(sql);
			try {
				while(res!= null && res.next()){
					ventana.getT_conceptos().addItem(res.getString("descripcion").toString());
					listaConceptos.add(res.getString("id").toString());
				}
			}
			catch (Exception e) {
			}
			con.desconectar();
		}
	}
	
	public void actualizarTipoPagos(){
		ventana.getT_tipoPago().removeAllItems();
		listaTipoPagos.clear();
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql = "SELECT id, descripcion FROM tipo_pago ORDER BY id ASC";
			java.sql.ResultSet res = con.consultar(sql);
			try {
				while(res!= null && res.next()){
					ventana.getT_tipoPago().addItem(res.getString("descripcion").toString());
					listaTipoPagos.add(res.getString("id").toString());
				}
			}
			catch (Exception e) {
			}
			con.desconectar();
		}
	}
	
	public void agregarConceptos(){
		vcctos.setVisible(true);
	}
	
	public void agregarTipoPagos(){
		vtpgos.setVisible(true);
	}

	public void cerrar(){
		vcctos.getVentana().dispose();
		ventana.getVentana().dispose();
	}
	
	public void exportarPDF(){
		/*PrintRegistrosCaja print = new PrintRegistrosCaja(autenticacion);
		print.cargarReporte(UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.YYYY_MM_DD));
		
		//print.mostrarReporte();
		
		JFileChooser buscador = new JFileChooser();		
        buscador.setSelectedFile(new File("Control_"+UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.DD_MM_YYYY)+".pdf"));
        String ruta;
        int d = buscador.showSaveDialog(null);
        if(d == JFileChooser.APPROVE_OPTION){
            ruta = buscador.getSelectedFile().toString();
            print.guardarReporte(ruta);
        }*/
		
		HiloImpresiones h = new HiloImpresiones(autenticacion);
		h.setTipoReporte(h.REPORTE_CAJA);
		h.setClase(ventana);
		h.start();
	}
	
	public void exportarExcel(){
		JFileChooser buscador = new JFileChooser();		
        buscador.setSelectedFile(new File("Control_"+UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.DD_MM_YYYY)+".xls"));
        String ruta;
        int d = buscador.showSaveDialog(null);
        if(d == JFileChooser.APPROVE_OPTION){
            ruta = buscador.getSelectedFile().toString();
            try{
                Workbook wb = new HSSFWorkbook();
                CreationHelper createHelper = wb.getCreationHelper();
                Sheet sheet = wb.createSheet("Registros de Caja");
                
                Row row = sheet.createRow((short)1);
                
                Font font = wb.createFont();
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                CellStyle style = wb.createCellStyle();
                style.setFont(font);
                style.setBorderBottom(CellStyle.BORDER_MEDIUM);
                style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderLeft(CellStyle.BORDER_MEDIUM);
                style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderRight(CellStyle.BORDER_MEDIUM);
                style.setRightBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderTop(CellStyle.BORDER_MEDIUM);
                style.setTopBorderColor(IndexedColors.BLACK.getIndex());

                CellStyle styleLeft = wb.createCellStyle();
                styleLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);
                styleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                CellStyle styleRight = wb.createCellStyle();
                styleRight.setFont(font);
                styleRight.setBorderRight(CellStyle.BORDER_MEDIUM);
                styleRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
                CellStyle styleLeftRight = wb.createCellStyle();
                //styleLeftRight.setFont(font);
                styleLeftRight.setBorderRight(CellStyle.BORDER_MEDIUM);
                styleLeftRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
                styleLeftRight.setBorderLeft(CellStyle.BORDER_MEDIUM);
                styleLeftRight.setLeftBorderColor(IndexedColors.BLACK.getIndex());                
                          
                CellStyle styleBottom = wb.createCellStyle();
                styleBottom.setBorderBottom(CellStyle.BORDER_MEDIUM);
                styleBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                styleBottom.setBorderLeft(CellStyle.BORDER_MEDIUM);
                styleBottom.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                styleBottom.setBorderRight(CellStyle.BORDER_MEDIUM);
                styleBottom.setRightBorderColor(IndexedColors.BLACK.getIndex());
                CellStyle styleLeftBottom = wb.createCellStyle();
                styleLeftBottom.setBorderLeft(CellStyle.BORDER_MEDIUM);
                styleLeftBottom.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                styleLeftBottom.setBorderBottom(CellStyle.BORDER_MEDIUM);
                styleLeftBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                CellStyle styleRightBottom = wb.createCellStyle();
                //styleRightBottom.setFont(font);
                styleRightBottom.setBorderRight(CellStyle.BORDER_MEDIUM);
                styleRightBottom.setRightBorderColor(IndexedColors.BLACK.getIndex());
                styleRightBottom.setBorderBottom(CellStyle.BORDER_MEDIUM);
                styleRightBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                styleRightBottom.setBorderLeft(CellStyle.BORDER_MEDIUM);
                styleRightBottom.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                
                row.createCell(0).setCellValue(createHelper.createRichTextString("Control de caja: "+UtilFechas.convertirFecha(ventana.getT_fecha().getDate(), UtilFechas.DD_MM_YYYY)));
                row.getCell(0).setCellStyle(style);
                
                row = sheet.createRow((short)3);
                row.createCell(1).setCellValue(createHelper.createRichTextString("Historia"));
                row.getCell(1).setCellStyle(style);
                row.createCell(2).setCellValue(createHelper.createRichTextString("Concepto"));
                row.getCell(2).setCellStyle(style);
                row.createCell(3).setCellValue(createHelper.createRichTextString("Detalles"));
                row.getCell(3).setCellStyle(style);
                row.createCell(4).setCellValue(createHelper.createRichTextString("Tipo de pago"));
                row.getCell(4).setCellStyle(style);
                row.createCell(5).setCellValue(createHelper.createRichTextString("Monto BsF."));
                row.getCell(5).setCellStyle(style);
                
                Row lastRow = null;
                int cont = 4;
                for(int i=0; i<ventana.getTabla().getRowCount(); i++){
                	row = sheet.createRow(i+4);
                	lastRow = row;
                	row.createCell(1).setCellValue(createHelper.createRichTextString(ventana.getTabla().getValueAt(i, 0).toString()));
                    row.getCell(1).setCellStyle(styleLeftRight);
                    row.createCell(2).setCellValue(createHelper.createRichTextString(ventana.getTabla().getValueAt(i, 1).toString()));
                    row.getCell(2).setCellStyle(styleLeftRight);
                    row.createCell(3).setCellValue(createHelper.createRichTextString(ventana.getTabla().getValueAt(i, 2).toString()));
                    row.getCell(3).setCellStyle(styleLeftRight);
                    row.createCell(4).setCellValue(createHelper.createRichTextString(ventana.getTabla().getValueAt(i, 3).toString()));
                    row.getCell(4).setCellStyle(styleLeftRight);
                    row.createCell(5).setCellValue(Double.parseDouble(ventana.getTabla().getValueAt(i, 4).toString()));
                    row.getCell(5).setCellStyle(styleLeftRight);
                    cont ++;
                }
                
                if(lastRow == null){
                	 lastRow = sheet.createRow(4);
                	 lastRow.createCell(1).setCellValue(createHelper.createRichTextString(""));
                	 lastRow.createCell(2).setCellValue(createHelper.createRichTextString("No hay"));
                	 lastRow.createCell(3).setCellValue(createHelper.createRichTextString("registros"));
                	 lastRow.createCell(4).setCellValue(createHelper.createRichTextString("para la fecha"));
                	 lastRow.createCell(5).setCellValue(createHelper.createRichTextString(""));
                	 
                }
                
                if(lastRow!=null){
                	lastRow.getCell(1).setCellStyle(styleLeftBottom);
                	lastRow.getCell(2).setCellStyle(styleBottom);
                	lastRow.getCell(3).setCellStyle(styleBottom);
                	lastRow.getCell(4).setCellStyle(styleBottom);
                	lastRow.getCell(5).setCellStyle(styleRightBottom);
                }
                
                row = sheet.createRow(cont+2);
                row.createCell(4).setCellValue(createHelper.createRichTextString("Total BsF.:"));
                row.getCell(4).setCellStyle(style);
                row.createCell(5).setCellValue(Double.parseDouble(ventana.getT_total().getText()));
                row.getCell(5).setCellStyle(style);
                
                for(int i=0; i<6; i++){
                    sheet.autoSizeColumn((short)i);
                }
                
                FileOutputStream fileOut = new FileOutputStream(ruta);
                try {
                    wb.write(fileOut);
                    fileOut.close();
                    ventana.mostrarMensaje("Generación Correcta", ventana.MENSAJE);                    
                    try {
                        Desktop.getDesktop().open(new File(ruta));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    ventana.mostrarMensaje("Ocurrio un error al exportar el archivo", ventana.ERROR);
                }
            }
            catch(Exception e){   
            	e.printStackTrace();
            }
        }
	}
	
	public void buscarPaciente(){
		Historias historia = new Historias(autenticacion);
		BuscarHistorias a = new BuscarHistorias(vp.getVentana(), true);
		a.setAutenticacion(autenticacion);	
		a.ChangeOptionChange(true);
		a.setVisibleDialog(true);
		if(a.getIDTable()>0){
			historia.buscarHistoriaNumero(a.getIDTable(), false);
			String id = "000000"+String.valueOf(historia.getId());
			id = id.substring(id.length()-6, id.length());
			ventana.getT_historia().setText(id);
		}
	}
}
