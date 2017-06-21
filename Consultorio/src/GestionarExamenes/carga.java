package GestionarExamenes;



import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import java.awt.Cursor;
public class carga {

	//private JLabel jLabel = null;
	private JProgressBar barra = null;
	private JWindow jWindow = null;  //  @jve:decl-index=0:visual-constraint="140,109"
	private JPanel jContentPane = null;
	private JLabel jLabel1 = null;

	/**
	 * This method initializes barra	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getBarra() {
		if (barra == null) {
			barra = new JProgressBar();
			barra.setBounds(new Rectangle(8, 30, 230, 18));
			barra.setIndeterminate(true);
		}
		return barra;
	}	
	/**
	 * This method initializes jWindow	
	 * 	
	 * @return javax.swing.JWindow	
	 */
	public JWindow getJWindow() {
		if (jWindow == null) {
			jWindow = new JWindow();
			jWindow.setSize(new Dimension(244, 64));
			jWindow.setVisible(true);
			jWindow.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			jWindow.setContentPane(getJContentPane());
			jWindow.setLocation(200,200);
			jWindow.setAlwaysOnTop(true);
		}
		return jWindow;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(6, 5, 126, 19));
			jLabel1.setText("Cargando imagenes");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBarra());
			jContentPane.add(jLabel1);
		}
		return jContentPane;
	}
	
	public void mostrar(){
		jWindow.setVisible(true);
	}
	
	public void ocultar(){
		jWindow.setVisible(false);
	}


}
