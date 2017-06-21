package GestionarExamenes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;


public class lienzo extends Canvas{
	
	private static final long serialVersionUID = 1L;
	private Image a;
	private int tamx,tamy,posx,posy,ang,dir;
	private int bufx,bufy;
	private String ruta;
	
	public void cargarImagen(String r){
		ruta = r;
		a= new ImageIcon(r).getImage();//getClass().getResource(r)
		tamx=400;
		tamy=275;
		posx=(760/2)-200;
		posy=(290/2)-140;
		ang=0;
		dir=0;
		bufx=bufy=-999;
		repaint();
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				public void mouseDragged(java.awt.event.MouseEvent e) {
					//System.out.println("moviendo imagen");
					mover(e.getX(),e.getY());
				}
			});
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void cargarImagen(ImageIcon ima){
		//ruta = r;
		a = ima.getImage();//getClass().getResource(r)
		tamx=400;
		tamy=275;
		posx=(760/2)-200;
		posy=(290/2)-140;
		ang=0;
		dir=0;
		bufx=bufy=-999;
		repaint();
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				public void mouseDragged(java.awt.event.MouseEvent e) {
					//System.out.println("moviendo imagen");
					mover(e.getX(),e.getY());
				}
			});
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,760,290);
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(ang), 760/2, 290/2);
		((Graphics2D) g).setTransform(at);
		g.drawImage(a,posx,posy,tamx,tamy,null);
		
	}
	
	public void update (Graphics g){
		Graphics offgc;
		Image offscreen = null;
		Dimension d = getSize();

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();
		// clear the exposed area
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(offscreen, 0, 0, this);
	}

	
	public void acercar(){
		tamx*=2;
		tamy*=2;
		repaint();
	}
	
	public void alejar(){
		tamx/=2;
		tamy/=2;
		repaint();
	}
	
	public void mover(int x,int y){
		if(dir==0){
			if(bufx>x&&bufx!=-999)posx-=3;
			if(bufx<x&&bufx!=-999)posx+=3;
			bufx=x;
			if(bufy>y&&bufy!=-999)posy-=3;
			if(bufy<y&&bufy!=-999)posy+=3;
			bufy=y;
		}	
		if(dir==1){
			if(bufx>x&&bufx!=-999)posy+=3;
			if(bufx<x&&bufx!=-999)posy-=3;
			bufx=x;
			if(bufy>y&&bufy!=-999)posx-=3;
			if(bufy<y&&bufy!=-999)posx+=3;
			bufy=y;
		}
		if(dir==2){
			if(bufx>x&&bufx!=-999)posx+=3;
			if(bufx<x&&bufx!=-999)posx-=3;
			bufx=x;
			if(bufy>y&&bufy!=-999)posy+=3;
			if(bufy<y&&bufy!=-999)posy-=3;
			bufy=y;
		}
		if(dir==3){
			if(bufx>x&&bufx!=-999)posy-=3;
			if(bufx<x&&bufx!=-999)posy+=3;
			bufx=x;
			if(bufy>y&&bufy!=-999)posx+=3;
			if(bufy<y&&bufy!=-999)posx-=3;
			bufy=y;
		}
		repaint();
	}
	
	public void rotar(){
		//int aux;
		ang+=90;
		if(ang==360||ang==0){ang=0;dir=0;}
		if(ang==90){dir=1;}
		if(ang==180){dir=2;}
		if(ang==270){dir=3;}
		repaint();
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
