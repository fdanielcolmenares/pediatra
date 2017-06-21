package GestionarConsultas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ConsultaDetallada {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="45,-3"
	private JScrollPane scrollVentana = null;  //  @jve:decl-index=0:
	private JPanel panelVentana = null;  //  @jve:decl-index=0:
	private JLabel lbl_1 = null;
	private JTextField t_nombre = null;
	private JLabel lbl_3 = null;
	public JLabel t_historia = null;
	private JButton btn_guardar2 = null;
	private GestorConsultaDetallada gestor;  //  @jve:decl-index=0:
	private JButton jButton = null;
	public JLabel lbl_fecha = null;
	private JButton b_Receta = null;
	private JPanel panelActual = null;
	private JTextField t_peso = null;
	private JLabel lbl_8 = null;
	private JLabel lbl_9 = null;
	private JTextField t_talla = null;
	private JLabel lbl_10 = null;
	private JLabel lbl_11 = null;
	private JScrollPane scroll_5 = null;
	private JTextArea t_motivoActual = null;
	private JScrollPane scroll_6 = null;
	private JTextArea t_diagnosticoActual = null;
	private JLabel lbl_12 = null;
	private JScrollPane scroll_7 = null;
	private JTextArea t_observaciones = null;
	private JLabel lbl_2 = null;
	private JTextField t_edad = null;
	private JButton btn_crearReceta = null;
	private JButton btn_guardar1 = null;
	private JButton btn_guardar21 = null;
	private JLabel lbl_13 = null;
	private JTextField t_saturacion = null;
	private JLabel lbl_15 = null;
	private JTextField t_indice = null;
	private JButton btn_examenes = null;
	private JLabel jLabel = null;
	private JTextField t_fc = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField t_temperatura = null;
	private JLabel jLabel3 = null;
	private JTextField t_frecuenciaRespiratoria = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel4 = null;
	private JTextField t_tas = null;
	private JLabel jLabel5 = null;
	private JTextField t_tad = null;
	private JLabel jLabel6 = null;
	private JTextField t_pf = null;
	
	public ConsultaDetallada(int w, int h, String numhis){
		getVentana();
		int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
        //gestor.llenarPrimera();
	}
	
	public void setGestor(GestorConsultaDetallada g){
		gestor = g;
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(aFlag);
	}

	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setVisible(false);
			ventana.setTitle("Ver consulta");
			ventana.setSize(new Dimension(689, 412));
			ventana.setResizable(true);
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image058.png")));
			ventana.setContentPane(getScrollVentana());
			ventana.setClosable(true);
		}
		return ventana;
	}

	/**
	 * This method initializes scrollVentana	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollVentana(){
		if(scrollVentana == null){
			 scrollVentana = new JScrollPane();
			 scrollVentana.setSize(600, 600);
			 getPanelVentana();
			 scrollVentana.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			 scrollVentana.getVerticalScrollBar().setUnitIncrement(20);
		     GroupLayout jPanelLayout = new GroupLayout(getPanelVentana());
		     panelVentana.setLayout(jPanelLayout);
		     jPanelLayout.setHorizontalGroup(
		         jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 630, Short.MAX_VALUE)
		     );
		     jPanelLayout.setVerticalGroup(
		         jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 570, Short.MAX_VALUE)
		     );
		     scrollVentana.setViewportView(getPanelVentana());
		}
		return scrollVentana;
	}

	/**
	 * This method initializes panelVentana	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelVentana() {
		if (panelVentana == null) {
			lbl_fecha = new JLabel();
			lbl_fecha.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_fecha.setLocation(new Point(466, 8));
			lbl_fecha.setSize(new Dimension(180, 20));
			lbl_fecha.setForeground(Color.black);
			lbl_fecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_fecha.setText("Fecha:");
			t_historia = new JLabel();
			t_historia.setFont(new Font("Dialog", Font.BOLD, 20));
			t_historia.setSize(new Dimension(82, 37));
			t_historia.setLocation(new Point(118, 26));
			t_historia.setText("000000");
			lbl_3 = new JLabel();
			lbl_3.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_3.setLocation(new Point(22, 36));
			lbl_3.setSize(new Dimension(94, 20));
			lbl_3.setHorizontalTextPosition(SwingConstants.RIGHT);
			lbl_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_3.setText("Historia N°:");
			panelVentana = new JPanel();
			panelVentana.setLayout(null);
			panelVentana.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			panelVentana.setSize(new Dimension(755, 1050));
			panelVentana.add(getLbl_1(), null);
			panelVentana.add(getT_nombre(), null);
			panelVentana.add(lbl_3, null);
			panelVentana.add(t_historia, null);
			panelVentana.add(getBtn_guardar2(), null);
			panelVentana.add(getJButton(), null);
			panelVentana.add(lbl_fecha, null);
			panelVentana.add(getB_Receta(), null);
			panelVentana.add(getPanelActual(), null);
		}
		return panelVentana;
	}

	/**
	 * This method initializes lbl_1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLbl_1() {
		if (lbl_1 == null) {
			lbl_1 = new JLabel();
			lbl_1.setText("Paciente:");
			lbl_1.setLocation(new Point(243, 36));
			lbl_1.setSize(new Dimension(79, 20));
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		}
		return lbl_1;
	}
	/**
	 * This method initializes t_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_nombre() {
		if (t_nombre == null) {
			t_nombre = new JTextField();
			t_nombre.setSize(new Dimension(319, 20));
			t_nombre.setEditable(false);
			t_nombre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_nombre.setLocation(new Point(330, 36));
		}
		return t_nombre;
	}

	/**
	 * This method initializes btn_guardar2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar2() {
		if (btn_guardar2 == null) {
			btn_guardar2 = new JButton();
			btn_guardar2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image022.png")));
			btn_guardar2.setBackground(Color.white);
			btn_guardar2.setLocation(new Point(585, 494));
			btn_guardar2.setSize(new Dimension(60, 60));
			btn_guardar2.setToolTipText("Cerrar");
			btn_guardar2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					getVentana().dispose();
				}
			});
		}
		return btn_guardar2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setLocation(new Point(510, 494));
			jButton.setBackground(Color.white);
			jButton.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			jButton.setSize(new Dimension(60, 60));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					/*int id = consulta.getId();
					//System.out.println(id);
					Date d = new Date();
					String dd = String.valueOf(d.getDate());
					String mm = String.valueOf(d.getMonth()+1);
					String yy = String.valueOf(d.getYear()+1900);
					if(dd.length()==1) dd = "0" + dd;
					if(mm.length()==1) mm = "0" + mm;
					String fecha = dd+"/"+mm+"/"+yy;
					String nombre = ventana.getT_nombre().getText();
					String edad = ventana.getT_edad().getText();
					
					GestorListadeRecetas gestorListadeRecetas = new GestorListadeRecetas(id, edad, fecha, nombrautenticacion, desktopPane);*/
				}
				
			});
			jButton.setVisible(false);
		}
		return jButton;
	}

	/**
	 * This method initializes b_Receta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_Receta() {
		if (b_Receta == null) {
			b_Receta = new JButton();
			b_Receta.setBounds(new Rectangle(500, 494, 60, 60));
			b_Receta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image045.png")));
			b_Receta.setToolTipText("Ver Receta");
			b_Receta.setBackground(Color.WHITE);
			b_Receta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
						gestor.verReceta();	
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return b_Receta;
	}

	/**
	 * This method initializes panelActual	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelActual() {
		if (panelActual == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(380, 28, 120, 20));
			jLabel6.setToolTipText("Pico Flujo");
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("P.F.:");
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel5.setLocation(new Point(380, 120));
			jLabel5.setSize(new Dimension(120, 20));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("T.A.D.:");
			jLabel4 = new JLabel();
			jLabel4.setLocation(new Point(246, 120));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel4.setToolTipText("Tensión arterial sistólica");
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("T.A.S.:");
			jLabel4.setSize(new Dimension(57, 20));
			jLabel11 = new JLabel();
			jLabel11.setLocation(new Point(193, 120));
			jLabel11.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel11.setText("x MIN");
			jLabel11.setSize(new Dimension(39, 20));
			jLabel3 = new JLabel();
			jLabel3.setLocation(new Point(68, 120));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel3.setToolTipText("Frecuencia respiratoria");
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("F.R.:");
			jLabel3.setSize(new Dimension(52, 20));
			jLabel2 = new JLabel();
			jLabel2.setLocation(new Point(380, 90));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Temperatura:");
			jLabel2.setSize(new Dimension(120, 20));
			jLabel1 = new JLabel();
			jLabel1.setText("x MIN");
			jLabel1.setSize(new Dimension(45, 20));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel1.setLocation(new Point(575, 60));
			jLabel = new JLabel();
			jLabel.setText("F.C.:");
			jLabel.setSize(new Dimension(120, 20));
			jLabel.setToolTipText("Frecuencia Cardíaca");
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setLocation(new Point(380, 60));
			lbl_15 = new JLabel();
			lbl_15.setLocation(new Point(230, 90));
			lbl_15.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_15.setToolTipText("Indice de masa corporal");
			lbl_15.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_15.setText("I.M.C.:");
			lbl_15.setSize(new Dimension(71, 20));
			lbl_13 = new JLabel();
			lbl_13.setLocation(new Point(60, 90));
			lbl_13.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_13.setToolTipText("Saturación de Oxígeno");
			lbl_13.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_13.setText("S.O.:");
			lbl_13.setSize(new Dimension(62, 20));
			lbl_2 = new JLabel();
			lbl_2.setBounds(new Rectangle(73, 29, 50, 20));
			lbl_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_2.setText("Edad:");
			lbl_2.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_12 = new JLabel();
			lbl_12.setLocation(new Point(0, 313));
			lbl_12.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_12.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_12.setText("Examen Físico:");
			lbl_12.setSize(new Dimension(128, 20));
			lbl_11 = new JLabel();
			lbl_11.setLocation(new Point(20, 237));
			lbl_11.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_11.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_11.setText("Diagnóstico:");
			lbl_11.setSize(new Dimension(103, 20));
			lbl_10 = new JLabel();
			lbl_10.setLocation(new Point(43, 161));
			lbl_10.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_10.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_10.setText("Motivo:");
			lbl_10.setSize(new Dimension(80, 20));
			lbl_9 = new JLabel();
			lbl_9.setLocation(new Point(235, 60));
			lbl_9.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_9.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_9.setText("Talla:");
			lbl_9.setSize(new Dimension(65, 20));
			lbl_8 = new JLabel();
			lbl_8.setLocation(new Point(60, 60));
			lbl_8.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_8.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_8.setText("Peso:");
			lbl_8.setSize(new Dimension(63, 20));
			panelActual = new JPanel();
			panelActual.setLayout(null);
			panelActual.setBounds(new Rectangle(8, 71, 663, 400));
			panelActual.setBorder(BorderFactory.createTitledBorder(null, "Consulta Detallada", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue));
			panelActual.add(getT_peso(), null);
			panelActual.add(lbl_8, null);
			panelActual.add(lbl_9, null);
			panelActual.add(getT_talla(), null);
			panelActual.add(lbl_10, null);
			panelActual.add(lbl_11, null);
			panelActual.add(getScroll_5(), null);
			panelActual.add(getScroll_6(), null);
			panelActual.add(lbl_12, null);
			panelActual.add(getScroll_7(), null);
			panelActual.add(lbl_2, null);
			panelActual.add(getT_edad(), null);
			panelActual.add(getBtn_crearReceta(), null);
			panelActual.add(getBtn_guardar1(), null);
			panelActual.add(getBtn_guardar21(), null);
			panelActual.add(lbl_13, null);
			panelActual.add(getT_saturacion(), null);
			panelActual.add(lbl_15, null);
			panelActual.add(getT_indice(), null);
			panelActual.add(getBtn_examenes(), null);
			panelActual.add(jLabel, null);
			panelActual.add(getT_fc(), null);
			panelActual.add(jLabel1, null);
			panelActual.add(jLabel2, null);
			panelActual.add(getT_temperatura(), null);
			panelActual.add(jLabel3, null);
			panelActual.add(getT_frecuenciaRespiratoria(), null);
			panelActual.add(jLabel11, null);
			panelActual.add(jLabel4, null);
			panelActual.add(getT_tas(), null);
			panelActual.add(jLabel5, null);
			panelActual.add(getT_tad(), null);
			panelActual.add(jLabel6, null);
			panelActual.add(getT_pf(), null);
		}
		return panelActual;
	}

	/**
	 * This method initializes t_peso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_peso() {
		if (t_peso == null) {
			t_peso = new JTextField();
			t_peso.setLocation(new Point(140, 60));
			t_peso.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_peso.setText("");
			t_peso.setSize(new Dimension(50, 20));
		}
		return t_peso;
	}

	/**
	 * This method initializes t_talla	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_talla() {
		if (t_talla == null) {
			t_talla = new JTextField();
			t_talla.setLocation(new Point(315, 60));
			t_talla.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_talla.setSize(new Dimension(50, 20));
		}
		return t_talla;
	}

	/**
	 * This method initializes scroll_5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_5() {
		if (scroll_5 == null) {
			scroll_5 = new JScrollPane();
			scroll_5.setLocation(new Point(140, 161));
			scroll_5.setViewportView(getT_motivoActual());
			scroll_5.setSize(new Dimension(470, 70));
		}
		return scroll_5;
	}

	/**
	 * This method initializes t_motivoActual	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_motivoActual() {
		if (t_motivoActual == null) {
			t_motivoActual = new JTextArea();
			t_motivoActual.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_motivoActual.setLineWrap(true);
		}
		return t_motivoActual;
	}

	/**
	 * This method initializes scroll_6	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_6() {
		if (scroll_6 == null) {
			scroll_6 = new JScrollPane();
			scroll_6.setLocation(new Point(140, 237));
			scroll_6.setViewportView(getT_diagnosticoActual());
			scroll_6.setSize(new Dimension(470, 70));
		}
		return scroll_6;
	}

	/**
	 * This method initializes t_diagnosticoActual	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_diagnosticoActual() {
		if (t_diagnosticoActual == null) {
			t_diagnosticoActual = new JTextArea();
			t_diagnosticoActual.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_diagnosticoActual.setLineWrap(true);
		}
		return t_diagnosticoActual;
	}

	/**
	 * This method initializes scroll_7	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_7() {
		if (scroll_7 == null) {
			scroll_7 = new JScrollPane();
			scroll_7.setLocation(new Point(140, 313));
			scroll_7.setViewportView(getT_observaciones());
			scroll_7.setSize(new Dimension(470, 70));
		}
		return scroll_7;
	}

	/**
	 * This method initializes t_observaciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_observaciones() {
		if (t_observaciones == null) {
			t_observaciones = new JTextArea();
			t_observaciones.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_observaciones.setLineWrap(true);
		}
		return t_observaciones;
	}

	/**
	 * This method initializes t_edad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_edad() {
		if (t_edad == null) {
			t_edad = new JTextField();
			t_edad.setLocation(new Point(140, 29));
			t_edad.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_edad.setEditable(false);
			t_edad.setSize(new Dimension(197, 20));
		}
		return t_edad;
	}

	/**
	 * This method initializes btn_crearReceta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_crearReceta() {
		if (btn_crearReceta == null) {
			btn_crearReceta = new JButton();
			btn_crearReceta.setLocation(new Point(459, 413));
			btn_crearReceta.setBackground(Color.white);
			btn_crearReceta.setToolTipText("Crear receta");
			btn_crearReceta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			btn_crearReceta.setSize(new Dimension(65, 65));
		}
		return btn_crearReceta;
	}

	/**
	 * This method initializes btn_guardar1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar1() {
		if (btn_guardar1 == null) {
			btn_guardar1 = new JButton();
			btn_guardar1.setLocation(new Point(190, 406));
			btn_guardar1.setBackground(Color.white);
			btn_guardar1.setSize(new Dimension(65, 65));
			btn_guardar1.setToolTipText("Guardar con cambios");
			btn_guardar1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image029.png")));
			btn_guardar1.setVisible(false);
		}
		return btn_guardar1;
	}

	/**
	 * This method initializes btn_guardar21	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar21() {
		if (btn_guardar21 == null) {
			btn_guardar21 = new JButton();
			btn_guardar21.setLocation(new Point(539, 413));
			btn_guardar21.setBackground(Color.white);
			btn_guardar21.setToolTipText("Guardar");
			btn_guardar21.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image021.png")));
			btn_guardar21.setSize(new Dimension(65, 65));
		}
		return btn_guardar21;
	}

	/**
	 * This method initializes t_saturacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_saturacion() {
		if (t_saturacion == null) {
			t_saturacion = new JTextField();
			t_saturacion.setLocation(new Point(140, 90));
			t_saturacion.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_saturacion.setSize(new Dimension(50, 20));
		}
		return t_saturacion;
	}

	/**
	 * This method initializes t_indice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_indice() {
		if (t_indice == null) {
			t_indice = new JTextField();
			t_indice.setLocation(new Point(315, 90));
			t_indice.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_indice.setSize(new Dimension(50, 20));
		}
		return t_indice;
	}

	/**
	 * This method initializes btn_examenes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_examenes() {
		if (btn_examenes == null) {
			btn_examenes = new JButton();
			btn_examenes.setLocation(new Point(377, 413));
			btn_examenes.setBackground(Color.white);
			btn_examenes.setToolTipText("Ver historia");
			btn_examenes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image019.png")));
			btn_examenes.setSize(new Dimension(65, 65));
		}
		return btn_examenes;
	}

	/**
	 * This method initializes t_fc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_fc() {
		if (t_fc == null) {
			t_fc = new JTextField();
			t_fc.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_fc.setSize(new Dimension(55, 20));
			t_fc.setText("");
			t_fc.setLocation(new Point(513, 60));
		}
		return t_fc;
	}

	/**
	 * This method initializes t_temperatura	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_temperatura() {
		if (t_temperatura == null) {
			t_temperatura = new JTextField();
			t_temperatura.setLocation(new Point(513, 91));
			t_temperatura.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_temperatura.setText("");
			t_temperatura.setSize(new Dimension(55, 20));
		}
		return t_temperatura;
	}

	/**
	 * This method initializes t_frecuenciaRespiratoria	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_frecuenciaRespiratoria() {
		if (t_frecuenciaRespiratoria == null) {
			t_frecuenciaRespiratoria = new JTextField();
			t_frecuenciaRespiratoria.setLocation(new Point(140, 120));
			t_frecuenciaRespiratoria.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_frecuenciaRespiratoria.setSize(new Dimension(50, 20));
		}
		return t_frecuenciaRespiratoria;
	}

	/**
	 * This method initializes t_tas	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_tas() {
		if (t_tas == null) {
			t_tas = new JTextField();
			t_tas.setLocation(new Point(315, 120));
			t_tas.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tas.setSize(new Dimension(50, 20));
		}
		return t_tas;
	}

	/**
	 * This method initializes t_tad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_tad() {
		if (t_tad == null) {
			t_tad = new JTextField();
			t_tad.setSize(new Dimension(55, 20));
			t_tad.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tad.setLocation(new Point(513, 120));
		}
		return t_tad;
	}

	/**
	 * This method initializes t_pf	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_pf() {
		if (t_pf == null) {
			t_pf = new JTextField();
			t_pf.setBounds(new Rectangle(513, 28, 55, 20));
                        t_pf.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return t_pf;
	}	

}
