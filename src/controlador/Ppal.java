/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package controlador;

import vista.fMaestros;
import vista.fVentas;

public class Ppal {
    
    public static void main(String[] args){
        ControladorLogin.mostrar();
        
        fMaestros m= new fMaestros();
        ControladorArticulos  c = new ControladorArticulos(m);
        ControladorCategorias d = new ControladorCategorias(m);
        ControladorEstados    e = new ControladorEstados(m);
        ControladorRedesSoc  rs = new ControladorRedesSoc(m);
        ControladorBancos     b = new ControladorBancos(m);
        ControladorComunas   cc = new ControladorComunas(m);
        ControladorUsuarios  cu = new ControladorUsuarios(m);
        ControladorClientes  cl = new ControladorClientes(m);
        ControladorPacks     cp = new ControladorPacks(m);
        ControladorTipoPacks tp = new ControladorTipoPacks(m);
 
        c.listar_articulos(m.grArticulos);
        d.listar_categorias(m.grCategorias);
        rs.listar_redes_grid(m.grRedSocial);
        b.listar_bancos_grid(m.grBancos);
        e.listar_estados(m.grEstados);
        cc.listar_comunas_grid(m.grComunas);
        cu.listar_usuarios_grid(m.grUsuarios);
        cl.listar_clientes(m.grClientes);
        cp.listar_packs(m.grPacks);
        tp.listar_tipo_packs(m.grTipoPacks);
        
        
        
        e.combo_listar_estados(m.cbEstadoArticulo);
        e.combo_listar_estados(m.cbEstadosRedes);
        e.combo_listar_estados(m.cbEstadosBanco);
        e.combo_listar_estados(m.cbEstadoUsuarios);
        e.combo_listar_estados(m.cbEstadoCliente);
        e.combo_listar_estados(m.cbEstadoPack);
        e.combo_listar_estados(m.cbEstadoTipoPack);

        d.combo_listar_categorias(m.cbCategoriaArticulo);
        tp.combo_listar_tipo_packs(m.cbSelTipoPacks);

        
        fVentas v = new fVentas();
        ControladorVentas   cv = new ControladorVentas(v);
        ControladorRedesSoc  r = new ControladorRedesSoc(v);
        ControladorEstados  ce = new ControladorEstados(v);
        ControladorBancos   cb = new ControladorBancos(v);
        ControladorComunas  ct = new ControladorComunas(v);
        ControladorPacks    ck = new ControladorPacks(v);

        cv.listar_pedidos_pendientes(v.grPedidosP);
        
        r.combo_listar_redes(v.cbRedes);
        ce.combo_listar_estados_ventas(v.cbEstados);
        cb.combo_listar_bancos_ventas(v.cbBancosVentas);
        ct.combo_listar_comunas_ventas(v.cbComunaVentas);
        ck.combo_listar_packs_ventas(v.cbPacksVentas);
        ce.combo_listar_estados(v.cbEstadoPedido);
        
        cv.block_windows_buttons();
        
        
    }
    
}
