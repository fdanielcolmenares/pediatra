package GestionImpresiones;

import Utilitario.Path;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class GenerarReportesDocx {
    private String nombre;
    private String fecha;
    private String representante;
    private String CIRepresentante;
    private String edad;
    private String enfermedad;
    private String cuida;
    private String dias;
    private String Titulo;
    private String Texto;
    private String Informacion;
   

    private String ruta;
    private int tipoReporte;

    public int ALIMENTACION_6_8_MESES = 1;
    public int ALIMENTACION_8_12_MESES = 2;
    public int INFORME_MEDICO = 3;
    public int VITAMINAS_MADRE = 4;
    public int ESQUEMA_CRISIS = 5;
    public int ALIMENTACION_12_MESES = 6;

    public GenerarReportesDocx(){        
        ruta = "";
        limpiarParametros();
        tipoReporte = -1;
    }

    public int generarReporte(){
        int retorna = 0;
        File f = new File(ruta+".docx");
        if(f.exists()){
            f.delete();
        }
        if(tipoReporte == ALIMENTACION_6_8_MESES){
            retorna = generarReporteAlimentacion_6_8_meses();
        }
        if(tipoReporte == ALIMENTACION_8_12_MESES){
            retorna = generarReporteAlimentacion_8_12_meses();
        }
        if(tipoReporte == ESQUEMA_CRISIS){
            retorna = generarReporteEsquemaCrisis();
        }
        if(tipoReporte == INFORME_MEDICO){
            retorna = generarReporteInformeMedico();
        }
        if(tipoReporte == VITAMINAS_MADRE){
            retorna = generarReporteVitaminasMadre();
        }
        if(tipoReporte == ALIMENTACION_12_MESES){
            retorna = generarReporteAlimentacion_12_meses();
        }

        return retorna;
    }

    private int generarReporteAlimentacion_6_8_meses(){
       this.Titulo = "ALIMENTACIÓN DE LOS 6 MESES";        
       this.Texto = "\nINICIAR ALIMENTACION NO LACTEA.\n\n";
       
       Texto = Texto + "SOPAS: Preparar el consomé con pechuga de pollo (sin piel) o lagarto. "
       +"Agregar una cucharada de aceite de Canola (PURILET) Y/O Aceite "
       +"de Oliva, y sal yodada al gusto. Después de la primera semana "
       +"se van agregando una por una las verduras (papa - plátano – apio - "
       +"zanahoria- ahuyama) para luego usarlas juntas.\n\n";
       
       Texto = Texto + "JUGOS: Se dan 5 días seguidos la misma fruta con cucharilla sin azúcar, "
       +"comenzando muy diluido, e ir aumentando la concentración de la fruta "
       +"hasta llegar a la compota para probar la tolerancia. Se comienza con "
       +"frutas no cítricas, (lechosa - melón - patilla - manzana - pera - durazno, "
       +"etc.), hasta llegar a las cítricas (naranja - mango - piña - parchita - limón). "
       +"Esta alimentación es desde los 6 hasta los 8 meses.\n\n";
       
       this.Informacion = nombre.toUpperCase()+"\n";
       Informacion = Informacion + "San Cristóbal,"+fecha;
       Map map = new HashMap();
       map.put("TITULO", Titulo);
       map.put("TEXTO", Texto);
       map.put("INFORMACION", Informacion);
       this.GenerarDoc(map);    	   
       return 0;
    }

    
    private int generarReporteAlimentacion_8_12_meses(){
    	Titulo = "ALIMENTACIÓN DE LOS 8 MESES";
       	Texto = "\nINICIAR ALIMENTACIÓN NO LACTEA DESPUÉS DE LOS 8 MESES\n";
       	
       	Texto = Texto + "SOPAS: Preparar el consomé con pechuga de pollo (sin piel) o lagarto. Agregar una "
        +"cucharada de aceite de Canola (PURILET), y sal yodada al gusto. Después "
        +"de la primera semana se van agregando una por una las verduras (papa - "
        +"plátano - apio) para luego usarlas juntas.\n";
       	
       	Texto = Texto +"JUGOS: Se dan 5 días seguidos la misma fruta con cucharilla sin azúcar, comenzando muy "
        +"diluido, e ir aumentando la concentración de la fruta hasta llegar a la compota "
        +"para probar la tolerancia. Se comienza con frutas no cítricas, (lechosa - melón "
        +"- patilla - manzana - pera- durazno, etc.), hasta llegar a las cítricas (naranja - "
        +"mango - piña - Parchita - limón).\n";
       	
       	Texto = Texto + "HARINAS: Dar arepas, pasta, pan, galletas diariamente (variar)\n";
       	
       	Texto = Texto + "GRANOS: Dar arvejas, lentejas, caraotas, fríjoles, 3 veces a la semana\n";
       	
       	Texto = Texto + "HIGADO: Dar 1 vez al semana frito o en salsa.\n";
       	
       	Texto = Texto + "LACTEOS: Como el yogurt, la mantequilla, y la margarita diariamente (variar).\n";
         
       	Texto = Texto + "NOTA: No dar huevos, pescado, cochino, chocolates antes del año de edad.\n\n";
       
       	this.Informacion = nombre.toUpperCase()+"\n";
        Informacion = Informacion + "San Cristóbal, "+fecha;     
        Map map = new HashMap();
        map.put("TITULO", Titulo);
        map.put("TEXTO", Texto);
        map.put("INFORMACION", Informacion);
        this.GenerarDoc(map);    	   
        return 0;      
       
     }
    
    public int generarReporteAlimentacion_12_meses(){
    	Titulo = "\nALIMENTACION DESPUES DEL AÑO DE EDAD\n";
    	
    	Texto = "- No dar ningun alimento en TETERO.\n";
    	Texto = Texto + "- Dar yogur liquido o firme en un vaso todos los dias.\n";
    	Texto = Texto + "- Dar yogur liquido o firme en un vaso todos los dias.\n";
    	Texto = Texto + "- Dar Huevo (cocido,frito,revuelto) diario.\n";
    	Texto = Texto + "- Dar Pescado (atun,sardinas,filete) 3 veces a la semana.\n";
    	Texto = Texto + "- Evitar los chocolates, Leche quesos en general principalmente los amarillos, y frutas secas.\n";
    	Texto = Texto + "- Dar granos (caraotas, arvejas,lentejas) 3 veces a la semana.\n";
    	Texto = Texto + "- Demas alimetos de acuerdo a las costumbres familiares.\n";
    	Texto = Texto + "- Dar higado bien asado 1 vez a la semana.\n";
    	Texto = Texto + "- Dar frutas Citricas y vegetales diariamente.\n\n";
       
    	this.Informacion = nombre.toUpperCase()+"\n";
        Informacion = Informacion + "San Cristóbal, "+fecha;     
        Map map = new HashMap();
        map.put("TITULO", Titulo);
        map.put("TEXTO", Texto);
        map.put("INFORMACION", Informacion);
        this.GenerarDoc(map);    	   
        return 0;      
       
    }

    private int generarReporteInformeMedico(){
    	Titulo = "\nINFORME MÉDICO\n";
    	
    	Texto = "Paciente:  "+nombre.toUpperCase()+" de "+edad+" de edad, "
        +"que ha sido traído a esta consulta, por "
        +representante.toUpperCase()+" con C.I. "+CIRepresentante.toUpperCase()
        +" por presentar: "+enfermedad.toUpperCase()+". "
        +"\n\tEl paciente amerita de tratamiento médico ambulatorio  "
        +"para mantener en buen estado su salud y así evitar "
        +"recaídas y/o complicaciones. "
        +"El paciente amerita reposo y los cuidados estrictos de su "
        +cuida.toUpperCase()+" por "+dias+" contados a partir de la fecha.\n\n\n";
    	
    	this.Informacion = nombre.toUpperCase()+"\n";
        Informacion = Informacion + "San Cristóbal, "+fecha;     
        Map map = new HashMap();
        map.put("TITULO", Titulo);
        map.put("TEXTO", Texto);
        map.put("INFORMACION", Informacion);
        this.GenerarDoc(map);    	   
        return 0;     
    }

    private int generarReporteVitaminasMadre(){
    	Titulo = "\nVITAMINAS PARA LA MADRE\n\n";
    	Texto = "OMEGA 3 (CÁPSULAS):\n";
    	Texto = Texto + "Tomar 1 cápsula 3 veces  al día con las comidas por 1 mes.\n\n";
    	Texto = Texto + "DAILY (TABLETAS):\n";
    	Texto = Texto + "Tomar 1 tableta 3 veces al día con las comidas por 3 meses.\n\n";
    	Texto = Texto + "CALMAG (TABLETAS):\n";
    	Texto = Texto + "Tomar 2 tabletas 3 veces al día con las comidas por 2 meses.\n\n";
    	Texto = Texto + "PROTEINA NATURAL (POLVO):\n";
    	Texto = Texto + "Tomar 1 medida 2 veces al día por 2 meses.\n\n";
    	
    	this.Informacion = nombre.toUpperCase()+"\n";
        Informacion = Informacion + "San Cristóbal, "+fecha;     
        Map map = new HashMap();
        map.put("TITULO", Titulo);
        map.put("TEXTO", Texto);
        map.put("INFORMACION", Informacion);
        this.GenerarDoc(map);    	   
        return 0; 

    }

      private int generarReporteEsquemaCrisis(){
    	  Titulo = "\nESQUEMA DE CRISIS\n\n";
    	  Texto = "\tEn caso de  tos continua, dificultad para respirar, silbido "
          +"en el pecho aplicar con:\n";
    	  Texto = Texto + "SALBUTAN";
    	  Texto = Texto + "- 3 disparos cada 30 minutos por 3 veces.\n\n";
    	  Texto = Texto + "- 3 disparos cada 2 horas por 3 veces.\n\n";
    	  Texto = Texto + "- 3 disparos cada 4 horas por 24 horas.\n\n";
    	  Texto = Texto + "- 3 disparos cada 6 horas por 48 horas.\n\n";
    	  Texto = Texto + "- 3 disparos tres veces al día por el tiempo "
          +"que dure la tos, más dos días.\n\n";
       
    	  this.Informacion = nombre.toUpperCase()+"\n";
          Informacion = Informacion + "San Cristóbal, "+fecha;     
          Map map = new HashMap();
          map.put("TITULO", Titulo);
          map.put("TEXTO", Texto);
          map.put("INFORMACION", Informacion);
          this.GenerarDoc(map);    	   
          return 0; 
    }

    public HashMap getTexto(String txt, String alineacion, boolean subrayado, boolean negrita, int size){
        HashMap datos = new HashMap();

        datos.put("text",txt);
        datos.put("jc", alineacion);
        datos.put("sz", String.valueOf(size));
        if(subrayado)
            datos.put("u", "single");
        if(negrita)
            datos.put("b", "single");

        return datos;
    }

    public HashMap getMargenes(){
        HashMap margenes = new HashMap();
        margenes.put("top", String.valueOf(4*566.9291));
        margenes.put("bottom", String.valueOf(11*566.9291));
        margenes.put("right", String.valueOf(7*566.9291));
        margenes.put("left", String.valueOf(2*566.9291));

        return margenes;
    }

    public void limpiarParametros(){
        nombre = "";
        fecha = "";
        representante = "";
        CIRepresentante = "";
        edad  = "";
        enfermedad = "";
        cuida = "";
        dias = "";
    }

    public void abrir(){
        try{
            Desktop.getDesktop().open(new File(ruta+".doc"));
        }
        catch(Exception e){
        }
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public void setCIRepresentante(String CIRepresentante) {
        this.CIRepresentante = CIRepresentante;
    }

    public void setCuida(String cuida) {
        this.cuida = cuida;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
    
    public void GenerarDoc(Map paMap){
    	JRDataSource dataSource = null;    	
    try{	
    	JasperReport reporte = (JasperReport) JRLoader.loadObject(Path.getPath()+"Files/jasper/PackReporteOne.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,paMap,dataSource);
		JRDocxExporter docxExporter = new JRDocxExporter();
	    File archivo = new File(this.ruta+".doc");
	    FileOutputStream outFile = new FileOutputStream(archivo);
	    docxExporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, jasperPrint);
	    docxExporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
	    docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, outFile);
	    docxExporter.exportReport();
	    outFile.close();   
	   // this.abrir();
    }catch(Exception e){
    		e.printStackTrace();
    }
    }
}