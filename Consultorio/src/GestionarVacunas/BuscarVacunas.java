package GestionarVacunas;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JLabel;

public class BuscarVacunas {
	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="89,35"
	private JPanel jContentPane = null;
	private JTextField t_buscar = null;
	private JButton b_buscar = null;
	private JPanel p_busqueda = null;
	private JPanel p_resultados = null;
	private JButton b_cerrar = null;
	private JCheckBox chk_cerrar = null;
	private JButton b_guardar = null;
	private Modelo_Tabla modelo;
	private JScrollPane s_tabla = null;
	private JTable tabla = null;	
	private Autenticacion autenticacion;  //  @jve:decl-index=0:
	private JDesktopPane panel;
	private JButton b_nuevo = null;
	private JTextField t_nombre = null;
	private JScrollPane jScrollPane = null;
	private JTextArea t_descripcion = null;
	public final int ERROR = 0;
	public final int MENSAJE = 1;
	private String id;  //  @jve:decl-index=0:
	private JLabel lbl_nom = null;
	private JLabel lbl_descripcion = null; 
	private List listaCodigos;
	
	public BuscarVacunas(JDesktopPane p) {
		panel = p;
		listaCodigos = new ArrayList();
		getVentana();
		int x = Math.max(0, (panel.getWidth() - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	public void setAutenticacion(Autenticacion a){
		autenticacion = a;
	}

	public void nuevo(){
		id = "-1";
		t_nombre.setEnabled(true);
		t_nombre.setText("");
		t_descripcion.setEnabled(true);
		t_descripcion.setText("");
		b_guardar.setEnabled(true);
	}
	
	public void mostrarSeleccionado(int index){
		id = listaCodigos.get(index).toString();
		t_nombre.setText(tabla.getValueAt(index, 0).toString());
		t_descripcion.setText(tabla.getValueAt(index, 1).toString());
		t_nombre.setEnabled(true);
		t_descripcion.setEnabled(true);
		b_guardar.setEnabled(true);
	}
	
	public void guardar(){
		if(t_nombre.getText().length()>0){
			if(JOptionPane.showConfirmDialog(ventana, "Seguro que desea guardar la vacuna?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
				int n = 0;
				Entidades.vacunas vac = new Entidades.vacunas(autenticacion);
				if(id.compareToIgnoreCase("-1")==0){
					vac.buscarNuevoID();
					n = 1;
				}
				else{
					vac.setId(Integer.parseInt(id));
					n = 0;
				}
				vac.setNombre(t_nombre.getText());
				vac.setDescripcion(t_descripcion.getText());
				String ret = "";
				if(n == 1){
					ret = vac.guardar();
				}
				else{
					ret = vac.actualizar();
				}
				
				if(ret.compareToIgnoreCase("Ok")==0){
					mostrarMensaje("Operación exitosa", MENSAJE);
					buscar();
					t_nombre.setText("");
					t_descripcion.setText("");
					t_nombre.setEnabled(false);
					t_descripcion.setEnabled(false);
					b_guardar.setEnabled(false);
				}
				else{
					mostrarMensaje(ret, ERROR);
				}
			}
		}
		else{
			mostrarMensaje("Debe llenar el nombre", ERROR);
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
	
	public void cerrar(){
		ventana.dispose();
	}
	
	public void buscar(){
		listaCodigos.clear();
		String sql;
		String busca = t_buscar.getText();
		busca = busca.toLowerCase();
		sql = "SELECT LPAD(id,5,0) as id, nombre, descripcion FROM vacunas WHERE ";
		if(t_buscar.getText().length()>0){
				sql = sql + "LOWER(nombre) LIKE '%"+busca.toLowerCase()+"%' ";
		}
		else{
			sql = sql +"1=1 ";
		}
		//sql = sql + "AND mdco_cedula='"+autenticacion.getCedulaMedico()+"' ";
		sql = sql + "ORDER BY nombre asc";
		
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
					String id = res.getString("id").toString();
					String nombre = res.getString("nombre").toString();
					String desc = res.getString("descripcion").toString();
					modelo.setRowCount(cont+1);
					//tabla.setValueAt(id, cont, 0);
					listaCodigos.add(id);
					tabla.setValueAt(nombre, cont, 0);
					tabla.setValueAt(desc, cont, 1);
					cont++;
				}
				tabla.getTableHeader().setReorderingAllowed(false);
				tabla.getColumnModel().getColumn(0).setPreferredWidth( (int)(40*tabla.getWidth()/100));
				tabla.getColumnModel().getColumn(1).setPreferredWidth( (int)(60*tabla.getWidth()/100));
		        if(cont == 0){
		        	JOptionPane.showMessageDialog(ventana, "No se encontraron resultados", "Búsqueda Completa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
		        }
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Buscar vacuna: Error");
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
			ventana.setSize(new Dimension(470, 422));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image066.png")));
			ventana.setClosable(true);
			ventana.setTitle("Buscar Vacunas");
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
			lbl_descripcion = new JLabel();
			lbl_descripcion.setText("Descripción:");
			lbl_descripcion.setSize(new Dimension(105, 20));
			lbl_descripcion.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_descripcion.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_descripcion.setLocation(new Point(0, 263));
			lbl_nom = new JLabel();
			lbl_nom.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_nom.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_nom.setLocation(new Point(13, 236));
			lbl_nom.setSize(new Dimension(91, 20));
			lbl_nom.setText("Nombre:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getP_busqueda(), null);
			jContentPane.add(getP_resultados(), null);
			jContentPane.add(getB_cerrar(), null);
			jContentPane.add(getChk_cerrar(), null);
			jContentPane.add(getB_guardar(), null);
			jContentPane.add(getB_nuevo(), null);
			jContentPane.add(getT_nombre(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(lbl_nom, null);
			jContentPane.add(lbl_descripcion, null);
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
			b_buscar.setBounds(new Rectangle(265, 25, 107, 25));
			b_buscar.setMnemonic(KeyEvent.VK_B);
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
			p_busqueda.setSize(new Dimension(420, 60));
			p_busqueda.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_busqueda.add(getT_buscar(), null);
			p_busqueda.add(getB_buscar(), null);
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
			p_resultados.setLocation(new Point(19, 78));
			p_resultados.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Resultados", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_resultados.setFont(new Font("Dialog", Font.PLAIN, 14));
			p_resultados.setSize(new Dimension(420, 149));
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
			b_cerrar.setLocation(new Point(318, 335));
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
			chk_cerrar.setLocation(new Point(22, 369));
			chk_cerrar.setSize(new Dimension(171, 20));
			chk_cerrar.setText("Cerrar automaticamente");
			chk_cerrar.setVisible(false);
			chk_cerrar.setVisible(false);
		}
		return chk_cerrar;
	}

	/**
	 * This method initializes b_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_guardar() {
		if (b_guardar == null) {
			b_guardar = new JButton();
			b_guardar.setLocation(new Point(20, 335));
			b_guardar.setFont(new Font("Dialog", Font.BOLD, 12));
			b_guardar.setText("Guardar");
			b_guardar.setMnemonic(KeyEvent.VK_G);
			b_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			b_guardar.setEnabled(false);
			b_guardar.setSize(new Dimension(120, 30));
			b_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					guardar();
				}
			});
		}
		return b_guardar;
	}

	/**
	 * This method initializes s_tabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getS_tabla() {
		if (s_tabla == null) {
			s_tabla = new JScrollPane();
			s_tabla.setBounds(new Rectangle(14, 20, 392, 122));
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
					new String [] {"Nombre", "Descripción"});
			modelo.setRowCount(0);
			tabla.setModel(modelo);
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == 10) {
						guardar();
					}
				}
			});
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					int index = tabla.getSelectedRow();
					if(index>=0){
						mostrarSeleccionado(index);
					}
				}
			});
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.getColumnModel().getColumn(0).setPreferredWidth( (int)(40*tabla.getWidth()/100));
	        tabla.getColumnModel().getColumn(1).setPreferredWidth( (int)(60*tabla.getWidth()/100));
		}
		return tabla;
	}

	/**
	 * This method initializes b_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_nuevo() {
		if (b_nuevo == null) {
			b_nuevo = new JButton();
			b_nuevo.setLocation(new Point(169, 335));
			b_nuevo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image065.png")));
			b_nuevo.setText("Nueva");
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

	/**
	 * This method initializes t_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_nombre() {
		if (t_nombre == null) {
			t_nombre = new JTextField();
			t_nombre.setEnabled(false);
			t_nombre.setLocation(new Point(115, 236));
			t_nombre.setSize(new Dimension(202, 20));
		}
		return t_nombre;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(312, 52));
			jScrollPane.setLocation(new Point(115, 263));
			jScrollPane.setViewportView(getT_descripcion());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes t_descripcion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getT_descripcion() {
		if (t_descripcion == null) {
			t_descripcion = new JTextArea();
			t_descripcion.setEnabled(false);
		}
		return t_descripcion;
	}


}

