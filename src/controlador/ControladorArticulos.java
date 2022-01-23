/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package controlador;

import static controlador.ControladorVentas.ocultar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.ParseException;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.ArticuloDAO;
import modelo.Articulos;
import modelo.CategoriasDAO;
import modelo.EstadosDAO;
import vista.fMaestros;



public class ControladorArticulos implements ActionListener{
    public static fMaestros fmae = new fMaestros();
    public static void mostrar() { fmae.setVisible(true);}
    public static void ocultar() { fmae.setVisible(false);}
    
    ArticuloDAO dao = new ArticuloDAO();
    Articulos a = new Articulos();
    CategoriasDAO daoCat = new CategoriasDAO();
    EstadosDAO daoEsta = new EstadosDAO();
    DefaultTableModel modelo = new DefaultTableModel();
     
    
       
    public ControladorArticulos(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }
    
    public static void btnSalir() {
        ocultar();
        ControladorMain.mostrar();
    }
   
    
    public void listar_articulos(JTable grArticulos){
        modelo = (DefaultTableModel)grArticulos.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grArticulos.setRowSorter(ordenada);
        //ordenada.setRowFilter(RowFilter.regexFilter(filtroTabla, 1));
        List<Articulos>listar_articulos=dao.listar_articulos_dao();
        Object[]object=new Object[9];
        for (int i = 0; i < listar_articulos.size(); i++){
            object[0]=listar_articulos.get(i).getIdArticulo();
            object[1]=listar_articulos.get(i).getNombreArticulo();
            object[2]=listar_articulos.get(i).getIdCategoria();
            object[3]=listar_articulos.get(i).getNombreCategoria();
            object[4]=listar_articulos.get(i).getStockArticulo();
            object[5]=listar_articulos.get(i).getPrecioArticulo();
            object[6]=listar_articulos.get(i).getFechaVence();
            object[7]=listar_articulos.get(i).getIdEstado();
            object[8]=listar_articulos.get(i).getDescripcionEstado();
            
            modelo.addRow(object);
        }
        fmae.grArticulos.setModel(modelo);
        fmae.grArticulos.requestFocus(); 
        fmae.grArticulos.changeSelection(0,1,false, false); 
        
        fmae.grArticulos.getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grArticulos.getColumnModel().getColumn(2).setMinWidth(0);
        fmae.grArticulos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grArticulos.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

        fmae.grArticulos.getColumnModel().getColumn(7).setMaxWidth(0);
        fmae.grArticulos.getColumnModel().getColumn(7).setMinWidth(0);
        fmae.grArticulos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        fmae.grArticulos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        
    }    
    
    public void insertar_articulos() throws ParseException{
        int idCat; 
        int idEsta;
        
        java.util.Date laFecha = fmae.dpFechaVencimiento.getDate();
        java.sql.Date sqlDate = new java.sql.Date(laFecha.getTime());
        a.setNombreArticulo(fmae.txtNombreArticulo.getText());
        idCat = daoCat.busca_id_categoria(fmae.cbCategoriaArticulo.getSelectedItem().toString());
        a.setIdCategoria(idCat);
        a.setStockArticulo(Integer.parseInt(fmae.txtStockArticulo.getText()));
        a.setPrecioArticulo(Float.parseFloat(fmae.txtPrecioArticulo.getText()));
        a.setFechaVence(laFecha);
        idEsta = daoEsta.busca_id_estado(fmae.cbEstadoArticulo.getSelectedItem().toString());
        a.setIdEstado(idEsta);
        int r;
        r = dao.insertar_articulos_dao(fmae.txtNombreArticulo.getText(), Integer.parseInt(fmae.txtStockArticulo.getText()), sqlDate, idCat, Float.parseFloat(fmae.txtPrecioArticulo.getText()), idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }

    
    public void actualizar_articulos() throws ParseException{
        int idArti;
        int idCat; 
        int idEsta;
            
        java.util.Date laFecha = fmae.dpFechaVencimiento.getDate();
        java.sql.Date sqlDate = new java.sql.Date(laFecha.getTime());
        idArti = Integer.parseInt(fmae.txtIdArticulo.getText());
        a.setNombreArticulo(fmae.txtNombreArticulo.getText());
        idCat = daoCat.busca_id_categoria(fmae.cbCategoriaArticulo.getSelectedItem().toString());
        a.setIdCategoria(idCat);
        a.setStockArticulo(Integer.parseInt(fmae.txtStockArticulo.getText()));
        a.setPrecioArticulo(Float.parseFloat(fmae.txtPrecioArticulo.getText()));
        a.setFechaVence(laFecha);
        idEsta = daoEsta.busca_id_estado(fmae.cbEstadoArticulo.getSelectedItem().toString());
        a.setIdEstado(idEsta);
  
        int r;
        r = dao.actualizar_articulos_dao(idArti, fmae.txtNombreArticulo.getText(), Integer.parseInt(fmae.txtStockArticulo.getText()), sqlDate, idCat, Float.parseFloat(fmae.txtPrecioArticulo.getText()), idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actulizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }

    
    public void limpia_articulos(){
        fmae.txtNombreArticulo.setEnabled(true);
        fmae.txtIdArticulo.setText("");
        fmae.txtNombreArticulo.setText("");
        fmae.txtStockArticulo.setText("");
        fmae.txtPrecioArticulo.setText("");
        //fmae.cbCategoriaArticulo.removeAllItems();
        //fmae.cbEstadoArticulo.removeAllItems();
    }
        
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(fmae.jTabs.getSelectedIndex()==2){
            if(ae.getSource()==fmae.btnGuardar){
                if (fmae.txtIdArticulo.getText().equals("") && fmae.txtNombreArticulo.getText().equals("")) {
                    JOptionPane.showMessageDialog(fmae, "Debe Ingresar un valor");
                    } else if (fmae.txtIdArticulo.getText().equals("") && fmae.txtNombreArticulo.getText() != "") {
                        try {  
                            insertar_articulos();
                        } catch (ParseException ex) {
                            Logger.getLogger(ControladorArticulos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        modelo.setNumRows(0);
                        listar_articulos(fmae.grArticulos);
                        limpia_articulos();
                    } else if (fmae.txtIdArticulo.getText() !="" && fmae.txtDescribeEstado.getText() != "") {
                        try {
                            actualizar_articulos();
                        } catch (ParseException ex) {
                            Logger.getLogger(ControladorArticulos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        modelo.setNumRows(0);
                        listar_articulos(fmae.grArticulos);
                        limpia_articulos();
                    }
                } else if(ae.getSource()==fmae.btnEditar){
                    fmae.cbCategoriaArticulo.setEditable(true);
                    fmae.cbEstadoArticulo.setEditable(true);
                    fmae.txtNombreArticulo.setEnabled(true);

                    int seleccion=fmae.grArticulos.getSelectedRow();
                    fmae.txtIdArticulo.setText(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 0)));
                    fmae.txtNombreArticulo.setText(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 1)));
                    fmae.cbCategoriaArticulo.getEditor().setItem(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 3)));
                    fmae.txtStockArticulo.setText(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 4)));
                    fmae.txtPrecioArticulo.setText(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 5)));
                    fmae.cbEstadoArticulo.getEditor().setItem(String.valueOf(fmae.grArticulos.getValueAt(seleccion, 8)));
                    
                } else if (ae.getSource()==fmae.btnAgregar) {
                    fmae.txtNombreArticulo.setEnabled(true);
                    limpia_articulos();
                }
            }
        }
}
