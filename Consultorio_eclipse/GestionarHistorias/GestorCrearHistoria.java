package GestionarHistorias;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDesktopPane;

import Utilitario.Autenticacion;
import Entidades.*;
import GestionarConsultas.CrearConsulta;
import GestionarConsultas.GestorCrearConsulta;
import GestionarConsultas.ListaConsultas;
import GestionarExamenes.FrmInternoVisorIma;
import GestionarExamenes.frmImagen;

public class GestorCrearHistoria {
	private CrearHistoria ventana;
	private Historias htra;
	private Autenticacion autenticacion;
		
	public GestorCrearHistoria(CrearHistoria v, Autenticacion a){
		ventana = v;		
		autenticacion = a;
		ventana.setNivelUsuario(autenticacion.getTipoUsuario());
		htra = new Historias(autenticacion);
	}
	
	public void setAutenticacion(Autenticacion param){
		autenticacion = param;
		ventana.setNivelUsuario(autenticacion.getTipoUsuario());
		htra.setAutenticacion(param);
	}
	public Autenticacion getAutenticacion(){
		return autenticacion;
	}
	public int getId_Historia(){
		return htra.getId();
	}
	public void nuevaConsulta(){
		CrearConsulta ven = new CrearConsulta(ventana.getDesktopPane().getWidth(), ventana.getDesktopPane().getHeight(),String.valueOf(htra.getId()),true);
    	GestorCrearConsulta q = new GestorCrearConsulta(ven, autenticacion, ventana.getDesktopPane());
    	ven.setGestor(q);
    	String ret = q.llenarPrimera(String.valueOf(htra.getId()));
    	q.llenarUltima(String.valueOf(htra.getId()), ret);
    	q.persona(String.valueOf(htra.getId()));
        q.privilegios();
    	ventana.getDesktopPane().add(ven.getVentana());
    	ven.setVisible(true);
	}
	
	public void listarConsultas(){
		ListaConsultas lista = new ListaConsultas(ventana.getDesktopPane().getWidth(),
				ventana.getDesktopPane().getHeight(),String.valueOf(htra.getId()),ventana.getDesktopPane(),autenticacion);
		ventana.getDesktopPane().add(lista.getVentana());
		lista.getVentana().setVisible(true);
	}
	
	public void cargarHistoriaNumero(int id){		
		String retorna = htra.buscarHistoriaNumero(id, true);
		if(retorna.compareToIgnoreCase("Ok")==0){
			try{
				ventana.setNuevo(false, autenticacion.getTipoUsuario());
				//ventana.setEditable(false);
				String idSt = "000000"+htra.getId();
				idSt = idSt.substring(idSt.length()-6, idSt.length());
				ventana.setNumeroHistoria(idSt);
				ventana.getT_nombre().setText(htra.getNombre());				
				ventana.getT_fechaNac().setDate(new SimpleDateFormat("dd-MM-yyyy").parse(htra.getFecha_nacimiento()));
				ventana.setFechaGuardada(htra.getFecha_creacion());
				
				ventana.getT_nomMadre().setText(htra.getDatosPadres().getNombre_madre());
				ventana.getT_profMadre().setText(htra.getDatosPadres().getProfesion_madre());
				ventana.getT_nomPadre().setText(htra.getDatosPadres().getNombre_padre());
				ventana.getT_profPadre().setText(htra.getDatosPadres().getProfesion_padre());
				ventana.getT_dir().setText(htra.getDatosPadres().getDireccion());
				ventana.getT_tlf1().setText(htra.getDatosPadres().getTelefono());
				
				ventana.getT_tiempoGestacion().setText(htra.getDatosNacimiento().getGesta());
				ventana.getT_peso().setText(htra.getDatosNacimiento().getPeso());
				ventana.getT_talla().setText(htra.getDatosNacimiento().getTalla());
				if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("normal")==0)ventana.getT_tipoParto().setSelectedIndex(0);
				else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("cesarea")==0) ventana.getT_tipoParto().setSelectedIndex(1);
				//else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("adopcion")==0) ventana.getT_tipoParto().setSelectedIndex(2);
				//else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("edema")==0) ventana.getT_tipoParto().setSelectedIndex(3);
				else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("instrumental")==0) ventana.getT_tipoParto().setSelectedIndex(2);
				//else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("gemelar")==0) ventana.getT_tipoParto().setSelectedIndex(5);
				//else if(htra.getDatosNacimiento().getTipo_parto().compareToIgnoreCase("trillizos")==0) ventana.getT_tipoParto().setSelectedIndex(6);
				else ventana.getT_tipoParto().setSelectedIndex(3);
				ventana.getT_causas().setText(htra.getDatosNacimiento().getCausas());
				ventana.getT_enfermedadesEmbarazo().setText(htra.getDatosNacimiento().getEnfermedades_embarazo());
				ventana.getT_complicaciones().setText(htra.getDatosNacimiento().getComplicaciones());
				ventana.getT_condiciones().setText(htra.getDatosNacimiento().getCondiciones());
				if(htra.getDatosNacimiento().getLactancia().compareToIgnoreCase("1")==0){
					ventana.getChk_siLactancia().setSelected(true);
					ventana.getChk_noLactancia().setSelected(false);
				}
				else{
					if(htra.getDatosNacimiento().getLactancia().compareToIgnoreCase("0")!=0){
						ventana.getChk_siLactancia().setSelected(true);
						ventana.getChk_noLactancia().setSelected(false);
						ventana.getT_lactancia().setText(htra.getDatosNacimiento().getLactancia());
					}
				}
				if(htra.getDatosNacimiento().getTetero().compareToIgnoreCase("1")==0){
					ventana.getChk_noTetero().setSelected(true);
					ventana.getChk_siTetero().setSelected(false);
				}
				else{
					if(htra.getDatosNacimiento().getTetero().compareToIgnoreCase("0")!=0){
						ventana.getChk_siTetero().setSelected(true);
						ventana.getChk_noTetero().setSelected(false);
						ventana.getT_tetero().setText(htra.getDatosNacimiento().getTetero());
					}
				}
				if(htra.getDatosNacimiento().getAblactacion().compareToIgnoreCase("1")==0){
					ventana.getChk_siAblactacion().setSelected(true);
					ventana.getChk_noAblactacion().setSelected(false);
				}
				else{
					if(htra.getDatosNacimiento().getAblactacion().compareToIgnoreCase("0")!=0){
						ventana.getChk_siAblactacion().setSelected(true);
						ventana.getChk_noAblactacion().setSelected(false);
						ventana.getT_ablactacion().setText(htra.getDatosNacimiento().getAblactacion());
					}
				}
				
				ventana.getT_sintomas().setText(htra.getAntecedentes().getSintomas_respiratorios());
				ventana.getT_fechaCrisis1().setText(htra.getAntecedentes().getPrimera_crisis());
				ventana.getT_fechaCrisis2().setText(htra.getAntecedentes().getUltima_crisis());
				ventana.getT_interCrisis().setText(htra.getAntecedentes().getSintomas_intercrisis());
				if( htra.getAntecedentes().getFrecuencia().compareToIgnoreCase("c")==0){
					ventana.getChk_continua().setSelected(true);
				}
				if( htra.getAntecedentes().getFrecuencia().compareToIgnoreCase("i")==0){
					ventana.getChk_intermitente().setSelected(true);
				}
				if( htra.getAntecedentes().getRespiracion_oral().compareToIgnoreCase("s")==0){
					ventana.getChk_respiracionOral().setSelected(true);
				}
				if( htra.getAntecedentes().getRonca().compareToIgnoreCase("s")==0){
					ventana.getChk_ronca().setSelected(true);
				}
				if( htra.getAntecedentes().getBurxismo().compareToIgnoreCase("s")==0){
					ventana.getChk_burxismo().setSelected(true);
				}
				ventana.getT_alergias().setText(htra.getAntecedentes().getOtras_alergias());
				ventana.getT_antecedentesEruptivas().setText(htra.getAntecedentes().getAntecedentes_eruptivos());
				ventana.getT_antecedentesGI().setText(htra.getAntecedentes().getAntecedentes_gi());
				ventana.getT_antecedentesGU().setText(htra.getAntecedentes().getAntecedentes_gu());
				ventana.getT_antecedentesOtros().setText(htra.getAntecedentes().getAntecedentes_otros());
				
				if(htra.getDatosFamiliares().getAsma().compareToIgnoreCase("1")==0){
					ventana.getChk_asma().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getAsma().compareToIgnoreCase("0")!=0){
						ventana.getChk_asma().setSelected(true);
						ventana.setAsma(htra.getDatosFamiliares().getAsma());
					}
				}				
				if(htra.getDatosFamiliares().getRinitis().compareToIgnoreCase("1")==0){
					ventana.getChk_rinitis().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getRinitis().compareToIgnoreCase("0")!=0){
						ventana.getChk_rinitis().setSelected(true);
						ventana.setRinitis(htra.getDatosFamiliares().getRinitis());
					}
				}				
				if(htra.getDatosFamiliares().getDermatitis().compareToIgnoreCase("1")==0){
					ventana.getChk_dermatitis().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getDermatitis().compareToIgnoreCase("0")!=0){
						ventana.getChk_dermatitis().setSelected(true);
						ventana.setDermatitis(htra.getDatosFamiliares().getDermatitis());
					}
				}				
				if(htra.getDatosFamiliares().getDiabetes().compareToIgnoreCase("1")==0){
					ventana.getChk_diabetes().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getDiabetes().compareToIgnoreCase("0")!=0){
						ventana.getChk_diabetes().setSelected(true);
						ventana.setDiabetes(htra.getDatosFamiliares().getDiabetes());
					}
				}
				if(htra.getDatosFamiliares().getTiroides().compareToIgnoreCase("1")==0){
					ventana.getChk_tiroides().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getTiroides().compareToIgnoreCase("0")!=0){
						ventana.getChk_tiroides().setSelected(true);
						ventana.setTiroides(htra.getDatosFamiliares().getTiroides());
					}
				}
				if(htra.getDatosFamiliares().getHipertension().compareToIgnoreCase("1")==0){
					ventana.getChk_hipertension().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getHipertension().compareToIgnoreCase("0")!=0){
						ventana.getChk_hipertension().setSelected(true);
						ventana.setHipertension(htra.getDatosFamiliares().getHipertension());
					}
				}				
				
				if(htra.getDatosFamiliares().getPrurito().compareToIgnoreCase("1")==0){
					ventana.getChk_prurito().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getPrurito().compareToIgnoreCase("0")!=0){
						ventana.getChk_prurito().setSelected(true);
						ventana.setPrurito(htra.getDatosFamiliares().getPrurito());
					}
				}
				if(htra.getDatosFamiliares().getCancer().compareToIgnoreCase("1")==0){
					ventana.getChk_cancer().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getCancer().compareToIgnoreCase("0")!=0){
						ventana.getChk_cancer().setSelected(true);
						ventana.setCancer(htra.getDatosFamiliares().getCancer());
					}
				}
				if(htra.getDatosFamiliares().getCancer().compareToIgnoreCase("1")==0){
					ventana.getChk_cancer().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getCancer().compareToIgnoreCase("0")!=0){
						ventana.getChk_cancer().setSelected(true);
						ventana.setCancer(htra.getDatosFamiliares().getCancer());
					}
				}				
				if(htra.getDatosFamiliares().getCardiopatias().compareToIgnoreCase("1")==0){
					ventana.getChk_cardiopatias().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getCardiopatias().compareToIgnoreCase("0")!=0){
						ventana.getChk_cardiopatias().setSelected(true);
						ventana.setCardiopatias(htra.getDatosFamiliares().getCardiopatias());
					}
				}				
				if(htra.getDatosFamiliares().getOtras().compareToIgnoreCase("1")==0){
					ventana.getChk_otras().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getOtras().compareToIgnoreCase("0")!=0){
						ventana.getChk_otras().setSelected(true);
						ventana.setOtras(htra.getDatosFamiliares().getOtras());
					}
				}
				if(htra.getDatosFamiliares().getUrticaria().compareToIgnoreCase("1")==0){
					ventana.getChk_urticaria().setSelected(true);
				}
				else{
					if(htra.getDatosFamiliares().getUrticaria().compareToIgnoreCase("0")!=0){
						ventana.getChk_urticaria().setSelected(true);
						ventana.setUrticaria(htra.getDatosFamiliares().getUrticaria());
					}
				}
				ventana.getT_observaciones().setText(htra.getDatosFamiliares().getObservaciones());
			}
			catch(Exception e){}
		}
		else{
			ventana.mostrarMensaje(retorna, ventana.ERROR);
		}
	}
	
	public void guardar(boolean nuevo){
		if(nuevo){
			String id = htra.buscarNuevoID();
			ventana.setNumeroHistoria(id);
		}
		
		htra.setNombre(ventana.getT_nombre().getText());
		if(ventana.getT_fechaNac().getCalendar()!=null){
			htra.setFecha_nacimiento(ventana.getT_fechaNac().getCalendar().get(Calendar.YEAR)+"-"
				+(ventana.getT_fechaNac().getCalendar().get(Calendar.MONTH)+1)+"-"
				+ventana.getT_fechaNac().getCalendar().get(Calendar.DAY_OF_MONTH));
		}
		htra.setUsro_cedula(autenticacion.getCedulaUsuario());
		htra.setMdco_cedula(autenticacion.getCedulaMedico());
		
		htra.getDatosPadres().setHtra_id(htra.getId());
		htra.getDatosPadres().setNombre_madre(ventana.getT_nomMadre().getText());
		htra.getDatosPadres().setProfesion_madre(ventana.getT_profMadre().getText());
		htra.getDatosPadres().setNombre_padre(ventana.getT_nomPadre().getText());
		htra.getDatosPadres().setProfesion_padre(ventana.getT_profPadre().getText());
		htra.getDatosPadres().setDireccion(ventana.getT_dir().getText());
		htra.getDatosPadres().setTelefono(ventana.getT_tlf1().getText());
		
		htra.getDatosNacimiento().setHtra_id(htra.getId());
		htra.getDatosNacimiento().setGesta(ventana.getT_tiempoGestacion().getText());
		htra.getDatosNacimiento().setPeso(ventana.getT_peso().getText());
		htra.getDatosNacimiento().setTalla(ventana.getT_talla().getText());
		if(ventana.getT_tipoParto().getSelectedIndex()==0) htra.getDatosNacimiento().setTipo_parto("NORMAL");
		else if(ventana.getT_tipoParto().getSelectedIndex()==1) htra.getDatosNacimiento().setTipo_parto("CESAREA");
		//else if(ventana.getT_tipoParto().getSelectedIndex()==2) htra.getDatosNacimiento().setTipo_parto("ADOPCION");
		//else if(ventana.getT_tipoParto().getSelectedIndex()==3) htra.getDatosNacimiento().setTipo_parto("EDEMA");
		else if(ventana.getT_tipoParto().getSelectedIndex()==4) htra.getDatosNacimiento().setTipo_parto("INSTRUMENTAL");
		//else if(ventana.getT_tipoParto().getSelectedIndex()==5) htra.getDatosNacimiento().setTipo_parto("GEMELAR");
		//else if(ventana.getT_tipoParto().getSelectedIndex()==6) htra.getDatosNacimiento().setTipo_parto("TRILLIZOS");
		else htra.getDatosNacimiento().setTipo_parto("OTRO");
			
		htra.getDatosNacimiento().setCausas(ventana.getT_causas().getText());
		htra.getDatosNacimiento().setEnfermedades_embarazo(ventana.getT_enfermedadesEmbarazo().getText());
		htra.getDatosNacimiento().setComplicaciones(ventana.getT_complicaciones().getText());
		htra.getDatosNacimiento().setCondiciones(ventana.getT_condiciones().getText());
		if(  ventana.getChk_siLactancia().isSelected()){
			if(ventana.getT_lactancia().getText().length()>0){ htra.getDatosNacimiento().setLactancia(ventana.getT_lactancia().getText()); }
			else{ htra.getDatosNacimiento().setLactancia("1"); }
		} else{ htra.getDatosNacimiento().setLactancia("0"); }		
		if(  ventana.getChk_siTetero().isSelected()){
			if(ventana.getT_tetero().getText().length()>0){	htra.getDatosNacimiento().setTetero(ventana.getT_tetero().getText()); }
			else{ htra.getDatosNacimiento().setTetero("1"); }
		} else{	htra.getDatosNacimiento().setTetero("0"); }		
		if(ventana.getChk_siAblactacion().isSelected()){
			if(ventana.getT_ablactacion().getText().length()>0){ htra.getDatosNacimiento().setAblactacion(ventana.getT_ablactacion().getText()); }
			else{ htra.getDatosNacimiento().setAblactacion("1"); }
		} else{	htra.getDatosNacimiento().setAblactacion("0"); }
		
		htra.getAntecedentes().setHtra_id(htra.getId());
		htra.getAntecedentes().setSintomas_respiratorios(ventana.getT_sintomas().getText());
		htra.getAntecedentes().setPrimera_crisis(ventana.getT_fechaCrisis1().getText());
		htra.getAntecedentes().setUltima_crisis(ventana.getT_fechaCrisis2().getText());
		htra.getAntecedentes().setSintomas_intercrisis(ventana.getT_interCrisis().getText());
		if(ventana.getChk_continua().isSelected()){ htra.getAntecedentes().setFrecuencia("c");  }
		else if(ventana.getChk_intermitente().isSelected()){ htra.getAntecedentes().setFrecuencia("i");  }
		else htra.getAntecedentes().setFrecuencia("0");
		if(ventana.getChk_respiracionOral().isSelected()){ htra.getAntecedentes().setRespiracion_oral("s");  }
		else{ htra.getAntecedentes().setRespiracion_oral("n"); }
		if(ventana.getChk_ronca().isSelected()){ htra.getAntecedentes().setRonca("s");  }
		else{ htra.getAntecedentes().setRonca("n"); }
		if(ventana.getChk_burxismo().isSelected()){ htra.getAntecedentes().setBurxismo("s");  }
		else{ htra.getAntecedentes().setBurxismo("n"); }
		htra.getAntecedentes().setOtras_alergias(ventana.getT_alergias().getText());
		htra.getAntecedentes().setAntecedentes_eruptivos(ventana.getT_antecedentesEruptivas().getText());
		htra.getAntecedentes().setAntecedentes_gi(ventana.getT_antecedentesGI().getText());
		htra.getAntecedentes().setAntecedentes_gu(ventana.getT_antecedentesGU().getText());
		htra.getAntecedentes().setAntecedentes_otros(ventana.getT_antecedentesOtros().getText());
		
		htra.getDatosFamiliares().setHtra_id(htra.getId());
		if(ventana.getChk_asma().isSelected()){
			if(ventana.getAsma().length()>0){ htra.getDatosFamiliares().setAsma(ventana.getAsma());	}
			else{   htra.getDatosFamiliares().setAsma("1");   }
		} else{ htra.getDatosFamiliares().setAsma("0"); }
		if(ventana.getChk_rinitis().isSelected()){
			if(ventana.getRinitis().length()>0){   htra.getDatosFamiliares().setRinitis(ventana.getRinitis());   }
			else{   htra.getDatosFamiliares().setRinitis("1");   }
		} else{ htra.getDatosFamiliares().setRinitis("0"); }
		if(ventana.getChk_dermatitis().isSelected()){
			if(ventana.getDermatitis().length()>0){   htra.getDatosFamiliares().setDermatitis(ventana.getDermatitis());   }
			else{   htra.getDatosFamiliares().setDermatitis("1");   }
		} else{ htra.getDatosFamiliares().setDermatitis("0"); }	
		if(ventana.getChk_diabetes().isSelected()){
			if(ventana.getDiabetes().length()>0){   htra.getDatosFamiliares().setDiabetes(ventana.getDiabetes());   }
			else{   htra.getDatosFamiliares().setDiabetes("1");   }
		} else{ htra.getDatosFamiliares().setDiabetes("0"); }
		if(ventana.getChk_tiroides().isSelected()){
			if(ventana.getTiroides().length()>0){   htra.getDatosFamiliares().setTiroides(ventana.getTiroides());   }
			else{   htra.getDatosFamiliares().setTiroides("1");   }
		} else{ htra.getDatosFamiliares().setTiroides("0"); }
		if(ventana.getChk_hipertension().isSelected()){
			if(ventana.getHipertension().length()>0){   htra.getDatosFamiliares().setHipertension(ventana.getHipertension());   }
			else{   htra.getDatosFamiliares().setHipertension("1");   }
		} else{ htra.getDatosFamiliares().setHipertension("0"); }
		if(ventana.getChk_prurito().isSelected()){
			if(ventana.getPrurito().length()>0){   htra.getDatosFamiliares().setPrurito(ventana.getPrurito());   }
			else{   htra.getDatosFamiliares().setPrurito("1");   }
		}
		else{ htra.getDatosFamiliares().setPrurito("0"); }
		if(ventana.getChk_cancer().isSelected()){
			if(ventana.getCancer().length()>0){   htra.getDatosFamiliares().setCancer(ventana.getCancer());   }
			else{   htra.getDatosFamiliares().setCancer("1");   }
		}
		else{ htra.getDatosFamiliares().setCancer("0"); }
		if(ventana.getChk_cardiopatias().isSelected()){
			if(ventana.getCardiopatias().length()>0){   htra.getDatosFamiliares().setCardiopatias(ventana.getCardiopatias());   }
			else{   htra.getDatosFamiliares().setCardiopatias("1");   }
		}
		else{ htra.getDatosFamiliares().setCardiopatias("0"); }
		if(ventana.getChk_otras().isSelected()){
			if(ventana.getOtras().length()>0){   htra.getDatosFamiliares().setOtras(ventana.getOtras());   }
			else{   htra.getDatosFamiliares().setOtras("1");   }
		}
		else{ htra.getDatosFamiliares().setOtras("0"); }
		if(ventana.getChk_urticaria().isSelected()){
			if(ventana.getUrticaria().length()>0){   htra.getDatosFamiliares().setUrticaria(ventana.getUrticaria());   }
			else{   htra.getDatosFamiliares().setUrticaria("1");   }
		}
		else{ htra.getDatosFamiliares().setUrticaria("0"); }
		htra.getDatosFamiliares().setObservaciones(ventana.getT_observaciones().getText());
		
		String retorna = "";
		if(nuevo){
			retorna = htra.guardar();
		}
		else{
			retorna = htra.actualizar();
		}
		if(retorna.compareToIgnoreCase("Ok") == 0){
			ventana.mostrarMensaje("Operación Exitosa", ventana.MENSAJE);
			//ventana.setEditable(false);
			ventana.setNuevo(false, autenticacion.getTipoUsuario());
		}
		else{
			ventana.mostrarMensaje(retorna, ventana.ERROR);
		}
	}
	
	public void examenNuevo(JDesktopPane panel){
    	estudios es = new estudios(autenticacion);
    	if(es.contar()>0){
    		frmImagen ventanaIma = new frmImagen(panel);
    		FrmInternoVisorIma visor = new FrmInternoVisorIma(ventanaIma,autenticacion,htra.getId(), panel);
    		ventanaIma.setPanelExamenes(visor);
    		
    		panel.add(visor.getVisorIma());    		
    		//panel.add(ventanaIma.getVtaImagen());
    		
    		visor.getVisorIma().setVisible(true);
    		
    		//visor.getVisorIma().toFront();
    	}
    	else{
    		ventana.mostrarMensaje("Debe cargar Tipos de Examen", ventana.ERROR);
    	}
    }
	
	public void calcularEdad (Date birth, Date d, javax.swing.JTextField campo) {
		if(d == null){
			d = new Date();
		}

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
		campo.setText(edad);
	}
}
