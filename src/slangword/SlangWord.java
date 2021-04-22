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
import file.FileAction;
import java.util.ArrayList;
import object.ListSlangWord;
/**
 *
 * @author Sang
 */
public class SlangWord {

    /**
     * @param args the command line arguments
     */
    JFrame frame;    
    public void setUpGUI(ListSlangWord list){
        frame = new JFrame();
        frame.setTitle("Slang-Word");
       
        frame.setLayout(new BorderLayout());
        JPanel MenuJPanel = new Menu(frame, list).getMenu();
       
        frame.add(MenuJPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuJPanel.setPreferredSize(new Dimension(400, 330));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        ListSlangWord list = new ListSlangWord();
        FileAction.read("Slangword.txt", list);
        //ArrayList<String> values = list.searchBySlangWord("#1");
        //for (String value : values){
        //    System.out.print(value + "|");
        //}
        //System.out.print(list.searchByDefinition("Meaning"));
        new SlangWord().setUpGUI(list);
    }
    
}
