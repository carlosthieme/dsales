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
import java.util.Scanner;


public class BancosDAO extends conectDb {
    conectDb conectar = new conectDb();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public int busca_id_banco(String bancoBuscado){
        int resultado;
        String sql = "SELECT id_banco FROM dg.bancos WHERE nombre_banco = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bancoBuscado);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex008 : " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex009 : " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }
    
    public List listar_bancos_dao(){
        List<Bancos>datos = new ArrayList<>();
        String sql = "SELECT a.id_banco, a.nombre_banco, a.id_estado, b.descripcion FROM dg.bancos a, dg.estados b WHERE a.id_estado = b.id_estado ORDER BY nombre_banco";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bancos a = new Bancos();
                a.setIdBanco(rs.getInt(1));
                a.setNombreBanco(rs.getString(2));
                a.setIdEstado(rs.getInt(3));
                a.setDescribeEstado(rs.getString(4));
                datos.add(a);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex0010 : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex0011 : " + e.getMessage());
        }
       } 
       return datos;
    }    

    public int insertar_bancos_dao(String nombreBanco, int idEstado){
        String sql = "INSERT INTO dg.bancos (nombre_banco, id_estado) VALUES (?, ?)";
        if (nombreBanco.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreBanco.toUpperCase());
                ps.setInt(2, idEstado);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0012 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre de un Banco de la Plaza");
            return 0;
        }
        return 1;
    }     
    
    
    public int actualizar_bancos_dao(int idBanco, String nombreBanco, int idEstado ){
        String sql = "UPDATE dg.bancos SET nombre_banco = ?, id_estado = ? WHERE id_banco = ?";
        if (nombreBanco.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreBanco.toUpperCase());
                ps.setInt(2, idEstado);
                ps.setInt(3, idBanco);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0013 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex0014 : " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre de un Banco de la Plaza");
            return 0;
        }
        return 1;
    }
    
}
