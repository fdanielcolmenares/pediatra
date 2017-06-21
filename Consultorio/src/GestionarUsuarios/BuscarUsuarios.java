package GestionarUsuarios;


import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class BuscarUsuarios {
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
	private JRadioButton chk_id = null;
	private JRadioButton chk_nombre = null;
	private Autenticacion autenticacion;  //  @jve:decl-index=0:
	private JDesktopPane panel;
	private JButton b_nuevo = null;
	
	public BuscarUsuarios(JDesktopPane p) {
		panel = p;
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setAutenticacion(Autenticacion a){
		autenticacion = a;
	}

	public void nuevo(){
		EditarUsuario v = new EditarUsuario(panel.getWidth(), panel.getHeight());
        v.setAutenticacion(autenticacion);
        v.setNuevo(true);
        panel.add(v.getVentana());
        v.setVisible(true);
        if(chk_cerrar.isSelected()){
        	ventana.dispose();
        }
	}
	
	public void abrir(){
		int index = tabla.getSelectedRow();
		if(index>=0){
			String cedula = tabla.getValueAt(index, 0).toString();
			String usuario = tabla.getValueAt(index, 2).toString();
			EditarUsuario v = new EditarUsuario(panel.getWidth(), panel.getHeight());
	        v.setAutenticacion(autenticacion);
	        v.setNuevo(false);
	        v.cargarUsuario(cedula, usuario);
	        panel.add(v.getVentana());
	        v.setVisible(true);
			
			if(chk_cerrar.isSelected()){
				ventana.dispose();
			}
		}
	}
	
	public void cerrar(){
		ventana.dispose();
	}
	
	public void buscar(){
		String sql;
		String busca = t_buscar.getText();
		busca = busca.toLowerCase();
		sql = "SELECT cedula, nombre, usuario, valido FROM usuarios WHERE ";
		if(t_buscar.getText().length()>0){
			if(chk_id.isSelected()){
				sql = sql + "cedula = "+busca+" ";
			}
			else{
				sql = sql + "(LOWER(nombre) LIKE '%"+busca.toLowerCase()+"%' OR LOWER(usuario) LIKE '%"+busca.toLowerCase()+"%') ";
			}
		}
		else{
			sql = sql +"1=1 ";
		}
		//sql = sql + "AND mdco_cedula='"+autenticacion.getCedulaMedico()+"' ";
		sql = sql + "ORDER BY cedula";
		
		ejecutaSQL(sql);
	}
	
	public void ejecutaSQL(String sql){
		//System.out.println(sql);
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{
				int cont = 0;
				while(res!=null && res.next()){
					String id = res.getString("cedula").toString();
					String nombre = res.getString("nombre").toString();
					String usuario = res.getString("usuario").toString();
					String valido = res.getString("valido").toString();
					if(valido.compareToIgnoreCase("s")==0){
						valido = "Activo";
					}
					else{
						valido = "Inactivo";
					}
					modelo.setRowCount(cont+1);
					tabla.setValueAt(id, cont, 0);
					tabla.setValueAt(nombre, cont, 1);
					tabla.setValueAt(usuario, cont, 2);
					tabla.setValueAt(valido, cont, 3);
					cont++;
				}
				tabla.getColumnModel().getColumn(0).setPreferredWidth(20*tabla.getWidth()/100);
		        tabla.getColumnModel().getColumn(1).setPreferredWidth(40*tabla.getWidth()/100);
		        tabla.getColumnModel().getColumn(2).setPreferredWidth(20*tabla.getWidth()/100);
		        tabla.getColumnModel().getColumn(3).setPreferredWidth(20*tabla.getWidth()/100);
		        if(cont == 0){
		        	JOptionPane.showMessageDialog(ventana, "No se encontraron resultados", "Búsqueda Completa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		        }
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Buscar Usuarios: Error");
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
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image041.png")));
			ventana.setClosable(true);
			ventana.setTitle("Buscar Usuarios");
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
			jContentPane.add(getB_nuevo(), null);
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
			t_buscar.setLocation(new Point(53, 27));
			t_buscar.setSize(new Dimension(200, 20));
			t_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						buscar();
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
			b_buscar.setMnemonic(KeyEvent.VK_B);
			b_buscar.setLocation(new Point(265, 23));
			b_buscar.setSize(new Dimension(107, 28));
			b_buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			b_buscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscar();
					/*if(t_buscar.getText().length()>0){
						buscar();
					}
					else{
						JOptionPane.showMessageDialog(ventana, "Debe ingresar un valor a buscar", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					}*/
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
			p_busqueda = new JPanel();
			p_busqueda.setLayout(null);
			p_busqueda.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Datos a buscar", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_busqueda.setLocation(new Point(20, 15));
			p_busqueda.setSize(new Dimension(420, 93));
			p_busqueda.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_busqueda.add(getT_buscar(), null);
			p_busqueda.add(getB_buscar(), null);
			p_busqueda.add(getChk_id(), null);
			p_busqueda.add(getChk_nombre(), null);
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
			p_resultados.setLocation(new Point(20, 121));
			p_resultados.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Resultados", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_resultados.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_resultados.setSize(new Dimension(420, 215));
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
			b_cerrar.setLocation(new Point(318, 347));
			b_cerrar.setSize(new Dimension(120, 30));
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
			b_abrir.setLocation(new Point(169, 347));
			b_abrir.setFont(new Font("Dialog", Font.BOLD, 12));
			b_abrir.setText("Editar");
			b_abrir.setMnemonic(KeyEvent.VK_E);
			b_abrir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image012.png")));
			b_abrir.setSize(new Dimension(120, 30));
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
					new String [] {"Cédula", "Nombre", "Usuario", "Estado"});
			modelo.setRowCount(0);
			tabla.setModel(modelo);
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						abrir();
					}
				}
			});
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount() == 2){
						abrir();
					}
				}
			});
			tabla.getTableHeader().setReorderingAllowed(false);			
		}
		tabla.getColumnModel().getColumn(0).setPreferredWidth(20*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(20*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(20*tabla.getWidth()/100);
		return tabla;
	}

	/**
	 * This method initializes chk_id	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_id() {
		if (chk_id == null) {
			chk_id = new JRadioButton();
			chk_id.setBounds(new Rectangle(49, 55, 86, 21));
			chk_id.setSelected(true);
			chk_id.setText("Cédula");
			chk_id.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_nombre.setSelected(false);
					chk_id.setSelected(true);
				}
			});
		}
		return chk_id;
	}

	/**
	 * This method initializes chk_nombre	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_nombre() {
		if (chk_nombre == null) {
			chk_nombre = new JRadioButton();
			chk_nombre.setSize(new Dimension(160, 21));
			chk_nombre.setText("Nombres o Apellidos");
			chk_nombre.setLocation(new Point(156, 55));
			chk_nombre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_id.setSelected(false);
					chk_nombre.setSelected(true);
				}
			});
		}
		return chk_nombre;
	}

	/**
	 * This method initializes b_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_nuevo() {
		if (b_nuevo == null) {
			b_nuevo = new JButton();
			b_nuevo.setLocation(new Point(20, 347));
			b_nuevo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image052.png")));
			b_nuevo.setText("Nuevo");
			b_nuevo.setMnemonic(KeyEvent.VK_N);
			b_nuevo.setFont(new Font("Dialog", Font.BOLD, 12));
			b_nuevo.setSize(new Dimension(120, 30));
			b_nuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nuevo();
				}
			});
		}
		return b_nuevo;
	}


}

