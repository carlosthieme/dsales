/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package modelo;


public class TipoPack {
    int idTipoPack;
    String nombreTipoPack;
    int idEstadoPack;
    String describeEstadoPack;

    public TipoPack() {
    }

    public TipoPack(int idTipoPack, String nombreTipoPack, int idEstadoPack, String describeEstadoPack) {
        this.idTipoPack = idTipoPack;
        this.nombreTipoPack = nombreTipoPack;
        this.idEstadoPack = idEstadoPack;
        this.describeEstadoPack = describeEstadoPack;
    }

    @Override
    public String toString() {
        return "tipo_pacls{" + "idTipoPack=" + idTipoPack + ", nombreTipoPack=" + nombreTipoPack + ", idEstadoPack=" + idEstadoPack + ", describeEstadoPack=" + describeEstadoPack + '}';
    }

    public int getIdTipoPack() {
        return idTipoPack;
    }

    public void setIdTipoPack(int idTipoPack) {
        this.idTipoPack = idTipoPack;
    }

    public String getNombreTipoPack() {
        return nombreTipoPack;
    }

    public void setNombreTipoPack(String nombreTipoPack) {
        this.nombreTipoPack = nombreTipoPack;
    }

    public int getIdEstadoPack() {
        return idEstadoPack;
    }

    public void setIdEstadoPack(int idEstadoPack) {
        this.idEstadoPack = idEstadoPack;
    }

    public String getDescribeEstadoPack() {
        return describeEstadoPack;
    }

    public void setDescribeEstadoPack(String describeEstadoPack) {
        this.describeEstadoPack = describeEstadoPack;
    }
    
    
    
}
