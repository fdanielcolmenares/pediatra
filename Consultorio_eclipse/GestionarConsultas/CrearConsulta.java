package GestionarConsultas;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import java.awt.Color;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

public class CrearConsulta {	
	
	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="45,-7"
	private JScrollPane scrollVentana = null;  //  @jve:decl-index=0:
	private JPanel panelVentana = null;  //  @jve:decl-index=0:
	private JLabel lbl_1 = null;
	private JLabel lbl_2 = null;
	private JTextField t_nombre = null;
	private JTextField t_edad = null;
	private JLabel lbl_3 = null;
	public JLabel t_historia = null;
	private JLabel lbl_4 = null;
	private JLabel lbl_5 = null;
	private JScrollPane scroll1 = null;
	private JTextArea t_motivoIngreso = null;
	private JPanel panelIngreso = null;
	private JScrollPane scroll2 = null;
	private JTextArea t_diagnosticoIngreso = null;
	private JPanel panelUltimaConsulta = null;
	private JLabel lbl_6 = null;
	private JLabel lbl_7 = null;
	private JScrollPane scroll3 = null;
	private JScrollPane scroll4 = null;
	private JTextArea t_motivoUltima = null;
	private JTextArea t_diagnosticoUltima = null;
	private JPanel panelActual = null;
	private JTextField t_peso = null;
	private JLabel lbl_8 = null;
	private JLabel lbl_9 = null;
	private JTextField t_talla = null;
	private JLabel lbl_10 = null;
	private JLabel lbl_11 = null;
	private JScrollPane scroll_5 = null;
	private JScrollPane scroll_6 = null;
	private JTextArea t_motivoActual = null;
	private JTextArea t_diagnosticoActual = null;
	private JButton btn_consultaIngreso = null;
	private JButton btn_tratamientoIngreso = null;
	private JLabel lbl_12 = null;
	private JScrollPane scroll_7 = null;
	private JButton btn_ultimaConsulta = null;
	private JButton btn_ultimoTratamiento = null;
	private JTextArea t_observaciones = null;
	public JLabel t_fechaIngreso = null;
	public JLabel t_edadIngreso = null;
	public JLabel t_fechaUltima = null;
	public JLabel t_edadUltima = null;
	private JButton btn_abajo1 = null;
	private JButton btn_arriba1 = null;
	private JButton btn_crearReceta = null;
	private JButton btn_guardar1 = null;
	private JButton btn_guardar2 = null;
	private JLabel lbl_13 = null;
	private JTextField t_saturacion = null;
	private JLabel lbl_15 = null;
	private JTextField t_indice = null;
	private JButton btn_examenes = null;
	private GestorCrearConsulta gestor;  //  @jve:decl-index=0:
	private String numhis;
	public String hora;
	public String hora1;
	private boolean nuevo;
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
	
	public CrearConsulta(int w, int h, String numhis,boolean nuevo){
		getVentana();
		int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
        this.numhis=numhis;
        this.nuevo=nuevo;//true nuevo false actualizar
        //gestor.llenarPrimera();
	}
	
	public void setGestor(GestorCrearConsulta g){
		gestor = g;
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(aFlag);
	}

	public void setNivelUsuario(int n){
		if(n == 3){
			t_diagnosticoActual.setEditable(false);
		}
		if(n == 2){
			t_diagnosticoActual.setEditable(true);
		}
		if(n == 1){
			t_diagnosticoActual.setEditable(true);
		}
	}
	
	public void habilitarBotones(boolean aFlag){
		btn_examenes.setVisible(false);
		//btn_examenes.setEnabled(aFlag);
		btn_crearReceta.setEnabled(aFlag);
		
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
			ventana.setTitle("Crear Consulta");
			ventana.setSize(new Dimension(765, 432));
			ventana.setResizable(true);
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image059.png")));
			/*ventana.addMouseListener(l)*/
			//ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);fdg
                        
			/*ventana.addInternalFrameListener(new javax.swing.event.InternalFrameListener(){
                            
				public void internalFrameClosing(InternalFrameEvent e) {
					System.out.println("chao");
					if(nuevo && gestor.autenticacion.getTipoUsuario()==1)
						gestor.Guardar(true,numhis,1);
					if(nuevo && gestor.autenticacion.getTipoUsuario()==2)
						gestor.Guardar(true,numhis,0);
					if(!nuevo && gestor.autenticacion.getTipoUsuario()==1){
						gestor.Guardar(false,numhis,1);
					}
					if(!nuevo && gestor.autenticacion.getTipoUsuario()==2){
						gestor.Guardar(false,numhis,0);
					}
					ventana.dispose();
				}
							
			});*/
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
		         .addGap(0, 600, Short.MAX_VALUE)
		     );
		     jPanelLayout.setVerticalGroup(
		         jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 1010, Short.MAX_VALUE)
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
			lbl_5 = new JLabel();
			lbl_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_5.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_5.setSize(new Dimension(103, 20));
			lbl_5.setLocation(new Point(22, 120));
			lbl_5.setText("Diagnóstico:");
			lbl_4 = new JLabel();
			lbl_4.setText("Motivo:");
			lbl_4.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_4.setLocation(new Point(47, 44));
			lbl_4.setSize(new Dimension(78, 20));
			lbl_4.setHorizontalAlignment(SwingConstants.RIGHT);
			t_historia = new JLabel();
			t_historia.setFont(new Font("Dialog", Font.BOLD, 20));
			t_historia.setSize(new Dimension(82, 37));
			t_historia.setLocation(new Point(138, 22));
			t_historia.setText("000000");
			lbl_3 = new JLabel();
			lbl_3.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_3.setLocation(new Point(41, 31));
			lbl_3.setSize(new Dimension(94, 20));
			lbl_3.setHorizontalTextPosition(SwingConstants.RIGHT);
			lbl_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_3.setText("Historia N°:");
			lbl_2 = new JLabel();
			lbl_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_2.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_2.setBounds(new Rectangle(73, 29, 50, 20));
			lbl_2.setText("Edad:");
			panelVentana = new JPanel();
			panelVentana.setLayout(null);
			panelVentana.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			panelVentana.setSize(new Dimension(755, 1050));
			panelVentana.add(getLbl_1(), null);
			panelVentana.add(getT_nombre(), null);
			panelVentana.add(lbl_3, null);
			panelVentana.add(t_historia, null);
			panelVentana.add(getPanelIngreso(), null);
			panelVentana.add(getPanelUltimaConsulta(), null);
			panelVentana.add(getPanelActual(), null);
			panelVentana.add(getBtn_abajo1(), null);
			panelVentana.add(getBtn_arriba1(), null);
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
			lbl_1.setLocation(new Point(320, 32));
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
			t_nombre.setFont(new Font("Dialog", Font.PLAIN, 18));
			t_nombre.setLocation(new Point(400, 32));
		}
		return t_nombre;
	}

	/**
	 * This method initializes t_edad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_edad() {
		if (t_edad == null) {
			t_edad = new JTextField();
			t_edad.setSize(new Dimension(197, 20));
			t_edad.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_edad.setLocation(new Point(140, 29));
			t_edad.setEditable(false);
		}
		return t_edad;
	}

	/**
	 * This method initializes scroll1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll1() {
		if (scroll1 == null) {
			scroll1 = new JScrollPane();
			scroll1.setLocation(new Point(140, 44));
			scroll1.setSize(new Dimension(470, 70));
			scroll1.setViewportView(getT_motivoIngreso());
		}
		return scroll1;
	}

	/**
	 * This method initializes t_motivoIngreso	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_motivoIngreso() {
		if (t_motivoIngreso == null) {
			t_motivoIngreso = new JTextArea();
			t_motivoIngreso.setSize(new Dimension(487, 70));
			t_motivoIngreso.setEditable(false);
			t_motivoIngreso.setLineWrap(true);
			t_motivoIngreso.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return t_motivoIngreso;
	}

	/**
	 * This method initializes panelIngreso	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelIngreso() {
		if (panelIngreso == null) {
			t_edadIngreso = new JLabel();
			t_edadIngreso.setFont(new Font("Dialog", Font.PLAIN, 18));
			t_edadIngreso.setSize(new Dimension(283, 20));
			t_edadIngreso.setLocation(new Point(325, 23));
			t_edadIngreso.setText("Edad:");
			t_fechaIngreso = new JLabel();
			t_fechaIngreso.setText("Fecha: 00/00/0000");
			t_fechaIngreso.setSize(new Dimension(173, 20));
			t_fechaIngreso.setFont(new Font("Dialog", Font.PLAIN, 18));
			t_fechaIngreso.setLocation(new Point(139, 23));
			panelIngreso = new JPanel();
			panelIngreso.setLayout(null);
			panelIngreso.setBorder(BorderFactory.createTitledBorder(null, "Consulta de Ingreso", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue));
			panelIngreso.setLocation(new Point(30, 68));
			panelIngreso.setSize(new Dimension(700, 200));
			panelIngreso.add(lbl_4, null);
			panelIngreso.add(lbl_5, null);
			panelIngreso.add(getScroll1(), null);
			panelIngreso.add(getScroll2(), null);
			panelIngreso.add(getBtn_consultaIngreso(), null);
			panelIngreso.add(getBtn_tratamientoIngreso(), null);
			panelIngreso.add(t_fechaIngreso, null);
			panelIngreso.add(t_edadIngreso, null);
		}
		return panelIngreso;
	}

	/**
	 * This method initializes scroll2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll2() {
		if (scroll2 == null) {
			scroll2 = new JScrollPane();
			scroll2.setLocation(new Point(140, 120));
			scroll2.setSize(new Dimension(470, 70));
			scroll2.setViewportView(getT_diagnosticoIngreso());
		}
		return scroll2;
	}

	/**
	 * This method initializes t_diagnosticoIngreso	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_diagnosticoIngreso() {
		if (t_diagnosticoIngreso == null) {
			t_diagnosticoIngreso = new JTextArea();
			t_diagnosticoIngreso.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_diagnosticoIngreso.setLineWrap(true);
			t_diagnosticoIngreso.setEditable(false);
		}
		return t_diagnosticoIngreso;
	}

	/**
	 * This method initializes panelUltimaConsulta	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelUltimaConsulta() {
		if (panelUltimaConsulta == null) {
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Última Consulta", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue);
			titledBorder.setTitle("Consulta Anterior");
			t_edadUltima = new JLabel();
			t_edadUltima.setText("Edad:");
			t_edadUltima.setLocation(new Point(325, 23));
			t_edadUltima.setFont(new Font("Dialog", Font.PLAIN, 18));
			t_edadUltima.setSize(new Dimension(283, 20));
			t_fechaUltima = new JLabel();
			t_fechaUltima.setFont(new Font("Dialog", Font.PLAIN, 18));
			t_fechaUltima.setSize(new Dimension(173, 20));
			t_fechaUltima.setLocation(new Point(139, 23));
			t_fechaUltima.setText("Fecha: 00/00/0000");
			lbl_7 = new JLabel();
			lbl_7.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_7.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_7.setLocation(new Point(22, 120));
			lbl_7.setSize(new Dimension(110, 20));
			lbl_7.setText("Diagnóstico:");
			lbl_6 = new JLabel();
			lbl_6.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_6.setLocation(new Point(47, 44));
			lbl_6.setSize(new Dimension(85, 20));
			lbl_6.setText("Motivo:");
			panelUltimaConsulta = new JPanel();
			panelUltimaConsulta.setLayout(null);
			panelUltimaConsulta.setLocation(new Point(29, 280));
			panelUltimaConsulta.setFont(new Font("Dialog", Font.PLAIN, 12));
			panelUltimaConsulta.setSize(new Dimension(700, 200));
			panelUltimaConsulta.setBorder(titledBorder);
			panelUltimaConsulta.add(lbl_6, null);
			panelUltimaConsulta.add(lbl_7, null);
			panelUltimaConsulta.add(getScroll3(), null);
			panelUltimaConsulta.add(getScroll4(), null);
			panelUltimaConsulta.add(getBtn_ultimaConsulta(), null);
			panelUltimaConsulta.add(getBtn_ultimoTratamiento(), null);
			panelUltimaConsulta.add(t_fechaUltima, null);
			panelUltimaConsulta.add(t_edadUltima, null);
		}
		return panelUltimaConsulta;
	}

	/**
	 * This method initializes scroll3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll3() {
		if (scroll3 == null) {
			scroll3 = new JScrollPane();
			scroll3.setLocation(new Point(140, 44));
			scroll3.setViewportView(getT_motivoUltima());
			scroll3.setSize(new Dimension(470, 70));
		}
		return scroll3;
	}

	/**
	 * This method initializes scroll4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll4() {
		if (scroll4 == null) {
			scroll4 = new JScrollPane();
			scroll4.setLocation(new Point(140, 120));
			scroll4.setViewportView(getT_diagnosticoUltima());
			scroll4.setSize(new Dimension(470, 70));
		}
		return scroll4;
	}

	/**
	 * This method initializes t_motivoUltima	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_motivoUltima() {
		if (t_motivoUltima == null) {
			t_motivoUltima = new JTextArea();
			t_motivoUltima.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_motivoUltima.setLineWrap(true);
			t_motivoUltima.setEditable(false);
		}
		return t_motivoUltima;
	}

	/**
	 * This method initializes t_diagnosticoUltima	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_diagnosticoUltima() {
		if (t_diagnosticoUltima == null) {
			t_diagnosticoUltima = new JTextArea();
			t_diagnosticoUltima.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_diagnosticoUltima.setLineWrap(true);
			t_diagnosticoUltima.setEditable(false);
		}
		return t_diagnosticoUltima;
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
			jLabel4.setText("T.A.S.:");
			jLabel4.setSize(new Dimension(57, 20));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel4.setToolTipText("Tensión arterial sistólica");
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setLocation(new Point(246, 120));
			jLabel11 = new JLabel();
			jLabel11.setText("x MIN");
			jLabel11.setLocation(new Point(195, 120));
			jLabel11.setSize(new Dimension(39, 20));
			jLabel11.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel3 = new JLabel();
			jLabel3.setToolTipText("Frecuencia respiratoria");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel3.setLocation(new Point(68, 120));
			jLabel3.setSize(new Dimension(52, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("F.R.:");
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel2.setLocation(new Point(380, 90));
			jLabel2.setSize(new Dimension(120, 20));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Temperatura:");
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
			lbl_15.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_15.setLocation(new Point(230, 90));
			lbl_15.setSize(new Dimension(71, 20));
			lbl_15.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_15.setToolTipText("Indice de masa corporal");
			lbl_15.setText("I.M.C.:");
			lbl_13 = new JLabel();
			lbl_13.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_13.setLocation(new Point(60, 90));
			lbl_13.setSize(new Dimension(62, 20));
			lbl_13.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_13.setToolTipText("Saturación de Oxígeno");
			lbl_13.setText("S.O.:");
			lbl_12 = new JLabel();
			lbl_12.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_12.setLocation(new Point(0, 313));
			lbl_12.setSize(new Dimension(128, 20));
			lbl_12.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_12.setText("Examen Físico:");
			lbl_11 = new JLabel();
			lbl_11.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_11.setLocation(new Point(20, 237));
			lbl_11.setSize(new Dimension(103, 20));
			lbl_11.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_11.setText("Diagnóstico:");
			lbl_10 = new JLabel();
			lbl_10.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_10.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_10.setLocation(new Point(43, 161));
			lbl_10.setSize(new Dimension(80, 20));
			lbl_10.setText("Motivo:");
			lbl_9 = new JLabel();
			lbl_9.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_9.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_9.setSize(new Dimension(65, 20));
			lbl_9.setLocation(new Point(235, 60));
			lbl_9.setText("Talla:");
			lbl_8 = new JLabel();
			lbl_8.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_8.setLocation(new Point(60, 60));
			lbl_8.setSize(new Dimension(63, 20));
			lbl_8.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_8.setText("Peso:");
			panelActual = new JPanel();
			panelActual.setLayout(null);
			panelActual.setLocation(new Point(30, 490));
			panelActual.setBorder(BorderFactory.createTitledBorder(null, "Consulta Actual", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue));
			panelActual.setSize(new Dimension(700, 503));
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
			panelActual.add(getBtn_guardar2(), null);
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
			t_peso.setSize(new Dimension(50, 20));
			t_peso.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_peso.setText("");
			t_peso.setLocation(new Point(140, 60));
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
			scroll_5.setSize(new Dimension(470, 70));
			scroll_5.setViewportView(getT_motivoActual());
		}
		return scroll_5;
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
			scroll_6.setSize(new Dimension(470, 70));
			scroll_6.setViewportView(getT_diagnosticoActual());
		}
		return scroll_6;
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
	 * This method initializes btn_consultaIngreso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_consultaIngreso() {
		if (btn_consultaIngreso == null) {
			btn_consultaIngreso = new JButton();
			btn_consultaIngreso.setSize(new Dimension(65, 65));
			btn_consultaIngreso.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image024.png")));
			btn_consultaIngreso.setBackground(Color.white);
			btn_consultaIngreso.setToolTipText("Ver Consulta");
			btn_consultaIngreso.setLocation(new Point(620, 44));
			btn_consultaIngreso.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.consultaDetallada(t_historia.getText(),t_fechaIngreso.getText(),hora);
				}
			});
		}
		return btn_consultaIngreso;
	}

	/**
	 * This method initializes btn_tratamientoIngreso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_tratamientoIngreso() {
		if (btn_tratamientoIngreso == null) {
			btn_tratamientoIngreso = new JButton();
			btn_tratamientoIngreso.setLocation(new Point(620, 120));
			btn_tratamientoIngreso.setBackground(Color.white);
			btn_tratamientoIngreso.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image025.png")));
			btn_tratamientoIngreso.setToolTipText("Ver Receta");
			btn_tratamientoIngreso.setText("");
			btn_tratamientoIngreso.setSize(new Dimension(65, 65));
			btn_tratamientoIngreso.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
							gestor.verRecetaPrimeraConsulta();
				}
			});
		}
		return btn_tratamientoIngreso;
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
	 * This method initializes btn_ultimaConsulta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_ultimaConsulta() {
		if (btn_ultimaConsulta == null) {
			btn_ultimaConsulta = new JButton();
			btn_ultimaConsulta.setLocation(new Point(620, 47));
			btn_ultimaConsulta.setBackground(Color.white);
			btn_ultimaConsulta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image024.png")));
			btn_ultimaConsulta.setToolTipText("Ver Consulta");
			btn_ultimaConsulta.setSize(new Dimension(65, 65));
			btn_ultimaConsulta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.consultaDetallada(t_historia.getText(),t_fechaUltima.getText(),hora1);
				}
			});
		}
		return btn_ultimaConsulta;
	}

	/**
	 * This method initializes btn_ultimoTratamiento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_ultimoTratamiento() {
		if (btn_ultimoTratamiento == null) {
			btn_ultimoTratamiento = new JButton();
			btn_ultimoTratamiento.setLocation(new Point(620, 120));
			btn_ultimoTratamiento.setBackground(Color.white);
			btn_ultimoTratamiento.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image025.png")));
			btn_ultimoTratamiento.setToolTipText("Ver Receta");
			btn_ultimoTratamiento.setSize(new Dimension(65, 65));
			btn_ultimoTratamiento.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					gestor.verRecetaUltimaConsulta();
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btn_ultimoTratamiento;
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
	 * This method initializes btn_abajo1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_abajo1() {
		if (btn_abajo1 == null) {
			btn_abajo1 = new JButton();
			btn_abajo1.setLocation(new Point(2, 78));
			btn_abajo1.setBackground(Color.white);
			btn_abajo1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image026.png")));
			btn_abajo1.setSize(new Dimension(25, 25));
			btn_abajo1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					scrollVentana.getVerticalScrollBar().setValue(490);
				}
			});
		}
		return btn_abajo1;
	}

	/**
	 * This method initializes btn_arriba1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_arriba1() {
		if (btn_arriba1 == null) {
			btn_arriba1 = new JButton();
			btn_arriba1.setLocation(new Point(3, 500));
			btn_arriba1.setBackground(Color.white);
			btn_arriba1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image027.png")));
			btn_arriba1.setSize(new Dimension(25, 25));
			btn_arriba1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					scrollVentana.getVerticalScrollBar().setValue(0);
				}
			});
		}
		return btn_arriba1;
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
			btn_crearReceta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			btn_crearReceta.setBackground(Color.white);
			btn_crearReceta.setToolTipText("Crear receta");
			btn_crearReceta.setSize(new Dimension(65, 65));
			btn_crearReceta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.receta();
				}
			});
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
			btn_guardar1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image029.png")));
			btn_guardar1.setBackground(Color.white);
			btn_guardar1.setToolTipText("Guardar con cambios");
			btn_guardar1.setSize(new Dimension(65, 65));
			btn_guardar1.setVisible(false);
			btn_guardar1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btn_guardar1;
	}

	/**
	 * This method initializes btn_guardar2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar2() {
		if (btn_guardar2 == null) {
			btn_guardar2 = new JButton();
			btn_guardar2.setLocation(new Point(539, 413));
			btn_guardar2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image021.png")));
			btn_guardar2.setBackground(Color.white);
			btn_guardar2.setToolTipText("Guardar");
			btn_guardar2.setSize(new Dimension(65, 65));
			btn_guardar2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(nuevo && gestor.autenticacion.getTipoUsuario()==1)
						gestor.Guardar(true,numhis,1);
					if(nuevo && gestor.autenticacion.getTipoUsuario()==2)
						gestor.Guardar(true,numhis,0);
					if(!nuevo && gestor.autenticacion.getTipoUsuario()==1){
						gestor.Guardar(false,numhis,1);
					}
					if(!nuevo && gestor.autenticacion.getTipoUsuario()==2){
						gestor.Guardar(false,numhis,0);
					}
				}
			});
		}
		return btn_guardar2;
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
			t_indice.setSize(new Dimension(50, 20));
			t_indice.setLocation(new Point(315, 90));
                        t_indice.setFont(new Font("Dialog", Font.PLAIN, 14));
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
			btn_examenes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image019.png")));
			btn_examenes.setToolTipText("Ver historia");
			btn_examenes.setSize(new Dimension(65, 65));
			btn_examenes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.historia();
				}
			});
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
			t_temperatura.setText("");
			t_temperatura.setFont(new Font("Dialog", Font.PLAIN, 14));
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

	
}  //  @jve:decl-index=0:visual-constraint="11,11"
