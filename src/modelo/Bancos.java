/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package modelo;

public class Bancos {
    
    int idBanco;
    String nombreBanco;
    int idEstado;
    String describeEstado;

    public Bancos() {
    }

    public Bancos(int idBanco, String nombreBanco, int idEstado, String describeEstado) {
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
        this.idEstado = idEstado;
        this.describeEstado = describeEstado;
    }

    @Override
    public String toString() {
        return "Bancos{" + "idBanco=" + idBanco + ", nombreBanco=" + nombreBanco + ", idEstado=" + idEstado + ", describeEstado=" + describeEstado + '}';
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

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescribeEstado() {
        return describeEstado;
    }

    public void setDescribeEstado(String describeEstado) {
        this.describeEstado = describeEstado;
    }
    
    
  }
