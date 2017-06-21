package GestionarVacunas;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GestionImpresiones.ImprimirVacunas;
import Utilitario.Autenticacion;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;


public class VentanaGestionarVacunas extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id_Historia;
	//GestorVacunas gestor ;
	//Vacunas vacuna;  //  @jve:decl-index=0:
	private JDesktopPane ventana;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="632,8"
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;  //  @jve:decl-index=0:visual-constraint="622,350"
	private JPanel jPanel2 = null;
	private JButton jButton = null;
	private Autenticacion autenticacion ;
	private CargaVacunas loadVac;
	private JButton edit_vac = null;
	private JButton print_vac = null;
	private String[] idVacunas;
	private JInternalFrame me = null;
	/**
	 * This is the xxx default constructor
	 */
	public VentanaGestionarVacunas(int Historia, Autenticacion a,JDesktopPane ventana) {
		super();	
		this.ventana = ventana;
		this.autenticacion = a;	
		///gestor = new GestorVacunas(Historia,a);
		loadVac = new CargaVacunas(a,Historia);
		idVacunas = loadVac.getIde();
		Id_Historia = Historia;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initialize();
		ventana.add(this);
		int x = Math.max(0, (ventana.getWidth() - this.getWidth()-15) / 2);
        int y = Math.max(0, (ventana.getHeight() - this.getHeight() -70) / 2);
        this.setLocation(new Point(x, y));
		this.setVisible(true);	
		me = this;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(567, 286);
		this.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image067.png")));
		this.setContentPane(getJPanel());
		this.setTitle("Historia de Vacunación del Paciente");
		this.setClosable(true);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setBounds(new Rectangle(15, 15, 527, 154));
			jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Historia de Vacunas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel1.add(getJScrollPane(), gridBagConstraints);
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
			jTable = new JTable();
			jTable.setModel(loadVac);
			jTable.getTableHeader().setReorderingAllowed(false);
			jTable.setSelectionMode(0);
		}
		
		
		return jTable;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBounds(new Rectangle(25, 173, 510, 70));
			jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Acciones con Vacunas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel2.add(getJButton(), null);
			jPanel2.add(getEdit_vac(), null);
			jPanel2.add(getprint_vac(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("Agregar Vacunas");
			jButton.setBounds(new Rectangle(20, 24, 150, 33));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//frame.dispose();
					new SaveVacuna(ventana,autenticacion,Id_Historia,jTable);
					loadVac.loadData(0);
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}
	
	
	private JButton getprint_vac() {
		if (print_vac == null) {
			print_vac = new JButton("Imprimir Vacunas");
			print_vac.setBounds(new Rectangle(180, 24, 150, 33));
			print_vac.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//frame.dispose();			
					
					if (jTable.getModel().getRowCount()>=1){
					new ImprimirVacunas(Id_Historia,me,autenticacion);
					}
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return print_vac;
	}
	
	/**
	 * This method initializes edit_vac	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEdit_vac() {
		if (edit_vac == null) {
			edit_vac = new JButton("Editar Vacuna");
			edit_vac.setBounds(new Rectangle(340, 24, 150, 33));
			edit_vac.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					//loadVac.lookDat();
					idVacunas = loadVac.getIde();
				if(jTable.getSelectedRow()>=0){
					SaveVacuna a = new SaveVacuna(ventana,autenticacion,Id_Historia,jTable);
					System.out.println("---------"+jTable.getSelectedRow()+"---"+idVacunas.length);
					a.preLoad(idVacunas[jTable.getSelectedRow()]);					
					System.out.println(idVacunas[jTable.getSelectedRow()]); // TODO Auto-generated Event stub actionPerformed()
				}
				}
			});
		}
		return edit_vac;
	}

	
}  //  @jve:decl-index=0:visual-constraint="12,5"
