package GestionarVacunas;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;

public class SaveVacuna extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JComboBox C_vacunas = null;
	private JTextField t_lote = null;
	private JButton b_save = null;
	private JButton b_cancel = null;
	private JLabel l_vacunas = null;
	private JLabel l_fecha = null;
	private JLabel l_proxi = null;
	private JLabel l_lote = null;
	private Autenticacion autenticacion;
	private JDateChooser chooser;
	private JComboBox chooser2;
	private JInternalFrame frame;
	private int Id_historia;
	private JTable table;
	private int id_vac = 0;
	private int tipo_a = 0;
	/**
	 * This is the xxx default constructor
	 */
	public SaveVacuna(JDesktopPane ventana,Autenticacion a, int id_historia,JTable table) {
		super();
		this.autenticacion = a;
		this.frame = this;
		this.table = table;
		this.Id_historia = id_historia;
		tipo_a = 0;
		
		initialize();
		ventana.add(this);
		int x = Math.max(0, (ventana.getWidth() - this.getWidth()-15) / 2);
        int y = Math.max(0, (ventana.getHeight() - this.getHeight() -70) / 2);
        this.setLocation(new Point(x, y));		
		this.setVisible(true);
	}
	
	public void preLoad(String dato){
		tipo_a = 1;
				String query = "SELECT a.vcna_id,a.fecha,a.tipo,a.lote, v.nombre AS nombre from vacunas_historia a, vacunas v WHERE a.vcna_id=v.id AND a.id= '"+dato+"'";
				Conexion conexion = new Conexion(autenticacion);
				conexion.conectar();
				id_vac = Integer.parseInt(dato);
				try {
					ResultSet resul = conexion.consultar(query);
					resul.next();
						//C_vacunas.setSelectedIndex(Integer.parseInt(resul.getString(1)));
						C_vacunas.setSelectedItem(resul.getString("nombre").toString());
						System.out.println(resul.getString(2));
				/////////FORMAT FECH		
						Date aux = new Date();						
						String[] date = resul.getString(2).split("-");
						SimpleDateFormat fech = new SimpleDateFormat("dd/MM/yyyy");
						String fechcon = date[2]+"/"+date[1]+"/"+date[0];
						aux = fech.parse(fechcon);						
						chooser.setDate(aux);
						///-------------
						//date = resul.getString(3).split("-");
						//fech = new SimpleDateFormat("dd/MM/yyyy");
						//fechcon = date[2]+"/"+date[1]+"/"+date[0];
						//aux = fech.parse(fechcon);						
						//chooser2.setDate(aux);
						chooser2.setSelectedIndex(Integer.parseInt(resul.getString(3))-1);
////////				/FORMAT FECH			
						
						
						t_lote.setText(resul.getString(4));
						conexion.desconectar();	
						this.setTitle("Editar Vacuna");
						this.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(284, 281);
		this.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image065.png")));
		this.setClosable(true);
		this.setTitle("Agregar Vacuna");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 */
	
	public JDateChooser getT_fechaNac() {
		if (chooser == null) {
			chooser = new JDateChooser();
			chooser.setFont(new Font("Dialog", Font.PLAIN, 14));
			//t_fechaNac.setNextFocusableComponent(b_siguiente1);
			chooser.setEnabled(true);
			chooser.setSize(new Dimension(180, 20));
			chooser.setLocation(new Point(16, 92));
            chooser.setMinSelectableDate(new Date());
		}
		return chooser;
	}
	
	public JComboBox getT_fecha2() {
		if (chooser2 == null) {
			chooser2 = new JComboBox();
			chooser2.setFont(new Font("Dialog", Font.PLAIN, 14));
			chooser2.addItem("1");
			chooser2.addItem("2");
			chooser2.addItem("3");
			chooser2.addItem("R");
			chooser2.setSelectedIndex(0);
			//t_fechaNac.setNextFocusableComponent(b_siguiente1);
			chooser2.setSize(new Dimension(50, 20));
			chooser2.setLocation(new Point(87, 123));
			chooser2.setEnabled(true);
            
		}
		return chooser2;
	}
	
	 /* @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getB_cancel(), null);
			jContentPane.add(getB_save(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			l_lote = new JLabel();
			l_lote.setText("Lote:");
			l_lote.setSize(new Dimension(61, 20));
			l_lote.setHorizontalAlignment(SwingConstants.RIGHT);
			l_lote.setLocation(new Point(20, 150));
			l_proxi = new JLabel();
			l_proxi.setText("(*)Dosis:");
			l_proxi.setSize(new Dimension(61, 20));
			l_proxi.setHorizontalAlignment(SwingConstants.RIGHT);
			l_proxi.setLocation(new Point(20, 123));
			l_fecha = new JLabel();
			l_fecha.setBounds(new Rectangle(20, 70, 162, 16));
			l_fecha.setText("(*)Fecha de Vacunacion");
			l_vacunas = new JLabel();
			l_vacunas.setBounds(new Rectangle(20, 24, 152, 16));
			l_vacunas.setText("(*)Nombre de la Vacuna:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(30, 14, 221, 185));
			jPanel.setSize(new Dimension(294,185));
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Agregar Vacuna", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(getC_vacunas(), null);
			jPanel.add(getT_fechaNac(), null);			
			jPanel.add(l_vacunas, null);
			jPanel.add(l_fecha, null);
			jPanel.add(l_proxi, null);
			jPanel.add(getT_lote(), null);
			jPanel.add(l_lote, null);
			jPanel.add(getT_fecha2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes C_vacunas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getC_vacunas() {
		SQLvacunas lvacunas = new SQLvacunas(autenticacion);
		if (C_vacunas == null) {
			C_vacunas = new JComboBox();
			C_vacunas.setSize(new Dimension(180, 20));
			C_vacunas.setLocation(new Point(16, 46));
			lvacunas.getVacunas(C_vacunas);
		}
		return C_vacunas;
	}

	/**
	 * This method initializes t_lote	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT_lote() {
		if (t_lote == null) {
			t_lote = new JTextField();
			t_lote.setLocation(new Point(87, 150));
			t_lote.setSize(new Dimension(108, 20));
		}
		return t_lote;
	}

	/**
	 * This method initializes b_save	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_save() {
		if (b_save == null) {
			b_save = new JButton();
			b_save.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image038.png")));
			b_save.setLocation(new Point(15, 210));
			b_save.setSize(new Dimension(115, 30));
			b_save.setMnemonic(KeyEvent.VK_G);
			b_save.setText("Guardar");
			b_save.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
						GuardarDatos(tipo_a);
					///System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return b_save;
	}
///	@SuppressWarnings("deprecation")
	public void GuardarDatos(int accion){
		
		///validacones
		if(chooser.getDate()!=null){
			///System.out.println(utiVac.DateFormatStringLong(chooser.getDate()));
			Conexion conexion = new Conexion(autenticacion);
			conexion.conectar();
			String query = "SELECT id FROM vacunas WHERE nombre ='"+C_vacunas.getItemAt(C_vacunas.getSelectedIndex())+"'";
			ResultSet a = null;
			String id_va=null;
			
			
			
			try {
				a = conexion.consultar(query);
				a.next();
				id_va = a.getString(1);
				
				if(accion==0){
				query = "SELECT MAX(id) FROM vacunas_historia";
				a = conexion.consultar(query);
				a.next();
				 if(a.getString(1)==null){
					 	id_vac = 1;
				 }else{
					 	id_vac = Integer.parseInt(a.getString(1))+1;
				 }
				}//fin if
				
				
				
			} catch (Exception e) {
				System.out.println("Eror en consulta de ID de Vacuna");
				// TODO: handle exception
			}
		/*String dateBali =null;	
			if(chooser2.getDate()!=null){
				dateBali =utiVac.DateFormatStringLong(chooser2.getDate());
			}else{
				dateBali = null;
			}*/
			
			/// System.out.println("--"+chooser.getCalendar().getTime().getDate());
			String fecha1 = null;
			String fecha2 = null;
			if(chooser.getDate()!=null){
			 fecha1 = (chooser.getCalendar().getTime().getYear()+1900)+"-"+(chooser.getCalendar().getTime().getMonth()+1)+"-"+chooser.getCalendar().getTime().getDate();
			}
			//if(chooser2.getDate()!=null){
			//	 fecha2 = (chooser2.getCalendar().getTime().getYear()+1900)+"-"+(chooser2.getCalendar().getTime().getMonth()+1)+"-"+chooser2.getCalendar().getTime().getDate();
				//}
			 System.out.println(fecha1);
			 if(accion==0){
			query = "INSERT INTO vacunas_historia VALUES('"+id_vac+"','"+Id_historia+"','"+id_va+"','"+fecha1+"','"+(chooser2.getSelectedIndex()+1)+"','"+t_lote.getText()+"')";
			}//fin if 
			
			if(accion==1){
					query = "UPDATE vacunas_historia SET vcna_id='"+id_va+"',fecha='"+fecha1+"',tipo='"+(chooser2.getSelectedIndex()+1)+"',lote='"+t_lote.getText()+"' WHERE id='"+id_vac+"'";
			}
			
			System.out.println(query);
			boolean vali = false;
		
			if(chooser.getDate()!=null){
			vali = conexion.actualizar(query);
			}
			///boolean vali = false;
			if(vali){
					JOptionPane.showMessageDialog(frame, "Operacion Exitosa!!!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					dispose();
			}else{
				JOptionPane.showMessageDialog(frame, "Debe ingresar los campos marcados con (*).", "Advertencia!", JOptionPane.WARNING_MESSAGE);
				
			}
			
		}else{
			JOptionPane.showMessageDialog(frame, "Debe ingresar los campos marcados con (*).", "Advertencia!", JOptionPane.WARNING_MESSAGE);
			
		}
		///
		
		CargaVacunas aux = new CargaVacunas(autenticacion,Id_historia);
		table.setModel(aux);
	}
	/**
	 * This method initializes b_cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB_cancel() {
		if (b_cancel == null) {
			b_cancel = new JButton();
			b_cancel.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image037.png")));
			b_cancel.setLocation(new Point(152, 210));
			b_cancel.setText("Cerrar");
			b_cancel.setMnemonic(KeyEvent.VK_C);
			b_cancel.setSize(new Dimension(110, 30));
			b_cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
						dispose();
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return b_cancel;
	}

}  //  @jve:decl-index=0:visual-constraint="94,-2"
