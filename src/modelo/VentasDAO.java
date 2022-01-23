/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
 * 
 *******************************/
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentasDAO extends conectDb {
    conectDb conectar = new conectDb();
    Connection conn;

    ResultSet rs;
    PreparedStatement ps;
    
    public int getLastNumeroPedido(){
        int idPedido = 0;
        String sql = "SELECT max(id_pedido) FROM dg.pedidos_clientes";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            idPedido = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex000v90 " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Fx00v90: " + e.getMessage());            
                return 0;
            }
         }
        return idPedido;
    }
    
    public int check_pedido_existe(int idPedido){
        int resultado;
        String sql = "SELECT id_pedido FROM dg.pedidos_clientes WHERE id_pedido = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00e20 : " + e.getMessage());            
            return 0;
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00e21 : " + e.getMessage());            
                return 0;
            }
         }
        return resultado;
    }
    
    
    public List listar_pedidos_pendientes_dao(){
        List<Ventas> datos = new ArrayList<>();
        String sql = "SELECT 	a.id_pedido, a.fecha_pedido, "
                + "a.rut_cliente, (SELECT concat_ws(' ', nombre_cliente, apellido_cliente) FROM dg.clientes where rut_cliente = a.rut_cliente) as persona,"
                + "(select fono_cliente FROM dg.clientes where rut_cliente = a.rut_cliente) as fonocliente, "
                + "a.id_pack, (SELECT nombre_pack FROM dg.packs WHERE id_pack = a.id_pack) as nombrepack, "
                + "a.precio_venta,  a.id_estado, (SELECT descripcion FROM dg.estados WHERE id_estado = a.id_estado) as estadopedido "
                + "FROM dg.pedidos_clientes a WHERE a.id_estado = 3";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas v = new Ventas();
                v.setIdVenta(rs.getInt(1));
                v.setFechaVenta(rs.getDate(2));
                v.setRutCliente(rs.getString(3));
                v.setNombreFull(rs.getString(4));
                v.setFonoCliente(rs.getString(5));
                v.setIdPack(rs.getInt(6));
                v.setNombrePack(rs.getString(7));
                v.setPrecioVenta(rs.getFloat(8));
                v.setIdEstado(rs.getInt(9));
                v.setEstadoPedido(rs.getString(10));
                datos.add(v);
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
    
    public ArrayList<Ventas> retorna_datos_pedido_cliente(int idPedido){
        ArrayList<Ventas>datosven = new ArrayList<>();
        String sql = "SELECT a.id_pedido, a.rut_cliente, a.fecha_pedido, "
                + "a.id_red, (SELECT nombre_red FROM dg.redes WHERE  id_red = a.id_red) as redsocial, "
                + "a.id_pack, (SELECT nombre_pack FROM dg.packs WHERE id_pack = a.id_pack) as nombrepack, a.precio_venta, "
                + "a.id_estado, (SELECT descripcion FROM dg.estados WHERE id_estado = a.id_estado) as estadopedido, a.fecha_pago, "
                + "a.id_banco, (SELECT  nombre_banco FROM dg.bancos WHERE id_banco = a.id_banco) as nombrebanco, a.codigo_transfer, "
                + "b.nombre_destino, b.direccion_destino, b.id_comuna, (SELECT nombre_comuna FROM dg.comunas WHERE id_comuna = b.id_comuna) as nombrecomuna, "
                + "b.fono_destino,b.correo_destino, b.fecha_envio, b.fecha_recibido, b.mensaje, b.hora_ini, b.hora_fin "
                + "FROM dg.pedidos_clientes a, dg.pedidos_destinos b "
                + "WHERE a.id_pedido = b.id_pedido AND  a.id_pedido = ?";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas v = new Ventas();
                v.setIdVenta(rs.getInt(1));
                v.setRutCliente(rs.getString(2));
                v.setFechaVenta(rs.getDate(3));
                v.setIdRedes(rs.getInt(4));
                v.setNombreRed(rs.getString(5));
                v.setIdPack(rs.getInt(6));
                v.setNombrePack(rs.getString(7));
                v.setPrecioVenta(rs.getFloat(8));
                v.setIdEstado(rs.getInt(9));
                v.setEstadoPedido(rs.getString(10));
                v.setFechaPago(rs.getDate(11));
                v.setIdBanco(rs.getInt(12));
                v.setNombreBanco(rs.getString(13));
                v.setIdTransfer(rs.getString(14));
                v.setNombreDestino(rs.getString(15));
                v.setDirDestino(rs.getString(16));
                v.setIdComunaDest(rs.getInt(17));
                v.setNombreComunaDest(rs.getString(18));
                v.setFonoDestino(rs.getString(19));
                v.setCorreoDestino(rs.getString(20));
                v.setFechaDespIni(rs.getDate(21));
                v.setFechaDespFin(rs.getDate(22));
                v.setMensajeDest(rs.getString(23));           
                v.setHoraIni(rs.getString(24));
                v.setHoraFin(rs.getString(25));
                datosven.add(v);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00020 : " + e.getMessage());
        }finally{
         try{
           conn.close();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error de conexión Ex0021 : " + e.getMessage());
        }
       } 
       return datosven;
    }    
        
   
    public int insertar_pedidos_dao(String rutCliente, Date fechaPedido, int idRed, int idPack, float precioVenta, int idEstado, Date fechaPago, int idBanco, String codigoTransfer ){
        String sql = "INSERT INTO dg.pedidos_clientes "
                + "(rut_cliente, fecha_pedido, id_red, id_pack, precio_venta, id_estado, fecha_pago, id_banco, codigo_transfer) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        
        if (rutCliente.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, rutCliente.toUpperCase());
                ps.setDate(2, fechaPedido);
                ps.setInt(3, idRed);
                ps.setInt(4, idPack);
                ps.setFloat(5, precioVenta);
                ps.setInt(6, idEstado);
                ps.setDate(7, fechaPago);
                ps.setInt(8, idBanco);
                ps.setString(9, codigoTransfer);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00070: " + e.getMessage());            
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex00071 : " + e.getMessage());  
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00072 ");
            return 0;
        }
        return 1;
    }

    public int insertar_destinos_dao(int idPedido, String nombreDestino, String direccionDestino, int idComuna, String fonoDestino, String correoDestino, Date fechaEnvio, String mensaje, int idEstado, String horaIni, String horaFin){
        String sql = "INSERT INTO dg.pedidos_destinos "
                + "(id_pedido, nombre_destino, direccion_destino, id_comuna, fono_destino, correo_destino, fecha_envio, mensaje, id_estado, hora_ini, hora_fin) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        if (nombreDestino.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, idPedido);
                ps.setString(2, nombreDestino);
                ps.setString(3, direccionDestino);
                ps.setInt(4, idComuna);
                ps.setString(5, fonoDestino);
                ps.setString(6, correoDestino);
                ps.setDate(7, fechaEnvio);
                ps.setString(8, mensaje);
                ps.setInt(9, idEstado);
                ps.setString(10, horaIni);
                ps.setString(11, horaFin);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00073: " + e.getMessage());  
                return 0;
            }finally{
                try{
                    conn.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error de conexión Ex00074 : " + e.getMessage());  
                }
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00075 ");
            return 0;
        }
        return 1;
    }
    
    public int actualiza_pedidos_dao(int idPedido, String rutCliente, Date fechaPedido, int idRed, int idPack, float precioVenta, int idEstado, Date fechaPago, int idBanco, String codigoTransfer ){
        String sql = "UPDATE dg.pedidos_clientes "
                + "rut_cliente=?, fecha_pedido=?, id_red=?, id_pack=?, precio_venta=?, id_estado=?, fecha_pago=?, id_banco=?, codigo_transfer=?"
                + "WHERE id_pedido = ?";
        
        if (rutCliente.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, rutCliente.toUpperCase());
                ps.setDate(3, fechaPedido);
                ps.setInt(4, idRed);
                ps.setInt(5, idPack);
                ps.setFloat(6, precioVenta);
                ps.setInt(7, idEstado);
                ps.setDate(7, fechaPago);
                ps.setInt(7, idBanco);
                ps.setString(7, codigoTransfer);
                ps.setInt(7, idPedido);
                
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00076 " + e.getMessage());    
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
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00077 ");
            return 0;
        }
        return 1;
    }

    public int actualiza_destinos_dao(int idPedido, String nombreDestino, String direccionDestino, int idComuna, String fonoDestino, String correoDestino, Date fechaEnvio, String mensaje, int idEstado, String horaIni, String horaFin){
        String sql = "UPDATE dg.pedidos_destinos "
                + "nombre_destino=?, direccion_destino=?, id_comuna=?, fono_destino=?, correo_destino=?, fecha_envio=?, mensaje=?, id_estado=?, hora_ini=?, hora_fin=?"
                + "WHERE id_pedido = ?";
        
        if (nombreDestino.trim() != "") {
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombreDestino.toUpperCase());
                ps.setString(2, direccionDestino.toUpperCase());
                ps.setInt(3, idComuna);
                ps.setString(4, fonoDestino);
                ps.setString(5, correoDestino);
                ps.setDate(6, fechaEnvio);
                ps.setString(7, mensaje);
                ps.setInt(8, idEstado);
                ps.setInt(9, idPedido);
                ps.setString(10, horaIni);
                ps.setString(11, horaFin);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex00078 " + e.getMessage());    
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
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00079 ");
            return 0;
        }
        return 1;
    }
    
    public int actualiza_datos_pago(int idEstado, int idBanco, Date fechaPago, String codeTransfer, int idPedido){
        String sql = "UPDATE dg.pedidos_clientes SET id_estado = ?, id_banco = ?, fecha_pago = ?, codigo_transfer = ? "
                + "WHERE id_pedido = ?";
        if (idPedido > 0){
            try{
                conn = conectar.getConexion();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, idEstado);
                ps.setInt(2, idBanco);
                ps.setDate(3, fechaPago);
                ps.setString(4, codeTransfer);
                ps.setInt(5, idPedido);
                ps.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión Ex000A78 " + e.getMessage());    
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
            JOptionPane.showMessageDialog(null, "Error de conexión Ex00079 ");
            return 0;
        }
        return 1;
    }
    
}
