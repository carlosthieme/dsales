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

public class RedesSocDAO extends conectDb{
    conectDb conectar = new conectDb();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public int busca_id_red_social(String redBuscada){
        int resultado;
        String sql = "SELECT id_red FROM dg.redes WHERE nombre_red = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, redBuscada);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex000 : " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex001: " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }

    
    public List listar_redes_sociales(){
        List<RedesSoc>datos = new ArrayList<>();
        String sql = "SELECT a.id_red, a.nombre_red, a.id_estado, b.descripcion FROM dg.redes a, dg.estados b WHERE a.id_estado = b.id_estado ORDER BY nombre_red";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                RedesSoc a = new RedesSoc();
                a.setIdRed(rs.getInt(1));
                a.setNombreRed(rs.getString(2));
                a.setIdEstado(rs.getInt(3));
                a.setDescripcionEstado(rs.getString(4));
                datos.add(a);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex003 " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex004: " + e.getMessage());
        }
       } 
       return datos;
    }
    
    public int insertar_redes_dao(String nombreRed, int idEstado){
        String sql = "INSERT INTO dg.redes (nombre_red, id_estado) VALUES (?, ?)";
        if (nombreRed.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreRed.toUpperCase());
                ps.setInt(2, idEstado);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex005: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar un Nombre de Red Social");
            return 0;
        }
        return 1;
    }     
    
    
    public int actualizar_redes_dao(int idRed, String nombreRed, int idEstado ){
        String sql = "UPDATE dg.redes SET nombre_red = ?, id_estado = ? WHERE id_red = ?";
        if (nombreRed.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreRed.toUpperCase());
                ps.setInt(2, idEstado);
                ps.setInt(3, idRed);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex006:  " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex007: " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de Red Social");
            return 0;
        }
        return 1;
    }
    
}
