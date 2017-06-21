package IniciarAplicacion;

import javax.swing.JWindow;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import ConexionBD.Conexion;
import Utilitario.Autenticacion;
import Utilitario.decodificador;
import VentanaPrincipal.GestorVentanaPrincipal;
import VentanaPrincipal.VentanaPrincipal;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.io.InputStream;

public class VentanaIniciarAplicacion {

    private JWindow ventana = null;  //  @jve:decl-index=0:visual-constraint="151,47"
    private JPanel panel = null;
    private JProgressBar barra = null;
    private Autenticacion autenticacion;
    private int errorAplicacion;
	private JLabel lbl_fondo = null;

    public VentanaIniciarAplicacion() {
        getVentana();
        autenticacion = new Autenticacion();
        errorAplicacion = 0;

        setVisible(true);
        validarInstalacion();
    }

    public void setVisible(boolean aFlag) {
        ventana.setVisible(aFlag);
    }

    public void validarInstalacion() {
        try {
            int numPasos = 4;
            int avance = 100 / numPasos;

            barra.setString("Validando instalacion - 0%");
            validarDisco();
            barra.setValue(0);Thread.sleep(300);
            Thread.sleep(300);

            barra.setString("Validando instalacion - " + (avance) + "%");
            //valido otra cosa
            barra.setValue(avance);
            Thread.sleep(300);

            barra.setString("Buscando Servidor - " + (2 * avance) + "%");
            buscarServidor();
            barra.setValue(2 * avance);
            Thread.sleep(300);

            barra.setString("Cargando modulos - " + (3 * avance) + "%");
            //valido otra cosa
            barra.setValue(3 * avance);
            Thread.sleep(300);

            for (int i = barra.getValue(); i <= 100; i++) {
                barra.setString("Iniciando - " + i + "%");
                barra.setValue(i);
                Thread.sleep(30);
            }

            if (errorAplicacion == 0) {            	
                VentanaPrincipal v = new VentanaPrincipal();
                GestorVentanaPrincipal gestor = new GestorVentanaPrincipal(v);
                v.setGestor(gestor);
                ventana.setVisible(false);
                v.setVisible(true);                
                v.getGestor().setAutenticacion(autenticacion);
            } else {
                System.out.println("Aplicacion no valida - Error " + errorAplicacion);
            }
        } catch (Exception e) {
            System.out.println("Error validar Instalacion");
            e.printStackTrace();
        }

    }

    public void validarDisco() {
        decodificador a = new decodificador("ConfigIni.dll");
        String idDiscoArchivo = a.desencriptar(0);
        String idDisco = "";
        int delay = 10;
        
        for(int j=0; j<5 && (idDisco.compareTo("") == 0); j++){
            try {
                byte b[] = new byte[500];
                Process p = Runtime.getRuntime().exec("cmd /C vol C:");
                InputStream is = p.getInputStream();
                int l = is.read(b);
                char c[] = new char[l];
                for (int i = 0; i < l; i++) {
                    c[i] = (char) b[i];
                }
                String cadena = String.valueOf(c);
                int index, fromIndex = 0;
                cadena = cadena.replaceAll("\r", "");
                if ((index = cadena.indexOf("es: ")) != -1) {
                    fromIndex = cadena.indexOf("\n", index + 1);
                    String id = cadena.substring(index + 4, fromIndex);
                    idDisco = id;
                }
                if(idDisco.length() == 0){
                	Thread.sleep(delay);
                	if(delay < 50){
                		delay = delay * 2;
                	}
                }
            } catch (Exception e) {
                System.out.println("Error al validar");
                idDisco = "";
            }
        }

        idDisco = idDisco.toUpperCase();
        idDiscoArchivo = idDiscoArchivo.toUpperCase();

        if (idDisco.length()>0 && idDisco.compareTo(idDiscoArchivo) != 0) {
            System.out.println(idDisco + " != " + idDiscoArchivo);
            //errorAplicacion = 1;
        }
    }

    public void buscarServidor() {
    	try{
	        decodificador a = new decodificador("ConfigServer.dll");
	        String lee = a.desencriptar(0);
	        autenticacion.setIpServidor(lee);
	        lee = a.desencriptar(1);
	        autenticacion.setPuerto(lee);
	
	        decodificador b = new decodificador("ConfigUser.dll");
	        lee = b.desencriptar(0);
	        autenticacion.setUsuarioBD(lee);
	        lee = b.desencriptar(1);
	        autenticacion.setClaveBD(lee);
	
	        Conexion con = new Conexion(autenticacion);
	        if (con.conectar() == false) {
	            autenticacion.setIpServidor("");
	            autenticacion.setPuerto("");
	        }
	        con.desconectar();
    	}
    	catch(Exception e){
    		autenticacion.setIpServidor("");
            autenticacion.setPuerto("");
    	}
    }

    /**
     * This method initializes ventana
     *
     * @return javax.swing.JWindow
     */
    private JWindow getVentana() {
        if (ventana == null) {
            ventana = new JWindow();
            ventana.setBounds(new Rectangle(0, 0, 450, 250));
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            ventana.setLocation((int) (d.getWidth() / 2 - ventana.getWidth() / 2), (int) (d.getHeight() / 2 - ventana.getHeight() / 2));
            ventana.setContentPane(getPanel());
        }
        return ventana;
    }

    /**
     * This method initializes panel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel() {
        if (panel == null) {
            lbl_fondo = new JLabel();
            lbl_fondo.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image023.png")));
            lbl_fondo.setSize(new Dimension(450, 250));
            lbl_fondo.setLocation(new Point(0, 0));
            lbl_fondo.setText("JLabel");
            panel = new JPanel();
            panel.setLayout(null);
            panel.add(getBarra(), null);
            panel.add(lbl_fondo, null);
        }
        return panel;
    }

    /**
     * This method initializes barra
     *
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getBarra() {
        if (barra == null) {
            barra = new JProgressBar();
            barra.setForeground(Color.blue);
            barra.setSize(new Dimension(420, 14));
            barra.setLocation(new Point(14, 200));
            barra.setStringPainted(true);
        }
        return barra;
    }
}
