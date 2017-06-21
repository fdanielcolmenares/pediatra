package GestionarMensajeria;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;

public class panelConectados extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbl_imagen = null;
	private JLabel lbl_nombre = null;
	private VentanaMensajes ventana;
	private Cliente cliente;
	private boolean conectado;

	/**
	 * This is the default constructor
	 */
	public panelConectados(Cliente c) {
		super();
		cliente = c;
		conectado = false;
		initialize();
		ventana = new VentanaMensajes(this);
		c.panel.add(ventana.getVentana());
	}
	
	public void setConectado(boolean param){
		conectado = param;
		if(param){
			lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image076.png")));
		}
		else{
			lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
		}
	}
	
	public boolean getConectado(){
		return conectado;
	}
	
	public void setUsuario(String u){
		lbl_nombre.setText(u);
		ventana.setTitulo(u);
	}
	
	public String getUsuario(){
		return lbl_nombre.getText();
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public VentanaMensajes getventana(){
		return ventana;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lbl_nombre = new JLabel();
		lbl_nombre.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_nombre.setSize(new Dimension(174, 26));
		lbl_nombre.setLocation(new Point(45, 17));
		lbl_nombre.setForeground(Color.black);
		lbl_nombre.setText("");
		lbl_imagen = new JLabel();
		lbl_imagen.setText("");
		lbl_imagen.setSize(new Dimension(32, 30));
		lbl_imagen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image076.png")));
		lbl_imagen.setLocation(new Point(8, 15));
		this.setSize(230, 60);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		this.setBackground(Color.white);
		this.add(lbl_imagen, null);
		this.add(lbl_nombre, null);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(e.getClickCount() == 2){
					if(conectado){
						if(!ventana.getVentana().isVisible()){
							ventana.setVisible(true);
						}
						else{
							ventana.getVentana().toFront();
						}
					}
				}
			}
		});
	}

}  //  @jve:decl-index=0:visual-constraint="197,49"
