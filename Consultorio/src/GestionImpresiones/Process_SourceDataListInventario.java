package GestionImpresiones;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class Process_SourceDataListInventario implements JRDataSource{
	private List lista;
	private int indice;
	
	public Process_SourceDataListInventario() {
		super();
		lista = new ArrayList();
		indice = -1;
	}

	public Object getFieldValue(JRField param) throws JRException {		
		Object valor = null;
		SourceDataListInventario dato = (SourceDataListInventario)lista.get(this.indice);
		if("nombre".equals(param.getName())){
			valor = dato.getNombre();		
		}
		else if("cantidad".equals(param.getName())){
			valor = dato.getCantidad();
		}
		else if("tipo".equals(param.getName())){
			valor = dato.getTipo();
		}
		
		return valor;
	}

	public boolean next() throws JRException {
		return ++indice < lista.size();
	}
	
	public int getIndice(){
		return indice;
	}
	
	public SourceDataListInventario get(int index){
		return (SourceDataListInventario)lista.get(index);
	}
	
	public void add(SourceDataListInventario d){
		lista.add(d);
	}
	
	public int size(){
		return lista.size();
	}
}
