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
import java.util.Random;
import sun.awt.SunHints;

/**
 *
 * @author Sang
 */
public class ListSlangWord {
    HashMap<String, ArrayList<String>> list = new HashMap<String, ArrayList<String>>();
    public void addMap(String key, ArrayList<String> values){
        list.put(key, values);
    }
    
    public void removeMap(String key){
        list.remove(key);
    }
    
    public void add(String key, String value){
        if(list.containsKey(key)){
            ArrayList<String> newArrayList = list.get(key);
            newArrayList.add(value);
            list.put(key, newArrayList);
        }
        else{
            ArrayList<String> newArrayList = new ArrayList<>();
            newArrayList.add(value);
            list.put(key, newArrayList);
        }
    }
    
    public void edit(String key, String value){
        
    }
    
    public void delete(String key, String value){
        
    }
    
    public void reset(String key, String value){
        FileAction.read("slangword", this);
    }
    
    public ArrayList<String> random(int n){
        ArrayList<String> result = new ArrayList<String>();
        while(result.size() < n){
            Object randomName = list.keySet().toArray()[new Random().nextInt(list.keySet().toArray().length)];
            String value = randomName.toString();
            if(!result.contains(value)){
                result.add(value);
            }
        }
        return result;
    }
    
    public HashMap<String, ArrayList<String>> getList(){
        return list;
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
