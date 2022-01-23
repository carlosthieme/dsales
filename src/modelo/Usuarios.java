/**
 * @author Carlos Thieme
 * @fecha   13/09/2021.-
 * 
 */
package modelo;


public class Usuarios {
    String idUsuario;
    String nombreUsuario;
    String claveUsuario;
    int    idEstadoUsuario;
    String estadoUsuario;

    public Usuarios() {
    }

    public Usuarios(String idUsuario, String nombreUsuario, String claveUsuario, int idEstadoUsuario, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.claveUsuario = claveUsuario;
        this.idEstadoUsuario = idEstadoUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", claveUsuario=" + claveUsuario + ", idEstadoUsuario=" + idEstadoUsuario + ", estadoUsuario=" + estadoUsuario + '}';
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public int getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(int idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    
    
}
