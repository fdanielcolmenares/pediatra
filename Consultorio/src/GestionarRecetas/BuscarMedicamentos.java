package GestionarRecetas;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class BuscarMedicamentos {
	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="89,35"
	private JPanel jContentPane = null;
	private JTextField t_buscar = null;
	private JButton b_buscar = null;
	private JPanel p_busqueda = null;
	private JPanel p_resultados = null;
	private JButton b_cerrar = null;
	private JCheckBox chk_cerrar = null;
	private JButton b_abrir = null;
	private Modelo_Tabla modelo;
	private JScrollPane s_tabla = null;
	private JTable tabla = null;	
	private Autenticacion autenticacion;  //  @jve:decl-index=0:
	private JLabel lbl_a = null;
	private JLabel lbl_b = null;
	private JLabel lbl_c = null;
	private JLabel lbl_d = null;
	private JLabel lbl_e = null;
	private JLabel lbl_f = null;
	private JLabel lbl_g = null;
	private JLabel lbl_h = null;
	private JLabel lbl_i = null;
	private JLabel lbl_j = null;
	private JLabel lbl_k = null;
	private JLabel lbl_m = null;
	private JLabel lbl_l = null;
	private JLabel lbl_n = null;
	private JLabel lbl_o = null;
	private JLabel lbl_nh = null;
	private JLabel lbl_p = null;
	private JLabel lbl_q = null;
	private JLabel lbl_r = null;
	private JLabel lbl_s = null;
	private JLabel lbl_t = null;
	private JLabel lbl_u = null;
	private JLabel lbl_v = null;
	private JLabel lbl_w = null;
	private JLabel lbl_x = null;
	private JLabel lbl_y = null;
	private JLabel lbl_z = null;
	private JDesktopPane panel;
	private JLabel jLabel = null;
	private List listamedi;
	
	public BuscarMedicamentos(JDesktopPane p) {
		panel = p;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
        listamedi = new ArrayList();
	}
	
	public void setAutenticacion(Autenticacion a){
		autenticacion = a;
	}

	public void abrir(){
		int index = tabla.getSelectedRow();
		if(index>=0){			
			AgregarMedicamento medica = new AgregarMedicamento(panel.getWidth(), panel.getHeight(),false);
	    	GestorAgregarMedicamento ges = new GestorAgregarMedicamento(autenticacion, medica);
	    	//ges.cargarPresenta();
	    	ges.cargar(listamedi.get(index).toString());
	    	medica.setGestor(ges);
	    	panel.add(medica.getVentanaNewMedica());
	    	medica.getVentanaNewMedica().setVisible(true);
		}
	}
	
	public void cerrar(){
		ventana.dispose();
	}
	
	public void buscar(){
		String sql;
		String busca = t_buscar.getText();
		busca = busca.toLowerCase();
		sql = "SELECT id, nombre FROM medicinas WHERE LOWER(nombre) LIKE '%"+busca.toLowerCase()+"%'";		
		ejecutaSQL(sql);
	}
	
	public void buscarPorLetra(String letra){
		String sql = "SELECT id, nombre FROM medicinas "
						+"WHERE LOWER(nombre) LIKE '"+letra+"%' ";
		ejecutaSQL(sql);
	}
	
	public void ejecutaSQL(String sql){
		modelo.removeAllRows();
		listamedi.clear();
		//System.out.println(sql);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{
				int cont = 0;
				while(res!=null && res.next()){
					listamedi.add(res.getString("id").toString());
					//String id = String.valueOf(cont+1);
					String nombre = res.getString("nombre").toString();
					modelo.setRowCount(cont+1);
					//tabla.setValueAt(id, cont, 0);
					tabla.setValueAt(nombre, cont, 0);
					cont++;
				}
				tabla.getTableHeader().setReorderingAllowed(false);
				//tabla.getColumnModel().getColumn(0).setPreferredWidth( (int)(20*tabla.getWidth()/100));
		        //tabla.getColumnModel().getColumn(1).setPreferredWidth(80*tabla.getWidth()/100);
		        if(cont == 0){
		        	JOptionPane.showMessageDialog(ventana, "No se encontraron resultados", "Búsqueda Completa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		        }
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Buscar Historia: Error");
			}
		}
	}
	
	public void setVisible(boolean aFlag){
		try{
			ventana.setSelected(true);
		}
		catch(Exception e){			
		}
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
			ventana.setSize(new Dimension(470, 440));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			ventana.setClosable(true);
			ventana.setTitle("Buscar Medicamentos");
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
			jContentPane.add(getP_busqueda(), null);
			jContentPane.add(getP_resultados(), null);
			jContentPane.add(getB_cerrar(), null);
			jContentPane.add(getChk_cerrar(), null);
			jContentPane.add(getB_abrir(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes t_buscar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_buscar() {
		if (t_buscar == null) {
			t_buscar = new JTextField();
			t_buscar.setLocation(new Point(48, 48));
			t_buscar.setSize(new Dimension(200, 20));
			t_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						if(t_buscar.getText().length()>0){
							buscar();
						}
						else{
							JOptionPane.showMessageDialog(ventana, "Debe ingresar un valor a buscar", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
						}
                    }
				}
			});
		}
		return t_buscar;
	}

	/**
	 * This method initializes b_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_buscar() {
		if (b_buscar == null) {
			b_buscar = new JButton();
			b_buscar.setText("Buscar");
			b_buscar.setFont(new Font("Dialog", Font.BOLD, 14));
			b_buscar.setHorizontalAlignment(SwingConstants.CENTER);
			b_buscar.setBounds(new Rectangle(261, 47, 107, 25));
			b_buscar.setMnemonic(KeyEvent.VK_B);
			b_buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			b_buscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//if(t_buscar.getText().length()>0){
						buscar();
					//}
					//else{
					//	JOptionPane.showMessageDialog(ventana, "Debe ingresar un valor a buscar", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					//	t_buscar.requestFocus();
					//}
				}
			});
		}
		return b_buscar;
	}

	/**
	 * This method initializes p_busqueda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP_busqueda() {
		if (p_busqueda == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(49, 25, 162, 19));
			jLabel.setText("Nombre del Medicamento:");
			lbl_z = new JLabel();
			lbl_z.setText("Z");
			lbl_z.setSize(new Dimension(15, 20));
			lbl_z.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_z.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_z.setForeground(Color.blue);
			lbl_z.setLocation(new Point(398, 80));
			lbl_z.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("z");
				}
			});
			lbl_y = new JLabel();
			lbl_y.setText("Y");
			lbl_y.setSize(new Dimension(15, 20));
			lbl_y.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_y.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_y.setForeground(Color.blue);
			lbl_y.setLocation(new Point(383, 80));
			lbl_y.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("y");
				}
			});
			lbl_x = new JLabel();
			lbl_x.setText("X");
			lbl_x.setSize(new Dimension(15, 20));
			lbl_x.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_x.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_x.setForeground(Color.blue);
			lbl_x.setLocation(new Point(368, 80));
			lbl_x.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("x");
				}
			});
			lbl_w = new JLabel();
			lbl_w.setText("W");
			lbl_w.setSize(new Dimension(15, 20));
			lbl_w.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_w.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_w.setForeground(Color.blue);
			lbl_w.setLocation(new Point(353, 80));
			lbl_w.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("w");
				}
			});
			lbl_v = new JLabel();
			lbl_v.setText("V");
			lbl_v.setSize(new Dimension(15, 20));
			lbl_v.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_v.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_v.setForeground(Color.blue);
			lbl_v.setLocation(new Point(338, 80));
			lbl_v.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("v");
				}
			});
			lbl_u = new JLabel();
			lbl_u.setText("U");
			lbl_u.setSize(new Dimension(15, 20));
			lbl_u.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_u.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_u.setForeground(Color.blue);
			lbl_u.setLocation(new Point(323, 80));
			lbl_u.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("u");
				}
			});
			lbl_t = new JLabel();
			lbl_t.setText("T");
			lbl_t.setSize(new Dimension(15, 20));
			lbl_t.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_t.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_t.setForeground(Color.blue);
			lbl_t.setLocation(new Point(308, 80));
			lbl_t.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("t");
				}
			});
			lbl_s = new JLabel();
			lbl_s.setText("S");
			lbl_s.setSize(new Dimension(15, 20));
			lbl_s.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_s.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_s.setForeground(Color.blue);
			lbl_s.setLocation(new Point(293, 80));
			lbl_s.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("s");
				}
			});
			lbl_r = new JLabel();
			lbl_r.setText("R");
			lbl_r.setSize(new Dimension(15, 20));
			lbl_r.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_r.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_r.setForeground(Color.blue);
			lbl_r.setLocation(new Point(278, 80));
			lbl_r.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("r");
				}
			});
			lbl_q = new JLabel();
			lbl_q.setText("Q");
			lbl_q.setSize(new Dimension(15, 20));
			lbl_q.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_q.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_q.setForeground(Color.blue);
			lbl_q.setLocation(new Point(263, 80));
			lbl_q.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("q");
				}
			});
			lbl_p = new JLabel();
			lbl_p.setText("P");
			lbl_p.setSize(new Dimension(15, 20));
			lbl_p.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_p.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_p.setForeground(Color.blue);
			lbl_p.setLocation(new Point(248, 80));
			lbl_p.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("p");
				}
			});
			lbl_nh = new JLabel();
			lbl_nh.setText("Ñ");
			lbl_nh.setSize(new Dimension(15, 20));
			lbl_nh.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_nh.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_nh.setForeground(Color.blue);
			lbl_nh.setLocation(new Point(218, 80));
			lbl_nh.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("ñ");
				}
			});
			lbl_o = new JLabel();
			lbl_o.setText("O");
			lbl_o.setSize(new Dimension(15, 20));
			lbl_o.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_o.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_o.setForeground(Color.blue);
			lbl_o.setLocation(new Point(233, 80));
			lbl_o.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("o");
				}
			});
			lbl_n = new JLabel();
			lbl_n.setText("N");
			lbl_n.setSize(new Dimension(15, 20));
			lbl_n.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_n.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_n.setForeground(Color.blue);
			lbl_n.setLocation(new Point(203, 80));
			lbl_n.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("n");
				}
			});
			lbl_l = new JLabel();
			lbl_l.setText("L");
			lbl_l.setSize(new Dimension(15, 20));
			lbl_l.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_l.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_l.setForeground(Color.blue);
			lbl_l.setLocation(new Point(173, 80));
			lbl_l.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("l");
				}
			});
			lbl_m = new JLabel();
			lbl_m.setText("M");
			lbl_m.setSize(new Dimension(15, 20));
			lbl_m.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_m.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_m.setForeground(Color.blue);
			lbl_m.setLocation(new Point(188, 80));
			lbl_m.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("m");
				}
			});
			lbl_k = new JLabel();
			lbl_k.setText("K");
			lbl_k.setSize(new Dimension(15, 20));
			lbl_k.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_k.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_k.setForeground(Color.blue);
			lbl_k.setLocation(new Point(158, 80));
			lbl_k.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("k");
				}
			});
			lbl_j = new JLabel();
			lbl_j.setText("J");
			lbl_j.setSize(new Dimension(15, 20));
			lbl_j.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_j.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_j.setForeground(Color.blue);
			lbl_j.setLocation(new Point(143, 80));
			lbl_j.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("j");
				}
			});
			lbl_i = new JLabel();
			lbl_i.setText("I");
			lbl_i.setSize(new Dimension(15, 20));
			lbl_i.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_i.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_i.setForeground(Color.blue);
			lbl_i.setLocation(new Point(128, 80));
			lbl_i.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("i");
				}
			});
			lbl_h = new JLabel();
			lbl_h.setText("H");
			lbl_h.setLocation(new Point(113, 80));
			lbl_h.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_h.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_h.setForeground(Color.blue);
			lbl_h.setSize(new Dimension(15, 20));
			lbl_h.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("h");
				}
			});
			lbl_g = new JLabel();
			lbl_g.setText("G");
			lbl_g.setLocation(new Point(98, 80));
			lbl_g.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_g.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_g.setForeground(Color.blue);
			lbl_g.setSize(new Dimension(15, 20));
			lbl_g.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("g");
				}
			});
			lbl_f = new JLabel();
			lbl_f.setText("F");
			lbl_f.setLocation(new Point(83, 80));
			lbl_f.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_f.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_f.setForeground(Color.blue);
			lbl_f.setSize(new Dimension(15, 20));
			lbl_f.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("f");
				}
			});
			lbl_e = new JLabel();
			lbl_e.setText("E");
			lbl_e.setLocation(new Point(68, 80));
			lbl_e.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_e.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_e.setForeground(Color.blue);
			lbl_e.setSize(new Dimension(15, 20));
			lbl_e.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("e");
				}
			});
			lbl_d = new JLabel();
			lbl_d.setText("D");
			lbl_d.setSize(new Dimension(15, 20));
			lbl_d.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_d.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_d.setForeground(Color.blue);
			lbl_d.setLocation(new Point(53, 80));
			lbl_d.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("d");
				}
			});
			lbl_c = new JLabel();
			lbl_c.setText("C");
			lbl_c.setSize(new Dimension(15, 20));
			lbl_c.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_c.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_c.setForeground(Color.blue);
			lbl_c.setLocation(new Point(38, 80));
			lbl_c.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("c");
				}
			});
			lbl_b = new JLabel();
			lbl_b.setText("B");
			lbl_b.setSize(new Dimension(15, 20));
			lbl_b.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_b.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_b.setForeground(Color.blue);
			lbl_b.setLocation(new Point(23, 80));
			lbl_b.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("b");
				}
			});
			lbl_a = new JLabel();
			lbl_a.setText("A");
			lbl_a.setSize(new Dimension(15, 20));
			lbl_a.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_a.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_a.setForeground(Color.blue);
			lbl_a.setLocation(new Point(8, 80));
			lbl_a.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					buscarPorLetra("a");
				}
			});
			p_busqueda = new JPanel();
			p_busqueda.setLayout(null);
			p_busqueda.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Datos a buscar", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_busqueda.setLocation(new Point(20, 15));
			p_busqueda.setSize(new Dimension(420, 112));
			p_busqueda.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_busqueda.add(getT_buscar(), null);
			p_busqueda.add(getB_buscar(), null);
			p_busqueda.add(lbl_a, null);
			p_busqueda.add(lbl_b, null);
			p_busqueda.add(lbl_c, null);
			p_busqueda.add(lbl_d, null);
			p_busqueda.add(lbl_e, null);
			p_busqueda.add(lbl_f, null);
			p_busqueda.add(lbl_g, null);
			p_busqueda.add(lbl_h, null);
			p_busqueda.add(lbl_i, null);
			p_busqueda.add(lbl_j, null);
			p_busqueda.add(lbl_k, null);
			p_busqueda.add(lbl_m, null);
			p_busqueda.add(lbl_l, null);
			p_busqueda.add(lbl_n, null);
			p_busqueda.add(lbl_o, null);
			p_busqueda.add(lbl_nh, null);
			p_busqueda.add(lbl_p, null);
			p_busqueda.add(lbl_q, null);
			p_busqueda.add(lbl_r, null);
			p_busqueda.add(lbl_s, null);
			p_busqueda.add(lbl_t, null);
			p_busqueda.add(lbl_u, null);
			p_busqueda.add(lbl_v, null);
			p_busqueda.add(lbl_w, null);
			p_busqueda.add(lbl_x, null);
			p_busqueda.add(lbl_y, null);
			p_busqueda.add(lbl_z, null);
			p_busqueda.add(jLabel, null);
		}
		return p_busqueda;
	}

	/**
	 * This method initializes p_resultados	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP_resultados() {
		if (p_resultados == null) {
			p_resultados = new JPanel();
			p_resultados.setLayout(null);
			p_resultados.setLocation(new Point(19, 137));
			p_resultados.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Resultados", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_resultados.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_resultados.setSize(new Dimension(420, 201));
			p_resultados.add(getS_tabla(), null);
		}
		return p_resultados;
	}

	/**
	 * This method initializes b_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_cerrar() {
		if (b_cerrar == null) {
			b_cerrar = new JButton();
			b_cerrar.setText("Cerrar");
			b_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			b_cerrar.setLocation(new Point(262, 350));
			b_cerrar.setSize(new Dimension(110, 30));
			b_cerrar.setMnemonic(KeyEvent.VK_C);
			b_cerrar.setFont(new Font("Dialog", Font.BOLD, 12));
			b_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrar();
				}
			});
		}
		return b_cerrar;
	}

	/**
	 * This method initializes chk_cerrar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChk_cerrar() {
		if (chk_cerrar == null) {
			chk_cerrar = new JCheckBox();
			chk_cerrar.setSelected(true);
			chk_cerrar.setLocation(new Point(18, 382));
			chk_cerrar.setSize(new Dimension(171, 20));
			chk_cerrar.setText("Cerrar automaticamente");
		}
		return chk_cerrar;
	}

	/**
	 * This method initializes b_abrir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_abrir() {
		if (b_abrir == null) {
			b_abrir = new JButton();
			b_abrir.setLocation(new Point(110, 350));
			b_abrir.setFont(new Font("Dialog", Font.BOLD, 12));
			b_abrir.setText("Abrir");
			b_abrir.setMnemonic(KeyEvent.VK_A);
			b_abrir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image016.png")));
			b_abrir.setSize(new Dimension(110, 30));
			b_abrir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					abrir();
				}
			});
		}
		return b_abrir;
	}

	/**
	 * This method initializes s_tabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getS_tabla() {
		if (s_tabla == null) {
			s_tabla = new JScrollPane();
			s_tabla.setBounds(new Rectangle(14, 27, 392, 164));
			s_tabla.setViewportView(getTabla());
		}
		return s_tabla;
	}

	/**
	 * This method initializes tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			tabla.setAutoCreateColumnsFromModel(true);
			tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			modelo = new Modelo_Tabla(null,
					new String [] {"Medicamento"});
			modelo.setRowCount(0);
			tabla.setModel(modelo);
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						abrir();
					}
				}
			});
			tabla.getTableHeader().setReorderingAllowed(false);
			//tabla.getColumnModel().getColumn(0).setPreferredWidth( (int)(20*tabla.getWidth()/100));
	        //tabla.getColumnModel().getColumn(1).setPreferredWidth(80*tabla.getWidth()/100);
		}
		return tabla;
	}


}
