package GestionImpresiones;

public class SourceDataListInventario {
	private String nombre;
	private String cantidad;
	private String tipo;
	
	public SourceDataListInventario() {
		nombre = "";
		cantidad = "";
		tipo = "";
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
