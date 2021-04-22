/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import file.FileAction;

/**
 *
 * @author Sang
 */
public class ListSlangWord {
    HashMap<String, ArrayList<String>> list = new HashMap<String, ArrayList<String>>();
    public void add(String key, ArrayList<String> values){
        list.put(key, values);
    }
    public void edit(String key, String value){
        
    }
    public void delete(String key, String value){
        
    }
    public void reset(String key, String value){
        FileAction.read("slangword", this);
    }
    public void random(String key, String value){
        
        
    }
    public ArrayList<String> searchBySlangWord(String slangword){
        return(list.get(slangword));
    }
    public String searchByDefinition(String definition){
        for(String key: list.keySet())
        {
            if(list.get(key).contains(definition)){
                return key;
            }
        }
        return null;
    }
    public void show(){
        Set<String> ketSet = list.keySet();
        for(String i : ketSet){
            ArrayList<String> values = new ArrayList<>();
            values = list.get(i);
            System.out.print(i + "`");
            for (String value : values){
                System.out.print(value + "|");
            }
            System.out.println("");
        }
    }
}
