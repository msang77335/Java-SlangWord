/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.Menu;
/**
 *
 * @author Sang
 */
public class SlangWord {

    /**
     * @param args the command line arguments
     */
    
    JFrame frame;    
    public void setUpGUI(){
        frame = new JFrame();
        frame.setTitle("Slang-Word");
       
        frame.setLayout(new BorderLayout());
        JPanel MenuJPanel = new Menu(frame).getMenu();
       
        frame.add(MenuJPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuJPanel.setPreferredSize(new Dimension(400, 300));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new SlangWord().setUpGUI();
    }
    
}
