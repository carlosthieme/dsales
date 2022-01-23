/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author user
 */
public class Proveedores {
    
    private int id_proveedor;
    private String rut_prove;
    private String nombre_prove;
    private String dir_prove;
    private int comunas_id_comuna;
    private String fono_prove;
    private String correo_prove;
    private int es_activo_prove;

    public Proveedores(int id_proveedor, String nombre_prove) {
        this.id_proveedor = id_proveedor;
        this.nombre_prove = nombre_prove;
    }

    public Proveedores() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRut_prove() {
        return rut_prove;
    }

    public void setRut_prove(String rut_prove) {
        this.rut_prove = rut_prove;
    }

    public String getNombre_prove() {
        return nombre_prove;
    }

    public void setNombre_prove(String nombre_prove) {
        this.nombre_prove = nombre_prove;
    }

    public String getDir_prove() {
        return dir_prove;
    }

    public void setDir_prove(String dir_prove) {
        this.dir_prove = dir_prove;
    }

    public int getComunas_id_comuna() {
        return comunas_id_comuna;
    }

    public void setComunas_id_comuna(int comunas_id_comuna) {
        this.comunas_id_comuna = comunas_id_comuna;
    }

    public String getFono_prove() {
        return fono_prove;
    }

    public void setFono_prove(String fono_prove) {
        this.fono_prove = fono_prove;
    }

    public String getCorreo_prove() {
        return correo_prove;
    }

    public void setCorreo_prove(String correo_prove) {
        this.correo_prove = correo_prove;
    }

    public int getEs_activo_prove() {
        return es_activo_prove;
    }

    public void setEs_activo_prove(int es_activo_prove) {
        this.es_activo_prove = es_activo_prove;
    }
    
    
    @Override
    public String toString() {
        return nombre_prove;
    }
    
}
