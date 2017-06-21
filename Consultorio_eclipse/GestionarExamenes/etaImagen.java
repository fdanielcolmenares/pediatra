package GestionarExamenes;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Utilitario.UtilFechas;


public class etaImagen {
	
	private JLabel etiqueta, fecha;
	//private Image imagen;
	private String ruta, date;
	private frmImagen ventIma;
	private String descripcion;
	
	public JDesktopPane desktopPanel;
	
	/*public etaImagen(String r,String f,frmImagen z){
		ruta=r;
		date=f;
		ventIma=z;
		inicializar();
	}*/
	
	public etaImagen(ImageIcon ima,String f,frmImagen z,String r,String des){
		ruta=r;
		
		desktopPanel = z.desktopPanel;
		
		date = UtilFechas.convertirFecha(f, UtilFechas.YYYY_MM_DD, UtilFechas.DD_MM_YYYY);
		ventIma=z;
		descripcion = des;
		inicializar(ima);
	}
	
	/*public void inicializar(){
		etiqueta=new JLabel();
		fecha=new JLabel(date);
		etiqueta.setSize(90,90);
		etiqueta.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(90,90,0)));
		etiqueta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				//System.out.println("imagen "+etiqueta.getIcon().toString());
				ventIma.mostrar(ruta);//aqui va donde se van a cargar las imagenes
			}
		});
		fecha.setSize(90,20);
		fecha.setHorizontalTextPosition(SwingConstants.CENTER);
		fecha.setHorizontalAlignment(SwingConstants.CENTER);
	}*/
	
	public void inicializar(final ImageIcon ima){
		etiqueta=new JLabel();
		fecha=new JLabel(date);
		etiqueta.setSize(90,90);
		etiqueta.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(90,90,0)));
		etiqueta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(e.getClickCount() == 2){
					frmImagen nuevoVisor = new frmImagen(desktopPanel);				
					nuevoVisor.setAutentica(ventIma.getAutentica());
					nuevoVisor.setNumEstu(ventIma.getNumEstu());
					nuevoVisor.setNumHis(ventIma.getNumHis());
					nuevoVisor.bloquear();
					nuevoVisor.mostrar(ima, descripcion);
					desktopPanel.add(nuevoVisor.getVtaImagen());
					nuevoVisor.getVtaImagen().toFront();
				}
			}
		});
		fecha.setSize(90,20);
		fecha.setHorizontalTextPosition(SwingConstants.CENTER);
		fecha.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public JLabel getEtiqueta(){
		return etiqueta;
	}
	
	public JLabel getFecha(){
		return fecha;
	}

}
