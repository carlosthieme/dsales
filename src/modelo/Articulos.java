/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package modelo;

import java.util.Date;


public class Articulos {
    int     idArticulo;
    String  nombreArticulo;
    int     stockArticulo;
    Date    fechaVence;
    int     idCategoria;
    String  nombreCategoria;
    float   precioArticulo;
    int     idEstado;
    String  descripcionEstado;

    public Articulos() {
    }

    public Articulos(int idArticulo, String nombreArticulo, int stockArticulo, Date fechaVence, int idCategoria, String nombreCategoria, float precioArticulo, int idEstado, String descripcionEstado) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.stockArticulo = stockArticulo;
        this.fechaVence = fechaVence;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.precioArticulo = precioArticulo;
        this.idEstado = idEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getStockArticulo() {
        return stockArticulo;
    }

    public void setStockArticulo(int stockArticulo) {
        this.stockArticulo = stockArticulo;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public float getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(float precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    
}