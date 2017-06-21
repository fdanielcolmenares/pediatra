package GestionarInventario;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import Utilitario.Autenticacion;

public class frmInventario {

	private JInternalFrame frmInventario = null;  //  @jve:decl-index=0:visual-constraint="162,20"
	private JPanel pnlfrmInventario = null;
	private JButton nuevo = null;
	private JButton generar = null;
	private JButton buscar = null;
	private JDesktopPane panel;
	private GestorGestionarInventario gestor;  //  @jve:decl-index=0:
	private Autenticacion aut;
	private JButton tipos = null;
	
	public frmInventario(int w, int h,JDesktopPane p,Autenticacion a){
		getFrmInventario();
		int x = Math.max(0, (w - frmInventario.getWidth()-15) / 2);
        int y = Math.max(0, (h - frmInventario.getHeight() -70) / 2);
        frmInventario.setLocation(new Point(x, y));
        panel=p;
        aut = a;
	}
	
	public void setGestor(GestorGestionarInventario g){
		gestor = g;
	}
	
	public GestorGestionarInventario getGestor(){
		return gestor;
	}
	
	/**
	 * This method initializes frmInventario	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getFrmInventario() {
		if (frmInventario == null) {
			frmInventario = new JInternalFrame();
			frmInventario.setSize(new Dimension(457, 144));
			frmInventario.setClosable(true);
			frmInventario.setTitle("Opciones Inventario");
			frmInventario.setVisible(false);
			frmInventario.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/kthememgr.png")));
			frmInventario.setContentPane(getPnlfrmInventario());
		}
		return frmInventario;
	}
	
	public void setVisible(boolean aFlag){
		frmInventario.setVisible(aFlag);
	}

	/**
	 * This method initializes pnlfrmInventario	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlfrmInventario() {
		if (pnlfrmInventario == null) {
			pnlfrmInventario = new JPanel();
			pnlfrmInventario.setLayout(null);
			pnlfrmInventario.setBackground(Color.white);
			pnlfrmInventario.add(getNuevo(), null);
			pnlfrmInventario.add(getGenerar(), null);
			pnlfrmInventario.add(getBuscar(), null);
			pnlfrmInventario.add(getTipos(), null);
		}
		return pnlfrmInventario;
	}

	/**
	 * This method initializes nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNuevo() {
		if (nuevo == null) {
			nuevo = new JButton();
			nuevo.setBounds(new Rectangle(15, 13, 87, 86));
			nuevo.setToolTipText("Crear un nuevo producto o categoria en el inventario");
			nuevo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/filenew.png")));
			nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					nueCate b = new nueCate(panel,true,aut);
					JInternalFrame a = b.getFrmnueCate();
					GestorGestionarInventario ges = new GestorGestionarInventario(b,aut);
			    	b.setGestor(ges);
			    	ges.validarTipos();
			    	ges.llenartipoProductos();
					panel.add(a);
					a.toFront();
					a.requestFocus();
				}
			});
		}
		return nuevo;
	}

	/**
	 * This method initializes generar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGenerar() {
		if (generar == null) {
			generar = new JButton();
			generar.setBounds(new Rectangle(350, 14, 89, 89));
			generar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/acroread.png")));
			generar.setToolTipText("Generar reporte de todo el inventario");
			generar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()");
				}
			});
		}
		return generar;
	}

	/**
	 * This method initializes buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscar() {
		if (buscar == null) {
			buscar = new JButton();
			buscar.setBounds(new Rectangle(122, 13, 94, 88));
			buscar.setToolTipText("Buscar producto");
			buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/filefind.png")));
			buscar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					BuscarInventario b = new BuscarInventario(panel);
					b.setAutenticacion(aut);
					JInternalFrame a=b.getVentana();
					panel.add(a);
					a.toFront();
					a.requestFocus();
					a.setVisible(true);
				}
			});
		}
		return buscar;
	}

	/**
	 * This method initializes tipos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTipos() {
		if (tipos == null) {
			tipos = new JButton();
			tipos.setBounds(new Rectangle(240, 15, 95, 86));
			tipos.setToolTipText("Agregar o Editar Tipo de Producto");
			tipos.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/kedit.png")));
			tipos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TipoMedicamento med = new TipoMedicamento(panel.getWidth(),panel.getHeight());
					JInternalFrame a=med.getVentana();
					GestorTipoMedicamento ges = new GestorTipoMedicamento(aut,med);
					med.setGestor(ges);
					panel.add(a);
					a.toFront();
					a.requestFocus();
					a.setVisible(true);
				}
			});
		}
		return tipos;
	}

}
