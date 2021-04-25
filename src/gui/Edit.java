/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import file.FileAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import object.ListSlangWord;

/**
 *
 * @author Sang
 */
public class Edit {
    JFrame frame;  
    JPanel JPTitle;
    JPanel JPContent;
    JPanel JPActions;
    JPanel Container;
    JTextField TF1;
    ListSlangWord list;
    JFrame frameDetail;
    JFrame frameEdit;
    JFrame frameMenu;  
    ButtonGroup bg;
    JTextField newDef;
    String oldDef;
    
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
            String strActionCommand = ae.getActionCommand();
		if (strActionCommand.equals("OK"))
		{
                    if(TF1.getText().isEmpty()){
                        Object[] options = {"OK"};
                        String mess = "SlangWord Trống !!!";
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
                        HashMap<String, ArrayList<String>> hasList;
                        hasList = list.getList();
                        if(hasList.containsKey(TF1.getText())){
                            frame.setEnabled(false);
                            openFrameDetail(hasList.get(TF1.getText()));
                        }
                        else{
                            Object[] options = {"OK"};
                            String mess = "SlangWord: " + TF1.getText() + " không có trong từ điển !!!";
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
                }
                else if(strActionCommand.equals("CHOSE")){
                    frameDetail.setEnabled(false);
                    openFrameEdit(bg.getSelection().getActionCommand());
                }
                else if(strActionCommand.equals("APPLY")){
                    if(newDef.getText().isEmpty()){
                        Object[] options = {"OK"};
                        String mess = "Definition rỗng !!!";
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
                        Object[] options = {"Đồng ý", "Hủy"};
                    String mess = "Bạn có chắc muốn cập nhật Definition đã chọn";
                    int n = JOptionPane.showOptionDialog(frameEdit,
                    mess,
                    "Edit SlangWord",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null);
                    if(n == 0){
                        ArrayList<String> newDefs = list.getList().get(TF1.getText());
                        int index = newDefs.indexOf(oldDef);
                        newDefs.set(index, (newDef.getText()));
                        list.addMap(TF1.getText(), newDefs);
                        FileAction.write("data.txt", list);
                        Object[] option = {"OK"};
                        mess = "Sửa SlangWord thành công !!!";
                        JOptionPane.showOptionDialog(frame,
                        mess,
                        "Notification",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        option,
                        null);
                        UpdateFrameEdit(newDefs);
                        frameDetail.setEnabled(true);
                        frameEdit.dispose();
                        }
                    }
                
                }
                else if(strActionCommand.equals("EXT")){
                    frameMenu.setEnabled(true);
                    frame.dispose();
                }
                else if(strActionCommand.equals("CANCEL")){
                    frame.setEnabled(true);
                    frameDetail.dispose();
                }else if(strActionCommand.equals("CANCELEDIT")){
                    frameDetail.setEnabled(true);
                    frameEdit.dispose();
                }
	}
    }
    
    public void openFrameEdit(String value) {
        oldDef = value;
        JLabel title = new JLabel("Sửa Slang-Word");
        JPanel Top = new JPanel();
        Top.add(title);

        JLabel l1 = new JLabel("Chỉnh sửa Definition: ");
        newDef = new JTextField(16);
        newDef.setText(value);
        JPanel Center = new JPanel();
        Center.setLayout(new FlowLayout());
        Center.add(l1);
        Center.add(newDef);

        JButton btn1 = new JButton("Cập nhập");
        btn1.setActionCommand("APPLY");
        btn1.addActionListener(new BtnAction());
        JButton btn2 = new JButton("Hủy");
        btn2.setActionCommand("CANCELEDIT");
        btn2.addActionListener(new BtnAction());
        JPanel Bottom = new JPanel();
        Bottom.setLayout(new FlowLayout());
        Bottom.add(btn1);
        Bottom.add(btn2);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(Top, BorderLayout.PAGE_START);
        container.add(Center, BorderLayout.CENTER);
        container.add(Bottom, BorderLayout.PAGE_END);
        container.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        
        frameEdit = new JFrame();
        frameEdit.setTitle("Sửa Slang-word");
        frameEdit.setLayout(new BorderLayout());
        frameEdit.add(container, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameEdit.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                frameDetail.setEnabled(true);
                frameEdit.dispose();
            }
        });
        frameEdit.setResizable(false);
        frameEdit.pack();
        frameEdit.setLocationRelativeTo(null);
        frameEdit.setVisible(true);
    }
    
    public void UpdateFrameEdit(ArrayList<String> listDef){
        int i = 0;
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setText(listDef.get(i));
            i++;
            if(i == 0){
                button.setSelected(true);
            }
        }
    }
    
    public void openFrameDetail(ArrayList<String> values) {
        frameDetail = new JFrame();
        frameDetail.setTitle("Sửa Slang-word");
        JLabel L1 = new JLabel("Chọn Definition cần chỉnh sửa");
        JPanel JP = new JPanel();
        JP.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        JP.setLayout(new BorderLayout());
        JPanel JTitle = new JPanel();
        JTitle.add(L1);
        JP.add(JTitle, BorderLayout.PAGE_START);
        JPanel JP1 = new JPanel();
        JP1.setLayout(new BoxLayout(JP1, BoxLayout.X_AXIS));
        JP1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        bg = new ButtonGroup();
        for(int i = 0; i < values.size(); i++){
            JRadioButton radio = new JRadioButton(values.get(i));
            JP1.add(radio);
            radio.setActionCommand(values.get(i));
            bg.add(radio);
            if(i == 0){
                radio.setSelected(true);
            }
        }
        JP.add(JP1, BorderLayout.CENTER);
        JButton btn = new JButton("Chọn");
        btn.setActionCommand("CHOSE");
        btn.addActionListener(new BtnAction());
        JButton btn1 = new JButton("Hủy");
        btn1.setActionCommand("CANCEL");
        btn1.addActionListener(new BtnAction());
        JPanel JBtn = new JPanel();
        JBtn.add(btn);
        JBtn.add(btn1);
        JP.add(JBtn, BorderLayout.PAGE_END);
        frameDetail.setLayout(new BorderLayout());
        frameDetail.add(JP, BorderLayout.CENTER);
        frameDetail.setMinimumSize(new Dimension(250, 100));
        frameDetail.setResizable(false);
        frameDetail.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameDetail.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                frame.setEnabled(true);
                frameDetail.dispose();
            }
        });
        frameDetail.pack();
        frameDetail.setLocationRelativeTo(null);
        frameDetail.setVisible(true);
       
    }
    
    public Edit(ListSlangWord list, JFrame frameMenu) {
        this.frameMenu = frameMenu;
        this.list = list;
        JLabel LTitle = new JLabel("Sửa Slang-Word");
        JPTitle = new JPanel();
        JPTitle.add(LTitle);
        
        JLabel L1 = new JLabel("Nhập SlangWord cần sửa: ");
        TF1 = new JTextField(16);
        JPContent = new JPanel();
        JPContent.setLayout(new FlowLayout());
        JPContent.add(L1);
        JPContent.add(TF1);
        
        JButton Btn1 = new JButton("Tìm");
        Btn1.setActionCommand("OK");
        Btn1.addActionListener(new BtnAction());
        JButton Btn2 = new JButton("Thoát");
        Btn2.setActionCommand("EXT");
        Btn2.addActionListener(new BtnAction());
        JPActions = new JPanel();
        JPActions.setLayout(new FlowLayout());
        JPActions.add(Btn1);
        JPActions.add(Btn2);
        
        Container = new JPanel();
        Container.setLayout(new BorderLayout());
        Container.add(JPTitle, BorderLayout.PAGE_START);
        Container.add(JPContent, BorderLayout.CENTER);
        Container.add(JPActions, BorderLayout.PAGE_END);
        Container.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
    }
    
    public void setUpGUI(){
        frame = new JFrame();
        frame.setTitle("Sửa Slang-word");
        frame.setLayout(new BorderLayout());
        frame.add(Container, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                frameMenu.setEnabled(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
