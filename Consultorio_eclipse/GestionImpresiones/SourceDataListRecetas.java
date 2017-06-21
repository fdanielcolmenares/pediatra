package GestionImpresiones;

public class SourceDataListRecetas {
	private String medicamento;
	private String dosis;
	private String number;
	
	
	public SourceDataListRecetas(){
		medicamento = "";
		dosis = "";
		number = "";
	}
	
	


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}




	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}	
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	
}
