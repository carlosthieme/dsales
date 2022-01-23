/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
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


public class CategoriasDAO extends conectDb{
    conectDb conectar = new conectDb();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public int busca_id_categoria(String categoriaBuscada){
        int resultado;
        String sql = "SELECT id_categoria FROM dg.categorias WHERE nombre_categoria = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoriaBuscada);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión E: " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión F: " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }
    
    public List listarcat(){
        List<Categorias>datos = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre_categoria FROM dg.categorias order by nombre_categoria";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias a = new Categorias();
                a.setIdCategoria(rs.getInt(1));
                a.setNombreCategoria(rs.getString(2));
                datos.add(a);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
       } 
       return datos;
    }
    
    public int insertar_categorias_dao(String nombreCategoria){
        String sql = "INSERT INTO dg.categorias (nombre_categoria) VALUES (?)";
        if (nombreCategoria.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreCategoria.toUpperCase());
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
            JOptionPane.showMessageDialog(null, "Debe Ingresar un Nombre de Categoría");
            return 0;
        }
        return 1;
    }     
    
    
    public int actualizar_categorias_dao(int idCategoria, String nombreCategoria ){
        String sql = "UPDATE dg.categorias SET nombre_categoria = ? WHERE id_categoria = ?";
        if (nombreCategoria.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreCategoria.toUpperCase());
                ps.setInt(2, idCategoria);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de Categoria");
            return 0;
        }
        return 1;
    }
    
}