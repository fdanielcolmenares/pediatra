package GestionImpresiones;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRDataSource;

public class Process_SourceDataVacunas implements JRDataSource{
	private int indice;
	private List lista;
	
	public Process_SourceDataVacunas(){		
		indice = -1;
		lista = new ArrayList();
	}
	
	public Object getFieldValue(JRField jrF) throws JRException {
		Object valor = null;
		SourceDataListVacunas dato = (SourceDataListVacunas)lista.get(this.indice);
		if("date".equals(jrF.getName())) valor = dato.getDate();		
		else if("descripcion".equals(jrF.getName()))	valor = dato.getDescripcion();		
		else if("dosis".equals(jrF.getName()))	valor = dato.getDosis();
		else if("lote".equals(jrF.getName()))	valor = dato.getLote();
		return valor;
	}

	public boolean next() throws JRException {
		return ++indice < lista.size();		
	}
	
	public void addVacunas(SourceDataListVacunas a){
		this.lista.add(a);
}
	public int getIndice(){
			return indice;
	}
	
	public SourceDataListVacunas get(int indice){		
			return (SourceDataListVacunas)lista.get(indice);
	}
	
}
