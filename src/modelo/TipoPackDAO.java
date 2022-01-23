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

public class TipoPackDAO {
    conectDb conectar = new conectDb();
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;
    
    public int busca_id_tipo_pack(String tipoBusca){
        int resultado;
        String sql = "SELECT id_tipo_pack FROM dg.packs_tipo WHERE nombre_tipo = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoBusca);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00090 : " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00091 : " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }

    public List listar_tipo_packs_dao(){
        List<TipoPack>datos = new ArrayList<>();
        String sql = "SELECT a.id_tipo_pack, a.nombre_tipo, a.id_estado, b.descripcion FROM dg.packs_tipo a, dg.estados b WHERE a.id_estado = b.id_estado ORDER BY a.nombre_tipo";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoPack a = new TipoPack();
                a.setIdTipoPack(rs.getInt(1));
                a.setNombreTipoPack(rs.getString(2));
                a.setIdEstadoPack(rs.getInt(3));
                a.setDescribeEstadoPack(rs.getString(4));
                datos.add(a);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00092 : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex00093 : " + e.getMessage());
        }
       } 
       return datos;
    }    
    
    
    public int insertar_tipo_packs_dao(String nombreTipoPack, int idEstadoTipoPack){
        String sql = "INSERT INTO dg.packs_tipo (nombre_tipo, id_estado) VALUES (?, ?)";
        if (nombreTipoPack.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreTipoPack.toUpperCase());
                ps.setInt(2, idEstadoTipoPack);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00094 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar un Nombre");
            return 0;
        }
        return 1;
    }     
    
    
    public int actualizar_tipo_packs_dao(int idTipoPack, String nombreTipoPack, int idEstadoTipoPack ){
        String sql = "UPDATE dg.packs_tipo SET nombre_tipo = ?, id_estado = ? WHERE id_tipo_pack = ?";
        if (nombreTipoPack.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreTipoPack.toUpperCase());
                ps.setInt(2, idEstadoTipoPack);
                ps.setInt(3, idTipoPack);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00095 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex00096 : " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre");
            return 0;
        }
        return 1;
    }
    
    
}
