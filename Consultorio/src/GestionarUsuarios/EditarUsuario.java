package GestionarUsuarios;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import ConexionBD.Conexion;
import Entidades.usuarios;
import Utilitario.Autenticacion;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class EditarUsuario {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="209,28"
	private JPanel jContentPane = null;
	private JLabel lbl_1 = null;
	private JLabel lbl_3 = null;
	private JLabel lbl_4 = null;
	private JLabel lbl_5 = null;
	private JLabel lbl_6 = null;
	private JTextField t_nombre = null;
	private JTextField t_usuario = null;
	private JPasswordField t_clave1 = null;
	private JPasswordField t_clave2 = null;
	private JRadioButton chk_activo = null;
	private JRadioButton chk_desactivo = null;
	private JButton b_guardar = null;
	private JButton b_cerrar = null;
	private JLabel lbl_0 = null;
	private JTextField t_cedula = null;
	private JLabel lbl_7 = null;
	private JComboBox t_tipo = null;
	private JLabel lbl_titulo = null;
	private JLabel lbl_8 = null;
	private JComboBox t_doctor = null;
	private Autenticacion autenticacion;  //  @jve:decl-index=0:
	private boolean nuevo;
	private String doctores[];
	
	public EditarUsuario(int w, int h){
		getVentana();
		int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));        
	}
	
	public void cargarDoctores(){
		Conexion con = new Conexion(autenticacion);
		String sql;
		sql = "SELECT cedula, nombre FROM medicos ORDER BY nombre";
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			String doc = "";
			try{
				while(res.next()){
					String ced, nom;
					nom = res.getString("nombre");
					ced = res.getString("cedula");
					t_doctor.addItem(nom);
					doc = doc + ced+"/";
				}
				doc=doc.substring(0, doc.length()-1);
				doctores = doc.split("/");
			}
			catch(Exception e){}
		}
	}
	
	public void setNuevo(boolean aFlag){
		nuevo = aFlag;
		t_cedula.setEditable(aFlag);
		t_usuario.setEditable(aFlag);
	}
	
	public void cargarUsuario(String ced, String usu){
		usuarios us = new usuarios(autenticacion);
		String retorna = us.buscarUsuario(ced, usu);
		if(retorna.compareToIgnoreCase("Ok")==0){
			t_cedula.setText(us.getCedula());
			t_nombre.setText(us.getNombre());
			t_usuario.setText(us.getUsuario());
			if(us.getValido().compareToIgnoreCase("s")==0){
				chk_activo.setSelected(true);
				chk_desactivo.setSelected(false);
			}
			else{
				chk_activo.setSelected(false);
				chk_desactivo.setSelected(true);
			}
			t_tipo.setSelectedIndex(us.getRol_id()-1);
			for(int i=0; i<doctores.length;i++){
				if(doctores[i].compareToIgnoreCase(us.getMdco_cedula())==0){
					t_doctor.setSelectedIndex(i);
					break;
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(ventana, retorna, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
		}
	}
	
	public void guardar(){
		if(nuevo){
			if(t_cedula.getText().isEmpty() || t_nombre.getText().isEmpty()
					||t_usuario.getText().isEmpty() || String.valueOf(t_clave1.getPassword()).isEmpty()
					||String.valueOf(t_clave2.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(ventana, "Debe llenar toda la información", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
			else{
				if(String.valueOf(t_clave2.getPassword()).compareToIgnoreCase(String.valueOf(t_clave1.getPassword()))==0){					
					usuarios us = new usuarios(autenticacion);
					if(us.validarUsuario(t_usuario.getText())){
						us.setCedula(t_cedula.getText());
						us.setNombre(t_nombre.getText());
						us.setUsuario(t_usuario.getText());
						us.setClave(String.valueOf(t_clave1.getPassword()));
						if(chk_activo.isSelected()){
							us.setValido("s");
						}
						else{
							us.setValido("n");
						}
						us.setRol_id(t_tipo.getSelectedIndex()+1);
						us.setMdco_cedula(doctores[t_doctor.getSelectedIndex()]);
						String retorna = us.guardar();
						if(retorna.compareToIgnoreCase("Ok")==0){
							JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
						}
						else{
							JOptionPane.showMessageDialog(ventana, retorna, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
						}
					}
					else{
						JOptionPane.showMessageDialog(ventana, "El nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					}
				}
				else{
					JOptionPane.showMessageDialog(ventana, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
				}
			}			
		}
		else{
			if(t_cedula.getText().isEmpty() || t_nombre.getText().isEmpty()
					||t_usuario.getText().isEmpty()){
				JOptionPane.showMessageDialog(ventana, "Debe llenar toda la información", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
			else{
				if(String.valueOf(t_clave2.getPassword()).compareToIgnoreCase(String.valueOf(t_clave1.getPassword()))==0){					
					usuarios us = new usuarios(autenticacion);
					us.buscarUsuario(t_cedula.getText(), t_usuario.getText());
					us.setCedula(t_cedula.getText());
					us.setNombre(t_nombre.getText());
					us.setUsuario(t_usuario.getText());		
					if(String.valueOf(t_clave2.getPassword()).length()>0){
						us.setClave(String.valueOf(t_clave1.getPassword()));
					}
					if(chk_activo.isSelected()){
						us.setValido("s");
					}
					else{
						us.setValido("n");
					}
					us.setRol_id(t_tipo.getSelectedIndex()+1);
					us.setMdco_cedula(doctores[t_doctor.getSelectedIndex()]);
					String retorna = us.actualizar();
					if(retorna.compareToIgnoreCase("Ok")==0){
						JOptionPane.showMessageDialog(ventana, "Actualización exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
					}
					else{
						JOptionPane.showMessageDialog(ventana, retorna, "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					}					
				}
				else{
					JOptionPane.showMessageDialog(ventana, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
				}
			}	
		}
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(aFlag);
	}
	
	public void cerrar(){
		ventana.dispose();
	}
	
	public void setAutenticacion(Autenticacion a){
		autenticacion = a;
		cargarDoctores();
	}
	
	/**
	 * This method initializes jInternalFrame	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(460, 390));
			ventana.setTitle("Editar Usuarios");
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image012.png")));
			ventana.setClosable(true);
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
			lbl_8 = new JLabel();
			lbl_8.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_8.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_8.setLocation(new Point(72, 279));
			lbl_8.setSize(new Dimension(102, 20));
			lbl_8.setText("Doctor:");
			lbl_titulo = new JLabel();
			lbl_titulo.setFont(new Font("Dialog", Font.BOLD, 36));
			lbl_titulo.setLocation(new Point(77, 11));
			lbl_titulo.setSize(new Dimension(301, 30));
			lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_titulo.setForeground(Color.blue);
			lbl_titulo.setText("Editar Usuario");
			lbl_7 = new JLabel();
			lbl_7.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_7.setLocation(new Point(0, 240));
			lbl_7.setSize(new Dimension(174, 20));
			lbl_7.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_7.setText("Tipo de Usuario:");
			lbl_0 = new JLabel();
			lbl_0.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_0.setLocation(new Point(70, 60));
			lbl_0.setSize(new Dimension(102, 20));
			lbl_0.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_0.setText("Cédula:");
			lbl_6 = new JLabel();
			lbl_6.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_6.setLocation(new Point(72, 210));
			lbl_6.setSize(new Dimension(102, 20));
			lbl_6.setText("Estado:");
			lbl_5 = new JLabel();
			lbl_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_5.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_5.setLocation(new Point(0, 180));
			lbl_5.setSize(new Dimension(174, 20));
			lbl_5.setText("Repetir Contraseña:");
			lbl_4 = new JLabel();
			lbl_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_4.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_4.setLocation(new Point(70, 150));
			lbl_4.setSize(new Dimension(103, 20));
			lbl_4.setText("Contraseña:");
			lbl_3 = new JLabel();
			lbl_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_3.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_3.setLocation(new Point(70, 120));
			lbl_3.setSize(new Dimension(102, 20));
			lbl_3.setText("Usuario:");
			lbl_1 = new JLabel();
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_1.setFont(new Font("Dialog", Font.PLAIN, 18));
			lbl_1.setLocation(new Point(70, 90));
			lbl_1.setSize(new Dimension(102, 20));
			lbl_1.setText("Nombre:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbl_1, null);
			jContentPane.add(lbl_3, null);
			jContentPane.add(lbl_4, null);
			jContentPane.add(lbl_5, null);
			jContentPane.add(lbl_6, null);
			jContentPane.add(getT_nombre(), null);
			jContentPane.add(getT_usuario(), null);
			jContentPane.add(getT_clave1(), null);
			jContentPane.add(getT_clave2(), null);
			jContentPane.add(getChk_activo(), null);
			jContentPane.add(getChk_desactivo(), null);
			jContentPane.add(getB_guardar(), null);
			jContentPane.add(getB_cerrar(), null);
			jContentPane.add(lbl_0, null);
			jContentPane.add(getT_cedula(), null);
			jContentPane.add(lbl_7, null);
			jContentPane.add(getT_tipo(), null);
			jContentPane.add(lbl_titulo, null);
			jContentPane.add(lbl_8, null);
			jContentPane.add(getT_doctor(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes t_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_nombre() {
		if (t_nombre == null) {
			t_nombre = new JTextField();
			t_nombre.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_nombre.setSize(new Dimension(220, 20));
			t_nombre.setLocation(new Point(185, 90));
		}
		return t_nombre;
	}

	/**
	 * This method initializes t_usuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_usuario() {
		if (t_usuario == null) {
			t_usuario = new JTextField();
			t_usuario.setLocation(new Point(185, 120));
			t_usuario.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_usuario.setSize(new Dimension(220, 20));
		}
		return t_usuario;
	}

	/**
	 * This method initializes t_clave1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getT_clave1() {
		if (t_clave1 == null) {
			t_clave1 = new JPasswordField();
			t_clave1.setLocation(new Point(185, 150));
			t_clave1.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_clave1.setSize(new Dimension(220, 20));
		}
		return t_clave1;
	}

	/**
	 * This method initializes t_clave2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getT_clave2() {
		if (t_clave2 == null) {
			t_clave2 = new JPasswordField();
			t_clave2.setLocation(new Point(185, 180));
			t_clave2.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_clave2.setSize(new Dimension(220, 20));
		}
		return t_clave2;
	}

	/**
	 * This method initializes chk_activo	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_activo() {
		if (chk_activo == null) {
			chk_activo = new JRadioButton();
			chk_activo.setText("Activo");
			chk_activo.setSize(new Dimension(83, 18));
			chk_activo.setLocation(new Point(188, 210));
			chk_activo.setFont(new Font("Dialog", Font.PLAIN, 18));
			chk_activo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_desactivo.setSelected(false);
					chk_activo.setSelected(true);
				}
			});
		}
		return chk_activo;
	}

	/**
	 * This method initializes chk_desactivo	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_desactivo() {
		if (chk_desactivo == null) {
			chk_desactivo = new JRadioButton();
			chk_desactivo.setText("Inactivo");
			chk_desactivo.setLocation(new Point(295, 210));
			chk_desactivo.setFont(new Font("Dialog", Font.PLAIN, 18));
			chk_desactivo.setSelected(true);
			chk_desactivo.setSize(new Dimension(109, 21));
			chk_desactivo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_activo.setSelected(false);
					chk_desactivo.setSelected(true);
				}
			});
		}
		return chk_desactivo;
	}

	/**
	 * This method initializes b_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_guardar() {
		if (b_guardar == null) {
			b_guardar = new JButton();
			b_guardar.setLocation(new Point(80, 316));
			b_guardar.setText("Guardar");
			b_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			b_guardar.setMnemonic(KeyEvent.VK_G);
			b_guardar.setFont(new Font("Dialog", Font.BOLD, 12));
			b_guardar.setSize(new Dimension(110, 30));
			b_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Guardar?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
						guardar();
					}
				}
			});
		}
		return b_guardar;
	}

	/**
	 * This method initializes b_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_cerrar() {
		if (b_cerrar == null) {
			b_cerrar = new JButton();
			b_cerrar.setLocation(new Point(240, 316));
			b_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			b_cerrar.setText("Cerrar");
			b_cerrar.setMnemonic(KeyEvent.VK_C);
			b_cerrar.setFont(new Font("Dialog", Font.BOLD, 12));
			b_cerrar.setSize(new Dimension(110, 30));
			b_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrar();
				}
			});
		}
		return b_cerrar;
	}

	/**
	 * This method initializes t_cedula	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_cedula() {
		if (t_cedula == null) {
			t_cedula = new JTextField();
			t_cedula.setLocation(new Point(185, 60));
			t_cedula.setSize(new Dimension(220, 20));
		}
		return t_cedula;
	}

	/**
	 * This method initializes t_tipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getT_tipo() {
		if (t_tipo == null) {
			t_tipo = new JComboBox();
			t_tipo.setLocation(new Point(185, 240));
			t_tipo.setEditable(false);
			t_tipo.setFont(new Font("Dialog", Font.BOLD, 14));
			t_tipo.setSize(new Dimension(160, 20));
			t_tipo.addItem("Nivel1");
			t_tipo.addItem("Nivel2");
			t_tipo.addItem("Nivel3");
			t_tipo.setSelectedIndex(2);
		}
		return t_tipo;
	}

	/**
	 * This method initializes t_doctor	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getT_doctor() {
		if (t_doctor == null) {
			t_doctor = new JComboBox();
			t_doctor.setLocation(new Point(185, 280));
			t_doctor.setFont(new Font("Dialog", Font.BOLD, 14));
			t_doctor.setSize(new Dimension(160, 20));
		}
		return t_doctor;
	}

}
