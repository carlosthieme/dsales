/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Scanner;


public class ArticuloDAO extends conectDb {
    
    conectDb conectar = new conectDb();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    
    
    public List listar_articulos_dao(){
        List<Articulos>datos = new ArrayList<>();
        String sql = "SELECT a.id_artic, a.nombre_artic, a.stock_artic, a.fecha_vence, b.id_categoria, b.nombre_categoria, a.precio_artic, c.id_estado, c.descripcion";
        sql = sql + " FROM dg.articulos a, dg.categorias b, dg.estados c";
        sql = sql + " WHERE a.id_categoria = b.id_categoria AND a.id_estado = c.id_estado";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Articulos a = new Articulos();
                a.setIdArticulo(rs.getInt(1));
                a.setNombreArticulo(rs.getString(2));
                a.setStockArticulo(rs.getInt(3));
                a.setFechaVence(rs.getDate(4));
                a.setIdCategoria(rs.getInt(5));
                a.setNombreCategoria(rs.getString(6));
                a.setPrecioArticulo(rs.getFloat(7));
                a.setIdEstado(rs.getInt(8));
                a.setDescripcionEstado(rs.getString(9));
                datos.add(a);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión A: " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión B: " + e.getMessage());
        }
       } 
       return datos;
    }
    
    
    public int insertar_articulos_dao(String nombreArticulo, int stockArticulo, Date fechaVence, int idCategoria, float precioArticulo, int idEstado ) throws ParseException{
        String sql = "INSERT INTO dg.articulos (nombre_artic, stock_artic, fecha_vence, id_categoria, precio_artic, id_estado) VALUES (?,?,?,?,?,?)";
        if (nombreArticulo.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreArticulo.toUpperCase());
                ps.setInt(2, stockArticulo);
                ps.setDate(3, fechaVence);
                ps.setInt(4, idCategoria);
                ps.setFloat(5, precioArticulo);
                ps.setInt(6, idEstado);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión 1: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión 2: " + e.getMessage());  
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            return 0;
        }
        return 1;
    }
    
    public int actualizar_articulos_dao(int idArticulo, String nombreArticulo, int stockArticulo, Date fechaVence, int idCategoria, float precioArticulo, int idEstado ){
        String sql = "UPDATE dg.articulos SET nombre_artic = ?, stock_artic= ?, fecha_vence = ?, id_categoria = ?, precio_artic = ?, id_estado = ? WHERE id_artic = ?";
        
        if (nombreArticulo.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreArticulo.toUpperCase());
                ps.setInt(2, stockArticulo);
                ps.setDate(3, fechaVence);
                ps.setInt(4, idCategoria);
                ps.setFloat(5, precioArticulo);
                ps.setInt(6, idEstado);
                ps.setInt(7, idArticulo);
                
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());    
                return 0;
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                    return 0;
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            return 0;
        }
        return 1;
    }
    
    
    
    
        
}
