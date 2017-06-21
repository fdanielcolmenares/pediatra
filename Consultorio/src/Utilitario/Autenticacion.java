package Utilitario;


public class Autenticacion {
    private String usuario;
    private String clave;
    private String ipServidor;
    private String puerto;
    private int tipoUsuario;
    private String usuarioBD;
    private String claveBD;
    private String cedulaUsuario;
    private String cedulaMedico;

    public Autenticacion() {
        this.usuario = "";
        this.clave = "";
        this.ipServidor = "";
        this.puerto = "";
        this.tipoUsuario = 3;
        this.usuarioBD = "";
        this.claveBD = "";
    }

    public Autenticacion(String usuario, String clave, String ipServidor, String puerto) {
        this.usuario = usuario;
        this.clave = clave;
        this.ipServidor = ipServidor;
        this.puerto = puerto;
        this.usuarioBD = "";
        this.claveBD = "";
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setClaveBD(String claveBD) {
        this.claveBD = claveBD;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public void setCedulaUsuario(String cedula) {
        this.cedulaUsuario = cedula;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public String getClave() {
        return clave;
    }

    public String getClaveBD() {
        return claveBD;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }
    
    
    
}
