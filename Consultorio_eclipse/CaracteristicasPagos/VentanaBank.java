package CaracteristicasPagos;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Utilitario.Modelo_Tabla;

public class VentanaBank extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6463327855150100838L;
	private JPanel jContentPane = null;
	private JTabbedPane TabPanel = null;
	private JPanel PanelBank = null;
	private JPanel PanelPay = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private Modelo_Tabla tabla, tabla2;
	private JLabel t_banco = null;
	private JTextField tf_banco = null;
	private JButton b_guardar = null;
	private GestorPagos p = null;  //  @jve:decl-index=0:
	private JScrollPane ScrollPay = null;
	private JTable jTablePay = null;
	private JLabel l_conceps = null;
	private JTextField t_conceps = null;
	private JButton jButton = null;
	/**
	 * This is the xxx default constructor
	 */
	public VentanaBank() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(480, 250);
		p = new GestorPagos();
		this.setContentPane(getJContentPane());
		this.setTitle("Agregar Caracteristicas de Pagos");
		this.setVisible(true);
		
		//modeltable = new DefaultTableModel(new Object[][]{{null,null}},new Object[]{null});
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
			jContentPane.add(getTabPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes TabPanel	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabPanel() {
		if (TabPanel == null) {
			TabPanel = new JTabbedPane();
			TabPanel.setTabPlacement(JTabbedPane.LEFT);
			TabPanel.setBounds(new Rectangle(1, 1, 467, 216));
			TabPanel.setFont(new Font("Dialog", Font.BOLD, 14));
			TabPanel.addTab("Bancos", null, getPanelBank(), null);
			TabPanel.addTab("Conceptos", null, getPanelPay(), null);
			
		}
		return TabPanel;
	}

	/**
	 * This method initializes PanelBank	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBank() {
		if (PanelBank == null) {
			t_banco = new JLabel();
			t_banco.setBounds(new Rectangle(228, 68, 49, 16));
			t_banco.setText("Banco:");
			PanelBank = new JPanel();
			PanelBank.setLayout(null);
			PanelBank.add(getJScrollPane(), null);
			PanelBank.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Agregar o Modificar Bancos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 14), Color.BLACK));
			PanelBank.add(t_banco, null);
			PanelBank.add(getTf_banco(), null);
			PanelBank.add(getB_guardar(), null);
		}
		return PanelBank;
	}

	/**
	 * This method initializes PanelPay	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelPay() {
		if (PanelPay == null) {
			l_conceps = new JLabel();
			l_conceps.setBounds(new Rectangle(228, 68, 81, 16));
			l_conceps.setText("Concepto:");
			PanelPay = new JPanel();
			PanelPay.setLayout(null);
			PanelPay.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Agregar o Modificar Conceptos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 14), Color.BLACK));
			PanelPay.add(getScrollPay(), null);
			PanelPay.add(l_conceps, null);
			PanelPay.add(getT_conceps(), null);
			PanelPay.add(getJButton(), null);
		}
		return PanelPay;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 31, 153, 168));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();	///Configuracion Basica de la Tabla
			jTable.getTableHeader().setReorderingAllowed(false);  ///Comando para que no se pueda deslizar la tabla   
			jTable.setSelectionMode(0);///codigo para seleccion simple			
			
			tabla = new Modelo_Tabla(new Object [][] {///declaracion de los componentes de la tabla
	                {null},{null},{null},{null},{null},{null},
	                {null},{null},{null},{null}                
	            }, new String [] {
	                "Bancos"});
			
			
			p.Load_table(tabla, "Bancos","BANCOS","NOMBRE");			
			jTable.setModel(tabla);///estableciendo modelo de la tabla
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					///System.out.println(jTable.getSelectedRow()); // TODO Auto-generated Event stub mouseClicked()
					
					if(tabla.getValueAt(jTable.getSelectedRow(), 0).toString().compareTo("NUEVO...")!=0){
					tf_banco.setText(tabla.getValueAt(jTable.getSelectedRow(), 0).toString());
					}else{
						tf_banco.setText("");
					}
					
					//tabla.getValueAt(jTable.getSelectedRow(), 0);	
				}
			});
			jTable.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					//System.out.println("keyReleased()"); // TODO Auto-generated Event stub keyReleased()
					if(tabla.getValueAt(jTable.getSelectedRow(), 0).toString().compareTo("NUEVO...")!=0){
						tf_banco.setText(tabla.getValueAt(jTable.getSelectedRow(), 0).toString());
						}else{
							tf_banco.setText("");
						}
				
				}
			});
		}
		return jTable;
	}

	/**
	 * This method initializes tf_banco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTf_banco() {
		if (tf_banco == null) {
			tf_banco = new JTextField();
			tf_banco.setBounds(new Rectangle(182, 100, 149, 20));
		}
		return tf_banco;
	}

	/**
	 * This method initializes b_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_guardar() {
		if (b_guardar == null) {
			b_guardar = new JButton();
			b_guardar.setBounds(new Rectangle(208, 134, 88, 41));
			b_guardar.setText("Guardar");
			b_guardar.setToolTipText("Guardar");
			b_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			if(tf_banco.getText().compareTo("")!=0&&tf_banco.getText()!=null){		
					if(jTable.getSelectedRow()>0){						
						if(JOptionPane.showConfirmDialog(new JFrame(), "Seguro que desea Modificar este Banco?", "Mensaje", JOptionPane.YES_NO_OPTION)== 0){
							p.actualizar(tabla.getValueAt(jTable.getSelectedRow(), 0).toString(),tf_banco.getText(),"BANCOS","NOMBRE");
						    p.Load_table(tabla, "Bancos","BANCOS","NOMBRE");
						}//fin if 1
					}	//fin if 2		
							
					if(jTable.getSelectedRow()==0){
						if(JOptionPane.showConfirmDialog(new JFrame(), "Seguro que desea Agregar este Banco?", "Mensaje", JOptionPane.YES_NO_OPTION)== 0){
							p.insertar(tf_banco.getText(), "BANCOS","NOMBRE");
						    p.Load_table(tabla, "Bancos","BANCOS","NOMBRE");
						}//fin if 1
						
					}//fin if
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"No se Permite Insertar caracteres Nulos.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
				
			}//fin fi grande
					
					
				/*	if(jTable.getSelectedRow()==-1){
						 JOptionPane.showMessageDialog(new JFrame(),"Seleccion Nuevo para ingresar un nuevo Banco\n o Seleccione un Banco para modificarlo.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					}//fin if*/
				}
							
								
			});
		}
		return b_guardar;
	}

	/**
	 * This method initializes ScrollPay	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPay() {
		if (ScrollPay == null) {
			ScrollPay = new JScrollPane();
			ScrollPay.setBounds(new Rectangle(16, 31, 153, 168));
			ScrollPay.setViewportView(getJTablePay());
		}
		return ScrollPay;
	}

	/**
	 * This method initializes jTablePay	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTablePay() {
		if (jTablePay == null) {
			jTablePay = new JTable();
			jTablePay.getTableHeader().setReorderingAllowed(false);  ///Comando para que no se pueda deslizar la tabla   
			jTablePay.setSelectionMode(0);///codigo para seleccion simple	
			tabla2 = new Modelo_Tabla(new Object [][] {///declaracion de los componentes de la tabla
	                {null},{null},{null},{null},{null},{null},
	                {null},{null},{null},{null}                
	            }, new String [] {
	                "Conceptos"});
			
			
			p.Load_table(tabla2, "Conceptos","CONCEPTOS","DESCRIPCION");
			jTablePay.setModel(tabla2);
			jTablePay.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					///System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				
					if(tabla2.getValueAt(jTablePay.getSelectedRow(), 0).toString().compareTo("NUEVO...")!=0){
						t_conceps.setText(tabla2.getValueAt(jTablePay.getSelectedRow(), 0).toString());
						}else{
							t_conceps.setText("");
						}
				
				
				}
			});
			jTablePay.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					//System.out.println("keyReleased()"); // TODO Auto-generated Event stub keyReleased()
					if(tabla2.getValueAt(jTablePay.getSelectedRow(), 0).toString().compareTo("NUEVO...")!=0){
						t_conceps.setText(tabla2.getValueAt(jTablePay.getSelectedRow(), 0).toString());
						}else{
							t_conceps.setText("");
						}
				
				}
			});
		}
		return jTablePay;
	}

	/**
	 * This method initializes t_conceps	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_conceps() {
		if (t_conceps == null) {
			t_conceps = new JTextField();
			t_conceps.setBounds(new Rectangle(182, 100, 149, 20));
		}
		return t_conceps;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(208, 134, 88, 41));
			jButton.setText("Guardar");
			jButton.setToolTipText("Guardar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				///----------------------------------
					if(t_conceps.getText().compareTo("")!=0&&t_conceps.getText()!=null){	
					if(jTablePay.getSelectedRow()>0){						
						if(JOptionPane.showConfirmDialog(new JFrame(), "Seguro que desea Modificar este Concepto?", "Mensaje", JOptionPane.YES_NO_OPTION)== 0){
							p.actualizar(tabla2.getValueAt(jTablePay.getSelectedRow(), 0).toString(),t_conceps.getText(),"CONCEPTOS","DESCRIPCION");
						    p.Load_table(tabla2, "Conceptos","CONCEPTOS","DESCRIPCION");
						}//fin if 1
					}	//fin if 2		
							
					if(jTablePay.getSelectedRow()==0){
						if(JOptionPane.showConfirmDialog(new JFrame(), "Seguro que desea Agregar este Concepto?", "Mensaje", JOptionPane.YES_NO_OPTION)== 0){
							p.insertar(t_conceps.getText(), "CONCEPTOS","DESCRIPCION");
						    p.Load_table(tabla2, "Conceptos","CONCEPTOS","DESCRIPCION");
						}//fin if 1
						
					}//fin if
					}else{
						
						JOptionPane.showMessageDialog(new JFrame(),"No se Permite Insertar caracteres Nulos.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					}
				/*	if(jTable.getSelectedRow()==-1){
						 JOptionPane.showMessageDialog(new JFrame(),"Seleccion Nuevo para ingresar un nuevo Banco\n o Seleccione un Banco para modificarlo.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					}//fin if*/
			
				
				///--------------------------------
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
