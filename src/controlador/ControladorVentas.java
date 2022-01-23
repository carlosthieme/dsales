/*******************************
 * @author Carlos Thieme
 * fecha   01/10/2021
 * 
 *******************************/
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.CategoriasDAO;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.Estados;
import modelo.EstadosDAO;
import modelo.Ventas;
import modelo.VentasDAO;
import modelo.BancosDAO;
import modelo.ComunasDAO;
import modelo.PacksDAO;
import modelo.RedesSocDAO;
import vista.fVentas;


public class ControladorVentas  implements ActionListener{
    public static fVentas fven = new fVentas();
    public static void mostrar() { fven.setVisible(true); }
    public static void ocultar() { fven.setVisible(false); }
    
    
    Ventas  v = new Ventas();
    Estados  esta = new Estados();
    BancosDAO bdao = new BancosDAO();
    public static PacksDAO  pdao = new PacksDAO();
    VentasDAO  vdao = new VentasDAO();
    ComunasDAO cdao = new ComunasDAO();
    EstadosDAO daoEsta = new EstadosDAO();
    ClientesDAO daoCli = new ClientesDAO();
    RedesSocDAO rdao = new RedesSocDAO();
    CategoriasDAO daoCat = new CategoriasDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public static void btnSalir() {
        clean_window();
        ocultar();
        ControladorMain.mostrar();
    }
    
    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorVentas(fVentas f){
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
        this.fven.btnBuscaPedido.addActionListener(this);
        this.fven.btnBuscaRut.addActionListener(this);
        this.fven.btnNuevoPedido.addActionListener(this);
    }
    
    public static void clean_window(){
        fven.txtApellidosCliente.setText(null);
        fven.txtCodigoTransfer.setText(null);
        fven.txtCorreoCliente.setText(null);
        fven.txtCorreoDestino.setText(null);
        fven.txtDirCliente.setText(null);
        fven.txtDireccionDestino.setText(null);
        fven.txtFonoCliente.setText(null);
        fven.txtFonoDestino.setText(null);
        fven.txtHoraFin.setText(null);
        fven.txtHoraIni.setText(null);
        fven.txtNombreCliente.setText(null);
        fven.txtNombreDestino.setText(null);
        fven.txtNumPedido.setText(null);
        fven.txtRut.setText(null);
        fven.txtSaludo.setText(null);
        fven.txtTotal.setText(null);
        fven.cbBancosVentas.setSelectedIndex(0);
        fven.cbComunaVentas.setSelectedIndex(0);
        fven.cbEstados.setSelectedIndex(0);
        fven.cbEstadoPedido.setSelectedIndex(0);
        fven.cbPacksVentas.setSelectedIndex(0);
        fven.cbRedes.setSelectedIndex(0);
    }
    
    public void listar_pedidos_pendientes(JTable grPedidosP){
        modelo = (DefaultTableModel)grPedidosP.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<TableModel>(modelo);
        grPedidosP.setRowSorter(ordenada);    
        List<Ventas>listar_pedidos_pendientes=vdao.listar_pedidos_pendientes_dao();
    
        Object[]object=new Object[6];
        for (int i = 0; i < listar_pedidos_pendientes.size(); i++){
            object[0]=listar_pedidos_pendientes.get(i).getIdVenta();
            object[1]=listar_pedidos_pendientes.get(i).getFechaVenta();
            object[2]=listar_pedidos_pendientes.get(i).getNombreFull();
            object[3]=listar_pedidos_pendientes.get(i).getFonoCliente();
            object[4]=listar_pedidos_pendientes.get(i).getNombrePack();
            object[5]=listar_pedidos_pendientes.get(i).getPrecioVenta();
            modelo.addRow(object);
        }
        fven.grPedidosP.setModel(modelo);
        fven.grPedidosP.requestFocus(); 
        fven.grPedidosP.changeSelection(0,1,false, false); 
    }    

    public static void get_precio_pack(){
        int idPack = pdao.busca_id_pack(fven.cbPacksVentas.getSelectedItem().toString());
        float precioPack = pdao.busca_precio_pack(idPack);
        fven.txtTotal.setText(Float.toString(precioPack));
    }
    
    public void getUltimoNumeroPedido(){
        int idPedido = vdao.getLastNumeroPedido();
        idPedido = idPedido + 1;
        fven.txtNumPedido.setText(String.valueOf(idPedido));
        fven.btnGuardaPago.setEnabled(false);
    }
    
    public void block_windows_buttons(){
        fven.btnEditaVentas.setEnabled(false);
        fven.btnGuardaPago.setEnabled(false);
        fven.btnGuardaVentas.setEnabled(false);
    }
    
    public void release_windows_buttons(){
        fven.btnEditaVentas.setEnabled(true);
        fven.btnGuardaPago.setEnabled(true);
        fven.btnGuardaVentas.setEnabled(true);
    }
    
    public void fill_datos_pedido() {
        int idPedido = Integer.parseInt(fven.txtNumPedido.getText());
        ArrayList<Ventas>datosVentas = vdao.retorna_datos_pedido_cliente(idPedido);
        if (datosVentas.size() > 0){
            for (int i = 0; i < datosVentas.size(); i++) {
                try {
                    fven.cbEstadoPedido.getModel().setSelectedItem(datosVentas.get(i).getEstadoPedido());
                    fven.txtRut.setText(datosVentas.get(i).getRutCliente());
                    fven.dpFechaPedido.setDate(datosVentas.get(i).getFechaVenta());
                    if (fven.txtRut.getText() != "" && fven.txtNumPedido.getText() != "") {
                        fill_datos_cliente();
                    }
                    /*******************Inicio  Valores del Destinatario *******************************/
                    fven.txtNombreDestino.setText(datosVentas.get(i).getNombreDestino());
                    fven.txtDireccionDestino.setText(datosVentas.get(i).getDirDestino());
                    fven.cbComunaVentas.getModel().setSelectedItem(datosVentas.get(i).getNombreComunaDest());
                    fven.txtCorreoDestino.setText(datosVentas.get(i).getCorreoDestino());
                    fven.txtFonoDestino.setText(datosVentas.get(i).getFonoDestino());
                    fven.txtSaludo.setText(datosVentas.get(i).getMensajeDest());
                    fven.txtHoraIni.setText(datosVentas.get(i).getHoraIni());
                    fven.txtHoraFin.setText(datosVentas.get(i).getHoraFin());
                    /*******************Fin  Valores del Destinatario *******************************/
                
                    fven.cbRedes.getModel().setSelectedItem(datosVentas.get(i).getNombreRed());
                    fven.cbPacksVentas.getModel().setSelectedItem(datosVentas.get(i).getNombrePack());
                    fven.dpFechaEntrega.setDate(datosVentas.get(i).getFechaDespIni());
                    fven.txtTotal.setText(String.valueOf(datosVentas.get(i).getPrecioVenta()));
                    fven.dpFechaPago.setDate(datosVentas.get(i).getFechaPago());
                    fven.cbBancosVentas.getModel().setSelectedItem(datosVentas.get(i).getNombreBanco());
                    fven.txtCodigoTransfer.setText(datosVentas.get(i).getIdTransfer());
                    block_windows_buttons();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            clean_window();
            JOptionPane.showMessageDialog(null, "No hay registro de un pedido con este número");
        }
    }
    
    public void fill_datos_cliente(){
        String rutCliente = fven.txtRut.getText();
        ArrayList<Clientes>datosCliente = daoCli.retorna_cliente_seleccionado(rutCliente);
        System.out.println(datosCliente);
        if(datosCliente == null || datosCliente.size() == 0){
            clean_window();
            JOptionPane.showMessageDialog(null, "No existe registro para este RUT");
        }else{
            for (int i = 0; i < datosCliente.size(); i++) {
                try {
                    fven.txtNombreCliente.setText(datosCliente.get(i).getNombreCliente());
                    fven.txtApellidosCliente.setText(datosCliente.get(i).getApellidoCliente());
                    fven.txtDirCliente.setText(datosCliente.get(i).getDirCliente());
                    fven.txtFonoCliente.setText(datosCliente.get(i).getFonoCliente());
                    fven.txtCorreoCliente.setText(datosCliente.get(i).getCorreoCliente());
                    fven.dpFechaNacimiento.setDate(datosCliente.get(i).getFechaNacimiento());
                    fven.cbEstados.getModel().setSelectedItem(datosCliente.get(i).getDescribeEstado());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
       
    public void guarda_datos_pago(){
        java.util.Date laFecha = fven.dpFechaPago.getDate();
        java.sql.Date sqlDate = new java.sql.Date(laFecha.getTime());
        int idPedido = Integer.parseInt(fven.txtNumPedido.getText());
                
        v.setFechaPago(sqlDate);
        int idBanco = bdao.busca_id_banco(fven.cbBancosVentas.getSelectedItem().toString());
        v.setIdBanco(idBanco);
        v.setIdTransfer(fven.txtCodigoTransfer.getText());
        int r;
        r = vdao.actualiza_datos_pago(4, idBanco, sqlDate, fven.txtCodigoTransfer.getText(), idPedido);
        if (r==1){
            JOptionPane.showMessageDialog(fven, "Datos de pago actualizados");
            clean_window();
            fven.txtNumPedido.setText(String.valueOf(idPedido));
            fill_datos_pedido();
            modelo.setNumRows(0);
            listar_pedidos_pendientes(fven.grPedidosP);
            block_windows_buttons();
        }else{
            JOptionPane.showMessageDialog(fven, "Error al actualizar");
        }
    }
    
    public void actualiza_datos_venta()throws ParseException, PropertyVetoException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
     
        int idPedido = Integer.parseInt(fven.txtNumPedido.getText());
        int idRed  = rdao.busca_id_red_social(fven.cbRedes.getSelectedItem().toString());
        int idPack = pdao.busca_id_pack(fven.cbPacksVentas.getSelectedItem().toString());
        int idComu = cdao.busca_id_comuna(fven.cbComunaVentas.getSelectedItem().toString());
        int idBanco  = bdao.busca_id_banco(fven.cbBancosVentas.getSelectedItem().toString());
        int idEstado = daoEsta.busca_id_estado(fven.cbEstadoPedido.getSelectedItem().toString());

        java.util.Date laFecha = fven.dpFechaPedido.getDate();
        java.sql.Date sqlFPedido = new java.sql.Date(laFecha.getTime());
        
        v.setRutCliente(fven.txtRut.getText());
        v.setFechaVenta(sqlFPedido);
        v.setIdRedes(idRed);
        v.setIdPack(idPack);
        v.setPrecioVenta(Float.parseFloat(fven.txtTotal.getText()));
        v.setNombreDestino(fven.txtNombreDestino.getText());
        v.setApellidoDestino(fven.txtApellidosCliente.getText());
        v.setIdComunaDest(idComu);
        v.setCorreoDestino(fven.txtCorreoCliente.getText());
        v.setFonoDestino(fven.txtFonoDestino.getText());
        v.setMensajeDest(fven.txtSaludo.getText());
        
        laFecha = fven.dpFechaEntrega.getDate();
        java.sql.Date sqlFEntrega  = new java.sql.Date(laFecha.getTime());
        
        v.setFechaDespIni(sqlFEntrega);
        v.setHoraIni(fven.txtHoraIni.getText());
        v.setHoraFin(fven.txtHoraFin.getText());
        v.setIdEstado(idEstado);

        laFecha = fven.dpFechaPago.getDate();
        java.sql.Date sqlFPago = new java.sql.Date(laFecha.getTime());
        v.setFechaPago(sqlFPago);
        v.setIdBanco(idBanco);
        v.setIdTransfer(fven.txtCodigoTransfer.getText());

        int r;
        int z;
        r = vdao.actualiza_pedidos_dao(idPedido, fven.txtRut.getText(), sqlFPedido, idRed, idPack, Float.parseFloat(fven.txtTotal.getText()), idEstado, sqlFPago, idBanco,fven.txtCodigoTransfer.getText());
        if (r==1){
            z = vdao.actualiza_destinos_dao(idPedido, fven.txtNombreDestino.getText(), fven.txtDireccionDestino.getText(), idComu, fven.txtFonoDestino.getText(), fven.txtCorreoDestino.getText(), sqlFEntrega, fven.txtSaludo.getText(), 0, fven.txtHoraIni.getText(), fven.txtHoraFin.getText());
            if (z == 1){
                JOptionPane.showMessageDialog(fven, "Datos actualizados satisfactoriamente");
            }else{
               JOptionPane.showMessageDialog(fven, "Error al actualizar"); 
            }
        }else{
            JOptionPane.showMessageDialog(fven, "Error al actualizar");
        }
    }
    
    public void guarda_datos_venta() throws ParseException, PropertyVetoException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String fechaGenerica = "2000-01-01";

        int idPedido = Integer.parseInt(fven.txtNumPedido.getText());
        
        java.util.Date laFecha = fven.dpFechaPedido.getDate();
        java.sql.Date sqlFPedido = new java.sql.Date(laFecha.getTime());
        
        int idRed  = rdao.busca_id_red_social(fven.cbRedes.getSelectedItem().toString());
        int idPack = pdao.busca_id_pack(fven.cbPacksVentas.getSelectedItem().toString());
        int idComu = cdao.busca_id_comuna(fven.cbComunaVentas.getSelectedItem().toString());
        int idBanco  = bdao.busca_id_banco(fven.cbBancosVentas.getSelectedItem().toString());
        int idEstado = daoEsta.busca_id_estado(fven.cbEstadoPedido.getSelectedItem().toString());
        
        v.setRutCliente(fven.txtRut.getText());
        v.setFechaVenta(sqlFPedido);
        v.setIdRedes(idRed);
        v.setIdPack(idPack);
        v.setPrecioVenta(Float.parseFloat(fven.txtTotal.getText()));
        v.setNombreDestino(fven.txtNombreDestino.getText());
        v.setApellidoDestino(fven.txtApellidosCliente.getText());
        v.setIdComunaDest(idComu);
        v.setCorreoDestino(fven.txtCorreoCliente.getText());
        v.setFonoDestino(fven.txtFonoDestino.getText());
        v.setMensajeDest(fven.txtSaludo.getText());
        
        laFecha = fven.dpFechaEntrega.getDate();
        java.sql.Date sqlFEntrega  = new java.sql.Date(laFecha.getTime());
        v.setFechaDespIni(sqlFEntrega);
        v.setHoraIni(fven.txtHoraIni.getText());
        v.setHoraFin(fven.txtHoraFin.getText());
        v.setIdEstado(3);
        
        if (fven.txtCodigoTransfer.getText() == null || fven.txtCodigoTransfer.getText().equals("")){
            fven.dpFechaPago.setDate(formatter.parse(fechaGenerica));
            laFecha = fven.dpFechaPago.getDate();
            java.sql.Date sqlFPago = new java.sql.Date(laFecha.getTime());
            v.setFechaPago(sqlFPago);
            v.setIdBanco(0);
            v.setIdTransfer("");
        }else{
            v.setIdBanco(idBanco);
            laFecha = fven.dpFechaPago.getDate();
            java.sql.Date sqlFPago = new java.sql.Date(laFecha.getTime());
            v.setFechaPago(sqlFPago);
            v.setIdTransfer(fven.txtCodigoTransfer.getText());
        }

        laFecha = fven.dpFechaPago.getDate();
        java.sql.Date sqlFPago = new java.sql.Date(laFecha.getTime());
 
        int r; 
        int z;
        r = vdao.insertar_pedidos_dao(fven.txtRut.getText(), sqlFPedido, idRed, idPack, Float.parseFloat(fven.txtTotal.getText()),  idEstado, sqlFPago, idBanco, fven.txtCodigoTransfer.getText());
        if (r==1){
            z = vdao.insertar_destinos_dao(idPedido, fven.txtNombreDestino.getText(), fven.txtDireccionDestino.getText(), idComu, fven.txtFonoDestino.getText(), fven.txtCorreoDestino.getText(), sqlFEntrega, fven.txtSaludo.getText(), 0, fven.txtHoraIni.getText(), fven.txtHoraFin.getText());
            if (z == 1){
                JOptionPane.showMessageDialog(fven, "Datos Ingresados satisfactoriamente");
            }else{
                JOptionPane.showMessageDialog(fven, "Error al Insertar");
            }
        }else{
            JOptionPane.showMessageDialog(fven, "Error al Insertar");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==fven.btnBuscaPedido){
            System.out.println("Boton Buscar Pedido");
            if (fven.txtNumPedido.getText().equals("")){
               JOptionPane.showMessageDialog(null, "Debe ingresar un Valor");
            }else {
                block_windows_buttons();
                fill_datos_pedido();
                fven.btnEditaVentas.setEnabled(true);
            } 
        }else if (ae.getSource()==fven.btnNuevoPedido){
            clean_window();
            release_windows_buttons();
            fven.btnGuardaPago.setEnabled(false);
            fven.btnEditaVentas.setEnabled(false);
            getUltimoNumeroPedido();
            
        }else if (ae.getSource()==fven.btnBuscaRut){
           
            fill_datos_cliente();
        
        }else if(ae.getSource()==fven.btnGuardaPago){
            guarda_datos_pago();
            clean_window();

        }else if(ae.getSource()==fven.btnEditaVentas){
            System.out.println("Boton Editar Venta");
            release_windows_buttons();
            
        }else if(ae.getSource()==fven.btnGuardaVentas){
            int existePedido = vdao.check_pedido_existe(Integer.parseInt(fven.txtNumPedido.getText()));
            if (existePedido == 0) {
                try {
                    guarda_datos_venta();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    actualiza_datos_venta();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }else if(ae.getSource()==fven.btnSalirVentas){
            
        }
    }
} //fin
