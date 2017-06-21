package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import Utilitario.Autenticacion;
import java.sql.ResultSet;

public class Conexion {

    private Connection conexion;
    private Autenticacion autenticacion;
    private Statement statement;

    public Conexion(Autenticacion a) {
        autenticacion = a;
        conexion = null;
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + autenticacion.getIpServidor() + "/consultorio", autenticacion.getUsuarioBD(), autenticacion.getClaveBD());
            statement = conexion.createStatement();
            return true;
        }
        catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
            }
            catch (Exception e) {
            }
        }
    }
    
    public ResultSet consultar(String query){
        try{
            ResultSet rs = statement.executeQuery(query);
            return rs;
        }
        catch(Exception e){
        	System.out.println("Error conexion "+query);
        	e.printStackTrace();
            return null;
        }
    }
    
    public boolean actualizar(String query){
        try{
            statement.executeUpdate(query);
            return true;
        }
        catch(Exception e){
        	System.out.println("Error Conexion "+query);
        	e.printStackTrace();
            return false;
        }
    }
    
    public Connection getConexion(){
    	return conexion;
    }
}
