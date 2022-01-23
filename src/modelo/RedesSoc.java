/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package modelo;


public class RedesSoc {
    
    int idRed;
    String nombreRed;
    int idEstado;
    String descripcionEstado;

    public RedesSoc() {
    }

    public RedesSoc(int idRed, String nombreRed, int idEstado, String descripcionEstado) {
        this.idRed = idRed;
        this.nombreRed = nombreRed;
        this.idEstado = idEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdRed() {
        return idRed;
    }

    public void setIdRed(int idRed) {
        this.idRed = idRed;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    @Override
    public String toString() {
        return "RedesSoc{" + "idRed=" + idRed + ", nombreRed=" + nombreRed + ", idEstado=" + idEstado + ", descripcionEstado=" + descripcionEstado + '}';
    }
    
    

        
    
}
