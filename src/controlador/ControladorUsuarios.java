/**
 * @author Carlos Thieme
 * fecha   13/09/2021
 * 
 */
package controlador;

import static controlador.ControladorBancos.fmae;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Estados;
import modelo.EstadosDAO;
import modelo.Usuarios;
import modelo.UsuariosDAO;
import vista.fMaestros;


public class ControladorUsuarios implements ActionListener{
    
    public static fMaestros fmae = new fMaestros();
    
    Usuarios      u = new Usuarios();
    UsuariosDAO dao;
    Estados       e = new Estados();
    EstadosDAO  daoEsta = new EstadosDAO();
    
    DefaultTableModel modelo = new DefaultTableModel();
       
    @SuppressWarnings({"static-access", "LeakingThisInConstructor"})
    public ControladorUsuarios(fMaestros m){
        this.dao = new UsuariosDAO();
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }

    public void listar_usuarios_grid(JTable grUsuarios){
        modelo = (DefaultTableModel)grUsuarios.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grUsuarios.setRowSorter(ordenada);    
        List<Usuarios>listar_usuarios = dao.listar_usuarios_dao();
        Object[]object=new Object[4];
        for (int i = 0; i < listar_usuarios.size(); i++){
            object[0]=listar_usuarios.get(i).getIdUsuario();
            object[1]=listar_usuarios.get(i).getNombreUsuario();
            object[2]=listar_usuarios.get(i).getIdEstadoUsuario();
            object[3]=listar_usuarios.get(i).getEstadoUsuario();
            modelo.addRow(object);
        }
        fmae.grUsuarios.setModel(modelo);
        fmae.grUsuarios.requestFocus(); 
        fmae.grUsuarios.changeSelection(0,1,false, false); 
    }    
    
    public void insertar_usuario(){
        String idUsuario     = fmae.txtIdUsuario.getText();
        String nombreUsuario = fmae.txtNombreUsuario.getText();
        String claveUsuario  = String.valueOf(fmae.txtClaveUsuario.getPassword());
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoUsuarios.getSelectedItem().toString());
        
        u.setIdUsuario(idUsuario);
        u.setNombreUsuario(nombreUsuario);
        u.setClaveUsuario(claveUsuario);
        u.setIdEstadoUsuario(idEsta);
        
        int r = dao.insertar_usuarios_dao(idUsuario, nombreUsuario, claveUsuario, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Ingresados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Insertar");
        }
    }

    public void actualizar_usuarios(){
        String idUsuario     = fmae.txtIdUsuario.getText();
        String nombreUsuario = fmae.txtNombreUsuario.getText();
        String claveUsuario  = String.valueOf(fmae.txtClaveUsuario.getPassword());
        int idEsta = daoEsta.busca_id_estado(fmae.cbEstadoUsuarios.getSelectedItem().toString());
        
        u.setIdUsuario(idUsuario);
        u.setNombreUsuario(nombreUsuario);
        u.setClaveUsuario(claveUsuario);
        u.setIdEstadoUsuario(idEsta);
        
        int r=dao.actualizar_usuarios_dao(idUsuario, nombreUsuario, claveUsuario, idEsta);
        if (r==1){
            JOptionPane.showMessageDialog(fmae, "Datos Actualizados");
        }else{
            JOptionPane.showMessageDialog(fmae, "Error al Actualizar");
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        int insertOK = 0;
        if(fmae.jTabs.getSelectedIndex()==10){
            if(ae.getSource()==fmae.btnAgregar) {
                insertOK = 1;
                fmae.txtIdUsuario.setEnabled(true);
                fmae.txtIdUsuario.setEditable(true);
                fmae.txtIdUsuario.setText("");
                fmae.txtNombreUsuario.setText("");
                fmae.txtClaveUsuario.setText("");

            }else if (ae.getSource()==fmae.btnEditar){
                insertOK = 0;
                fmae.txtIdUsuario.setEnabled(true);
                int seleccion=fmae.grUsuarios.getSelectedRow();
                fmae.txtIdUsuario.setText(String.valueOf(fmae.grUsuarios.getValueAt(seleccion, 0)));
                fmae.txtNombreUsuario.setText(String.valueOf(fmae.grUsuarios.getValueAt(seleccion, 1)));
                
            }else if(ae.getSource()==fmae.btnGuardar) {
                    if (fmae.txtIdUsuario.getText().equals("") && fmae.txtNombreUsuario.getText().equals("")) {
                        JOptionPane.showMessageDialog(fmae, "Debe Ingresar un valor");
                    
                    }else if (insertOK == 1 && fmae.txtIdUsuario.getText() !="" && fmae.txtNombreUsuario.getText() != "") {
                        insertar_usuario();  
                        modelo.setNumRows(0);
                        listar_usuarios_grid(fmae.grUsuarios);
                        fmae.txtIdUsuario.setText("");
                        fmae.txtIdUsuario.setEditable(false);
                        fmae.txtNombreUsuario.setText("");
                        fmae.txtClaveUsuario.setText("");
                        
                    }else if (insertOK == 0 && fmae.txtIdUsuario.getText() !="" && fmae.txtNombreUsuario.getText() != "") {
                        actualizar_usuarios();
                        modelo.setNumRows(0);
                        listar_usuarios_grid(fmae.grUsuarios);
                        fmae.txtIdUsuario.setText("");
                        fmae.txtNombreUsuario.setText("");
                        fmae.txtClaveUsuario.setText("");
                    }
            }
        }
    }
}
