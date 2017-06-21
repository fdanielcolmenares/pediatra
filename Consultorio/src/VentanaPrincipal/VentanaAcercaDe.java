package VentanaPrincipal;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.WindowConstants;

public class VentanaAcercaDe {

	private JDialog ventana = null;  //  @jve:decl-index=0:visual-constraint="98,40"
	private JPanel jContentPane = null;
	private JLabel jLabel = null;

	public VentanaAcercaDe(VentanaPrincipal padre){
		getVentana();
		Rectangle parentBounds = padre.getVentana().getBounds();
        Dimension size = ventana.getSize();		
        int x = Math.max(0, parentBounds.x + (parentBounds.width - size.width) / 2);
        int y = Math.max(0, parentBounds.y + (parentBounds.height - size.height) / 2);
        ventana.setLocation(new Point(x, y));
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	public JDialog getVentana() {
		if (ventana == null) {
			ventana = new JDialog();
			ventana.setSize(new Dimension(454, 284));
			ventana.setModal(true);
			ventana.setTitle("Acerca de");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setResizable(false);
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
			jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image023.png")));
			jLabel.setSize(new Dimension(450, 249));
			jLabel.setLocation(new Point(0, 0));
			jLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setToolTipText("Acerca de");
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

}
