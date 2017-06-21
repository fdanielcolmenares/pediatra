package GestionarRecetas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Utilitario.Modelo_Tabla;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MostrarMedicamentos {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="133,83"
	private JPanel jContentPane = null;
	private JPanel panelMedicamentos = null;
	private JScrollPane scrollMedicamentos = null;
	private JTable tablaMedicamentos = null;
	private Modelo_Tabla modeloMedicamentos;
	private JPanel panelDosis = null;
	private JLabel lbl_abc = null;
	private JLabel lbl_def = null;
	private JLabel lbl_ghi = null;
	private JLabel lbl_jkl = null;
	private JLabel lbl_mno = null;
	private JLabel lbl_pqrs = null;
	private JLabel lbl_tuv = null;
	private JLabel lbl_wxyz = null;
	private JButton btn_cerrar = null;
	private JLabel lbl_todos = null;
	
	public final int WARNING = 1;
	public final int ERROR = 2;
	public final int MENSAJE = 3;	
	private GestorMostrarMedicamentos gestor;  //  @jve:decl-index=0:
	private JScrollPane jScrollPane = null;
	private JPanel panelListaDosis = null;
	
	public MostrarMedicamentos(int w, int h){
		getVentana();
		int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setGestor(GestorMostrarMedicamentos g){
		gestor = g;
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(true);
	}
	
	public void seleccionaMedicamento(int index){
		String medicamento = tablaMedicamentos.getValueAt(index, 0).toString();
		String presentacion = tablaMedicamentos.getValueAt(index,1).toString();
		gestor.mostrarDosis(medicamento, presentacion, index);
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
			ventana.setSize(new Dimension(662, 414));
			ventana.setClosable(false);
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image060.png")));
			ventana.setTitle("Agregar medicamentos");
			ventana.setContentPane(getJContentPane());
                        ventana.setClosable(true);
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
			jContentPane.add(getPanelMedicamentos(), null);
			jContentPane.add(getPanelDosis(), null);
			jContentPane.add(getBtn_cerrar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelMedicamentos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelMedicamentos() {
		if (panelMedicamentos == null) {
			lbl_todos = new JLabel();
			lbl_todos.setText("*");
			lbl_todos.setSize(new Dimension(19, 20));
			lbl_todos.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_todos.setFont(new Font("Dialog", Font.BOLD, 24));
			lbl_todos.setForeground(Color.blue);
			lbl_todos.setLocation(new Point(165, 305));
			lbl_todos.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "1=1";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_wxyz = new JLabel();
			lbl_wxyz.setText("WXYZ");
			lbl_wxyz.setSize(new Dimension(48, 20));
			lbl_wxyz.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_wxyz.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_wxyz.setForeground(Color.blue);
			lbl_wxyz.setLocation(new Point(115, 305));
			lbl_wxyz.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'w%' OR LOWER(nombre) LIKE 'x%' OR LOWER(nombre) LIKE 'y%' OR LOWER(nombre) LIKE 'z%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_tuv = new JLabel();
			lbl_tuv.setText("TUV");
			lbl_tuv.setSize(new Dimension(40, 20));
			lbl_tuv.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_tuv.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_tuv.setForeground(Color.blue);
			lbl_tuv.setLocation(new Point(75, 305));
			lbl_tuv.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'w%' OR LOWER(nombre) LIKE 'x%' OR LOWER(nombre) LIKE 'y%' OR LOWER(nombre) LIKE 'z%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_pqrs = new JLabel();
			lbl_pqrs.setText("PQRS");
			lbl_pqrs.setSize(new Dimension(40, 20));
			lbl_pqrs.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_pqrs.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_pqrs.setForeground(Color.blue);
			lbl_pqrs.setLocation(new Point(35, 305));
			lbl_pqrs.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'p%' OR LOWER(nombre) LIKE 'q%' OR LOWER(nombre) LIKE 'r%' OR LOWER(nombre) LIKE 's%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_mno = new JLabel();
			lbl_mno.setText("MNÑO");
			lbl_mno.setSize(new Dimension(44, 20));
			lbl_mno.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_mno.setForeground(Color.blue);
			lbl_mno.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_mno.setLocation(new Point(167, 282));
			lbl_mno.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'm%' OR LOWER(nombre) LIKE 'n%' OR LOWER(nombre) LIKE 'ñ%' OR LOWER(nombre) LIKE 'o%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_jkl = new JLabel();
			lbl_jkl.setText("JKL");
			lbl_jkl.setSize(new Dimension(40, 20));
			lbl_jkl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_jkl.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_jkl.setForeground(Color.blue);
			lbl_jkl.setLocation(new Point(127, 282));
			lbl_jkl.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'j%' OR LOWER(nombre) LIKE 'k%' OR LOWER(nombre) LIKE 'l%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_ghi = new JLabel();
			lbl_ghi.setText("GHI");
			lbl_ghi.setSize(new Dimension(40, 20));
			lbl_ghi.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ghi.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_ghi.setForeground(Color.blue);
			lbl_ghi.setLocation(new Point(87, 282));
			lbl_ghi.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'g%' OR LOWER(nombre) LIKE 'h%' OR LOWER(nombre) LIKE 'i%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_def = new JLabel();
			lbl_def.setText("DEF");
			lbl_def.setSize(new Dimension(40, 20));
			lbl_def.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_def.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_def.setForeground(Color.blue);
			lbl_def.setLocation(new Point(47, 282));
			lbl_def.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'd%' OR LOWER(nombre) LIKE 'e%' OR LOWER(nombre) LIKE 'f%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			lbl_abc = new JLabel();
			lbl_abc.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_abc.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_abc.setBackground(Color.blue);
			lbl_abc.setLocation(new Point(7, 282));
			lbl_abc.setSize(new Dimension(40, 20));
			lbl_abc.setForeground(Color.blue);
			lbl_abc.setText("ABC");
			lbl_abc.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					String sql = "(LOWER(nombre) LIKE 'a%' OR LOWER(nombre) LIKE 'b%' OR LOWER(nombre) LIKE 'c%')";
					gestor.mostrarMedicamentos(sql);
				}
			});
			panelMedicamentos = new JPanel();
			panelMedicamentos.setLayout(null);
			panelMedicamentos.setBounds(new Rectangle(13, 20, 219, 339));
			panelMedicamentos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Medicamentos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelMedicamentos.add(getScrollMedicamentos(), null);
			panelMedicamentos.add(lbl_abc, null);
			panelMedicamentos.add(lbl_def, null);
			panelMedicamentos.add(lbl_ghi, null);
			panelMedicamentos.add(lbl_jkl, null);
			panelMedicamentos.add(lbl_mno, null);
			panelMedicamentos.add(lbl_pqrs, null);
			panelMedicamentos.add(lbl_tuv, null);
			panelMedicamentos.add(lbl_wxyz, null);
			panelMedicamentos.add(lbl_todos, null);
		}
		return panelMedicamentos;
	}

	/**
	 * This method initializes scrollMedicamentos	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollMedicamentos() {
		if (scrollMedicamentos == null) {
			scrollMedicamentos = new JScrollPane();
			scrollMedicamentos.setSize(new Dimension(207, 259));
			scrollMedicamentos.setLocation(new Point(3, 20));
			scrollMedicamentos.setViewportView(getTablaMedicamentos());
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
			modeloMedicamentos = new Modelo_Tabla(null,
					new String [] {"Medicamento", "Presentación"});
			modeloMedicamentos.setRowCount(0);
			tablaMedicamentos.setModel(modeloMedicamentos);
			tablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					int index = tablaMedicamentos.getSelectedRow();
					if(index>=0){
						seleccionaMedicamento(index);
					}
				}
			});
			tablaMedicamentos.getTableHeader().setReorderingAllowed(false);
			tablaMedicamentos.getColumnModel().getColumn(0).setPreferredWidth( (int)(60*tablaMedicamentos.getWidth()/100));
	        tablaMedicamentos.getColumnModel().getColumn(1).setPreferredWidth(40*tablaMedicamentos.getWidth()/100);
			
		}
		return tablaMedicamentos;
	}
	
	public Modelo_Tabla getModeloMedicamentos(){
		return modeloMedicamentos;
	}

	/**
	 * This method initializes panelDosis	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDosis() {
		if (panelDosis == null) {
			panelDosis = new JPanel();
			panelDosis.setLayout(null);
			panelDosis.setLocation(new Point(250, 20));
			panelDosis.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Dosis", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			panelDosis.setSize(new Dimension(330, 339));
			panelDosis.add(getJScrollPane(), null);
		}
		return panelDosis;
	}

	/**
	 * This method initializes btn_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar() {
		if (btn_cerrar == null) {
			btn_cerrar = new JButton();
			btn_cerrar.setLocation(new Point(586, 295));
			btn_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image022.png")));
			btn_cerrar.setToolTipText("Cerrar");
			btn_cerrar.setSize(new Dimension(60, 60));
			btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.setVisible(false);
					//ventana.dispose();
				}
			});
		}
		return btn_cerrar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	/*private JScrollPane getJScrollPane(){
		if(jScrollPane == null){
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(600, 600);
			 getPanelListaDosis();
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		     GroupLayout jPanelLayout = new GroupLayout(getPanelListaDosis());
		     jScrollPane.setLayout(jPanelLayout);
		     jPanelLayout.setHorizontalGroup(
		         jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 600, Short.MAX_VALUE)
		     );
		     jPanelLayout.setVerticalGroup(
		         jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		         .addGap(0, 1010, Short.MAX_VALUE)
		     );
		     jScrollPane.setViewportView(getPanelListaDosis());
		}
		return jScrollPane;
	}*/
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setLocation(new Point(22, 20));
			jScrollPane.setPreferredSize(new Dimension(280, 288));
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setViewportView(getPanelListaDosis());
			jScrollPane.setSize(new Dimension(291, 288));
		}
		return jScrollPane;
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
			panelListaDosis.setSize(new Dimension(280, 260));
			panelListaDosis.setPreferredSize(new Dimension(280, 260));
			panelListaDosis.setBackground(Color.white);
		}
		return panelListaDosis;
	}

}
