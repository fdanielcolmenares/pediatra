package GestionarInventario;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Utilitario.Modelo_Tabla;

import java.awt.Point;

public class TipoMedicamento {

	private JInternalFrame Ventana = null;  //  @jve:decl-index=0:visual-constraint="96,27"
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel Agregar = null;
	private JPanel jPanel = null;
	private JPanel Editar = null;
	private JLabel jLabel = null;
	private JTextField NombreTipo = null;
	private JButton Guardar = null;
	private JScrollPane jScrollPane = null;
	private JButton Guardar1 = null;
	private JLabel jLabel1 = null;
	private JTextField editTipo = null;
	private JTable Tabla = null;
	private GestorTipoMedicamento gestor;  //  @jve:decl-index=0:
	public Modelo_Tabla modelo = null;

	public TipoMedicamento(int w, int h){
		getVentana();
		int x = Math.max(0, (w - 510-15) / 2); 
        int y = Math.max(0, (h - 227 -70) / 2);
        Ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorTipoMedicamento g){
		gestor = g;
	}
	/**
	 * This method initializes Ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (Ventana == null) {
			Ventana = new JInternalFrame();
			Ventana.setSize(new Dimension(231, 174));
			Ventana.setClosable(true);
			Ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			Ventana.setTitle("Tipos de Producto");
			Ventana.setContentPane(getJContentPane());
		}
		return Ventana;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(Color.white);
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setName("");
			jTabbedPane.addTab("Agregar Tipo", null, getAgregar(), null);
			jTabbedPane.addTab("Editar Tipo", null, getEditar(), null);
			jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(jTabbedPane.getSelectedIndex()==1){
						gestor.validar_tipo();
						gestor.llenarTipos();
						getVentana().setSize(510, 227);
					}
					if(jTabbedPane.getSelectedIndex()==0){
						getVentana().setSize(231,174);
					}
				}
			});
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes Agregar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAgregar() {
		if (Agregar == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 109, 19));
			jLabel.setText("Ingresar Nombre:");
			Agregar = new JPanel();
			Agregar.setLayout(null);
			Agregar.add(getJPanel(), null);
			Agregar.add(jLabel, null);
			Agregar.add(getNombreTipo(), null);
			Agregar.add(getGuardar(), null);
		}
		return Agregar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(0, 0, 0, 0));
		}
		return jPanel;
	}

	/**
	 * This method initializes Editar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEditar() {
		if (Editar == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(287, 38, 64, 19));
			jLabel1.setText("Editar:");
			Editar = new JPanel();
			Editar.setLayout(null);
			Editar.add(getJScrollPane(), null);
			Editar.add(getGuardar1(), null);
			Editar.add(jLabel1, null);
			Editar.add(getEditTipo(), null);
		}
		return Editar;
	}

	/**
	 * This method initializes NombreTipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getNombreTipo() {
		if (NombreTipo == null) {
			NombreTipo = new JTextField();
			NombreTipo.setBounds(new Rectangle(15, 45, 186, 20));
		}
		return NombreTipo;
	}

	/**
	 * This method initializes Guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (Guardar == null) {
			Guardar = new JButton();
			Guardar.setBounds(new Rectangle(43, 73, 112, 28));
			Guardar.setMnemonic(KeyEvent.VK_G);
			Guardar.setText("Guardar");
			Guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			Guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.guardar();
				}
			});
		}
		return Guardar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 10, 267, 145));
			jScrollPane.setViewportView(getTabla());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes Guardar1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar1() {
		if (Guardar1 == null) {
			Guardar1 = new JButton();
			Guardar1.setBounds(new Rectangle(337, 101, 105, 28));
			Guardar1.setMnemonic(KeyEvent.VK_G);
			Guardar1.setText("Guardar");
			Guardar1.setToolTipText("Guardar Modificacion Tipo Medicamento");
			Guardar1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			Guardar1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.actualizarTipo();
				}
			});
		}
		return Guardar1;
	}

	/**
	 * This method initializes editTipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getEditTipo() {
		if (editTipo == null) {
			editTipo = new JTextField();
			editTipo.setLocation(new Point(287, 71));
			editTipo.setToolTipText("Edite el nombre del tipo de producto");
			editTipo.setSize(new Dimension(196, 20));
		}
		return editTipo;
	}

	/**
	 * This method initializes Tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTabla() {
		if (Tabla == null) {
			Tabla = new JTable();
			Tabla.setAutoCreateColumnsFromModel(true);
			Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			modelo = new Modelo_Tabla(null,
					new String [] {"Numero","tipo"});
			modelo.setRowCount(0);
			Tabla.setModel(modelo);			
			Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					gestor.modificar();
				}
			});
			Tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode()==10){
						gestor.modificar();
					}
				}
			});
			Tabla.getTableHeader().setReorderingAllowed(false);
			Tabla.getColumnModel().getColumn(0).setPreferredWidth( (int)(10*Tabla.getWidth()/100));
			Tabla.getColumnModel().getColumn(1).setPreferredWidth(80*Tabla.getWidth()/100);
		}
		return Tabla;
	}

}  //  @jve:decl-index=0:visual-constraint="10,34"
