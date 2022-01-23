/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
 * 
 *******************************/
package modelo;

import java.sql.Date;


public class Clientes {
    String rutCliente;
    String nombreCliente;
    String apellidoCliente;
    String dirCliente;
    String fonoCliente;
    String correoCliente;
    Date fechaNacimiento;
    int idEstadoCliente;
    String describeEstado;

    public Clientes() {
    }

    public Clientes(String rutCliente, String nombreCliente, String apellidoCliente, String dirCliente, String fonoCliente, String correoCliente, Date fechaNacimiento, int idEstadoCliente, String describeEstado) {
        this.rutCliente = rutCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dirCliente = dirCliente;
        this.fonoCliente = fonoCliente;
        this.correoCliente = correoCliente;
        this.fechaNacimiento = fechaNacimiento;
        this.idEstadoCliente = idEstadoCliente;
        this.describeEstado = describeEstado;
    }

    @Override
    public String toString() {
        return "Clientes{" + "rutCliente=" + rutCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", dirCliente=" + dirCliente + ", fonoCliente=" + fonoCliente + ", correoCliente=" + correoCliente + ", fechaNacimiento=" + fechaNacimiento + ", idEstadoCliente=" + idEstadoCliente + ", describeEstado=" + describeEstado + '}';
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    public String getFonoCliente() {
        return fonoCliente;
    }

    public void setFonoCliente(String fonoCliente) {
        this.fonoCliente = fonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(int idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getDescribeEstado() {
        return describeEstado;
    }

    public void setDescribeEstado(String describeEstado) {
        this.describeEstado = describeEstado;
    }
    
    
}
