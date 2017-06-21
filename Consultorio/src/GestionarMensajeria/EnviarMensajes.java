package GestionarMensajeria;

import java.io.*;
import java.net.*;

public class EnviarMensajes {
    private DatagramSocket s;
    private DatagramPacket enviap;
    private int puertoDestino;
    private InetAddress IP;
    private String ipDestino;
    
    public EnviarMensajes(){
        try {
            s = null;        
            s = new DatagramSocket( );
        }
        catch( SocketException e ) {
        	e.printStackTrace();
        }
    }
    
    public void setIpDestino(String dir){
        ipDestino = dir;
    }

    public void setPuertodestino(int p){
        puertoDestino = p;
    }
    
    public boolean enviarCadena(String cad){ 
        try {
            IP = null;
            IP = InetAddress.getByName(ipDestino);
            byte buffer[] = new byte[1000];
            cad.getBytes(0,cad.length(),buffer,0);
            enviap = new DatagramPacket(buffer,cad.length(), IP, puertoDestino );
            s.send( enviap );
            //System.out.println("Enviado <<"+cad+">>");
            return true;
        }
        catch( IOException e ) {
        	e.printStackTrace();
            s.close();
        }
        return  false;
    }
}
    