package GestionarInventario;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import Entidades.historial_inventarios;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.Font;

public class tabla_eventos {

	private JInternalFrame jInternalFrame = null;  //  @jve:decl-index=0:visual-constraint="224,20"
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	public JLabel producto = null;
	private JScrollPane jScrollPane = null;
	private JTable tabla = null;
	private JButton cerrar = null;
	private Modelo_Tabla modelo;
	private historial_inventarios historial;

	public tabla_eventos(JDesktopPane panel, int id, Autenticacion a,String pro){
		historial = new historial_inventarios(a);
		panel.add(getJInternalFrame());
		int x = Math.max(0, (panel.getWidth() - jInternalFrame.getWidth()-15) / 2);
        int y = Math.max(0, (panel.getHeight() - jInternalFrame.getHeight() -70) / 2);
        jInternalFrame.setLocation(new Point(x, y));
        jInternalFrame.setVisible(true);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(15*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(25*tabla.getWidth()/100);
        if(id == 0){
        	producto.setText(pro);
        	cargar();
        }
        else{
        	producto.setText(pro);
        	cargar(id);
        }
	}
	
	/**
	 * This method initializes jInternalFrame	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getJInternalFrame() {
		if (jInternalFrame == null) {
			jInternalFrame = new JInternalFrame();
			jInternalFrame.setSize(new Dimension(452, 332));
			jInternalFrame.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/kthememgr.png")));
			jInternalFrame.setTitle("Eventos");
			jInternalFrame.setClosable(true);
			jInternalFrame.setContentPane(getJContentPane());
		}
		return jInternalFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			producto = new JLabel();
			producto.setBounds(new Rectangle(19, 32, 254, 20));
			producto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			producto.setText("General");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 7, 59, 20));
			jLabel.setText("Producto:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(producto, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getCerrar(), null);
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
			jScrollPane.setBounds(new Rectangle(21, 58, 404, 189));
			jScrollPane.setViewportView(getTabla());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			tabla.setAutoCreateColumnsFromModel(true);
			tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			modelo = new Modelo_Tabla(null,
					new String [] {"Nº","Evento", "Usuario"});
			modelo.setRowCount(0);
			tabla.setModel(modelo);
			tabla.getTableHeader().setReorderingAllowed(false);
		}
		tabla.getColumnModel().getColumn(0).setPreferredWidth(15*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60*tabla.getWidth()/100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(25*tabla.getWidth()/100);
		return tabla;
	}

	/**
	 * This method initializes cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCerrar() {
		if (cerrar == null) {
			cerrar = new JButton();
			cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			cerrar.setMnemonic(KeyEvent.VK_C);
			cerrar.setToolTipText("Cerrar la ventana");
			cerrar.setLocation(new Point(171, 257));
			cerrar.setSize(new Dimension(109, 30));
			cerrar.setText("Cerrar");
			cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jInternalFrame.dispose();
				}
			});
		}
		return cerrar;
	}
	
	public void cargar(){
		int cont = 0;
		ResultSet res = historial.cargar();
		modelo.removeAllRows();
		try{
			while(res.next()){
				modelo.setRowCount(cont+1);
				tabla.setValueAt(String.valueOf(cont+1), cont, 0);
				tabla.setValueAt(res.getString("observacion"), cont, 1);
				tabla.setValueAt(res.getString("nombre"), cont, 2);
				cont++;
			}
		}catch (Exception e) {
		}
	}
	
	public void cargar(int id){
		int cont = 0;
		ResultSet res = historial.cargar(id);
		modelo.removeAllRows();
		try{
			while(res.next()){
				modelo.setRowCount(cont+1);
				tabla.setValueAt(String.valueOf(cont+1), cont, 0);
				tabla.setValueAt(res.getString("observacion"), cont, 1);
				tabla.setValueAt(res.getString("nombre"), cont, 2);
				cont++;
			}
		}catch (Exception e) {
		}
	}

}
