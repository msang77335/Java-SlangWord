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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import object.ListSlangWord;

/**
 *
 * @author Sang
 */
public class Add {
    JFrame frame;  
    JFrame menuFrame;
    ListSlangWord list;
    JPanel JPTitle;
    JPanel JPContent;
    JPanel JPActions;
    JPanel Container;
    JTextField slang;
    JTextField def;
    
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
            String strActionCommand = ae.getActionCommand();
		if (strActionCommand.equals("OK"))
		{
                    if(slang.getText().isEmpty() || def.getText().isEmpty()){
                        Object[] options = {"OK"};
                        String mess = "SlangWord or Definition Trống !!!";
                        int n = JOptionPane.showOptionDialog(frame,
                        mess,
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        null);
                    }
                    else{
                        String newSlang = slang.getText();
                        String newDef = def.getText();
                        list.add(newSlang, newDef);
                        Object[] options = {"OK"};
                        String mess = "Thêm SlangWord Thành Công!!";
                        int n = JOptionPane.showOptionDialog(frame,
                        mess,
                        "Notification",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        null);
                        menuFrame.setEnabled(true);
                        frame.dispose();
                    }
                }
                else if(strActionCommand.equals("CANCEL")){
                    menuFrame.setEnabled(true);
                    frame.dispose();
                }
	}
    }
    
    public Add(JFrame menuFrame, ListSlangWord list) {
        this.list = list;
        this.menuFrame = menuFrame;
        JLabel LTitle = new JLabel("Thêm Slang-Word");
        JPTitle = new JPanel();
        JPTitle.add(LTitle);
        
        JLabel L1 = new JLabel("Slang-Word:");
        slang = new JTextField(16);
        JLabel L2 = new JLabel("Definition:");
        def = new JTextField(16);
        JPContent = new JPanel();
        JPContent.setLayout(new FlowLayout());
        JPContent.add(L1);
        JPContent.add(slang);
        JPContent.add(L2);
        JPContent.add(def);
        
        JButton Btn1 = new JButton("Thêm");
        Btn1.setActionCommand("OK");
        Btn1.addActionListener(new BtnAction());
        JButton Btn2 = new JButton("Hủy");
        Btn2.setActionCommand("CANCEL");
        Btn2.addActionListener(new BtnAction());
        JPActions = new JPanel();
        JPActions.setLayout(new FlowLayout());
        JPActions.add(Btn1);
        JPActions.add(Btn2);
        
    }
    
    public void setUpGUI(){
        frame = new JFrame();
        frame.setTitle("Thêm Slang-word");
        frame.setLayout(new BorderLayout());
        Container = new JPanel();
        Container.setLayout(new BorderLayout());
        Container.add(JPTitle, BorderLayout.PAGE_START);
        Container.add(JPContent, BorderLayout.CENTER);
        Container.add(JPActions, BorderLayout.PAGE_END);
        Container.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.add(Container, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                menuFrame.setEnabled(true);
                frame.dispose();
            }
        });
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
