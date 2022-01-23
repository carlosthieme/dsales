/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Articulos;
import modelo.Estados;
import modelo.OrdenesCompra;
import modelo.OrdenesCompraDAO;
import modelo.Proveedores;
import vista.fCompras;

/**
 *
 * @author MRBS oct 2021 Madicap SOFT
 */
public class ControladorOrdenesCompra implements ActionListener{

    OrdenesCompra ordC = new OrdenesCompra();
    OrdenesCompraDAO DAO = new OrdenesCompraDAO(); 
    fCompras fc = new fCompras();
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    JComboBox cbProveedores;
    JComboBox cbEstados;
    DefaultTableModel modeloArticulos;
    DefaultTableModel modeloDetOrdenes;
    DefaultTableModel modeloOrdenes;
    
    
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorOrdenesCompra(fCompras f) throws SQLException {

        this.fc = f;
        this.fc.btnGenOrden.addActionListener(this);
        this.fc.btnAnularOrden.addActionListener(this);
        this.fc.txtIdProve.setVisible(false);
        this.fc.txtIdOrden.setVisible(false);
        this.fc.btnArticulos.addActionListener(this);
        this.fc.btnBusqOrdenFact.addActionListener(this);
        this.fc.btnLimpiarFact.addActionListener(this);
        this.fc.cbBusqEstadoOrden.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                modeloDetOrdenes = (DefaultTableModel) fc.grDetalleOrden.getModel();
                modeloDetOrdenes.setNumRows(0);
                buscarOrdenPorEstado();
            }
        });
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(fc.btnGenOrden) && fc.cbProveOrden.getSelectedIndex() > 0) {

            ordC.setProveedoresIdProveedor(Integer.parseInt(fc.txtIdProve.getText()));
            fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            try {
                if (DAO.registarOrden(ordC)) {
                    int filaDetalle = fc.grArticulosSolic.getModel().getRowCount();
                    DefaultTableModel datosFilaArtiSolic = (DefaultTableModel) fc.grArticulosSolic.getModel();
                    

                    for (int i = 0; i < filaDetalle; i++) {
                        ordC.setArticulos_id_artic(Integer.parseInt(datosFilaArtiSolic.getValueAt(i, 0).toString()));
                        ordC.setCantidad_artic(Integer.parseInt(datosFilaArtiSolic.getValueAt(i, 2).toString()));
                        DAO.registarDetOrdenes(ordC);
                    }

                    fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Se ha Generado Orden de Compra");
                    datosFilaArtiSolic.setNumRows(0);
                    fc.btnGenOrden.setEnabled(false);
                    fc.cbBusqEstadoOrden.setSelectedIndex(1);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorOrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            fc.cbProveOrden.setSelectedIndex(0);
        }

        if (e.getSource() == fc.btnArticulos) {
            fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            listarArticulos(fc.grArticulosStock);
            fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
        if (e.getSource() == fc.btnAnularOrden && Integer.parseInt(fc.txtIdOrden.getText()) > 0) {

            ordC.setIdOrdenCompra(Integer.parseInt(fc.txtIdOrden.getText()));
            if (JOptionPane.showOptionDialog(null, "Esta Operación es irreversible ¿Esta seguro de Anular la Orden " + fc.txtIdOrden.getText() + "?", "Confirmar Anulación de Orden", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI") == 0) {
                fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if (DAO.anularOrden(ordC)) {
                   fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Orden de Compra Anulada");
                    try {
                        fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        listarGrOrdenes(fc.grOrdenes, 3);
                        fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        fc.txtIdOrden.setText("0");
                        modeloDetOrdenes = (DefaultTableModel) fc.grDetalleOrden.getModel();
                        modeloDetOrdenes.setNumRows(0);
                        fc.btnAnularOrden.setEnabled(false);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorOrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Error al Actualizar");
                }
            }
        }
        
        if (e.getSource() == fc.btnBusqOrdenFact && !fc.txtOrdenBusqRegFact.getText().isEmpty()) {
            fc.txtOrdenBusqRegFact.setEnabled(false);
            fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            int id_orden = Integer.parseInt(fc.txtOrdenBusqRegFact.getText());
            ordC.setIdOrdenCompra(id_orden);
            try {
                if (!DAO.buscarOrdenCompra(ordC).isEmpty()) {
                    ArrayList<OrdenesCompra> datosOrdenCompra = DAO.buscarOrdenCompra(ordC);
                    fc.lblProveedor.setText(datosOrdenCompra.get(0).getProveedor());
                    listarDetalleOrden(fc.grDetalleArtiFact, id_orden);
                } else {
                    JOptionPane.showMessageDialog(null, "La Orden no fue encontrada o no esta pendiente por Facturar");
                    fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    limpiarFactura();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorOrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if (e.getSource() == fc.btnLimpiarFact) {
            limpiarFactura();
        }
        

    }
    
    public void limpiarFactura() {
        fc.txtOrdenBusqRegFact.setEnabled(true);
        fc.txtOrdenBusqRegFact.setText("");
        fc.lblProveedor.setText("");
        DefaultTableModel modeloOrdenesArtiF = (DefaultTableModel) fc.grDetalleArtiFact.getModel();
        modeloOrdenesArtiF.setNumRows(0);
        DefaultTableModel modeloOrdenesDetalleF = (DefaultTableModel) fc.grDetalleFact.getModel();
        modeloOrdenesDetalleF.setNumRows(0);
        fc.txtIVA.setText("");
        fc.txtSubTotal.setText("");
        fc.txtTotal.setText("");
    }

    public void listarGrOrdenes(JTable listOrdenes, int idEstado) throws SQLException {
        modeloOrdenes = (DefaultTableModel) listOrdenes.getModel();
        modeloOrdenes.getDataVector().removeAllElements();
        modeloOrdenes.fireTableDataChanged();
        List<OrdenesCompra> lista;

        lista = DAO.listarOrdenesCompra(idEstado);

        Object[] object = new Object[5];
        int i;

        for (i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdOrdenCompra();
            object[1] = lista.get(i).getProveedor();
            object[2] = fecha.format(lista.get(i).getFechaOrden());
            object[3] = lista.get(i).getEstado();
            object[4] = lista.get(i).getEstadosIdEstado();

            modeloOrdenes.addRow(object);
        }
        fc.grOrdenes.setModel(modeloOrdenes);

    }

    public void listarArticulos(JTable listArticulos) {

        modeloArticulos = (DefaultTableModel) listArticulos.getModel();
        modeloArticulos.getDataVector().removeAllElements();
        modeloArticulos.fireTableDataChanged();

        List<Articulos> lista = DAO.listarStockArticulos();
        Object[] object = new Object[3];
        int i;
        for (i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdArticulo();
            object[1] = lista.get(i).getNombreArticulo();
            object[2] = lista.get(i).getStockArticulo();
            modeloArticulos.addRow(object);
        }
        fc.grArticulosStock.setModel(modeloArticulos);
    }

    public void listarCbProveedores(JComboBox<Proveedores> cbProveedores) throws SQLException {
        DAO.listarCbProveedores(cbProveedores);
    }

    public void listarDetalleOrden(JTable listDetalleOrden, int id_orden) {
        fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        modeloDetOrdenes = (DefaultTableModel) listDetalleOrden.getModel();
        modeloDetOrdenes.getDataVector().removeAllElements();
        modeloDetOrdenes.fireTableDataChanged();

        List<OrdenesCompra> lista = DAO.listarDetalleOrden(id_orden);
        Object[] object = new Object[3];
        int i;
        for (i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getArticulos_id_artic();
            object[1] = lista.get(i).getNombreArticulo();
            object[2] = lista.get(i).getCantidad_artic();
            modeloDetOrdenes.addRow(object);
        }
        listDetalleOrden.setModel(modeloDetOrdenes);
        fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }
    
    public void listarCbEstado(JComboBox<Estados> cbEstados) throws SQLException {
        DAO.listarCbEstado(cbEstados);
    }

    public void buscarOrdenPorEstado() {

        int idEstado = fc.cbBusqEstadoOrden.getItemAt(fc.cbBusqEstadoOrden.getSelectedIndex()).getIdEstado();
        if (idEstado > 0) {
            try {
                fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                listarGrOrdenes(fc.grOrdenes, idEstado);
                fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorOrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarOrdenes() {
        try {
            fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            listarGrOrdenes(fc.grOrdenes, 0);
            fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } catch (SQLException ex) {
            Logger.getLogger(ControladorOrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   

}
