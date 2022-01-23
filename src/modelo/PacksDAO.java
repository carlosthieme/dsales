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


public class PacksDAO extends conectDb {
    
    conectDb conectar = new conectDb();
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;

    public int busca_id_pack(String packBuscado){
        int resultado;
        String sql = "SELECT id_pack FROM dg.packs WHERE nombre_pack = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, packBuscado);
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
     
    public float busca_precio_pack(int idPack){
        float resultado;
        String sql = "SELECT precio_pack FROM dg.packs WHERE id_pack = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPack);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getFloat(1);
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
     

    
    
    
    public List listar_packs_dao(){    
        List<Packs> datos = new ArrayList<>();
        String sql = "SELECT a.id_pack, a.nombre_pack, a.precio_pack, a.stock_pack, a.id_tipo_pack, b.nombre_tipo, a.id_estado, c.descripcion ";
        sql = sql + "FROM dg.packs a, dg.packs_tipo b, dg.estados c ";
        sql = sql + "WHERE a.id_tipo_pack = b.id_tipo_pack ";
        sql = sql + "AND   a.id_estado = c.id_estado ";
        sql = sql + "ORDER BY a.nombre_pack";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Packs p = new Packs();
                p.setIdPack(rs.getInt(1));
                p.setNombrePack(rs.getString(2));
                p.setPrecioPack(rs.getFloat(3));
                p.setStockPack(rs.getInt(4));
                p.setIdTipoPack(rs.getInt(5));
                p.setNombreTipoPack(rs.getString(6));
                p.setIdEstado(rs.getInt(7));
                p.setDescribeEstado(rs.getString(8));
                datos.add(p);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex0050: " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex0051: " + e.getMessage());
        }
       } 
       return datos;
        
    }
}
