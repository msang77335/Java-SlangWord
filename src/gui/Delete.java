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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import object.ListSlangWord;
import sun.nio.cs.ext.GB18030;

/**
 *
 * @author Sang
 */
public class Delete {
    JFrame frame;  
    JPanel JPTitle;
    JPanel JPContent;
    JPanel JPActions;
    JPanel Container;
    JTextField TF1;
    ListSlangWord list;
    JFrame frameDetail;
    JFrame frameMenu;  
    ArrayList<JCheckBox> checks;
    
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
                else if(strActionCommand.equals("DEL")){
                    Object[] options = {"Đồng ý", "Hủy"};
                    String mess = "Bạn có chắc muốn xóa các Definition đã chọn";
                    int n = JOptionPane.showOptionDialog(frameDetail,
                    mess,
                    "Delete SlangWord",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null);
                    if(n == 0){
                        ArrayList<String> newDef = list.getList().get(TF1.getText());
                        for ( JCheckBox checkbox : checks ) {
                            if( checkbox.isSelected() )
                            {
                                newDef.remove(checkbox.getActionCommand());
                            }
                        }
                       
                        if(newDef.size() == 0){
                            list.removeMap(TF1.getText());
                        }
                        else{
                            list.addMap(TF1.getText(), newDef);
                        }
                        FileAction.write("data.txt", list);
                        Object[] option = {"OK"};
                        mess = "Xóa SlangWord thành công !!!";
                        JOptionPane.showOptionDialog(frame,
                        mess,
                        "Notification",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        option,
                        null);
                        frame.setEnabled(true);
                        frameDetail.dispose();
                    }
                }
                else if(strActionCommand.equals("EXT")){
                    frameMenu.setEnabled(true);
                    frame.dispose();
                }
                else if(strActionCommand.equals("CANCEL")){
                    frame.setEnabled(true);
                    frameDetail.dispose();
                }
	}
    }
    
    public void openFrameDetail(ArrayList<String> values) {
        frameDetail = new JFrame();
        frameDetail.setTitle("Xóa Slang-word");
        JLabel L1 = new JLabel("Chọn Definition cần xóa");
        JPanel JP = new JPanel();
        JP.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        JP.setLayout(new BorderLayout());
        JPanel JTitle = new JPanel();
        JTitle.add(L1);
        JP.add(JTitle, BorderLayout.PAGE_START);
        JPanel JP1 = new JPanel();
        JP1.setLayout(new BoxLayout(JP1, BoxLayout.X_AXIS));
        JP1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        checks = new ArrayList<>();
        for(int i = 0; i < values.size(); i++){
            JCheckBox chk = new JCheckBox(values.get(i));
            JP1.add(chk);
            chk.setActionCommand(values.get(i));
            checks.add(chk);
        }
        JP.add(JP1, BorderLayout.CENTER);
        JButton btn = new JButton("Xóa");
        btn.setActionCommand("DEL");
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
        frameDetail.pack();
        frameDetail.setLocationRelativeTo(null);
        frameDetail.setVisible(true);
       
    }
    
    public Delete(ListSlangWord list, JFrame frameMenu) {
        this.frameMenu = frameMenu;
        this.list = list;
        JLabel LTitle = new JLabel("Xóa Slang-Word");
        JPTitle = new JPanel();
        JPTitle.add(LTitle);
        
        JLabel L1 = new JLabel("Nhập SlangWord cần xóa: ");
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
        frame.setTitle("Xóa Slang-word");
        frame.setLayout(new BorderLayout());
        frame.add(Container, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
