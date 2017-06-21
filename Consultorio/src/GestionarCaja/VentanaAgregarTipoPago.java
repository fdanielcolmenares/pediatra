package GestionarCaja;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import ConexionBD.Conexion;
import Entidades.tipo_pago;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import javax.swing.WindowConstants;

public class VentanaAgregarTipoPago {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="137,48"
	private JPanel jContentPane = null;
	private JPanel panelConceptos = null;
	private JScrollPane jScrollPane = null;
	private JTable tabla = null;
	private Modelo_Tabla modelo;
	private JButton btn_guardar = null;
	private JButton btn_cerrar = null;
	private JButton btn_nuevo = null;
	private JLabel lbl_1 = null;
	private JTextField t_descripcion = null;
	private GestorAgregarRegistro otraVentana = null;  //  @jve:decl-index=0:
	public JDesktopPane panel;
	private Autenticacion autenticacion;
	private List lista;
	private int idTipoPago;
	public int ERROR = 1;
	public int MENSAJE = 2;
	public boolean dispose;
	
	public VentanaAgregarTipoPago(JDesktopPane p, Autenticacion a){
		panel = p;
		autenticacion = a;
		lista = new ArrayList();
		idTipoPago = -1;
		dispose = true;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void cerrar(){
		if(dispose){
			ventana.dispose();
		}
		else{
			ventana.setVisible(false);
		}
	}
	
	public void guardar(){
		tipo_pago tpgo = new tipo_pago(autenticacion);
		if(idTipoPago == -1){
			tpgo.buscarNuevoID();
			tpgo.setDescripcion(t_descripcion.getText());
			String retorna = tpgo.guardar();
			if(retorna.compareToIgnoreCase("Ok")==0){
				mostrarMensaje("Registro exitoso", MENSAJE);
				idTipoPago = tpgo.getId();
				actualizarTipoPagos();
				if(otraVentana!=null){
					otraVentana.actualizarTipoPagos();
				}
				
			}
			else{
				mostrarMensaje(retorna, ERROR);
			}
		}
		else{
			tpgo.setId(idTipoPago);
			tpgo.setDescripcion(t_descripcion.getText());
			String retorna = tpgo.actualizar();
			if(retorna.compareToIgnoreCase("Ok")==0){
				mostrarMensaje("Actualización exitosa", MENSAJE);
				actualizarTipoPagos();
				if(otraVentana!=null){
					otraVentana.actualizarConceptos();
				}
			}
			else{
				mostrarMensaje(retorna, ERROR);
			}
		}
	}
	
	public void actualizarTipoPagos(){
		lista.clear();
		modelo.setRowCount(0);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql = "SELECT id, descripcion FROM tipo_pago ORDER BY descripcion ASC";
			java.sql.ResultSet res = con.consultar(sql);
			try {
				int cont = 0;
				while(res!= null && res.next()){
					modelo.setRowCount(cont+1);
					tabla.setValueAt(res.getString("descripcion").toString(), cont, 0);
					lista.add(res.getString("id"));
					cont++;
				}
			}
			catch (Exception e) {
			}
			con.desconectar();
		}
	}
	
	public void seleccionaConcepto(){
		int index = tabla.getSelectedRow();
		if(index >= 0){
			t_descripcion.setText(tabla.getValueAt(index, 0).toString());
			idTipoPago = Integer.parseInt(lista.get(index).toString());
		}
	}
	
	public void nuevo(){
		t_descripcion.setText("");
		t_descripcion.setEditable(true);
		idTipoPago = -1;
	}
	
	public void setOtraVentana(GestorAgregarRegistro g){
		otraVentana = g;
	}
	
	public void setVisible(boolean param){
		ventana.setVisible(param);
		
		if(param){
			int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
	        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
	        ventana.setLocation(new Point(x, y));
		}
	}
	
	public void mostrarMensaje(String msj, int tipo){
		if(tipo == ERROR){
			JOptionPane.showMessageDialog(ventana, msj, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		if(tipo == MENSAJE){
			JOptionPane.showMessageDialog(ventana, msj, "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		}
	}
	
	public void setDispose(boolean param){
		dispose = param;
		if(param){
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		else{
			ventana.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		}
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(382, 295));
			ventana.setClosable(true);
			ventana.setTitle("Tipos de pago");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image084.png")));
			ventana.setContentPane(getJContentPane());
		}
		return ventana;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbl_1 = new JLabel();
			lbl_1.setText("Descripción:");
			lbl_1.setSize(new Dimension(101, 20));
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_1.setLocation(new Point(17, 179));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPanelConceptos(), null);
			jContentPane.add(getBtn_guardar(), null);
			jContentPane.add(getBtn_cerrar(), null);
			jContentPane.add(getBtn_nuevo(), null);
			jContentPane.add(lbl_1, null);
			jContentPane.add(getT_descripcion(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelConceptos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelConceptos() {
		if (panelConceptos == null) {
			panelConceptos = new JPanel();
			panelConceptos.setLayout(null);
			panelConceptos.setBounds(new Rectangle(22, 13, 327, 147));
			panelConceptos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Conceptos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelConceptos.add(getJScrollPane(), null);
		}
		return panelConceptos;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(54, 21, 239, 111));
			jScrollPane.setViewportView(getTabla());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			modelo = new Modelo_Tabla(null,
					new String [] {"Descripción"});
			modelo.setRowCount(0);
			tabla.setModel(modelo);
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					seleccionaConcepto();
				}
			});
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						seleccionaConcepto();
					}
				}
			});
		}
		return tabla;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setLocation(new Point(127, 218));
			btn_guardar.setText("Guardar");
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar.setMnemonic(KeyEvent.VK_G);
			btn_guardar.setSize(new Dimension(115, 30));
			btn_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_descripcion.getText().length()==0){
						mostrarMensaje("Debe ingresar una descripción", ERROR);
					}
					else{
						if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Guardar?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
							guardar();
						}
					}
				}
			});
		}
		return btn_guardar;
	}

	/**
	 * This method initializes btn_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar() {
		if (btn_cerrar == null) {
			btn_cerrar = new JButton();
			btn_cerrar.setText("Cerrar");
			btn_cerrar.setSize(new Dimension(115, 30));
			btn_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cerrar.setMnemonic(KeyEvent.VK_C);
			btn_cerrar.setLocation(new Point(247, 218));
			btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrar();
				}
			});
		}
		return btn_cerrar;
	}

	/**
	 * This method initializes btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_nuevo() {
		if (btn_nuevo == null) {
			btn_nuevo = new JButton();
			btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image074.png")));
			btn_nuevo.setLocation(new Point(5, 218));
			btn_nuevo.setSize(new Dimension(115, 30));
			btn_nuevo.setText("Nuevo");
			btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nuevo();
				}
			});
		}
		return btn_nuevo;
	}

	/**
	 * This method initializes t_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_descripcion() {
		if (t_descripcion == null) {
			t_descripcion = new JTextField();
			t_descripcion.setLocation(new Point(125, 179));
			t_descripcion.setEditable(true);
			t_descripcion.setSize(new Dimension(200, 20));
		}
		return t_descripcion;
	}

}
