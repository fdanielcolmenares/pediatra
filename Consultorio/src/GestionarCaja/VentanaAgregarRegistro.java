package GestionarCaja;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import Utilitario.Modelo_Tabla;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.util.Date;

public class VentanaAgregarRegistro {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="121,48"
	private JPanel jContentPane = null;
	private JScrollPane scrollTabla = null;
	private JTable tabla = null;
	public Modelo_Tabla modeloTabla;
	private JPanel panelRegistros = null;
	private JDateChooser t_fecha = null;
	private JLabel lbl_1 = null;
	private JPanel panelNuevo = null;
	private JButton btn_cerrar = null;
	private JLabel lbl_2 = null;
	private JLabel lbl_3 = null;
	private JLabel lbl_4 = null;
	private JScrollPane scrollDescripcion = null;
	private JTextField t_monto = null;
	private JComboBox t_conceptos = null;
	private JButton btn_nuevo = null;
	private JTextArea t_detalles = null;
	private JButton btn_guardar = null;
	private JLabel lbl_total = null;
	private JTextField t_total = null;
	private GestorAgregarRegistro gestor;  //  @jve:decl-index=0:
	public JDesktopPane panel;
	public int ERROR = 1;
	public int MENSAJE = 2;
	private JLabel lbl_5 = null;
	private JComboBox t_tipoPago = null;
	private JButton btn_nuevoTipoPago = null;
	private JButton btn_exportarExcel = null;
	private JButton btn_exportarPDF = null;
	private JButton btn_producto = null;
	private JButton btn_producto2 = null;
	private JLabel jLabel = null;
	private JTextField t_historia = null;
	private JButton btn_buscar = null;
	public VentanaAgregarRegistro(JDesktopPane p){
		panel = p;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorAgregarRegistro g){
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
			ventana.setSize(new Dimension(533, 522));
			ventana.setClosable(true);
			ventana.setTitle("Registros de Caja");
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image081.png")));
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
			lbl_1.setText("Fecha:");
			lbl_1.setSize(new Dimension(69, 20));
			lbl_1.setLocation(new Point(20, 20));
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPanelRegistros(), null);
			jContentPane.add(getPanelNuevo(), null);
			jContentPane.add(getBtn_cerrar(), null);
			jContentPane.add(getBtn_guardar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes scrollTabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollTabla() {
		if (scrollTabla == null) {
			scrollTabla = new JScrollPane();
			scrollTabla.setSize(new Dimension(452, 125));
			scrollTabla.setLocation(new Point(20, 45));
			scrollTabla.setViewportView(getTabla());
		}
		return scrollTabla;
	}

	/**
	 * This method initializes tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			modeloTabla = new Modelo_Tabla(null,
					new String [] {"Historia", "Concepto", "Detalles", "Tipo de pago", "Monto BsF."});
			modeloTabla.setRowCount(0);
			tabla.setModel(modeloTabla);
			tabla.getTableHeader().setReorderingAllowed(false);
		}
		return tabla;
	}

	/**
	 * This method initializes panelRegistros	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelRegistros() {
		if (panelRegistros == null) {
			lbl_total = new JLabel();
			lbl_total.setPreferredSize(new Dimension(38, 20));
			lbl_total.setLocation(new Point(303, 180));
			lbl_total.setSize(new Dimension(61, 20));
			lbl_total.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_total.setText("Total:");
			panelRegistros = new JPanel();
			panelRegistros.setLayout(null);
			panelRegistros.setBounds(new Rectangle(15, 8, 495, 218));
			panelRegistros.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Todos los Registros", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelRegistros.add(getScrollTabla(), null);
			panelRegistros.add(lbl_1, null);
			panelRegistros.add(getT_fecha(), null);
			panelRegistros.add(lbl_total, null);
			panelRegistros.add(getT_total(), null);
			panelRegistros.add(getBtn_exportarExcel(), null);
			panelRegistros.add(getBtn_exportarPDF(), null);
		}
		return panelRegistros;
	}

	/**
	 * This method initializes t_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getT_fecha() {
		if (t_fecha == null) {
			t_fecha = new JDateChooser();
			t_fecha.setSize(new Dimension(126, 20));
			t_fecha.setLocation(new Point(96, 20));
			t_fecha.setDate(new Date());
			t_fecha.addPropertyChangeListener("date",
            		new java.beans.PropertyChangeListener() {
            	public void propertyChange(java.beans.PropertyChangeEvent e) {
            		gestor.actualizarTabla();
            	}
    });
		}
		return t_fecha;
	}

	/**
	 * This method initializes panelNuevo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelNuevo() {
		if (panelNuevo == null) {
			jLabel = new JLabel();
			jLabel.setText("Historia:");
			jLabel.setLocation(new Point(20, 40));
			jLabel.setSize(new Dimension(75, 20));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_5 = new JLabel();
			lbl_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_5.setLocation(new Point(10, 110));
			lbl_5.setSize(new Dimension(85, 20));
			lbl_5.setText("Tipo de pago:");
			lbl_4 = new JLabel();
			lbl_4.setText("Monto BsF.:");
			lbl_4.setSize(new Dimension(74, 20));
			lbl_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_4.setLocation(new Point(299, 110));
			lbl_3 = new JLabel();
			lbl_3.setText("Detalles:");
			lbl_3.setSize(new Dimension(75, 20));
			lbl_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_3.setLocation(new Point(20, 140));
			lbl_2 = new JLabel();
			lbl_2.setText("Concepto:");
			lbl_2.setSize(new Dimension(75, 20));
			lbl_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_2.setLocation(new Point(20, 80));
			panelNuevo = new JPanel();
			panelNuevo.setLayout(null);
			panelNuevo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Nuevo Registro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelNuevo.setSize(new Dimension(494, 209));
			panelNuevo.setLocation(new Point(15, 230));
			panelNuevo.add(lbl_2, null);
			panelNuevo.add(lbl_3, null);
			panelNuevo.add(lbl_4, null);
			panelNuevo.add(getScrollDescripcion(), null);
			panelNuevo.add(getT_monto(), null);
			panelNuevo.add(getT_conceptos(), null);
			panelNuevo.add(getBtn_nuevo(), null);
			panelNuevo.add(lbl_5, null);
			panelNuevo.add(getT_tipoPago(), null);
			panelNuevo.add(getBtn_nuevoTipoPago(), null);
			panelNuevo.add(getBtn_producto(), null);
			panelNuevo.add(getBtn_producto2(), null);
			panelNuevo.add(jLabel, null);
			panelNuevo.add(getT_historia(), null);
			panelNuevo.add(getBtn_buscar(), null);
		}
		return panelNuevo;
	}

	/**
	 * This method initializes btn_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar() {
		if (btn_cerrar == null) {
			btn_cerrar = new JButton();
			btn_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cerrar.setLocation(new Point(292, 450));
			btn_cerrar.setSize(new Dimension(115, 30));
			btn_cerrar.setMnemonic(KeyEvent.VK_C);
			btn_cerrar.setText("Cerrar");
			btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.cerrar();
				}
			});
		}
		return btn_cerrar;
	}

	/**
	 * This method initializes scrollDescripcion	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollDescripcion() {
		if (scrollDescripcion == null) {
			scrollDescripcion = new JScrollPane();
			scrollDescripcion.setSize(new Dimension(282, 49));
			scrollDescripcion.setViewportView(getT_detalles());
			scrollDescripcion.setLocation(new Point(100, 140));
		}
		return scrollDescripcion;
	}

	/**
	 * This method initializes t_monto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_monto() {
		if (t_monto == null) {
			t_monto = new JTextField();
			t_monto.setText("");
			t_monto.setLocation(new Point(378, 110));
			t_monto.setSize(new Dimension(100, 20));
		}
		return t_monto;
	}

	/**
	 * This method initializes t_conceptos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getT_conceptos() {
		if (t_conceptos == null) {
			t_conceptos = new JComboBox();
			t_conceptos.setSize(new Dimension(160, 20));
			t_conceptos.setLocation(new Point(100, 80));
			t_conceptos.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					gestor.limpiarPendientes();
				}
			});
		}
		return t_conceptos;
	}

	/**
	 * This method initializes btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_nuevo() {
		if (btn_nuevo == null) {
			btn_nuevo = new JButton();
			btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image074.png")));
			btn_nuevo.setSize(new Dimension(30, 30));
			btn_nuevo.setToolTipText("Agregar Concepto");
			btn_nuevo.setLocation(new Point(268, 74));
			btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarConceptos();
				}
			});
		}
		return btn_nuevo;
	}

	/**
	 * This method initializes t_detalles	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_detalles() {
		if (t_detalles == null) {
			t_detalles = new JTextArea();
		}
		return t_detalles;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setText("Guardar");
			btn_guardar.setSize(new Dimension(115, 30));
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar.setMnemonic(KeyEvent.VK_G);
			btn_guardar.setLocation(new Point(116, 450));
			btn_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Guardar?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
						gestor.guardar();
					}
				}
			});
		}
		return btn_guardar;
	}

	/**
	 * This method initializes t_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_total() {
		if (t_total == null) {
			t_total = new JTextField();
			t_total.setLocation(new Point(370, 180));
			t_total.setEditable(false);
			t_total.setBackground(Color.white);
			t_total.setSize(new Dimension(100, 20));
		}
		return t_total;
	}

	/**
	 * This method initializes t_tipoPago	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getT_tipoPago() {
		if (t_tipoPago == null) {
			t_tipoPago = new JComboBox();
			t_tipoPago.setSize(new Dimension(160, 20));
			t_tipoPago.setLocation(new Point(100, 110));
		}
		return t_tipoPago;
	}

	/**
	 * This method initializes btn_nuevoTipoPago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_nuevoTipoPago() {
		if (btn_nuevoTipoPago == null) {
			btn_nuevoTipoPago = new JButton();
			btn_nuevoTipoPago.setLocation(new Point(268, 106));
			btn_nuevoTipoPago.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image074.png")));
			btn_nuevoTipoPago.setSize(new Dimension(30, 30));
			btn_nuevoTipoPago.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarTipoPagos();
				}
			});
		}
		return btn_nuevoTipoPago;
	}

	/**
	 * This method initializes btn_exportarExcel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_exportarExcel() {
		if (btn_exportarExcel == null) {
			btn_exportarExcel = new JButton();
			btn_exportarExcel.setLocation(new Point(23, 177));
			btn_exportarExcel.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image086.png")));
			btn_exportarExcel.setText("Excel");
			btn_exportarExcel.setSize(new Dimension(110, 30));
			btn_exportarExcel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.exportarExcel();
				}
			});
		}
		return btn_exportarExcel;
	}

	/**
	 * This method initializes btn_exportarPDF	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_exportarPDF() {
		if (btn_exportarPDF == null) {
			btn_exportarPDF = new JButton();
			btn_exportarPDF.setLocation(new Point(151, 177));
			btn_exportarPDF.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image055.png")));
			btn_exportarPDF.setText("PDF");
			btn_exportarPDF.setSize(new Dimension(110, 30));
			btn_exportarPDF.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.exportarPDF();
				}
			});
		}
		return btn_exportarPDF;
	}

	/**
	 * This method initializes btn_producto	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_producto() {
		if (btn_producto == null) {
			btn_producto = new JButton();
			btn_producto.setLocation(new Point(315, 74));
			btn_producto.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image100.png")));
			btn_producto.setSize(new Dimension(30, 30));
			btn_producto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.descuentaProducto();
				}
			});
		}
		return btn_producto;
	}

	/**
	 * This method initializes btn_producto2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_producto2() {
		if (btn_producto2 == null) {
			btn_producto2 = new JButton();
			btn_producto2.setLocation(new Point(355, 74));
			btn_producto2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image101.png")));
			btn_producto2.setSize(new Dimension(30, 30));
			btn_producto2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarProducto();
				}
			});
		}
		return btn_producto2;
	}

	/**
	 * This method initializes t_historia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_historia() {
		if (t_historia == null) {
			t_historia = new JTextField();
			t_historia.setSize(new Dimension(94, 20));
			t_historia.setLocation(new Point(100, 40));
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
			btn_buscar.setText("Buscar");
			btn_buscar.setSize(new Dimension(100, 25));
			btn_buscar.setLocation(new Point(205, 37));
			btn_buscar.setMnemonic(KeyEvent.VK_B);
			btn_buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			btn_buscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarPaciente();
				}
			});
		}
		return btn_buscar;
	}

}
