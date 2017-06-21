package GestionImpresiones;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class Process_SourceDataListHistorias implements JRDataSource {

	private int indice;
	private List lista;
	
	public Process_SourceDataListHistorias() {
		super();
		indice = -1;
		lista = new ArrayList();
	}
	
	public Object getFieldValue(JRField jrF) throws JRException {
		Object valor = null;
		SourceDataListHistorias dato = (SourceDataListHistorias)lista.get(this.indice);
		if("ID1".equals(jrF.getName())){
			valor = dato.getID1String();		
		}
		else if("NAME1".equals(jrF.getName())){
			valor = dato.getName();
		}
		
		return valor;
	}

	public boolean next() throws JRException {
		return ++indice < lista.size();		
	}
	
	public void addHistoria(SourceDataListHistorias a){
		this.lista.add(a);
}
	public int getIndice(){
			return indice;
	}

}
