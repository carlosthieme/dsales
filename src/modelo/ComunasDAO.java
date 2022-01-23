/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ComunasDAO extends conectDb {
    conectDb   conectar = new conectDb();
    Connection conn;
    ResultSet  rs;
    PreparedStatement ps;
    
    public int busca_id_comuna(String comunaBuscada){
        int resultado;
        String sql = "SELECT id_comuna FROM dg.comunas WHERE nombre_comuna = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, comunaBuscada);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex0020 : " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0021 : " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }
        
    public List listar_comunas_dao(){
        List<Comunas>datos = new ArrayList<>();
        String sql = "SELECT id_comuna, nombre_comuna FROM dg.comunas ORDER BY nombre_comuna";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comunas a = new Comunas();
                a.setIdComuna(rs.getInt(1));
                a.setNombreComuna(rs.getString(2));
                datos.add(a);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex0022 : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex0023 : " + e.getMessage());
        }
       } 
       return datos;
    }    

    public int insertar_comunas_dao(String nombreComuna){
        String sql = "INSERT INTO dg.comunas (nombre_comuna) VALUES (?)";
        if (nombreComuna.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreComuna.toUpperCase());
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0024 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre de una Comuna de la Región Metropolitana");
            return 0;
        }
        return 1;
    }     
    
    
    public int actualizar_comunas_dao(int idComuna, String nombreComuna){
        String sql = "UPDATE dg.comunas SET nombre_comuna = ? WHERE id_comuna = ?";
        if (nombreComuna.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreComuna.toUpperCase());
                ps.setInt(2, idComuna);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0025 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex0026: " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de un comuna");
            return 0;
        }
        return 1;
    }
    
    
}
