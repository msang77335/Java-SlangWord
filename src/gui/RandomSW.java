/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import object.ListSlangWord;

/**
 *
 * @author Sang
 */
public class RandomSW {
    ListSlangWord list;
    JFrame menuFrame;
    
    public RandomSW(JFrame menuFrame, ListSlangWord list) {
        this.menuFrame = menuFrame;
        this.list = list;
    }
    public void RandomOne()
    {
        String key = list.random(1).get(0);
        ArrayList<String> values = list.getList().get(key);
        String def = "";
        for(int i = 0; i < values.size(); i++){
            if(i != values.size() - 1){
                def = def + values.get(i) + " | ";
            }
            else if (i == values.size() - 1){
                def = def + values.get(i);
            }
        }
        Object[] options = {"OK"};
            String mess = "SlangWord: " + key + "\nDefinition: "+ def;
            int n = JOptionPane.showOptionDialog(menuFrame,
            mess,
            "Random SlangWord",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            null);
    }
    public void RandomQuestion()
    {
        String key = list.random(1).get(0);
        ArrayList<String> values = list.getList().get(key);
        String def = "";
        for(int i = 0; i < values.size(); i++){
            if(i != values.size() - 1){
                def = def + values.get(i) + " | ";
            }
            else if (i == values.size() - 1){
                def = def + values.get(i);
            }
        }
        Object[] options = {"OK"};
            String mess = "SlangWord: " + key + "\nDefinition: "+ def;
            int n = JOptionPane.showOptionDialog(menuFrame,
            mess,
            "Random SlangWord",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            null);
    }
}
