/**
 * @author Carlos Thieme
 * fecha   05/10/2021
 * 
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.UsuariosDAO;
import vista.*;


public class ControladorLogin {
    public static fLogin ventana = new fLogin();
    public static void mostrar() { ventana.setVisible(true);}
    public static void ocultar() { ventana.setVisible(false);}
    
    
    
    public static void evento_btn_ingreso(){
        String usuario = ventana.getTxtUsuario().getText();
        String clave = String.valueOf(ventana.getTxtClave().getPassword());

        UsuariosDAO dao = new UsuariosDAO();
        PreparedStatement ps;
        ResultSet rs;

        int userOk = dao.busca_usuarios_login(usuario, clave);
        if (userOk == 1){
            ventana.txtUsuario.setText("");
            ventana.txtClave.setText("");
            ocultar();
            ControladorMain.mostrar();
        }else{
            ventana.txtUsuario.setText("");
            ventana.txtClave.setText("");
            JOptionPane.showMessageDialog(null, "Usuario o Clave incorrecto", "Login Error", 2);
        }
    }
}
