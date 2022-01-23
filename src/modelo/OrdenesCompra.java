/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Pro 6300
 */
public class OrdenesCompra {
    
   private int idOrdenCompra;
   private Date fechaOrden;
   private int estadosIdEstado;
   private int proveedoresIdProveedor;
   private int ordenes_idOrdenC; // de la relacion de tabla detalle orden compra
   private int articulos_id_artic; //de la relacion de tabla detalle orden compra
   private int cantidad_artic; //de la relacion de tabla detalle orden compra
   private String articulo; // literal nombre articulo
   private String proveedor; //literal de relacion tabla proveedores
   private String estado; // literal tabla estados
   
   
    public String getNombreArticulo() {
        return articulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.articulo = nombreArticulo;
    }
  
    public int getOrdenes_idOrdenC() {
        return ordenes_idOrdenC;
    }

    public void setOrdenes_idOrdenC(int ordenes_idOrdenC) {
        this.ordenes_idOrdenC = ordenes_idOrdenC;
    }

    public int getArticulos_id_artic() {
        return articulos_id_artic;
    }

    public void setArticulos_id_artic(int articulos_id_artic) {
        this.articulos_id_artic = articulos_id_artic;
    }

    public int getCantidad_artic() {
        return cantidad_artic;
    }

    public void setCantidad_artic(int cantidad_aric) {
        this.cantidad_artic = cantidad_aric;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public int getEstadosIdEstado() {
        return estadosIdEstado;
    }

    public void setEstadosIdEstado(int estadosIdEstado) {
        this.estadosIdEstado = estadosIdEstado;
    }

    public int getProveedoresIdProveedor() {
        return proveedoresIdProveedor;
    }

    public void setProveedoresIdProveedor(int proveedoresIdProveedor) {
        this.proveedoresIdProveedor = proveedoresIdProveedor;
    }
    
}
