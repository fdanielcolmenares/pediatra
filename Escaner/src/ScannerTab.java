import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import uk.co.mmscomputing.device.scanner.*;
import uk.co.mmscomputing.device.twain.*;

// Referenced classes of package uk.co.mmscomputing.application.imageviewer:
//            ImageTab

public class ScannerTab implements ScannerListener
{

    public ScannerTab()
    {
        //super(properties);
    }

    protected void setButtonPanel(JPanel jpanel)
    {
        //super.setButtonPanel(jpanel);
        try
        {
            scanner = Scanner.getDevice();
            if(scanner != null)
            {
                jpanel.add(scanner.getScanGUI());
                scanner.addListener(this);
                if(scanner instanceof TwainScanner)
                {
                    TwainIdentity atwainidentity[] = ((TwainScanner)scanner).getIdentities();
                    for(int i = 0; i < atwainidentity.length; i++)
                        System.out.println(atwainidentity[i].toString());

                }
                //scanner.select();for(int i=0;i<2000000;i++);for(int i=0;i<2000000;i++);
                scanner.acquire();
		for(int i=0;i<20000;i++);
		while(scanner.isBusy());
            }
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".setButtonPanel:\n\t").append(exception).toString());
        }
    }

    public void update(uk.co.mmscomputing.device.scanner.ScannerIOMetadata.Type type, ScannerIOMetadata scanneriometadata)
    {
        if(type.equals(ScannerIOMetadata.ACQUIRED))
        {
            final BufferedImage image = scanneriometadata.getImage();
            (new Thread() {

                public void run()
                {
                	System.out.println("escanee");
                	//final BufferedImage image=scanneriometadata.getImage();
                    System.out.println("Have image "+1+" now! ");
                    try{
                      ImageIO.write(image, "jpeg", new File("mmsc_image"+(1)+".jpeg"));
                    }catch(Exception e){
                      e.printStackTrace();
                    }
                	/*  try
                    {
                        addImage((new StringBuilder()).append("scan_").append(ScannerTab.no++).toString(), image);
                    }
                    catch(Exception exception3)
                    {
                        System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".update:\n\t").append(exception3).toString());
                        System.err.println((new StringBuilder()).append(getClass().getName()).append(".update:\n\t").append(exception3).toString());
                        exception3.printStackTrace();
                    }*/
                }

                //final BufferedImage val$image=null;
                final ScannerTab this$0;

            
            {
                this$0 = ScannerTab.this;
             //   image = bufferedimage;
             //   super();
            }
            }).start();
        } else
        if(type.equals(ScannerIOMetadata.FILE))
        {
            final File file = scanneriometadata.getFile();
            (
                    new Thread() {

                public void run()
                {
                   // open(file.getPath());
                    if(!file.delete())
                    {
                        System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                        System.err.println((new StringBuilder()).append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                    }
                //    break MISSING_BLOCK_LABEL_394;
                    Exception exception3 = null;
                    //exception3;
                    System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".update:\n\t").append(exception3).toString());
                    System.err.println((new StringBuilder()).append(getClass().getName()).append(".update:\n\t").append(exception3).toString());
               //     exception3.printStackTrace();
                    if(!file.delete())
                    {
                        System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                        System.err.println((new StringBuilder()).append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                    }
           //         break MISSING_BLOCK_LABEL_394;
                    Exception exception4;
                   // exception4;
                    if(!file.delete())
                    {
                        System.out.println((new StringBuilder()).append("9\b").append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                        System.err.println((new StringBuilder()).append(getClass().getName()).append(".update:\n\tCould not delete: ").append(file.getPath()).toString());
                    }
               //     throw exception4;
                }

                //final File val$file=null;
                final ScannerTab this$0;

            
            {
                this$0 = ScannerTab.this;
             //   file = file1;
        //        super();
            }
            }).start();
        } else
        if(type.equals(ScannerIOMetadata.NEGOTIATE))
            negotiate(scanneriometadata);
        else
        if(type.equals(ScannerIOMetadata.STATECHANGE))
        {
            System.out.println((new StringBuilder()).append("Scanner State ").append(scanneriometadata.getStateStr()).toString());
            System.err.println((new StringBuilder()).append("Scanner State ").append(scanneriometadata.getStateStr()).toString());
            if(scanneriometadata instanceof TwainIOMetadata)
                if(scanneriometadata.isState(6))
                {
                    TwainSource twainsource = ((TwainIOMetadata)scanneriometadata).getSource();
                    try
                    {
                        TwainImageInfo twainimageinfo = new TwainImageInfo(twainsource);
                        twainimageinfo.get();
                        System.out.println(twainimageinfo.toString());
                    }
                    catch(Exception exception)
                    {
                        System.out.println((new StringBuilder()).append("3\b").append(getClass().getName()).append(".update:\n\tCannot retrieve image information.\n\t").append(exception).toString());
                    }
                    try
                    {
                        TwainImageLayout twainimagelayout = new TwainImageLayout(twainsource);
                        twainimagelayout.get();
                        System.out.println(twainimagelayout.toString());
                    }
                    catch(Exception exception1)
                    {
                        System.out.println((new StringBuilder()).append("3\b").append(getClass().getName()).append(".update:\n\tCannot retrieve image layout.\n\t").append(exception1).toString());
                    }
                } else
                if(scanneriometadata.isState(7))
                {
                    TwainSource twainsource1 = ((TwainIOMetadata)scanneriometadata).getSource();
                    try
                    {
                        int ai[] = new int[64];
                        for(int i = 0; i < ai.length; i++)
                            ai[i] = 4608 + i;

                        TwainExtImageInfo twainextimageinfo = new TwainExtImageInfo(twainsource1, ai);
                        twainextimageinfo.get();
                        System.out.println(twainextimageinfo.toString());
                    }
                    catch(Exception exception2)
                    {
                        System.out.println((new StringBuilder()).append("3\b").append(getClass().getName()).append(".update:\n\tCannot retrieve extra image information.\n\t").append(exception2).toString());
                    }
                }
        } else
        if(type.equals(ScannerIOMetadata.INFO))
            System.out.println(scanneriometadata.getInfo());
        else
        if(type.equals(ScannerIOMetadata.EXCEPTION))
        {
            System.out.println((new StringBuilder()).append("9\b").append(scanneriometadata.getException().getMessage()).toString());
            scanneriometadata.getException().printStackTrace();
        }
    }

    private void negotiate(ScannerIOMetadata scanneriometadata)
    {
        if(scanneriometadata instanceof TwainIOMetadata)
        {
            TwainSource twainsource = ((TwainIOMetadata)scanneriometadata).getSource();
            try
            {
                TwainCapability atwaincapability[] = twainsource.getCapabilities();
                for(int i = 0; i < atwaincapability.length; i++)
                    System.out.println(atwaincapability[i].toString());

            }
            catch(Exception exception)
            {
                System.out.println((new StringBuilder()).append("9\b").append(exception.getMessage()).toString());
            }
        }
    }

    public void stop()
    {
        if(scanner != null)
            scanner.waitToExit();
    }

    private static int no = 1;
    private Scanner scanner;


}