package GestionarInventario;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class nueCate {

	private JInternalFrame frmnueCate = null;  //  @jve:decl-index=0:visual-constraint="280,15"
	private JPanel pnlnueCate = null;  //  @jve:decl-index=0:
	private JLabel jLabel = null;
	private JTextField txtnomCate = null;
	private JButton guardar = null;
	//private int x,y;
	private JTextField cantidad = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox TiposPro = null;
	private GestorGestionarInventario gestor;  //  @jve:decl-index=0:
	public boolean nuevo;//tru nuevo false editar
	private JLabel jLabel3 = null;
	private JTextField ctdmin = null;
	private JButton b_aumentar = null;
	private JButton b_disminuir = null;
	private JButton historial = null;
	public JDesktopPane pan;
	public Autenticacion autenticar;
	
	public nueCate(JDesktopPane panel, boolean n,Autenticacion a){
		autenticar = a;
		pan = panel;
		getFrmnueCate();
		nuevo = n;
		int x = Math.max(0, (panel.getWidth() - frmnueCate.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - frmnueCate.getHeight() -70) / 2);
        frmnueCate.setLocation(new Point(x, y));
        if(nuevo)
        	historial.setEnabled(false);
	}	
	
	public void setGestor(GestorGestionarInventario g){
		gestor = g;
	}
	
	public void setNuevo(boolean n){
		nuevo = n;
	}
	
	/**
	 * This method initializes frmnueCate	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getFrmnueCate() {
		if (frmnueCate == null) {
			frmnueCate = new JInternalFrame();
			frmnueCate.setSize(new Dimension(271, 323));
			frmnueCate.setClosable(true);
			frmnueCate.setTitle("Editar Producto");
			//frmnueCate.setLocation(x,y);
			frmnueCate.setVisible(false);
			frmnueCate.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/nuevo.png")));
			frmnueCate.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			frmnueCate.setContentPane(getPnlnueCate());
			frmnueCate.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {   
				public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {    
					//System.out.println("Esta cerrando la ventana");
					frmnueCate.dispose();
				}
			});
		}
		return frmnueCate;
	}
	/**
	 * This method initializes pnlnueCate	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlnueCate() {
		if (pnlnueCate == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Cantidad mínima:");
			jLabel3.setLocation(new Point(40, 190));
			jLabel3.setSize(new Dimension(157, 20));
			jLabel2 = new JLabel();
			jLabel2.setText("Tipo de Producto:");
			jLabel2.setLocation(new Point(40, 75));
			jLabel2.setSize(new Dimension(178, 20));
			jLabel1 = new JLabel();
			jLabel1.setText("Cantidad:");
			jLabel1.setSize(new Dimension(95, 20));
			jLabel1.setLocation(new Point(40, 130));
			jLabel = new JLabel();
			jLabel.setText("Nombre del producto:");
			jLabel.setSize(new Dimension(182, 20));
			jLabel.setLocation(new Point(40, 20));
			pnlnueCate = new JPanel();
			pnlnueCate.setLayout(null);
			pnlnueCate.add(jLabel, null);
			pnlnueCate.add(getTxtnomCate(), null);
			pnlnueCate.add(getGuardar(), null);
			pnlnueCate.add(getCantidad(), null);
			pnlnueCate.add(jLabel1, null);
			pnlnueCate.add(jLabel2, null);
			pnlnueCate.add(getTiposPro(), null);
			pnlnueCate.add(jLabel3, null);
			pnlnueCate.add(getCtdmin(), null);
			pnlnueCate.add(getB_aumentar(), null);
			pnlnueCate.add(getB_disminuir(), null);
			pnlnueCate.add(getHistorial(), null);
		}
		return pnlnueCate;
	}
	/**
	 * This method initializes txtnomCate	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtnomCate() {
		if (txtnomCate == null) {
			txtnomCate = new JTextField();
			txtnomCate.setToolTipText("Ingrese el nombre que va a tener la nueva categoria o producto");
			txtnomCate.setLocation(new Point(40, 45));
			txtnomCate.setSize(new Dimension(182, 20));
		}
		return txtnomCate;
	}
	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton();
			guardar.setToolTipText("Guardar la categoria o producto");
			guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			guardar.setLocation(new Point(7, 245));
			guardar.setSize(new Dimension(115, 30));
			guardar.setText("Guardar");
			guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(nuevo)
						gestor.guardar();
					else
						gestor.actualizar();
				}
			});
		}
		return guardar;
	}

	/**
	 * This method initializes cantidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCantidad() {
		if (cantidad == null) {
			cantidad = new JTextField();
			cantidad.setText("0");
			cantidad.setLocation(new Point(40, 156));
			cantidad.setEditable(false);
			cantidad.setBackground(Color.white);
			cantidad.setSize(new Dimension(100, 20));
		}
		return cantidad;
	}

	/**
	 * This method initializes TiposPro	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getTiposPro() {
		if (TiposPro == null) {
			TiposPro = new JComboBox();
			TiposPro.setSelectedIndex(-1);
			TiposPro.setLocation(new Point(40, 100));
			TiposPro.setSize(new Dimension(182, 22));
		}
		return TiposPro;
	}

	/**
	 * This method initializes ctdmin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCtdmin() {
		if (ctdmin == null) {
			ctdmin = new JTextField();
			ctdmin.setLocation(new Point(40, 215));
			ctdmin.setSize(new Dimension(182, 20));
		}
		return ctdmin;
	}

	/**
	 * This method initializes b_aumentar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_aumentar() {
		if (b_aumentar == null) {
			b_aumentar = new JButton();
			b_aumentar.setLocation(new Point(189, 152));
			b_aumentar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			b_aumentar.setSize(new Dimension(30, 30));
			b_aumentar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String x = JOptionPane.showInputDialog(frmnueCate, "Cantidad de unidades a agregar", "Agregar productos", JOptionPane.QUESTION_MESSAGE);
					try{
						int actual = 0;
						try{
							actual = Integer.parseInt(cantidad.getText());
						}
						catch(Exception exe){}
						int ag = Integer.parseInt(x);
						if(ag > 0){
							if (JOptionPane.showConfirmDialog(frmnueCate, "Se agregaran "+ag+" unidades al inventario, desea continuar?", "Inventario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
								int c = actual + ag;
								cantidad.setText(String.valueOf(c));
								if(nuevo)
									gestor.agregarEvento("Agrega "+ag+" unidades el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
											+" a las "+UtilFechas.getHoraActual());
								else
									gestor.guardarEvento("Agregadas "+ag+" unidades el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
											+" a las "+UtilFechas.getHoraActual());
							}
						}
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			});
		}
		return b_aumentar;
	}

	/**
	 * This method initializes b_disminuir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_disminuir() {
		if (b_disminuir == null) {
			b_disminuir = new JButton();
			b_disminuir.setLocation(new Point(149, 152));
			b_disminuir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image087.png")));
			b_disminuir.setSize(new Dimension(30, 30));
			b_disminuir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						int c = Integer.parseInt(cantidad.getText());
						if(c > 0){
							if (JOptionPane.showConfirmDialog(frmnueCate, "Se descontará una unidad del inventario, desea continuar?", "Inventario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
								c = c-1;
								cantidad.setText(String.valueOf(c));
								if(nuevo)
									gestor.agregarEvento("Descontada 1 unidad el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
											+" a las "+UtilFechas.getHoraActual());
								else
									gestor.guardarEvento("Descontada 1 unidad el "+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)
											+" a las "+UtilFechas.getHoraActual());
							}
						}					
					}
					catch(Exception ex){}
					}
				});
		}
		return b_disminuir;
	}

	/**
	 * This method initializes historial	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getHistorial() {
		if (historial == null) {
			historial = new JButton();
			historial.setLocation(new Point(130, 245));
			historial.setText("Historial");
			historial.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image090.png")));
			historial.setSize(new Dimension(120, 30));
			historial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.eventos();
				}
			});
		}
		return historial;
	}
}  //  @jve:decl-index=0:visual-constraint="18,10"
