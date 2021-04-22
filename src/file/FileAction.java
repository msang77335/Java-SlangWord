/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
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
                   values.add(item);
                }
                list.addMap(key, values);
            }
           
        } catch (Exception e) {
        }
    }
    public void write(ListSlangWord list){
        
    }
}
