/*******************************
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 *******************************/
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Categorias;
import modelo.CategoriasDAO;
import vista.fMaestros;
import vista.fVentas;

public class ControladorCategorias implements ActionListener{

    CategoriasDAO dao = new CategoriasDAO();
    Categorias a = new Categorias();
    fMaestros fmae = new fMaestros();
    fVentas fven = new fVentas();
    DefaultTableModel modelo = new DefaultTableModel();
       
    public ControladorCategorias(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
   }
    
    public ControladorCategorias (fVentas f){
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
    }
    
    
    public void combo_listar_categorias(JComboBox cbCategoriaArticulo){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbCategoriaArticulo.setModel(combito);
        List<Categorias>listar_categorias=dao.listarcat();
        for (int i = 0; i < listar_categorias.size(); i++){
            combito.addElement(listar_categorias.get(i).getNombreCategoria());
        }
     }
    
    public void listar_categorias(JTable grCategorias){
        modelo = (DefaultTableModel)grCategorias.getModel();
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modelo);
        grCategorias.setRowSorter(elQueOrdena);    
        List<Categorias>listar_categorias=dao.listarcat();
        Object[]object=new Object[2];
        for (int i = 0; i < listar_categorias.size(); i++){
            object[0]=listar_categorias.get(i).getIdCategoria();
            object[1]=listar_categorias.get(i).getNombreCategoria();
            modelo.addRow(object);
        }
        fmae.grCategorias.setModel(modelo);
        fmae.grCategorias.requestFocus(); 
        fmae.grCategorias.changeSelection(0,1,false, false); 
        
    }    
  
    public void insertar_categoria(){
        String nombreCategoria=fmae.txtNombreCategoria.getText();
        a.setNombreCategoria(nombreCategoria);
        int r=dao.insertar_categorias_dao(nombreCategoria);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }

    public void actualizar_categoria(){
        int idCategoria=Integer.parseInt(fmae.txtIdCategoria.getText());
        String nombreCategoria=fmae.txtNombreCategoria.getText();
        a.setNombreCategoria(nombreCategoria);
        int r=dao.actualizar_categorias_dao(idCategoria, nombreCategoria);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actulizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
            if(fmae.jTabs.getSelectedIndex()==7){
                if(ae.getSource()==fmae.btnGuardar) {
                    if (fmae.txtIdCategoria.getText().equals("") && fmae.txtNombreCategoria.getText().equals("")) {
                        JOptionPane.showMessageDialog(fmae, "Debe Ingresar un valor");
                    } else if (fmae.txtIdCategoria.getText().equals("") && fmae.txtNombreCategoria.getText() != "") {
                        insertar_categoria();  
                        JOptionPane.showMessageDialog(fmae, "Valor Ingresado :" + fmae.txtNombreCategoria.getText());
                        modelo.setNumRows(0);
                        listar_categorias(fmae.grCategorias);
                        fmae.txtIdCategoria.setText("");
                        fmae.txtNombreCategoria.setText("");
                    }else if (fmae.txtIdCategoria.getText() !="" && fmae.txtNombreCategoria.getText() != "") {
                        actualizar_categoria();
                        JOptionPane.showMessageDialog(fmae, "Valor Actualizado :" + fmae.txtNombreCategoria.getText());
                        modelo.setNumRows(0);
                        listar_categorias(fmae.grCategorias);
                        fmae.txtIdCategoria.setText("");
                        fmae.txtNombreCategoria.setText("");
                    }
                } else if(ae.getSource()==fmae.btnEditar){
                    fmae.txtNombreCategoria.setEnabled(true);
                    int seleccion=fmae.grCategorias.getSelectedRow();
                    fmae.txtIdCategoria.setText(String.valueOf(fmae.grCategorias.getValueAt(seleccion, 0)));
                    fmae.txtNombreCategoria.setText(String.valueOf(fmae.grCategorias.getValueAt(seleccion, 1)));
                } else if (ae.getSource()==fmae.btnAgregar) {
                    fmae.txtNombreCategoria.setEnabled(true);
                    fmae.txtIdCategoria.setText("");
                    fmae.txtNombreCategoria.setText("");
                }
               
            }
        }
    }

