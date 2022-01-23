package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author MRBS oct 2021 madicap
 */

public class OrdenesCompraDAO extends conectDb {
   
    PreparedStatement ps = null;
    ResultSet rs;
    String sql;


    public List listarOrdenesCompra(int IdEstado) throws SQLException {
        
        Connection con = getConexion();
        
        if (IdEstado == 0) {
            sql = "SELECT id_oc, fecha_orden, nombre_prove ,descripcion,estados_id_estado FROM ordenes_compra "
                    + "inner join proveedores as p on p.id_proveedor = proveedores_id_proveedor "
                    + "inner join estados as e on e.id_estado = estados_id_estado ORDER BY id_oc DESC";
        } else {  
            sql = "SELECT id_oc, fecha_orden, nombre_prove ,descripcion,estados_id_estado FROM ordenes_compra "
                    + "inner join proveedores as p on p.id_proveedor = proveedores_id_proveedor "
                    + "inner join estados as e on e.id_estado = estados_id_estado where estados_id_estado=? ORDER BY id_oc DESC"; 
        }
        
        List<OrdenesCompra> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            if (IdEstado > 0) {
                ps.setInt(1, IdEstado);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenesCompra ordComp = new OrdenesCompra();

                ordComp.setIdOrdenCompra(Integer.parseInt(rs.getString("id_oc")));
                ordComp.setFechaOrden(rs.getDate("fecha_orden"));
                ordComp.setProveedor(rs.getString("nombre_prove"));
                ordComp.setEstado(rs.getString("descripcion"));
                ordComp.setEstadosIdEstado(Integer.parseInt(rs.getString("estados_id_estado")));
                datos.add(ordComp);
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
    
    public List listarStockArticulos() {
        Connection con = getConexion();
        sql = "SELECT id_artic, nombre_artic, stock_artic FROM articulos ORDER BY nombre_artic ASC";

        List<Articulos> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Articulos art = new Articulos();

                art.setIdArticulo(Integer.parseInt(rs.getString("id_artic")));
                art.setNombreArticulo(rs.getString("nombre_artic"));
                art.setStockArticulo(Integer.parseInt(rs.getString("stock_artic")));
                datos.add(art);
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
       
    public void listarCbProveedores(JComboBox<Proveedores> cbProveedores) throws SQLException {

       
        Connection con = getConexion();

        sql = "SELECT * FROM proveedores where es_activo_prove > 0 ORDER BY nombre_prove ASC";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            cbProveedores.addItem(new Proveedores(0," Seleccione..."));
            
            while (rs.next()) {
                cbProveedores.addItem(
                        new Proveedores(
                                Integer.parseInt(rs.getString("id_proveedor")),
                                rs.getString("nombre_prove")
                        )
                );
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
 
    }
    
    public void listarCbEstado(JComboBox<Estados> cbEstado) throws SQLException {

        Connection con = getConexion();

        sql = "SELECT * FROM estados";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            cbEstado.addItem(new Estados(0," Seleccione..."));
            
            while (rs.next()) {
                cbEstado.addItem(
                        new Estados(
                                Integer.parseInt(rs.getString("id_estado")),
                                rs.getString("descripcion")
                        )
                );
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
 
    }
    
    public List listarDetalleOrden(int id_orden) {
        Connection con = getConexion();
        sql = "SELECT id_artic, nombre_artic, cantidad_artic FROM detalle_ordenes "
              + "inner join articulos on id_artic = articulos_id_artic WHERE ordenes_id_oc = ?";

        List<OrdenesCompra> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_orden);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenesCompra ordC = new OrdenesCompra();
                ordC.setArticulos_id_artic(Integer.parseInt(rs.getString("id_artic")));
                ordC.setNombreArticulo(rs.getString("nombre_artic"));
                ordC.setCantidad_artic(Integer.parseInt(rs.getString("cantidad_artic")));
                datos.add(ordC);
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
    
    public ArrayList<OrdenesCompra> buscarOrdenCompra(OrdenesCompra ord) throws SQLException{
        Connection con = getConexion();
        ArrayList<OrdenesCompra> resultado = new ArrayList<>();

        sql = "SELECT id_oc, nombre_prove FROM ordenes_compra inner join proveedores on id_proveedor = proveedores_id_proveedor where id_oc = ? AND estados_id_estado = 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ord.getIdOrdenCompra());
            rs = ps.executeQuery();

            while (rs.next()) {
                OrdenesCompra o = new OrdenesCompra();
                o.setIdOrdenCompra(rs.getInt(1));
                o.setProveedor(rs.getString(2));
                resultado.add(o);
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
        return resultado;
    }
    
    
    public boolean registarOrden(OrdenesCompra ord) throws SQLException {
       Connection con = getConexion(); 
       sql = "INSERT INTO ordenes_compra (proveedores_id_proveedor) VALUES (?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ord.getProveedoresIdProveedor());
            
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
    
    public boolean registarDetOrdenes(OrdenesCompra ord) throws SQLException {
        Connection con = getConexion();
        sql = "INSERT INTO detalle_ordenes (articulos_id_artic, ordenes_id_oc, cantidad_artic) VALUES (?,(SELECT max(id_oc) FROM ordenes_compra),?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, ord.getArticulos_id_artic());
            ps.setInt(2, ord.getCantidad_artic());
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
 
    public boolean anularOrden(OrdenesCompra ord) {
        Connection con = getConexion();
        sql = "UPDATE ordenes_compra SET estados_id_estado = 3 WHERE id_oc=?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, ord.getIdOrdenCompra());
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
}
