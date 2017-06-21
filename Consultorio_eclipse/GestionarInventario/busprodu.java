package GestionarInventario;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import Utilitario.Autenticacion;
import Utilitario.TableRenderDemo;
public class busprodu {

	private JInternalFrame frmbusProdu = null;  //  @jve:decl-index=0:visual-constraint="249,21"
	private JPanel pnlbusProdu = null;
	private JLabel jLabel = null;
	private JTextField nomprod = null;
	private JButton buscar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private int x,y;
	private DefaultTableModel modelo = null;
	private DefaultTableModel modelo1 = null;
	private JButton guardar = null;
	private boolean ban;
	private TableRenderDemo tabla;
	private Autenticacion aut;
	private String numhis;
	
	public busprodu(){
		this.x=0;
		this.y=0;
		this.ban=false;
	}
	
	public busprodu(int x,int y,boolean ban,Autenticacion aut){
		this.x=x;
		this.y=y;
		this.ban=ban;
		this.aut = aut;
		//this.numhis = numhis;
	}

	/**
	 * This method initializes frmbusProdu	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getFrmbusProdu() {
		if (frmbusProdu == null) {
			frmbusProdu = new JInternalFrame();
			frmbusProdu.setSize(new Dimension(348, 326));
			frmbusProdu.setClosable(true);
			frmbusProdu.setTitle("Buscar producto");
			frmbusProdu.setFrameIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/buscar.png")));
			frmbusProdu.setLocation(x, y);
			frmbusProdu.setVisible(true);
			frmbusProdu.setContentPane(getPnlbusProdu());
		}
		return frmbusProdu;
	}

	/**
	 * This method initializes pnlbusProdu	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlbusProdu() {
		if (pnlbusProdu == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(17, 17, 113, 22));
			jLabel.setText("Ingrese el nombre:");
			pnlbusProdu = new JPanel();
			pnlbusProdu.setLayout(null);
			pnlbusProdu.setBackground(Color.white);
			pnlbusProdu.add(jLabel, null);
			pnlbusProdu.add(getNomprod(), null);
			pnlbusProdu.add(getBuscar(), null);
			pnlbusProdu.add(getJScrollPane(), null);
			pnlbusProdu.add(getGuardar(), null);
		}
		return pnlbusProdu;
	}

	/**
	 * This method initializes nomprod	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomprod() {
		if (nomprod == null) {
			nomprod = new JTextField();
			nomprod.setBounds(new Rectangle(17, 46, 197, 21));
		}
		return nomprod;
	}

	/**
	 * This method initializes buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscar() {
		if (buscar == null) {
			buscar = new JButton();
			buscar.setBounds(new Rectangle(219, 45, 105, 28));
			buscar.setText("Buscar");
			buscar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/buscar.png")));
			buscar.setToolTipText("Buscar el producto");
			buscar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println(tabla.table.getModel().getValueAt(1,2).toString());
				}
			});
		}
		return buscar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 93, 310, 170));
			tabla = new TableRenderDemo(jScrollPane,310,170,false,aut,numhis);
			//jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {		
		modelo = new DefaultTableModel();
		modelo1 = new DefaultTableModel();
		if (jTable == null) {
			if(!ban){
				jTable = new JTable(modelo1);
				modelo1.addColumn("Nombre del producto");
				modelo1.addColumn("Cantidad del producto");
				modelo1.addColumn("Eliminar");
			}
			else{
				jTable = new JTable(modelo);
				modelo.addColumn("Nombre del producto");
				modelo.addColumn("Cantidad del producto");
				//eliminar esto
				Object [] fila = new Object[2];
				fila[0] = "atamel";
				fila[1] = new Boolean(true);
				modelo.addRow(fila);
				jTable.setCellEditor(new DefaultCellEditor(new JCheckBox()));
				//hasta aqui
				/*while (rs.next())
				{
				   Object [] fila = new Object[2]; 
				   for (int i=0;i<2;i++)
				      fila[i] = rs.getObject(i+1); 
				   modelo.addRow(fila); 
				}*/
		 	}
			jTable.setPreferredScrollableViewportSize(new Dimension(200,300));
			jTable.setRowSelectionAllowed(true);
			jTable.setCellSelectionEnabled(true);
			//JScrollPane scrollPane1 = new JScrollPane(jTable);
			jTable.setDragEnabled(true);
		}
		return jTable;
	}

	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton();
			guardar.setBounds(new Rectangle(106, 265, 111, 26));
			guardar.setToolTipText("Guardar los cambios en la base de datos");
			guardar.setText("Guardar");
			guardar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/3floppy_unmount.png")));
			if(ban)
				guardar.setVisible(false);
			else
				guardar.setVisible(true);
		}
		return guardar;
	}

}
