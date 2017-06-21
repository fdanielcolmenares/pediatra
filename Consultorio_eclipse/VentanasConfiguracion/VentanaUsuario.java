package VentanasConfiguracion;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.Encriptar;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

public class VentanaUsuario {

    private JDialog ventana = null;  //  @jve:decl-index=0:visual-constraint="134,29"

    private JPanel panelVentana = null;
    private JPanel panelUsuario = null;
    private JTextField t_usuario = null;
    private JPasswordField t_clave = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JButton b_aceptar = null;
    private JButton b_salir = null;
    private JFrame padre;
    private boolean valido;
    private Autenticacion autenticacion;    
    private String usro_cedula;
    private String mdco_cedula;
    private int rol;
    private String nombre;
    
    /**
     * This method initializes ventana	
     * 	
     * @return javax.swing.JDialog	
     */
    public VentanaUsuario(JFrame p, Autenticacion a) {
        padre = p;
        autenticacion = new Autenticacion();
        autenticacion.setIpServidor(a.getIpServidor());
        autenticacion.setPuerto(a.getPuerto());
        autenticacion.setUsuarioBD(a.getUsuarioBD());
        autenticacion.setClaveBD(a.getClaveBD());
        valido = false;
        getVentana();
    }

    public void setVisible(boolean aFlag) {
        ventana.setVisible(aFlag);
    }

    public String getUsuario() {
        return t_usuario.getText();
    }

    public String getClave() {
        return String.valueOf(t_clave.getPassword());
    }

    public void dispose() {
        ventana.dispose();
    }

    public boolean esValido() {
        return valido;
    }
        
    public void validar(){
    	try{
	    	Conexion con = new Conexion(autenticacion);
	    	if(con.conectar()){
	    		String query;
	    		int cont=0;
	    		query = "SELECT count(*) "
	    				+"FROM usuarios u, roles r, medicos m "
	    				+"WHERE r.id=u.rol_id "
	    				+"AND u.mdco_cedula=m.cedula "
	    				+"AND u.valido = 's' "
	    				+"AND u.usuario='"+t_usuario.getText()+"' AND u.clave='"+Encriptar.encryptMD5(String.valueOf(t_clave.getPassword()))+"'";
	    		
	    		ResultSet res = con.consultar(query);
	    		if(res.next()){
	    			cont = Integer.parseInt(res.getString(1).toString());	    			
	    		}
	    		con.desconectar();
	    		if(cont == 0){
	    			valido = false;
	    			JOptionPane.showMessageDialog(ventana, "Usuario o Clave incorrectos", "Mensaje",
                            JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
	    			t_clave.setText("");
	    		}
	    		else{
	    			if(cont == 1){
	    				unDoctor();
	    				JOptionPane.showMessageDialog(ventana, "Bienvenido(a): "+nombre, "Correcto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
	    				ventana.setVisible(false);
	    			}
	    			else{
	    				//mostrar dialogo
	    			}
	    		}
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(ventana, "No se pudo conectar con el servidor", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
	    	}
    	}
    	catch(Exception e){    		
    	}    	
    }
    
    public void unDoctor(){
    	String query = "SELECT u.cedula, m.cedula, m.nombre, r.nivel, u.nombre "
			+"FROM usuarios u, roles r, medicos m "
			+"WHERE r.id=u.rol_id "
			+"AND u.mdco_cedula=m.cedula "
			+"AND u.usuario='"+t_usuario.getText()+"' AND u.clave='"+Encriptar.encryptMD5(String.valueOf(t_clave.getPassword()))+"'";
    	Conexion con = new Conexion(autenticacion);
    	if(con.conectar()){
    		java.sql.ResultSet res = con.consultar(query);
    		try{
    			if(res.next()){
    				usro_cedula = res.getString(1).toString();
    				mdco_cedula = res.getString(2).toString();
    				rol = Integer.parseInt(res.getString(4).toString());
    				nombre = res.getString(5).toString();
    				valido = true;
    			}    			
    			con.desconectar();
    		}
    		catch(Exception e){    			
    		}
    	}    	
    }
    
    

    public String getMdco_cedula() {
		return mdco_cedula;
	}

	public void setMdco_cedula(String mdco_cedula) {
		this.mdco_cedula = mdco_cedula;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getUsro_cedula() {
		return usro_cedula;
	}

	public void setUsro_cedula(String usro_cedula) {
		this.usro_cedula = usro_cedula;
	}

    private JDialog getVentana() {
        if (ventana == null) {
            ventana = new JDialog();
            ventana.setAlwaysOnTop(true);
            ventana.setModal(true);
            ventana.setSize(new Dimension(290, 232));
            ventana.setTitle("Autenticación");
            ventana.setResizable(false);
            ventana.setMaximumSize(new Dimension(290, 232));
            ventana.setContentPane(getPanelVentana());
            Rectangle parentBounds = padre.getBounds();
            Dimension size = ventana.getSize();
            int x = Math.max(0, parentBounds.x + (parentBounds.width - size.width) / 2);
            int y = Math.max(0, parentBounds.y + (parentBounds.height - size.height) / 2);
            ventana.setLocation(new Point(x, y));
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
            panelVentana.add(getPanelUsuario(), null);
            panelVentana.add(getB_aceptar(), null);
            panelVentana.add(getB_salir(), null);
        }
        return panelVentana;
    }

    /**
     * This method initializes panelUsuario	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getPanelUsuario() {
        if (panelUsuario == null) {
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(10, 68, 74, 20));
            jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
            jLabel1.setText("Contraseña:");
            jLabel = new JLabel();
            jLabel.setHorizontalTextPosition(SwingConstants.TRAILING);
            jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
            jLabel.setSize(new Dimension(69, 20));
            jLabel.setLocation(new Point(14, 31));
            jLabel.setText("Usuario:");
            panelUsuario = new JPanel();
            panelUsuario.setLayout(null);
            panelUsuario.setBounds(new Rectangle(18, 27, 248, 112));
            panelUsuario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Ingrese Usuario y Contrasena", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
            panelUsuario.add(getT_usuario(), null);
            panelUsuario.add(getT_clave(), null);
            panelUsuario.add(jLabel, null);
            panelUsuario.add(jLabel1, null);
        }
        return panelUsuario;
    }

    /**
     * This method initializes t_usuario	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getT_usuario() {
        if (t_usuario == null) {
            t_usuario = new JTextField();
            t_usuario.setLocation(new Point(93, 31));
            //t_usuario.setText("admin");
            t_usuario.setSize(new Dimension(140, 20));
            
            t_usuario.addKeyListener(new java.awt.event.KeyAdapter() {   
            	public void keyPressed(java.awt.event.KeyEvent e) {    
            		if (e.getKeyCode() == 10) {
                        validar();
                    }
            	}
            });
        }
        return t_usuario;
    }

    /**
     * This method initializes t_clave	
     * 	
     * @return javax.swing.JPasswordField	
     */
    private JPasswordField getT_clave() {
        if (t_clave == null) {
            t_clave = new JPasswordField();
            t_clave.setLocation(new Point(92, 70));
            t_clave.setEchoChar('*');
            //t_clave.setText("adminCONS");
            t_clave.setSize(new Dimension(140, 20));
            t_clave.addKeyListener(new java.awt.event.KeyAdapter() {   
            	public void keyPressed(java.awt.event.KeyEvent e) {    
            		if (e.getKeyCode() == 10) {
                        validar();
                    }
            	}
            	public void keyTyped(java.awt.event.KeyEvent e) {
            		char x = e.getKeyChar();
            		if(x == '\n'){
            			validar();
            		}
            	}
            });
        }
        return t_clave;
    }

    /**
     * This method initializes b_aceptar	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getB_aceptar() {
        if (b_aceptar == null) {
            b_aceptar = new JButton();
            b_aceptar.setMnemonic(KeyEvent.VK_A);
            b_aceptar.setLocation(new Point(9, 160));
            b_aceptar.setSize(new Dimension(120, 30));
            //b_aceptar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
            b_aceptar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image042.png")));
            b_aceptar.setText("Aceptar");
            b_aceptar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    validar();                    
                }
            });
        }
        return b_aceptar;
    }

    /**
     * This method initializes b_salir	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getB_salir() {
        if (b_salir == null) {
            b_salir = new JButton();
            b_salir.setMnemonic(KeyEvent.VK_C);
            b_salir.setLocation(new Point(150, 160));
            b_salir.setSize(new Dimension(120, 30));
            b_salir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
            b_salir.setPreferredSize(new Dimension(122, 40));
            b_salir.setText("Cancelar");
            b_salir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ventana.setVisible(false);
                }
            });
        }
        return b_salir;
    }
}

