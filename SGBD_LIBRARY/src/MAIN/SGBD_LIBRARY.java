
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MAIN;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin6
 */
public class SGBD_LIBRARY {
    public static void main(String[] args) {
        Menu menu=new Menu();
        try {
            menu.ChoixClasse();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SGBD_LIBRARY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

