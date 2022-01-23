package controlador;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Compras;
import modelo.ComprasDAO;
import vista.fCompras;


public class ControladorCompras implements ActionListener{
    public static fCompras fc = new fCompras();
    //public static void mostrar() { fc.setVisible(true); }
    //public static void ocultar() { fc.setVisible(false); }

    Compras com = new Compras();
    ComprasDAO DAO = new ComprasDAO();

    DefaultTableModel modeloArtiFact;
    DefaultTableModel modeloDetFact;
    DefaultTableModel modeloCompras;
    DefaultTableModel modeloDetCompra;
    Date fecha;
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MM-yyyy");
    
    
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorCompras(fCompras f) throws SQLException {
        
        ControladorCompras.fc=f;
        ControladorCompras.fc.btnGrabFactura.addActionListener(this);
        ControladorCompras.fc.btnBusqHistoricoFact.addActionListener(this);
            
    }
    
    public void actionPerformed(ActionEvent e) {
    
        if (e.getSource() == fc.btnGrabFactura && !fc.txtOrdenBusqRegFact.getText().isEmpty()) {
            
            com.setOrdenesIdOrden(Integer.parseInt(fc.txtOrdenBusqRegFact.getText()));
            com.setNumeroFactura(Integer.parseInt(fc.txtNumFact.getText()));
            com.setFechaCompra(new java.sql.Date(fc.pickFechaFactura.getDate().getTime()));
            fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            try {
                if (DAO.registarCompra(com)) {
                    
                    modeloDetFact = (DefaultTableModel) fc.grDetalleFact.getModel();
                    int filas = fc.grDetalleFact.getModel().getRowCount(); 
                    
                    for (int i = 0; i < filas; i++) {
                        fecha =  new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(modeloDetFact.getValueAt(i, 4).toString()).getTime());
                        try {

                            com.setArticulos_id_articulo(Integer.parseInt(modeloDetFact.getValueAt(i, 0).toString()));
                            com.setCant_art(Integer.parseInt(modeloDetFact.getValueAt(i, 2).toString()));
                            com.setPrecio_art(Integer.parseInt(modeloDetFact.getValueAt(i, 3).toString()));
                            com.setFechaVencimiento(fecha);
                            
                            DAO.registarDetCompras(com);

                        } catch (SQLException ex ) {
                            Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Se ha Ingresado la Factura");
                    limpiarPantalla();     
       
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Error al guardar");

                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if(e.getSource()== fc.btnBusqHistoricoFact){
            try {
                fc.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                listarCompras(fc.grFacturas);
                fc.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    public void limpiarPantalla(){
    
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
    
    
    public void listarCompras(JTable listCompras) throws SQLException {
        
        modeloCompras = (DefaultTableModel) listCompras.getModel();
        modeloCompras.getDataVector().removeAllElements();
        modeloCompras.fireTableDataChanged();

        List<Compras> lista;

        if (fc.cbBusqMes.getSelectedIndex() == 0) {
            lista = DAO.listarCompras(0, 0);
        } else {
            lista = DAO.listarCompras(fc.cbBusqMes.getSelectedIndex(), Integer.parseInt(fc.cbBusqYear.getSelectedItem().toString()));
        }

        Object[] object = new Object[7];
        int i;
        
        for (i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getOrdenesIdOrden();
            object[1] = lista.get(i).getProveedor();
            object[2] = lista.get(i).getNumeroFactura();
            object[3] = fechaFormato.format(lista.get(i).getFechaPedido());
            object[4] = fechaFormato.format(lista.get(i).getFechaCompra());
            object[5] = lista.get(i).getTotal();
            object[6] = lista.get(i).getIdCompra();

            modeloCompras.addRow(object);
        }

        listCompras.setModel(modeloCompras);

    }
    
    public void listarDetalleFactura(JTable listDetalleFact, int idCompra) throws SQLException {
        modeloDetCompra = (DefaultTableModel) listDetalleFact.getModel();
        modeloDetCompra .getDataVector().removeAllElements();
        modeloDetCompra .fireTableDataChanged();
        List<Compras> lista;

        lista = DAO.listarDetalleCompra(idCompra);

        Object[] object = new Object[4];
        int i;

        for (i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getNombreArticulo();
            object[1] = lista.get(i).getCant_art();
            object[2] = lista.get(i).getPrecio_art();
            object[3] = fechaFormato.format(lista.get(i).getFechaVencimiento());
           
            modeloDetCompra.addRow(object);
        }
        
        listDetalleFact.setModel(modeloDetCompra);
    }
       
}
