package GestionarRecetas;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class GestorMostrarMedicamentos {
	private Autenticacion autenticacion;
	private MostrarMedicamentos ventana;
	private List lista;
	private String vecPM[];
	private GestorListadeRecetas listadeRecetas;
	
	public GestorMostrarMedicamentos(MostrarMedicamentos v, Autenticacion a,GestorListadeRecetas gestorListadeRecetas){
		ventana = v;
		autenticacion = a;
		lista = new ArrayList();
		this.listadeRecetas = gestorListadeRecetas;
	}
	
	public void retornarDatos(String m, String p, int dosisINT, String dosisST ){
		System.out.println("retorna: "+m+"-"+p+"-"+dosisINT+"-"+dosisST);
		listadeRecetas.addNewPanel(m, p, dosisST,dosisINT);
	}
	
	public void mostrarDosis(String m, String p, int index){
		String sql;
		sql = "SELECT d.descripcion, d.id, pm.id "
				+"FROM dosis d, presentaciones p, medicinas m, presentaciones_medicina pm "
				+"WHERE m.id=pm.mdna_id AND p.id=pm.pscn_id AND pm.id=d.mdna_id AND d.valida='s' "
				+"AND m.nombre='"+m+"'AND p.descripcion='"+p+"'";
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			java.sql.ResultSet res = con.consultar(sql);
			try{				
				limpiarLista();
				ListaDosis nue = new ListaDosis(m, p, 0, "", this, 0);
				nue.setAutenticacion(autenticacion);
				nue.editarTexto(true);
				if(vecPM!=null){
					try{
						nue.setIdPM(Integer.parseInt(vecPM[index]));
					}
					catch(Exception e){}
				}				
				lista.add(nue);
				while(res.next()){
					String dosis = res.getString(1);
					int idDosis = Integer.parseInt(res.getString(2));
					int idPM = Integer.parseInt(res.getString(3));
					ListaDosis l = new ListaDosis(m, p, idDosis, dosis, this, idPM);
					l.editarTexto(false);
					lista.add(l);
				}
				if(lista.size()>0){
					agregarLista();
				}
			}
			catch(Exception e){}
		}
	}
	
	public void limpiarLista(){
		int tam = lista.size();
		for(int i=0; i<tam; i++){
			ListaDosis l = (ListaDosis)lista.get(0);
			l.setVisible(false);
			lista.remove(0);
		}
	}
	
	public void agregarLista(){
		int height = 95*lista.size();
		ventana.getPanelListaDosis().setPreferredSize(new Dimension(280, height));
		ventana.getPanelListaDosis().setSize(new Dimension(280, 288));
		for(int i=0; i<lista.size(); i++){
			ListaDosis l = (ListaDosis)lista.get(i);
			l.setLocation(0, 95*i);
			ventana.getPanelListaDosis().add(l);
			int x = ventana.getVentana().getX();
			ventana.getVentana().setLocation(x+1, ventana.getVentana().getY());
			ventana.getVentana().setLocation(x, ventana.getVentana().getY());
		}
	}
	
	public void mostrarMedicamentos(String where){
		Conexion con = new Conexion(autenticacion);
		if(con.conectar()){
			String sql;
			sql = "SELECT m.nombre as nombre, p.descripcion as presentacion, pm.id as pm "
					+"FROM medicinas m, presentaciones p, presentaciones_medicina pm "
					+"WHERE m.id=pm.mdna_id AND p.id=pm.pscn_id AND m.valida='s' ";
			sql = sql + "AND "+where;
			ventana.getModeloMedicamentos().setRowCount(0);
			int cont = 0;
			java.sql.ResultSet res = con.consultar(sql);
			try{
				String vpm="";
				while(res.next()){
					ventana.getModeloMedicamentos().setRowCount(cont+1);
					ventana.getTablaMedicamentos().setValueAt(res.getString("nombre").toString(), cont, 0);
					ventana.getTablaMedicamentos().setValueAt(res.getString("presentacion").toString(), cont, 1);
					cont++;
					vpm = res.getString("pm").toString()+"/";
				}
				if(cont == 0){
					//ventana.mostrarMensaje("No se encontraron medicamentos", ventana.ERROR);
					vecPM = null;
					limpiarLista();
					return;
				}
				else{
					vpm = vpm.substring(0, vpm.length()-1);
					vecPM = vpm.split("/");
				}
			}
			catch(Exception e){
				System.out.println("Error GestorMostrarMedicamentos: mostrarMedicamentos()");
			}
		}
		else{
			ventana.mostrarMensaje("No se pudo conectar al servidor", ventana.ERROR);
		}
	}
}
