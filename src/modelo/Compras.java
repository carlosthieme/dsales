package modelo;

/**
 *
 * @author Pro 6300
 */

import java.util.Date;

public class Compras {
    
    private int idCompra;
    private int ordenesIdOrden;
    private int numeroFactura;
    private Date fechaCompra;
    
    private int articulos_id_articulo; //del detalle de compra
    private String nombreArticulo;      //idem
    private int cant_art;               //idem
    private int precio_art;             //idem
    private Date fechaVencimiento;      //idem
    private Date fechaPedido;           //de ordenes de compra
    private String proveedor;           //proveedor de la compra
    private Float total;                  //total de la factura

    
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getArticulos_id_articulo() {
        return articulos_id_articulo;
    }

    public void setArticulos_id_articulo(int articulos_id_articulo) {
        this.articulos_id_articulo = articulos_id_articulo;
    }

    public int getCant_art() {
        return cant_art;
    }

    public void setCant_art(int cant_art) {
        this.cant_art = cant_art;
    }

    public int getPrecio_art() {
        return precio_art;
    }

    public void setPrecio_art(int precio_art) {
        this.precio_art = precio_art;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }  

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getOrdenesIdOrden() {
        return ordenesIdOrden;
    }

    public void setOrdenesIdOrden(int ordenesIdOrden) {
        this.ordenesIdOrden = ordenesIdOrden;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

   
    
}
