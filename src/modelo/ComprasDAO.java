/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ComprasDAO extends conectDb {
    
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public boolean registarCompra(Compras com) throws SQLException {
       Connection con = getConexion(); 
       sql = "INSERT INTO compras (ordenes_id_oc,numfactura,fecha_compra) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, com.getOrdenesIdOrden());
            ps.setInt(2, com.getNumeroFactura());
            ps.setDate(3, (Date) com.getFechaCompra());
            ps.execute();
            
            sql = "UPDATE ordenes_compra SET estados_id_estado = 2 WHERE id_oc= ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, com.getOrdenesIdOrden());
            ps.execute();         
                      
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
      
    
    public boolean registarDetCompras(Compras com) throws SQLException {

        Connection con = getConexion();
        
        sql = "INSERT INTO detalle_compra ( articulos_id_artic, compras_id_compra, cant_art, precio_art, fecha_venc) VALUES (?,(SELECT max(id_compra) FROM compras),?,?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, com.getArticulos_id_articulo());
            ps.setInt(2, com.getCant_art());
            ps.setInt(3, com.getPrecio_art());
            ps.setDate(4, (Date) com.getFechaVencimiento());
            ps.execute();
            
            sql = "UPDATE articulos SET stock_artic = stock_artic + ?, fecha_venc = ? WHERE id_artic = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, com.getCant_art());
            ps.setDate(2, (Date) com.getFechaVencimiento());
            ps.setInt(3, com.getArticulos_id_articulo());
            ps.execute();         

            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
    
    public List listarCompras(int mes,int year) {
        
        Connection con = getConexion();
        
        if (mes == 0) {
            
            sql = "SELECT ordenes_id_oc, nombre_prove, numfactura, fecha_orden, fecha_compra, "
                    + "(select sum(precio_art * cant_art * 1.19) from detalle_compra "
                    + "where compras_id_compra=id_compra group by compras_id_compra) as total, id_compra FROM compras "
                    + "inner join ordenes_compra on id_oc = ordenes_id_oc "
                    + "inner join proveedores on id_proveedor = proveedores_id_proveedor";
        } else {
            
            sql = "SELECT ordenes_id_oc, nombre_prove, numfactura, fecha_orden, fecha_compra, "
                    + "(select sum(precio_art * cant_art * 1.19) from detalle_compra "
                    + "where compras_id_compra=id_compra group by compras_id_compra) as total, id_compra FROM compras "
                    + "inner join ordenes_compra on id_oc = ordenes_id_oc "
                    + "inner join proveedores on id_proveedor = proveedores_id_proveedor "
                    + "where DATE_FORMAT(fecha_compra,'%m')= ? and DATE_FORMAT(fecha_compra,'%Y')= ?";
        }

        List<Compras> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            if (mes > 0) {
                ps.setInt(1, mes);
                ps.setInt(2,year);
            }
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Compras com = new Compras();

                com.setOrdenesIdOrden(Integer.parseInt(rs.getString("ordenes_id_oc")));
                com.setProveedor(rs.getString("nombre_prove"));
                com.setNumeroFactura(Integer.parseInt(rs.getString("numfactura")));
                com.setFechaPedido(rs.getDate("fecha_orden"));
                com.setFechaCompra(rs.getDate("fecha_compra"));
                com.setTotal(Float.parseFloat(rs.getString("total")));
                com.setIdCompra(Integer.parseInt(rs.getString("id_compra")));
                datos.add(com);
            }
        } catch (SQLException e) {
            System.err.println(e);

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return datos;
    }
    
    
    public List listarDetalleCompra(int id_compra) {
       
        Connection con = getConexion();

        String sql = "SELECT nombre_artic, cant_art, precio_art, detalle_compra.fecha_venc FROM detalle_compra  "
                + "inner join articulos on id_artic = articulos_id_artic WHERE compras_id_compra = ?";
           
        List<Compras> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compras com = new Compras();

                com.setNombreArticulo(rs.getString("nombre_artic"));
                com.setCant_art(Integer.parseInt(rs.getString("cant_art")));
                com.setPrecio_art(Integer.parseInt(rs.getString("precio_art")));
                com.setFechaVencimiento(rs.getDate("fecha_venc"));
                datos.add(com);
            }
        } catch (SQLException e) {
            System.err.println(e);

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return datos;
    }
    
    
}
