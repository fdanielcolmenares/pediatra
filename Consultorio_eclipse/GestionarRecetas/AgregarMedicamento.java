package GestionarRecetas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import Utilitario.Modelo_Tabla;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AgregarMedicamento {

	private JInternalFrame ventanaNewMedica = null;  //  @jve:decl-index=0:visual-constraint="155,14"
	private JPanel pnl_newMed = null;
	private GestorAgregarMedicamento gestor;  //  @jve:decl-index=0:
	private JTabbedPane jTabbedPane = null;
	private JPanel presentacion = null;
	private JPanel medicamento = null;
	private JLabel jLabel = null;
	private JTextField presenta = null;
	private JButton guarPre = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField nomMedi = null;
	private JLabel jLabel3 = null;
	private JComboBox presenta1 = null;
	private JButton guarMedi = null;
	private JLabel jLabel4 = null;
	private JTextArea desc = null;
	private JPanel pnl_dosis = null;
	private JLabel jLabel5 = null;
	private JRadioButton si_medi = null;
	private JRadioButton no_medi = null;
	private JTextArea dosis = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JTextField txt_dosis = null;
	private JButton add = null;
	private JList lst_dosis = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JRadioButton si_dosis = null;
	private JRadioButton no_dosis = null;
	private JButton actualizar = null;
	private JButton btn_guardar = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	public boolean newMedica,saveMedica=false;
	private JTable jTable = null;
	public Modelo_Tabla modelo,modelo2;  //  @jve:decl-index=0:visual-constraint="2,51"
	private JScrollPane jScrollPane2 = null;
	private JTable tablaP = null;
	
	public AgregarMedicamento(int w, int h, boolean ban){
		getVentanaNewMedica();
		int x = Math.max(0, (w - ventanaNewMedica.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventanaNewMedica.getHeight() -70) / 2);
        newMedica = ban;//true nuevo, false editar
        if(newMedica)
			ventanaNewMedica.setSize(new Dimension(335, 396));
		else
			ventanaNewMedica.setSize(new Dimension(335, 396)); 
		y-=87;
        ventanaNewMedica.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorAgregarMedicamento g){
		gestor = g;
            if(newMedica)    
            gestor.presenta();
	}
	
	/**
	 * This method initializes ventanaNewMedica	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentanaNewMedica() {
		if (ventanaNewMedica == null) {
			ventanaNewMedica = new JInternalFrame();
			ventanaNewMedica.setSize(new Dimension(335, 209));		
			ventanaNewMedica.setClosable(true);
			ventanaNewMedica.setTitle("Agregar Medicamento");
			ventanaNewMedica.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			ventanaNewMedica.setContentPane(getPnl_newMed());
		}
		return ventanaNewMedica;
	}

	/**
	 * This method initializes pnl_newMed	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnl_newMed() {
		if (pnl_newMed == null) {
			pnl_newMed = new JPanel();
			pnl_newMed.setLayout(new BorderLayout());
			pnl_newMed.setBackground(Color.white);
			pnl_newMed.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return pnl_newMed;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setName("");
			jTabbedPane.setEnabled(true);
			jTabbedPane.addTab("Presentacion", null, getPresentacion(), null);
			jTabbedPane.addTab("Medicamento", null, getMedicamento(), null);
			jTabbedPane.addTab("Dosis", null, getPnl_dosis(), null);
			jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(getJTabbedPane().getSelectedIndex()==1){
						getVentanaNewMedica().setSize(335,396);
						if(newMedica)
							gestor.cargarPresenta(0);
						else
							gestor.cargarPresenta(getPresenta1().getSelectedIndex());
					}
					if(getJTabbedPane().getSelectedIndex()==2){
						getVentanaNewMedica().setSize(335,343);
						if(!saveMedica && newMedica){
							JOptionPane.showMessageDialog(getVentanaNewMedica(), "Debe Guardar Meidcamento Primero", "Mensaje",
				                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
							getJTabbedPane().setSelectedIndex(1);
						}
					}
					if(getJTabbedPane().getSelectedIndex()==0){
						if(newMedica)
							getVentanaNewMedica().setSize(335,209);
						else
							getVentanaNewMedica().setSize(335,396);
					}
				}
			});
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes presentacion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPresentacion() {
		if (presentacion == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(31, 53, 154, 19));
			jLabel7.setText("Nombre de Presentacion:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(95, 16, 129, 22));
			jLabel.setText("Agregar Presentacion");
			presentacion = new JPanel();
			presentacion.setLayout(null);
			presentacion.setBackground(Color.white);
			presentacion.add(jLabel, null);
			presentacion.add(getPresenta(), null);
			presentacion.add(getGuarPre(), null);
			presentacion.add(jLabel7, null);
			presentacion.add(getJScrollPane2(), null);
		}
		return presentacion;
	}

	/**
	 * This method initializes medicamento	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMedicamento() {
		if (medicamento == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(31, 254, 55, 16));
			jLabel5.setText("Valido:");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(31, 152, 83, 23));
			jLabel4.setText("Descripcion:");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 98, 186, 22));
			jLabel3.setText("Seleccionar precentacion:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(28, 46, 153, 27));
			jLabel2.setText("Nombre del medicamento:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(95, 16, 136, 24));
			jLabel1.setText("Agregar Medicamento");
			medicamento = new JPanel();
			medicamento.setLayout(null);
			medicamento.setBackground(Color.white);
			medicamento.setFont(new Font("Dialog", Font.PLAIN, 14));
			medicamento.add(jLabel1, null);
			medicamento.add(jLabel2, null);
			medicamento.add(getNomMedi(), null);
			medicamento.add(jLabel3, null);
			medicamento.add(getPresenta1(), null);
			medicamento.add(getGuarMedi(), null);
			medicamento.add(jLabel4, null);
			medicamento.add(jLabel5, null);
			getGrupo();
			medicamento.add(getSi_medi(), null);
			medicamento.add(getNo_medi(), null);
			medicamento.add(getJScrollPane(), null);
		}
		return medicamento;
	}

	/**
	 * This method initializes presenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getPresenta() {
		if (presenta == null) {
			presenta = new JTextField();
			presenta.setBounds(new Rectangle(30, 79, 260, 20));
		}
		return presenta;
	}

	/**
	 * This method initializes guarPre	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuarPre() {
		if (guarPre == null) {
			guarPre = new JButton();
			guarPre.setBounds(new Rectangle(100, 108, 112, 30));
			guarPre.setText("Guardar");
			guarPre.setToolTipText("Guardar presentacion");
			guarPre.setBackground(Color.white);
			guarPre.setOpaque(true);
			guarPre.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			guarPre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(newMedica)
						gestor.guardarPresenta();
					else
						gestor.actualizarPre();
				}
			});
		}
		return guarPre;
	}

	/**
	 * This method initializes nomMedi	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getNomMedi() {
		if (nomMedi == null) {
			nomMedi = new JTextField();
			nomMedi.setBounds(new Rectangle(29, 72, 260, 20));
		}
		return nomMedi;
	}

	/**
	 * This method initializes presenta1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getPresenta1() {
		if (presenta1 == null) {
			presenta1 = new JComboBox();
			presenta1.setBounds(new Rectangle(30, 125, 250, 20));
		}
		return presenta1;
	}

	/**
	 * This method initializes guarMedi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getGuarMedi() {
		if (guarMedi == null) {
			guarMedi = new JButton();
			guarMedi.setBounds(new Rectangle(102, 301, 112, 30));
			guarMedi.setText("Guardar");
			guarMedi.setEnabled(false);
			guarMedi.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			guarMedi.setBackground(Color.white);
			guarMedi.setToolTipText("Guardar Medicamento");
			guarMedi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(newMedica)
						saveMedica=gestor.guardarMedica();
					else
						gestor.actualizarMedica();
				}
			});
		}
		return guarMedi;
	}

	/**
	 * This method initializes desc	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getDesc() {
		if (desc == null) {
			desc = new JTextArea();
			desc.setFont(new Font("Dialog", Font.PLAIN, 14));
			desc.setBackground(Color.white);
		}
		return desc;
	}

	/**
	 * This method initializes pnl_dosis	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnl_dosis() {
		if (pnl_dosis == null) {
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(241, 106, 46, 23));
			jLabel10.setText("Valido:");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(39, 104, 107, 23));
			jLabel9.setText("Dosis Existentes:");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(37, 44, 44, 20));
			jLabel8.setText("Dosis:");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(95, 16, 87, 16));
			jLabel6.setBackground(Color.white);
			jLabel6.setText("Agregar Dosis");
			pnl_dosis = new JPanel();
			pnl_dosis.setLayout(null);
			pnl_dosis.setEnabled(true);
			pnl_dosis.setBackground(Color.white);
			pnl_dosis.add(jLabel6, null);
			pnl_dosis.add(jLabel8, null);
			pnl_dosis.add(getTxt_dosis(), null);
			pnl_dosis.add(getAdd(), null);
			pnl_dosis.add(jLabel9, null);
			pnl_dosis.add(jLabel10, null);
			getGrupo2();
			pnl_dosis.add(getSi_dosis(), null);
			pnl_dosis.add(getNo_dosis(), null);
			pnl_dosis.add(getActualizar(), null);
			pnl_dosis.add(getBtn_guardar(), null);
			pnl_dosis.add(getJScrollPane1(), null);
		}
		return pnl_dosis;
	}

	/**
	 * This method initializes si_medi	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getSi_medi() {
		if (si_medi == null) {
			si_medi = new JRadioButton();
			si_medi.setBounds(new Rectangle(30, 276, 37, 21));
			si_medi.setSelected(true);
			si_medi.setText("Si");
			si_medi.setBackground(Color.white);
		}
		return si_medi;
	}

	/**
	 * This method initializes no_medi	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getNo_medi() {
		if (no_medi == null) {
			no_medi = new JRadioButton();
			no_medi.setBounds(new Rectangle(68, 276, 43, 21));
			no_medi.setText("No");
			no_medi.setBackground(Color.white);
		}
		return no_medi;
	}
	
	public void getGrupo(){
		ButtonGroup grupo= new ButtonGroup();
		grupo.add(getSi_medi());
		grupo.add(getNo_medi());
	}

	/**
	 * This method initializes dosis	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getDosis() {
		if (dosis == null) {
			dosis = new JTextArea();
		}
		return dosis;
	}

	/**
	 * This method initializes txt_dosis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxt_dosis() {
		if (txt_dosis == null) {
			txt_dosis = new JTextField();
			txt_dosis.setBounds(new Rectangle(39, 70, 198, 23));
		}
		return txt_dosis;
	}

	/**
	 * This method initializes add	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd() {
		if (add == null) {
			add = new JButton();
			add.setBounds(new Rectangle(247, 69, 62, 26));
			add.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			add.setBackground(Color.white);
			add.setToolTipText("Agregar Dosis");
			add.setText("");
			add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.guardarDosis();
				}
			});
		}
		return add;
	}

	/**
	 * This method initializes lst_dosis	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getLst_dosis() {
		if (lst_dosis == null) {
			lst_dosis = new JList();
		}
		return lst_dosis;
	}

	/**
	 * This method initializes si_dosis	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getSi_dosis() {
		if (si_dosis == null) {
			si_dosis = new JRadioButton();
			si_dosis.setBounds(new Rectangle(240, 136, 40, 14));
			si_dosis.setBackground(Color.white);
			si_dosis.setText("Si");
		}
		return si_dosis;
	}

	/**
	 * This method initializes no_dosis	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getNo_dosis() {
		if (no_dosis == null) {
			no_dosis = new JRadioButton();
			no_dosis.setBounds(new Rectangle(276, 133, 43, 21));
			no_dosis.setText("No");
			no_dosis.setBackground(Color.white);
		}
		return no_dosis;
	}
	
	public void getGrupo2(){
		ButtonGroup grupo= new ButtonGroup();
		grupo.add(getSi_dosis());
		grupo.add(getNo_dosis());
	}

	/**
	 * This method initializes actualizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getActualizar() {
		if (actualizar == null) {
			actualizar = new JButton();
			actualizar.setBounds(new Rectangle(247, 161, 56, 42));
			actualizar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			actualizar.setToolTipText("Actualizar estado de la dosis");
			actualizar.setBackground(Color.white);
			actualizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.actualizar();
				}
			});
		}
		return actualizar;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setBounds(new Rectangle(101, 243, 110, 32));
			btn_guardar.setToolTipText("Finalizar edicion de dosis");
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar.setText("Finalizar");
			btn_guardar.setBackground(Color.white);
			btn_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getVentanaNewMedica().dispose();
				}
			});
		}
		return btn_guardar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(31, 180, 250, 67));
			jScrollPane.setBackground(Color.white);
			jScrollPane.setViewportView(getDesc());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(41, 134, 197, 99));
			jScrollPane1.setViewportView(getJTable());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setAutoCreateColumnsFromModel(true);
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			modelo = new Modelo_Tabla(null,
					new String [] {"Numero","Dosis"});
			modelo.setRowCount(0);
			jTable.setModel(modelo);			
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					gestor.statusDosis();
				}
			});
			jTable.getTableHeader().setReorderingAllowed(false);
			jTable.getColumnModel().getColumn(0).setPreferredWidth( (int)(10*jTable.getWidth()/100));
			jTable.getColumnModel().getColumn(1).setPreferredWidth(80*jTable.getWidth()/100);
		}
		return jTable;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(19, 161, 284, 150));
			jScrollPane2.setVisible(true);
			jScrollPane2.setViewportView(getTablaP());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes tablaP	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaP() {
		if (tablaP == null) {
			tablaP = new JTable();
			tablaP.setAutoCreateColumnsFromModel(true);
			tablaP.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			modelo2 = new Modelo_Tabla(null,
					new String [] {"Numero","Dosis"});
			modelo2.setRowCount(0);
			tablaP.setModel(modelo2);			
			tablaP.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					gestor.editPre();
				}
			});
			tablaP.getTableHeader().setReorderingAllowed(false);
			tablaP.getColumnModel().getColumn(0).setPreferredWidth( (int)(10*tablaP.getWidth()/100));
			tablaP.getColumnModel().getColumn(1).setPreferredWidth(80*tablaP.getWidth()/100);
		}
		return tablaP;
	}

}  //  @jve:decl-index=0:visual-constraint="7,6"
