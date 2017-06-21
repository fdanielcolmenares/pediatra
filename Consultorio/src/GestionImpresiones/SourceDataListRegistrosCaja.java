package GestionImpresiones;

public class SourceDataListRegistrosCaja {
	private String concepto;
	private String detalles;
	private String tipo_pago;
	private String monto;
	private String htra_id;
	
	public SourceDataListRegistrosCaja(){
		htra_id = "";
		concepto = "";
		detalles = "";
		tipo_pago = "";
		monto = "";
	}

	public String getHtra_id() {
		return htra_id;
	}

	public void setHtra_id(String htra_id) {
		this.htra_id = htra_id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
}
