/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import object.ListSlangWord;
import java.util.Random;

/**
 *
 * @author Sang
 */
public class FunnyQuestion {
    JFrame frame;  
    ListSlangWord list;
    JPanel JPTitle;
    JPanel JPContent;
    JPanel JPActions;
    JPanel JPActions2;
    JLabel Question;
    JPanel Container;
    ButtonGroup bg;
    JFrame PRFrame;
    ArrayList<String> arr;
    JRadioButton radioBtn1;
    JRadioButton radioBtn2;
    JRadioButton radioBtn3;
    JRadioButton radioBtn4;
    Boolean isSlangWord;
    
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
            String strActionCommand = ae.getActionCommand();
		if (strActionCommand.equals("OK"))
		{
                    if(bg.getSelection().getActionCommand().equals("0")){
                        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();      
                            if (button.isSelected()) {
                                button.setForeground(Color.GREEN);
                            }
                        }
                    }
                    else{
                        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();
                            if (button.isSelected()) {
                                button.setForeground(Color.RED);
                            }
                             if (button.getActionCommand().equals("0")) {
                                button.setForeground(Color.GREEN);
                            }
                        }
                    }
                    JPActions.setVisible(false);
                    frame.add(JPActions2, BorderLayout.PAGE_END);
                    JPActions2.setVisible(true);
                }
                else if (strActionCommand.equals("CONTINUE"))
		{
                    JPActions2.setVisible(false);
                    frame.add(JPActions, BorderLayout.PAGE_END);
                    JPActions.setVisible(true);  
                    random();
                }
                else if(strActionCommand.equals("EXT")){
                    PRFrame.setEnabled(true);
                    frame.dispose();
                }
	}
    }

    public FunnyQuestion(JFrame fbFrame, ListSlangWord list, Boolean isSlangWord) {
        this.isSlangWord = isSlangWord;
        this.list = list;
        this.PRFrame = fbFrame;
        randomQuestion();
    }
    
    public void random(){
        arr = list.random(4);
        HashMap<String, ArrayList<String>> hasList;
        hasList = list.getList();
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        randomList.add(0);
        randomList.add(1);
        randomList.add(2);
        randomList.add(3);
        Collections.shuffle(randomList);
        
        //random
        if(isSlangWord){
            Question.setText("Definition của slang-word: " + arr.get(0));
        
            Random rand = new Random();
            ArrayList<String> value = hasList.get(arr.get(randomList.get(0)));
            radioBtn1.setText("A. " + value.get(rand.nextInt(value.size())));
            radioBtn1.setForeground(null);
            radioBtn1.setActionCommand(randomList.get(0).toString());

            value = hasList.get(arr.get(randomList.get(1)));
            radioBtn2.setText("B. " + value.get(rand.nextInt(value.size())));
            radioBtn2.setForeground(null);
            radioBtn2.setActionCommand(randomList.get(1).toString());

            value = hasList.get(arr.get(randomList.get(2)));
            radioBtn3.setText("C. " + value.get(rand.nextInt(value.size())));
            radioBtn3.setForeground(null);
            radioBtn3.setActionCommand(randomList.get(2).toString());

            value = hasList.get(arr.get(randomList.get(3)));
            radioBtn4.setText("D. " + value.get(rand.nextInt(value.size())));
            radioBtn4.setForeground(null);
            radioBtn4.setActionCommand(randomList.get(3).toString());
            radioBtn1.setSelected(true);
        }
        else{
            Random rand = new Random();
            ArrayList<String> values = hasList.get(arr.get(0));
            Question.setText("SlangWord của Definition: " + values.get(rand.nextInt(values.size())));
        
            radioBtn1.setText("A. " + arr.get(randomList.get(0)));
            radioBtn1.setForeground(null);
            radioBtn1.setActionCommand(randomList.get(0).toString());

            radioBtn2.setText("B. " + arr.get(randomList.get(1)));
            radioBtn2.setForeground(null);
            radioBtn2.setActionCommand(randomList.get(1).toString());

            radioBtn3.setText("C. " + arr.get(randomList.get(2)));
            radioBtn3.setForeground(null);
            radioBtn3.setActionCommand(randomList.get(2).toString());

            radioBtn4.setText("D. " + arr.get(randomList.get(3)));
            radioBtn4.setForeground(null);
            radioBtn4.setActionCommand(randomList.get(3).toString());
            radioBtn1.setSelected(true);
        }
    }
    
    public void randomQuestion(){
        arr = list.random(4);
        HashMap<String, ArrayList<String>> hasList;
        hasList = list.getList();
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        randomList.add(0);
        randomList.add(1);
        randomList.add(2);
        randomList.add(3);
        Collections.shuffle(randomList);
        
        //random
        if(isSlangWord){
            Question = new JLabel("Definition của slang-word: " + arr.get(0));
            JPTitle = new JPanel();
            JPTitle.add(Question);

            Random rand = new Random();
            ArrayList<String> value = hasList.get(arr.get(randomList.get(0)));
            radioBtn1 = new JRadioButton("A. " + value.get(rand.nextInt(value.size())));
            radioBtn1.setActionCommand(randomList.get(0).toString());

            value = hasList.get(arr.get(randomList.get(1)));
            radioBtn2 = new JRadioButton("B. " + value.get(rand.nextInt(value.size())));
            radioBtn2.setActionCommand(randomList.get(1).toString());

            value = hasList.get(arr.get(randomList.get(2)));
            radioBtn3 = new JRadioButton("C. " + value.get(rand.nextInt(value.size())));
            radioBtn3.setActionCommand(randomList.get(2).toString());

            value = hasList.get(arr.get(randomList.get(3)));
            radioBtn4 = new JRadioButton("D. " + value.get(rand.nextInt(value.size())));
            radioBtn4.setActionCommand(randomList.get(3).toString());
        }
        else{
            Random rand = new Random();
            ArrayList<String> values = hasList.get(arr.get(0));
            Question = new JLabel("SlangWord của Definition: " + values.get(rand.nextInt(values.size())));
            JPTitle = new JPanel();
            JPTitle.add(Question);

        
            radioBtn1 = new JRadioButton("A. " + arr.get(randomList.get(0)));
            radioBtn1.setActionCommand(randomList.get(0).toString());

            radioBtn2 = new JRadioButton("B. " + arr.get(randomList.get(1)));
            radioBtn2.setActionCommand(randomList.get(1).toString());

            radioBtn3 = new JRadioButton("C. " + arr.get(randomList.get(2)));
            radioBtn3.setActionCommand(randomList.get(2).toString());

            radioBtn4 = new JRadioButton("D. " + arr.get(randomList.get(3)));
            radioBtn4.setActionCommand(randomList.get(3).toString());
        }
        
        
        bg = new ButtonGroup();
        bg.add(radioBtn1);
        bg.add(radioBtn2);
        bg.add(radioBtn3);
        bg.add(radioBtn4);
        radioBtn1.setSelected(true);
        
        JPContent = new JPanel();
        JPContent.add(radioBtn1);
        JPContent.add(radioBtn2);
        JPContent.add(radioBtn3);
        JPContent.add(radioBtn4);
        JPContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        JPContent.setLayout(new BoxLayout(JPContent, BoxLayout.Y_AXIS));
       
        JButton Btn1 = new JButton("Chọn");
        Btn1.setActionCommand("OK");
        Btn1.addActionListener(new BtnAction());
        JPActions = new JPanel();
        JPActions.setLayout(new FlowLayout());
        JPActions.add(Btn1);
        JPActions.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JButton Btn2 = new JButton("Tiếp tục");
        Btn2.setActionCommand("CONTINUE");
        Btn2.addActionListener(new BtnAction());
        JButton Btn3 = new JButton("Thoát");
        Btn3.setActionCommand("EXT");
        Btn3.addActionListener(new BtnAction());
        JPActions2 = new JPanel();
        JPActions2.setLayout(new FlowLayout());
        JPActions2.add(Btn2);
        JPActions2.add(Btn3);
        JPActions2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        Container = new JPanel();
        Container.setLayout(new BorderLayout());
        Container.add(JPTitle, BorderLayout.PAGE_START);
        Container.add(JPContent, BorderLayout.CENTER);
        Container.add(JPActions, BorderLayout.PAGE_END);
        Container.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
    }
    public void setUpGUI(){
        frame = new JFrame();
        frame.setTitle("FunnyQuestion Slang-word");
        frame.setLayout(new BorderLayout());
        frame.add(Container, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(350, 230));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                PRFrame.setEnabled(true);
                frame.dispose();
            }
        });
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
