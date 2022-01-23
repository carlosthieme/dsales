/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package modelo;


public class Packs {
    int idPack;
    String nombrePack;
    Float precioPack;
    int stockPack;
    int idTipoPack;
    String nombreTipoPack;
    int idEstado;
    String describeEstado;

    public Packs() {
    }

    public Packs(int idPack, String nombrePack, Float precioPack, int stockPack, int idTipoPack, String nombreTipoPack, int idEstado, String describeEstado) {
        this.idPack = idPack;
        this.nombrePack = nombrePack;
        this.precioPack = precioPack;
        this.stockPack = stockPack;
        this.idTipoPack = idTipoPack;
        this.nombreTipoPack = nombreTipoPack;
        this.idEstado = idEstado;
        this.describeEstado = describeEstado;
    }

    @Override
    public String toString() {
        return "Packs{" + "idPack=" + idPack + ", nombrePack=" + nombrePack + ", precioPack=" + precioPack + ", stockPack=" + stockPack + ", idTipoPack=" + idTipoPack + ", nombreTipoPack=" + nombreTipoPack + ", idEstado=" + idEstado + ", describeEstado=" + describeEstado + '}';
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

    public Float getPrecioPack() {
        return precioPack;
    }

    public void setPrecioPack(Float precioPack) {
        this.precioPack = precioPack;
    }

    public int getStockPack() {
        return stockPack;
    }

    public void setStockPack(int stockPack) {
        this.stockPack = stockPack;
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
