package GestionarExamenes;



import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

import Entidades.estudios_historia;
import Utilitario.Autenticacion;

public class psaExamen {

	private JPanel pnlIma = null;
	private etaImagen []imagenes;
	private frmImagen venIma;
	private GroupLayout jPanel1Layout;
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="107,15"
	int tamScroll;
	private Autenticacion autenticar;
	private estudios_historia estudios;
	private Blob imablob;
	private ResultSet res = null;
	int et,hist;
	
	public psaExamen(frmImagen v, Autenticacion a, int es, int his){
		venIma=v;
		autenticar = a;
		et = es;
		hist = his;
	}
	
	public void cargarImagenes(){
		int x=5;
		int y=5;
		int f=0;
		estudios = new estudios_historia(autenticar);
		ImageIcon imagen;
		f=estudios.contar();		
		tamScroll=(f/6)*120;
		if(f%6!=0)
			tamScroll+=120;
		imagenes= new etaImagen[f];
		res = estudios.cargartipos(et, hist);
		for(int i=0;i<f;i++){   
			try {
				res.next();
				imablob = res.getBlob("imagen");
				imagen = new ImageIcon(imablob.getBytes(1,(int)imablob.length()));
				imagenes[i]=new etaImagen(imagen,res.getString("fecha"),venIma,"/Files/Imagenes/a.jpg"
						,res.getString("observacion"));
				//colocando las posiciones de las imagenes
				if(i!=0&&i%6==0){
					y+=115;
					x=5;
				}
				imagenes[i].getEtiqueta().setLocation(x,y);
				imagenes[i].getFecha().setLocation(x,y+90);
				x+=95;
				//fin posicionar imagenes
				pnlIma.add(imagenes[i].getEtiqueta());
				pnlIma.add(imagenes[i].getFecha());
			} catch (SQLException e) {
				//e.printStackTrace();
			}		
			
		}
	}
	/**
	 * This method initializes pnlIma	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlIma() {
		if (pnlIma == null) {
			pnlIma = new JPanel();
			cargarImagenes();
			pnlIma.setSize(new Dimension(150, 150));
			jPanel1Layout = new GroupLayout(pnlIma);
			pnlIma.setLayout(jPanel1Layout);
			jPanel1Layout.setHorizontalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGap(0, 600, Short.MAX_VALUE)
		        );
		    jPanel1Layout.setVerticalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGap(0, tamScroll, Short.MAX_VALUE)
		    	);

		}
		return pnlIma;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			JScrollBar jScrollBar = new JScrollBar();
			jScrollBar.setBlockIncrement(20);
			jScrollBar.setUnitIncrement(20);
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(100, 100));
			jScrollPane.setAutoscrolls(true);
			jScrollPane.setBorder(null);
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setVerticalScrollBar(jScrollBar);
			jScrollPane.setViewportView(getPnlIma());
		}
		return jScrollPane;
	}

}
