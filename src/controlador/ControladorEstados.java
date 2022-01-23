/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Estados;
import modelo.EstadosDAO;
import vista.fMaestros;
import vista.fVentas;

public class ControladorEstados implements ActionListener{
    EstadosDAO dao = new EstadosDAO();
    Estados a = new Estados();
    fMaestros fmae = new fMaestros();
    fVentas fven = new fVentas();
    DefaultTableModel modelo = new DefaultTableModel();
   
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorEstados(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }
    
    //fMaestro
    public void combo_listar_estados(JComboBox cbEstadoArticulo){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstadoArticulo.setModel(combito);
        List<Estados>listar_estados=dao.listar_estados_dao();
        for (int i = 0; i < listar_estados.size(); i++){
            combito.addElement(listar_estados.get(i).getDescribeEstado());
        }
    }
    
    public void combo_listar_estados_clientes(JComboBox cbEstadoCliente){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstadoCliente.setModel(combito);
        List<Estados>listar_estados=dao.listar_estados_dao();
        for (int i = 0; i < listar_estados.size(); i++){
            combito.addElement(listar_estados.get(i).getDescribeEstado());
        }
    }

    public void combo_listar_estados_banco(JComboBox cbEstadoBancos){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstadoBancos.setModel(combito);
        List<Estados>listar_estados=dao.listar_estados_dao();
        for (int i = 0; i < listar_estados.size(); i++){
            combito.addElement(listar_estados.get(i).getDescribeEstado());
        }
    }
    
    public void combo_listar_estados_usuarios(JComboBox cbEstadoUsuarios){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstadoUsuarios.setModel(combito);
        List<Estados>listar_estados=dao.listar_estados_dao();
        for (int i = 0; i < listar_estados.size(); i++){
            combito.addElement(listar_estados.get(i).getDescribeEstado());
        }
    }
    
    
    public void insertar_estado(){
        String describeEstado=fmae.txtDescribeEstado.getText();
        a.setDescribeEstado(describeEstado);
        int r=dao.insertar_estado_dao(describeEstado);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }
    
    public void actualizar_estado(){
        int idEstado=Integer.parseInt(fmae.txtIdEstado.getText());
        String describeEstado=fmae.txtDescribeEstado.getText();
        a.setDescribeEstado(describeEstado);
        int r=dao.actualizar_estado_dao(idEstado, describeEstado);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actulizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }
    
    public void listar_estados(JTable grEstados){
        modelo = (DefaultTableModel)grEstados.getModel();
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        grEstados.setRowSorter(elQueOrdena);    
        List<Estados>listar_estados=dao.listar_estados_dao();
    
        Object[]object=new Object[2];
        for (int i = 0; i < listar_estados.size(); i++){
            object[0]=listar_estados.get(i).getIdEstado();
            object[1]=listar_estados.get(i).getDescribeEstado();
            modelo.addRow(object);
        }
        fmae.grEstados.setModel(modelo);
        fmae.grEstados.requestFocus(); 
        fmae.grEstados.changeSelection(0,1,false, false); 
       
    }    

 
    public ControladorEstados(fVentas m){
        this.fven = m;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
    }
    
    
    public void combo_listar_estados_ventas(JComboBox cbEstados){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstados.setModel(combito);
        List<Estados>listar_estados=dao.listar_estados_dao();
        for (int i = 0; i < listar_estados.size(); i++){
            combito.addElement(listar_estados.get(i).getDescribeEstado());
        }
    }


    
    @Override
    public void actionPerformed(ActionEvent ae) {
            if(fmae.jTabs.getSelectedIndex()==8){
                if(ae.getSource()==fmae.btnGuardar){
                    if (fmae.txtIdEstado.getText().equals("") && fmae.txtDescribeEstado.getText().equals("")) {
                        JOptionPane.showMessageDialog(fmae, "Debe Ingresar un valor");
                    } else if (fmae.txtIdEstado.getText().equals("") && fmae.txtDescribeEstado.getText() != "") {
                        insertar_estado();  
                        JOptionPane.showMessageDialog(fmae, "Valor Ingresado :" + fmae.txtDescribeEstado.getText());
                        modelo.setNumRows(0);
                        listar_estados(fmae.grEstados);
                        fmae.txtIdEstado.setText("");
                        fmae.txtDescribeEstado.setText("");

                     }else if (fmae.txtIdEstado.getText() !="" && fmae.txtDescribeEstado.getText() != "") {
                        actualizar_estado();
                        JOptionPane.showMessageDialog(fmae, "Valor Actualizado :" + fmae.txtDescribeEstado.getText());
                        modelo.setNumRows(0);
                        listar_estados(fmae.grEstados);
                        fmae.txtIdEstado.setText("");
                        fmae.txtDescribeEstado.setText("");
                
                    }
                } else if(ae.getSource()==fmae.btnEditar){
                    fmae.txtDescribeEstado.setEnabled(true);
                    int seleccion=fmae.grEstados.getSelectedRow();
                    fmae.txtIdEstado.setText(String.valueOf(fmae.grEstados.getValueAt(seleccion, 0)));
                    fmae.txtDescribeEstado.setText(String.valueOf(fmae.grEstados.getValueAt(seleccion, 1)));
                } else if (ae.getSource()==fmae.btnAgregar) {
                    fmae.txtDescribeEstado.setEnabled(true);
                    fmae.txtIdEstado.setText("");
                    fmae.txtDescribeEstado.setText("");
                }
            }
        }
    }

    

