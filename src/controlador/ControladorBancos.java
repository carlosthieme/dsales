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
import modelo.Bancos;
import modelo.BancosDAO;
import modelo.Estados;
import modelo.EstadosDAO;
import vista.fMaestros;
import vista.fVentas;

public class ControladorBancos implements ActionListener{
    
    public static fMaestros fmae = new fMaestros();
    public static fVentas   fven = new fVentas();
    
    BancosDAO dao = new BancosDAO();
    Bancos      b = new Bancos();
    Estados     e = new Estados();
    EstadosDAO daoEsta;
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorBancos(fMaestros m){
        this.daoEsta = new EstadosDAO();
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
   }
    
    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorBancos(fVentas f){
        this.daoEsta = new EstadosDAO();
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
    }
    
    public void combo_listar_bancos_maestros(JComboBox cbEstadosBancos){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbEstadosBancos.setModel(combito);
        List<Bancos>listar_bancos = dao.listar_bancos_dao();
        for (int i = 0; i < listar_bancos.size(); i++){
            combito.addElement(listar_bancos.get(i).getNombreBanco());
        }
    }
    
    public void combo_listar_bancos_ventas(JComboBox cbBancosVentas){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbBancosVentas.setModel(combito);
        List<Bancos>listar_bancos = dao.listar_bancos_dao();
        for (int i = 0; i < listar_bancos.size(); i++){
            combito.addElement(listar_bancos.get(i).getNombreBanco());
        }
    }
    
    public void listar_bancos_grid(JTable grBancos){
        modelo = (DefaultTableModel)grBancos.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grBancos.setRowSorter(ordenada);    
        List<Bancos>listar_bancos = dao.listar_bancos_dao();
        Object[]object=new Object[4];
        for (int i = 0; i < listar_bancos.size(); i++){
            object[0]=listar_bancos.get(i).getIdBanco();
            object[1]=listar_bancos.get(i).getNombreBanco();
            object[2]=listar_bancos.get(i).getIdEstado();
            object[3]=listar_bancos.get(i).getDescribeEstado();
            
            modelo.addRow(object);
        }
        fmae.grBancos.setModel(modelo);
        fmae.grBancos.requestFocus(); 
        fmae.grBancos.changeSelection(0,1,false, false); 
        
        fmae.grBancos.getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grBancos.getColumnModel().getColumn(2).setMinWidth(0);
        fmae.grBancos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        fmae.grBancos.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
    }    
    
    
    public void insertar_banco(){
        String nombreBanco = fmae.txtNombreBanco.getText();
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadosBanco.getSelectedItem().toString());
        
        b.setNombreBanco(nombreBanco);
        b.setIdEstado(idEsta);
        
        int r = dao.insertar_bancos_dao(nombreBanco, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }
    
    public void actualizar_banco(){
        int idBanco = Integer.parseInt(fmae.txtIdBanco.getText());
        int idEsta  = daoEsta.busca_id_estado(fmae.cbEstadosBanco.getSelectedItem().toString());
        String nombreBanco = fmae.txtNombreBanco.getText();
        
        b.setNombreBanco(nombreBanco);
        b.setIdBanco(idBanco);
        b.setIdEstado(idEsta);
        
        int r=dao.actualizar_bancos_dao(idBanco, nombreBanco, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actulizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(fmae.jTabs.getSelectedIndex()==5){
            if(ae.getSource()==fmae.btnGuardar) {
                if (fmae.txtNombreBanco.getText().equals("") && fmae.txtIdBanco.getText().equals("")) {
                    JOptionPane.showMessageDialog(fmae, "Debe Ingresar un Nombre");
                    
                } else if (fmae.txtIdBanco.getText().equals("") && fmae.txtNombreBanco.getText() != "") {
                    insertar_banco();  
                    modelo.setNumRows(0);
                    listar_bancos_grid(fmae.grBancos);
                    fmae.txtIdBanco.setText("");
                    fmae.txtNombreBanco.setText("");
                        
                }else if (fmae.txtIdBanco.getText() !="" && fmae.txtNombreBanco.getText() != "") {
                    actualizar_banco();
                    modelo.setNumRows(0);
                    listar_bancos_grid(fmae.grBancos);
                    fmae.txtIdBanco.setText("");
                    fmae.txtNombreBanco.setText("");
                }
            } else if(ae.getSource()==fmae.btnEditar){
                fmae.txtIdBanco.setEnabled(true);
                int seleccion=fmae.grBancos.getSelectedRow();
                fmae.txtIdBanco.setText(String.valueOf(fmae.grBancos.getValueAt(seleccion, 0)));
                fmae.txtNombreBanco.setText(String.valueOf(fmae.grBancos.getValueAt(seleccion, 1)));
                
            } else if (ae.getSource()==fmae.btnAgregar) {
                fmae.txtIdBanco.setEnabled(true);
                fmae.txtIdBanco.setText("");
                fmae.txtNombreBanco.setText("");
            }
        }
    }
}
