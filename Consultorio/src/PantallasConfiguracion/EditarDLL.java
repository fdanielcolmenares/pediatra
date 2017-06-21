package PantallasConfiguracion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;
import Utilitario.DecodificadorDLL;
import javax.swing.JComboBox;


public class EditarDLL {

	private JFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="187,28"
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTextField t_ruta = null;
	private JLabel lbl_1 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JScrollPane jScrollPane = null;
	private JTextPane t_texto = null;
	private JButton jButton3 = null;
	private JComboBox t_clave = null;

	public EditarDLL(){
		getVentana();
	}
	
	public void abrir(){
		DecodificadorDLL dec = new DecodificadorDLL();
		if(t_ruta.getText().length()>0){
			t_texto.setText(dec.leer(t_ruta.getText()));
		}		
	}
	
	public void decodificar(){
		if(t_ruta.getText().length()>0){
			String val = t_clave.getSelectedItem().toString();
			String sep[] = val.split("-");
			DecodificadorDLL dec = new DecodificadorDLL();
			t_texto.setText(dec.decodificar(t_ruta.getText(), Integer.parseInt(sep[0])));
		}
	}
	
	public void codificar(){
		if(t_ruta.getText().length()>0){
			String val = t_clave.getSelectedItem().toString();
			String sep[] = val.split("-");
			DecodificadorDLL dec = new DecodificadorDLL();
			if(dec.codificar(t_ruta.getText(), t_texto.getText(), Integer.parseInt(sep[0]))){
				JOptionPane.showMessageDialog(ventana, "Operación Exitosa", "Exito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			}
			else{
				JOptionPane.showMessageDialog(ventana, "No se pudo guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			}
		}
	}
	
	public void examinar(){
		JFileChooser buscador = new JFileChooser();
		buscador.setDialogTitle("Archivo a abrir");
		if(buscador.showOpenDialog(ventana) == 0){
			t_ruta.setText(buscador.getSelectedFile().toString());
		}
	}
	
	public void cerrar(){
		if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea salir?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
			ventana.dispose();
		}
	}
	
	public void setVisible(boolean aFlag){
		ventana.setVisible(aFlag);
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getVentana() {
		if (ventana == null) {
			ventana = new JFrame();
			ventana.setSize(new Dimension(487, 346));
			ventana.setTitle("Editar DLL");
			ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Files/Imagenes/Image049.png")));
			ventana.setContentPane(getJContentPane());
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            ventana.setLocation((int) (d.getWidth() / 2 - ventana.getWidth() / 2), (int) (d.getHeight() / 2 - ventana.getHeight() / 2));
			ventana.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					cerrar();
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
			lbl_1 = new JLabel();
			lbl_1.setText("Clave:");
			lbl_1.setSize(new Dimension(38, 20));
			lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_1.setLocation(new Point(33, 74));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getT_ruta(), null);
			jContentPane.add(lbl_1, null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getT_clave(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Examinar");
			jButton.setSize(new Dimension(115, 25));
			jButton.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			jButton.setLocation(new Point(345, 29));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					examinar();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes t_ruta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_ruta() {
		if (t_ruta == null) {
			t_ruta = new JTextField();
			t_ruta.setLocation(new Point(32, 32));
			t_ruta.setSize(new Dimension(300, 20));
		}
		return t_ruta;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Guardar");
			jButton1.setSize(new Dimension(117, 30));
			jButton1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image049.png")));
			jButton1.setLocation(new Point(343, 69));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					codificar();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Abrir");
			jButton2.setLocation(new Point(222, 69));
			jButton2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image048.png")));
			jButton2.setSize(new Dimension(117, 30));
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					decodificar();
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(52, 114, 388, 111));
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
		}
		return t_texto;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setLocation(new Point(195, 262));
			jButton3.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			jButton3.setText("Cerrar");
			jButton3.setFont(new Font("Dialog", Font.BOLD, 12));
			jButton3.setSize(new Dimension(110, 30));
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrar();
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes t_clave	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getT_clave() {
		if (t_clave == null) {
			t_clave = new JComboBox();
			t_clave.setLocation(new Point(76, 74));
			t_clave.setSize(new Dimension(140, 20));
			t_clave.addItem("247-ConfigServer");
			t_clave.addItem("634-ConfigIni");
			t_clave.addItem("981-ConfigUser");
		}
		return t_clave;
	}

}
