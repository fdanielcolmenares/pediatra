package GestionarMedicamentos;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Utilitario.Modelo_Tabla;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.lang.String;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class EditarMedicamentos {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="87,16"
	private JTabbedPane pestanas = null;
	private JPanel panelPresentaciones = null;
	private JPanel panelMedicamentos = null;
	private JPanel panelDosis = null;
	private JScrollPane jScrollPane = null;
	public Modelo_Tabla modeloPresentaciones = null;
	private JTable tablaPresentaciones = null;
	private JTextField t_presentacion = null;
	private JButton btn_guardar1 = null;
	private JButton btn_cerrar1 = null;
	private JPanel panelMedicamentos1 = null;
	private JScrollPane scrollMedicamentos = null;
	private JTable tablaMedicamentos = null;
	private JLabel lbl_abc = null;
	private JLabel lbl_def = null;
	private JLabel lbl_ghi = null;
	private JLabel lbl_jkl = null;
	private JLabel lbl_mno = null;
	private JLabel lbl_pqrs = null;
	private JLabel lbl_tuv = null;
	private JLabel lbl_wxyz = null;
	private JLabel lbl_todos = null;
	private JButton btn_nuevo1 = null;
	private JLabel lbl_nomPresentacion = null;
	private JPanel panelPresentMed = null;
	private JButton btn_guardar2 = null;
	private JScrollPane jScrollPane1 = null;
	public Modelo_Tabla modeloMedicamentos = null;
	public Modelo_Tabla modeloPresMed = null;
	private JTable tablaPresMed = null;
	private JComboBox listaPresentaciones = null;
	private JLabel lbl_21 = null;
	private JButton btn_guardar3 = null;
	private JLabel lbl_22 = null;
	private JTextField t_medicamento = null;
	private JButton btn_nuevo2 = null;
	private JButton btn_dosis = null;
	public JDesktopPane panel;
	private GestorEditarMedicamentos gestor;  //  @jve:decl-index=0:
	public int ERROR = 1;
	public int MENSAJE = 2;
	private JLabel lbl_23 = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea t_descripcion = null;
	private JLabel lbl_valido = null;
	private JRadioButton chk_si = null;
	private JRadioButton chk_no = null;
	private JScrollPane jScrollPane3 = null;
	private JPanel panelListaDosis = null;
	public JLabel lbl_31 = null;
	
	public EditarMedicamentos(JDesktopPane p){
		panel = p;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight()-70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorEditarMedicamentos g){
		gestor = g;
	}
	
	public void mostrarMensaje(String msj, int tipo){
		if(tipo == ERROR){
			JOptionPane.showMessageDialog(ventana, msj, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
		if(tipo == MENSAJE){
			JOptionPane.showMessageDialog(ventana, msj, "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		}
	}
	
	public void cambiarSize(int index){
		//Original 623,432
		if(index == 0){
			ventana.setSize(425, 295);
		}
		if(index == 1){
			ventana.setSize(623, 432);
			if(pestanas.getComponentCount()>2){
				try{
					pestanas.removeTabAt(2);
				}
				catch(Exception ex){
				}
			}
		}
		if(index == 2){
			ventana.setSize(346, 420);
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
			ventana.setSize(new Dimension(623, 432));
			ventana.setClosable(true);
			ventana.setTitle("Medicamentos");
			ventana.setContentPane(getPestanas());
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
		}
		return ventana;
	}

	/**
	 * This method initializes pestanas	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getPestanas() {
		if (pestanas == null) {
			pestanas = new JTabbedPane();
			pestanas.addTab("Presentaciones", null, getPanelPresentaciones(), null);
			pestanas.addTab("Medicamentos", null, getPanelMedicamentos(), null);
			//pestanas.addTab("Dosis", null, getPanelDosis(), null);
			getPanelDosis();
			pestanas.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					int index = pestanas.getSelectedIndex();
					cambiarSize(index);
				}
			});
		}
		return pestanas;
	}

	/**
	 * This method initializes panelPresentaciones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelPresentaciones() {
		if (panelPresentaciones == null) {
			lbl_nomPresentacion = new JLabel();
			lbl_nomPresentacion.setText("Tipo de presentación:");
			lbl_nomPresentacion.setSize(new Dimension(132, 20));
			lbl_nomPresentacion.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_nomPresentacion.setLocation(new Point(10, 160));
			panelPresentaciones = new JPanel();
			panelPresentaciones.setLayout(null);
			panelPresentaciones.add(getJScrollPane(), null);
			panelPresentaciones.add(getT_presentacion(), null);
			panelPresentaciones.add(getBtn_guardar1(), null);
			panelPresentaciones.add(getBtn_cerrar1(), null);
			panelPresentaciones.add(getBtn_nuevo1(), null);
			panelPresentaciones.add(lbl_nomPresentacion, null);
		}
		return panelPresentaciones;
	}

	/**
	 * This method initializes panelMedicamentos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelMedicamentos() {
		if (panelMedicamentos == null) {
			lbl_valido = new JLabel();
			lbl_valido.setText("Válido:");
			lbl_valido.setSize(new Dimension(93, 20));
			lbl_valido.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_valido.setLocation(new Point(255, 295));
			lbl_23 = new JLabel();
			lbl_23.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_23.setLocation(new Point(255, 240));
			lbl_23.setSize(new Dimension(93, 20));
			lbl_23.setText("Descripción:");
			lbl_22 = new JLabel();
			lbl_22.setText("Medicamento:");
			lbl_22.setSize(new Dimension(93, 20));
			lbl_22.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_22.setLocation(new Point(255, 210));
			panelMedicamentos = new JPanel();
			panelMedicamentos.setLayout(null);
			panelMedicamentos.add(getPanelMedicamentos1(), null);
			panelMedicamentos.add(getPanelPresentMed(), null);
			panelMedicamentos.add(getBtn_guardar2(), null);
			panelMedicamentos.add(lbl_22, null);
			panelMedicamentos.add(getT_medicamento(), null);
			panelMedicamentos.add(getBtn_nuevo2(), null);
			panelMedicamentos.add(lbl_23, null);
			panelMedicamentos.add(getJScrollPane2(), null);
			panelMedicamentos.add(lbl_valido, null);
			panelMedicamentos.add(getChk_si(), null);
			panelMedicamentos.add(getChk_no(), null);
		}
		return panelMedicamentos;
	}

	/**
	 * This method initializes panelDosis	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDosis() {
		if (panelDosis == null) {
			lbl_31 = new JLabel();
			lbl_31.setText("Medicamento:");
			lbl_31.setLocation(new Point(20, 1));
			lbl_31.setSize(new Dimension(291, 20));
			panelDosis = new JPanel();
			panelDosis.setLayout(null);
			panelDosis.add(getJScrollPane3(), null);
			panelDosis.setVisible(true);
			panelDosis.add(lbl_31, null);
		}
		return panelDosis;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(204, 131));
			jScrollPane.setLocation(new Point(96, 16));
			jScrollPane.setViewportView(getTablaPresentaciones());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tablaPresentaciones	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaPresentaciones() {
		if (tablaPresentaciones == null) {
			tablaPresentaciones = new JTable();
			modeloPresentaciones = new Modelo_Tabla(null,
					new String [] {"Presentaciones actuales"});
			modeloPresentaciones.setRowCount(0);
			tablaPresentaciones.setModel(modeloPresentaciones);
			tablaPresentaciones.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int index = tablaPresentaciones.getSelectedRow();
					if(index>=0){
						gestor.seleccionaPresentacion(index);
					}
				}
			});
			tablaPresentaciones.getTableHeader().setReorderingAllowed(false);
		}
		return tablaPresentaciones;
	}

	/**
	 * This method initializes t_presentacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_presentacion() {
		if (t_presentacion == null) {
			t_presentacion = new JTextField();
			t_presentacion.setSize(new Dimension(201, 20));
			t_presentacion.setEditable(false);
			t_presentacion.setLocation(new Point(147, 160));
		}
		return t_presentacion;
	}

	/**
	 * This method initializes btn_guardar1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar1() {
		if (btn_guardar1 == null) {
			btn_guardar1 = new JButton();
			btn_guardar1.setText("Guardar");
			btn_guardar1.setSize(new Dimension(115, 30));
			btn_guardar1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar1.setMnemonic(KeyEvent.VK_G);
			btn_guardar1.setLocation(new Point(150, 195));
			btn_guardar1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_presentacion.getText().length()>0){
						if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea guardar la presentación?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
							gestor.guardarPresentacion();
						}
					}
					else{
						mostrarMensaje("Debe ingresar el tipo de presentación", ERROR);
					}
				}
			});
		}
		return btn_guardar1;
	}

	/**
	 * This method initializes btn_cerrar1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar1() {
		if (btn_cerrar1 == null) {
			btn_cerrar1 = new JButton();
			btn_cerrar1.setText("Cerrar");
			btn_cerrar1.setSize(new Dimension(115, 30));
			btn_cerrar1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cerrar1.setMnemonic(KeyEvent.VK_C);
			btn_cerrar1.setLocation(new Point(285, 195));
			btn_cerrar1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.cerrar();
				}
			});
		}
		return btn_cerrar1;
	}

	/**
	 * This method initializes panelMedicamentos1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelMedicamentos1() {
		if (panelMedicamentos1 == null) {
			lbl_todos = new JLabel();
			lbl_todos.setLocation(new Point(165, 315));
			lbl_todos.setFont(new Font("Dialog", Font.BOLD, 24));
			lbl_todos.setForeground(Color.blue);
			lbl_todos.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_todos.setText("*");
			lbl_todos.setSize(new Dimension(19, 20));
			lbl_todos.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "1=1";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_wxyz = new JLabel();
			lbl_wxyz.setLocation(new Point(115, 315));
			lbl_wxyz.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_wxyz.setForeground(Color.blue);
			lbl_wxyz.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_wxyz.setText("WXYZ");
			lbl_wxyz.setSize(new Dimension(48, 20));
			lbl_wxyz.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'w%' OR LOWER(nombre) LIKE 'x%' OR LOWER(nombre) LIKE 'y%' OR LOWER(nombre) LIKE 'z%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_tuv = new JLabel();
			lbl_tuv.setLocation(new Point(75, 315));
			lbl_tuv.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_tuv.setForeground(Color.blue);
			lbl_tuv.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_tuv.setText("TUV");
			lbl_tuv.setSize(new Dimension(40, 20));
			lbl_tuv.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 't%' OR LOWER(nombre) LIKE 'u%' OR LOWER(nombre) LIKE 'v%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_pqrs = new JLabel();
			lbl_pqrs.setLocation(new Point(35, 315));
			lbl_pqrs.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_pqrs.setForeground(Color.blue);
			lbl_pqrs.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_pqrs.setText("PQRS");
			lbl_pqrs.setSize(new Dimension(40, 20));
			lbl_pqrs.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'p%' OR LOWER(nombre) LIKE 'q%' OR LOWER(nombre) LIKE 'r%' OR LOWER(nombre) LIKE 's%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_mno = new JLabel();
			lbl_mno.setLocation(new Point(167, 292));
			lbl_mno.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_mno.setForeground(Color.blue);
			lbl_mno.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_mno.setText("MNÑO");
			lbl_mno.setSize(new Dimension(44, 20));
			lbl_mno.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'm%' OR LOWER(nombre) LIKE 'n%' OR LOWER(nombre) LIKE 'ñ%' OR LOWER(nombre) LIKE 'o%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_jkl = new JLabel();
			lbl_jkl.setLocation(new Point(127, 292));
			lbl_jkl.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_jkl.setForeground(Color.blue);
			lbl_jkl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_jkl.setText("JKL");
			lbl_jkl.setSize(new Dimension(40, 20));
			lbl_jkl.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'j%' OR LOWER(nombre) LIKE 'k%' OR LOWER(nombre) LIKE 'l%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_ghi = new JLabel();
			lbl_ghi.setLocation(new Point(87, 292));
			lbl_ghi.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_ghi.setForeground(Color.blue);
			lbl_ghi.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ghi.setText("GHI");
			lbl_ghi.setSize(new Dimension(40, 20));
			lbl_ghi.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'g%' OR LOWER(nombre) LIKE 'h%' OR LOWER(nombre) LIKE 'i%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_def = new JLabel();
			lbl_def.setLocation(new Point(47, 292));
			lbl_def.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_def.setForeground(Color.blue);
			lbl_def.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_def.setText("DEF");
			lbl_def.setSize(new Dimension(40, 20));
			lbl_def.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'd%' OR LOWER(nombre) LIKE 'e%' OR LOWER(nombre) LIKE 'f%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			lbl_abc = new JLabel();
			lbl_abc.setLocation(new Point(7, 292));
			lbl_abc.setBackground(Color.blue);
			lbl_abc.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_abc.setForeground(Color.blue);
			lbl_abc.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_abc.setText("ABC");
			lbl_abc.setSize(new Dimension(40, 20));
			lbl_abc.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'a%' OR LOWER(nombre) LIKE 'b%' OR LOWER(nombre) LIKE 'c%')";
					gestor.buscarMedicamentos(sql);
				}
			});
			panelMedicamentos1 = new JPanel();
			panelMedicamentos1.setLayout(null);
			panelMedicamentos1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Medicamentos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelMedicamentos1.setSize(new Dimension(223, 350));
			panelMedicamentos1.setLocation(new Point(14, 10));
			panelMedicamentos1.add(getScrollMedicamentos(), null);
			panelMedicamentos1.add(lbl_abc, null);
			panelMedicamentos1.add(lbl_def, null);
			panelMedicamentos1.add(lbl_ghi, null);
			panelMedicamentos1.add(lbl_jkl, null);
			panelMedicamentos1.add(lbl_mno, null);
			panelMedicamentos1.add(lbl_pqrs, null);
			panelMedicamentos1.add(lbl_tuv, null);
			panelMedicamentos1.add(lbl_wxyz, null);
			panelMedicamentos1.add(lbl_todos, null);
		}
		return panelMedicamentos1;
	}

	/**
	 * This method initializes scrollMedicamentos	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollMedicamentos() {
		if (scrollMedicamentos == null) {
			scrollMedicamentos = new JScrollPane();
			scrollMedicamentos.setLocation(new Point(3, 20));
			scrollMedicamentos.setViewportView(getTablaMedicamentos());
			scrollMedicamentos.setSize(new Dimension(207, 259));
		}
		return scrollMedicamentos;
	}

	/**
	 * This method initializes tablaMedicamentos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaMedicamentos() {
		if (tablaMedicamentos == null) {
			tablaMedicamentos = new JTable();
			modeloMedicamentos = new Modelo_Tabla(null, new String[] {"Medicamento"});
			modeloMedicamentos.setRowCount(0);			
			tablaMedicamentos.setModel(modeloMedicamentos);
			tablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int index = tablaMedicamentos.getSelectedRow();
					if(index>=0){
						gestor.seleccionaMedicamento(index);
						if(pestanas.getComponentCount()>2){
							try{
								pestanas.removeTabAt(2);
							}
							catch(Exception ex){
							}
						}
					}
				}
			});
			tablaMedicamentos.getTableHeader().setReorderingAllowed(false);
		}
		return tablaMedicamentos;
	}

	/**
	 * This method initializes btn_nuevo1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_nuevo1() {
		if (btn_nuevo1 == null) {
			btn_nuevo1 = new JButton();
			btn_nuevo1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			btn_nuevo1.setSize(new Dimension(115, 30));
			btn_nuevo1.setText("Nueva");
			btn_nuevo1.setMnemonic(KeyEvent.VK_N);
			btn_nuevo1.setLocation(new Point(13, 195));
			btn_nuevo1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.nuevaPresentacion();
				}
			});
		}
		return btn_nuevo1;
	}

	/**
	 * This method initializes panelPresentMed	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelPresentMed() {
		if (panelPresentMed == null) {
			lbl_21 = new JLabel();
			lbl_21.setText("Agregar:");
			lbl_21.setSize(new Dimension(58, 20));
			lbl_21.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_21.setLocation(new Point(42, 120));
			panelPresentMed = new JPanel();
			panelPresentMed.setLayout(null);
			panelPresentMed.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Presentaciones del medicamento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelPresentMed.setSize(new Dimension(304, 190));
			panelPresentMed.setLocation(new Point(275, 10));
			panelPresentMed.add(getJScrollPane1(), null);
			panelPresentMed.add(getListaPresentaciones(), null);
			panelPresentMed.add(lbl_21, null);
			panelPresentMed.add(getBtn_guardar3(), null);
			panelPresentMed.add(getBtn_dosis(), null);
		}
		return panelPresentMed;
	}

	/**
	 * This method initializes btn_guardar2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar2() {
		if (btn_guardar2 == null) {
			btn_guardar2 = new JButton();
			btn_guardar2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar2.setSize(new Dimension(110, 30));
			btn_guardar2.setText("Guardar");
			btn_guardar2.setMnemonic(KeyEvent.VK_G);
			btn_guardar2.setLocation(new Point(450, 325));
			btn_guardar2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_medicamento.getText().length()>0){
						if(JOptionPane.showConfirmDialog(ventana, "Seguro que desea guardar el medicamento?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
							gestor.guardarMedicamento();
						}
					}
					else{
						mostrarMensaje("Debe ingresar el nombre del medicamento", ERROR);
					}
				}
			});
		}
		return btn_guardar2;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setSize(new Dimension(259, 92));
			jScrollPane1.setLocation(new Point(24, 20));
			jScrollPane1.setViewportView(getTablaPresMed());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tablaPresMed	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaPresMed() {
		if (tablaPresMed == null) {
			tablaPresMed = new JTable();
			modeloPresMed = new Modelo_Tabla(null, new String[] {"Presentaciones"});
			modeloPresMed.setRowCount(0);
			tablaPresMed.setModel(modeloPresMed);
			tablaPresMed.getTableHeader().setReorderingAllowed(false);
		}
		return tablaPresMed;
	}

	/**
	 * This method initializes listaPresentaciones	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getListaPresentaciones() {
		if (listaPresentaciones == null) {
			listaPresentaciones = new JComboBox();
			listaPresentaciones.setLocation(new Point(105, 120));
			listaPresentaciones.setSize(new Dimension(151, 20));
		}
		return listaPresentaciones;
	}

	/**
	 * This method initializes btn_guardar3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar3() {
		if (btn_guardar3 == null) {
			btn_guardar3 = new JButton();
			btn_guardar3.setLocation(new Point(168, 150));
			btn_guardar3.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar3.setText("Agregar");
			btn_guardar3.setMnemonic(KeyEvent.VK_A);
			btn_guardar3.setSize(new Dimension(115, 30));
			btn_guardar3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(gestor.getIdMedicamento() != -1){
						if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea agregar la presentación?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
							gestor.agregarPresentacionMedicamento();
						}
					}
					else{
						mostrarMensaje("Debe seleccionar un medicamento primero", ERROR);
					}
				}
			});
		}
		return btn_guardar3;
	}

	/**
	 * This method initializes t_medicamento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_medicamento() {
		if (t_medicamento == null) {
			t_medicamento = new JTextField();
			t_medicamento.setSize(new Dimension(224, 20));
			t_medicamento.setEditable(false);
			t_medicamento.setLocation(new Point(355, 210));
		}
		return t_medicamento;
	}

	/**
	 * This method initializes btn_nuevo2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_nuevo2() {
		if (btn_nuevo2 == null) {
			btn_nuevo2 = new JButton();
			btn_nuevo2.setText("Nuevo");
			btn_nuevo2.setSize(new Dimension(110, 30));
			btn_nuevo2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image074.png")));
			btn_nuevo2.setMnemonic(KeyEvent.VK_N);
			btn_nuevo2.setLocation(new Point(285, 325));
			btn_nuevo2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.nuevoMedicamento();
				}
			});
		}
		return btn_nuevo2;
	}

	/**
	 * This method initializes btn_dosis	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_dosis() {
		if (btn_dosis == null) {
			btn_dosis = new JButton();
			btn_dosis.setText("Dosis");
			btn_dosis.setSize(new Dimension(115, 30));
			btn_dosis.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			btn_dosis.setMnemonic(KeyEvent.VK_D);
			btn_dosis.setLocation(new Point(27, 150));
			btn_dosis.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaPresMed.getSelectedRow();
					if(index>=0){
						gestor.verDosis(index);
						if(pestanas.getComponentCount()==2){
							pestanas.addTab("Dosis", null, getPanelDosis(), null);
						}
						pestanas.setSelectedIndex(2);
					}
					else{
						mostrarMensaje("Debe seleccionar una presentación primero", ERROR);
					}
				}
			});
		}
		return btn_dosis;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setEnabled(false);
			jScrollPane2.setSize(new Dimension(224, 47));
			jScrollPane2.setLocation(new Point(355, 241));
			jScrollPane2.setViewportView(getT_descripcion());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes t_descripcion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getT_descripcion() {
		if (t_descripcion == null) {
			t_descripcion = new JTextArea();
			t_descripcion.setEditable(false);
			t_descripcion.setLineWrap(true);
		}
		return t_descripcion;
	}

	/**
	 * This method initializes chk_si	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_si() {
		if (chk_si == null) {
			chk_si = new JRadioButton();
			chk_si.setText("Si");
			chk_si.setLocation(new Point(356, 295));
			chk_si.setEnabled(false);
			chk_si.setSelected(true);
			chk_si.setSize(new Dimension(50, 20));
			chk_si.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_si.setSelected(true);
					chk_no.setSelected(false);
				}
			});
		}
		return chk_si;
	}

	/**
	 * This method initializes chk_no	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChk_no() {
		if (chk_no == null) {
			chk_no = new JRadioButton();
			chk_no.setSize(new Dimension(50, 20));
			chk_no.setText("No");
			chk_no.setEnabled(false);
			chk_no.setLocation(new Point(414, 295));
			chk_no.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_si.setSelected(false);
					chk_no.setSelected(true);
				}
			});
		}
		return chk_no;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setLocation(new Point(20, 22));
			jScrollPane3.setBackground(Color.white);
			jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane3.setViewportView(getPanelListaDosis());
			jScrollPane3.setSize(new Dimension(290, 337));
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes panelListaDosis	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanelListaDosis() {
		if (panelListaDosis == null) {
			panelListaDosis = new JPanel();
			panelListaDosis.setLayout(null);
		}
		return panelListaDosis;
	}

}
