package GestionarConsultas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Entidades.Historias;
import Entidades.consultas;
import GestionarHistorias.CrearHistoria;
import GestionarRecetas.GestorListadeRecetas;
import Utilitario.Autenticacion;

public class GestorCrearConsulta {
	
	public Autenticacion autenticacion;
	private consultas consulta;
	private CrearConsulta ventana;
	private CrearHistoria ventana1;
	private JDesktopPane desktopPane;
	private  int primeraConsulta;
	private int ultimaConsulta;
	private String nombre;
	private String edad;
	private String fechaIngreso;
	private String fechaUltima;
	
	public GestorCrearConsulta(CrearConsulta v, Autenticacion a, JDesktopPane panel){
		ventana = v;
		autenticacion = a;
		primeraConsulta = -1;
		ultimaConsulta = -1;
		ventana.setNivelUsuario(autenticacion.getTipoUsuario());
		desktopPane = panel;
		consulta = new consultas(autenticacion);
		ventana.habilitarBotones(false);
		
	}
	
	public GestorCrearConsulta(CrearHistoria v, Autenticacion a, JDesktopPane panel){
		ventana1 = v;
		autenticacion = a;
		primeraConsulta = -1;
		ultimaConsulta = -1;
		ventana1.setNivelUsuario(autenticacion.getTipoUsuario());
		desktopPane = panel;
		consulta = new consultas(autenticacion);
	}
	
	public int getPrimeraConsulta() {
		return primeraConsulta;
	}

	public void setPrimeraConsulta(int primeraConsulta) {
		this.primeraConsulta = primeraConsulta;
	}

	public int getUltimaConsulta() {
		return ultimaConsulta;
	}

	public void setUltimaConsulta(int ultimaConsulta) {
		this.ultimaConsulta = ultimaConsulta;
	}

	public void setAutenticacion(Autenticacion param){
		autenticacion = param;
		consulta.setAutenticacion(param);
		ventana.setNivelUsuario(autenticacion.getTipoUsuario());
	}
	
	public String validarTextField (JTextField campo){
		if(campo.getText().compareTo("")==0){
			campo.setText("[S/I]");
			//campo.setText("---");
			return "FALLO";
		}
		return "OK";
	}
	
	public String validarTextArea (JTextArea campo){
		if(campo.getText().compareTo("")==0){
			campo.setText("[Sin Información]");
			//campo.setText("---");
			return "FALLO";
		}
		return "OK";
	}
	
	public String Guardar(boolean nuevo,String numhis,int edo){
		boolean ban=false;
		String res="";
		
		if(nuevo){
			consulta.buscarNuevoID();
		}
		consulta.setEstado(edo);//1 bloqueado 0 libre
		if(validarTextField(ventana.getT_peso()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_saturacion()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_talla()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_indice()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_temperatura()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_tas()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_tad()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_fc()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_frecuenciaRespiratoria()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana.getT_pf()).compareTo("FALLO")==0)
			ban=true;		
		if(validarTextArea(ventana.getT_motivoActual()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextArea(ventana.getT_diagnosticoActual()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextArea(ventana.getT_observaciones()).compareTo("FALLO")==0)
			ban=true;
		consulta.setPeso(ventana.getT_peso().getText());
		consulta.setSaturacion(ventana.getT_saturacion().getText());
		consulta.setTalla(ventana.getT_talla().getText());
		consulta.setMasa(ventana.getT_indice().getText());
		consulta.setMotivo(ventana.getT_motivoActual().getText());
		consulta.setDiagnostico(ventana.getT_diagnosticoActual().getText());
		consulta.setObservaciones(ventana.getT_observaciones().getText());
		consulta.setHtra_id(Integer.parseInt(numhis));
		consulta.setTcta_id(1);//preguntar que es tipo de consuta
		consulta.setTemperatura(ventana.getT_temperatura().getText());
		consulta.setFc(ventana.getT_fc().getText());
		consulta.setFr(ventana.getT_frecuenciaRespiratoria().getText());
		consulta.setTas(ventana.getT_tas().getText());
		consulta.setTad(ventana.getT_tad().getText());
		consulta.setPf(ventana.getT_pf().getText());
		
		
		if(!ban){
			if(nuevo)
				res=consulta.guardar();
			else
				res=consulta.actualiza();
		}
		else{
			if (JOptionPane.showConfirmDialog(ventana.getVentana(),
					"No fueron completados todos los campo \n" +
					"¿Seguro que desea guardar?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
				if(nuevo)
					res=consulta.guardar();
				else
					res=consulta.actualiza();
	        }
		}
		
		if(res.compareTo("OK")==0){
			JOptionPane.showMessageDialog(ventana.getVentana(), "Guardado Exitosamente", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			//ventana.getVentana().dispose();
			ventana.habilitarBotones(true);
		}
		if(res.compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(ventana.getVentana(), "No se pudo guardar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			return "FALLO";
		}
		
		return "OK";
	}
	
	public String Guardar2(boolean nuevo,String numhis, int edo){
		boolean ban=false;
		String res="";
		//System.out.println("hola: "+ nuevo);
		if(nuevo){
			consulta.buscarNuevoID(); //System.out.println("entre en nuevo");
		}
		consulta.setEstado(edo);//1 bloqueado 0 libre
		if(validarTextField(ventana1.getT_peso1()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_saturacion()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_talla1()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_temperatura()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_tas()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_tad()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_fc()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_frecuenciaRespiratoria()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_indice()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextArea(ventana1.getT_motivoActual()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextArea(ventana1.getT_diagnosticoActual()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextArea(ventana1.getT_observaciones1()).compareTo("FALLO")==0)
			ban=true;
		if(validarTextField(ventana1.getT_pf()).compareTo("FALLO")==0)
			ban=true;
		consulta.setPeso(ventana1.getT_peso1().getText());
		consulta.setSaturacion(ventana1.getT_saturacion().getText());
		consulta.setTalla(ventana1.getT_talla1().getText());
		consulta.setMasa(ventana1.getT_indice().getText());
		consulta.setMotivo(ventana1.getT_motivoActual().getText());
		consulta.setDiagnostico(ventana1.getT_diagnosticoActual().getText());
		consulta.setObservaciones(ventana1.getT_observaciones1().getText());
		consulta.setHtra_id(Integer.parseInt(numhis));
		consulta.setTcta_id(1);//preguntar que es tipo de consulta
		consulta.setTemperatura(ventana1.getT_temperatura().getText());
		consulta.setFc(ventana1.getT_fc().getText());
		consulta.setFr(ventana1.getT_frecuenciaRespiratoria().getText());
		consulta.setTas(ventana1.getT_tas().getText());
		consulta.setTad(ventana1.getT_tad().getText());
		consulta.setPf(ventana1.getT_pf().getText());
				
		//if(!ban){
			if(nuevo)
				res=consulta.guardar();
			else
				res=consulta.actualiza();
		//}
		/*else{
			if (JOptionPane.showConfirmDialog(ventana1.getVentana(),
					"No fueron completados todos los campos en la consulta de ingreso \n" +
					"¿Seguro que desea guardar?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
				if(nuevo)
					res=consulta.guardar();
				else
					res=consulta.actualiza();
	        }
		}*/
		
		if(res.compareTo("OK")==0){
			//JOptionPane.showMessageDialog(ventana1.getVentana(), "Guardado Exitosamente", "Mensaje",
              //      JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image006.png")));
			//ventana.getVentana().dispose();
			//ventana1.habilitarBotones(true);
			ventana1.getB_Receta().setEnabled(true);
			try{
				Historias htras = new Historias(autenticacion);
				htras.buscarHistoriaNumero(Integer.parseInt(numhis), false);
				
				Date d = new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getFecha().replaceAll("Fecha: ", ""));
				
				ventana1.getT_edad1().setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htras.getFecha_nacimiento()), d));
			
			
			}
			catch(Exception exp){
				exp.printStackTrace();
			}
			
		}
		if(res.compareTo("FALLO")==0){
			JOptionPane.showMessageDialog(ventana1.getVentana(), "No se pudo guardar la consulta de ingreso", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
			return "FALLO";
		}
		
		return "OK";
	}
	
	public String llenarPrimera(String numhis){
		//ventana.habilitarBotones(true);
		Historias htra = new Historias(autenticacion);
		htra.buscarHistoriaNumero(Integer.parseInt(numhis), false);
		
		if(consulta.primera(numhis).compareTo("FALLO")==0)
			System.out.println("error llenar primera datos vacios GestorCrearConsulta");
		//ventana.t_fechaIngreso.setText(consulta.getFecha());
		ventana.hora=consulta.getHora();
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getFecha().replaceAll("Fecha: ", ""));
			String dd = String.valueOf(d.getDate());
			String mm = String.valueOf(d.getMonth()+1);
			String yy = String.valueOf(d.getYear()+1900);
			if(dd.length()==1){ dd = "0"+dd; }
			if(mm.length()==1){ mm = "0"+mm; }
			ventana.t_fechaIngreso.setText("Fecha: "+dd+"-"+mm+"-"+yy);
			fechaIngreso = dd+"/"+mm+"/"+yy;
			ventana.t_edadIngreso.setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new SimpleDateFormat("dd-MM-yyyy").parse(dd+"-"+mm+"-"+yy)));
			edad = calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new SimpleDateFormat("dd-MM-yyyy").parse(dd+"-"+mm+"-"+yy));
			//System.out.println("Prim***"+consulta.getId());
			primeraConsulta = consulta.getId();
			//ventana.t_edadIngreso.setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), null));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ventana.getT_motivoIngreso().setText(consulta.getMotivo());
		ventana.getT_diagnosticoIngreso().setText(consulta.getDiagnostico());
		return consulta.getFecha()+" "+consulta.getHora();
	}
	
	public String llenarPrimera2(String numhis){
		//ventana.habilitarBotones(true);
		Historias htra = new Historias(autenticacion);
		htra.buscarHistoriaNumero(Integer.parseInt(numhis), false);
		
		if(consulta.primera(numhis).compareTo("FALLO")==0)
			System.out.println("error llenar primera datos vacios GestorCrearConsulta");
		//ventana.t_fechaIngreso.setText(consulta.getFecha());
		//ventana.hora=consulta.getHora();
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getFecha().replaceAll("Fecha: ", ""));
			String dd = String.valueOf(d.getDate());
			String mm = String.valueOf(d.getMonth()+1);
			String yy = String.valueOf(d.getYear()+1900);
			if(dd.length()==1){ dd = "0"+dd; }
			if(mm.length()==1){ mm = "0"+mm; }
			//ventana.t_fechaIngreso.setText("Fecha: "+dd+"-"+mm+"-"+yy);
			fechaIngreso = dd+"/"+mm+"/"+yy;
			ventana1.getT_edad1().setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new SimpleDateFormat("dd-MM-yyyy").parse(dd+"-"+mm+"-"+yy)));
			edad = calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new SimpleDateFormat("dd-MM-yyyy").parse(dd+"-"+mm+"-"+yy));
			//System.out.println("Prim***"+consulta.getId());
			primeraConsulta = consulta.getId();
			//ventana.t_edadIngreso.setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), null));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ventana1.getT_motivoActual().setText(consulta.getMotivo());
		ventana1.getT_diagnosticoActual().setText(consulta.getDiagnostico());
		ventana1.getT_observaciones1().setText(consulta.getObservaciones());
		ventana1.getT_peso1().setText(consulta.getPeso());
		ventana1.getT_talla1().setText(consulta.getTalla());
		ventana1.getT_fc().setText(consulta.getFc());
		ventana1.getT_saturacion().setText(consulta.getSaturacion());
		ventana1.getT_indice().setText(consulta.getMasa());
		ventana1.getT_temperatura().setText(consulta.getTemperatura());
		ventana1.getT_frecuenciaRespiratoria().setText(consulta.getFr());
		ventana1.getT_tas().setText(consulta.getTas());
		ventana1.getT_tad().setText(consulta.getTad());
		ventana1.getT_pf().setText(consulta.getPf());
		return consulta.getFecha()+" "+consulta.getHora();
	}
	
	public String llenarUltima(String numhis, String param){		
		Historias htra = new Historias(autenticacion);
		htra.buscarHistoriaNumero(Integer.parseInt(numhis), false);
		if(consulta.ultima(numhis).compareTo("FALLO")==0)
			System.out.println("error llenar ultima datos vacios GestorCrearConsulta");
		if(param.compareToIgnoreCase(consulta.getFecha()+"-"+consulta.getHora())==0){
			consulta.ultima("-1");
		}
		ventana.hora1=consulta.getHora();
		System.out.println("***"+consulta.getId());
		ultimaConsulta = consulta.getId();
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getFecha().replaceAll("Fecha: ", ""));
			String dd = String.valueOf(d.getDate());
			String mm = String.valueOf(d.getMonth()+1);
			String yy = String.valueOf(d.getYear()+1900);
			if(dd.length()==1){ dd = "0"+dd; }
			if(mm.length()==1){ mm = "0"+mm; }
			ventana.t_fechaUltima.setText("Fecha: "+dd+"-"+mm+"-"+yy);
			fechaUltima = dd+"/"+mm+"/"+yy;
			ventana.t_edadUltima.setText("Edad: "+calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new SimpleDateFormat("dd-MM-yyyy").parse(dd+"-"+mm+"-"+yy)));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ventana.getT_motivoUltima().setText(consulta.getMotivo());
		ventana.getT_diagnosticoUltima().setText(consulta.getDiagnostico());
		
		if(ventana.t_fechaIngreso.getText().compareTo("Fecha: 00/00/0000")==0){
			ventana.getBtn_consultaIngreso().setEnabled(false);
			ventana.getBtn_tratamientoIngreso().setEnabled(false);
		}
		if(ventana.t_fechaUltima.getText().compareTo("Fecha: 00/00/0000")==0){
			ventana.getBtn_ultimaConsulta().setEnabled(false);
			ventana.getBtn_ultimoTratamiento().setEnabled(false);
		}
		
		return "OK";
	}
	
	public String calcularEdad (Date birth, Date d) {
		if(d == null){
			d = new Date();
		}
		//System.out.println(birth+"-"+d);
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
	
	public void persona(String numhis){
		Historias htra = new Historias(autenticacion);
		htra.buscarHistoriaNumero(Integer.parseInt(numhis), false);
		String idSt = "000000"+htra.getId();
		idSt = idSt.substring(idSt.length()-6, idSt.length());
		ventana.t_historia.setText(idSt);
		ventana.getT_nombre().setText(htra.getNombre());
		nombre = htra.getNombre();
		try{
			ventana.getT_edad().setText(calcularEdad(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()), new Date()));
		}
		catch(Exception e){}
	}
	
	public void receta(){
		int id = consulta.getId();
		//System.out.println(id);
		Date d = new Date();
		String dd = String.valueOf(d.getDate());
		String mm = String.valueOf(d.getMonth()+1);
		String yy = String.valueOf(d.getYear()+1900);
		if(dd.length()==1) dd = "0" + dd;
		if(mm.length()==1) mm = "0" + mm;
		String fecha = dd+"/"+mm+"/"+yy;
		String nombre = ventana.getT_nombre().getText();
		String edad = ventana.getT_edad().getText();
		
		/*GestorListadeRecetas gestorListadeRecetas*/ new GestorListadeRecetas(id, edad, fecha, nombre,autenticacion, desktopPane);
	}
	
	public void receta2(){
		int id = consulta.getId();
		//System.out.println(id);
		Date d = new Date();
		String dd = String.valueOf(d.getDate());
		String mm = String.valueOf(d.getMonth()+1);
		String yy = String.valueOf(d.getYear()+1900);
		if(dd.length()==1) dd = "0" + dd;
		if(mm.length()==1) mm = "0" + mm;
		String fecha = dd+"/"+mm+"/"+yy;
		String nombre = ventana1.getT_nombre().getText();
		String edad = ventana1.getT_edad().getText();
		
		/*GestorListadeRecetas gestorListadeRecetas*/ new GestorListadeRecetas(id, edad, fecha, nombre,autenticacion, desktopPane);
	}
	
	public void historia(){
		System.out.println("aqui va el metodo para ver la historia");
	}
	
	public void consultaDetallada(String numhis, String fecha, String hora){
		ConsultaDetallada cons = new ConsultaDetallada(desktopPane.getWidth(), desktopPane.getHeight(),numhis);
		GestorConsultaDetallada ges = new GestorConsultaDetallada(cons,autenticacion,numhis,fecha,hora);
		cons.setGestor(ges);
		desktopPane.add(cons.getVentana());
		cons.setVisible(true);
	}
	
	public void verRecetaPrimeraConsulta(){
			GestorListadeRecetas obj = new GestorListadeRecetas(autenticacion,desktopPane);
			obj.setNombre(nombre);
			obj.setEdad(edad);
			obj.setFecha(fechaIngreso);
			obj.cargar(primeraConsulta);
			obj.setHabilitacionBotones(false);
	}

	public void verRecetaUltimaConsulta(){
		GestorListadeRecetas obj = new GestorListadeRecetas(autenticacion,desktopPane);
		obj.setNombre(nombre);
		obj.setEdad(edad);
		obj.setFecha(fechaUltima);
		obj.cargar(ultimaConsulta);
		obj.setHabilitacionBotones(false);
	}
	
	public int privilegios(){
		if(autenticacion.getTipoUsuario()==2 || autenticacion.getTipoUsuario()==3){
			//ventana.getT_saturacion().setEditable(false);
			//ventana.getT_indice().setEditable(false);
			ventana.getT_diagnosticoActual().setEditable(false);
			ventana.getT_observaciones().setEditable(false);
		}
		return autenticacion.getTipoUsuario();
	}
	
	public boolean cargarActual(String numhis, String fecha, String hora){
		try{
			java.util.Date d = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			String dd = String.valueOf(d.getDate());
			String mm = String.valueOf(d.getMonth()+1);
			String yy = String.valueOf(d.getYear()+1900);
			if(dd.length()==1){	dd = "0"+dd; }
			if(mm.length()==1){	mm = "0"+mm; }
			fecha = yy+"-"+mm+"-"+dd;
		} catch(Exception e){}
		if(consulta.buscar(numhis, fecha, hora).compareTo("FALLO")==0)
			System.out.println("No se pudo buscar la consulta. GestroCrearConsulta cargarActual");
		if(consulta.getEstado()==1)
			return true;
		ventana.getT_peso().setText(consulta.getPeso());
		ventana.getT_talla().setText(consulta.getTalla());
		ventana.getT_motivoActual().setText(consulta.getMotivo());
		ventana.getT_temperatura().setText(consulta.getTemperatura());
		ventana.getT_fc().setText(consulta.getFc());
		ventana.getT_frecuenciaRespiratoria().setText(consulta.getFr());
		ventana.getT_tas().setText(consulta.getTas());
		ventana.getT_tad().setText(consulta.getTad());
		return false;
		
	}
	
	public void actualizar(){
		
	}
}
