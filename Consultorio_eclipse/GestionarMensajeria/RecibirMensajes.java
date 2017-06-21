package GestionarMensajeria;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RecibirMensajes extends Thread{
    private DatagramSocket s = null;
    private DatagramPacket recibep;
    private byte buffer[] = new byte[1000];        
    private int puertoRecep;
    public boolean ciclo;
    private Servidor servidor = null;
    private GestorCliente cliente = null;
    private int ventana;

    public RecibirMensajes(int p){
        puertoRecep = p;
        ciclo = true;
        ventana = 0;
        try {
            s = new DatagramSocket( puertoRecep );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public void setCliente(GestorCliente cliente) {
        this.cliente = cliente;
        ventana = 1;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
        ventana = 2;
    }
    
    public void run(){
        while(ciclo){
            recibep = new DatagramPacket( buffer,1000);
            try {
                s.receive( recibep );
            }
            catch(Exception e ) {
                e.printStackTrace();
            }
            String datos = new String(recibep.getData());
            datos = datos.substring(0, recibep.getLength());
            String fromIP = recibep.getAddress().toString();
            fromIP = fromIP.substring(1, fromIP.length());
            
            if(datos.length()>0){            
	            //System.out.println("Llega: <<"+datos+">> desde <<"+fromIP+">>");
	
	            if(ventana == 1){
	                cliente.recibeMensaje(datos, fromIP);
	            }
	            if(ventana == 2){
	                servidor.recibeMensaje(datos, fromIP);
	            }
            }
            
        }
    }
}
