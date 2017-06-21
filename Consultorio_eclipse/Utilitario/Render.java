package Utilitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;



public class Render extends DefaultTableCellRenderer
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      if(table.getModel().getValueAt(row,column)!=null){  
      boolean ban = true;    
      if (table.getModel().getValueAt(row,2).toString().indexOf("En espera")>=0)
      {         
         this.setOpaque(true);
         this.setBackground(Color.YELLOW);
         this.setForeground(Color.black);
         ban = false;
      }   
      if (table.getModel().getValueAt(row,2).toString().indexOf("En consulta")>=0)
      {
         this.setOpaque(true);
         this.setBackground(Color.GREEN);
         this.setForeground(Color.black);
         ban = false;
      }
      if (table.getModel().getValueAt(row,2).toString().indexOf("pre-consulta")>=0)
      {
         this.setOpaque(true);
         this.setBackground(Color.BLUE);
         this.setForeground(Color.WHITE);
         ban = false;
      }
      if (table.getModel().getValueAt(row,2).toString().indexOf("post-consulta")>=0)
      {
         this.setOpaque(true);
         this.setBackground(Color.GRAY);
         this.setForeground(Color.WHITE);
         ban = false;
      }
      if (ban)
      {
         this.setOpaque(true);
         //this.setBackground(Color.ORANGE);
         this.setBackground(Color.BLACK);
         this.setForeground(Color.YELLOW);
      }
      }//fin if global
      if(isSelected==true){
          this.setOpaque(true); 
          //setBackground(null);
          setBackground(SystemColor.textHighlight);
          this.setForeground(Color.black);
      }
      return this;
   }
}