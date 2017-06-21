package GestionarRecetas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class jPanelMedicamento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JCheckBox jCheckBox = null;
	private JCheckBox c_imprimir = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JLabel l_texto = null;	
	private int ID_dosis;
	private int posy;
	/**
	 * This is the default constructor
	 */
	
	public jPanelMedicamento() {
		super();
		initialize();
		this.setVisible(true);
		this.setBackground(Color.WHITE);
	}
	
	public void setposY(int Poy){
				this.posy = Poy;
				this.setLocation(new Point(0,posy));
	}
	

	public int getPosy() {
		return posy;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	///get
	public int getIdDosis(){
		return ID_dosis;
	}
	public String getNameMedicina(){
		return l_texto.getText();
	}
	public void setNameMedicina(String name){
		l_texto.setText(name);
		//this.setBorder(BorderFactory.createTitledBorder(null, name, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
	}
	public String getTexArea(){
		return this.jTextArea.getText();
	}
	public void setTexArea(String t){
	jTextArea.setText(t);
	this.repaint();
	///jTextArea.repaint();
	}
	public boolean getImprimir(){
		return c_imprimir.isSelected();
	}
	public boolean getEliminar(){
		return jCheckBox.isSelected();
	}
	////GET
	private void initialize() {
		l_texto = new JLabel();
		l_texto.setBounds(new Rectangle(15, 19, 328, 16));
		l_texto.setText("Dosis:");
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SystemColor.activeCaptionText, 5), " ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		this.setBounds(new Rectangle(0, 0, 360, 135));
		this.add(getJCheckBox(), null);
		this.add(getC_imprimir(), null);
		this.add(getJScrollPane(), null);
		this.add(l_texto, null);
		this.repaint();
	}

	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setBounds(new Rectangle(211, 103, 97, 21));
			jCheckBox.setText("Eliminar");
			jCheckBox.setBackground(Color.WHITE);
			jCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(jCheckBox.isSelected()){
						setBackground(Color.ORANGE);
						jCheckBox.setBackground(Color.ORANGE);
						c_imprimir.setBackground(Color.ORANGE);
					}else{
						setBackground(Color.WHITE);
						jCheckBox.setBackground(Color.WHITE);
						c_imprimir.setBackground(Color.WHITE);
					}
					
					///System.out.println("stateChanged()"); // TODO Auto-generated Event stub stateChanged()
				}
			});
		}
		return jCheckBox;
	}

	/**
	 * This method initializes c_imprimir	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getC_imprimir() {
		if (c_imprimir == null) {
			c_imprimir = new JCheckBox();
			c_imprimir.setBounds(new Rectangle(97, 103, 98, 21));
			c_imprimir.setText("Imprimir");
			c_imprimir.setSelected(true);
			c_imprimir.setBackground(Color.WHITE);
		}
		return c_imprimir;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 38, 327, 57));
			jScrollPane.setAutoscrolls(true);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setText("");
			jTextArea.setLineWrap(true);
			jTextArea.setDoubleBuffered(true);			
		}
		return jTextArea;
	}

	public int getID_dosis() {
		return ID_dosis;
	}

	public void setID_dosis(int id_dosis) {
		ID_dosis = id_dosis;
	}

}  //  @jve:decl-index=0:visual-constraint="16,66"
