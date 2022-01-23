/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class EstadosDAO extends conectDb{
    conectDb conectar = new conectDb();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public int busca_id_estado(String estadoBuscado){
        int resultado;
        String sql = "SELECT id_estado FROM dg.estados WHERE descripcion = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, estadoBuscado);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión C: " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión D: " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }
    

    public List listar_estados_dao(){
        List<Estados>datos = new ArrayList<>();
        String sql = "SELECT id_estado, descripcion FROM dg.estados order by descripcion";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estados a = new Estados();
                a.setIdEstado(rs.getInt(1));
                a.setDescribeEstado(rs.getString(2));
                datos.add(a);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
       } 
       return datos;
    }    
    
    public int insertar_estado_dao(String describeEstado){
        String sql = "INSERT INTO dg.estados (descripcion) VALUES (?)";
        if (describeEstado.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, describeEstado.toUpperCase());
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            return 0;
        }
        return 1;
    }
    
    public int actualizar_estado_dao(int idEstado, String describeEstado ){
        String sql = "UPDATE dg.estados SET descripcion = ? WHERE id_estado = ?";
        if (describeEstado.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, describeEstado.toUpperCase());
                ps.setInt(2, idEstado);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            return 0;
        }
        return 1;
    }
   
}
