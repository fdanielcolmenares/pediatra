package Utilitario;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import Entidades.consultas;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;

/** 
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class TableRenderDemo /*extends JPanel*/ {
    private boolean DEBUG = false;

    public JTable table;
    public JScrollPane scrollPane;
    public TableRenderDemo(JScrollPane a,int x, int y, boolean ban, Autenticacion aut, String numhis) {
        //super(new GridLayout(1,0));

        table = new JTable(new MyTableModel(ban,aut,numhis));
        table.setPreferredScrollableViewportSize(new Dimension(x, y));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        a.setViewportView(table);

        //Set up column sizes.
        initColumnSizes(table);

        table.getTableHeader().setReorderingAllowed(false);
        //Fiddle with the Sport column's cell editors/renderers.
        /*if(ban)
        	setUpSportColumn(table, table.getColumnModel().getColumn(2));*/

        //Add the scroll pane to this panel.
        //add(scrollPane);
    }
    
    public TableRenderDemo(JScrollPane a,int x, int y, boolean ban, Autenticacion aut) {
        
        table = new JTable(new MyTableModel(ban,aut,"00"));
        table.setPreferredScrollableViewportSize(new Dimension(x, y));
        table.setFillsViewportHeight(true);

        a.setViewportView(table);

        initColumnSizes(table);
    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 3 && model.getRowCount()>0; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        /*JComboBox comboBox = new JComboBox();
        comboBox.addItem("Snowboarding");
        comboBox.addItem("Rowing");
        comboBox.addItem("Knitting");
        comboBox.addItem("Speed reading");
        comboBox.addItem("Pool");
        comboBox.addItem("None of the above");*/
    	//System.out.println("hola");
    	JCheckBox boton = new JCheckBox();
        sportColumn.setCellEditor(new DefaultCellEditor(boton));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }

    class MyTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames;
        private Object[][] data;
        private java.sql.ResultSet res;
        private int cont;
        
        public MyTableModel(boolean ban,Autenticacion aut,String numhis){
        	int c=0;
        	if(ban){
        		columnNames = new String[4];
        		columnNames[0] = "Fecha";
        		columnNames[1] = "Hora";
        		columnNames[2] = "Motivo";
        		columnNames[3] = "Ver";
        		
        		//System.out.println("Error aqui");
        		consultas con = new consultas(aut);
            	res = con.buscarlista(numhis);
            	cont = 0;
            	try {
    				while(res.next()){
    					cont++;
    				}
    			} catch (SQLException e1) {
    				System.out.println("Error buscar lista clase tablerender constructor");
    			}
    			res = con.buscarlista(numhis);
            	data =new Object[cont][4];
            	try {
    				while(res.next()){
    					String fecha = res.getDate("fecha").toString();
    					fecha = UtilFechas.convertirFecha(fecha, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
    					//data[c][0]=res.getDate("fecha").toString();
    					data[c][0] = fecha;
    					data[c][2]=res.getString("motivo");
    					data[c][1]=res.getString("hora");
    					data[c][3]=new Boolean(false);
    					c++;
    				}
    			} catch (SQLException e) {
    				System.out.println("Error buscar lista clase tablerender constructor");
    			}
        	}
        	else{
        		columnNames = new String[3];
        		columnNames[0] = "Nombre del Producto";
        		columnNames[1] = "Cantidad del Producto";
        		columnNames[2] = "Eliminar";
        	}        	
        }
       /* private Object[][] data = {
	    {"Atamel", new Integer(3), new Boolean(false)},
	    {"Corisidin", new Integer(5),new Boolean(false)},
	    {"teragrip", new Integer(2),new Boolean(false)},
	    {"tachipirin", new Integer(20),new Boolean(false)},
	    {"noni", new Integer(10),new Boolean(false)}
        };*/

        public final Object[] longValues = {"algo", new Integer(20),Boolean.TRUE};

        public int getCont(){
        	return cont;
        }
        
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 3) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        

        //Create and set up the content pane.
       // final TableRenderDemo newContentPane = new TableRenderDemo();
       // newContentPane.setOpaque(true); //content panes must be opaque
        
      /*  JButton buscar;
        buscar = new JButton();
		buscar.setBounds(new Rectangle(219, 45, 105, 28));
		buscar.setText("Buscar");
		buscar.setToolTipText("Buscar el producto");
		buscar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				//newContentPane.table.getModel().getValueAt(1,2).toString();
				System.out.println(newContentPane.table.getModel().getValueAt(1,2).toString());
			}
		});*/
        
      //  newContentPane.add(buscar);
       // frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}