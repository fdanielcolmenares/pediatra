package GestionImpresiones;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class Process_SourceDataListRegistrosCaja implements JRDataSource{
	private List lista;
	private int indice;
	
	public Process_SourceDataListRegistrosCaja() {
		super();
		lista = new ArrayList();
		indice = -1;
	}

	public Object getFieldValue(JRField param) throws JRException {		
		Object valor = null;
		SourceDataListRegistrosCaja dato = (SourceDataListRegistrosCaja)lista.get(this.indice);
		if("htra_id".equals(param.getName())){
			valor = dato.getHtra_id();		
		}
		else if("concepto".equals(param.getName())){
			valor = dato.getConcepto();		
		}
		else if("detalles".equals(param.getName())){
			valor = dato.getDetalles();
		}
		else if("tipo_pago".equals(param.getName())){
			valor = dato.getTipo_pago();
		}
		else if("monto".equals(param.getName())){
			valor = dato.getMonto();
		}
		
		return valor;
	}

	public boolean next() throws JRException {
		return ++indice < lista.size();
	}
	
	public int getIndice(){
		return indice;
	}
	
	public SourceDataListRegistrosCaja get(int index){
		return (SourceDataListRegistrosCaja)lista.get(index);
	}
	
	public void add(SourceDataListRegistrosCaja d){
		lista.add(d);
	}
	
	public int size(){
		return lista.size();
	}

}
