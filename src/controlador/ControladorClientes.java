/*******************************
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 *******************************/
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.EstadosDAO;
import vista.fMaestros;
import vista.fVentas;


public class ControladorClientes implements ActionListener{
    
    public static int insertOK = 0;
    public static fVentas   fven = new fVentas();
    public static fMaestros fmae = new fMaestros();
    
    public static void mostrar() { fmae.setVisible(true);}
    public static void ocultar() { fmae.setVisible(false);}
    
    Clientes        c  = new Clientes();
    ClientesDAO    dao = new ClientesDAO();
    EstadosDAO daoEsta = new EstadosDAO();
    DefaultTableModel modelo = new DefaultTableModel();
     
    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorClientes(fMaestros m){
        ControladorClientes.fmae = m;
        ControladorClientes.fmae.btnEditar.addActionListener(this);
        ControladorClientes.fmae.btnGuardar.addActionListener(this);
        ControladorClientes.fmae.btnAgregar.addActionListener(this);
    }

    public void listar_clientes(JTable grClientes){
        modelo = (DefaultTableModel)grClientes.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grClientes.setRowSorter(ordenada);
        List<Clientes>listar_clientes = dao.listar_clientes_dao();
        Object[]object=new Object[9];
        for (int i = 0; i < listar_clientes.size(); i++){
            object[0]=listar_clientes.get(i).getRutCliente();
            object[1]=listar_clientes.get(i).getNombreCliente();
            object[2]=listar_clientes.get(i).getApellidoCliente();
            object[3]=listar_clientes.get(i).getDirCliente();
            object[4]=listar_clientes.get(i).getFonoCliente();
            object[5]=listar_clientes.get(i).getCorreoCliente();
            object[6]=listar_clientes.get(i).getFechaNacimiento();
            object[7]=listar_clientes.get(i).getIdEstadoCliente();
            object[8]=listar_clientes.get(i).getDescribeEstado();
            
            modelo.addRow(object);
        }
        fmae.grClientes.setModel(modelo);
        fmae.grClientes.requestFocus(); 
        fmae.grClientes.changeSelection(0,1,false, false); 
    }
    
    public void insertar_clientes() throws ParseException {
        //System.out.println("Entro x aca");
        java.util.Date laFecha = fmae.dpFechaNacimiento.getDate();
        java.sql.Date sqlDate = new java.sql.Date(laFecha.getTime());
        
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoCliente.getSelectedItem().toString());        
        
        c.setRutCliente(fmae.txtRut.getText());
        c.setNombreCliente(fmae.txtNombreCliente.getText());
        c.setApellidoCliente(fmae.txtApellidosCliente.getText());
        c.setDirCliente(fmae.txtDireccion.getText());
        c.setFonoCliente(fmae.txtFono.getText());
        c.setCorreoCliente(fmae.txtCorreo.getText());
        c.setFechaNacimiento(sqlDate);
        c.setIdEstadoCliente(idEsta);

        int r;
        r = dao.insertar_clientes_dao(fmae.txtRut.getText(), fmae.txtNombreCliente.getText(), fmae.txtApellidosCliente.getText(), fmae.txtDireccion.getText(), fmae.txtFono.getText(), fmae.txtCorreo.getText(), sqlDate, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }

    public void actualizar_clientes() throws ParseException{
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoArticulo.getSelectedItem().toString());
        java.util.Date laFecha = fmae.dpFechaNacimiento.getDate();
        java.sql.Date sqlDate = new java.sql.Date(laFecha.getTime());

        c.setRutCliente(fmae.txtRut.getText());
        c.setNombreCliente(fmae.txtNombreCliente.getText());
        c.setApellidoCliente(fmae.txtApellidosCliente.getText());
        c.setDirCliente(fmae.txtDireccion.getText());
        c.setFonoCliente(fmae.txtFono.getText());
        c.setCorreoCliente(fmae.txtCorreo.getText());
        c.setFechaNacimiento(sqlDate);
        c.setIdEstadoCliente(idEsta);
  
        int r;
        r = dao.actualizar_clientes_dao(fmae.txtRut.getText(), fmae.txtNombreCliente.getText(), fmae.txtApellidosCliente.getText(), fmae.txtDireccion.getText(), fmae.txtFono.getText(), fmae.txtCorreo.getText(), sqlDate, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actualizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(fmae.jTabs.getSelectedIndex()==0){
            System.out.println(fmae.jTabs.getSelectedIndex());
            //Presiona Botón Agregar
            if(ae.getSource()==fmae.btnAgregar) {
                insertOK = 1;
                fmae.txtRut.setEnabled(true);
                fmae.txtRut.setEditable(true);
                fmae.txtRut.setText("");
                fmae.txtNombreCliente.setText("");
                fmae.txtApellidosCliente.setText("");
                fmae.txtDireccion.setText("");
                fmae.txtFono.setText("");
                fmae.txtCorreo.setText("");
                
            //Presiona Botón Editar
            }else if (ae.getSource()==fmae.btnEditar){
                insertOK = 0;
                fmae.txtRut.setEnabled(true);
                int seleccion=fmae.grClientes.getSelectedRow();
                fmae.txtRut.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 0)));
                fmae.txtNombreCliente.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 1)));
                fmae.txtApellidosCliente.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 2)));
                fmae.txtDireccion.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 3)));
                fmae.txtFono.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 4)));
                fmae.txtCorreo.setText(String.valueOf(fmae.grClientes.getValueAt(seleccion, 5)));
                
            //Presiona Botón Guardar 
            }else if(ae.getSource()==fmae.btnGuardar) {
                if (fmae.txtRut.getText().equals("") && fmae.txtNombreCliente.getText().equals("")) {
                    JOptionPane.showMessageDialog(fmae, "Debe Ingresar un Valor");
                } else if (insertOK == 1 && fmae.txtRut.getText() !="" && fmae.txtNombreUsuario.getText() != "") {
                    try {
                        insertar_clientes();
                        modelo.setNumRows(0);
                        listar_clientes(fmae.grClientes);
                        fmae.txtRut.setEnabled(false);
                        fmae.txtRut.setEditable(false);
                        fmae.txtRut.setText("");
                        fmae.txtNombreCliente.setText("");
                        fmae.txtApellidosCliente.setText("");
                        fmae.txtDireccion.setText("");
                        fmae.txtFono.setText("");
                        fmae.txtCorreo.setText("");
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                }else if (insertOK == 0 && fmae.txtRut.getText() != "" && fmae.txtNombreCliente.getText() != "") {
                    try {
                        actualizar_clientes();
                        modelo.setNumRows(0);
                        listar_clientes(fmae.grClientes);
                        fmae.txtRut.setEnabled(true);
                        fmae.txtRut.setEditable(true);
                        fmae.txtRut.setText("");
                        fmae.txtNombreCliente.setText("");
                        fmae.txtApellidosCliente.setText("");
                        fmae.txtDireccion.setText("");
                        fmae.txtFono.setText("");
                        fmae.txtCorreo.setText("");
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
       }
    }
}
        

   