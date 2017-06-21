package VentanasConfiguracion;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.codificador;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

public class VentanaServidor {

    private JDialog ventana = null;  //  @jve:decl-index=0:visual-constraint="138,38"
    private JPanel panelVentana = null;
    private JPanel panelUsuario = null;
    private JTextField t_ip = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JButton b_aceptar = null;
    private JButton b_salir = null;
    private JTextField t_puerto = null;
    private JFrame padre;
    private Autenticacion autenticacion;
    private boolean conecta;

    public VentanaServidor(JFrame p, Autenticacion a) {
        padre = p;
        autenticacion = new Autenticacion();
        autenticacion.setUsuarioBD(a.getUsuarioBD());
        autenticacion.setClaveBD(a.getClaveBD());
        autenticacion.setIpServidor(a.getIpServidor());
        autenticacion.setPuerto(a.getPuerto());
        conecta = false;

        getVentana();
        ventana.setVisible(false);
    }

    public void setVisible(boolean aFlag) {
        ventana.setVisible(aFlag);
    }

    public void setTexto(String a, String b) {
        t_ip.setText(a);
        t_puerto.setText(b);
    }

    public String getTextoIP() {
        return t_ip.getText();
    }

    public String getTextoPuerto() {
        return t_puerto.getText();
    }

    public void dispose() {
        ventana.dispose();
    }

    public void validar() {
        autenticacion.setIpServidor(t_ip.getText());
        autenticacion.setPuerto(t_puerto.getText());
        if (probarConexion(autenticacion)) {
            codificador cod = new codificador();
            try {
                cod.setTexto("ip = " + autenticacion.getIpServidor() + "\nport = " + autenticacion.getPuerto());
                cod.codificar(247, "ConfigServer.dll");
            } catch (Exception e) {
            }
            conecta = true;
            JOptionPane.showMessageDialog(ventana, "Operación exitosa", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
            ventana.setVisible(false);
        } else {
            conecta = false;
            JOptionPane.showMessageDialog(ventana, "No se pudo conectar con el servidor",
                    "Mensaje", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
        }
    }

    public boolean probarConexion(Autenticacion aut) {
        Conexion con = new Conexion(autenticacion);
        if (con.conectar()) {
            con.desconectar();
            return true;
        } else {
            return false;
        }
    }

    public boolean esValido() {
        return conecta;
    }

    private JDialog getVentana() {
        if (ventana == null) {
        	ventana = new JDialog();
        	ventana.setAlwaysOnTop(true);
        	ventana.setModal(true);
            ventana.setTitle("Configuración del Servidor");
            ventana.setSize(new Dimension(290, 232));
            ventana.setResizable(false);
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
            jLabel1.setText("Puerto:");
            jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
            jLabel = new JLabel();
            jLabel.setLocation(new Point(14, 31));
            jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
            jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            jLabel.setHorizontalTextPosition(SwingConstants.TRAILING);
            jLabel.setText("IP:");
            jLabel.setSize(new Dimension(69, 20));
            panelUsuario = new JPanel();
            panelUsuario.setLayout(null);
            panelUsuario.setBounds(new Rectangle(18, 27, 248, 112));
            panelUsuario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Ingrese IP y Puerto del Servidor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
            panelUsuario.add(getT_ip(), null);
            panelUsuario.add(jLabel, null);
            panelUsuario.add(jLabel1, null);
            panelUsuario.add(getT_puerto(), null);
        }
        return panelUsuario;
    }

    /**
     * This method initializes t_ip
     *
     * @return javax.swing.JTextField
     */
    private JTextField getT_ip() {
        if (t_ip == null) {
            t_ip = new JTextField();
            if(autenticacion.getIpServidor().compareTo("") == 0){
                t_ip.setText("127.0.0.1");
            }
            else{
                t_ip.setText(autenticacion.getIpServidor());
            }
            t_ip.setLocation(new Point(93, 31));
            t_ip.setSize(new Dimension(140, 20));

            t_ip.addKeyListener(new java.awt.event.KeyAdapter() {
                //@Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == 10) {
                        validar();
                    }
                }
            });
        }
        return t_ip;
    }

    /**
     * This method initializes b_aceptar
     *
     * @return javax.swing.JButton
     */
    private JButton getB_aceptar() {
        if (b_aceptar == null) {
            b_aceptar = new JButton();
            b_aceptar.setLocation(new Point(11, 160));
            b_aceptar.setMnemonic(KeyEvent.VK_A);
            b_aceptar.setText("Aceptar");
            b_aceptar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image042.png")));
            b_aceptar.setSize(new Dimension(120, 30));
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
            b_salir.setLocation(new Point(152, 160));
            b_salir.setMnemonic(KeyEvent.VK_C);
            b_salir.setText("Cancelar");
            b_salir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
            b_salir.setSize(new Dimension(120, 30));
            b_salir.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ventana.setVisible(false);
                }
            });
        }
        return b_salir;
    }

    /**
     * This method initializes t_puerto
     *
     * @return javax.swing.JTextField
     */
    private JTextField getT_puerto() {
        if (t_puerto == null) {
            t_puerto = new JTextField();
            if(autenticacion.getPuerto().compareTo("") == 0){
                t_puerto.setText("3306");
            }
            else{
                t_puerto.setText(autenticacion.getPuerto());
            }
            t_puerto.setLocation(new Point(91, 68));
            t_puerto.setSize(new Dimension(140, 20));
            t_puerto.addKeyListener(new java.awt.event.KeyAdapter() {
                //@Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == 10) {
                        validar();
                    }
                }
            });
        }
        return t_puerto;
    }
}
