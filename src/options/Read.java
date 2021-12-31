package options;

import Controller.CommandController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Read implements Option{
    private CommandController commandController;
    private HashMap<String, ArrayList<String>> map;

    public Read(CommandController commandController){
        this.commandController = commandController;
        map = new HashMap<>();
    }

    public void explain() throws IOException {
        int id = 1;
        if (!commandController.getShortCutKey().equals("")) // Select one
            map.put(commandController.getShortCutKey(), commandController.getDataMap().get(commandController.getShortCutKey()));
        else // select All
            map = commandController.getDataMap();
        System.out.println("********************Read********************");
        // get All data in repo.txt
        for (Map.Entry<String, ArrayList<String>> entrySet : map.entrySet()){
            System.out.println("==================================================");
            System.out.println(id++ + ". shortCut Key: " + entrySet.getKey());
            while (!entrySet.getValue().isEmpty()){
                if (entrySet.getValue().get(0).contains(commandController.getCommentDeli())) // if Comment
                    System.out.println("Comment : " + entrySet.getValue().remove(0).split("###")[1]);
                else // if Command
                    System.out.println("Command : " + entrySet.getValue().remove(0));
            }
            System.out.println("==================================================");
        }
    }


}
