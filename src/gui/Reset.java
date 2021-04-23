/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import file.FileAction;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import object.ListSlangWord;

/**
 *
 * @author Sang
 */
public class Reset {
    ListSlangWord list;
    JFrame menuFrame;
    
    public Reset(JFrame menuFrame, ListSlangWord list) {
        this.menuFrame = menuFrame;
        this.list = list;
    }
    public void reset()
    {
        Object[] options = {"Đồng ý", "Hủy"};
            String mess = "Bạn có chắc muốn reset lại từ điển SlangWord";
            int n = JOptionPane.showOptionDialog(menuFrame,
            mess,
            "Reset SlangWord",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null);
        if(n == 0){
            Object[] optionsResule = {"Thoát"};
            String messResult = "Reset SlangWord thành công.";
            JOptionPane.showOptionDialog(menuFrame,
            messResult,
            "Reset SlangWord",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            optionsResule,
            null);
        }
    }
}
