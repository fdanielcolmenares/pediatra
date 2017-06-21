package GestionarInventario;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

public class nueProdu {

	private JInternalFrame frmnueProdu = null;  //  @jve:decl-index=0:visual-constraint="255,22"
	private JPanel pnlnueProdu = null;
	private JLabel jLabel = null;
	private JComboBox listProdu = null;
	public String[] lista={"probando1","yrobando2","crobando3"};  //  @jve:decl-index=0:
	private JLabel jLabel1 = null;
	private JTextField numprodu = null;
	private JButton guardar = null;
	private int x,y;
	
	public nueProdu(){
		this.x=0;
		this.y=0;
	}
	
	public nueProdu(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * This method initializes frmnueProdu	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getFrmnueProdu() {
		if (frmnueProdu == null) {
			frmnueProdu = new JInternalFrame();
			frmnueProdu.setSize(new Dimension(243, 223));
			frmnueProdu.setContentPane(getPnlnueProdu());
			frmnueProdu.setClosable(true);
			frmnueProdu.setTitle("Agregar Producto");
			frmnueProdu.setLocation(x,y);
			frmnueProdu.requestFocus();
			frmnueProdu.setVisible(true);
			frmnueProdu.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/editar.png")));
			frmnueProdu.setContentPane(getPnlnueProdu());
		}
		return frmnueProdu;
	}

	/**
	 * This method initializes pnlnueProdu	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlnueProdu() {
		if (pnlnueProdu == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(28, 77, 181, 23));
			jLabel1.setText("Cantidad de unidades:");
			jLabel1.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(28, 16, 143, 23));
			jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel.setText("Seleccione el producto:");
			pnlnueProdu = new JPanel();
			pnlnueProdu.setLayout(null);
			pnlnueProdu.setBackground(Color.white);
			pnlnueProdu.add(jLabel, null);
			pnlnueProdu.add(getListProdu(), null);
			pnlnueProdu.add(jLabel1, null);
			pnlnueProdu.add(getNumprodu(), null);
			pnlnueProdu.add(getGuardar(), null);
		}
		return pnlnueProdu;
	}

	/**
	 * This method initializes listProdu	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getListProdu() {
		if (listProdu == null) {
			listProdu = new JComboBox();//lista
			listProdu.setToolTipText("Seleccionar el producto");
			listProdu.setLocation(new Point(29, 45));
			listProdu.setSize(new Dimension(180, 25));
			listProdu.setSelectedIndex(0);
			listProdu.setBackground(Color.white);
			listProdu.setEditable(false);
			listProdu.setPreferredSize(null);
			listProdu.setAutoscrolls(true);
			listProdu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			listProdu.setVisible(true);
		}
		return listProdu;
	}

	/**
	 * This method initializes numprodu	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumprodu() {
		if (numprodu == null) {
			numprodu = new JTextField();
			numprodu.setBounds(new Rectangle(31, 105, 104, 21));
		}
		return numprodu;
	}

	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton("Guardar");
			guardar.setBounds(new Rectangle(63, 146, 110, 29));
			guardar.setToolTipText("Guardar los productos");
			guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/3floppy_unmount.png")));
			guardar.setText("Guardar");
		}
		return guardar;
	}

}  //  @jve:decl-index=0:visual-constraint="18,10"
