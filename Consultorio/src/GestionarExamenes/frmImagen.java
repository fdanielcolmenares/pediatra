package GestionarExamenes;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import Utilitario.Autenticacion;
import Entidades.estudios_historia;
import javax.swing.JScrollPane;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class frmImagen {

	private JInternalFrame vtaImagen = null;  //  @jve:decl-index=0:visual-constraint="194,-42"
	private JPanel pnlImagen = null;
	private lienzo pinta;
	private JPanel pnlDescricion = null;
	private JTextArea txaDesc = null;
	private JButton guardar = null;
	private JButton zoomin = null;
	private JButton zoomout = null;
	private JButton rotar = null;
	private Autenticacion autentica;  //  @jve:decl-index=0:
	private JScrollPane jScrollPane = null;
	private String ruta;  //  @jve:decl-index=0:
	private int numEstu,numHis;
	private FrmInternoVisorIma panelImagenes;
	public JDesktopPane desktopPanel;
	
	public frmImagen(Autenticacion a, JDesktopPane p){
		autentica = a;
		desktopPanel = p;
		pinta = new lienzo();
		pinta.setSize(760,290);
		getVtaImagen();
		int x = Math.max(0, (p.getWidth() - vtaImagen.getWidth()-15) / 2);
        int y = Math.max(0, (p.getHeight() - vtaImagen.getHeight() -70) / 2);
        vtaImagen.setLocation(new Point(x, y));
	}
	
	public frmImagen(JDesktopPane p){	
		desktopPanel = p;
		pinta=new lienzo();
		pinta.setSize(760,290);
		getVtaImagen();
		int x = Math.max(0, (p.getWidth() - vtaImagen.getWidth()-15) / 2);
        int y = Math.max(0, (p.getHeight() - vtaImagen.getHeight() -70) / 2);
        vtaImagen.setLocation(new Point(x, y));
	}
	
	public void setPanelExamenes(FrmInternoVisorIma pi){
		panelImagenes = pi;
	}
	
	public int getNumHis() {
		return numHis;
	}

	public void setNumHis(int numHis) {
		this.numHis = numHis;
	}

	public int getNumEstu() {
		return numEstu;
	}

	public void setNumEstu(int numEstu) {
		this.numEstu = numEstu;
	}

	public Autenticacion getAutentica() {
		return autentica;
	}

	public void setAutentica(Autenticacion autentica) {
		this.autentica = autentica;
	}

	public void mostrar(String r){
		ruta = r;
		pinta.cargarImagen(r);
		vtaImagen.setVisible(true);
	}
	
	public void mostrar(ImageIcon ima, String des){
		pinta.cargarImagen(ima);
		getTxaDesc().setText(des);
		vtaImagen.setVisible(true);
	}
	
	public void escanear(){
		vtaImagen.setVisible(true);
	}
	
	/**
	 * This method initializes vtaImagen	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVtaImagen() {
		if (vtaImagen == null) {
			vtaImagen = new JInternalFrame();
			vtaImagen.setSize(new Dimension(770, 454));
			vtaImagen.setTitle("Examen");
			vtaImagen.setClosable(true);
			vtaImagen.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			vtaImagen.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image102.png")));
			vtaImagen.setResizable(true);
			vtaImagen.setContentPane(getPnlImagen());
			vtaImagen.setVisible(false);
		}
		return vtaImagen;
	}

	/**
	 * This method initializes pnlImagen	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlImagen() {
		if (pnlImagen == null) {
			pnlImagen = new JPanel();
			pnlImagen.setLayout(null);
			pnlImagen.add(getPnlDescricion(), null);
			pnlImagen.add(pinta);
		}
		return pnlImagen;
	}

	/**
	 * This method initializes pnlDescricion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlDescricion() {
		if (pnlDescricion == null) {
			pnlDescricion = new JPanel();
			pnlDescricion.setLayout(null);
			pnlDescricion.setBounds(new Rectangle(15, 292, 729, 126));
			pnlDescricion.setBorder(BorderFactory.createTitledBorder(null, "Descripción", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlDescricion.add(getJScrollPane(), null);
			pnlDescricion.add(getZoomin(), null);
			pnlDescricion.add(getZoomout(), null);
			pnlDescricion.add(getRotar(), null);
			pnlDescricion.add(getGuardar(), null);
		}
		return pnlDescricion;
	}

	/**
	 * This method initializes txaDesc	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTxaDesc() {
		if (txaDesc == null) {
			txaDesc = new JTextArea();
		}
		return txaDesc;
	}

	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton();
			guardar.setToolTipText("Guardar");
			guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			guardar.setVerticalTextPosition(SwingConstants.CENTER);
			guardar.setSize(new Dimension(45, 35));
			guardar.setLocation(new Point(178, 84));
			guardar.setMnemonic(KeyEvent.VK_UNDEFINED);
			guardar.setText("");
			guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					estudios_historia exa = new estudios_historia(autentica);
					exa.buscarNuevoID();
					exa.setObservacion(getTxaDesc().getText());
					/*String fecha = 
						dia = Integer.toString(c.get(Calendar.DATE));
					mes = Integer.toString(c.get(Calendar.MONTH));
					annio = Integer.toString(c.get(Calendar.YEAR));*/
					exa.setFecha("sysdate()");
					exa.setImagen(ruta);
					exa.setEtdo_id(getNumEstu());
					exa.setHtra_id(numHis);
					if(exa.guardar()){
						JOptionPane.showMessageDialog(getVtaImagen(), "Guardado Exitosamente", "Mensaje",
			                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
						//getTxaDesc().setText("");
						panelImagenes.actualizarExamenes();
						guardar.setEnabled(false);
					}
					else{
						JOptionPane.showMessageDialog(getVtaImagen(), "No se pudo guardar", "Mensaje",
			                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					}
				}
			});
		}
		return guardar;
	}

	/**
	 * This method initializes zoomin	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getZoomin() {
		if (zoomin == null) {
			zoomin = new JButton();
			zoomin.setToolTipText("Acerca la imagen");
			zoomin.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image104.png")));
			zoomin.setActionCommand("");
			zoomin.setLocation(new Point(16, 84));
			zoomin.setSize(new Dimension(45, 35));
			zoomin.setText("");
			zoomin.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("zoom in");
					pinta.acercar();
				}
			});
		}
		return zoomin;
	}

	/**
	 * This method initializes zoomout	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getZoomout() {
		if (zoomout == null) {
			zoomout = new JButton();
			zoomout.setToolTipText("Aleja la imagen");
			zoomout.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image103.png")));
			zoomout.setSize(new Dimension(45, 35));
			zoomout.setLocation(new Point(70, 84));
			zoomout.setText("");
			zoomout.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("zoom out"); 
					pinta.alejar();
				}
			});
		}
		return zoomout;
	}
	
	public void bloquear(){
		/*zoomout.setVisible(true);
		zoomin.setVisible(true);
		rotar.setVisible(true);*/
		guardar.setEnabled(false);
		txaDesc.setEditable(false);
	}
	
	public void desbloquear(){
		/*zoomout.setVisible(false);
		zoomin.setVisible(false);
		rotar.setVisible(false);*/
		guardar.setEnabled(true);
		txaDesc.setEditable(true);
	}

	/**
	 * This method initializes rotar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRotar() {
		if (rotar == null) {
			rotar = new JButton();
			rotar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/rotar.png")));
			rotar.setToolTipText("Rotar la imagen");
			rotar.setLocation(new Point(124, 84));
			rotar.setSize(new Dimension(45, 35));
			rotar.setText("");
			rotar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("rotando");
					pinta.rotar();
				}
			});
		}
		return rotar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 18, 696, 62));
			jScrollPane.setViewportView(getTxaDesc());
		}
		return jScrollPane;
	}

}
