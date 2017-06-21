package GestionImpresiones;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class Process_SourceDataListRecetas implements JRDataSource {
	private int indice;
	private List lista;
	
	public Process_SourceDataListRecetas() {
		super();
		indice = -1;
		lista = new ArrayList();
	}
	
	public Object getFieldValue(JRField jrF) throws JRException {
		Object valor = null;
		SourceDataListRecetas dato = (SourceDataListRecetas)lista.get(this.indice);
		if("Medicamento".equals(jrF.getName())) valor = dato.getMedicamento();		
		else if("Dosis".equals(jrF.getName()))	valor = dato.getDosis();		
		else if("Numero".equals(jrF.getName()))	valor = dato.getNumber();
		return valor;
	}

	public boolean next() throws JRException {
		return ++indice < lista.size();		
	}
	
	public void addReceta(SourceDataListRecetas a){
		this.lista.add(a);
}
	public int getIndice(){
			return indice;
	}
	
	public SourceDataListRecetas get(int indice){		
			return (SourceDataListRecetas)lista.get(indice);
	}
	
	public void Depurar(){
		int i =0;
		do{
			SourceDataListRecetas aux = (SourceDataListRecetas)lista.get(i);
			int cont =0;
			for (int j = 0; j < lista.size(); j++) {				
				SourceDataListRecetas aux2 = (SourceDataListRecetas)lista.get(j);
				if(aux.getMedicamento().compareToIgnoreCase(aux2.getMedicamento())==0){
						cont++;
				}
			}//fin for
			i++;
			if(cont>1){lista.remove(i);i--;}
		}while(i!=lista.size());//fin for
	}
	
}
