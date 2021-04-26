/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import object.ListSlangWord;
/**
 *
 * @author Sang
 */
public class FileAction {
    public static void read(String path, ListSlangWord list){
        File file = new File(path);
        try {
             Scanner scan = new Scanner(file);
             while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split("`");
                String key = lineSplit[0];
                ArrayList<String> values = new ArrayList<>();
                for(String item : lineSplit[1].split("\\|")){
                   values.add(item.trim());
                }
                list.addMap(key, values);
            }
           
        } catch (Exception e) {
        }
    }
    public static Object[][] readHistory(String path){
        ArrayList<Object[]> data = new ArrayList<>();
        File file = new File(path);
        try {
             Scanner scan = new Scanner(file);
             while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split("`");
                Object[] lineData = {lineSplit[0], lineSplit[1]};
                data.add(lineData);
            }
           
        } catch (Exception e) {
        }
        Object[][] result = new Object[data.size()][];
        return data.toArray(result);
    }
    public static void write(String path, ListSlangWord list){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
                fw = new FileWriter(path);
                bw = new BufferedWriter(fw);
                HashMap<String, ArrayList<String>> newList = list.getList();
                String line = "";
                Set<String> ketSet = newList.keySet();
                for(String i : ketSet){
                    line = line + i + "`";
                    
                    ArrayList<String> values = new ArrayList<String>();
                    values = newList.get(i);
                    for(int j = 0; j < values.size(); j++){
                        if(j != values.size() - 1){
                            line = line + values.get(j) + "|";
                            }
                            else if (j == values.size() - 1){
                                line = line + values.get(j);
                            }
                    }
                    bw.write(line);
                    bw.newLine();
                    line = "";
                }
                bw.close();
        } catch (IOException e) {
                e.printStackTrace();
                
        }
    }
    public static void writeHistory(String path, String str1, String str2){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
                fw = new FileWriter(path, true);
                bw = new BufferedWriter(fw);
                String line = str1 + "`" + str2;
                bw.write(line);
                bw.newLine();
                bw.close();
        } catch (IOException e) {
                e.printStackTrace();
          
        }
    }
}
