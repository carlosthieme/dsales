/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/

package modelo;


public class Comunas {
    private int idComuna;
    private String nombreComuna;

    public Comunas() {
    }

    public Comunas(int idComuna, String nombreComuna) {
        this.idComuna = idComuna;
        this.nombreComuna = nombreComuna;
    }

    @Override
    public String toString() {
        return "Comunas{" + "idComuna=" + idComuna + ", nombreComuna=" + nombreComuna + '}';
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }
 
    
}