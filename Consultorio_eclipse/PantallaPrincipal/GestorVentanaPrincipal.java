package PantallaPrincipal;

import GestionImpresiones.ImprimirHistorias;
import GestionarHistorias.BuscarHistorias;
import GestionarHistorias.CrearHistoria;
import GestionarHistorias.GestorCrearHistoria;
import GestionarInventario.GestorGestionarInventario;
import GestionarInventario.frmInventario;
import GestionarRecetas.AgregarMedicamento;
import GestionarRecetas.BuscarMedicamentos;
import GestionarRecetas.GestorAgregarMedicamento;
import GestionarRecetas.GestorMostrarMedicamentos;
import GestionarRecetas.MostrarMedicamentos;
import GestionarUsuarios.BuscarUsuarios;
import GestionarUsuarios.EditarUsuario;
import PantallasConfiguracion.VentanaServidor;
import PantallasConfiguracion.VentanaUsuario;
import Utilitario.Autenticacion;

public class GestorVentanaPrincipal {

    private VentanaPrincipal ventana;
    private boolean conectado;
    private boolean autenticado;
    private Autenticacion autenticacion;

    public GestorVentanaPrincipal(VentanaPrincipal v) {
        ventana = v;
        conectado = false;
        autenticado = false;
        setNivelUsuario(-1);
    }

    public void setAutenticacion(Autenticacion a) {
        autenticacion = a;
        if (a.getIpServidor().compareTo("") == 0) {
            ventana.setConectadoServidor(false);
            mostrarConfigurarServidor();            
        } else {
            conectado = true;
            ventana.setConectadoServidor(true);
            mostrarIniciarSesion();
        }
    }
    
    public void setNivelUsuario(int n){
    	ventana.setNivelUsuario(n);
    }

    public void mostrarConfigurarServidor() {
        VentanaServidor vs = new VentanaServidor(ventana.getVentana(), autenticacion);
        vs.setVisible(true);

        if(vs.esValido()){
            autenticacion.setIpServidor(vs.getTextoIP());
            autenticacion.setPuerto(vs.getTextoPuerto());
            ventana.setConectadoServidor(true);
        }
        else{
        	if(!conectado){
        		ventana.setConectadoServidor(false);
        	}
        }
        vs.dispose();
    }

    public void mostrarIniciarSesion() {
        VentanaUsuario vu = new VentanaUsuario(ventana.getVentana(), autenticacion);
        vu.setVisible(true);
        if(vu.esValido()){
            ventana.setConectado(true);
            autenticacion.setCedulaUsuario(vu.getUsro_cedula());
            autenticacion.setCedulaMedico(vu.getMdco_cedula());
            autenticacion.setTipoUsuario(vu.getRol());
            autenticacion.setUsuario(vu.getUsuario());
            autenticacion.setClave(vu.getClave());
            autenticado = true;
            setNivelUsuario(autenticacion.getTipoUsuario());
        }
        vu.setVisible(false);
        vu.dispose();
    }


    public void iniciar() {
    }
    
    public void desconectar(){
    	autenticacion.setCedulaMedico("");
    	autenticacion.setCedulaUsuario("");
    	autenticacion.setClave("");
    	autenticacion.setUsuario("");
    	autenticacion.setTipoUsuario(3);
    	autenticado = false;
    	setNivelUsuario(-1);
    	mostrarIniciarSesion();
    }
    
    public void crearNuevaHistoria(){
    	CrearHistoria v = new CrearHistoria(ventana.getDesktopPanel());
        GestorCrearHistoria gestor = new GestorCrearHistoria(v, autenticacion);
        v.setGestor(gestor);
        v.setNuevo(true, -1);
        //gestor.cargarHistoriaNumero(1);//
        ventana.getDesktopPanel().add(v.getVentana());
        v.setVisible(true);
    	/*VentanaBank o = new VentanaBank();
    	ventana.getDesktopPanel().add(o);*/
    }
    
    public void crearNuevaConsulta(String numhis){
    	/*CrearConsulta ven = new CrearConsulta(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight(),numhis);
    	GestorCrearConsulta q = new GestorCrearConsulta(ven, autenticacion,ventana);
    	ven.setGestor(q);
    	q.llenarPrimera(numhis);
    	q.llenarUltima(numhis);
    	q.persona(numhis);
    	ventana.getDesktopPanel().add(ven.getVentana());
    	ven.setVisible(true);*/
    }
    
    public void GestioInventario(){
    	
    }
    
    public void buscarHistoria(){
    	BuscarHistorias buscar= new BuscarHistorias(ventana.getDesktopPanel());
    	buscar.setAutenticacion(autenticacion);
    	ventana.getDesktopPanel().add(buscar.getVentana());
    	buscar.setVisible(true);
    }
    
    public void nuevoUsuario(){
    	EditarUsuario v = new EditarUsuario(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight());
        v.setAutenticacion(autenticacion);
        v.setNuevo(true);
        ventana.getDesktopPanel().add(v.getVentana());
        v.setVisible(true);
    }
    
    public void buscarUsuarios(){
    	BuscarUsuarios v = new BuscarUsuarios(ventana.getDesktopPanel());
    	v.setAutenticacion(autenticacion);
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.setVisible(true);
    }
    
    public void generarReporteHistorias(){
    	ImprimirHistorias a = new ImprimirHistorias(autenticacion, ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight());
    	ventana.getDesktopPanel().add(a.getVentana());
    	a.setVisible(true);
    	/*MostrarMedicamentos ven = new MostrarMedicamentos(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight());
        GestorMostrarMedicamentos ges = new GestorMostrarMedicamentos(ven, autenticacion);
        ven.setGestor(ges);
        ventana.getDesktopPanel().add(ven.getVentana());
        ven.setVisible(true);
        ges.mostrarMedicamentos("1=1");*/
    }
    
    public void agregarMedi(){
    	AgregarMedicamento medica = new AgregarMedicamento(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight(),true);
    	GestorAgregarMedicamento ges = new GestorAgregarMedicamento(autenticacion, medica);
    	medica.setGestor(ges);
    	ventana.getDesktopPanel().add(medica.getVentanaNewMedica());
    	medica.getVentanaNewMedica().setVisible(true);
    }
    
    public void buscarMedicamento(){
    	BuscarMedicamentos buscar= new BuscarMedicamentos(ventana.getDesktopPanel());
    	buscar.setAutenticacion(autenticacion);
    	ventana.getDesktopPanel().add(buscar.getVentana());
    	buscar.setVisible(true);
    }
}
