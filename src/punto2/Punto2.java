/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class Punto2 {
    
    public static Controller controller;
    private final GUI gui;
    
    public Punto2() {
        controller = new Controller();
        controller.testing();
        
        gui = new GUI();
    }
        
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { }
        
        Punto2 obj = new Punto2();
    }
}
