package GestionarMensajeria;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Cliente {

    private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="253,6"
	private JPanel jContentPane = null;
	private JScrollPane scrollPaneles = null;
	private JButton btn_minimizar = null;
	private JLabel lbl_imagen = null;
	private JLabel lbl_nombre = null;
	private GestorCliente gestor;  //  @jve:decl-index=0:
	public int CONECTADO = 1;
	public int DESCONECTADO = 2;
	public int CONECTANDO = 3;
	private JPanel panelConectados = null;
	public JDesktopPane panel;
	
	public Cliente(JDesktopPane p){
		getVentana();
		ventana.setLocation(0,0);
		panel = p;
	}
	
	public void setUsuario(String u){
		lbl_nombre.setText(u);
	}
	
	public String getUsuario(){
		return lbl_nombre.getText();
	}
	
	public void setGestor(GestorCliente g){
		gestor = g;
	}
	
	public void setEstado(int e){
		if(e == CONECTADO){
			lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image076.png")));
			//ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image076.png")));
			lbl_imagen.setToolTipText("Conectado");
			lbl_nombre.setToolTipText("Conectado");
		}
		else{
			lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
			//ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
			lbl_imagen.setToolTipText("No Conectado");
			lbl_nombre.setToolTipText("No Conectado");
		}
	}
	
	public void enviarMensaje(String msj, String para){
		gestor.enviarMensaje(msj, para);
	}
	
	public void setVisible(boolean param){
		ventana.setVisible(param);
	}

	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(288, 312));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image079.png")));
			ventana.setLocation(new Point(100, 100));
			ventana.setTitle("Mensajes instantáneos");
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
			lbl_nombre = new JLabel();
			lbl_nombre.setBounds(new Rectangle(51, 41, 214, 32));
			lbl_nombre.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl_nombre.setForeground(Color.black);
			lbl_nombre.setText("");
			lbl_imagen = new JLabel();
			lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
			lbl_imagen.setLocation(new Point(12, 42));
			lbl_imagen.setSize(new Dimension(33, 30));
			lbl_imagen.setText("");
			lbl_imagen.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					gestor.buscarServidor();
				}
			});
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getScrollPaneles(), null);
			jContentPane.add(getBtn_minimizar(), null);
			jContentPane.add(lbl_imagen, null);
			jContentPane.add(lbl_nombre, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes scrollPaneles	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPaneles() {
		if (scrollPaneles == null) {
			scrollPaneles = new JScrollPane();
			scrollPaneles.setLocation(new Point(12, 89));
			scrollPaneles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneles.setViewportView(getPanelConectados());
			scrollPaneles.setSize(new Dimension(250, 180));
		}
		return scrollPaneles;
	}

	/**
	 * This method initializes btn_minimizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_minimizar() {
		if (btn_minimizar == null) {
			btn_minimizar = new JButton();
			btn_minimizar.setLocation(new Point(234, 2));
			btn_minimizar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image077.png")));
			btn_minimizar.setToolTipText("Minimizar");
			btn_minimizar.setSize(new Dimension(30, 30));
			btn_minimizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.minimizar();
				}
			});
		}
		return btn_minimizar;
	}

	/**
	 * This method initializes panelConectados	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanelConectados() {
		if (panelConectados == null) {
			panelConectados = new JPanel();
			panelConectados.setLayout(null);
			panelConectados.setSize(new Dimension(230, 180));
		}
		return panelConectados;
	}
}
