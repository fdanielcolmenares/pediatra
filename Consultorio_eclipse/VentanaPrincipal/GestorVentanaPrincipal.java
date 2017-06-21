package VentanaPrincipal;

import java.awt.Desktop;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import GestionImpresiones.GestorGenerarReportes;
import GestionImpresiones.HiloImpresiones;
import GestionImpresiones.ImprimirHistorias;
import GestionImpresiones.VentanaGenerarReportes;
import GestionarCaja.Calculadora;
import GestionarCaja.GestorAgregarRegistro;
import GestionarCaja.VentanaAgregarConceptos;
import GestionarCaja.VentanaAgregarRegistro;
import GestionarCaja.VentanaAgregarTipoPago;
import GestionarConsultas.GestorCrearConsulta;
import GestionarConsultas.ListaPacientesConsul;
import GestionarExamenes.GestorTipoExamen;
import GestionarExamenes.TipoExamen;
import GestionarHistorias.BuscarHistorias;
import GestionarHistorias.CrearHistoria;
import GestionarHistorias.GestorCrearHistoria;
import GestionarInventario.BuscarInventario;
import GestionarInventario.GestorGestionarInventario;
import GestionarInventario.GestorTipoMedicamento;
import GestionarInventario.TipoMedicamento;
import GestionarInventario.frmInventario;
import GestionarInventario.nueCate;
import GestionarInventario.tabla_eventos;
import GestionarMedicamentos.EditarMedicamentos;
import GestionarMedicamentos.GestorEditarMedicamentos;
import GestionarMensajeria.Cliente;
import GestionarMensajeria.GestorCliente;
import GestionarMensajeria.Servidor;
import GestionarRecetas.BuscarMedicamentos;
import GestionarUsuarios.BuscarUsuarios;
import GestionarUsuarios.EditarUsuario;
import GestionarVacunas.BuscarVacunas;
import Utilitario.Autenticacion;
import Utilitario.Path;
import VentanasConfiguracion.VentanaServidor;
import VentanasConfiguracion.VentanaUsuario;

public class GestorVentanaPrincipal {

    private VentanaPrincipal ventana;
    private boolean conectado;
    public boolean autenticado;
    private Autenticacion autenticacion;
    private boolean clienteConectado;
    private boolean servidorIniciado;
    private Servidor servidor;
    private Cliente cliente;
    private GestorCliente gestorCliente;
    
    public GestorVentanaPrincipal(VentanaPrincipal v) {
        ventana = v;
        conectado = false;
        autenticado = false;
        clienteConectado = false;
        servidorIniciado = false;
        setNivelUsuario(-1);
    }

    public VentanaPrincipal getVentana() {
		return ventana;
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
            mostrarIniciarSesion();
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
            iniciarCliente();
        }
        vu.setVisible(false);
        vu.dispose();
    }


    public void salir() {
    	try{
    		gestorCliente.pararBusquedaServidor();
        	gestorCliente.desconectar();
    		servidor.pararServidor();                
    	}
    	catch(Exception e){    		
    	}
        
        String ruta = Path.getPath()+"Files\\Respaldar.bat";
        try{            
            Desktop.getDesktop().open(new File(ruta));
        }
        catch(Exception e){
            System.out.println("Error al ejecutar "+ruta);
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
    
    public void desconectar(){
    	gestorCliente.pararBusquedaServidor();
    	gestorCliente.desconectar();
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
        GestorCrearConsulta ges = new GestorCrearConsulta(v,autenticacion,ventana.getDesktopPanel());
        v.setGestor(ges);
        v.setNuevo(true, -1);
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
    
    public void GestionInventario(){
    	frmInventario inventa = new frmInventario(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight(),ventana.getDesktopPanel(),autenticacion);    	
    	ventana.getDesktopPanel().add(inventa.getFrmInventario());
    	inventa.setVisible(true);
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
    	v.buscar();
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
    
    public void gestionarVacunas(){
    	BuscarVacunas v = new BuscarVacunas(ventana.getDesktopPanel());
    	v.setAutenticacion(autenticacion);
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.setVisible(true);
    }
    
    public void mostrarReportesDocx(){
    	VentanaGenerarReportes v = new VentanaGenerarReportes(ventana.getDesktopPanel());
    	GestorGenerarReportes g = new GestorGenerarReportes(autenticacion);
    	g.setVentana(v);
    	v.setGestor(g);
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.setVisible(true);
    }
    
    public void agregarMedicamentos(){
    	/*AgregarMedicamento medica = new AgregarMedicamento(ventana.getDesktopPanel().getWidth(), ventana.getDesktopPanel().getHeight(),true);
    	GestorAgregarMedicamento ges = new GestorAgregarMedicamento(autenticacion, medica);
    	medica.setGestor(ges);
    	ventana.getDesktopPanel().add(medica.getVentanaNewMedica());
    	medica.getVentanaNewMedica().setVisible(true);*/
    	EditarMedicamentos v = new EditarMedicamentos(ventana.getDesktopPanel());
    	GestorEditarMedicamentos g = new GestorEditarMedicamentos(autenticacion, v);
    	v.setGestor(g);
    	g.actualizarPresentaciones();
    	g.actualizarMedicamentos();
    	v.cambiarSize(0);
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.getVentana().setVisible(true);
    }
    
    public void buscarMedicamentos(){
    	BuscarMedicamentos buscar= new BuscarMedicamentos(ventana.getDesktopPanel());
    	buscar.setAutenticacion(autenticacion);
    	ventana.getDesktopPanel().add(buscar.getVentana());
    	buscar.setVisible(true);
    }
    
    public void mensajes(){
    	if (JOptionPane.showConfirmDialog(ventana.getVentana(), "Arrancar servidor?", "Mensajes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
    		iniciarServidor();
    	}
    	/*if (JOptionPane.showConfirmDialog(ventana.getVentana(), "Arrancar cliente?", "Mensajes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
    		//iniciarCliente();
    		ventana.getBtn_mensajes().setVisible(true);
    		ventana.getBtn_mensajes().setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
    		cliente = new Cliente(ventana.getDesktopPanel());
    		gestorCliente = new GestorCliente(cliente, autenticacion, ventana.getBtn_mensajes());
    		cliente.setGestor(gestorCliente);
    		ventana.getDesktopPanel().add(cliente.getVentana());
    	}*/
    }
    
    public void iniciarServidor(){
    	if(!servidorIniciado){
    		servidor = new Servidor();
    		servidor.iniciarServidor();
    		servidorIniciado = true;
    	}
    	
    }
    
    public void iniciarCliente(){
    	ventana.getBtn_mensajes().setVisible(true);
		ventana.getBtn_mensajes().setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
		if(!clienteConectado){
			cliente = new Cliente(ventana.getDesktopPanel());
			gestorCliente = new GestorCliente(cliente, autenticacion, this);
			cliente.setGestor(gestorCliente);
			ventana.getDesktopPanel().add(cliente.getVentana());
			clienteConectado = true;
		}
		else{
			cliente = new Cliente(ventana.getDesktopPanel());
			cliente.setGestor(gestorCliente);
			gestorCliente.setVentana(cliente);
			try{
				ventana.getDesktopPanel().add(cliente.getVentana());
			}
			catch(Exception e){
			}
			gestorCliente.borrarConectados();
			gestorCliente.eliminarPaneles();
			gestorCliente.iniciarBusquedaServidor();
			gestorCliente.setUsuario(autenticacion.getUsuario());
		}
    }
    
    public void maximizarCliente(){
    	cliente.getVentana().setLocation(ventana.getDesktopPanel().getWidth()-305, 
    			ventana.getDesktopPanel().getHeight()-387);
    	cliente.setVisible(true);
    	ventana.getBtn_mensajes().setVisible(false);
    }
    
    public void agregarRegistroCaja(){
    	VentanaAgregarRegistro v = new VentanaAgregarRegistro(ventana.getDesktopPanel());
    	GestorAgregarRegistro g = new GestorAgregarRegistro(v, autenticacion, ventana);
    	v.setGestor(g);
    	g.actualizarConceptos();
    	g.actualizarTipoPagos();
    	g.actualizarTabla();
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.setVisible(true);
    }
    
    public void agregarConceptos(){
    	VentanaAgregarConceptos v = new VentanaAgregarConceptos(ventana.getDesktopPanel(), autenticacion);
		v.actualizarConceptos();
		ventana.getDesktopPanel().add(v.getVentana());
		v.setVisible(true);
    }
    
    public void agregarTipoPago(){
    	VentanaAgregarTipoPago v = new VentanaAgregarTipoPago(ventana.getDesktopPanel(), autenticacion);
		v.actualizarTipoPagos();
		ventana.getDesktopPanel().add(v.getVentana());
		v.setVisible(true);
    }
    
    public void agregarProducto(){
    	nueCate b = new nueCate(ventana.getDesktopPanel(),true,autenticacion);
		JInternalFrame a = b.getFrmnueCate();
		GestorGestionarInventario ges = new GestorGestionarInventario(b, autenticacion);
    	b.setGestor(ges);
		a.setVisible(true);
    	if(!ges.validarTipos())return;
    	ges.llenartipoProductos();
		ventana.getDesktopPanel().add(a);
		a.toFront();
		a.requestFocus();
    }
    
    public void buscarProducto(){
    	BuscarInventario b = new BuscarInventario(ventana.getDesktopPanel());
		b.setAutenticacion(autenticacion);
		JInternalFrame a=b.getVentana();
		ventana.getDesktopPanel().add(a);
		a.toFront();
		a.requestFocus();
		a.setVisible(true);
    }
    
    public void agregarTipoProducto(){
    	TipoMedicamento med = new TipoMedicamento(ventana.getDesktopPanel().getWidth(),ventana.getDesktopPanel().getHeight());
		JInternalFrame a=med.getVentana();
		GestorTipoMedicamento ges = new GestorTipoMedicamento(autenticacion, med);
		med.setGestor(ges);
		ventana.getDesktopPanel().add(a);
		a.toFront();
		a.requestFocus();
		a.setVisible(true);
    }
    
    public void verListadePacientes(){
		ListaPacientesConsul consul = new ListaPacientesConsul(ventana, autenticacion);
		ventana.getDesktopPanel().add(consul.getInternalFrame());
		consul.ajustarTabla();
		consul.getTHIS().setVisible(true);
    }

    public void generarReporteInventario(){
		/*PrintInventario print = new PrintInventario(autenticacion);
		print.cargarReporte();
		
		//print.mostrarReporte();
		
		JFileChooser buscador = new JFileChooser();		
        buscador.setSelectedFile(new File("Inventario_"+UtilFechas.convertirFecha(new Date(), UtilFechas.DD_MM_YYYY)+".pdf"));
        String ruta;
        int d = buscador.showSaveDialog(null);
        if(d == JFileChooser.APPROVE_OPTION){
            ruta = buscador.getSelectedFile().toString();
            print.guardarReporte(ruta);
        }*/
    	HiloImpresiones h = new HiloImpresiones(autenticacion);
    	h.setTipoReporte(h.REPORTE_INVENTARIO);
    	h.start();
	}
    
    public void eventosInv(){
    	/*VentanaFactura v = new VentanaFactura(ventana.getDesktopPanel());
    	GestorFactura g = new GestorFactura(autenticacion, v, ventana);
    	v.setGestor(g);    	
    	ventana.getDesktopPanel().add(v.getVentana());
    	v.setVisible(true);*/
    	new tabla_eventos(ventana.getDesktopPanel(),0,autenticacion,"General");
    }
    
    public void mostrarCalculadora(){
    	new Calculadora(ventana.getDesktopPanel());
    	//mostrarImagen vv = new mostrarImagen(autenticacion, ventana.getDesktopPanel());	
    }
    
    public void agregarTipoExamen(){
    	TipoExamen exa = new TipoExamen(ventana.getDesktopPanel().getWidth(),ventana.getDesktopPanel().getHeight());
		JInternalFrame a=exa.getVentana();
		GestorTipoExamen ges = new GestorTipoExamen(autenticacion, exa);
		exa.setGestor(ges);
		ventana.getDesktopPanel().add(a);
		a.toFront();
		a.requestFocus();
		a.setVisible(true);
    }
    
    public void acercaDe(){
    	VentanaAcercaDe v = new VentanaAcercaDe(ventana);
    	v.getVentana().setVisible(true);
    }
    
    public void abrirManual(){
    	try{
    		Desktop.getDesktop().open(new File(Path.getPath()+"Files/Manual.pdf"));
    	}
    	catch(Exception e){
    		JOptionPane.showMessageDialog(ventana.getVentana(), "No se encontro el archivo", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image007.png")));
    	}
    }
}
