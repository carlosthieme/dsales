/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
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
import modelo.RedesSoc;
import modelo.RedesSocDAO;
import vista.fMaestros;
import vista.fVentas;


public class ControladorRedesSoc implements ActionListener{
    
    public static fMaestros fmae = new fMaestros();
    public static fVentas   fven = new fVentas();

    RedesSocDAO dao = new RedesSocDAO();
    RedesSoc r = new RedesSoc();
    Estados e = new Estados();
    EstadosDAO daoEsta;
    DefaultTableModel modelo = new DefaultTableModel();

    
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorRedesSoc(fMaestros m){
        this.daoEsta = new EstadosDAO();
        ControladorRedesSoc.fmae = m;
        ControladorRedesSoc.fmae.btnEditar.addActionListener(this);
        ControladorRedesSoc.fmae.btnGuardar.addActionListener(this);
        ControladorRedesSoc.fmae.btnAgregar.addActionListener(this);
   }
    
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorRedesSoc(fVentas f){
        this.daoEsta = new EstadosDAO();
        ControladorRedesSoc.fven = f;
        ControladorRedesSoc.fven.btnEditaVentas.addActionListener(this);
        ControladorRedesSoc.fven.btnGuardaVentas.addActionListener(this);
        ControladorRedesSoc.fven.btnGuardaPago.addActionListener(this);
        ControladorRedesSoc.fven.btnSalirVentas.addActionListener(this);
    }

    
    public void combo_listar_redes(JComboBox cbRedes){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbRedes.setModel(combito);
        List<RedesSoc>listar_redes = dao.listar_redes_sociales();
        for (int i = 0; i < listar_redes.size(); i++){
            combito.addElement(listar_redes.get(i).getNombreRed());
        }
     }
    
    public void listar_redes_grid(JTable grRedSocial){
        modelo = (DefaultTableModel)grRedSocial.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grRedSocial.setRowSorter(ordenada);    
        List<RedesSoc>listar_redes = dao.listar_redes_sociales();
        Object[]object=new Object[4];
        for (int i = 0; i < listar_redes.size(); i++){
            object[0]=listar_redes.get(i).getIdRed();
            object[1]=listar_redes.get(i).getNombreRed();
            object[2]=listar_redes.get(i).getIdEstado();
            object[3]=listar_redes.get(i).getDescripcionEstado();
            
            modelo.addRow(object);
        }
        fmae.grRedSocial.setModel(modelo);
        fmae.grRedSocial.requestFocus(); 
        fmae.grRedSocial.changeSelection(0,1,false, false); 
        
        fmae.grRedSocial.getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grRedSocial.getColumnModel().getColumn(2).setMinWidth(0);
        fmae.grRedSocial.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grRedSocial.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
    }    

    public void insertar_red_social(){
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadosRedes.getSelectedItem().toString());
        String nombreRed = fmae.txtNombreRedSocial.getText();
        
        r.setNombreRed(nombreRed);
        r.setIdEstado(idEsta);
        
        int a = dao.insertar_redes_dao(nombreRed, idEsta);
        if (a==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }
    
    public void actualizar_red_social(){
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadosRedes.getSelectedItem().toString());
        int idRed = Integer.parseInt(fmae.txtIdRed.getText());
        System.out.println(fmae.txtNombreRedSocial.getText());
        String nombreRed = fmae.txtNombreRedSocial.getText();
        
        r.setIdRed(idRed);
        r.setNombreRed(nombreRed);
        r.setIdEstado(idEsta);
        
        int a=dao.actualizar_redes_dao(idRed, nombreRed, idEsta);
        if (a==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actualizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
            if(fmae.jTabs.getSelectedIndex()==6){
                if(ae.getSource()==fmae.btnGuardar) {
                    if (fmae.txtNombreRedSocial.getText().equals("") && fmae.txtIdRed.getText().equals("")) {
                        JOptionPane.showMessageDialog(fmae, "Debe Ingresar un Nombre");
                    } else if (fmae.txtIdRed.getText().equals("") && fmae.txtNombreRedSocial.getText() != "") {
                        insertar_red_social();  
                        modelo.setNumRows(0);
                        listar_redes_grid(fmae.grRedSocial);
                        fmae.txtIdRed.setText("");
                        fmae.txtNombreRedSocial.setText("");
                        
                    }else if (fmae.txtIdRed.getText() !="" && fmae.txtNombreRedSocial.getText() != "") {
                        actualizar_red_social();
                        modelo.setNumRows(0);
                        listar_redes_grid(fmae.grRedSocial);
                        fmae.txtIdRed.setText("");
                        fmae.txtNombreRedSocial.setText("");
                    }
                } else if(ae.getSource()==fmae.btnEditar){
                    fmae.txtIdRed.setEnabled(true);
                    int seleccion=fmae.grRedSocial.getSelectedRow();
                    fmae.txtIdRed.setText(String.valueOf(fmae.grRedSocial.getValueAt(seleccion, 0)));
                    fmae.txtNombreRedSocial.setText(String.valueOf(fmae.grRedSocial.getValueAt(seleccion, 1)));
                } else if (ae.getSource()==fmae.btnAgregar) {
                    fmae.txtIdRed.setEnabled(true);
                    fmae.txtIdRed.setText("");
                    fmae.txtNombreRedSocial.setText("");
                }
               
            }
        }
}
