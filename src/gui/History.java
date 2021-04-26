/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import file.FileAction;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Sang
 */

public class History implements ItemListener {
    JPanel cards;
    JFrame jFrame;
    JFrame frameMenu;
    final static String SLANGPANEL = "Slang-Word History";
    final static String DEFPANEL = "Definition History";
    
    class BtnAction implements ActionListener
    {
	public void actionPerformed(ActionEvent ae)
	{
             String strActionCommand = ae.getActionCommand();
                if(strActionCommand.equals("EXT")){
                    frameMenu.setEnabled(true);
                    jFrame.dispose();
                }
	}
    }
    
    JPanel Container;
    JPanel Top;
    JPanel Center;
    JPanel Bottom;
    JPanel Slang;
    JPanel Def;
    JScrollPane scrollPaneSlang;
    JScrollPane scrollPaneDef;
    JTable tableDef;
    
    
    public History(JFrame frameMenu){
        this.frameMenu = frameMenu;
        Container = new JPanel();
        Top = new JPanel();
        Center = new JPanel();
        Bottom = new JPanel();
        
        String[] columnNamesSlang = {"Slang-Word", "Definition"};
        Object[][] dataSlang = FileAction.readHistory("historySlang.txt");
        JTable tableSlang = new JTable(dataSlang, columnNamesSlang);
        tableSlang.setSize(200, 500);
        scrollPaneSlang = new JScrollPane(tableSlang);
        
        String[] columnNamesDef = {"Definition", "Slang-Word"};
        Object[][] dataDef = FileAction.readHistory("historyDef.txt");
        tableDef = new JTable(dataDef, columnNamesDef);
        tableDef.setSize(200, 500);
        scrollPaneDef = new JScrollPane(tableDef);
        
        cards = new JPanel(new CardLayout());
        cards.setSize(200, 500);
        cards.add(scrollPaneSlang, SLANGPANEL);
        cards.add(scrollPaneDef, DEFPANEL);
        
        
        Center.add(cards);
        
        Container.setLayout(new BorderLayout());
        Container.add(Top, BorderLayout.PAGE_START);
        Container.add(Center, BorderLayout.CENTER);
        Container.add(Bottom, BorderLayout.PAGE_END);
        Container.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JPanel comboBoxPane = new JPanel(); 
        String comboBoxItems[] = {SLANGPANEL, DEFPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        Top.add(comboBoxPane);
        
        JButton btnExt = new JButton("Thoát");
        btnExt.setActionCommand("EXT");
        btnExt.addActionListener(new BtnAction());
        Bottom.add(btnExt);
        
        Container.setMaximumSize(null);
    }

    public void itemStateChanged(ItemEvent evt) 
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public void setUpGUI() 
    {
        jFrame = new JFrame();
        // Frame Title
        jFrame.setTitle("JTable Example");
        jFrame.setLayout(new BorderLayout());
        // Data to be displayed in the JTable
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent event) {
                frameMenu.setEnabled(true);
                jFrame.dispose();
            }
        });
        jFrame.add(Container, BorderLayout.CENTER);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
