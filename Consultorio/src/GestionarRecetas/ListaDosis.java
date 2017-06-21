package GestionarRecetas;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Utilitario.Autenticacion;

import Entidades.dosis;

import java.awt.Point;

public class ListaDosis extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTextArea t_dosis = null;
	private JButton jButton = null;
	private Autenticacion autenticacion;
	private int idDosis;
	private GestorMostrarMedicamentos gestor;
	private String medicamento;  //  @jve:decl-index=0:
	private String presentacion;  //  @jve:decl-index=0:
	private int idPM;//IdPesentacionesMedicina
	/**
	 * This is the default constructor
	 */
	public ListaDosis(String m, String p, int dosisINT, String dosisST, GestorMostrarMedicamentos g, int pm) {
		super();
		initialize();
		gestor = g;
		medicamento = m;
		presentacion = p;
		this.idDosis = dosisINT;
		t_dosis.setText(dosisST);
		idPM = pm;
	}
	
	public void setAutenticacion(Autenticacion a){
		autenticacion = a;
	}
	
	public void setDosis(String dosisST){
		t_dosis.setText(dosisST);
	}
	
	public void editarTexto(boolean aFlag){
		t_dosis.setEditable(aFlag);
	}
	
	public void setIdDosis(int dosisINT){
		idDosis = dosisINT;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	public int getIdPM() {
		return idPM;
	}

	public void setIdPM(int idPM) {
		this.idPM = idPM;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(273, 95);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		this.setPreferredSize(new Dimension(280, 95));
		this.add(getJScrollPane(), null);
		this.add(getJButton(), null);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(243, 49));
			jScrollPane.setLocation(new Point(15, 10));
			jScrollPane.setViewportView(getT_dosis());
		}
		return jScrollPane;
	}
	
	public void repintar(){
		t_dosis.repaint();
		jScrollPane.repaint();
		this.repaint();
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			jButton.setSize(new Dimension(25, 25));
			jButton.setLocation(new Point(15, 62));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t_dosis.getText().length()>0){
						if(idDosis == 0){
							dosis d = new dosis(autenticacion);
							d.buscarNuevoID();
							d.setDescripcion(t_dosis.getText());
							d.setValida("s");
							d.setMdna_id(idPM);
							d.guardar();
							idDosis = d.getId();
						}
						gestor.retornarDatos(medicamento, presentacion, idDosis, t_dosis.getText());
					}
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
