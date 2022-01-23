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
import modelo.Comunas;
import modelo.ComunasDAO;
import modelo.Estados;
import modelo.EstadosDAO;
import vista.fMaestros;
import vista.fVentas;


public class ControladorComunas  implements ActionListener{
    
    public static fMaestros fmae = new fMaestros();
    public static fVentas   fven = new fVentas();
    
    Comunas      c = new Comunas();
    ComunasDAO dao = new ComunasDAO();

    DefaultTableModel modelo = new DefaultTableModel();
   
    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorComunas(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }

    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorComunas(fVentas f){
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
    }

    public void listar_comunas_grid(JTable grComunas){
        modelo = (DefaultTableModel)grComunas.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grComunas.setRowSorter(ordenada);    
        List<Comunas>listar_comunas = dao.listar_comunas_dao();
        Object[]object=new Object[2];
        for (int i = 0; i < listar_comunas.size(); i++){
            object[0]=listar_comunas.get(i).getIdComuna();
            object[1]=listar_comunas.get(i).getNombreComuna();
            modelo.addRow(object);
        }
        fmae.grComunas.setModel(modelo);
        fmae.grComunas.requestFocus(); 
        fmae.grComunas.changeSelection(0,1,false, false); 
       
    }    
     
    
   public void combo_listar_comunas_ventas(JComboBox cbComunasVentas){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbComunasVentas.setModel(combito);
        List<Comunas>listar_comunas = dao.listar_comunas_dao();
        for (int i = 0; i < listar_comunas.size(); i++){
            combito.addElement(listar_comunas.get(i).getNombreComuna());
        }
    }
  
    
    public void insertar_comuna(){
        String nombreComuna = fmae.txtNombreComuna.getText();
        
        c.setNombreComuna(nombreComuna);
        
        int r = dao.insertar_comunas_dao(nombreComuna);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }
    
    public void actualizar_comunas(){
        int idComuna = Integer.parseInt(fmae.txtIdComuna.getText());
        String nombreComuna = fmae.txtNombreComuna.getText();
        
        c.setNombreComuna(nombreComuna);
        
        int r = dao.actualizar_comunas_dao(idComuna, nombreComuna);
        
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actulizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }
    
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(fmae.jTabs.getSelectedIndex()==4){
            if(ae.getSource()==fmae.btnGuardar) {
                if (fmae.txtNombreComuna.getText().equals("") && fmae.txtIdComuna.getText().equals("")) {
                    JOptionPane.showMessageDialog(fmae, "Debe Ingresar un Nombre");
                    
                }else if (fmae.txtIdComuna.getText().equals("") && fmae.txtNombreComuna.getText() != "") {
                    insertar_comuna();  
                    modelo.setNumRows(0);
                    listar_comunas_grid(fmae.grComunas);
                    fmae.txtIdComuna.setText("");
                    fmae.txtNombreComuna.setText("");
                        
                }else if (fmae.txtIdComuna.getText() !="" && fmae.txtNombreComuna.getText() != "") {
                    actualizar_comunas();
                    modelo.setNumRows(0);
                    listar_comunas_grid(fmae.grComunas);
                    fmae.txtIdComuna.setText("");
                    fmae.txtNombreComuna.setText("");
                }
            } else if(ae.getSource()==fmae.btnEditar){
                fmae.txtIdComuna.setEnabled(true);
                int seleccion=fmae.grComunas.getSelectedRow();
                fmae.txtIdComuna.setText(String.valueOf(fmae.grComunas.getValueAt(seleccion, 0)));
                fmae.txtNombreComuna.setText(String.valueOf(fmae.grComunas.getValueAt(seleccion, 1)));
                
            } else if (ae.getSource()==fmae.btnAgregar) {
                fmae.txtIdComuna.setEnabled(true);
                fmae.txtIdComuna.setText("");
                fmae.txtNombreComuna.setText("");
            }
        }
    }
  
}