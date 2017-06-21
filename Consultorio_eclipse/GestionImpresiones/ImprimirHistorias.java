package GestionImpresiones;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Utilitario.Autenticacion;
import java.awt.event.KeyEvent;

public class ImprimirHistorias {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="190,39"
	private JPanel jContentPane = null;
	private JPanel p_intervalo = null;
	private JRadioButton chk_todo = null;
	private JRadioButton chk_intervalo = null;
	private JTextField t_intervalo = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JButton btn_generar = null;
	private JButton btn_cancelar = null;
	private JCheckBox chk_cerrar = null;
	private Autenticacion auten;

	public ImprimirHistorias(Autenticacion auten, int w, int h){
					this.auten = auten;
					getVentana();
					int x = Math.max(0, (w - ventana.getWidth()-15) / 2);
			        int y = Math.max(0, (h - ventana.getHeight() -70) / 2);
			        ventana.setLocation(new Point(x, y));
	}
	
	public ImprimirHistorias getObjeto(){
				return this;
	}
	public void setVisible(boolean visible){
		ventana.setVisible(visible);
	}
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(331, 278));
			ventana.setTitle("Generar reporte de historias");
			ventana.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image055.png")));
			ventana.setClosable(true);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getP_intervalo(), null);
			jContentPane.add(getBtn_generar(), null);
			jContentPane.add(getBtn_cancelar(), null);
			jContentPane.add(getChk_cerrar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes p_intervalo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP_intervalo() {
		if (p_intervalo == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 14));
			jLabel1.setSize(new Dimension(172, 18));
			jLabel1.setLocation(new Point(63, 106));
			jLabel1.setText("o un intervalo. Ej: 1-250");
			jLabel = new JLabel();
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			jLabel.setLocation(new Point(63, 88));
			jLabel.setSize(new Dimension(139, 18));
			jLabel.setText("Escriba un número");
			p_intervalo = new JPanel();
			p_intervalo.setLayout(null);
			p_intervalo.setBounds(new Rectangle(26, 20, 264, 146));
			p_intervalo.setBorder(BorderFactory.createTitledBorder(null, "Intervalo de historias", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 14), Color.blue));
			p_intervalo.add(getChk_todo(), null);
			p_intervalo.add(getChk_intervalo(), null);
			p_intervalo.add(getT_intervalo(), null);
			p_intervalo.add(jLabel, null);
			p_intervalo.add(jLabel1, null);
		}
		return p_intervalo;
	}

	/**
	 * This method initializes chk_todo	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_todo() {
		if (chk_todo == null) {
			chk_todo = new JRadioButton();
			chk_todo.setBounds(new Rectangle(14, 29, 177, 21));
			chk_todo.setSelected(true);
			chk_todo.setFont(new Font("Dialog", Font.PLAIN, 18));
			chk_todo.setText("Todo");
			chk_todo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_todo.setSelected(true);
					chk_intervalo.setSelected(false);
					t_intervalo.setEnabled(false);
				}
			});
		}
		return chk_todo;
	}

	/**
	 * This method initializes chk_intervalo	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_intervalo() {
		if (chk_intervalo == null) {
			chk_intervalo = new JRadioButton();
			chk_intervalo.setBounds(new Rectangle(19, 60, 103, 21));
			chk_intervalo.setFont(new Font("Dialog", Font.PLAIN, 18));
			chk_intervalo.setText("Historias");
			chk_intervalo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_todo.setSelected(false);
					chk_intervalo.setSelected(true);
					t_intervalo.setEnabled(true);
				}
			});
		}
		return chk_intervalo;
	}

	/**
	 * This method initializes t_intervalo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_intervalo() {
		if (t_intervalo == null) {
			t_intervalo = new JTextField();
			t_intervalo.setLocation(new Point(125, 60));
			t_intervalo.setFont(new Font("Dialog", Font.PLAIN, 14));
			t_intervalo.setEnabled(false);
			t_intervalo.setSize(new Dimension(136, 20));
			t_intervalo.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(t_intervalo.getText().length()>0 && e.getKeyCode() == 10){
						PrintHistoriasAplication a = new PrintHistoriasAplication(auten,ventana);	
						if(chk_todo.isSelected()){
								a.setQuery("1");
								a.GeneraReporte(chk_cerrar.isSelected());
						}
						if(chk_intervalo.isSelected()){
							String q[] = t_intervalo.getText().toString().split("-");
							if(q!=null&&q.length==2){
							a.setQuery("id BETWEEN "+q[0]+" AND "+q[1]);
							a.GeneraReporte(chk_cerrar.isSelected());
							}else{
								JOptionPane.showMessageDialog(ventana, "Error al introducir rango de datos...!!!");
							}
						}
			
			
					}
				}
			});
		}
		return t_intervalo;
	}

	/**
	 * This method initializes btn_generar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_generar() {
		if (btn_generar == null) {
			btn_generar = new JButton();
			btn_generar.setText("Generar PDF");
			btn_generar.setLocation(new Point(18, 180));
			btn_generar.setSize(new Dimension(140, 30));
			btn_generar.setMnemonic(KeyEvent.VK_G);
			btn_generar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image055.png")));
			btn_generar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					PrintHistoriasAplication a = new PrintHistoriasAplication(auten,ventana);	
							if(chk_todo.isSelected()){
									a.setQuery("1");
									a.GeneraReporte(chk_cerrar.isSelected());
							}
							if(chk_intervalo.isSelected()){
								String q[] = t_intervalo.getText().toString().split("-");
								if(q!=null&&q.length==2){
								a.setQuery("id BETWEEN "+q[0]+" AND "+q[1]);
								a.GeneraReporte(chk_cerrar.isSelected());
								}else{
									JOptionPane.showMessageDialog(ventana, "Error al introducir rango de datos...!!!");
								}
							}
				
				}
			});
		}
		return btn_generar;
	}

	/**
	 * This method initializes btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_cancelar() {
		if (btn_cancelar == null) {
			btn_cancelar = new JButton();
			btn_cancelar.setLocation(new Point(167, 180));
			btn_cancelar.setText("Cancelar");
			btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			btn_cancelar.setMnemonic(KeyEvent.VK_C);
			btn_cancelar.setSize(new Dimension(140, 30));
			btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
							ventana.dispose();
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btn_cancelar;
	}

	/**
	 * This method initializes chk_cerrar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChk_cerrar() {
		if (chk_cerrar == null) {
			chk_cerrar = new JCheckBox();
			chk_cerrar.setBounds(new Rectangle(11, 220, 179, 21));
			chk_cerrar.setSelected(true);
			chk_cerrar.setText("Cerrar automaticamente");
		}
		return chk_cerrar;
	}

}
