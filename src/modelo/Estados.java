/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package modelo;


public class Estados {
    int idEstado;
    String describeEstado;

    public Estados() {
    }

    public Estados(int idEstado, String describeEstado) {
        this.idEstado = idEstado;
        this.describeEstado = describeEstado;
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
