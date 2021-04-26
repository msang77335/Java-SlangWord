/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Sang
 */
public class table {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        // Frame Title
        jFrame.setTitle("JTable Example");
        // Data to be displayed in the JTable
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };
        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };
        // Initializing the JTable
        JTable jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        jFrame.add(sp);
        // Frame Size
        jFrame.setSize(500, 200);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
