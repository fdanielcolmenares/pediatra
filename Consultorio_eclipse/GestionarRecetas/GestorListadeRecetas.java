package GestionarRecetas;

import java.awt.Point;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import ConexionBD.Conexion;
import GestionImpresiones.ImprimirReceta;
import Utilitario.Autenticacion;

public class GestorListadeRecetas extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton b_eliminar = null;
	private JButton b_agregar = null;
	private GestorPanelMedicamento gestor;  //  @jve:decl-index=0:
	private JScrollPane jScrollPane = null;
	private JPanel jPanel = null;
	private int ID_consulta;
	private Autenticacion autenticacion;
	private int aument = 0;
	private JButton b_guardar = null;
	private JButton b_pdf = null;
	private JDesktopPane pane;
	private MostrarMedicamentos medicamentos;  //  @jve:decl-index=0:
	private GestorMostrarMedicamentos gestorListadeRecetas;//  @jve:decl-index=0:
	private Conexion conexion;  //  @jve:decl-index=0:
	private String edad;
	private String fecha;
	private String nombre;  //  @jve:decl-index=0:
	private JInternalFrame frame;
	
	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GestorListadeRecetas(int ID_consulta, String ed, String fec, String name, Autenticacion autenticacion, JDesktopPane pane){
		//public GestorListadeRecetas(){
		super();
		this.autenticacion = autenticacion;
		this.ID_consulta = ID_consulta;
		edad = ed;
		fecha = fec;
		nombre = name;
		gestor = new GestorPanelMedicamento();
		this.pane = pane;		
		initialize();		
		this.setDoubleBuffered(true);
		pane.add(this);
		int x = Math.max(0, (pane.getWidth() - this.getWidth()) / 2);
        int y = Math.max(0, (pane.getHeight() - this.getHeight()-70) / 2);
        this.setLocation(new Point(x, y));
        this.setVisible(true);
        try{
        	this.setSelected(true);
        }
        catch(Exception e){
        	
        }
        /*prueba
        gestor.loadMedicinas(19428, autenticacion);
        this.Actualizar();
        /*pruebas*/ 
         
        
        frame = this;
	}
	
	public GestorListadeRecetas(Autenticacion autenticacion, JDesktopPane pane){
		super();
		this.autenticacion = autenticacion;
		gestor = new GestorPanelMedicamento();
		this.pane = pane;		
		initialize();		
		this.setDoubleBuffered(true);
		pane.add(this);
		int x = Math.max(0, (pane.getWidth() - this.getWidth()) / 2);
        int y = Math.max(0, (pane.getHeight() - this.getHeight()-70) / 2);
        this.setLocation(new Point(x, y));
        this.setVisible(true);
        try{
        	this.setSelected(true);
        }
        catch(Exception e){
        	
        }
    
        
        frame = this;
		
	}
	
	public void cargar(int idConsulta){
		ID_consulta = idConsulta;
		gestor.loadMedicinas(idConsulta, autenticacion);
        this.Actualizar();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(425, 400);
		this.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image060.png")));
		this.setTitle("Receta de Medicamentos");
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		medicamentos = new MostrarMedicamentos(pane.getWidth(),pane.getHeight());
        gestorListadeRecetas = new GestorMostrarMedicamentos(medicamentos, autenticacion,this);
        medicamentos.setGestor(gestorListadeRecetas);
        pane.add(medicamentos.getVentana());
       
	}

	public void setHabilitacionBotones(boolean active){
				if(gestor.getTamano() == 0){
					b_eliminar.setEnabled(true);
					b_agregar.setEnabled(true);
					b_guardar.setEnabled(true);
				}
				else{
					b_eliminar.setEnabled(false);
					b_agregar.setEnabled(false);
					b_guardar.setEnabled(false);
				}
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
			jContentPane.add(getB_eliminar(), null);
			jContentPane.add(getB_agregar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getB_guardar(), null);
			jContentPane.add(getB_pdf(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	

	/**
	 * This method initializes b_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_eliminar() {
		if (b_eliminar == null) {
			b_eliminar = new JButton();
			b_eliminar.setBounds(new Rectangle(137, 296, 60, 60));
			b_eliminar.setToolTipText("Eliminar marcados");
			b_eliminar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			b_eliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.RemovePanel(jPanel);
					aument = 0;					
					Actualizar();
					jPanel.setPreferredSize(new Dimension(350,aument));
					/*int x = getX();
					setLocation(x+1, getY());
					setLocation(x, getY());*/
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return b_eliminar;
	}

	/**
	 * This method initializes b_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_agregar() {
		if (b_agregar == null) {
			b_agregar = new JButton();
			b_agregar.setBounds(new Rectangle(68, 296, 60, 60));
			b_agregar.setToolTipText("Agregar Medicamento");
			b_agregar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image064.png")));
			b_agregar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					///gestor.AddPanel(iden+" - Aspirina","Tomar una vez al dia",0);
					//iden++;
					//jPanel.setPreferredSize(new Dimension(350,0));
					///Actualizar();
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
						medicamentos.setVisible(true);
						gestorListadeRecetas.mostrarMedicamentos("1=1");
				}
			});
		}
		return b_agregar;
	}
	
	
	public void addNewPanel(String m, String p,String d, int ID){
		
		for (int i = 0; i < gestor.getTamano(); i++) {
				if(gestor.getIdDosis(i)==ID){
						return;
					}
		}
			gestor.AddPanel(m+"-"+p,d, ID);
			Actualizar();
		
	}
	public void Actualizar(){		
		jContentPane.remove(jScrollPane);
		jScrollPane.setViewportView(getJPanel());
		jScrollPane.getViewport().setView(getJPanel());
		jContentPane.add(getJScrollPane(), null);
		aument = 0;
		//initialize();
	//	jPanel.removeAll();
		///jPanel.setBounds(0, 0, 350, 135);
		
		for (int i = 0; i < gestor.getTamano(); i++) {
			jPanel.add(gestor.getPanel(i));
			jPanel.setPreferredSize(new Dimension(350,aument));			
			aument=gestor.getTamano()*135;
			///System.out.println(gestor.getPanel(i).getLocation().y);
		}
		///System.out.println("-----------------");
		gestor.RemovePanel(jPanel);
		int x = this.getX();
		this.setLocation(x+1, this.getY());
		this.setLocation(x, this.getY());
		jPanel.repaint();
				
		//System.out.println(jPanel.getPreferredSize().height);
		//System.out.println();
		
		///System.out.println("-----------------");
		///jScrollPane.repaint();
		///jPanel.repaint();
		//this.repaint();
		///jScrollPane.repaint();
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(24, 16, 371, 270));
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
			jScrollPane.getVerticalScrollBar().setAutoscrolls(true);
		//	jScrollPane.getVerticalScrollBar().
			///jScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//jScrollPane.setViewportView(getJPanel());
			jScrollPane.setViewportView(getJPanel());
			jScrollPane.getViewport().setView(getJPanel());
			this.jScrollPane.setAutoscrolls(true);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(350,0));
			
			
		}
		return jPanel;
	}

	/**
	 * This method initializes b_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_guardar() {
		if (b_guardar == null) {
			b_guardar = new JButton();
			b_guardar.setBounds(new Rectangle(206, 295, 60, 60));
			b_guardar.setToolTipText("Guardar");
			b_guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image021.png")));
			b_guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveVacunas();
					
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return b_guardar;
	}

	public void saveVacunas(){
	int op = JOptionPane.showConfirmDialog(this,"Esta seguro de Guardar estos medicamentos?","Mensaje",JOptionPane.YES_NO_OPTION);
	
	if(op==0&&gestor.getTamano()>0){
		String query = null;
				conexion = new Conexion(autenticacion);
				conexion.conectar();
				query = "DELETE FROM medicinas_consulta WHERE csta_id="+ID_consulta;
				
				try {
					conexion.actualizar(query);	
					
				for (int i = 0; i < gestor.getTamano(); i++) {
					query = "INSERT INTO medicinas_consulta VALUES("+ID_consulta+","+gestor.getIdDosis(i)+")";
					conexion.actualizar(query);
				}	
				conexion.desconectar();	
				
				///b_guardar.setEnabled(false);
					
				} catch (Exception e) {
					System.out.println("Error a Insertar");
				}
				
	}
	}
	/**
	 * This method initializes b_pdf	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_pdf() {
		if (b_pdf == null) {
			b_pdf = new JButton();
			b_pdf.setBounds(new Rectangle(271, 296, 60, 60));
			b_pdf.setToolTipText("Generar Receta");
			b_pdf.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image062.png")));
			b_pdf.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(gestor.lista.size()>0){
					ImprimirReceta a = new ImprimirReceta(nombre, edad, fecha, frame);
					a.GenerarReceta(gestor);
					}else{
						JOptionPane.showMessageDialog(frame, "No hay datos para imprimir.", "Atencion!!!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return b_pdf;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,0"
