package GestionarConsultas;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Rectangle;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import Utilitario.Autenticacion;
import Utilitario.Modelo_Tabla;
import Utilitario.Render;
import Utilitario.UtilFechas;
import VentanaPrincipal.VentanaPrincipal;
import ConexionBD.Conexion;
import Entidades.Historias;
import GestionarHistorias.BuscarHistorias;
import GestionarHistorias.CrearHistoria;
import GestionarHistorias.GestorCrearHistoria;

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.Dimension;

public class ListaPacientesConsul extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel PanelLista = null;
	private JLabel fecha = null;
	private JScrollPane jScrollPane = null;
	private JTable jTablePacientes = null;
	private JPanel PanelAcciones = null;
	private JButton AddPaciente = null;
	private JButton DeletePaciente = null;
	private JDesktopPane jDesktopPane= null;
	private VentanaPrincipal ventana=null;
	private Autenticacion autenticacion = null;
	private Modelo_Tabla modelTabla = null;
	private Historias historias = null;  //  @jve:decl-index=0:
	private Date date = null;
	private Conexion conexion;
	private String query = null;  //  @jve:decl-index=0:
	private JButton b_actualizar = null;
	private String last;
	
	/**
	 * This is the xxx default constructor
	 */
	public JInternalFrame getTHIS(){
		return this;
	}
	
	public ListaPacientesConsul(VentanaPrincipal ventana, Autenticacion autenticacion) {
		super();
		last = "";
		this.requestFocus();
		this.jDesktopPane = ventana.getDesktopPanel();
		this.ventana = ventana;
		this.autenticacion = autenticacion;		
		conexion = new Conexion(this.autenticacion);		
		historias = new Historias(autenticacion);
		date = new Date();	
		ClearPacientes();
		initialize();
		ClearPacientes();
		jTablePacientes.requestFocus();
        int x = Math.max(0, (jDesktopPane.getWidth() - this.getWidth()-15) / 2);
        int y = Math.max(0, (jDesktopPane.getHeight() - this.getHeight() -70) / 2);
        this.setLocation(new Point(x, y));
		//jDesktopPane.add(this);
       
	}
	
	public void ajustarTabla(){
		jTablePacientes.getColumnModel().getColumn(0).setPreferredWidth(15*jTablePacientes.getWidth()/100);
        jTablePacientes.getColumnModel().getColumn(1).setPreferredWidth(50*jTablePacientes.getWidth()/100);
        jTablePacientes.getColumnModel().getColumn(2).setPreferredWidth(35*jTablePacientes.getWidth()/100);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(522, 434);		
		this.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image095.png")));
		this.setTitle("Lista de Pacientes por Consulta");
		this.setContentPane(getJContentPane());		
		this.setClosable(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			fecha = new JLabel();
			fecha.setText("Fecha: ");
			fecha.setSize(new Dimension(238, 20));
			fecha.setFont(new Font("Dialog", Font.BOLD, 14));
			fecha.setLocation(new Point(14, 6));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPanelLista(), null);
			jContentPane.add(fecha, null);
			jContentPane.add(getPanelAcciones(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelLista	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelLista() {
		if (PanelLista == null) {
			PanelLista = new JPanel();
			PanelLista.setLayout(null);
			PanelLista.setBounds(new Rectangle(12, 30, 488, 305));
			PanelLista.setBorder(BorderFactory.createTitledBorder(null, "Lista de Pacientes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			PanelLista.add(getJScrollPane(), null);
		}
		return PanelLista;
	}

	/**
	 * This method initializes b_Down	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JInternalFrame getInternalFrame(){
		return this;		
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(12, 21, 463, 278));
			jScrollPane.setViewportView(getJTablePacientes());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTablePacientes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTablePacientes() {
		if (jTablePacientes == null) {
			jTablePacientes = new JTable();
			modelTabla = new Modelo_Tabla(null,new String[]{"Historia","Apellidos y Nombres","Estado"});
			modelTabla.setColumnEditable(2);
			
			Render render = new Render();
			jTablePacientes.setDefaultRenderer(Object.class,render);
			this.LoadTablaInicio();
			jTablePacientes.setModel(modelTabla);
			
			JComboBox opciones = new JComboBox();
			opciones.addItem("En espera");
			opciones.addItem("En consulta");
			opciones.addItem("Nebulización pre-consulta");
			opciones.addItem("Nebulización post-consulta");
			opciones.addItem("Retirado");
			opciones.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                selecciona();
	            }
	        });
			jTablePacientes.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(opciones));
			
			jTablePacientes.getTableHeader().setReorderingAllowed(false);
			jTablePacientes.setAutoCreateColumnsFromModel(true);
			ajustarTabla();
			
			jTablePacientes.setSelectionMode(0);
			jTablePacientes.setRowHeight(20);
			jTablePacientes.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			jTablePacientes.getTableHeader().setReorderingAllowed(false);
			
			jTablePacientes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount() == 2){
						int index = jTablePacientes.getSelectedRow();
						if(index >= 0){
							int id = Integer.parseInt(jTablePacientes.getValueAt(index, 0).toString());
							CrearHistoria v = new CrearHistoria(jDesktopPane);
					        GestorCrearHistoria g = new GestorCrearHistoria(v, autenticacion);
					        v.setGestor(g);
					        g.cargarHistoriaNumero(id);
					        jDesktopPane.add(v.getVentana());
					        v.setVisible(true);
						}
					}
				}
			});
		}
		//jTablePacientes.getColumnModel().getColumn(0).setPreferredWidth( (int)(20*jTablePacientes.getWidth()/100));
        //jTablePacientes.getColumnModel().getColumn(1).setPreferredWidth(65*jTablePacientes.getWidth()/100);
        //jTablePacientes.getColumnModel().getColumn(2).setPreferredWidth(15*jTablePacientes.getWidth()/100);
		return jTablePacientes;
	}

	/**
	 * This method initializes PanelAcciones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelAcciones() {
		if (PanelAcciones == null) {
			PanelAcciones = new JPanel();
			PanelAcciones.setLayout(null);
			PanelAcciones.setBorder(BorderFactory.createTitledBorder(null, "Acciones", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			PanelAcciones.setLocation(new Point(12, 337));
			PanelAcciones.setSize(new Dimension(488, 54));
			PanelAcciones.add(getAddPaciente(), null);
			PanelAcciones.add(getDeletePaciente(), null);
			PanelAcciones.add(getB_actualizar(), null);
		}
		return PanelAcciones;
	}

	/**
	 * This method initializes AddPaciente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddPaciente() {
		if (AddPaciente == null) {
			AddPaciente = new JButton("Agregar");
			AddPaciente.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image039.png")));
			AddPaciente.setSize(new Dimension(112, 30));
			AddPaciente.setLocation(new Point(25, 18));
			AddPaciente.setToolTipText("Agregar Paciente");
			AddPaciente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BuscarHistorias a = new BuscarHistorias(ventana.getVentana(),true);
					a.setAutenticacion(autenticacion);	
					a.ChangeOptionChange(true);
					a.setVisibleDialog(true);
					if(a.getIDTable()>0){
					historias.buscarHistoriaNumero(a.getIDTable(), true);
					String id = "000000"+String.valueOf(historias.getId());
					id = id.substring(id.length()-6, id.length());
					Object[] object = new Object[3];
					object[0] = id;
					object[1] = historias.getNombre();
					object[2] = "En espera";
					a.DialogDispose();
				if(!ValidarPaciente(id)){
					AddPacienteTablaAuxiliar(historias.getId());
					modelTabla.addRow(object);
				}else{
					JOptionPane.showMessageDialog(getTHIS(),"Este Paciente ya esta en la Consulta de hoy.!!!","Advertencia", JOptionPane.INFORMATION_MESSAGE,null);
				}///fin else
					ajustarTabla();
					}
					//System.out.println(a.getIDTable());						
				}	
			});
			AddPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode()==32){
						jTablePacientes.requestFocus();
					}
					//System.out.println("keyPressed()"); // 
				}
			});
		}
		return AddPaciente;
	}

	/**
	 * This method initializes DeletePaciente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeletePaciente() {
		if (DeletePaciente == null) {
			DeletePaciente = new JButton("Eliminar");
			DeletePaciente.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			DeletePaciente.setLocation(new Point(180, 18));
			DeletePaciente.setSize(new Dimension(115, 30));
			DeletePaciente.setToolTipText("Eliminar Paciente");
			DeletePaciente.addActionListener(new java.awt.event.ActionListener() {
					
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = jTablePacientes.getSelectedRow();
					if(index>=0){					
					ElminarPacienteTabla(Integer.parseInt(modelTabla.getValueAt(index,0).toString()));
					modelTabla.removeRow(index);
					ajustarTabla();
					}
					//System.out.println("actionPerformed()"); // 
				}
			});
		}
		return DeletePaciente;
	}

	public boolean ValidarPaciente(String referencia){
			for (int i=0;i<this.jTablePacientes.getRowCount();i++){
					if(this.modelTabla.getValueAt(i, 0).toString().compareToIgnoreCase(referencia)==0){
							return true;					
					}		}
				
		
		return false;
	}
	
	public void AddPacienteTablaAuxiliar(int id){						
			conexion.conectar();
			query = "SELECT IFNULL((SELECT MAX(id)+1 FROM consultas_hoy), 1)";
			java.sql.ResultSet res = conexion.consultar(query);
			int idCH = 1;
			try {
				if(res.next()){
					idCH = Integer.parseInt(res.getString(1).toString());
				}
			}
			catch (Exception e) {
			}
			
			query = "INSERT INTO consultas_hoy VALUES("+idCH+", "+id+",0,'"+UtilFechas.convertirFecha(date,UtilFechas.YYYY_MM_DD)+"')";
			//System.out.println(query);
			conexion.actualizar(query);
			conexion.desconectar();
	}
	
	public void LoadTablaInicio(){
		conexion.conectar();
		query = "SELECT id_htra,estado FROM consultas_hoy WHERE 1";
		ResultSet resultSet = conexion.consultar(query);
		modelTabla.removeAllRows();
		this.historias = new Historias(autenticacion);
	try {
		while(resultSet.next()){
			historias.buscarHistoriaNumero(Integer.parseInt(resultSet.getString(1)), true);
			String id = "000000"+String.valueOf(historias.getId());
			id = id.substring(id.length()-6, id.length());
			Object[] object = new Object[3];
			object[0] = id;
			object[1] = historias.getNombre();
				if(resultSet.getString(2).compareToIgnoreCase("0")==0){
					object[2] = "En espera";
				}else if(resultSet.getString(2).compareToIgnoreCase("1")==0){
					object[2] = "En consulta";
				}else if(resultSet.getString(2).compareToIgnoreCase("2")==0){
					object[2] = "Nebulización pre-consulta";					
				}
				else if(resultSet.getString(2).compareToIgnoreCase("3")==0){
					object[2] = "Nebulización post-consulta";					
				}
				else if(resultSet.getString(2).compareToIgnoreCase("4")==0){
					object[2] = "Retirado";					
				}
				
			modelTabla.addRow(object);
		}	
	} catch (Exception e) {	
		e.printStackTrace();
	}	
		conexion.desconectar();
		
	}
	
	private void ElminarPacienteTabla(int referencia){
		conexion.conectar();
		query = "DELETE FROM consultas_hoy WHERE id_htra="+referencia;
		conexion.actualizar(query);
		conexion.desconectar();		
	}
	
	public void ClearPacientes(){
		date = new Date();
		conexion.conectar();
		query = "DELETE FROM consultas_hoy WHERE fecha != '"+UtilFechas.convertirFecha(date, UtilFechas.YYYY_MM_DD)+"'";
		conexion.actualizar(query);
		//System.out.println(query);
		if(fecha!=null){
			fecha.setText("Fecha: "+UtilFechas.convertirFecha(date, UtilFechas.DD_MM_YYYY));
		}
		conexion.desconectar();
	}
	/**
	 * This method initializes b_actualizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_actualizar() {
		if (b_actualizar == null) {
			b_actualizar = new JButton();
			b_actualizar.setSize(new Dimension(125, 30));
			b_actualizar.setText("Actualizar");
			b_actualizar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image077.png")));
			b_actualizar.setPreferredSize(new Dimension(120, 40));
			b_actualizar.setLocation(new Point(335, 18));
			b_actualizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					LoadTablaInicio();
				}
			});
		}
		return b_actualizar;
	}
	
	public void selecciona(){
		int index = jTablePacientes.getSelectedRow();
		if(index>=0){
			String c = jTablePacientes.getValueAt(index, 2).toString();
			String ev = index+">>"+c;
			
			if(last.compareTo("")==0 || last.compareTo(ev)!=0){
				//System.out.println(index+">>"+c);
				int idH = Integer.parseInt(jTablePacientes.getValueAt(index, 0).toString());
				last = ev;
				cambiarEstado(idH, c);
			}
			
			//System.out.println(index+">>"+c);
		}
	}
	
	public void cambiarEstado(int idHtra, String txt){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			int ne = 0;
			if(txt.compareTo("En espera")==0)
				ne = 0;
			else if(txt.compareTo("En consulta")==0)
				ne = 1;
			else if(txt.compareTo("Nebulización pre-consulta")==0)
				ne = 2;
			else if(txt.compareTo("Nebulización post-consulta")==0)
				ne = 3;
			else if(txt.compareTo("Retirado")==0)
				ne = 4;
			
			String sql;
			sql = "UPDATE consultas_hoy SET "
					+"estado="+ne+" WHERE id_htra="+idHtra;			
			//System.out.println(sql);
			
			con.actualizar(sql);
			
			con.desconectar();
		}
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
