/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
 * 
 *******************************/
package modelo;

import java.sql.Date;

public class Ventas {
    int idVenta;
    String rutCliente;
    Date fechaVenta;
    int idRedes;
    String nombreRed;
    int idPack;
    String nombrePack;
    Float precioVenta;
    int idEstado;
    String estadoPedido;
    Date fechaPago;
    int idBanco;
    String nombreBanco;
    String idTransfer;
    String nombreDestino;
    String apellidoDestino;
    String dirDestino;
    int idComunaDest;
    String nombreComunaDest;
    String fonoDestino;
    String correoDestino;
    Date fechaDespIni;
    Date fechaDespFin;
    Date fechaRecepcion;
    String mensajeDest;
    String horaIni;
    String horaFin;
    String nombreFull;
    String fonoCliente;
    
    
    public Ventas() {
    }

    public Ventas(int idVenta, String rutCliente, Date fechaVenta, int idRedes, String nombreRed, int idPack, String nombrePack, Float precioVenta, int idEstado, String estadoPedido, Date fechaPago, int idBanco, String nombreBanco, String idTransfer, String nombreDestino, String apellidoDestino, String dirDestino, int idComunaDest, String nombreComunaDest, String fonoDestino, String correoDestino, Date fechaDespIni, Date fechaDespFin, Date fechaRecepcion, String mensajeDest, String horaIni, String horaFin, String nombreFull, String fonoCliente) {
        this.idVenta = idVenta;
        this.rutCliente = rutCliente;
        this.fechaVenta = fechaVenta;
        this.idRedes = idRedes;
        this.nombreRed = nombreRed;
        this.idPack = idPack;
        this.nombrePack = nombrePack;
        this.precioVenta = precioVenta;
        this.idEstado = idEstado;
        this.estadoPedido = estadoPedido;
        this.fechaPago = fechaPago;
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
        this.idTransfer = idTransfer;
        this.nombreDestino = nombreDestino;
        this.apellidoDestino = apellidoDestino;
        this.dirDestino = dirDestino;
        this.idComunaDest = idComunaDest;
        this.nombreComunaDest = nombreComunaDest;
        this.fonoDestino = fonoDestino;
        this.correoDestino = correoDestino;
        this.fechaDespIni = fechaDespIni;
        this.fechaDespFin = fechaDespFin;
        this.fechaRecepcion = fechaRecepcion;
        this.mensajeDest = mensajeDest;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.nombreFull = nombreFull;
        this.fonoCliente = fonoCliente;
    }

    @Override
    public String toString() {
        return "Ventas{" + "idVenta=" + idVenta + ", rutCliente=" + rutCliente + ", fechaVenta=" + fechaVenta + ", idRedes=" + idRedes + ", nombreRed=" + nombreRed + ", idPack=" + idPack + ", nombrePack=" + nombrePack + ", precioVenta=" + precioVenta + ", idEstado=" + idEstado + ", estadoPedido=" + estadoPedido + ", fechaPago=" + fechaPago + ", idBanco=" + idBanco + ", nombreBanco=" + nombreBanco + ", idTransfer=" + idTransfer + ", nombreDestino=" + nombreDestino + ", apellidoDestino=" + apellidoDestino + ", dirDestino=" + dirDestino + ", idComunaDest=" + idComunaDest + ", nombreComunaDest=" + nombreComunaDest + ", fonoDestino=" + fonoDestino + ", correoDestino=" + correoDestino + ", fechaDespIni=" + fechaDespIni + ", fechaDespFin=" + fechaDespFin + ", fechaRecepcion=" + fechaRecepcion + ", mensajeDest=" + mensajeDest + ", horaIni=" + horaIni + ", horaFin=" + horaFin + ", nombreFull=" + nombreFull + ", fonoCliente=" + fonoCliente + '}';
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdRedes() {
        return idRedes;
    }

    public void setIdRedes(int idRedes) {
        this.idRedes = idRedes;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public String getNombrePack() {
        return nombrePack;
    }

    public void setNombrePack(String nombrePack) {
        this.nombrePack = nombrePack;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(String idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getApellidoDestino() {
        return apellidoDestino;
    }

    public void setApellidoDestino(String apellidoDestino) {
        this.apellidoDestino = apellidoDestino;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public int getIdComunaDest() {
        return idComunaDest;
    }

    public void setIdComunaDest(int idComunaDest) {
        this.idComunaDest = idComunaDest;
    }

    public String getNombreComunaDest() {
        return nombreComunaDest;
    }

    public void setNombreComunaDest(String nombreComunaDest) {
        this.nombreComunaDest = nombreComunaDest;
    }

    public String getFonoDestino() {
        return fonoDestino;
    }

    public void setFonoDestino(String fonoDestino) {
        this.fonoDestino = fonoDestino;
    }

    public String getCorreoDestino() {
        return correoDestino;
    }

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

    public Date getFechaDespIni() {
        return fechaDespIni;
    }

    public void setFechaDespIni(Date fechaDespIni) {
        this.fechaDespIni = fechaDespIni;
    }

    public Date getFechaDespFin() {
        return fechaDespFin;
    }

    public void setFechaDespFin(Date fechaDespFin) {
        this.fechaDespFin = fechaDespFin;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getMensajeDest() {
        return mensajeDest;
    }

    public void setMensajeDest(String mensajeDest) {
        this.mensajeDest = mensajeDest;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreFull() {
        return nombreFull;
    }

    public void setNombreFull(String nombreFull) {
        this.nombreFull = nombreFull;
    }

    public String getFonoCliente() {
        return fonoCliente;
    }

    public void setFonoCliente(String fonoCliente) {
        this.fonoCliente = fonoCliente;
    }

    
}