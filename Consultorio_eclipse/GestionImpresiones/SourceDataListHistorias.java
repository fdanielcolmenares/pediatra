package GestionImpresiones;

public class SourceDataListHistorias {
		private int ID;
		private String name = null;
		
		public SourceDataListHistorias(){
			ID = 0;		
		}
		
		public SourceDataListHistorias(int ID,String name){
				this.ID = ID;
				this.name = name;
		}

//GET AND SETTERS		
		public int getID() {
			return ID;
		}

		public void setID(int id) {
			ID = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
//		GET AND SETTERS
		public String getID1String(){
			String valor = "";
			valor = "000000"+this.ID;
			valor = valor.substring(valor.length()-6, valor.length());
			return valor;
		}
		
}
