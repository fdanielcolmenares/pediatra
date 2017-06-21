package Utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilFechas {
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static String convertirFecha(String fecha, String formatoOrigen, String formatoDestino){
		try {
			Date d = new SimpleDateFormat(formatoOrigen).parse(fecha);
			return convertirFecha(d, formatoDestino);
		}
		catch (ParseException e) {
		}
		return "";
	}
	
	public static String convertirFecha(Date d, String formatoDestino){
		String dd = String.valueOf(d.getDate());
		String mm = String.valueOf(d.getMonth()+1);
		String yy = String.valueOf(d.getYear()+1900);
		if(dd.length()==1){ dd = "0"+dd; }
		if(mm.length()==1){ mm = "0"+mm; }
		String fecha = "";
		
		if(formatoDestino.compareToIgnoreCase("dd-MM-yyyy")==0)
			fecha = dd+"-"+mm+"-"+yy;
		else
			fecha = yy+"-"+mm+"-"+dd;
		return fecha;
	}
	
	public static String getHoraActual(){
		Calendar c = Calendar.getInstance();
		String h = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String m = String.valueOf(c.get(Calendar.MINUTE));
		String s = String.valueOf(c.get(Calendar.SECOND));
		
		if(h.length()==1)
			h = "0"+h;
		if(m.length()==1)
			m = "0"+m;
		if(s.length()==1)
			s = "0"+s;
		
		String hora;
		hora = ""+h+":"+m+":"+s;
		
		return hora;
	}
}
