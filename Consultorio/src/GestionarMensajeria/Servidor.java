package GestionarMensajeria;

import java.util.ArrayList;
import java.util.List;

public class Servidor {
	private RecibirMensajes recibe = null;

	private EnviarMensajes envia = null;

	private int puertoEscuchaServidor = 3310;

	private int puertoEscuchaCliente = 3311;

	private List listaUsuarios = null;

	private List listaIP = null;

	public Servidor() {
		recibe = new RecibirMensajes(puertoEscuchaServidor);
		recibe.setServidor(this);
		envia = new EnviarMensajes();
		envia.setPuertodestino(puertoEscuchaCliente);
		listaUsuarios = new ArrayList();
		listaIP = new ArrayList();
	}

	public void iniciarServidor() {
		recibe.ciclo = true;
		recibe.start();
		System.out.println("Servidor iniciado por el puerto: "
				+ puertoEscuchaServidor);
	}

	public void pararServidor() {
		recibe.ciclo = false;
		recibe.interrupt();
		// recibe.stop();
		System.out.println("Servidor detenido");
	}

	public void recibeMensaje(String msj, String fromIP) {
		if (msj.compareToIgnoreCase("Estado") == 0) {
			envia.setIpDestino(fromIP);
			envia.enviarCadena("Activo");
		}
		if (msj.compareToIgnoreCase("Usuarios") == 0) {
			enviarUsuarios(fromIP);
		}
		if (msj.indexOf("Conectado") == 0) {
			String sep[] = msj.split("/");
			listaUsuarios.add(sep[1]);
			listaIP.add(fromIP);
			envia.setIpDestino(fromIP);
			envia.enviarCadena("Ok");
		}
		if (msj.indexOf("Desconectado") == 0) {
			//System.out.println(msj+"-"+fromIP);
			int index = -1;
			String sep[] = msj.split("/");
			for (int i = 0; i < listaUsuarios.size(); i++) {
				if (listaUsuarios.get(i).toString().compareToIgnoreCase(sep[1]) == 0
						&& listaIP.get(i).toString().compareToIgnoreCase(fromIP) == 0) {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				listaUsuarios.remove(index);
				listaIP.remove(index);
			}
			//System.out.println(listaUsuarios.size()+"-"+listaIP.size());
			enviarUsuarios("*");
		}
		if (msj.indexOf("Mensaje") == 0) {
			String sep[] = msj.split("/");
			for (int i = 0; i < listaUsuarios.size(); i++) {
				if (listaUsuarios.get(i).toString().compareToIgnoreCase(sep[2]) == 0) {
					envia.setIpDestino(listaIP.get(i).toString());
					envia.enviarCadena(msj);
				}
			}
		}
	}

	public void enviarUsuarios(String ip) {
		String env = "Usuario";
		for (int i = 0; i < listaUsuarios.size(); i++) {
			env = env + "/" + listaUsuarios.get(i).toString();
		}
		if (ip.compareToIgnoreCase("*") == 0) {
			for (int i = 0; i < listaIP.size(); i++) {
				envia.setIpDestino(listaIP.get(i).toString());
				envia.enviarCadena(env);
			}
		} else {
			envia.setIpDestino(ip);
			envia.enviarCadena(env);
		}
	}

}
