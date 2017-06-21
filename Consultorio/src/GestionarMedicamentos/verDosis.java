package GestionarMedicamentos;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import Entidades.dosis;
import Utilitario.Autenticacion;

public class verDosis extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTextArea t_dosis = null;
	private JLabel lbl_1 = null;
	private JButton btn_guardar = null;
	private JRadioButton chk_si = null;
	private JRadioButton chk_no = null;
	private int idDosis;
	private GestorEditarMedicamentos gestor;
	private Autenticacion autenticacion;
	private int idPM;
	
	/**
	 * This is the default constructor
	 */
	
	public verDosis(GestorEditarMedicamentos g, Autenticacion a, int id) {
		super();
		gestor = g;
		autenticacion = a;
		idDosis = -1;
		idPM = id;
		initialize();
	}

	public int getIdDosis() {
		return idDosis;
	}

	public void setTexto(int id, String desc, boolean val){
		idDosis = id;
		t_dosis.setText(desc);
		if(val){
			chk_si.setSelected(true);
			chk_no.setSelected(false);
		}
		else{
			chk_si.setSelected(false);
			chk_no.setSelected(true);
		}
	}
	
	public void guardar(){
		if(idDosis == -1){
			dosis d = new dosis(autenticacion);
			d.buscarNuevoID();
			d.setDescripcion(t_dosis.getText());
			if(chk_si.isSelected())
				d.setValida("s");
			else
				d.setValida("n");
			d.setMdna_id(idPM);
			String ret = d.guardar();
			if(ret.compareToIgnoreCase("Ok")==0){
				gestor.getVentana().mostrarMensaje("Registro exitoso", gestor.getVentana().MENSAJE);
				int index=gestor.getVentana().getTablaPresMed().getSelectedRow();
				if(index>=0){
					gestor.verDosis(index);
				}
			}
			else{
				gestor.getVentana().mostrarMensaje("No se pudo guardar la dosis", gestor.getVentana().ERROR);
			}
		}
		else{
			dosis d = new dosis(autenticacion);
			d.setId(idDosis);
			d.setDescripcion(t_dosis.getText());
			if(chk_si.isSelected())
				d.setValida("s");
			else
				d.setValida("n");
			d.setMdna_id(idPM);
			String ret = d.actualizar();
			if(ret.compareToIgnoreCase("Ok")==0){
				gestor.getVentana().mostrarMensaje("Actualización exitosa", gestor.getVentana().MENSAJE);
			}
			else{
				gestor.getVentana().mostrarMensaje("No se pudo actualizar la dosis", gestor.getVentana().ERROR);
			}
		}
	}

	public void setIdDosis(int idDosis) {
		this.idDosis = idDosis;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lbl_1 = new JLabel();
		lbl_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_1.setLocation(new Point(19, 74));
		lbl_1.setSize(new Dimension(50, 20));
		lbl_1.setText("Válida:");
		this.setSize(270, 110);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		this.add(getJScrollPane(), null);
		this.add(lbl_1, null);
		this.add(getBtn_guardar(), null);
		this.add(getChk_si(), null);
		this.add(getChk_no(), null);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(12, 12, 244, 59));
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setViewportView(getT_dosis());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes t_dosis	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getT_dosis() {
		if (t_dosis == null) {
			t_dosis = new JTextArea();
			t_dosis.setLineWrap(true);
		}
		return t_dosis;
	}

	/**
	 * This method initializes btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_guardar() {
		if (btn_guardar == null) {
			btn_guardar = new JButton();
			btn_guardar.setLocation(new Point(224, 73));
			btn_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			btn_guardar.setSize(new Dimension(30, 30));
			btn_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_dosis.getText().length()>0){
						guardar();
					}
					else{
						gestor.getVentana().mostrarMensaje("El campo esta vacío", gestor.getVentana().ERROR);
					}
				}
			});
		}
		return btn_guardar;
	}

	/**
	 * This method initializes chk_si	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_si() {
		if (chk_si == null) {
			chk_si = new JRadioButton();
			chk_si.setLocation(new Point(75, 74));
			chk_si.setText("Si");
			chk_si.setSelected(true);
			chk_si.setSize(new Dimension(50, 20));
			chk_si.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_si.setSelected(true);
					chk_no.setSelected(false);
				}
			});
		}
		return chk_si;
	}

	/**
	 * This method initializes chk_no	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getChk_no() {
		if (chk_no == null) {
			chk_no = new JRadioButton();
			chk_no.setLocation(new Point(128, 74));
			chk_no.setText("No");
			chk_no.setSize(new Dimension(50, 20));
			chk_no.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					chk_si.setSelected(false);
					chk_no.setSelected(true);
				}
			});
		}
		return chk_no;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
