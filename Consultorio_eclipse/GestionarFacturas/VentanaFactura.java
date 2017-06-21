package GestionarFacturas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.Point;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GestionarConsultas.tablaConsultas;
import Utilitario.Modelo_Tabla;
import Utilitario.Render;
import Utilitario.UtilFechas;

import java.awt.event.KeyEvent;
import java.util.Date;

public class VentanaFactura {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="29,27"
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JTextField t_nombre = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField t_historia = null;
	private JButton btn_buscar = null;

	private GestorFactura gestor;  //  @jve:decl-index=0:
	public JDesktopPane panel;
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane = null;
	private JTable tablaItems = null;
	private JButton btn_guardar = null;
	private JButton btn_cancelar = null;
	private JButton btn_agregar = null;
	private JButton btn_eliminar = null;
	private Modelo_Tabla modeloTabla;
	private Modelo_Tabla modeloPagos;
	private JLabel jLabel2 = null;
	private JTextField t_total = null;
	private JPanel jPanel2 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable tablaPagos = null;
	private JLabel jLabel3 = null;
	private JTextField t_resta = null;
	private JLabel jLabel4 = null;
	private JTextField t_totalPago = null;
	private JButton btn_agregarPago = null;
	private JButton btn_eliminarPago = null;
	public int ERROR = 1;
	public int MENSAJE = 2;
	
	public VentanaFactura(JDesktopPane p){
		panel = p;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight()-70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorFactura g){
		gestor = g;
	}
	
	public void setVisible(boolean param){
		ventana.setVisible(param);
	}
	
	public void mostrarMensaje(String msj, int tipo){
		if(tipo == ERROR){
			JOptionPane.showMessageDialog(ventana, msj, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		if(tipo == MENSAJE){
			JOptionPane.showMessageDialog(ventana, msj, "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
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
			ventana.setSize(new Dimension(512, 540));
			ventana.setTitle("Registro de Caja");
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image081.png")));
			ventana.setClosable(true);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getBtn_guardar(), null);
			jContentPane.add(getBtn_cancelar(), null);
			jContentPane.add(getJPanel2(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Número de historia:");
			jLabel1.setSize(new Dimension(145, 20));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 14));
			jLabel1.setLocation(new Point(21, 25));
			jLabel = new JLabel();
			jLabel.setText("Nombre del paciente:");
			jLabel.setSize(new Dimension(145, 20));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			jLabel.setLocation(new Point(21, 55));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Historia", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			jPanel.setSize(new Dimension(470, 91));
			jPanel.setLocation(new Point(10, 5));
			jPanel.add(getT_nombre(), null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(getT_historia(), null);
			jPanel.add(getBtn_buscar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes t_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_nombre() {
		if (t_nombre == null) {
			t_nombre = new JTextField();
			t_nombre.setLocation(new Point(172, 55));
			t_nombre.setEditable(false);
			t_nombre.setBackground(Color.white);
			t_nombre.setSize(new Dimension(285, 20));
		}
		return t_nombre;
	}

	/**
	 * This method initializes t_historia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_historia() {
		if (t_historia == null) {
			t_historia = new JTextField();
			t_historia.setLocation(new Point(172, 25));
			t_historia.setEditable(false);
			t_historia.setBackground(Color.white);
			t_historia.setSize(new Dimension(124, 20));
		}
		return t_historia;
	}

	/**
	 * This method initializes btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_buscar() {
		if (btn_buscar == null) {
			btn_buscar = new JButton();
			btn_buscar.setLocation(new Point(340, 21));
			btn_buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			btn_buscar.setText("Buscar");
			btn_buscar.setSize(new Dimension(100, 25));
			btn_buscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarHistoria();
				}
			});
		}
		return btn_buscar;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Total:");
			jLabel2.setSize(new Dimension(51, 20));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setLocation(new Point(285, 170));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setLocation(new Point(10, 100));
			jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Detalles", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			jPanel1.setSize(new Dimension(470, 212));
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(getBtn_agregar(), null);
			jPanel1.add(getBtn_eliminar(), null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getT_total(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(14, 24, 437, 136));
			jScrollPane.setViewportView(getTablaItems());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tablaItems	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTablaItems() {
		if (tablaItems == null) {
			tablaItems = new JTable();
			modeloTabla = new Modelo_Tabla(null,
					new String [] {"Concepto", "Detalles", "Monto", "Cant.", "Total"});
			modeloTabla.setRowCount(0);
			tablaItems.setModel(modeloTabla);
			tablaItems.getTableHeader().setReorderingAllowed(false);
		}
		return tablaItems;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setLocation(new Point(357, 377));
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar.setText("Guardar");
			btn_guardar.setMnemonic(KeyEvent.VK_G);
			btn_guardar.setSize(new Dimension(120, 30));
		}
		return btn_guardar;
	}

	/**
	 * This method initializes btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cancelar() {
		if (btn_cancelar == null) {
			btn_cancelar = new JButton();
			btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cancelar.setLocation(new Point(358, 438));
			btn_cancelar.setSize(new Dimension(120, 30));
			btn_cancelar.setText("Cancelar");
			btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.dispose();
				}
			});
		}
		return btn_cancelar;
	}

	/**
	 * This method initializes btn_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_agregar() {
		if (btn_agregar == null) {
			btn_agregar = new JButton();
			btn_agregar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			btn_agregar.setSize(new Dimension(30, 30));
			btn_agregar.setText("");
			btn_agregar.setLocation(new Point(22, 165));
		}
		return btn_agregar;
	}

	/**
	 * This method initializes btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_eliminar() {
		if (btn_eliminar == null) {
			btn_eliminar = new JButton();
			btn_eliminar.setText("");
			btn_eliminar.setLocation(new Point(69, 165));
			btn_eliminar.setSize(new Dimension(30, 30));
			btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image087.png")));
			btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaItems.getSelectedRow();
					if(index>=0){
						gestor.eliminarItem(index);
					}
				}
			});
		}
		return btn_eliminar;
	}

	/**
	 * This method initializes t_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_total() {
		if (t_total == null) {
			t_total = new JTextField();
			t_total.setLocation(new Point(341, 170));
			t_total.setEditable(false);
			t_total.setBackground(Color.white);
			t_total.setSize(new Dimension(103, 20));
		}
		return t_total;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Total:");
			jLabel4.setSize(new Dimension(53, 20));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setLocation(new Point(112, 122));
			jLabel3 = new JLabel();
			jLabel3.setText("Resta:");
			jLabel3.setSize(new Dimension(53, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setLocation(new Point(112, 144));
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Pagos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			jPanel2.setSize(new Dimension(302, 176));
			jPanel2.setLocation(new Point(17, 317));
			jPanel2.add(getJScrollPane1(), null);
			jPanel2.add(jLabel3, null);
			jPanel2.add(getT_resta(), null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(getT_totalPago(), null);
			jPanel2.add(getBtn_agregarPago(), null);
			jPanel2.add(getBtn_eliminarPago(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(10, 21, 273, 95));
			jScrollPane1.setViewportView(getTablaPagos());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tablaPagos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaPagos() {
		if (tablaPagos == null) {
			tablaPagos = new JTable();
			tablaPagos.setRowHeight(20);
			modeloPagos = new Modelo_Tabla(null,
					new String [] {"Fecha", "Forma de Pago", "Monto"});
			modeloPagos.setRowCount(0);
			tablaPagos.setModel(modeloPagos);			
			tablaPagos.getTableHeader().setReorderingAllowed(false);
		}
		return tablaPagos;
	}

	/**
	 * This method initializes t_resta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_resta() {
		if (t_resta == null) {
			t_resta = new JTextField();
			t_resta.setLocation(new Point(170, 144));
			t_resta.setEditable(false);
			t_resta.setBackground(Color.white);
			t_resta.setSize(new Dimension(103, 20));
		}
		return t_resta;
	}

	/**
	 * This method initializes t_totalPago	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_totalPago() {
		if (t_totalPago == null) {
			t_totalPago = new JTextField();
			t_totalPago.setLocation(new Point(170, 122));
			t_totalPago.setEditable(false);
			t_totalPago.setBackground(Color.white);
			t_totalPago.setSize(new Dimension(103, 20));
		}
		return t_totalPago;
	}

	/**
	 * This method initializes btn_agregarPago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_agregarPago() {
		if (btn_agregarPago == null) {
			btn_agregarPago = new JButton();
			btn_agregarPago.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			btn_agregarPago.setSize(new Dimension(30, 30));
			btn_agregarPago.setLocation(new Point(15, 122));
			btn_agregarPago.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.mostrarDialogPagos();
				}
			});
		}
		return btn_agregarPago;
	}

	/**
	 * This method initializes btn_eliminarPago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_eliminarPago() {
		if (btn_eliminarPago == null) {
			btn_eliminarPago = new JButton();
			btn_eliminarPago.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image087.png")));
			btn_eliminarPago.setSize(new Dimension(30, 30));
			btn_eliminarPago.setLocation(new Point(56, 122));
			btn_eliminarPago.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaPagos.getSelectedRow();
					if(index >= 0){
						gestor.eliminarPago(index);
					}
				}
			});
		}
		return btn_eliminarPago;
	}

	public Modelo_Tabla getModeloPagos() {
		return modeloPagos;
	}

	public void setModeloPagos(Modelo_Tabla modeloPagos) {
		this.modeloPagos = modeloPagos;
	}

	public Modelo_Tabla getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(Modelo_Tabla modeloTabla) {
		this.modeloTabla = modeloTabla;
	}
}
