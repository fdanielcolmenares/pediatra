import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class principal {

	/**
	 * @param args
	 */
	static ScannerTab scaner=null;
	public static void main(String[] args) {
		ImageIO.scanForPlugins();
		scaner=new ScannerTab();
		scaner.setButtonPanel(new JPanel());
                
                System.out.println("Fin");
	}

}
