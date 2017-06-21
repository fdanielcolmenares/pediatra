package GestionarMensajeria;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Font;

public class VentanaMensajes {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="149,29"
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTextPane t_texto = null;
	private JLabel lbl_1 = null;
	private JScrollPane jScrollPane1 = null;
	private JButton btn_enviar = null;
	private JTextPane t_mensaje = null;
	private panelConectados panel;
	private JLabel lbl_conversacion = null;
	
	public VentanaMensajes(panelConectados p){
		panel = p;
		getVentana();
		
	}
	
	public void setTitulo(String param){
		lbl_conversacion.setText("Conversación con: \" "+param+" \" ");
	}

	public void recibeMensaje(String de, String msj, boolean sonido){
		if(!ventana.isVisible()){
			setVisible(true);
		}
		
		msj = msj.replaceAll("\n", "\n\t");
		
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setBold(attrs, true);
		StyleConstants.setFontSize(attrs, 14);
		StyleConstants.setForeground(attrs, Color.BLACK);
		
		SimpleAttributeSet attrs2 = new SimpleAttributeSet();
		StyleConstants.setBold(attrs2, true);
		StyleConstants.setFontSize(attrs2, 14);
		StyleConstants.setForeground(attrs2, Color.BLUE);
		try{
			t_texto.getStyledDocument().insertString(t_texto.getStyledDocument().getLength(), 
				">>"+de+" dice:\n", attrs);
			
			t_texto.getStyledDocument().insertString(t_texto.getStyledDocument().getLength(), 
					"\t"+msj+"\n", attrs2);	
			
			//jScrollPane.getVerticalScrollBar().setValue(20000);
			if(sonido){
				suenaMensaje();
			}
			
			jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum()+146);
			
			
			int px = ventana.getX();
			int py = ventana.getY();
			ventana.setLocation(px-1, py);
			ventana.setLocation(px, py);
			
			t_mensaje.requestFocus();
		}
		catch(Exception e){e.printStackTrace();
		}
		
		if(!ventana.isVisible()){
			ventana.setVisible(true);
		}
	}
	
	public void suenaMensaje(){
		//Toolkit.getDefaultToolkit().beep();
		try{
			//System.out.println(getClass().getResource("/Files/Imagenes/Sound001.wav").toString());
			AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Files/Imagenes/Sound001.wav"));
			sonido.play();
        }catch(Exception e){   
        	e.printStackTrace();
        }
	}
	
	public void setVisible(boolean param){
		if(param){
			ventana.setLocation(panel.getCliente().panel.getWidth()-465, 
					panel.getCliente().panel.getHeight()-360);
			t_texto.setText("");
		}
		
		ventana.setVisible(param);
		t_mensaje.requestFocus();
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(445, 288));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image079.png")));
			ventana.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
			ventana.setTitle("Mensajes");
			ventana.setClosable(true);
			ventana.setContentPane(getJContentPane());
			ventana.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
				public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
					ventana.setVisible(false);
				}
			});
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
			lbl_conversacion = new JLabel();
			lbl_conversacion.setText("Conversación con:");
			lbl_conversacion.setSize(new Dimension(389, 20));
			lbl_conversacion.setLocation(new Point(23, 7));
			lbl_1 = new JLabel();
			lbl_1.setText("Mensaje:");
			lbl_1.setSize(new Dimension(80, 20));
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_1.setLocation(new Point(-2, 188));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(lbl_1, null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getBtn_enviar(), null);
			jContentPane.add(lbl_conversacion, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(21, 28, 393, 148));
			jScrollPane.setViewportView(getT_texto());
		}
		return jScrollPane;
	}
		/**
	 * This method initializes t_texto	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getT_texto() {
		if (t_texto == null) {
			t_texto = new JTextPane();
			t_texto.setEditable(false);
			t_texto.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return t_texto;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setLocation(new Point(85, 188));
			jScrollPane1.setFont(new Font("Dialog", Font.BOLD, 14));
			jScrollPane1.setViewportView(getT_mensaje());
			jScrollPane1.setSize(new Dimension(258, 55));
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes btn_enviar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_enviar() {
		if (btn_enviar == null) {
			btn_enviar = new JButton();
			btn_enviar.setLocation(new Point(358, 188));
			btn_enviar.setText("");
			btn_enviar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image080.png")));
			btn_enviar.setSize(new Dimension(57, 57));
			btn_enviar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_mensaje.getText().length()>0){
						recibeMensaje(panel.getCliente().getUsuario(), t_mensaje.getText(), false);
						
						panel.getCliente().enviarMensaje(t_mensaje.getText(), panel.getUsuario());
						
						t_mensaje.setText("");
						t_mensaje.requestFocus();
					}
				}
			});
		}
		return btn_enviar;
	}
	
	

	/**
	 * This method initializes t_mensaje	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextPane getT_mensaje() {
		if (t_mensaje == null) {
			t_mensaje = new JTextPane();
			t_mensaje.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return t_mensaje;
	}

}
