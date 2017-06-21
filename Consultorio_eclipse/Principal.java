
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import de.muntjak.tinylookandfeel.Theme;
import de.muntjak.tinylookandfeel.ThemeDescription;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import IniciarAplicacion.VentanaIniciarAplicacion;
import Utilitario.*;

public class Principal {

	public static void main(String[] args) {
		/*VentanaPrincipal ventana = new VentanaPrincipal();
		GestorVentanaPrincipal gestor = new GestorVentanaPrincipal(ventana);
		ventana.setGestor(gestor);
		*/		            
            
		/*try{
			java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2010-09-18");
			String date = ""+d.getDate()+"-"+d.getMonth()+"-"+(d.getYear()+1900);
			System.out.println(d);
			System.out.println(date);
		}
		catch(Exception e){			
		}*/
		
		//VentanaIniciarAplicacion obj = new VentanaIniciarAplicacion();
		
		Principal o = new Principal();
		//o.cambiarEstiloNimROD();
		//o.cambiarEstiloTinyLAF();
		//o.cambiarEstiloJTatoo();
		o.cambiarEstiloWindows();
		//o.cambiarEstiloBasico();			
		
		//System.out.println(Path.getPath());
		//Path.setPath("D:/");
		//System.out.println(Path.getPath("Files/jasper/nada.jsp"));
		
		//System.out.println(UtilFechas.convertirFecha("1-4-2010", UtilFechas.DD_MM_YYYY, UtilFechas.YYYY_MM_DD));
		
		
		Path.buscarPath();
		new VentanaIniciarAplicacion();/*********************/
		
		/*EditarDLL ven = new EditarDLL();
		ven.setVisible(true);*/
		
		//new CrearConsulta();
		
		//System.out.print(Encriptar.encryptMD5("123456"));
		
		/*codificador a=new codificador();
		try {
			//a.setTexto("vol = 2EB4-6094\nruta = C:/Users/Chander/Documents/Eclipse Projects/Consultorio/Files/ConfigIni.dll");
			//a.setTexto("vol = BC14-40BA\nruta = C:/Documents and Settings/Ch@nder/Mis documentos/Programas/Eclipse/Consultorio/Files/ConfigIni.dll");
			//a.codificar(634, "ConfigIni.dll");
			a.setTexto("usuario = root\npass = chander");
			a.codificar(981,"ConfigUser.dll");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		/*decodificador a=new decodificador("ConfigIni.dll");
		System.out.println(a.desencriptar(0));*/
	}
	
	public void cambiarEstiloBasico(){
		JDialog.setDefaultLookAndFeelDecorated(true);
	}
	
	public void cambiarEstiloNimROD(){
		try {
			//UIManager.setLookAndFeel(new com.nilo.plaf.nimrod.NimRODLookAndFeel());
			//F:/Chander/Desktop/miTema.theme
			
			/*NimRODTheme nt = new NimRODTheme();
			nt.setPrimary1( new java.awt.Color(10,10,10));
			nt.setPrimary2( new java.awt.Color(20,20,20));
			nt.setPrimary3( new java.awt.Color(30,30,30));*/
			
			Principal p = new Principal();			
			String ruta = p.getClass().getResource("Files/miTemaNimrROD.theme").toString();
			ruta = ruta.replaceAll("file:/", "");
			ruta = ruta.replaceAll("%20", " ");
			
			NimRODTheme nt = new NimRODTheme(ruta);
			
			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			//NimRODLF.setCurrentTheme(nt);
			NimRODLookAndFeel.setCurrentTheme(nt);
			UIManager.setLookAndFeel( NimRODLF);

		} catch (UnsupportedLookAndFeelException e) {
			System.out.println("Error Look and Feel");
		}
		catch (Exception e) {
			System.out.println("Error Look and Feel");
		} 
	}
	
	public void cambiarEstiloWindows(){
		try {
			System.setProperty("sun.awt.noerasebackground", "true");
			//System.out.println(System.getProperty("sun.awt.noerasebackground"));
			WindowsLookAndFeel laf = new WindowsLookAndFeel();
			UIManager.setLookAndFeel(laf);
		} catch (Exception e) {
		}
	}
	
	public void cambiarEstiloJTatoo(){
		try{
			//McWinLookAndFeel laf = new McWinLookAndFeel();	
			//SmartLookAndFeel laf = new SmartLookAndFeel();		//OK
			//NoireLookAndFeel laf = new NoireLookAndFeel();		//NO
			//MintLookAndFeel laf = new MintLookAndFeel();			//OK
			//LunaLookAndFeel laf = new LunaLookAndFeel();			//OK
			//HiFiLookAndFeel laf = new HiFiLookAndFeel();			//NO
			//GraphiteLookAndFeel laf = new GraphiteLookAndFeel();	//OK
			//FastLookAndFeel laf = new FastLookAndFeel();			//NO
			//PulsarLookAndFeel laf = new PulsarLookAndFeel();		//OK
			//BernsteinLookAndFeel laf = new BernsteinLookAndFeel();//NO
			//AluminiumLookAndFeel laf = new AluminiumLookAndFeel();//NO
			//AeroLookAndFeel laf = new AeroLookAndFeel();			//OK
			AcrylLookAndFeel laf = new AcrylLookAndFeel();		//OK
			UIManager.setLookAndFeel(laf);
		}
		catch(Exception e){			
		}
	}
	
	public void cambiarEstiloTinyLAF(){
		try{
			Toolkit.getDefaultToolkit().setDynamicLayout(true);
			System.setProperty("sun.awt.noerasebackground", "true");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true); 

			
			Principal p = new Principal();			
			String ruta = p.getClass().getResource("Files/miTemaTinyLAF.theme").toString();
			ruta = ruta.replaceAll("file:/", "");
			ruta = ruta.replaceAll("%20", " ");
			
            ThemeDescription td = new ThemeDescription(new File(ruta).toURI());
            //System.out.println(td.getFile());
            Theme.loadTheme(td);

            
            UIManager.setLookAndFeel(new TinyLookAndFeel());
            //SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception e){
            System.out.println("Error TinyLAF");
        }
	}

}
