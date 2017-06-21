package GestionarConsultas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Entidades.Historias;
import Entidades.consultas;
import GestionarRecetas.GestorListadeRecetas;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;

public class GestorConsultaDetallada {
	
	private ConsultaDetallada detalle;
	private Autenticacion autenticar;
	private String numhis;
	private String fecha;
	private String fechaConsul;
	private consultas consulta;
	private String hora;
	private String nombre;
	private String edad;
	private int id_consulta;
	
	
	public GestorConsultaDetallada(ConsultaDetallada con, Autenticacion a, String numhis,
			String fecha, String hora){
		detalle = con;
		autenticar = a;
		this.numhis = numhis;
		this.hora = hora;		
		this.fecha = fecha.replaceAll("Fecha: ","");
		//System.out.println(this.fecha);
		consulta = new consultas(autenticar);
		inicializar();
		detalle.lbl_fecha.setText("Fecha: "+this.fecha);
		//con.setGestor(this);
	}
	
	public void inicializar(){
		Historias htra = new Historias(autenticar);
		htra.buscarHistoriaNumero(Integer.parseInt(numhis), false);
		nombre = htra.getNombre();
		String idSt = "000000"+htra.getId();
		idSt = idSt.substring(idSt.length()-6, idSt.length());
		detalle.t_historia.setText(idSt);
		detalle.getT_nombre().setText(htra.getNombre());
		try {
			detalle.getT_edad().setText(calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()),
					new SimpleDateFormat("dd-MM-yyyy").parse(fecha)));//System.out.println(fecha);
		edad = calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()),
				new SimpleDateFormat("dd-MM-yyyy").parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String fecha2="";
		fecha2 = UtilFechas.convertirFecha(fecha, UtilFechas.DD_MM_YYYY, UtilFechas.YYYY_MM_DD);
		fechaConsul = fecha2;
		/*try{
			java.util.Date d = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			String dd = String.valueOf(d.getDate());
			String mm = String.valueOf(d.getMonth()+1);
			String yy = String.valueOf(d.getYear()+1900);
			if(dd.length()==1){	dd = "0"+dd; }
			if(mm.length()==1){	mm = "0"+mm; }
			fecha2 = yy+"-"+mm+"-"+dd;
			fechaConsul = yy+"/"+mm+"/"+dd;
		} catch(Exception e){}*/
		if(consulta.buscar(numhis, fecha2, hora).compareTo("FALLO")==0)
			System.out.println("No se pudo buscar la consulta. GestroConsultaDetallada inicializar");
		id_consulta = consulta.getId();
		System.out.println(consulta.getId());
		detalle.getT_peso().setText(consulta.getPeso());
		detalle.getT_talla().setText(consulta.getTalla());
		detalle.getT_saturacion().setText(consulta.getSaturacion());
		detalle.getT_indice().setText(consulta.getMasa());
		detalle.getT_motivoActual().setText(consulta.getMotivo());
		detalle.getT_diagnosticoActual().setText(consulta.getDiagnostico());
		detalle.getT_observaciones().setText(consulta.getObservaciones());
		detalle.getT_temperatura().setText(consulta.getTemperatura());
		detalle.getT_fc().setText(consulta.getFc());
		detalle.getT_frecuenciaRespiratoria().setText(consulta.getFr());
		detalle.getT_tas().setText(consulta.getTas());
		detalle.getT_tad().setText(consulta.getTad());
		detalle.getT_pf().setText(consulta.getPf());
		
	}
	
	public String calcularEdad (Date birth, Date d) {
		if(d == null){
			d = new Date();
		}
		
		//System.out.println(""+birth+"-"+d);

		SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
		SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
		SimpleDateFormat sdfAño = new SimpleDateFormat("yyyy");

		int a = Integer.parseInt(sdfAño.format(d)) - Integer.parseInt(sdfAño.format(birth));
		int b = Integer.parseInt(sdfMes.format(d)) - Integer.parseInt(sdfMes.format(birth));
		int c = Integer.parseInt(sdfDia.format(d)) - Integer.parseInt(sdfDia.format(birth));

		if (b < 0) {
			a = a -1;
			b = 12 + b;
		}

		if (c < 0) {
			b = b - 1;
			switch (Integer.parseInt(sdfMes.format(d))) {
				case 2:
					int año = Integer.parseInt(sdfAño.format(d));
					if ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0)))
						c = 29 + c;
					else
						c = 28 + c;
					break;
				case 4:
				case 6:
				case 9:
				case 10:
				case 11: c = 30 + c;
					break;
				case 1:
				case 3:	
				case 5:
				case 7:
				case 8:
				case 12: c = 31 + c;
					break;
			}
                        if (b < 0) {
                            a = a -1;
                            b = 12 + b;
                        }
		}
	
		String edad, anhos="", meses="", dias="";
		if(a>0){
			if(a==1){
				anhos = String.valueOf(a) + " año  ";
			}
			else{
				anhos = String.valueOf(a) + " años  ";
			}
		}
		if(b>0){
			if(b==1){
				meses =  String.valueOf(b) + " mes  ";
			}
			else{
				meses =  String.valueOf(b) + " meses  ";
			}
		}
		if(c!=0){
			if(c==1){
				dias = String.valueOf(c) + " dia";
			}
			else{
				dias = String.valueOf(c) + " dias";
			}
		}
		if(a==0 && b==0 && c==0){
			dias = "Hoy";
		}
		edad = anhos + meses + dias;
		return edad;
	}
	
	public String getFecha(){
		return fecha;
	}
	
	public void verReceta(){
		GestorListadeRecetas obj = new GestorListadeRecetas(autenticar,detalle.getVentana().getDesktopPane());
		obj.setNombre(nombre);
		obj.setEdad(edad);
		obj.setFecha(fechaConsul);
		obj.cargar(id_consulta);
		obj.setHabilitacionBotones(false);
	}

}
