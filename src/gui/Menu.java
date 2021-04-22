/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import object.ListSlangWord;

/**
 *
 * @author Sang
 */
public class Menu {
    ListSlangWord list;
    JPanel MenuPanel;
    ButtonGroup bg;
    JFrame frame;
    
    public JPanel getMenu() {
        return MenuPanel;
    }
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
             String strActionCommand = ae.getActionCommand();
		if (strActionCommand.equals("OK"))
		{
                    ChoseAction(bg.getSelection().getActionCommand());
                }
                else if(strActionCommand.equals("EXT")){
                    frame.dispose();
                }
	}
    }
    
    void ChoseAction(String ac){
        switch(Integer.parseInt(ac)){
           case 1:
                SearchSW searchSW = new SearchSW(list, frame, true);
                searchSW.setUpGUI();
                frame.setEnabled(false);
                break;
            case 2:
               SearchSW searchDef = new SearchSW(list, frame, false);
               searchDef.setUpGUI();
               frame.setEnabled(false);
               break;
            case 4:
               Add addSW = new Add(frame, list);
               addSW.setUpGUI();
               frame.setEnabled(false);
               break;
            case 8:
               RandomSW randomSW = new RandomSW(frame, list);
               randomSW.RandomOne();
               break;
        }
    }
    
    public Menu(JFrame frame, ListSlangWord data) {
        this.list = data;
        this.frame = frame;
        MenuPanel = new JPanel();
        JLabel L1 = new JLabel("MENU");
        JRadioButton radioBtn1 = new JRadioButton("1. Tìm kiếm theo slang-word.");
        radioBtn1.setActionCommand("1");
        JRadioButton radioBtn2 = new JRadioButton("2. Tìm kiếm theo definition.");
        radioBtn2.setActionCommand("2");
        JRadioButton radioBtn3 = new JRadioButton("3. Hiển thị history.");
        radioBtn3.setActionCommand("3");
        JRadioButton radioBtn4 = new JRadioButton("4. Thêm slang-word.");
        radioBtn4.setActionCommand("4");
        JRadioButton radioBtn5 = new JRadioButton("5. Chỉnh sữa một slang-word.");
        radioBtn5.setActionCommand("5");
        JRadioButton radioBtn6 = new JRadioButton("6. Xóa 1 slang-word.");
        radioBtn6.setActionCommand("6");
        JRadioButton radioBtn7 = new JRadioButton("7. Reset danh sách slang-word.");
        radioBtn7.setActionCommand("7");
        JRadioButton radioBtn8 = new JRadioButton("8. Random 1 slang-word.");
        radioBtn8.setActionCommand("8");
        JRadioButton radioBtn9 = new JRadioButton("9. Đố vui slang-word.");
        radioBtn9.setActionCommand("9");
        JRadioButton radioBtn10 = new JRadioButton("10. Đố vui definition.");
        radioBtn10.setActionCommand("10");
        JButton btnChose = new JButton("Chọn");
        btnChose.setActionCommand("OK");
        btnChose.addActionListener(new BtnAction());
        JButton btnExt = new JButton("Thoát");
        btnExt.setActionCommand("EXT");
        btnExt.addActionListener(new BtnAction());
       
        
        
 
        bg = new ButtonGroup();
        bg.add(radioBtn1);
        bg.add(radioBtn2);
        bg.add(radioBtn3);
        bg.add(radioBtn4);
        bg.add(radioBtn5);
        bg.add(radioBtn6);
        bg.add(radioBtn7);
        bg.add(radioBtn8);
        bg.add(radioBtn9);
        bg.add(radioBtn10);
        radioBtn1.setSelected(true);
        
        JPanel TitlePanel = new JPanel();
        TitlePanel.add(L1);
        
        JPanel ContentJPanel = new JPanel();
        ContentJPanel.add(radioBtn1);
        ContentJPanel.add(radioBtn2);
        ContentJPanel.add(radioBtn3);
        ContentJPanel.add(radioBtn4);
        ContentJPanel.add(radioBtn5);
        ContentJPanel.add(radioBtn6);
        ContentJPanel.add(radioBtn7);
        ContentJPanel.add(radioBtn8);
        ContentJPanel.add(radioBtn9);
        ContentJPanel.add(radioBtn10);
        ContentJPanel.setLayout(new BoxLayout(ContentJPanel, BoxLayout.Y_AXIS));
        
        JPanel ChoseJPanel = new JPanel();
        ChoseJPanel.add(btnChose);
        ChoseJPanel.add(btnExt);
        ChoseJPanel.setLayout(new FlowLayout());
        
        MenuPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        MenuPanel.setLayout(new BorderLayout());
        MenuPanel.add(TitlePanel, BorderLayout.PAGE_START);
        MenuPanel.add(ContentJPanel, BorderLayout.CENTER);
        MenuPanel.add(ChoseJPanel, BorderLayout.PAGE_END);
       
       
        MenuPanel.setVisible(true);
    }
}
