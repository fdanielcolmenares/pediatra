package Utilitario;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Modelo_Tabla extends DefaultTableModel{
	
	/**
	 * 
	 */
	private int columnEditable = -1;
	private int minFilaEditable = -1;
	private int minColumnaEditable = -1;
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	public Modelo_Tabla(){
        super();   
    }
	
	public Modelo_Tabla(Object[][] data,Object[] ColumnNames){
	      super(data,ColumnNames);   	      
	    }
	public Modelo_Tabla(Vector data,Vector ColumnNames){
	      super(data,ColumnNames);   	      
	    }
	
	public void removeAllRows(){
		/*int rows = this.getRowCount();
		for(int i=0; i<rows; i++){
			this.removeRow(i);
		}*/
		this.setRowCount(0);
	}
	
	public boolean isCellEditable (int row, int column){
		if(minFilaEditable == -1 && minColumnaEditable == -1){
			if(columnEditable == -1){
				return false;
			}
			else{
				if(column == columnEditable){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(row >= minFilaEditable && column >= minColumnaEditable){
				return true;
			}
			else{
				return false;
			}
		}
    }//fin funcion
	
	public void setColumnEditable(int r){
		columnEditable = r;
	}
	
	public void setMinFilaEditable(int r){
		minFilaEditable = r;
	}
	
	public void setMinColumnaEditable(int c){
		minColumnaEditable = c;
	}
}
