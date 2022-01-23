/**
 * @author Carlos Thieme
 * fecha   13/09/2021.-
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

public class UsuariosDAO  extends conectDb{
    conectDb   conectar = new conectDb();
    Connection conn;
    ResultSet  rs;
    PreparedStatement ps;

    public int busca_usuarios_login(String idUsuario, String claveUsuario){
    int resultado;
    String sql = "SELECT id_estado FROM dg.usuarios WHERE id_usuario = ? AND clave_usuario = ? AND id_estado = 1";
    try{
        conn = conectar.getConexion();
        ps = conn.prepareStatement(sql);
        ps.setString(1, idUsuario);
        ps.setString(2, claveUsuario);
        rs = ps.executeQuery();
        if(rs.next()) {
            resultado = rs.getInt(1);
        } else{
            resultado = 0;
        }
            
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage() + ". Error de conexión Ex0030");            
        return 0;
    }finally{
        try{
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() + ". Error de conexión Ex0031");          
            return 0;
        }
    }
    return resultado;
    }

    public List listar_usuarios_dao(){
        List<Usuarios>datos = new ArrayList<>();
        String sql = "Select a.id_usuario, a.nombre_usuario, b.id_estado, b.descripcion FROM dg.usuarios a, dg.estados b WHERE a.id_estado = b.id_estado ORDER BY a.nombre_usuario";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getString(1));
                u.setNombreUsuario(rs.getString(2));
                u.setIdEstadoUsuario(rs.getInt(3));
                u.setEstadoUsuario(rs.getString(4));
                datos.add(u);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex0032 : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex0033 : " + e.getMessage());
        }
       } 
       return datos;
    }        
 
    public int insertar_usuarios_dao(String idUsuario, String nombreUsuario, String claveUsuario, int idEstado){
        String sql = "INSERT INTO dg.usuarios (id_usuario, nombre_usuario, clave_usuario, id_Estado) VALUES (?,?,?,?)";
        if (nombreUsuario.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, idUsuario);
                ps.setString(2, nombreUsuario.toUpperCase());
                ps.setString(3, claveUsuario);
                ps.setInt(4, idEstado);
                
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0034 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre de Usuario");
            return 0;
        }
        return 1;
    }     
    
    public int actualizar_usuarios_dao(String idUsuario, String nombreUsuario, String claveUsuario, int idEstado){
        String sql = "UPDATE dg.usuarios SET nombre_usuario = ?, clave_usuario = ?, id_estado = ? WHERE id_usuario = ?";
        if (nombreUsuario.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreUsuario.toUpperCase());
                ps.setString(2, claveUsuario);
                ps.setInt(3, idEstado);
                ps.setString(4, idUsuario);
                
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex0035 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex0036: " + e.getMessage());
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe Ingresar el Nombre de Usuario");
            return 0;
        }
        return 1;
    }
    
}
