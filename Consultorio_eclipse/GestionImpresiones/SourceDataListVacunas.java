package GestionImpresiones;

public class SourceDataListVacunas {
	private String date;
	private String descripcion;
	private String dosis;
	private String lote;
	
	
	public SourceDataListVacunas(String date, String descripcion, String dosis, String lote) {
		super();
		this.date = date;
		this.descripcion = descripcion;
		this.dosis = dosis;
		this.lote = lote;
	}
	
	public SourceDataListVacunas(){
		
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDosis() {	
		
		return dosis;
	}
	public void setDosis(String dosis) {
		if(Integer.parseInt(dosis)>=4){
			this.dosis = "R";
		}else{
			this.dosis = dosis;
		}
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	

}
