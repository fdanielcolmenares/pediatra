package GestionarMensajeria;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import Utilitario.Autenticacion;
import VentanaPrincipal.GestorVentanaPrincipal;

public class GestorCliente {
	private int puertoEscuchaServidor = 3310;
    private int puertoEscuchaCliente = 3311;
	private Cliente ventana;
	private RecibirMensajes recibe;
	private EnviarMensajes envia;
	private List listaConectados;
	private int estado;
	private Autenticacion autenticacion;
	private JButton botonPrincipal;
	private Timer timerBusqueda;
	private GestorVentanaPrincipal gestorPrincipal;
	private int contPaquetesEnviados;
	private int TIEMPO_BUSQUEDA = 5000;
	private int TIEMPO_COMPROBACION = 10000;
	private int LIMITE_PAQUETES = 2;
	
	public GestorCliente(Cliente v, Autenticacion a, GestorVentanaPrincipal g){
		ventana = v;
		autenticacion = a;
		contPaquetesEnviados = 0;
		estado = ventana.DESCONECTADO;
		ventana.setEstado(estado);
		botonPrincipal = g.getVentana().getBtn_mensajes();
		gestorPrincipal = g;
		botonPrincipal.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
		
		recibe = new RecibirMensajes(puertoEscuchaCliente);
		recibe.setCliente(this);
		envia = new EnviarMensajes();
		envia.setPuertodestino(puertoEscuchaServidor);
		envia.setIpDestino(autenticacion.getIpServidor());
		timerBusqueda = new Timer(TIEMPO_BUSQUEDA , new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarServidor();
			}
		});
		
		listaConectados = new ArrayList();
		ventana.setUsuario(autenticacion.getUsuario());
		iniciarCliente();
		iniciarBusquedaServidor();
	}

	public void setVentana(Cliente v){
		ventana = v;
	}
	
	public void desconectar(){
		envia.enviarCadena("Desconectado/"+autenticacion.getUsuario());
		estado = ventana.DESCONECTADO;
	}
	
	public void setUsuario(String u){
		ventana.setUsuario(autenticacion.getUsuario());
	}
	
	public void iniciarBusquedaServidor(){
		timerBusqueda.start();
	}
	
	public void conectar(){
		if(estado == ventana.DESCONECTADO || estado == ventana.CONECTANDO){
			estado = ventana.CONECTANDO;
			envia.enviarCadena("Conectado/"+autenticacion.getUsuario());
		}
	}
	
	public void pararBusquedaServidor(){
		timerBusqueda.stop();
	}
	
	public void iniciarCliente(){
		recibe.ciclo = true;
		recibe.start();
	}
	
	public void pararCliente(){
		recibe.ciclo = false;
		recibe.interrupt();
	}
	
	public void buscarServidor(){
		if(estado == ventana.DESCONECTADO || estado == ventana.CONECTANDO){
			//estado = ventana.CONECTANDO;
			envia.enviarCadena("Estado");
			if(contPaquetesEnviados >= LIMITE_PAQUETES){
				if(autenticacion.getIpServidor().compareToIgnoreCase("127.0.0.1")==0
						|| autenticacion.getIpServidor().compareToIgnoreCase("localhost")==0){
					gestorPrincipal.iniciarServidor();
				}
				contPaquetesEnviados = 0;
			}
			else{
				contPaquetesEnviados ++;
			}
		}
		else{
			envia.enviarCadena("Estado");
			if(contPaquetesEnviados >= LIMITE_PAQUETES){
				estado = ventana.DESCONECTADO;
				ventana.setEstado(estado);
				if(autenticacion.getIpServidor().compareToIgnoreCase("127.0.0.1")==0
						|| autenticacion.getIpServidor().compareToIgnoreCase("localhost")==0){
					gestorPrincipal.iniciarServidor();
				}
				contPaquetesEnviados = 0;
				borrarConectados();
			}
			else{
				contPaquetesEnviados ++;
			}
		}
	}
	
	public void recibeMensaje (String msj, String fromIP){
		if(msj.compareToIgnoreCase("Activo")==0){
			contPaquetesEnviados = 0;
			if(estado == ventana.DESCONECTADO || estado == ventana.CONECTANDO){
				conectar();
			}
			envia.enviarCadena("Usuarios");
		}
		if(msj.compareToIgnoreCase("Ok")==0){
			estado = ventana.CONECTADO;
			ventana.setEstado(ventana.CONECTADO);
			envia.enviarCadena("Usuarios");
			botonPrincipal.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image076.png")));
			//pararBusquedaServidor();
			timerBusqueda.setDelay(TIEMPO_COMPROBACION);
		}
		if(msj.indexOf("Usuario")==0){
			//System.out.println(msj);
			borrarConectados();
			String sep[] = msj.split("/");
			for(int i=1; i<sep.length; i++){
				String user = sep[i];
				int f = 0;
				for(int j=0; j<listaConectados.size(); j++){
					panelConectados p = (panelConectados)listaConectados.get(j);
					if(p.getUsuario().compareToIgnoreCase(user)==0){
						f = 1;
						p.setConectado(true);
						break;
					}
				}
				
				if(f == 0){
					if(ventana.getUsuario().compareToIgnoreCase(user)!=0){
						panelConectados p = new panelConectados(ventana);
						p.setUsuario(user);
						p.setConectado(true);
						listaConectados.add(p);
					}
				}
			}
			pintarConectados();
		}
		if (msj.indexOf("Mensaje") == 0) {
			String sep[] = msj.split("/");
			if(sep[2].compareToIgnoreCase(ventana.getUsuario())==0){
				for(int i=0; i<listaConectados.size(); i++){
					panelConectados p = (panelConectados)listaConectados.get(i);
					if(sep[1].compareToIgnoreCase(p.getUsuario())==0){
						p.getventana().recibeMensaje(sep[1], sep[3], true);
					}
				}
			}
		}
    }
	
	public void enviarMensaje(String msj, String para){
		String env = "Mensaje/";
		env = env + ventana.getUsuario()+"/";
		env = env + para + "/";
		env = env + msj;
		envia.enviarCadena(env);
	}
	
	public void borrarConectados(){
		for(int i=0; i<listaConectados.size(); i++){
			panelConectados p = (panelConectados)listaConectados.get(i);
			//p.setVisible(false);
			//p = null;
			p.setConectado(false);
		}
		//listaConectados.clear();
	}
	
	public void eliminarPaneles(){
		int x = listaConectados.size();
		for(int i=0; i<x; i++){
			panelConectados p = (panelConectados)listaConectados.get(0);
			p.setVisible(false);
			listaConectados.remove(0);
		}
	}
	
	public void pintarConectados(){
		int height = 60;
		for(int i=0; i<listaConectados.size(); i++){
			panelConectados p = (panelConectados)listaConectados.get(i);
			p.setLocation(0, i*height);
			p.setVisible(true);
			ventana.getPanelConectados().add(p);
		}
		int x = ventana.getVentana().getX();
		ventana.getVentana().setLocation(x+1, ventana.getVentana().getY());
		ventana.getVentana().setLocation(x, ventana.getVentana().getY());
		ventana.getPanelConectados().setPreferredSize(new Dimension(230,height*listaConectados.size()));
	}
	
	public void prueba(){
		System.out.println(listaConectados.size());
	}
	
	public void minimizar(){
		ventana.getVentana().setVisible(false);
		botonPrincipal.setVisible(true);
	}
}
