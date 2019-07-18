/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import Views.frmEdit;
import Views.frmUtama;
import Views.frmTambah;
import Views.frmTentang;
import Views.frmUserMan;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dwi Candra Permana
 */
public class mdlUtama extends javax.swing.JFrame {
    private Modules.ConnectionBuilder cb = new Modules.ConnectionBuilder();
    private Modules.OutputSuara os = new Modules.OutputSuara();
    private DefaultTableModel mdl;
    private frmUtama frmMain;
    private frmTambah frmTambah;
    private frmTentang frmTentang;
    private frmUserMan frmUserMan;
    public String userLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
    
    public Component cmpMain() {
        return frmMain;
    }
    
    public void showMain(String username, String email) {
        frmMain = new frmUtama();
        frmMain.txtUsername.setText(username);
        frmMain.txtEmail.setText(email);
        frmMain.setVisible(true);
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.RefreshData();
        
    }
    
    public void showTambah() {
        frmTambah = new frmTambah();
        frmTambah.setVisible(true);
    }
    
    public void showTentang() {
        frmTentang = new frmTentang();
        frmTentang.setVisible(true);
    }
    
    public void showUserMan(String username) {
        frmUserMan = new frmUserMan();
        frmUserMan.RefreshDialog(username);
        frmUserMan.setVisible(true);
    }
}
