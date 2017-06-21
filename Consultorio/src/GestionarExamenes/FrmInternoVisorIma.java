package GestionarExamenes;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Utilitario.Autenticacion;
import Entidades.estudios;
import java.awt.Font;

public class FrmInternoVisorIma {

	private JInternalFrame visorIma = null;  //  @jve:decl-index=0:visual-constraint="163,37"
	private JPanel pnlVisorIma = null;
	private JTabbedPane pnlPestana = null;
	private psaExamen []pestanas;
	private JButton guardar = null;
	private frmImagen ven;
	public ScannerTab scaner=null;
	private int contima=0;
	private	estudios pest;
	private Autenticacion autentica;
	private int historia;
	private JButton btn_cerrar = null;
	private JDesktopPane desktopPane;
	/**
	 * This method initializes visorIma	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public FrmInternoVisorIma(frmImagen v, Autenticacion a, int numhis, JDesktopPane p){
		autentica = a;
		ven = v;
		ven.setAutentica(a);
		pest = new estudios(a);
		historia = numhis;
		getVisorIma();
		int x = Math.max(0, (p.getWidth() - visorIma.getWidth()-15) / 2);
        int y = Math.max(0, (p.getHeight() - visorIma.getHeight() -70) / 2);
        visorIma.setLocation(new Point(x, y));
        desktopPane = p;
	}
	
	public void actualizarExamenes(){
		//System.out.println("Actualizar");
		for(int i=0; i<pestanas.length; i++){
			pestanas[i].cargarImagenes();
		}
	}
	
	public JInternalFrame getVisorIma() {
		if (visorIma == null) {
			visorIma = new JInternalFrame();
			visorIma.setSize(new Dimension(770, 430));
			visorIma.setClosable(true);
			visorIma.setPreferredSize(new Dimension(770, 430));
			visorIma.setTitle("Visor de Examenes");
			visorIma.setToolTipText("Muestra Imagenes");
			visorIma.setVisible(false);
			visorIma.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			visorIma.setContentPane(getPnlVisorIma());
		}
		return visorIma;
	}	

	/**
	 * This method initializes pnlVisorIma	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlVisorIma() {
		if (pnlVisorIma == null) {
			pnlVisorIma = new JPanel();
			pnlVisorIma.setLayout(null);
			pnlVisorIma.add(getPnlPestana(), null);
			pnlVisorIma.add(getGuardar(), null);
			pnlVisorIma.add(getBtn_cerrar(), null);
		}
		return pnlVisorIma;
	}
	/**
	 * This method initializes pnlPestaña	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getPnlPestana() {
		if (pnlPestana == null) {
			pnlPestana = new JTabbedPane();
			pnlPestana.setBounds(new Rectangle(14, 15, 737, 343));
			pnlPestana.setBackground(Color.white);
			pnlPestana.setFont(new Font("Dialog", Font.BOLD, 14));
			pnlPestana.setTabPlacement(JTabbedPane.LEFT);
			//agregando las pestañas
			pestanas=new psaExamen[pest.contar()];
			ResultSet res = pest.cargartipos();
			int ctps=0;
			try {
				while(res.next()){
					pestanas[ctps]=new psaExamen(ven,autentica,ctps+1,historia);
					pnlPestana.addTab(res.getString("descripcion"), null, pestanas[ctps].getJScrollPane(), null);
					ctps++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pnlPestana;
	}
	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton();
			guardar.setToolTipText("Crear una nueva imagen");
			guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			guardar.setLocation(new Point(512, 362));
			guardar.setSize(new Dimension(110, 30));
			guardar.setText("Nuevo");
			guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					/*ImageIO.scanForPlugins();
					scaner=new ScannerTab(String.valueOf(contima),ven);
					scaner.setButtonPanel(new JPanel());*/
					JFileChooser buscador = new JFileChooser();		
			        buscador.setSelectedFile(new File("*.jpg"));
			        String ruta="";
			        int d = buscador.showSaveDialog(null);
			        if(d == JFileChooser.APPROVE_OPTION){
			            ruta = buscador.getSelectedFile().toString();
			            System.out.println("ruta: "+ruta);
			            contima++;
						//ven.desbloquear();
						ven.setNumEstu(pnlPestana.getSelectedIndex()+1);
						ven.setNumHis(historia);
						ven.desbloquear();
						ven.getTxaDesc().setText("");
						desktopPane.add(ven.getVtaImagen());
						ven.mostrar(ruta);
						//ven.escanear();
						//System.out.println(pnlPestaña.getSelectedIndex());
			        }
				}
			});
		}
		return guardar;
	}

	/**
	 * This method initializes btn_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar() {
		if (btn_cerrar == null) {
			btn_cerrar = new JButton();
			btn_cerrar.setLocation(new Point(640, 362));
			btn_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cerrar.setText("Cerrar");
			btn_cerrar.setSize(new Dimension(110, 30));
			btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					visorIma.dispose();
				}
			});
		}
		return btn_cerrar;
	}

}
