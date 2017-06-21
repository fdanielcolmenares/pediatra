package GestionarConsultas;


import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Utilitario.Autenticacion;
import Utilitario.TableRenderDemo;
import javax.swing.JCheckBox;

public class ListaConsultas {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="18,17"
	private JPanel panel = null;
	private JScrollPane jScrollPane = null;
	private JButton cerrar = null;
	private JButton nueva = null;
	private TableRenderDemo tabla;  //  @jve:decl-index=0:
	private String numhis;
	private JDesktopPane desktopPane;
	private Autenticacion autenticacion;
	private JButton ver = null;
	private JCheckBox chk_cerrar = null;

	public ListaConsultas(int w, int h,String numhis,JDesktopPane p, Autenticacion a){
        this.numhis = numhis;
        desktopPane = p;
        autenticacion = a;
        getVentana();
		int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
        ventana.setLocation(new Point(x, y));
        setNivelUsuario(autenticacion.getTipoUsuario());
        //System.out.println("autenticar: "+autenticacion.getClaveBD());
        //gestor.llenarPrimera();
	}
	
	public void setNivelUsuario(int n){
		if(n == 3){
			nueva.setEnabled(false);
		}
		if(n == 2){
			nueva.setEnabled(true);
		}
		if(n == 1){
			nueva.setEnabled(true);
		}
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setClosable(true);
			ventana.setTitle("Lista de Consultas");
			ventana.setBounds(new Rectangle(0, 0, 500, 318));
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image057.png")));
			ventana.setContentPane(getPanel());
		}
		return ventana;
	}

	/**
	 * This method initializes panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();			
			panel.setLayout(null);
			panel.add(getJScrollPane(), null);
			panel.add(getCerrar(), null);
			panel.add(getNueva(), null);
			panel.add(getVer(), null);
			panel.add(getChk_cerrar(), null);
		}
		return panel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(8, 7, 473, 209));
			tabla = new TableRenderDemo(jScrollPane, 473, 209, true, autenticacion, numhis);
			if(tabla.table.getModel().getRowCount()==0){
				JOptionPane.showMessageDialog(getVentana(), "No hay consultas para esta historia", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE);
				getVentana().dispose();
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCerrar() {
		if (cerrar == null) {
			cerrar = new JButton();
			cerrar.setBounds(new Rectangle(414, 220, 60, 60));
			cerrar.setBackground(Color.white);
			cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image022.png")));
			cerrar.setToolTipText("Cerrar");
			cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getVentana().dispose();
				}
			});
		}
		return cerrar;
	}

	/**
	 * This method initializes nueva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNueva() {
		if (nueva == null) {
			nueva = new JButton();
			nueva.setBounds(new Rectangle(275, 220, 60, 60));
			nueva.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image044.png")));
			nueva.setToolTipText("Crear nueva consulta");
			nueva.setBackground(Color.white);
			nueva.setOpaque(true);
			nueva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int found=0;
					boolean cer=false;
					for(int i=0;i<tabla.table.getModel().getRowCount();i++){
						if(tabla.table.getModel().getValueAt(i,3).toString().compareTo("true")==0){					
							CrearConsulta ven = new CrearConsulta(desktopPane.getWidth(), desktopPane.getHeight(),numhis,false);
							GestorCrearConsulta q = new GestorCrearConsulta(ven, autenticacion, desktopPane);
							ven.setGestor(q);
							String ret = q.llenarPrimera(numhis);
							q.llenarUltima(numhis, ret);
							q.persona(numhis);
							if(q.cargarActual(numhis,tabla.table.getModel().getValueAt(i,0).toString(),tabla.table.getModel().getValueAt(i,1).toString())){
								JOptionPane.showMessageDialog(getVentana(), "La consulta ya fue cerrada", "Mensaje",
					                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
								cer=true;
							}
							else{
							q.privilegios();
							desktopPane.add(ven.getVentana());
							ven.setVisible(true);
							}
							found = 1;
						}
					}
					if(found == 0){
						CrearConsulta ven = new CrearConsulta(desktopPane.getWidth(), desktopPane.getHeight(),numhis,true);
						GestorCrearConsulta q = new GestorCrearConsulta(ven, autenticacion, desktopPane);
						ven.setGestor(q);
						String ret = q.llenarPrimera(numhis);
						q.llenarUltima(numhis, ret);
						q.persona(numhis);
						q.privilegios();
						desktopPane.add(ven.getVentana());
						ven.setVisible(true);
					}
					if(chk_cerrar.isSelected() && !cer){
						ventana.dispose();
					}
				}
			});
		}
		return nueva;
	}

	/**
	 * This method initializes ver	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVer() {
		if (ver == null) {
			ver = new JButton();
			ver.setBounds(new Rectangle(345, 220, 60, 60));
			ver.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image024.png")));
			ver.setBackground(Color.white);
			ver.setToolTipText("Ver Consulta");
			ver.setOpaque(true);
			ver.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					int found = 0;
					for(int i=0;i<tabla.table.getModel().getRowCount();i++){
						if(tabla.table.getModel().getValueAt(i,3).toString().compareTo("true")==0){
							ConsultaDetallada cons = new ConsultaDetallada(desktopPane.getWidth(), desktopPane.getHeight(),numhis);
							GestorConsultaDetallada ges = new GestorConsultaDetallada(cons,autenticacion,numhis,tabla.table.getModel().getValueAt(i,0).toString(),tabla.table.getModel().getValueAt(i,1).toString());
							cons.setGestor(ges);
							desktopPane.add(cons.getVentana());
							cons.setVisible(true);
							found = 1;
						}
					}
					if(found == 1 && chk_cerrar.isSelected()){
						ventana.dispose();
					}
				}
			});
		}
		return ver;
	}

	/**
	 * This method initializes chk_cerrar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChk_cerrar() {
		if (chk_cerrar == null) {
			chk_cerrar = new JCheckBox();
			chk_cerrar.setBounds(new Rectangle(14, 259, 178, 21));
			chk_cerrar.setSelected(true);
			chk_cerrar.setText("Cerrar automaticamente");
		}
		return chk_cerrar;
	}

}
