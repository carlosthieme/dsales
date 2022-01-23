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
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import modelo.Bancos;
import modelo.BancosDAO;
import modelo.Comunas;
import modelo.ComunasDAO;
import modelo.EstadosDAO;
import modelo.Packs;
import modelo.PacksDAO;
import modelo.RedesSoc;
import modelo.RedesSocDAO;
import vista.fMaestros;
import vista.fVentas;

public class ControladorPacks implements ActionListener{

    private JTree tree;
    public static fMaestros fmae = new fMaestros();
    public static fVentas   fven = new fVentas();
    
    public static void mostrar() { fmae.setVisible(true);}
    public static void ocultar() { fmae.setVisible(false);}

    Packs             p = new Packs();
    PacksDAO    daoPack = new PacksDAO();
    EstadosDAO  daoEsta = new EstadosDAO();
    Comunas           c = new Comunas();
    ComunasDAO  daoComu = new ComunasDAO();
    Bancos            b = new Bancos();
    BancosDAO   daoBank = new BancosDAO();
    RedesSoc          r = new RedesSoc();
    RedesSocDAO  daoRed = new RedesSocDAO();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    @SuppressWarnings({"static-access", "LeakingThisInConstructor"})
    public ControladorPacks(fMaestros m){
        this.fmae = m;
        this.fmae.btnEditar.addActionListener(this);
        this.fmae.btnGuardar.addActionListener(this);
        this.fmae.btnAgregar.addActionListener(this);
    }

    @SuppressWarnings({"LeakingThisInConstructor", "static-access"})
    public ControladorPacks(fVentas f){
        this.fven = f;
        this.fven.btnEditaVentas.addActionListener(this);
        this.fven.btnGuardaVentas.addActionListener(this);
        this.fven.btnGuardaPago.addActionListener(this);
        this.fven.btnSalirVentas.addActionListener(this);
    }
    
    public void listar_packs(JTable grPacks){
        modelo = (DefaultTableModel)grPacks.getModel();
        TableRowSorter<TableModel> ordenada = new TableRowSorter<>(modelo);
        grPacks.setRowSorter(ordenada);
        List<Packs>listar_packs = daoPack.listar_packs_dao();
        Object[]object=new Object[8];
        for (int i = 0; i < listar_packs.size(); i++){
            object[0]=listar_packs.get(i).getIdPack();
            object[1]=listar_packs.get(i).getNombrePack();
            object[2]=listar_packs.get(i).getPrecioPack();
            object[3]=listar_packs.get(i).getStockPack();
            object[4]=listar_packs.get(i).getIdTipoPack();
            object[5]=listar_packs.get(i).getNombreTipoPack();
            object[6]=listar_packs.get(i).getIdEstado();
            object[7]=listar_packs.get(i).getDescribeEstado();
            
            modelo.addRow(object);
        }
        fmae.grPacks.setModel(modelo);
        fmae.grPacks.requestFocus(); 
        fmae.grPacks.changeSelection(0,1,false, false); 
    }
    
    public void combo_listar_packs_ventas(JComboBox cbPacksVentas){
        DefaultComboBoxModel combito = new DefaultComboBoxModel();
        combito.removeAllElements();
        cbPacksVentas.setModel(combito);
        List<Packs>listar_packs = daoPack.listar_packs_dao();
        for (int i = 0; i < listar_packs.size(); i++){
            combito.addElement(listar_packs.get(i).getNombrePack());
        }
    }
    
    
    public void arbol(){
        
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.add(tree);
         
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //this.setTitle("JTree Example");       
       // this.pack();
       // this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

    }
    
}
