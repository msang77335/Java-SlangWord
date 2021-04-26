/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import file.FileAction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import object.ListSlangWord;
import javax.swing.JOptionPane;

/**
 *
 * @author Sang
 */
public class SearchSW {
    JFrame frame;
    JFrame menuFrame;
    JPanel JPTitle;
    JPanel JPContent;
    JPanel JPActions;
    JPanel Container;
    JPanel Result;
    JTextField KeyWord;
    ListSlangWord list;
    Boolean isSlangWord;
    
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
            String strActionCommand = ae.getActionCommand();
		if (strActionCommand.equals("SEARCH"))
		{
                    if(!KeyWord.getText().isEmpty()){
                        if(isSlangWord == true){
                            ArrayList<String> result = list.searchBySlangWord(KeyWord.getText());
                            if(result == null){
                                String mess = "SlangWord: " + KeyWord.getText() + "\nKhông có trong từ điển!!";
                                Object[] options = {"OK"};
                                int n = JOptionPane.showOptionDialog(frame,
                                mess,
                                "Search",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                null);
                            }
                            else{
                                String def = "";
                                for (int i = 0; i< result.size(); i++){
                                    if(i != result.size() - 1){
                                        def = def + result.get(i) + " | ";
                                    }
                                    else if (i == result.size() - 1){
                                        def = def + result.get(i);
                                    }
                                }
                                FileAction.writeHistory("historySlang.txt", KeyWord.getText(), def);
                                String mess = "SlangWord: " + KeyWord.getText() + "\nDefinition: " + def;
                                Object[] options = {"OK"};
                                int n = JOptionPane.showOptionDialog(frame,
                                mess,
                                "Search",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                null);
                            }
                        }
                        else{
                            String result = list.searchByDefinition(KeyWord.getText());
                            if(result == null){
                                String mess = "Definition: " + KeyWord.getText() + "\nKhông có trong từ điển!!";
                                Object[] options = {"OK"};
                                int n = JOptionPane.showOptionDialog(frame,
                                mess,
                                "Search",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                null);
                            }
                            else{
                                FileAction.writeHistory("historyDef.txt", KeyWord.getText(), result);
                                String mess = "Definition: " + KeyWord.getText() + "\nSlangWord: " + result;
                                Object[] options = {"OK"};
                                int n = JOptionPane.showOptionDialog(frame,
                                mess,
                                "Search",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                null);
                            }
                        }
                        //ArrayList<String> result = list.searchBySlangWord(KeyWord.getText());
                        //frameResult = new JFrame();
                        //frameResult.setTitle("Tìm kiếm bằng Slang-word");
                        //JLabel question = new JLabel("SlangWord: " + KeyWord.getText());    
                        //String def = "";
                        //for (int i = 0; i< result.size(); i++){
                        //    if(i != result.size() - 1){
                        //        def = def + result.get(i) + " | ";
                        //    }
                        //    else if (i == result.size() - 1){
                        //        def = def + result.get(i);
                        //    }
                        // }
                        //JLabel defJLabel = new JLabel("Definition: " + def);
                        
                        //JPanel content = new JPanel();
                        //content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                        //content.add(question);
                        //content.add(defJLabel);
                                              
                        //JButton btnOK = new JButton("OK");
                        //JPanel Button = new JPanel();
                        //btnOK.setActionCommand("OK");
                        //btnOK.addActionListener(new BtnAction());
                        //Button.add(btnOK);
                        //Button.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
                        
                        //JPanel Content = new JPanel();
                        //Content.setLayout(new BorderLayout());
                        //Content.add(content, BorderLayout.CENTER);
                        //Content.add(Button, BorderLayout.PAGE_END);
                        
                        //Content.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
                        //frameResult.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        //frameResult.addWindowListener(new WindowAdapter() {
                        //@Override
                        //    public void windowClosing(WindowEvent event) {
                        //        frame.setEnabled(true);
                        //        frameResult.dispose();
                        //    }
                        //});
                        //frameResult.setMinimumSize(new Dimension(250, 100));
                        //frameResult.setLayout(new BorderLayout());
                        //frameResult.add(Content, BorderLayout.CENTER);
                        //frameResult.pack();
                        //frameResult.setLocationRelativeTo(null);
                        //frameResult.setVisible(true);
                    }
                    else{
                        Object[] options = {"OK"};
                        String mess = "KeyWord Search Trống !!!";
                        int n = JOptionPane.showOptionDialog(frame,
                        mess,
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        null);
                    }
                }
                else if(strActionCommand.equals("EXT")){
                    menuFrame.setEnabled(true);
                    frame.dispose();
                }
                else if(strActionCommand.equals("OK")){
                    //frame.setEnabled(true);
                    //frameResult.dispose();
                    
                }
	}
    }
    
    public SearchSW(ListSlangWord list, JFrame menu, Boolean isSlangWord) {
        this.isSlangWord = isSlangWord;
        this.menuFrame = menu;
        this.list = list;
        JLabel LTitle = new JLabel();
        JLabel L1 = new JLabel();
        if(isSlangWord == true)
        {
            LTitle.setText("Tìm kiếm theo Slang-Word");
            L1.setText("Slang-Word:");
        }else{
            LTitle.setText("Tìm kiếm theo Definition");
            L1.setText("Definition:");
        }
        JPTitle = new JPanel();
        JPTitle.add(LTitle);
        
        KeyWord = new JTextField(16);
        JPContent = new JPanel();
        JPContent.setLayout(new FlowLayout());
       
        JPContent.add(L1);
        JPContent.add(KeyWord);
        
        JButton Btn1 = new JButton("Tìm");
        Btn1.setActionCommand("SEARCH");
        Btn1.addActionListener(new BtnAction());
        JButton Btn2 = new JButton("Thoát");
        Btn2.setActionCommand("EXT");
        Btn2.addActionListener(new BtnAction());
        JPActions = new JPanel();
        JPActions.setLayout(new FlowLayout());
        JPActions.add(Btn1);
        JPActions.add(Btn2);
        
    }
    
    public void setUpGUI(){
        frame = new JFrame();
        if(isSlangWord == true){
            frame.setTitle("Tìm kiếm bằng Slang-word");
        }
        else{
             frame.setTitle("Tìm kiếm bằng Definition");
        }
        Container = new JPanel();
        Container.setLayout(new BorderLayout());
        Container.add(JPTitle, BorderLayout.PAGE_START);
        Container.add(JPContent, BorderLayout.CENTER);
        Container.add(JPActions, BorderLayout.PAGE_END);
        Container.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                menuFrame.setEnabled(true);
                frame.dispose();
            }
        });
        frame.add(Container);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
