package GestionarFacturas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DialogPagos {

	private JDialog ventana = null;  //  @jve:decl-index=0:visual-constraint="142,28"
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField t_monto = null;
	private JButton btn_aceptar = null;
	private JButton btn_cerrar = null;
	private JComboBox t_forma = null;
	private List listaTipoPago;
	private Autenticacion autenticacion;
	private GestorFactura gestorFactura;
	public DialogPagos(Autenticacion a, GestorFactura g){
		autenticacion = a;
		gestorFactura = g;
		getVentana();
		listaTipoPago = new ArrayList();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLocation((int) (d.getWidth() / 2 - ventana.getWidth() / 2), (int) (d.getHeight() / 2 - ventana.getHeight() / 2));
        actualizarFormasPago();
	}
	
	public void setVisible(boolean param){
		ventana.setVisible(param);
		if(param){
			t_monto.requestFocus();
		}
	}
	
	public void dispose(){
		//this.dispose();
	}
	
	public void actualizarFormasPago(){
		t_forma.removeAllItems();
		listaTipoPago.clear();
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql = "SELECT id, descripcion FROM tipo_pago ORDER BY id ASC";
			java.sql.ResultSet res = con.consultar(sql);
			try {
				while(res!= null && res.next()){
					t_forma.addItem(res.getString("descripcion").toString());
					listaTipoPago.add(res.getString("id").toString());
				}
			}
			catch (Exception e) {
			}
			con.desconectar();
		}
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getVentana() {
		if (ventana == null) {
			ventana = new JDialog();
			ventana.setResizable(false);
			ventana.setSize(new Dimension(297, 163));
			ventana.setTitle("Ingresar Pago");
			ventana.setPreferredSize(new Dimension(310, 163));
			ventana.setModal(true);
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
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setSize(new Dimension(100, 20));
			jLabel1.setLocation(new Point(2, 50));
			jLabel1.setText("Monto:");
			jLabel = new JLabel();
			jLabel.setText("Forma de Pago:");
			jLabel.setSize(new Dimension(100, 20));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setLocation(new Point(2, 20));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getT_monto(), null);
			jContentPane.add(getBtn_aceptar(), null);
			jContentPane.add(getBtn_cerrar(), null);
			jContentPane.add(getT_forma(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes t_monto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_monto() {
		if (t_monto == null) {
			t_monto = new JTextField();
			t_monto.setLocation(new Point(106, 50));
			t_monto.setSize(new Dimension(124, 20));
			t_monto.addKeyListener(new java.awt.event.KeyAdapter() {
                //@Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == 10) {
                    	try{
    						Float.parseFloat(t_monto.getText());
    						if(t_forma.getItemCount() == 0){
    							JOptionPane.showMessageDialog(ventana, "Debe seleccionar una forma de pago", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
    						}
    						else{
    							String fecha = UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY);
    							gestorFactura.agregarPago(fecha, t_monto.getText(), t_forma.getSelectedItem().toString(), Integer.parseInt(listaTipoPago.get(t_forma.getSelectedIndex()).toString()));
    							ventana.setVisible(false);
    							ventana.dispose();
    						}
    					}
    					catch(Exception ex){
    						JOptionPane.showMessageDialog(ventana, "La cantidad ingresada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
    					}
                    }
                }
            });
		}
		return t_monto;
	}

	/**
	 * This method initializes btn_aceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_aceptar() {
		if (btn_aceptar == null) {
			btn_aceptar = new JButton();
			btn_aceptar.setLocation(new Point(21, 87));
			btn_aceptar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image042.png")));
			btn_aceptar.setText("Aceptar");
			btn_aceptar.setSize(new Dimension(115, 30));
			btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						Float.parseFloat(t_monto.getText());
						if(t_forma.getItemCount() == 0){
							JOptionPane.showMessageDialog(ventana, "Debe seleccionar una forma de pago", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
						}
						else{
							String fecha = UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY);
							gestorFactura.agregarPago(fecha, t_monto.getText(), t_forma.getSelectedItem().toString(), Integer.parseInt(listaTipoPago.get(t_forma.getSelectedIndex()).toString()));
							ventana.setVisible(false);
							ventana.dispose();
						}
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(ventana, "La cantidad ingresada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
					}
				}
			});
		}
		return btn_aceptar;
	}

	/**
	 * This method initializes btn_cerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cerrar() {
		if (btn_cerrar == null) {
			btn_cerrar = new JButton();
			btn_cerrar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cerrar.setLocation(new Point(158, 87));
			btn_cerrar.setSize(new Dimension(115, 30));
			btn_cerrar.setText("Cerrar");
			btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.setVisible(false);
				}
			});
		}
		return btn_cerrar;
	}

	/**
	 * This method initializes t_forma	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getT_forma() {
		if (t_forma == null) {
			t_forma = new JComboBox();
			t_forma.setLocation(new Point(106, 20));
			t_forma.setSize(new Dimension(149, 20));
		}
		return t_forma;
	}

}
