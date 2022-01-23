/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
 * 
 *******************************/
package modelo;

//import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ClientesDAO extends conectDb {
    
    conectDb conectar = new conectDb();
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;
    
    
    public ArrayList<Clientes> retorna_cliente_seleccionado(String rutCliente){
        ArrayList<Clientes>resultado = new ArrayList<>();
        String sql = "SELECT a.rut_cliente, a.nombre_cliente, a.apellido_cliente, a.dir_cliente, a.fono_cliente, a.correo_cliente, a.fenac_cliente, "
                + "a.id_estado, b.descripcion " 
                + "FROM dg.clientes a, dg.estados b "
                + "WHERE a.id_estado = b.id_estado "
                + "AND a.rut_cliente = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, rutCliente.toUpperCase());            
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setRutCliente(rs.getString(1));
                c.setNombreCliente(rs.getString(2));
                c.setApellidoCliente(rs.getString(3));
                c.setDirCliente(rs.getString(4));
                c.setFonoCliente(rs.getString(5));
                c.setCorreoCliente(rs.getString(6));
                c.setFechaNacimiento(rs.getDate(7));
                c.setIdEstadoCliente(rs.getInt(8));
                c.setDescribeEstado(rs.getString(9));
                resultado.add(c);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex008 : " + e.getMessage());            
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex009 : " + e.getMessage());            
            }
        }
        return resultado;
    }
    
    
    public List listar_clientes_dao(){
        List<Clientes>datos = new ArrayList<>();
        String sql = "SELECT a.rut_cliente, a.nombre_cliente, a.apellido_cliente, a.dir_cliente, a.fono_cliente, a.correo_cliente, a.fenac_cliente, a.id_estado, b.descripcion " 
                + "FROM dg.clientes a, dg.estados b "
                + "WHERE a.id_estado = b.id_estado "
                + "ORDER BY a.nombre_cliente";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setRutCliente(rs.getString(1));
                c.setNombreCliente(rs.getString(2));
                c.setApellidoCliente(rs.getString(3));
                c.setDirCliente(rs.getString(4));
                c.setFonoCliente(rs.getString(5));
                c.setCorreoCliente(rs.getString(6));
                c.setFechaNacimiento(rs.getDate(7));
                c.setIdEstadoCliente(rs.getInt(8));
                c.setDescribeEstado(rs.getString(9));
                datos.add(c);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00080 : " + e.getMessage());
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00081 : " + e.getMessage());
            }
        } 
        return datos;
    }
    
    public int insertar_clientes_dao(String rutCliente, String nombreCliente, String apellidoCliente, String dirCliente, String fonoCliente, String correoCliente, Date fechaNacimiento, int idEstado ) throws ParseException{
        String sql = "INSERT INTO dg.clientes (rut_cliente, nombre_cliente, apellido_cliente, dir_cliente, fono_cliente, correo_cliente, fenac_cliente, id_estado) VALUES (?,?,?,?,?,?,?,?)";
        if (rutCliente.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, rutCliente.toUpperCase());
                ps.setString(2, nombreCliente);
                ps.setString(3, apellidoCliente);
                ps.setString(4, dirCliente);
                ps.setString(5, fonoCliente);
                ps.setString(6, correoCliente);
                ps.setDate(7, fechaNacimiento);
                ps.setInt(8, idEstado);
                
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println(rutCliente);
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00082 : " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex00083 : " + e.getMessage());  
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Debe ingresar un Valor");
            return 0;
        }
        return 1;
    }
    
    public int actualizar_clientes_dao(String rutCliente, String nombreCliente, String apellidoCliente, String dirCliente, String fonoCliente, String correoCliente, Date fechaNacimiento, int idEstado){
        String sql = "UPDATE dg.clientes SET nombre_cliente= ?, apellido_cliente= ?, dir_cliente= ?, fono_cliente= ?, correo_cliente= ?, fenac_cliente= ?, id_estado= ? WHERE rut_cliente = ?";
        if (rutCliente.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreCliente);
                ps.setString(2, apellidoCliente);
                ps.setString(3, dirCliente);
                ps.setString(4, fonoCliente);
                ps.setString(5, correoCliente);
                ps.setDate(6, fechaNacimiento);
                ps.setInt(7, idEstado);
                ps.setString(8, rutCliente);
                
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00084 " + e.getMessage());    
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
            JOptionPane.showMessageDialog(null, "Debe ingresar un Valor");
            return 0;
        }
        return 1;
    }

    
    
    
}
