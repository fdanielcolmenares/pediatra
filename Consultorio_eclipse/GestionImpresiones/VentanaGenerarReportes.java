package GestionImpresiones;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;

public class VentanaGenerarReportes {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="101,66"
	private JPanel jContentPane = null;
	private JPanel panelReportes = null;
	private JPanel panelDatos = null;
	private JLabel lbl_tipoReporte = null;
	private JLabel lbl_tupoReporte2 = null;
	private JComboBox listaReportes = null;
	private JButton btn_generar = null;
	private JButton btn_cancelar = null;
	public JDesktopPane panel;
	private GestorGenerarReportes gestor;  //  @jve:decl-index=0:
	private boolean habilitado;
	private JPanel panelNombreFecha = null;
	public JLabel lbl_campo1 = null;
	public JLabel lbl_campo2 = null;
	public JTextField t_campo1 = null;
	public JDateChooser t_campo2 = null;
	private JScrollPane scroll = null;
	public JLabel lbl_campo3 = null;
	public JLabel lbl_campo4 = null;
	public JLabel lbl_campo5 = null;
	public JLabel lbl_campo6 = null;
	public JLabel lbl_campo7 = null;
	public JTextField t_campo3 = null;
	public JTextField t_campo4 = null;
	public JTextField t_campo5 = null;
	public JTextField t_campo6 = null;
	public JTextField t_campo7 = null;
	public JLabel lbl_campo8 = null;
	public JTextField t_campo8 = null;
	
	public VentanaGenerarReportes(JDesktopPane p){
		panel = p;
		habilitado = false;
		getVentana();
		habilitado = true;
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorGenerarReportes g){
		gestor = g;
	}
	
	public void setVisible(boolean param){
		ventana.setVisible(param);
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(469, 386));
			ventana.setTitle("Generar Reportes");
			ventana.setClosable(true);
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image071.png")));
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
			jContentPane.add(getPanelReportes(), null);
			jContentPane.add(getPanelDatos(), null);
			jContentPane.add(getBtn_generar(), null);
			jContentPane.add(getBtn_cancelar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelReportes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelReportes() {
		if (panelReportes == null) {
			lbl_tupoReporte2 = new JLabel();
			lbl_tupoReporte2.setText("a generar:");
			lbl_tupoReporte2.setSize(new Dimension(138, 20));
			lbl_tupoReporte2.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_tupoReporte2.setLocation(new Point(20, 50));
			lbl_tipoReporte = new JLabel();
			lbl_tipoReporte.setText("Seleccione el reporte");
			lbl_tipoReporte.setSize(new Dimension(138, 20));
			lbl_tipoReporte.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_tipoReporte.setLocation(new Point(20, 30));
			panelReportes = new JPanel();
			panelReportes.setLayout(null);
			panelReportes.setBounds(new Rectangle(12, 11, 426, 94));
			panelReportes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Tipo de Reporte", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelReportes.add(lbl_tipoReporte, null);
			panelReportes.add(lbl_tupoReporte2, null);
			panelReportes.add(getListaReportes(), null);
		}
		return panelReportes;
	}

	/**
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.setBounds(new Rectangle(16, 124, 421, 176));
			panelDatos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Datos del Reporte", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelDatos.add(getScroll(), null);
		}
		return panelDatos;
	}

	/**
	 * This method initializes listaReportes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getListaReportes() {
		if (listaReportes == null) {
			listaReportes = new JComboBox();
			listaReportes.setEditable(false);
			listaReportes.setSize(new Dimension(220, 24));
			listaReportes.setLocation(new Point(168, 30));
			listaReportes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(habilitado)
						gestor.establecerPanel(listaReportes.getSelectedIndex());
				}
			});
			listaReportes.addItem("Alimentación de 6 a 8 meses");
			listaReportes.addItem("Alimentación de 8 a 12 meses");
			listaReportes.addItem("Alimentación a los 12 meses");
			listaReportes.addItem("Vitaminas para la madre");
			//listaReportes.addItem("Esquema de Crisis");
			listaReportes.addItem("Informe Médico");
			
		}
		return listaReportes;
	}

	/**
	 * This method initializes btn_generar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_generar() {
		if (btn_generar == null) {
			btn_generar = new JButton();
			btn_generar.setText("Generar");
			btn_generar.setMnemonic(KeyEvent.VK_G);
			btn_generar.setSize(new Dimension(120, 30));
			btn_generar.setLocation(new Point(91, 311));
			btn_generar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image070.png")));
			btn_generar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.generarReporte();
				}
			});
		}
		return btn_generar;
	}

	/**
	 * This method initializes btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cancelar() {
		if (btn_cancelar == null) {
			btn_cancelar = new JButton();
			btn_cancelar.setMnemonic(KeyEvent.VK_C);
			btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cancelar.setSize(new Dimension(120, 30));
			btn_cancelar.setLocation(new Point(256, 311));
			btn_cancelar.setText("Cancelar");
			btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.setVisible(false);
					ventana.dispose();
				}
			});
		}
		return btn_cancelar;
	}

	/**
	 * This method initializes panelNombreFecha	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanelNombreFecha() {
		if (panelNombreFecha == null) {
			lbl_campo8 = new JLabel();
			lbl_campo8.setText("Edad del paciente:");
			lbl_campo8.setSize(new Dimension(105, 20));
			lbl_campo8.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo8.setLocation(new Point(4, 80));
			lbl_campo7 = new JLabel();
			lbl_campo7.setText("Reposo por:");
			lbl_campo7.setSize(new Dimension(100, 20));
			lbl_campo7.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo7.setLocation(new Point(4, 230));
			lbl_campo6 = new JLabel();
			lbl_campo6.setText("Diagnóstico:");
			lbl_campo6.setSize(new Dimension(94, 20));
			lbl_campo6.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo6.setLocation(new Point(10, 200));
			lbl_campo5 = new JLabel();
			lbl_campo5.setText("Parentesco:");
			lbl_campo5.setSize(new Dimension(94, 20));
			lbl_campo5.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo5.setLocation(new Point(10, 170));
			lbl_campo4 = new JLabel();
			lbl_campo4.setText("C.I.:");
			lbl_campo4.setSize(new Dimension(94, 20));
			lbl_campo4.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo4.setLocation(new Point(10, 140));
			lbl_campo3 = new JLabel();
			lbl_campo3.setText("Representante:");
			lbl_campo3.setSize(new Dimension(94, 20));
			lbl_campo3.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo3.setLocation(new Point(10, 110));
			lbl_campo2 = new JLabel();
			lbl_campo2.setText("Fecha:");
			lbl_campo2.setSize(new Dimension(94, 20));
			lbl_campo2.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo2.setLocation(new Point(10, 50));
			lbl_campo1 = new JLabel();
			lbl_campo1.setText("Nombre:");
			lbl_campo1.setSize(new Dimension(94, 20));
			lbl_campo1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_campo1.setLocation(new Point(10, 20));
			panelNombreFecha = new JPanel();
			panelNombreFecha.setLayout(null);
			panelNombreFecha.add(lbl_campo1, null);
			panelNombreFecha.add(lbl_campo2, null);
			panelNombreFecha.add(getT_campo1(), null);
			panelNombreFecha.add(getT_campo2(), null);
			panelNombreFecha.add(lbl_campo3, null);
			panelNombreFecha.add(lbl_campo4, null);
			panelNombreFecha.add(lbl_campo5, null);
			panelNombreFecha.add(lbl_campo6, null);
			panelNombreFecha.add(lbl_campo7, null);
			panelNombreFecha.add(getT_campo3(), null);
			panelNombreFecha.add(getT_campo4(), null);
			panelNombreFecha.add(getT_campo5(), null);
			panelNombreFecha.add(getT_campo6(), null);
			panelNombreFecha.add(getT_campo7(), null);
			panelNombreFecha.add(lbl_campo8, null);
			panelNombreFecha.add(getT_campo8(), null);
		}
		return panelNombreFecha;
	}

	/**
	 * This method initializes t_campo1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo1() {
		if (t_campo1 == null) {
			t_campo1 = new JTextField();
			t_campo1.setLocation(new Point(108, 20));
			t_campo1.setSize(new Dimension(254, 20));
		}
		return t_campo1;
	}

	/**
	 * This method initializes t_campo2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getT_campo2() {
		if (t_campo2 == null) {
			t_campo2 = new JDateChooser();
			t_campo2.setSize(new Dimension(123, 20));
			t_campo2.setLocation(new Point(108, 50));
			t_campo2.setDate(new Date());
		}
		return t_campo2;
	}

	/**
	 * This method initializes scroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getScroll() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setBounds(new Rectangle(13, 27, 393, 138));
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(390, 235));
			scroll.setViewportView(getPanelNombreFecha());
			scroll.getVerticalScrollBar().setUnitIncrement(20);
		}
		return scroll;
	}

	/**
	 * This method initializes t_campo3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo3() {
		if (t_campo3 == null) {
			t_campo3 = new JTextField();
			t_campo3.setLocation(new Point(108, 110));
			t_campo3.setSize(new Dimension(254, 20));
		}
		return t_campo3;
	}

	/**
	 * This method initializes t_campo4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo4() {
		if (t_campo4 == null) {
			t_campo4 = new JTextField();
			t_campo4.setLocation(new Point(108, 140));
			t_campo4.setSize(new Dimension(123, 20));
		}
		return t_campo4;
	}

	/**
	 * This method initializes t_campo5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo5() {
		if (t_campo5 == null) {
			t_campo5 = new JTextField();
			t_campo5.setLocation(new Point(108, 170));
			t_campo5.setSize(new Dimension(123, 20));
		}
		return t_campo5;
	}

	/**
	 * This method initializes t_campo6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo6() {
		if (t_campo6 == null) {
			t_campo6 = new JTextField();
			t_campo6.setLocation(new Point(108, 200));
			t_campo6.setSize(new Dimension(254, 20));
		}
		return t_campo6;
	}

	/**
	 * This method initializes t_campo7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo7() {
		if (t_campo7 == null) {
			t_campo7 = new JTextField();
			t_campo7.setLocation(new Point(108, 230));
			t_campo7.setSize(new Dimension(123, 20));
		}
		return t_campo7;
	}

	/**
	 * This method initializes t_campo8	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_campo8() {
		if (t_campo8 == null) {
			t_campo8 = new JTextField();
			t_campo8.setLocation(new Point(110, 80));
			t_campo8.setSize(new Dimension(251, 20));
		}
		return t_campo8;
	}

}
