/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package controlador;

import static controlador.ControladorPacks.fmae;
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
import modelo.EstadosDAO;
import modelo.Packs;
import modelo.PacksDAO;
import modelo.TipoPack;
import modelo.TipoPackDAO;
import vista.fMaestros;
import vista.fVentas;


public class ControladorTipoPacks implements ActionListener{
    public static fMaestros fmae = new fMaestros();
    public static fVentas   fven = new fVentas();

    Packs              p = new Packs();
    PacksDAO     daoPack = new PacksDAO();
    TipoPack           t = new TipoPack();
    TipoPackDAO  daoTipo = new TipoPackDAO();
    EstadosDAO   daoEsta = new EstadosDAO();

    DefaultTableModel modelo = new DefaultTableModel();
  
    
    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorTipoPacks(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }

    
    @SuppressWarnings({"static-access", "LeakingThisInConstructor"})
    public ControladorTipoPacks(fVentas f){
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
    }

    public void listar_tipo_packs(JTable grTipoPacks){
        modelo = (DefaultTableModel)grTipoPacks.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grTipoPacks.setRowSorter(ordenada);
        List<TipoPack>listar_tipopacks = daoTipo.listar_tipo_packs_dao();
        Object[]object=new Object[4];
        for (int i = 0; i < listar_tipopacks.size(); i++){
            object[0]=listar_tipopacks.get(i).getIdTipoPack();
            object[1]=listar_tipopacks.get(i).getNombreTipoPack();
            object[2]=listar_tipopacks.get(i).getIdEstadoPack();
            object[3]=listar_tipopacks.get(i).getDescribeEstadoPack();
            modelo.addRow(object);
        }
        fmae.grTipoPacks.setModel(modelo);
        fmae.grTipoPacks.requestFocus(); 
        fmae.grTipoPacks.changeSelection(0,1,false, false); 
    }
    
    public void combo_listar_tipo_packs(JComboBox cbSelTipoPacks){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbSelTipoPacks.setModel(combito);
        List<TipoPack>listar_tipopacks = daoTipo.listar_tipo_packs_dao();
        for (int i = 0; i < listar_tipopacks.size(); i++){
            combito.addElement(listar_tipopacks.get(i).getNombreTipoPack());
        }
    }
    

    public void insertar_tipo_packs(){
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoTipoPack.getSelectedItem().toString());
        String nombreTipoPack = fmae.txtNombreTipoPack.getText();
        
        t.setNombreTipoPack(nombreTipoPack);
        t.setIdEstadoPack(idEsta);
        
        int r = daoTipo.insertar_tipo_packs_dao(nombreTipoPack, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }
    
    public void actualizar_tipo_packs(){
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoTipoPack.getSelectedItem().toString());
        int idTipo = Integer.parseInt(fmae.txtIdTipoPack.getText());
        String nombreTipoPack = fmae.txtNombreTipoPack.getText();
        
        t.setIdTipoPack(idTipo);
        t.setNombreTipoPack(nombreTipoPack);
        t.setIdEstadoPack(idEsta);
        
        int r = daoTipo.actualizar_tipo_packs_dao(idTipo, nombreTipoPack, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actualizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(fmae.jTabs.getSelectedIndex()==9){
            System.out.println(fmae.jTabs.getSelectedIndex());
            if(ae.getSource()==fmae.btnGuardar) {
                if (fmae.txtNombreTipoPack.getText().equals("") && fmae.txtIdTipoPack.getText().equals("")) {
                    JOptionPane.showMessageDialog(fmae, "Debe Ingresar un Nombre");
                } else if (fmae.txtIdTipoPack.getText().equals("") && fmae.txtNombreTipoPack.getText() != "") {
                    insertar_tipo_packs();  
                    modelo.setNumRows(0);
                    listar_tipo_packs(fmae.grTipoPacks);
                    fmae.txtIdTipoPack.setText("");
                    fmae.txtNombreTipoPack.setText("");
                        
                }else if (fmae.txtIdTipoPack.getText() !="" && fmae.txtNombreTipoPack.getText() != "") {
                    actualizar_tipo_packs();
                    modelo.setNumRows(0);
                    listar_tipo_packs(fmae.grTipoPacks);
                    fmae.txtIdTipoPack.setText("");
                    fmae.txtNombreTipoPack.setText("");
                }
            } else if(ae.getSource()==fmae.btnEditar){
                fmae.txtIdTipoPack.setEnabled(true);
                int seleccion=fmae.grTipoPacks.getSelectedRow();
                fmae.txtIdTipoPack.setText(String.valueOf(fmae.grTipoPacks.getValueAt(seleccion, 0)));
                fmae.txtNombreTipoPack.setText(String.valueOf(fmae.grTipoPacks.getValueAt(seleccion, 1)));
            } else if (ae.getSource()==fmae.btnAgregar) {
                fmae.txtIdTipoPack.setEnabled(true);
                fmae.txtIdTipoPack.setText("");
                fmae.txtNombreTipoPack.setText("");
            }
        }
    }

    
}
