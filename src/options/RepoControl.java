package options;

import Controller.CommandController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepoControl {
    private CommandController commandController;
    private HashMap<String, ArrayList<String>> newMap;

    public RepoControl(CommandController commandController){
        this.commandController = commandController;
        newMap = commandController.getDataMap();    }

    public Map.Entry<String, ArrayList<String>> selectKey(String key){
        for (Map.Entry<String, ArrayList<String>> entrySet : commandController.getDataMap().entrySet()){
            if (entrySet.getKey().equals(key))
                return entrySet;
        }
        return null;
    }

    public boolean deleteKey(String key){
        if (commandController.getDataMap().get(key) == null)
            return false; // Can't find key
        else{
            newMap.remove(key);
            return true;
        }
    }

    public boolean updateKey(Map.Entry<String, ArrayList<String>> entry){
        if (commandController.getDataMap().get(entry.getKey()) == null)
            return false; // Can't find key
        else{
            newMap.remove(entry.getKey());
            newMap.put(entry.getKey(), entry.getValue());
            return true;
        }
    }

    public void insertKey(Map.Entry<String, ArrayList<String>> entry){
        newMap.put(entry.getKey(), entry.getValue());
    }

    public void applyData() throws IOException {
        commandController.setDataMap(newMap);
        File file = new File ("/Users/dong/IdeaProjects/easyCommand/src/repo/repo.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        // Input New Data in repo.txt and save
        for (Map.Entry<String, ArrayList<String>> entry : commandController.getDataMap().entrySet()){
            bufferedWriter.write(entry.getKey() + commandController.getDelimeter(), 0, entry.getKey().length() + commandController.getDelimeter().length());
            while (!entry.getValue().isEmpty()){
                String value = entry.getValue().get(0);
                bufferedWriter.write(entry.getValue().remove(0) + commandController.getDelimeter(), 0, value.length() + commandController.getDelimeter().length());
            }
            String enter = "\n";
            bufferedWriter.write(enter, 0, enter.length());
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
