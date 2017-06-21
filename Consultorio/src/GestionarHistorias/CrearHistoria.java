package GestionarHistorias;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.KeyEvent;
import java.awt.ComponentOrientation;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import javax.swing.JComboBox;

import Entidades.consultas;
import GestionarConsultas.GestorCrearConsulta;
import GestionarVacunas.VentanaGestionarVacunas;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class CrearHistoria {	
	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="159,5"
	private JPanel panelVentana = null;
	private JTabbedPane pestanas = null;
	private JPanel panelDatos = null;
	private JPanel panelPadres = null;
	private JLabel label_11 = null;
	private JTextField t_nombre = null;
	private JLabel label_13 = null;
	private JDateChooser t_fechaNac = null;
	private JLabel label_14 = null;
	private JTextField t_edad = null;
	private JButton b_siguiente1 = null;
	private JLabel label_21 = null;
	private JLabel label_23 = null;
	private JLabel label_22 = null;
	private JLabel label_24 = null;
	private JButton b_siguiente2 = null;
	private JTextField t_nomMadre = null;
	private JTextField t_profMadre = null;
	private JTextField t_nomPadre = null;
	private JTextField t_profPadre = null;
	private JLabel label_25 = null;
	private JLabel label_26 = null;
	private JTextArea t_dir = null;
	private JScrollPane scrollDir = null;
	private JTextField t_tlf1 = null;
	private JScrollPane scroll_nacimiento = null;
	private JPanel panelNacimiento = null;
	private JPanel panelFamiliares = null;
	private JButton b_siguiente3 = null;
	private JButton b_siguiente4 = null;
	private JButton b_atras2 = null;
	private JButton b_atras3 = null;
	private JButton b_atras4 = null;
	private JLabel label0 = null;
	private JLabel t_historia = null;
	private JLabel label_32 = null;
	private JLabel label_31 = null;
	private JLabel label_33 = null;
	private JLabel label_34 = null;
	private JLabel label_35 = null;
	private JLabel label_36 = null;
	private JLabel label_37 = null;
	private JLabel label_38 = null;
	private JLabel label_39 = null;
	private JTextField t_tiempoGestacion = null;
	private JTextField t_numPartos = null;
	private JTextField t_peso = null;
	private JTextField t_talla = null;
	private JComboBox t_tipoParto = null;
	private JScrollPane scroll_causas = null;
	private JTextArea t_causas = null;
	private JScrollPane scroll_enfermedadesEmbarazo = null;
	private JScrollPane scroll_complicaciones = null;
	private JTextArea t_enfermedadesEmbarazo = null;
	private JTextArea t_complicaciones = null;
	private String urlArchivos = null;
	private JCheckBox chk_asma = null;
	private JCheckBox chk_rinitis = null;
	private JCheckBox chk_dermatitis = null;
	private JCheckBox chk_prurito = null;
	private JCheckBox chk_hipertension = null;
	private JCheckBox chk_diabetes = null;
	private JCheckBox chk_cancer = null;
	private JCheckBox chk_cardiopatias = null;
	private JCheckBox chk_tiroides = null;
	private JCheckBox chk_otras = null;
	private JScrollPane jScrollPane = null;
	private JTextArea t_antecedentesFamiliares = null;
	private JButton btn_51 = null;
	private JButton btn_56 = null;
	private JButton btn_57 = null;
	private JButton btn_52 = null;
	private JButton btn_59 = null;
	private JButton btn_510 = null;
	private JButton btn_53 = null;
	private JButton jButtonbtn_55 = null;
	private JButton btn_55 = null;
	private JButton btn_58 = null;
	private JPanel panelAntecedentes = null;
	private JLabel label_51 = null;
	private JLabel label_52 = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea t_sintomas = null;
	private JLabel label_53 = null;
	private JLabel label_54 = null;
	private JLabel label_55 = null;
	private JLabel label_56 = null;
	private JLabel label_57 = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea t_interCrisis = null;
	private JLabel label_58 = null;
	private JLabel label_59 = null;
	private JLabel label_510 = null;
	private JRadioButton chk_continua = null;
	private JRadioButton chk_intermitente = null;
	private JLabel label_511 = null;
	private JLabel label_512 = null;
	private JLabel label_513 = null;
	private JCheckBox chk_respiracionOral = null;
	private JCheckBox chk_ronca = null;
	private JCheckBox chk_burxismo = null;
	private JScrollPane scrollAntecedentes = null;
	private JScrollPane jScrollPane3 = null;
	private JTextArea t_antecedentesEruptivas = null;
	private JScrollPane jScrollPane4 = null;
	private JScrollPane jScrollPane5 = null;
	private JScrollPane jScrollPane6 = null;
	private JTextArea t_antecedentesGI = null;
	private JTextArea t_antecedentesGU = null;
	private JTextArea t_antecedentesOtros = null;
	private JLabel label_514 = null;
	private JLabel label_515 = null;
	private JLabel label_516 = null;
	private JLabel label_517 = null;
	private JLabel label_518 = null;
	private JLabel label_519 = null;
	private JLabel label_520 = null;
	private JLabel label_521 = null;
	private JLabel label_522 = null;
	private JButton b_atras5 = null;
	private JButton b_siguiente5 = null;
	private JLabel label_310 = null;
	private JLabel label_311 = null;
	private JTextField t_lactancia = null;
	private JLabel label_312 = null;
	private JLabel label_313 = null;
	private JTextField t_tetero = null;
	private JLabel label_314 = null;
	private JLabel label_315 = null;
	private JTextField t_ablactacion = null;
	private GestorCrearHistoria gestor;  //  @jve:decl-index=0:
	private JButton btn_guardar = null;
	private JButton btn_cancelar = null;
	private JButton b_atras1 = null;
	private String asma="", rinitis="", dermatitis="", diabetes="", tiroides="";  // @jve:decl-index=0:
	private String hipertension="", prurito="", cancer="", cardiopatias="", otras="", urticaria=""; 
	private int lastAntecedente;
	public final int WARNING = 1;
	public final int ERROR = 2;
	public final int MENSAJE = 3;
	public boolean editable;
	private boolean nuevo;
	private JButton btn_vacunas = null;
	private JButton btn_consultas = null;
	private JButton btn_receta = null;
	private JTextField t_fechaCrisis1 = null;
	private JTextField t_fechaCrisis2 = null;
	private JLabel lbel_1 = null;
	private JButton btn_nuevaConsulta = null;
	private JButton btn_examenes = null;
	private JDesktopPane desktopPane;
	private JLabel lbl_41 = null;
	private JScrollPane jScrollPane7 = null;
	private JTextArea t_observaciones = null;
	private JCheckBox chk_urticaria = null;
	private JButton btn_511 = null;
	private JPanel panel_1 = null;
	private JPanel panel_2 = null;
	private JRadioButton chk_siLactancia = null;
	private JRadioButton chk_noLactancia = null;
	private JRadioButton chk_siTetero = null;
	private JRadioButton chk_noTetero = null;
	private JRadioButton chk_siAblactacion = null;
	private JRadioButton chk_noAblactacion = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JScrollPane jScrollPane8 = null;
	private JTextArea t_condiciones = null;
	private JLabel jLabel2 = null;
	private JScrollPane jScrollPane9 = null;
	private JTextArea t_alergias = null;
	private JScrollPane scrollVentana = null;
	private JPanel panelVentana1 = null;
	private JButton jButton = null;
	private JButton b_Receta = null;
	private JPanel panelActual = null;
	private JTextField t_peso1 = null;
	private JLabel lbl_8 = null;
	private JLabel lbl_9 = null;
	private JTextField t_talla1 = null;
	private JLabel lbl_10 = null;
	private JLabel lbl_11 = null;
	private JScrollPane scroll_5 = null;
	private JTextArea t_motivoActual = null;
	private JScrollPane scroll_6 = null;
	private JTextArea t_diagnosticoActual = null;
	private JLabel lbl_12 = null;
	private JScrollPane scroll_7 = null;
	private JTextArea t_observaciones1 = null;
	private JLabel lbl_2 = null;
	private JTextField t_edad1 = null;
	private JButton btn_crearReceta = null;
	private JButton btn_guardar1 = null;
	private JButton btn_guardar21 = null;
	private JLabel lbl_13 = null;
	private JTextField t_saturacion = null;
	private JLabel lbl_15 = null;
	private JTextField t_indice = null;
	private JButton btn_examenes1 = null;
	private JLabel jLabel3 = null;
	private JTextField t_fc = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel21 = null;
	private JTextField t_temperatura = null;
	private JLabel jLabel31 = null;
	private JTextField t_frecuenciaRespiratoria = null;
	private JLabel jLabel111 = null;
	private JLabel jLabel4 = null;
	private JTextField t_tas = null;
	private JLabel jLabel5 = null;
	private JTextField t_tad = null;
	private JButton btn_crearReceta1 = null;
	private GestorCrearConsulta gestor1;  //  @jve:decl-index=0:
	private consultas consul;
	private JLabel jLabel32 = null;
	private JTextField t_pf = null;
	
	public CrearHistoria(JDesktopPane p){
		desktopPane = p;
		getVentana();
		int x = Math.max(0, (desktopPane.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (desktopPane.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
        lastAntecedente = 0;
        editable = true;
        nuevo = true;
        setNumeroHistoria("Sin Guardar");
	}
	
	public void guardar(){
		if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Guardar?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
			cambiarAntecedente(1);
			gestor.guardar(nuevo);
			
			//System.out.println("-->"+consul.existe(gestor.getId_Historia()));
			if(consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==1){
				gestor1.Guardar2(false,String.valueOf(gestor.getId_Historia()),1);
			}
			if(consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==2){
				gestor1.Guardar2(false,String.valueOf(gestor.getId_Historia()),0);
			}
			if(consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==3){
				gestor1.Guardar2(false,String.valueOf(gestor.getId_Historia()),0);
			}
			if(!consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==1){
				gestor1.Guardar2(true,String.valueOf(gestor.getId_Historia()),1);
			}
			if(!consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==2){
				gestor1.Guardar2(true,String.valueOf(gestor.getId_Historia()),0);
			}
			if(!consul.existe(gestor.getId_Historia()) && gestor1.autenticacion.getTipoUsuario()==3){
				gestor1.Guardar2(true,String.valueOf(gestor.getId_Historia()),0);
			}
		}
	}
	
	public void cerrar(){
		if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea cerrar la ventana?\nSe perderan los cambios que no se hayan guardado", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
			ventana.dispose();
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
	
	public void setNumeroHistoria(String param){
		t_historia.setText(param);
	}
	
	public void setFechaGuardada(String text){
		lbel_1.setText("Fecha de creación: "+text);
		lbel_1.setVisible(true);
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(aFlag);
		try{
			ventana.setSelected(true);
		}
		catch(Exception ex){
		}
		t_nombre.requestFocus();
	}
	
	public void setNuevo(boolean param, int n){
		nuevo = param;
		btn_nuevaConsulta.setEnabled(!param);
		btn_consultas.setEnabled(!param);
		btn_vacunas.setEnabled(!param);
		btn_examenes.setEnabled(!param);
		setNivelUsuario(n);
	}
	
	public void setEditable(boolean aFlag){
		editable = aFlag;
		t_nombre.setEnabled(aFlag);
		t_fechaNac.setEnabled(aFlag);
		/*t_nomMadre.setEditable(aFlag);
		t_profMadre.setEditable(aFlag);
		t_nomPadre.setEditable(aFlag);
		t_profPadre.setEditable(aFlag);*/		
	}
	
	public void setGestor(GestorCrearHistoria g){
		gestor = g;
	}
	
	public void setGestor(GestorCrearConsulta g){
		gestor1 = g;
		consul = new consultas(gestor1.autenticacion);		
	}
	
	public void cargarPrimera(int id){
		if(consul.existe(id)){
			gestor1.llenarPrimera2(String.valueOf(id));
			getB_Receta().setEnabled(true);
			getBtn_crearReceta1().setEnabled(true);
			if(consul.estado(id)==1){
				getT_peso1().setEditable(false);
				getT_pf().setEditable(false);
				getT_talla1().setEditable(false);
				getT_fc().setEditable(false);
				getT_saturacion().setEditable(false);
				getT_indice().setEditable(false);
				getT_temperatura().setEditable(false);
				getT_frecuenciaRespiratoria().setEditable(false);
				getT_tas().setEditable(false);
				getT_tad().setEditable(false);
				getT_motivoActual().setEditable(false);
				getT_diagnosticoActual().setEditable(false);
				getT_observaciones1().setEditable(false);
			}
		}
	}
	
	public JDesktopPane getDesktopPane(){
		return desktopPane;
	}
	
	public void setNivelUsuario(int n){
		//System.out.println("Entra "+n);
		if(n == -1){
			btn_nuevaConsulta.setEnabled(false);
			getT_diagnosticoActual().setEditable(false);
			getT_observaciones().setEditable(false);
		}
		if(n == 3){
			btn_nuevaConsulta.setEnabled(false);
			getT_diagnosticoActual().setEditable(false);
			getT_observaciones().setEditable(false);
		}
		if(n == 2){
			btn_nuevaConsulta.setEnabled(true);
			getT_diagnosticoActual().setEditable(false);
			getT_observaciones1().setEditable(false);
		}
		if(n == 1){
			btn_nuevaConsulta.setEnabled(true);
			getT_diagnosticoActual().setEditable(true);
			getT_observaciones1().setEditable(true);
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
                        ventana.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			ventana.setSize(new Dimension(770, 430));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image015.png")));
			//ventana.setFrameIcon(new ImageIcon(getURLArchivos("Imagenes/Image015.png")));
			ventana.setMaximizable(false);
			ventana.setResizable(false);
			ventana.setClosable(true);
			ventana.setTitle("Editar Historia");
			ventana.setLocation(5, 10);
                        ventana.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {				
                            public void internalFrameClosing(
                                javax.swing.event.InternalFrameEvent e) {
                                cerrar();
                            }
			});
			ventana.setContentPane(getPanelVentana());
		}
		return ventana;
	}

	/**
	 * This method initializes panelVentana	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelVentana() {
		if (panelVentana == null) {
			panelVentana = new JPanel();
			panelVentana.setLayout(null);
			panelVentana.setForeground(Color.white);
			panelVentana.setVisible(true);
			panelVentana.add(getPestanas(), null);
			panelVentana.add(getBtn_guardar(), null);
			panelVentana.add(getBtn_cancelar(), null);
			panelVentana.add(getBtn_vacunas(), null);
			panelVentana.add(getBtn_consultas(), null);
			panelVentana.add(getBtn_receta(), null);
			panelVentana.add(getBtn_nuevaConsulta(), null);
			panelVentana.add(getBtn_examenes(), null);
		}
		return panelVentana;
	}

	/**
	 * This method initializes pestanas	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getPestanas() {
		if (pestanas == null) {
			pestanas = new JTabbedPane();
			pestanas.setTabPlacement(JTabbedPane.LEFT);
			pestanas.setFont(new Font("Dialog", Font.BOLD, 14));
			pestanas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			pestanas.setLocation(new Point(0, 0));
			pestanas.setSize(new Dimension(757, 330));
			pestanas.addTab("Paciente", new ImageIcon(getClass().getResource("/Files/Imagenes/Image014.png")), getPanelDatos(), null);
			
			pestanas.addTab("Padres", new ImageIcon(getClass().getResource("/Files/Imagenes/Image012.png")), getPanelPadres(), null);
			pestanas.addTab("Nacimiento", new ImageIcon(getClass().getResource("/Files/Imagenes/Image013.png")), getScrollNacimiento(), null);
			pestanas.addTab("Familiares", new ImageIcon(getClass().getResource("/Files/Imagenes/Image020.png")), getPanelFamiliares(), null);
			pestanas.addTab("Antecedentes", new ImageIcon(getClass().getResource("/Files/Imagenes/Image018.png")), getScrollAntecedentes(), null);
			pestanas.addTab("Ingreso", new ImageIcon(getClass().getResource("/Files/Imagenes/Image107.png")), getScrollVentana(), null);
			
			//pestanas.addTab("Datos Personales", new ImageIcon(getURLArchivos("Imagenes/Image015.png")), getPanelDatos(), null);
			//pestanas.addTab("Padres", new ImageIcon(getURLArchivos("Imagenes/Image012.png")), getPanelPadres(), null);
			//pestanas.addTab("Nacimiento", new ImageIcon(getURLArchivos("Imagenes/Image013.png")), getPanelNacimiento(), null);
			
		}
		return pestanas;
	}

	/**
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			lbel_1 = new JLabel();
			lbel_1.setBounds(new Rectangle(24, 231, 438, 40));
			lbel_1.setFont(new Font("Dialog", Font.BOLD, 24));
			lbel_1.setText("Fecha de creación:");
			lbel_1.setVisible(false);
			t_historia = new JLabel();
			t_historia.setBounds(new Rectangle(214, 46, 253, 26));
			t_historia.setFont(new Font("Dialog", Font.BOLD, 24));
			t_historia.setText("000000");
			t_historia.setVisible(true);
			label0 = new JLabel();
			label0.setFont(new Font("Dialog", Font.BOLD, 24));
			label0.setHorizontalAlignment(SwingConstants.RIGHT);
			label0.setLocation(new Point(54, 45));
			label0.setSize(new Dimension(145, 25));
			label0.setText("Historia N°:");
			label_14 = new JLabel();
			label_14.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_14.setHorizontalAlignment(SwingConstants.RIGHT);
			label_14.setSize(new Dimension(85, 20));
			label_14.setLocation(new Point(113, 155));
			label_14.setText("Edad:");
			label_13 = new JLabel();
			label_13.setName("label3");
			label_13.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_13.setHorizontalAlignment(SwingConstants.RIGHT);
			label_13.setSize(new Dimension(173, 20));
			label_13.setLocation(new Point(25, 120));
			label_13.setText("Fecha de nacimiento:");
			label_11 = new JLabel();
			label_11.setText("Nombres:");
			label_11.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_11.setSize(new Dimension(111, 20));
			label_11.setLocation(new Point(88, 85));
			label_11.setHorizontalAlignment(SwingConstants.RIGHT);
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.setFont(new Font("Dialog", Font.PLAIN, 14));
			panelDatos.setBorder(BorderFactory.createTitledBorder

(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Datos Personales", 

TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 

Font.PLAIN, 18), Color.blue));
			panelDatos.add(label_11, null);
			panelDatos.add(getT_nombre(), null);
			panelDatos.add(label_13, null);
			panelDatos.add(getT_fechaNac(), null);
			panelDatos.add(label_14, null);
			panelDatos.add(getT_edad(), null);
			panelDatos.add(getB_siguiente1(), null);
			panelDatos.add(label0, null);
			panelDatos.add(t_historia, null);
			panelDatos.add(getB_atras1(), null);
			panelDatos.add(lbel_1, null);
		}
		return panelDatos;
	}

	/**
	 * This method initializes panelPadres	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelPadres() {
		if (panelPadres == null) {
			label_26 = new JLabel();
			label_26.setHorizontalAlignment(SwingConstants.RIGHT);
			label_26.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_26.setSize(new Dimension(95, 20));
			label_26.setLocation(new Point(128, 240));
			label_26.setText("Teléfonos:");
			label_25 = new JLabel();
			label_25.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_25.setLocation(new Point(135, 170));
			label_25.setSize(new Dimension(87, 20));
			label_25.setHorizontalAlignment(SwingConstants.RIGHT);
			label_25.setText("Dirección:");
			label_24 = new JLabel();
			label_24.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_24.setLocation(new Point(55, 135));
			label_24.setSize(new Dimension(169, 20));
			label_24.setHorizontalAlignment(SwingConstants.RIGHT);
			label_24.setText("Profesión del padre:");
			label_22 = new JLabel();
			label_22.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_22.setLocation(new Point(39, 65));
			label_22.setSize(new Dimension(185, 20));
			label_22.setHorizontalAlignment(SwingConstants.RIGHT);
			label_22.setText("Profesión de la madre:");
			label_23 = new JLabel();
			label_23.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_23.setHorizontalAlignment(SwingConstants.RIGHT);
			label_23.setLocation(new Point(64, 100));
			label_23.setSize(new Dimension(160, 20));
			label_23.setText("Nombre del padre:");
			label_21 = new JLabel();
			label_21.setText("Nombre de la madre:");
			label_21.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_21.setLocation(new Point(30, 30));
			label_21.setSize(new Dimension(194, 20));
			label_21.setHorizontalAlignment(SwingConstants.RIGHT);
			panelPadres = new JPanel();
			panelPadres.setLayout(null);
			panelPadres.setBorder(BorderFactory.createTitledBorder

(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Datos de los Padres", 

TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 

Font.PLAIN, 18), Color.blue));
			panelPadres.setComponentOrientation(ComponentOrientation.UNKNOWN);
			panelPadres.add(label_21, null);
			panelPadres.add(label_23, null);
			panelPadres.add(label_22, null);
			panelPadres.add(label_24, null);
			panelPadres.add(getB_siguiente2(), null);
			panelPadres.add(getT_nomMadre(), null);
			panelPadres.add(getT_profMadre(), null);
			panelPadres.add(getT_nomPadre(), null);
			panelPadres.add(getT_profPadre(), null);
			panelPadres.add(label_25, null);
			panelPadres.add(label_26, null);
			panelPadres.add(getScrollDir(), null);
			panelPadres.add(getT_tlf1(), null);
			panelPadres.add(getB_atras2(), null);
		}
		return panelPadres;
	}

	/**
	 * This method initializes t_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_nombre() {
		if (t_nombre == null) {
			t_nombre = new JTextField();
			t_nombre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_nombre.setLocation(new Point(213, 85));
			t_nombre.setEditable(true);
			t_nombre.setEnabled(true);
			t_nombre.setSize(new Dimension(350, 20));
		}
		return t_nombre;
	}

	/**
	 * This method initializes t_fechaNac	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getT_fechaNac() {
		if (t_fechaNac == null) {
			t_fechaNac = new JDateChooser();
			t_fechaNac.setLocation(new Point(213, 120));
			t_fechaNac.setFont(new Font("Dialog", Font.PLAIN, 14));
			//t_fechaNac.setNextFocusableComponent(b_siguiente1);
			t_fechaNac.setSize(new Dimension(130, 20));
			t_fechaNac.setEnabled(true);
            t_fechaNac.setMaxSelectableDate(new Date());
            t_fechaNac.addPropertyChangeListener("date",
            		new java.beans.PropertyChangeListener() {
                    	public void propertyChange(java.beans.PropertyChangeEvent e) {
                    		gestor.calcularEdad(t_fechaNac.getDate(), null, t_edad);
                    	}
            });
		}
		return t_fechaNac;
	}

	/**
	 * This method initializes t_edad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_edad() {
		if (t_edad == null) {
			t_edad = new JTextField();
			t_edad.setLocation(new Point(213, 155));
			t_edad.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_edad.setEditable(false);
			t_edad.setSize(new Dimension(350, 20));
		}
		return t_edad;
	}

	/**
	 * This method initializes b_siguiente1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_siguiente1() {
		if (b_siguiente1 == null) {
			b_siguiente1 = new JButton();
			b_siguiente1.setLocation(new Point(440, 280));
			b_siguiente1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image010.png")));
			//b_siguiente1.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image010.png")));
			b_siguiente1.setText("Siguiente");
			b_siguiente1.setHorizontalAlignment(SwingConstants.LEFT);
			b_siguiente1.setPreferredSize(new Dimension(120, 42));
			b_siguiente1.setMnemonic(KeyEvent.VK_S);
			b_siguiente1.setSize(new Dimension(123, 34));
			b_siguiente1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(1);
				}
			});
		}
		return b_siguiente1;
	}

	/**
	 * This method initializes b_siguiente2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_siguiente2() {
		if (b_siguiente2 == null) {
			b_siguiente2 = new JButton();
			b_siguiente2.setLocation(new Point(440, 280));
			b_siguiente2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image010.png")));
			//b_siguiente2.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image010.png")));
			b_siguiente2.setText("Siguiente");
			b_siguiente2.setMnemonic(KeyEvent.VK_S);
			b_siguiente2.setSize(new Dimension(123, 34));
			b_siguiente2.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					pestanas.setSelectedIndex(2);
				}
			
			});
		}
		return b_siguiente2;
	}

	/**
	 * This method initializes t_nomMadre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_nomMadre() {
		if (t_nomMadre == null) {
			t_nomMadre = new JTextField();
			t_nomMadre.setLocation(new Point(240, 30));
			t_nomMadre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_nomMadre.setSize(new Dimension(323, 20));
		}
		return t_nomMadre;
	}

	/**
	 * This method initializes t_profMadre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_profMadre() {
		if (t_profMadre == null) {
			t_profMadre = new JTextField();
			t_profMadre.setLocation(new Point(240, 66));
			t_profMadre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_profMadre.setSize(new Dimension(323, 20));
		}
		return t_profMadre;
	}

	/**
	 * This method initializes t_nomPadre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_nomPadre() {
		if (t_nomPadre == null) {
			t_nomPadre = new JTextField();
			t_nomPadre.setLocation(new Point(240, 100));
			t_nomPadre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_nomPadre.setSize(new Dimension(323, 20));
		}
		return t_nomPadre;
	}

	/**
	 * This method initializes t_profPadre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_profPadre() {
		if (t_profPadre == null) {
			t_profPadre = new JTextField();
			t_profPadre.setLocation(new Point(240, 135));
			t_profPadre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_profPadre.setSize(new Dimension(323, 20));
		}
		return t_profPadre;
	}

	/**
	 * This method initializes t_dir	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_dir() {
		if (t_dir == null) {
			t_dir = new JTextArea();
			t_dir.setLineWrap(true);
			t_dir.setFont(new Font("Dialog", Font.PLAIN, 14));
			//t_dir.setWrapStyleWord(true);
			t_dir.setSize(new Dimension(323, 47));
		}
		return t_dir;
	}

	/**
	 * This method initializes scrollDir	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollDir() {
		if (scrollDir == null) {
			scrollDir = new JScrollPane();
			scrollDir.setLocation(new Point(240, 167));
			scrollDir.setViewportView(getT_dir());
			scrollDir.setSize(new Dimension(321, 50));
		}
		return scrollDir;
	}

	/**
	 * This method initializes t_tlf1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_tlf1() {
		if (t_tlf1 == null) {
			t_tlf1 = new JTextField();
			t_tlf1.setLocation(new Point(240, 240));
			t_tlf1.setColumns(5);
			t_tlf1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tlf1.setSize(new Dimension(323, 20));
		}
		return t_tlf1;
	}

	private JScrollPane getScrollNacimiento(){
		if(scroll_nacimiento == null){
			 scroll_nacimiento = new JScrollPane();
			 scroll_nacimiento.setHorizontalScrollBarPolicy

(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			 scroll_nacimiento.getVerticalScrollBar().setUnitIncrement(20);
		     GroupLayout jPanelNacLayout = new GroupLayout(getPanelNacimiento());
		     panelNacimiento.setLayout(jPanelNacLayout);
		     jPanelNacLayout.setHorizontalGroup(
		         jPanelNacLayout.createParallelGroup

(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 800, Short.MAX_VALUE)
		     );
		     jPanelNacLayout.setVerticalGroup(
		         jPanelNacLayout.createParallelGroup

(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 550, Short.MAX_VALUE)
		     );
		     scroll_nacimiento.setViewportView(getPanelNacimiento());
		}
		return scroll_nacimiento;
	}
	
	/**
	 * This method initializes panelNacimiento	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelNacimiento() {
		if (panelNacimiento == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(54, 376, 142, 19));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("recién nacido:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(57, 358, 138, 19));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Condiciones del");
			label_315 = new JLabel();
			label_315.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_315.setLocation(new Point(322, 507));
			label_315.setSize(new Dimension(65, 20));
			label_315.setHorizontalAlignment(SwingConstants.RIGHT);
			label_315.setText("Edad:");
			label_314 = new JLabel();
			label_314.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_314.setLocation(new Point(105, 507));
			label_314.setSize(new Dimension(100, 20));
			label_314.setHorizontalAlignment(SwingConstants.RIGHT);
			label_314.setText("Ablactación");
			label_313 = new JLabel();
			label_313.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_313.setHorizontalAlignment(SwingConstants.RIGHT);
			label_313.setLocation(new Point(318, 477));
			label_313.setSize(new Dimension(71, 20));
			label_313.setText("Hasta:");
			label_312 = new JLabel();
			label_312.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_312.setHorizontalAlignment(SwingConstants.RIGHT);
			label_312.setLocation(new Point(145, 477));
			label_312.setSize(new Dimension(60, 20));
			label_312.setText("Tetero");
			label_311 = new JLabel();
			label_311.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_311.setLocation(new Point(317, 447));
			label_311.setSize(new Dimension(71, 20));
			label_311.setHorizontalAlignment(SwingConstants.RIGHT);
			label_311.setText("Hasta:");
			label_310 = new JLabel();
			label_310.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_310.setLocation(new Point(51, 447));
			label_310.setSize(new Dimension(155, 20));
			label_310.setHorizontalAlignment(SwingConstants.RIGHT);
			label_310.setText("Lactancia materna");
			label_39 = new JLabel();
			label_39.setHorizontalAlignment(SwingConstants.RIGHT);
			label_39.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_39.setLocation(new Point(51, 278));
			label_39.setSize(new Dimension(150, 20));
			label_39.setText("Complicaciones:");
			label_38 = new JLabel();
			label_38.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_38.setHorizontalAlignment(SwingConstants.RIGHT);
			label_38.setLocation(new Point(81, 225));
			label_38.setSize(new Dimension(120, 20));
			label_38.setText("el embarazo:");
			label_37 = new JLabel();
			label_37.setHorizontalAlignment(SwingConstants.RIGHT);
			label_37.setLocation(new Point(17, 205));
			label_37.setSize(new Dimension(184, 20));
			label_37.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_37.setText("Enfermedades durante");
			label_36 = new JLabel();
			label_36.setHorizontalAlignment(SwingConstants.RIGHT);
			label_36.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_36.setLocation(new Point(110, 135));
			label_36.setSize(new Dimension(88, 20));
			label_36.setText("Causas:");
			label_35 = new JLabel();
			label_35.setHorizontalAlignment(SwingConstants.RIGHT);
			label_35.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_35.setLocation(new Point(63, 100));
			label_35.setSize(new Dimension(135, 20));
			label_35.setText("Tipo de parto:");
			label_34 = new JLabel();
			label_34.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_34.setLocation(new Point(400, 65));
			label_34.setSize(new Dimension(71, 20));
			label_34.setHorizontalAlignment(SwingConstants.RIGHT);
			label_34.setText("Talla:");
			label_33 = new JLabel();
			label_33.setHorizontalAlignment(SwingConstants.RIGHT);
			label_33.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_33.setLocation(new Point(71, 65));
			label_33.setSize(new Dimension(127, 20));
			label_33.setText("Peso:");
			label_31 = new JLabel();
			label_31.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_31.setLocation(new Point(20, 30));
			label_31.setSize(new Dimension(179, 20));
			label_31.setHorizontalAlignment(SwingConstants.RIGHT);
			label_31.setText("Gesta:");
			label_32 = new JLabel();
			label_32.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_32.setLocation(new Point(322, 29));
			label_32.setSize(new Dimension(150, 20));
			label_32.setHorizontalAlignment(SwingConstants.RIGHT);
			label_32.setText("Numero de partos:");
			label_32.setVisible(false);
			panelNacimiento = new JPanel();
			panelNacimiento.setLayout(null);
			panelNacimiento.setBorder(BorderFactory.createTitledBorder(new 

SoftBevelBorder(SoftBevelBorder.RAISED), "Datos al Nacer", TitledBorder.DEFAULT_JUSTIFICATION, 

TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue));
			panelNacimiento.add(getB_siguiente3(), null);
			panelNacimiento.add(getB_atras3(), null);
			panelNacimiento.add(label_32, null);
			panelNacimiento.add(label_31, null);
			panelNacimiento.add(label_33, null);
			panelNacimiento.add(label_34, null);
			panelNacimiento.add(label_35, null);
			panelNacimiento.add(label_36, null);
			panelNacimiento.add(label_37, null);
			panelNacimiento.add(label_38, null);
			panelNacimiento.add(label_39, null);
			panelNacimiento.add(getT_tiempoGestacion(), null);
			panelNacimiento.add(getT_numPartos(), null);
			panelNacimiento.add(getT_peso(), null);
			panelNacimiento.add(getT_talla(), null);
			panelNacimiento.add(getScroll_causas(), null);
			panelNacimiento.add(getT_tipoParto(), null);			
			panelNacimiento.add(getScroll_enfermedadesEmbarazo(), null);
			panelNacimiento.add(getScroll_complicaciones(), null);
			panelNacimiento.add(label_310, null);
			panelNacimiento.add(label_311, null);
			panelNacimiento.add(getT_lactancia(), null);
			panelNacimiento.add(label_312, null);
			panelNacimiento.add(label_313, null);
			panelNacimiento.add(getT_tetero(), null);
			panelNacimiento.add(label_314, null);
			panelNacimiento.add(label_315, null);
			panelNacimiento.add(getT_ablactacion(), null);
			panelNacimiento.add(getChk_siLactancia(), null);
			panelNacimiento.add(getChk_noLactancia(), null);
			panelNacimiento.add(getChk_siTetero(), null);
			panelNacimiento.add(getChk_noTetero(), null);
			panelNacimiento.add(getChk_siAblactacion(), null);
			panelNacimiento.add(getChk_noAblactacion(), null);
			panelNacimiento.add(jLabel, null);
			panelNacimiento.add(jLabel1, null);
			panelNacimiento.add(getJScrollPane8(), null);
		}
		return panelNacimiento;
	}

	/**
	 * This method initializes panelFamiliares	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelFamiliares() {
		if (panelFamiliares == null) {
			lbl_41 = new JLabel();
			lbl_41.setText("Observaciones:");
			lbl_41.setSize(new Dimension(118, 20));
			lbl_41.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_41.setLocation(new Point(24, 225));
			panelFamiliares = new JPanel();
			panelFamiliares.setLayout(null);
			panelFamiliares.setBorder(BorderFactory.createTitledBorder(new 

SoftBevelBorder(SoftBevelBorder.RAISED), "Antecedentes Familiares", 

TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 

Font.PLAIN, 18), Color.blue));
			panelFamiliares.add(getB_siguiente4(), null);
			panelFamiliares.add(getB_atras4(), null);
			panelFamiliares.add(getJScrollPane(), null);
			panelFamiliares.add(lbl_41, null);
			panelFamiliares.add(getJScrollPane7(), null);
			panelFamiliares.add(getPanel_1(), null);
			panelFamiliares.add(getPanel_2(), null);
		}
		return panelFamiliares;
	}

	/**
	 * This method initializes b_siguiente3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_siguiente3() {
		if (b_siguiente3 == null) {
			b_siguiente3 = new JButton();
			b_siguiente3.setSize(new Dimension(123, 34));
			b_siguiente3.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image010.png")));
			//b_siguiente3.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image010.png")));
			b_siguiente3.setText("Siguiente");
			b_siguiente3.setMnemonic(KeyEvent.VK_S);
			b_siguiente3.setLocation(new Point(444, 546));
			b_siguiente3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(3);
				}
			});
		}
		return b_siguiente3;
	}

	/**
	 * This method initializes b_siguiente4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_siguiente4() {
		if (b_siguiente4 == null) {
			b_siguiente4 = new JButton();
			b_siguiente4.setSize(new Dimension(123, 34));
			b_siguiente4.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image010.png")));
			//b_siguiente4.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image010.png")));
			b_siguiente4.setText("Siguiente");
			b_siguiente4.setMnemonic(KeyEvent.VK_S);
			b_siguiente4.setLocation(new Point(440, 280));
			b_siguiente4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(4);
				}
			});
		}
		return b_siguiente4;
	}

	/**
	 * This method initializes b_atras2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_atras2() {
		if (b_atras2 == null) {
			b_atras2 = new JButton();
			b_atras2.setLocation(new Point(280, 280));
			b_atras2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image011.png")));
			//b_atras2.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image011.png")));
			b_atras2.setText("Atras");
			b_atras2.setHorizontalAlignment(SwingConstants.CENTER);
			b_atras2.setMnemonic(KeyEvent.VK_A);
			b_atras2.setSize(new Dimension(123, 34));
			b_atras2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(0);
				}
			});
		}
		return b_atras2;
	}

	/**
	 * This method initializes b_atras3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_atras3() {
		if (b_atras3 == null) {
			b_atras3 = new JButton();
			b_atras3.setLocation(new Point(284, 546));
			b_atras3.setText("Atras");
			b_atras3.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image011.png")));
			//b_atras3.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image011.png")));
			b_atras3.setMnemonic(KeyEvent.VK_A);
			b_atras3.setSize(new Dimension(123, 34));
			b_atras3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(1);
				}
			});
		}
		return b_atras3;
	}

	/**
	 * This method initializes b_atras4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_atras4() {
		if (b_atras4 == null) {
			b_atras4 = new JButton();
			b_atras4.setSize(new Dimension(123, 34));
			b_atras4.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image011.png")));
			//b_atras4.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image011.png")));
			b_atras4.setText("Atras");
			b_atras4.setMnemonic(KeyEvent.VK_A);
			b_atras4.setLocation(new Point(280, 280));
			b_atras4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(2);
				}
			});
		}
		return b_atras4;
	}

	/**
	 * This method initializes t_tiempoGestacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_tiempoGestacion() {
		if (t_tiempoGestacion == null) {
			t_tiempoGestacion = new JTextField();
			t_tiempoGestacion.setLocation(new Point(210, 30));
			t_tiempoGestacion.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tiempoGestacion.setSize(new Dimension(80, 20));
		}
		return t_tiempoGestacion;
	}

	/**
	 * This method initializes t_numPartos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_numPartos() {
		if (t_numPartos == null) {
			t_numPartos = new JTextField();
			t_numPartos.setLocation(new Point(486, 30));
			t_numPartos.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_numPartos.setSize(new Dimension(80, 20));
			t_numPartos.setVisible(false);
		}
		return t_numPartos;
	}

	/**
	 * This method initializes t_peso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_peso() {
		if (t_peso == null) {
			t_peso = new JTextField();
			t_peso.setLocation(new Point(210, 65));
			t_peso.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_peso.setSize(new Dimension(56, 20));
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
			t_talla.setLocation(new Point(486, 65));
			t_talla.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_talla.setSize(new Dimension(80, 20));
		}
		return t_talla;
	}

	/**
	 * This method initializes t_tipoParto	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getT_tipoParto() {
		if (t_tipoParto == null) {
			t_tipoParto = new JComboBox();
			t_tipoParto.setLocation(new Point(210, 100));
			t_tipoParto.setEditable(false);
			t_tipoParto.setFont(new Font("Dialog", Font.BOLD, 14));
			t_tipoParto.setOpaque(true);
			t_tipoParto.setSize(new Dimension(148, 24));			
			t_tipoParto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_tipoParto.getSelectedItem().toString().compareToIgnoreCase("Normal")==0){
						t_causas.setEnabled(false);
					}
					else{
						t_causas.setEnabled(true);
					}
				}
			});
			t_tipoParto.addItem("Normal");
			t_tipoParto.addItem("Cesárea");
			//t_tipoParto.addItem("Adopción");
			//t_tipoParto.addItem("Edema");
			t_tipoParto.addItem("Instrumental");
			//t_tipoParto.addItem("Gemelar");			
			//t_tipoParto.addItem("Trillizos");
			t_tipoParto.addItem("Otro");
		}
		return t_tipoParto;
	}

	/**
	 * This method initializes scroll_causas	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_causas() {
		if (scroll_causas == null) {
			scroll_causas = new JScrollPane();
			scroll_causas.setSize(new Dimension(353, 59));
			scroll_causas.setLocation(new Point(210, 135));
			scroll_causas.setViewportView(getT_causas());
		}
		return scroll_causas;
	}

	/**
	 * This method initializes t_causas	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_causas() {
		if (t_causas == null) {
			t_causas = new JTextArea();
			t_causas.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_causas.setEnabled(false);
			t_causas.setWrapStyleWord(true);
			t_causas.setLineWrap(true);
			t_causas.setEditable(true);
		}
		return t_causas;
	}

	/**
	 * This method initializes scroll_enfermedadesEmbarazo	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_enfermedadesEmbarazo() {
		if (scroll_enfermedadesEmbarazo == null) {
			scroll_enfermedadesEmbarazo = new JScrollPane();
			scroll_enfermedadesEmbarazo.setLocation(new Point(210, 205));
			scroll_enfermedadesEmbarazo.setViewportView(getT_enfermedadesEmbarazo

());
			scroll_enfermedadesEmbarazo.setSize(new Dimension(353, 59));
		}
		return scroll_enfermedadesEmbarazo;
	}

	/**
	 * This method initializes scroll_complicaciones	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_complicaciones() {
		if (scroll_complicaciones == null) {
			scroll_complicaciones = new JScrollPane();
			scroll_complicaciones.setSize(new Dimension(353, 59));
			scroll_complicaciones.setViewportView(getT_complicaciones());
			scroll_complicaciones.setLocation(new Point(210, 278));
		}
		return scroll_complicaciones;
	}

	/**
	 * This method initializes t_enfermedadesEmbarazo	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_enfermedadesEmbarazo() {
		if (t_enfermedadesEmbarazo == null) {
			t_enfermedadesEmbarazo = new JTextArea();
			t_enfermedadesEmbarazo.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_enfermedadesEmbarazo.setLineWrap(true);
			t_enfermedadesEmbarazo.setSize(new Dimension(350, 59));
		}
		return t_enfermedadesEmbarazo;
	}

	/**
	 * This method initializes t_complicaciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_complicaciones() {
		if (t_complicaciones == null) {
			t_complicaciones = new JTextArea();
			t_complicaciones.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_complicaciones.setLineWrap(true);
		}
		return t_complicaciones;
	}
	
	public String getRutaArchivos(String path){
        if(urlArchivos == null || urlArchivos.compareTo("") == 0){
            URI n = null;
            urlArchivos = this.getClass().getProtectionDomain().getCodeSource().getLocation

().toString();
            try {
                File aux = new File(new URL(urlArchivos).toURI());
                n = aux.getParentFile().toURI();
            } catch (Exception ex) {
            }
            urlArchivos = n.toString();
            int val = urlArchivos.indexOf("build");
            if (val > 0) {
                urlArchivos = urlArchivos.replaceAll("build/", "");
            }
        }
        return urlArchivos + path;
    }

    public URL getURLArchivos(String path){
        try{
            return new URL(getRutaArchivos(path));
        }
        catch(Exception ex){
            return null;
        }
    }

	/**
	 * This method initializes chk_asma	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_asma() {
		if (chk_asma == null) {
			chk_asma = new JCheckBox();
			chk_asma.setLocation(new Point(3, 5));
			chk_asma.setSize(new Dimension(20, 20));
			chk_asma.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(1);
				}
			});
		}
		return chk_asma;
	}

	/**
	 * This method initializes chk_rinitis	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_rinitis() {
		if (chk_rinitis == null) {
			chk_rinitis = new JCheckBox();
			chk_rinitis.setLocation(new Point(3, 30));
			chk_rinitis.setSize(new Dimension(20, 20));
			chk_rinitis.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(2);
				}
			});
		}
		return chk_rinitis;
	}

	/**
	 * This method initializes chk_dermatitis	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_dermatitis() {
		if (chk_dermatitis == null) {
			chk_dermatitis = new JCheckBox();
			chk_dermatitis.setPreferredSize(new Dimension(18, 18));
			chk_dermatitis.setLocation(new Point(130, 5));
			chk_dermatitis.setSize(new Dimension(20, 20));
			chk_dermatitis.setMnemonic(KeyEvent.VK_UNDEFINED);
			chk_dermatitis.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(3);
				}
			});
		}
		return chk_dermatitis;
	}

	/**
	 * This method initializes chk_prurito	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_prurito() {
		if (chk_prurito == null) {
			chk_prurito = new JCheckBox();
			chk_prurito.setPreferredSize(new Dimension(18, 18));
			chk_prurito.setSize(new Dimension(20, 20));
			chk_prurito.setLocation(new Point(3, 57));
			chk_prurito.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(7);
				}
			});
		}
		return chk_prurito;
	}

	/**
	 * This method initializes chk_hipertension	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_hipertension() {
		if (chk_hipertension == null) {
			chk_hipertension = new JCheckBox();
			chk_hipertension.setSize(new Dimension(20, 20));
			chk_hipertension.setLocation(new Point(3, 5));
			chk_hipertension.addActionListener(new java.awt.event.ActionListener() 

{
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(6);
				}
			});
		}
		return chk_hipertension;
	}

	/**
	 * This method initializes chk_diabetes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_diabetes() {
		if (chk_diabetes == null) {
			chk_diabetes = new JCheckBox();
			chk_diabetes.setSize(new Dimension(20, 20));
			chk_diabetes.setLocation(new Point(3, 55));
			chk_diabetes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(4);
				}
			});
		}
		return chk_diabetes;
	}

	/**
	 * This method initializes chk_cancer	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_cancer() {
		if (chk_cancer == null) {
			chk_cancer = new JCheckBox();
			chk_cancer.setLocation(new Point(3, 80));
			chk_cancer.setPreferredSize(new Dimension(20, 20));
			chk_cancer.setSize(new Dimension(20, 20));
			chk_cancer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(8);
				}
			});
		}
		return chk_cancer;
	}

	/**
	 * This method initializes chk_cardiopatias	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_cardiopatias() {
		if (chk_cardiopatias == null) {
			chk_cardiopatias = new JCheckBox();
			chk_cardiopatias.setSize(new Dimension(20, 20));
			chk_cardiopatias.setLocation(new Point(3, 30));
			chk_cardiopatias.addActionListener(new java.awt.event.ActionListener() 

{
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(9);
				}
			});
		}
		return chk_cardiopatias;
	}

	/**
	 * This method initializes chk_tiroides	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_tiroides() {
		if (chk_tiroides == null) {
			chk_tiroides = new JCheckBox();
			chk_tiroides.setSize(new Dimension(18, 18));
			chk_tiroides.setLocation(new Point(160, 5));
			chk_tiroides.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(5);
				}
			});
		}
		return chk_tiroides;
	}

	/**
	 * This method initializes chk_otras	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_otras() {
		if (chk_otras == null) {
			chk_otras = new JCheckBox();
			chk_otras.setSize(new Dimension(18, 18));
			chk_otras.setLocation(new Point(160, 30));
			chk_otras.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(10);
				}
			});
		}
		return chk_otras;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(573, 79));
			jScrollPane.setLocation(new Point(10, 140));
			jScrollPane.setViewportView(getT_antecedentesFamiliares());
		}
		return jScrollPane;
	}

	
	/**
	 * This method initializes t_antecedentesFamiliares	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getT_antecedentesFamiliares() {
		if (t_antecedentesFamiliares == null) {
			t_antecedentesFamiliares = new JTextArea();
			t_antecedentesFamiliares.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_antecedentesFamiliares.setLineWrap(true);
			t_antecedentesFamiliares.setEditable(false);
			t_antecedentesFamiliares.requestFocus();
		}
		return t_antecedentesFamiliares;
	}

	/**
	 * This method initializes btn_51	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_51() {
		if (btn_51 == null) {
			btn_51 = new JButton();
			btn_51.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_51.setSize(new Dimension(91, 20));
			btn_51.setLocation(new Point(30, 5));
			btn_51.setText("Asma");
			btn_51.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(1);
				}
			});
		}
		return btn_51;
	}

	/**
	 * This method initializes btn_56	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_56() {
		if (btn_56 == null) {
			btn_56 = new JButton();
			btn_56.setText("Diábetes");
			btn_56.setSize(new Dimension(91, 20));
			btn_56.setLocation(new Point(30, 55));
			btn_56.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_56.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(4);
				}
			});
		}
		return btn_56;
	}

	/**
	 * This method initializes btn_57	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_57() {
		if (btn_57 == null) {
			btn_57 = new JButton();
			btn_57.setText("Cancer");
			btn_57.setSize(new Dimension(91, 20));
			btn_57.setLocation(new Point(30, 80));
			btn_57.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_57.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(8);
				}
			});
		}
		return btn_57;
	}

	/**
	 * This method initializes btn_52	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_52() {
		if (btn_52 == null) {
			btn_52 = new JButton();
			btn_52.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_52.setSize(new Dimension(91, 20));
			btn_52.setLocation(new Point(30, 30));
			btn_52.setText("Rinitis");
			btn_52.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(2);
				}
			});
		}
		return btn_52;
	}

	/**
	 * This method initializes btn_59	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_59() {
		if (btn_59 == null) {
			btn_59 = new JButton();
			btn_59.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_59.setSize(new Dimension(91, 20));
			btn_59.setLocation(new Point(187, 5));
			btn_59.setText("Tiroides");
			btn_59.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(5);
				}
			});
		}
		return btn_59;
	}

	/**
	 * This method initializes btn_510	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_510() {
		if (btn_510 == null) {
			btn_510 = new JButton();
			btn_510.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_510.setSize(new Dimension(91, 20));
			btn_510.setLocation(new Point(187, 30));
			btn_510.setText("Otras");
			btn_510.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(10);
				}
			});
		}
		return btn_510;
	}

	/**
	 * This method initializes btn_53	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_53() {
		if (btn_53 == null) {
			btn_53 = new JButton();
			btn_53.setText("Dermatitis");
			btn_53.setSize(new Dimension(120, 20));
			btn_53.setLocation(new Point(157, 5));
			btn_53.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_53.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(3);
				}
			});
		}
		return btn_53;
	}

	/**
	 * This method initializes jButtonbtn_55	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonbtn_55() {
		if (jButtonbtn_55 == null) {
			jButtonbtn_55 = new JButton();
			jButtonbtn_55.setFont(new Font("Dialog", Font.PLAIN, 14));
			jButtonbtn_55.setSize(new Dimension(120, 20));
			jButtonbtn_55.setLocation(new Point(30, 5));
			jButtonbtn_55.setText("Hipertensión");
			jButtonbtn_55.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(6);
				}
			});
		}
		return jButtonbtn_55;
	}

	/**
	 * This method initializes btn_55	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_55() {
		if (btn_55 == null) {
			btn_55 = new JButton();
			btn_55.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_55.setSize(new Dimension(91, 20));
			btn_55.setLocation(new Point(30, 56));
			btn_55.setText("Prurigo");
			btn_55.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(7);
				}
			});
		}
		return btn_55;
	}

	/**
	 * This method initializes btn_58	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_58() {
		if (btn_58 == null) {
			btn_58 = new JButton();
			btn_58.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_58.setSize(new Dimension(120, 20));
			btn_58.setLocation(new Point(30, 30));
			btn_58.setText("Cardiopatías");
			btn_58.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(9);
				}
			});
		}
		return btn_58;
	}

	private JScrollPane getScrollAntecedentes(){
		if(scrollAntecedentes == null){
			 scrollAntecedentes = new JScrollPane();
			 scrollAntecedentes.setHorizontalScrollBarPolicy

(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			 scrollAntecedentes.getVerticalScrollBar().setUnitIncrement(20);
		     GroupLayout jPanel1Layout = new GroupLayout(getPanelAntecedentes());
		     panelAntecedentes.setLayout(jPanel1Layout);
		     jPanel1Layout.setHorizontalGroup(
		         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 600, Short.MAX_VALUE)
		     );
		     jPanel1Layout.setVerticalGroup(
		         jPanel1Layout.createParallelGroup

(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 750, Short.MAX_VALUE)
		     );
		     scrollAntecedentes.setViewportView(getPanelAntecedentes());
		}
		return scrollAntecedentes;
	}
	
	/**
	 * This method initializes panelAntecedentes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelAntecedentes() {
		if (panelAntecedentes == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel2.setLocation(new Point(13, 333));
			jLabel2.setSize(new Dimension(119, 20));
			jLabel2.setText("Otras alergias:");
			label_522 = new JLabel();
			label_522.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_522.setHorizontalAlignment(SwingConstants.RIGHT);
			label_522.setSize(new Dimension(123, 20));
			label_522.setLocation(new Point(11, 655));
			label_522.setText("Antecedentes:");
			label_521 = new JLabel();
			label_521.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_521.setHorizontalAlignment(SwingConstants.RIGHT);
			label_521.setLocation(new Point(71, 635));
			label_521.setSize(new Dimension(63, 20));
			label_521.setText("Otros");
			label_520 = new JLabel();
			label_520.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_520.setLocation(new Point(-1, 578));
			label_520.setSize(new Dimension(135, 20));
			label_520.setHorizontalAlignment(SwingConstants.RIGHT);
			label_520.setText("Genito Urinarios:");
			label_519 = new JLabel();
			label_519.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_519.setLocation(new Point(11, 558));
			label_519.setSize(new Dimension(123, 20));
			label_519.setHorizontalAlignment(SwingConstants.RIGHT);
			label_519.setText("Antecedentes");
			label_518 = new JLabel();
			label_518.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_518.setSize(new Dimension(95, 16));
			label_518.setLocation(new Point(39, 521));
			label_518.setHorizontalAlignment(SwingConstants.RIGHT);
			label_518.setText("Intestinales:");
			label_517 = new JLabel();
			label_517.setText("Gastro");
			label_517.setSize(new Dimension(63, 20));
			label_517.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_517.setHorizontalAlignment(SwingConstants.RIGHT);
			label_517.setLocation(new Point(71, 501));
			label_516 = new JLabel();
			label_516.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_516.setLocation(new Point(11, 481));
			label_516.setSize(new Dimension(123, 20));
			label_516.setHorizontalAlignment(SwingConstants.RIGHT);
			label_516.setText("Antecedentes");
			label_515 = new JLabel();
			label_515.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_515.setHorizontalAlignment(SwingConstants.RIGHT);
			label_515.setLocation(new Point(44, 424));
			label_515.setSize(new Dimension(90, 20));
			label_515.setText("Eruptivos:");
			label_514 = new JLabel();
			label_514.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_514.setHorizontalAlignment(SwingConstants.RIGHT);
			label_514.setLocation(new Point(11, 404));
			label_514.setSize(new Dimension(123, 20));
			label_514.setText("Antecedentes");
			label_513 = new JLabel();
			label_513.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_513.setPreferredSize(new Dimension(90, 20));
			label_513.setLocation(new Point(419, 285));
			label_513.setSize(new Dimension(90, 20));
			label_513.setHorizontalAlignment(SwingConstants.RIGHT);
			label_513.setText("Burxismo");
			label_512 = new JLabel();
			label_512.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_512.setPreferredSize(new Dimension(70, 20));
			label_512.setLocation(new Point(270, 285));
			label_512.setSize(new Dimension(58, 20));
			label_512.setHorizontalAlignment(SwingConstants.RIGHT);
			label_512.setText("Ronca");
			label_511 = new JLabel();
			label_511.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_511.setLocation(new Point(38, 285));
			label_511.setSize(new Dimension(140, 20));
			label_511.setHorizontalAlignment(SwingConstants.RIGHT);
			label_511.setText("Respiracion Oral");
			label_510 = new JLabel();
			label_510.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_510.setLocation(new Point(425, 240));
			label_510.setSize(new Dimension(81, 20));
			label_510.setText("Continua");
			label_59 = new JLabel();
			label_59.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_59.setLocation(new Point(238, 240));
			label_59.setSize(new Dimension(95, 20));
			label_59.setText("Intermitente");
			label_58 = new JLabel();
			label_58.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			label_58.setLocation(new Point(73, 241));
			label_58.setSize(new Dimension(106, 20));
			label_58.setText("Frecuencia:");
			label_57 = new JLabel();
			label_57.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_57.setLocation(new Point(22, 190));
			label_57.setSize(new Dimension(115, 20));
			label_57.setHorizontalAlignment(SwingConstants.RIGHT);
			label_57.setText("Inter-Crisis:");
			label_56 = new JLabel();
			label_56.setHorizontalAlignment(SwingConstants.RIGHT);
			label_56.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_56.setSize(new Dimension(83, 20));
			label_56.setLocation(new Point(54, 170));
			label_56.setText("Sintomas:");
			label_55 = new JLabel();
			label_55.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_55.setLocation(new Point(34, 140));
			label_55.setSize(new Dimension(105, 20));
			label_55.setHorizontalAlignment(SwingConstants.RIGHT);
			label_55.setText("Ultima crisis:");
			label_54 = new JLabel();
			label_54.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_54.setHorizontalAlignment(SwingConstants.RIGHT);
			label_54.setLocation(new Point(20, 111));
			label_54.setSize(new Dimension(119, 20));
			label_54.setText("Primera crisis:");
			label_53 = new JLabel();
			label_53.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			label_53.setLocation(new Point(55, 89));
			label_53.setSize(new Dimension(71, 20));
			label_53.setHorizontalAlignment(SwingConstants.RIGHT);
			label_53.setText("Crisis:");
			label_52 = new JLabel();
			label_52.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_52.setHorizontalAlignment(SwingConstants.RIGHT);
			label_52.setLocation(new Point(10, 45));
			label_52.setSize(new Dimension(126, 20));
			label_52.setText("Respiratorios:");
			label_51 = new JLabel();
			label_51.setFont(new Font("Dialog", Font.PLAIN, 18));
			label_51.setLocation(new Point(39, 25));
			label_51.setSize(new Dimension(97, 20));
			label_51.setHorizontalAlignment(SwingConstants.RIGHT);
			label_51.setText("Síntomas:");
			panelAntecedentes = new JPanel();
			panelAntecedentes.setLayout(null);
			panelAntecedentes.add(label_51, null);
			panelAntecedentes.add(label_52, null);
			panelAntecedentes.add(getJScrollPane1(), null);
			panelAntecedentes.add(label_53, null);
			panelAntecedentes.add(label_54, null);
			panelAntecedentes.add(label_55, null);
			panelAntecedentes.add(label_56, null);
			panelAntecedentes.add(label_57, null);
			panelAntecedentes.add(getJScrollPane2(), null);
			panelAntecedentes.add(label_58, null);
			panelAntecedentes.add(label_59, null);
			panelAntecedentes.add(label_510, null);
			panelAntecedentes.add(getChk_continua(), null);
			panelAntecedentes.add(getChk_intermitente(), null);
			panelAntecedentes.add(label_511, null);
			panelAntecedentes.add(label_512, null);
			panelAntecedentes.add(label_513, null);
			panelAntecedentes.add(getChk_respiracionOral(), null);
			panelAntecedentes.add(getChk_ronca(), null);
			panelAntecedentes.add(getChk_burxismo(), null);
			panelAntecedentes.add(getJScrollPane3(), null);
			panelAntecedentes.add(getJScrollPane4(), null);
			panelAntecedentes.add(getJScrollPane5(), null);
			panelAntecedentes.add(getJScrollPane6(), null);
			panelAntecedentes.add(label_514, null);
			panelAntecedentes.add(label_515, null);
			panelAntecedentes.add(label_516, null);
			panelAntecedentes.add(label_517, null);
			panelAntecedentes.add(label_518, null);
			panelAntecedentes.add(label_519, null);
			panelAntecedentes.add(label_520, null);
			panelAntecedentes.add(label_521, null);
			panelAntecedentes.add(label_522, null);
			panelAntecedentes.add(getB_atras5(), null);
			panelAntecedentes.add(getB_siguiente5(), null);
			panelAntecedentes.add(getT_fechaCrisis1(), null);
			panelAntecedentes.add(getT_fechaCrisis2(), null);
			panelAntecedentes.add(jLabel2, null);
			panelAntecedentes.add(getJScrollPane9(), null);
		}
		return panelAntecedentes;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setLocation(new Point(145, 24));
			jScrollPane1.setSize(new Dimension(425, 59));
			jScrollPane1.setViewportView(getT_sintomas());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes t_sintomas	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_sintomas() {
		if (t_sintomas == null) {
			t_sintomas = new JTextArea();
			t_sintomas.setText("");
			t_sintomas.setLineWrap(true);
			t_sintomas.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return t_sintomas;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setLocation(new Point(145, 170));
			jScrollPane2.setViewportView(getT_interCrisis());
			jScrollPane2.setSize(new Dimension(425, 59));
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes t_interCrisis	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_interCrisis() {
		if (t_interCrisis == null) {
			t_interCrisis = new JTextArea();
			t_interCrisis.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_interCrisis.setLineWrap(true);
		}
		return t_interCrisis;
	}

	/**
	 * This method initializes chk_continua	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_continua() {
		if (chk_continua == null) {
			chk_continua = new JRadioButton();
			chk_continua.setSize(new Dimension(21, 21));
			chk_continua.setLocation(new Point(513, 240));
			chk_continua.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(chk_intermitente.isSelected()){
						chk_intermitente.setSelected(false);
					}
				}
			});
		}
		return chk_continua;
	}

	/**
	 * This method initializes chk_intermitente	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_intermitente() {
		if (chk_intermitente == null) {
			chk_intermitente = new JRadioButton();
			chk_intermitente.setSize(new Dimension(21, 21));
			chk_intermitente.setLocation(new Point(342, 240));
			chk_intermitente.addActionListener(new java.awt.event.ActionListener() 

{
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(chk_continua.isSelected()){
						chk_continua.setSelected(false);
					}
				}
			});
		}
		return chk_intermitente;
	}

	/**
	 * This method initializes chk_respiracionOral	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_respiracionOral() {
		if (chk_respiracionOral == null) {
			chk_respiracionOral = new JCheckBox();
			chk_respiracionOral.setLocation(new Point(183, 285));
			chk_respiracionOral.setSize(new Dimension(20, 20));
		}
		return chk_respiracionOral;
	}

	/**
	 * This method initializes chk_ronca	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_ronca() {
		if (chk_ronca == null) {
			chk_ronca = new JCheckBox();
			chk_ronca.setLocation(new Point(333, 285));
			chk_ronca.setSize(new Dimension(20, 20));
		}
		return chk_ronca;
	}

	/**
	 * This method initializes chk_burxismo	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_burxismo() {
		if (chk_burxismo == null) {
			chk_burxismo = new JCheckBox();
			chk_burxismo.setLocation(new Point(515, 285));
			chk_burxismo.setSize(new Dimension(20, 20));
		}
		return chk_burxismo;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setLocation(new Point(141, 404));
			jScrollPane3.setSize(new Dimension(425, 59));
			jScrollPane3.setViewportView(getT_antecedentesEruptivas());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes t_antecedentesEruptivas	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_antecedentesEruptivas() {
		if (t_antecedentesEruptivas == null) {
			t_antecedentesEruptivas = new JTextArea();
			t_antecedentesEruptivas.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_antecedentesEruptivas.setLineWrap(true);
		}
		return t_antecedentesEruptivas;
	}

	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setLocation(new Point(141, 481));
			jScrollPane4.setViewportView(getT_antecedentesGI());
			jScrollPane4.setSize(new Dimension(425, 59));
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jScrollPane5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setSize(new Dimension(425, 59));
			jScrollPane5.setViewportView(getT_antecedentesGU());
			jScrollPane5.setLocation(new Point(141, 558));
		}
		return jScrollPane5;
	}

	/**
	 * This method initializes jScrollPane6	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane6() {
		if (jScrollPane6 == null) {
			jScrollPane6 = new JScrollPane();
			jScrollPane6.setSize(new Dimension(425, 59));
			jScrollPane6.setViewportView(getT_antecedentesOtros());
			jScrollPane6.setLocation(new Point(141, 633));
		}
		return jScrollPane6;
	}

	/**
	 * This method initializes t_antecedentesGI	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_antecedentesGI() {
		if (t_antecedentesGI == null) {
			t_antecedentesGI = new JTextArea();
			t_antecedentesGI.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_antecedentesGI.setLineWrap(true);
		}
		return t_antecedentesGI;
	}

	/**
	 * This method initializes t_antecedentesGU	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_antecedentesGU() {
		if (t_antecedentesGU == null) {
			t_antecedentesGU = new JTextArea();
			t_antecedentesGU.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_antecedentesGU.setLineWrap(true);
		}
		return t_antecedentesGU;
	}

	/**
	 * This method initializes t_antecedentesOtros	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_antecedentesOtros() {
		if (t_antecedentesOtros == null) {
			t_antecedentesOtros = new JTextArea();
			t_antecedentesOtros.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_antecedentesOtros.setLineWrap(true);
		}
		return t_antecedentesOtros;
	}

	/**
	 * This method initializes b_atras5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_atras5() {
		if (b_atras5 == null) {
			b_atras5 = new JButton();
			b_atras5.setSize(new Dimension(123, 34));
			b_atras5.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image011.png")));
			//b_atras5.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image011.png")));
			b_atras5.setText("Atras");
			b_atras5.setMnemonic(KeyEvent.VK_A);
			b_atras5.setLocation(new Point(279, 708));
			b_atras5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(3);
				}
			});
		}
		return b_atras5;
	}

	/**
	 * This method initializes b_siguiente5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_siguiente5() {
		if (b_siguiente5 == null) {
			b_siguiente5 = new JButton();
			b_siguiente5.setSize(new Dimension(123, 34));
			b_siguiente5.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image010.png")));
			//b_siguiente5.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image010.png")));
			b_siguiente5.setText("Siguiente");
			b_siguiente5.setMnemonic(KeyEvent.VK_S);
			b_siguiente5.setLocation(new Point(439, 708));
			b_siguiente5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pestanas.setSelectedIndex(5);
				}
			});
		}
		return b_siguiente5;
	}

	/**
	 * This method initializes t_lactancia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_lactancia() {
		if (t_lactancia == null) {
			t_lactancia = new JTextField();
			t_lactancia.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_lactancia.setSize(new Dimension(163, 20));
			t_lactancia.setLocation(new Point(406, 447));
			t_lactancia.setEditable(false);
		}
		return t_lactancia;
	}

	/**
	 * This method initializes t_tetero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_tetero() {
		if (t_tetero == null) {
			t_tetero = new JTextField();
			t_tetero.setLocation(new Point(406, 477));
			t_tetero.setSize(new Dimension(163, 20));
			t_tetero.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tetero.setEditable(false);
		}
		return t_tetero;
	}

	/**
	 * This method initializes t_ablactacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_ablactacion() {
		if (t_ablactacion == null) {
			t_ablactacion = new JTextField();
			t_ablactacion.setLocation(new Point(406, 507));
			t_ablactacion.setSize(new Dimension(163, 20));
			t_ablactacion.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_ablactacion.setEditable(false);
		}
		return t_ablactacion;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setLocation(new Point(620, 335));
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image021.png")));
			btn_guardar.setOpaque(true);
			btn_guardar.setToolTipText("Guardar historia");
			btn_guardar.setSize(new Dimension(60, 60));
			btn_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					guardar();			
				}
			});
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
			btn_cancelar.setLocation(new Point(688, 335));
			btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image022.png")));
			btn_cancelar.setToolTipText("Cerrar ventana");
			btn_cancelar.setSize(new Dimension(60, 60));
			btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrar();
				}
			});
		}
		return btn_cancelar;
	}

	/**
	 * This method initializes b_atras1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_atras1() {
		if (b_atras1 == null) {
			b_atras1 = new JButton();
			b_atras1.setLocation(new Point(280, 280));
			b_atras1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image011.png")));
			//b_atras1.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image011.png")));
			b_atras1.setText("Atras");
			b_atras1.setHorizontalAlignment(SwingConstants.CENTER);
			b_atras1.setMnemonic(KeyEvent.VK_A);
			b_atras1.setEnabled(false);
			b_atras1.setSize(new Dimension(123, 34));
		}
		return b_atras1;
	}

	public String getAsma() {
		return asma;
	}

	public void setAsma(String param) {
		this.asma = param;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String param) {
		this.cancer = param;
	}

	public String getCardiopatias() {
		return cardiopatias;
	}

	public void setCardiopatias(String param) {
		this.cardiopatias = param;
	}

	public String getDermatitis() {
		return dermatitis;
	}

	public void setDermatitis(String param) {
		this.dermatitis = param;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String param) {
		this.diabetes = param;
	}

	public String getHipertension() {
		return hipertension;
	}

	public void setHipertension(String param) {
		this.hipertension = param;
	}

	public String getOtras() {
		return otras;
	}

	public void setOtras(String param) {
		this.otras = param;
	}
	
	public void setUrticaria(String param) {
		this.urticaria = param;
	}
	
	public String getUrticaria(){
		return urticaria;
	}

	public String getPrurito() {
		return prurito;
	}

	public void setPrurito(String param) {
		this.prurito = param;
	}

	public String getRinitis() {
		return rinitis;
	}

	public void setRinitis(String param) {
		this.rinitis = param;
	}

	public String getTiroides() {
		return tiroides;
	}

	public void setTiroides(String param) {
		this.tiroides = param;
	}
	
	public void cambiarAntecedente(int sel){
		t_antecedentesFamiliares.setEditable(true);
		switch(lastAntecedente){
			case 1: asma = t_antecedentesFamiliares.getText();
					btn_51.setEnabled(true);
					break;
			case 2: rinitis = t_antecedentesFamiliares.getText();
					btn_52.setEnabled(true);
					break;
			case 3: dermatitis = t_antecedentesFamiliares.getText();
					btn_53.setEnabled(true);
					break;
			case 4: diabetes = t_antecedentesFamiliares.getText();
					btn_56.setEnabled(true);
					break;
			case 5: tiroides = t_antecedentesFamiliares.getText();
					btn_59.setEnabled(true);
					break;
			case 6: hipertension = t_antecedentesFamiliares.getText();
					jButtonbtn_55.setEnabled(true);
					break;
			case 7: prurito = t_antecedentesFamiliares.getText();
					btn_55.setEnabled(true);
					break;
			case 8: cancer = t_antecedentesFamiliares.getText();
					btn_57.setEnabled(true);
					break;
			case 9: cardiopatias = t_antecedentesFamiliares.getText();
					btn_58.setEnabled(true);
					break;
			case 10: otras = t_antecedentesFamiliares.getText();
					btn_510.setEnabled(true);
					break;
			case 11: urticaria = t_antecedentesFamiliares.getText();
					btn_511.setEnabled(true);
					break;
		}
		
		lastAntecedente = sel;
		t_antecedentesFamiliares.setText("");
		
		switch(sel){
			case 1: t_antecedentesFamiliares.setText(asma);
					btn_51.setEnabled(false);
					break;
			case 2: t_antecedentesFamiliares.setText(rinitis);
					btn_52.setEnabled(false);
					break;
			case 3: t_antecedentesFamiliares.setText(dermatitis);
					btn_53.setEnabled(false);
					break;
			case 4: t_antecedentesFamiliares.setText(diabetes);
					btn_56.setEnabled(false);
					break;
			case 5: t_antecedentesFamiliares.setText(tiroides);
					btn_59.setEnabled(false);
					break;
			case 6: t_antecedentesFamiliares.setText(hipertension);
					jButtonbtn_55.setEnabled(false);
					break;
			case 7: t_antecedentesFamiliares.setText(prurito);
					btn_55.setEnabled(false);
					break;
			case 8: t_antecedentesFamiliares.setText(cancer);
					btn_57.setEnabled(false);
					break;
			case 9: t_antecedentesFamiliares.setText(cardiopatias);
					btn_58.setEnabled(false);
					break;
			case 10: t_antecedentesFamiliares.setText(otras);
					btn_510.setEnabled(false);
					break;
			case 11: t_antecedentesFamiliares.setText(urticaria);
					btn_511.setEnabled(false);
					break;
		}
		t_antecedentesFamiliares.requestFocus();		
	}

	/**
	 * This method initializes btn_vacunas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_vacunas() {
		if (btn_vacunas == null) {
			btn_vacunas = new JButton();
			btn_vacunas.setLocation(550, 335);
			btn_vacunas.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image040.png")));
			btn_vacunas.setOpaque(true);
			btn_vacunas.setToolTipText("Ver vacunas");
			btn_vacunas.setMnemonic(KeyEvent.VK_UNDEFINED);
			btn_vacunas.setSize(new Dimension(60, 60));
			btn_vacunas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 new VentanaGestionarVacunas(gestor.getId_Historia(),gestor.getAutenticacion(),desktopPane);
					
				}
			});
		}		
		return btn_vacunas;
	}

	/**
	 * This method initializes btn_consultas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_consultas() {
		if (btn_consultas == null) {
			btn_consultas = new JButton();
			btn_consultas.setLocation(410, 335);
			btn_consultas.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image024.png")));
			btn_consultas.setOpaque(true);
			btn_consultas.setToolTipText("Ver consultas");
			btn_consultas.setMnemonic(KeyEvent.VK_UNDEFINED);
			btn_consultas.setSize(new Dimension(60, 60));
			btn_consultas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.listarConsultas();
				}
			});
		}
		return btn_consultas;
	}

	/**
	 * This method initializes btn_receta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_receta() {
		if (btn_receta == null) {
			btn_receta = new JButton();
			btn_receta.setLocation(270, 335);
			btn_receta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image043.png")));
			btn_receta.setOpaque(true);
			btn_receta.setToolTipText("Agregar receta");
			btn_receta.setBackground(Color.white);
			btn_receta.setMnemonic(KeyEvent.VK_UNDEFINED);
			btn_receta.setEnabled(false);
			btn_receta.setSize(new Dimension(60, 60));
			btn_receta.setVisible(false);
			btn_receta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					guardar();
				}
			});
		}
		return btn_receta;
	}

	/**
	 * This method initializes t_fechaCrisis1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_fechaCrisis1() {
		if (t_fechaCrisis1 == null) {
			t_fechaCrisis1 = new JTextField();
			t_fechaCrisis1.setLocation(new Point(145, 111));
			t_fechaCrisis1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_fechaCrisis1.setSize(new Dimension(250, 20));
		}
		return t_fechaCrisis1;
	}

	/**
	 * This method initializes t_fechaCrisis2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_fechaCrisis2() {
		if (t_fechaCrisis2 == null) {
			t_fechaCrisis2 = new JTextField();
			t_fechaCrisis2.setSize(new Dimension(250, 20));
			t_fechaCrisis2.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_fechaCrisis2.setLocation(new Point(145, 140));
		}
		return t_fechaCrisis2;
	}

	/**
	 * This method initializes btn_nuevaConsulta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_nuevaConsulta() {
		if (btn_nuevaConsulta == null) {
			btn_nuevaConsulta = new JButton();
			btn_nuevaConsulta.setLocation(new Point(340, 335));
			btn_nuevaConsulta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image044.png")));
			btn_nuevaConsulta.setToolTipText("Agregar nueva consulta");
			btn_nuevaConsulta.setSize(new Dimension(60, 60));
			btn_nuevaConsulta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.nuevaConsulta();
				}
			});
		}
		return btn_nuevaConsulta;
	}

	/**
	 * This method initializes btn_examenes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_examenes() {
		if (btn_examenes == null) {
			btn_examenes = new JButton();
			btn_examenes.setLocation(new Point(480, 335));
			btn_examenes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image106.png")));
			btn_examenes.setEnabled(true);
			btn_examenes.setToolTipText("Ver examenes");
			btn_examenes.setSize(new Dimension(60, 60));
			btn_examenes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.examenNuevo(desktopPane);
				}
			});
		}
		return btn_examenes;
	}

	/**
	 * This method initializes jScrollPane7	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane7() {
		if (jScrollPane7 == null) {
			jScrollPane7 = new JScrollPane();
			jScrollPane7.setSize(new Dimension(433, 50));
			jScrollPane7.setLocation(new Point(147, 225));
			jScrollPane7.setViewportView(getT_observaciones());
		}
		return jScrollPane7;
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
	 * This method initializes chk_urticaria	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getChk_urticaria() {
		if (chk_urticaria == null) {
			chk_urticaria = new JCheckBox();
			chk_urticaria.setLocation(new Point(3, 80));
			chk_urticaria.setSize(new Dimension(20, 20));
			chk_urticaria.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(11);
				}
			});
		}
		return chk_urticaria;
	}

	/**
	 * This method initializes btn_511	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_511() {
		if (btn_511 == null) {
			btn_511 = new JButton();
			btn_511.setText("Urticaria");
			btn_511.setSize(new Dimension(91, 20));
			btn_511.setLocation(new Point(30, 80));
			btn_511.setFont(new Font("Dialog", Font.PLAIN, 14));
			btn_511.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarAntecedente(11);
				}
			});
		}
		return btn_511;
	}

	/**
	 * This method initializes panel_1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panel_1.setBounds(new Rectangle(7, 28, 286, 109));
			panel_1.add(getBtn_51(), null);
			panel_1.add(getChk_asma(), null);
			panel_1.add(getBtn_52(), null);
			panel_1.add(getChk_rinitis(), null);
			panel_1.add(getBtn_53(), null);
			panel_1.add(getChk_dermatitis(), null);
			panel_1.add(getBtn_511(), null);
			panel_1.add(getChk_urticaria(), null);
			panel_1.add(getBtn_55(), null);
			panel_1.add(getChk_prurito(), null);
		}
		return panel_1;
	}

	/**
	 * This method initializes panel_2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBounds(new Rectangle(299, 28, 285, 109));
			panel_2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panel_2.add(getJButtonbtn_55(), null);
			panel_2.add(getChk_hipertension(), null);
			panel_2.add(getBtn_58(), null);
			panel_2.add(getChk_cardiopatias(), null);
			panel_2.add(getBtn_56(), null);
			panel_2.add(getChk_diabetes(), null);
			panel_2.add(getBtn_57(), null);
			panel_2.add(getChk_cancer(), null);
			panel_2.add(getChk_tiroides(), null);
			panel_2.add(getBtn_510(), null);
			panel_2.add(getChk_otras(), null);
			panel_2.add(getBtn_59(), null);
		}
		return panel_2;
	}

	/**
	 * This method initializes chk_siLactancia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_siLactancia() {
		if (chk_siLactancia == null) {
			chk_siLactancia = new JRadioButton();
			chk_siLactancia.setLocation(new Point(216, 447));
			chk_siLactancia.setText("Si");
			chk_siLactancia.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_siLactancia.setSize(new Dimension(45, 20));
			chk_siLactancia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noLactancia.setSelected(false);
					chk_siLactancia.setSelected(true);
					getT_lactancia().setEditable(true);
					getT_lactancia().requestFocus();
				}
			});
		}
		return chk_siLactancia;
	}

	/**
	 * This method initializes chk_noLactancia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_noLactancia() {
		if (chk_noLactancia == null) {
			chk_noLactancia = new JRadioButton();
			chk_noLactancia.setLocation(new Point(261, 447));
			chk_noLactancia.setText("No");
			chk_noLactancia.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_noLactancia.setSelected(true);
			chk_noLactancia.setSize(new Dimension(45, 20));
			chk_noLactancia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noLactancia.setSelected(true);
					chk_siLactancia.setSelected(false);
					getT_lactancia().setEditable(false);
				}
			});
		}
		return chk_noLactancia;
	}

	/**
	 * This method initializes chk_siTetero	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_siTetero() {
		if (chk_siTetero == null) {
			chk_siTetero = new JRadioButton();
			chk_siTetero.setLocation(new Point(216, 477));
			chk_siTetero.setText("Si");
			chk_siTetero.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_siTetero.setSize(new Dimension(45, 20));
			chk_siTetero.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noTetero.setSelected(false);
					chk_siTetero.setSelected(true);
					getT_tetero().setEditable(true);
					getT_tetero().requestFocus();
				}
			});
		}
		return chk_siTetero;
	}

	/**
	 * This method initializes chk_noTetero	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_noTetero() {
		if (chk_noTetero == null) {
			chk_noTetero = new JRadioButton();
			chk_noTetero.setLocation(new Point(261, 477));
			chk_noTetero.setText("No");
			chk_noTetero.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_noTetero.setSelected(true);
			chk_noTetero.setSize(new Dimension(45, 20));
			chk_noTetero.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noTetero.setSelected(true);
					chk_siTetero.setSelected(false);
					getT_tetero().setEditable(false);
				}
			});
		}
		return chk_noTetero;
	}

	/**
	 * This method initializes chk_siAblactacion	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_siAblactacion() {
		if (chk_siAblactacion == null) {
			chk_siAblactacion = new JRadioButton();
			chk_siAblactacion.setText("Si");
			chk_siAblactacion.setSize(new Dimension(45, 20));
			chk_siAblactacion.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_siAblactacion.setLocation(new Point(216, 507));
			chk_siAblactacion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noAblactacion.setSelected(false);
					chk_siAblactacion.setSelected(true);
					getT_ablactacion().setEditable(true);
					getT_ablactacion().requestFocus();
				}
			});
		}
		return chk_siAblactacion;
	}

	/**
	 * This method initializes chk_noAblactacion	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_noAblactacion() {
		if (chk_noAblactacion == null) {
			chk_noAblactacion = new JRadioButton();
			chk_noAblactacion.setLocation(new Point(261, 507));
			chk_noAblactacion.setText("No");
			chk_noAblactacion.setFont(new Font("Dialog", Font.BOLD, 14));
			chk_noAblactacion.setSelected(true);
			chk_noAblactacion.setSize(new Dimension(45, 20));
			chk_noAblactacion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_noAblactacion.setSelected(true);
					chk_siAblactacion.setSelected(false);
					getT_ablactacion().setEditable(true);
				}
			});
		}
		return chk_noAblactacion;
	}

	/**
	 * This method initializes jScrollPane8	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane8() {
		if (jScrollPane8 == null) {
			jScrollPane8 = new JScrollPane();
			jScrollPane8.setLocation(new Point(210, 358));
			jScrollPane8.setSize(new Dimension(353, 59));
			jScrollPane8.setViewportView(getT_condiciones());
		}
		return jScrollPane8;
	}

	/**
	 * This method initializes t_condiciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_condiciones() {
		if (t_condiciones == null) {
			t_condiciones = new JTextArea();
			t_condiciones.setLineWrap(true);
		}
		return t_condiciones;
	}

	/**
	 * This method initializes jScrollPane9	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane9() {
		if (jScrollPane9 == null) {
			jScrollPane9 = new JScrollPane();
			jScrollPane9.setLocation(new Point(141, 333));
			jScrollPane9.setViewportView(getT_alergias());
			jScrollPane9.setSize(new Dimension(425, 59));
		}
		return jScrollPane9;
	}

	/**
	 * This method initializes t_alergias	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_alergias() {
		if (t_alergias == null) {
			t_alergias = new JTextArea();
			t_alergias.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_alergias.setLineWrap(true);
		}
		return t_alergias;
	}

	/**
	 * This method initializes scrollVentana	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollVentana() {
		if (scrollVentana == null) {
			scrollVentana = new JScrollPane();
			scrollVentana.setHorizontalScrollBarPolicy

			(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollVentana.getVerticalScrollBar().setUnitIncrement(20);
					     GroupLayout jPanel1Layout = new GroupLayout(getPanelVentana1());
					     if(panelVentana1 == null) System.out.println("error mio");
					     panelVentana1.setLayout(jPanel1Layout);
					     jPanel1Layout.setHorizontalGroup(
					         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					         .addGap(0, 500, Short.MAX_VALUE)
					     );
					     jPanel1Layout.setVerticalGroup(
					         jPanel1Layout.createParallelGroup

			(javax.swing.GroupLayout.Alignment.LEADING)
					         .addGap(0, 490, Short.MAX_VALUE)
					     );
			scrollVentana.setViewportView(getPanelVentana1());
		}
		return scrollVentana;
	}

	/**
	 * This method initializes panelVentana1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelVentana1() {
		if (panelVentana1 == null) {
			panelVentana1 = new JPanel();
			panelVentana1.setLayout(null);
			panelVentana1.setSize(new Dimension(755, 1050));
			panelVentana1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			panelVentana1.add(getJButton(), null);
			panelVentana1.add(getB_Receta(), null);
			panelVentana1.add(getPanelActual(), null);
			panelVentana1.add(getBtn_crearReceta1(), null);
		}
		return panelVentana1;
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
			jButton.setSize(new Dimension(60, 60));
			jButton.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			jButton.setVisible(false);
		}
		return jButton;
	}

	/**
	 * This method initializes b_Receta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getB_Receta() {
		if (b_Receta == null) {
			b_Receta = new JButton();
			b_Receta.setToolTipText("Receta");
			b_Receta.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			b_Receta.setSize(new Dimension(60, 60));
			b_Receta.setLocation(new Point(500, 422));
			b_Receta.setEnabled(false);
			b_Receta.setBackground(Color.WHITE);
			b_Receta.setVisible(true);
			b_Receta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor1.verRecetaPrimeraConsulta();
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
			jLabel32 = new JLabel();
			jLabel32.setBounds(new Rectangle(389, 29, 41, 20));
			jLabel32.setToolTipText("Frecuencia Cardíaca");
			jLabel32.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel32.setText("P.F.:");
			jLabel32.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel5 = new JLabel();
			jLabel5.setLocation(new Point(380, 120));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("T.A.D.:");
			jLabel5.setSize(new Dimension(60, 20));
			jLabel4 = new JLabel();
			jLabel4.setLocation(new Point(246, 120));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel4.setToolTipText("Tensión arterial sistólica");
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("T.A.S.:");
			jLabel4.setSize(new Dimension(57, 20));
			jLabel111 = new JLabel();
			jLabel111.setLocation(new Point(193, 120));
			jLabel111.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel111.setText("x MIN");
			jLabel111.setSize(new Dimension(39, 20));
			jLabel31 = new JLabel();
			jLabel31.setLocation(new Point(68, 120));
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel31.setToolTipText("Frecuencia respiratoria");
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("F.R.:");
			jLabel31.setSize(new Dimension(52, 20));
			jLabel21 = new JLabel();
			jLabel21.setLocation(new Point(380, 90));
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel21.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21.setText("Temperatura:");
			jLabel21.setSize(new Dimension(120, 20));
			jLabel11 = new JLabel();
			jLabel11.setLocation(new Point(505, 60));
			jLabel11.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel11.setText("x MIN");
			jLabel11.setSize(new Dimension(45, 20));
			jLabel3 = new JLabel();
			jLabel3.setLocation(new Point(390, 60));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
			jLabel3.setToolTipText("Frecuencia Cardíaca");
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("F.C.:");
			jLabel3.setSize(new Dimension(50, 20));
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
			panelActual.setBounds(new Rectangle(6, 9, 577, 400));
			panelActual.setBorder(BorderFactory.createTitledBorder(null, "Consulta de Ingreso", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 18), Color.blue));
			panelActual.add(getT_peso1(), null);
			panelActual.add(lbl_8, null);
			panelActual.add(lbl_9, null);
			panelActual.add(getT_talla1(), null);
			panelActual.add(lbl_10, null);
			panelActual.add(lbl_11, null);
			panelActual.add(getScroll_5(), null);
			panelActual.add(getScroll_6(), null);
			panelActual.add(lbl_12, null);
			panelActual.add(getScroll_7(), null);
			panelActual.add(lbl_2, null);
			panelActual.add(getT_edad1(), null);
			panelActual.add(getBtn_crearReceta(), null);
			panelActual.add(getBtn_guardar1(), null);
			panelActual.add(getBtn_guardar21(), null);
			panelActual.add(lbl_13, null);
			panelActual.add(getT_saturacion(), null);
			panelActual.add(lbl_15, null);
			panelActual.add(getT_indice(), null);
			panelActual.add(getBtn_examenes1(), null);
			panelActual.add(jLabel3, null);
			panelActual.add(getT_fc(), null);
			panelActual.add(jLabel11, null);
			panelActual.add(jLabel21, null);
			panelActual.add(getT_temperatura(), null);
			panelActual.add(jLabel31, null);
			panelActual.add(getT_frecuenciaRespiratoria(), null);
			panelActual.add(jLabel111, null);
			panelActual.add(jLabel4, null);
			panelActual.add(getT_tas(), null);
			panelActual.add(jLabel5, null);
			panelActual.add(getT_tad(), null);
			panelActual.add(jLabel32, null);
			panelActual.add(getT_pf(), null);
		}
		return panelActual;
	}

	/**
	 * This method initializes t_peso1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_peso1() {
		if (t_peso1 == null) {
			t_peso1 = new JTextField();
			t_peso1.setLocation(new Point(140, 60));
			t_peso1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_peso1.setText("");
			t_peso1.setSize(new Dimension(50, 20));
		}
		return t_peso1;
	}

	/**
	 * This method initializes t_talla1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_talla1() {
		if (t_talla1 == null) {
			t_talla1 = new JTextField();
			t_talla1.setLocation(new Point(315, 60));
			t_talla1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_talla1.setSize(new Dimension(50, 20));
		}
		return t_talla1;
	}

	/**
	 * This method initializes scroll_5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll_5() {
		if (scroll_5 == null) {
			scroll_5 = new JScrollPane();
			scroll_5.setLocation(new Point(130, 161));
			scroll_5.setViewportView(getT_motivoActual());
			scroll_5.setSize(new Dimension(436, 70));
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
			t_motivoActual.setSize(new Dimension(430, 70));
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
			scroll_6.setLocation(new Point(130, 237));
			scroll_6.setViewportView(getT_diagnosticoActual());
			scroll_6.setSize(new Dimension(436, 70));
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
			t_diagnosticoActual.setSize(new Dimension(430, 70));
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
			scroll_7.setLocation(new Point(130, 313));
			scroll_7.setViewportView(getT_observaciones1());
			scroll_7.setSize(new Dimension(436, 70));
		}
		return scroll_7;
	}

	/**
	 * This method initializes t_observaciones1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_observaciones1() {
		if (t_observaciones1 == null) {
			t_observaciones1 = new JTextArea();
			t_observaciones1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_observaciones1.setLineWrap(true);
		}
		return t_observaciones1;
	}

	/**
	 * This method initializes t_edad1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_edad1() {
		if (t_edad1 == null) {
			t_edad1 = new JTextField();
			t_edad1.setLocation(new Point(140, 29));
			t_edad1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_edad1.setEditable(false);
			t_edad1.setSize(new Dimension(197, 20));
		}
		return t_edad1;
	}

	/**
	 * This method initializes btn_crearReceta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_crearReceta() {
		if (btn_crearReceta == null) {
			btn_crearReceta = new JButton();
			btn_crearReceta.setLocation(new Point(459, 418));
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
	 * This method initializes btn_examenes1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_examenes1() {
		if (btn_examenes1 == null) {
			btn_examenes1 = new JButton();
			btn_examenes1.setLocation(new Point(377, 418));
			btn_examenes1.setBackground(Color.white);
			btn_examenes1.setToolTipText("Ver historia");
			btn_examenes1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image019.png")));
			btn_examenes1.setSize(new Dimension(65, 65));
		}
		return btn_examenes1;
	}

	/**
	 * This method initializes t_fc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_fc() {
		if (t_fc == null) {
			t_fc = new JTextField();
			t_fc.setLocation(new Point(452, 60));
			t_fc.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_fc.setText("");
			t_fc.setSize(new Dimension(50, 20));
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
			t_frecuenciaRespiratoria.setText("");
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
			t_tad.setLocation(new Point(452, 120));
			t_tad.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_tad.setSize(new Dimension(50, 20));
		}
		return t_tad;
	}

	/**
	 * This method initializes btn_crearReceta1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_crearReceta1() {
		if (btn_crearReceta1 == null) {
			btn_crearReceta1 = new JButton();
			btn_crearReceta1.setToolTipText("Crear receta");
			btn_crearReceta1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image028.png")));
			btn_crearReceta1.setSize(new Dimension(62, 60));
			btn_crearReceta1.setLocation(new Point(430, 422));
			btn_crearReceta1.setEnabled(true);
			btn_crearReceta1.setBackground(Color.white);
			btn_crearReceta1.setVisible(false);
			btn_crearReceta1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor1.receta2();
				}
			});
		}
		return btn_crearReceta1;
	}

	/**
	 * This method initializes t_pf	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_pf() {
		if (t_pf == null) {
			t_pf = new JTextField();
			t_pf.setBounds(new Rectangle(441, 30, 60, 19));
		}
		return t_pf;
	}
	
}
