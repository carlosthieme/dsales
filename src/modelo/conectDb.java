package modelo;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class conectDb {
   
    private Connection conn = null;

/* Conexión a base de datos externa */
//    private final String BASE ="datalex2_dreamgift";
//    private final String URL = "jdbc:mysql://data4lex.com:3306/" + BASE;  
//    private final String USER = "datalex2_cthieme"; 
//    private final String CLAVE = "aw868950";

/* Conexión a base de datos local desktop */    
    private final String BASE ="dg";
    private final String URL = "jdbc:mysql://localhost:8689/" + BASE;  
    private final String USER = "caarlos";
    private final String CLAVE = "868950";
  
/* Conexión a base de datos local note */    
//    private final String BASE ="dg";
//    private final String URL = "jdbc:mysql://localhost:3306/" + BASE;  
//    private final String USER = "root";
//    private final String CLAVE = "aw868950";
 
    public Connection getConexion(){
    //public conectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
           // conn = DriverManager.getConnection(URL, USER, CLAVE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión." + e.getMessage()); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
            return conn;
    }
     
}

