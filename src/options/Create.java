package options;

import Controller.CommandController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Create implements Option{
    private CommandController commandController;
    private ArrayList<String> userCommand;
    private String comment;
    private String userInput = "";
    private Scanner s;
    public Create(CommandController commandController){
        this.commandController = commandController;
        userCommand = new ArrayList<>();
        userCommand.add("/bin/sh");
        userCommand.add("-c");
        s = new Scanner(System.in);
    }

    public void explain() throws IOException {
        System.out.println("********************Create********************");
        System.out.println("==================================================");
        // Insert Command Line
        while (true){
            System.out.print("Insert Command Line (end : q, Re-text: r) : ");
            userInput = s.nextLine();

            if (userInput.equals("r")){ // Re-write commands
                System.out.println("Re-text Command Line");
                userCommand = new ArrayList<>();
                userCommand.add("/bin/sh");
                userCommand.add("-c");
            }
            else if(userInput.equals("q")){
                break;
            }
            else
                userCommand.add(userInput);
        }

        // Insert Comment
        System.out.print("Insert Comment : ");
        comment = s.nextLine();
        userCommand.add(commandController.getCommentDeli() + comment);

        // Insert Data in Repo.txt
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put(commandController.getShortCutKey(), userCommand);
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
            // if new Data
            if (commandController.getRepoControl().selectKey(commandController.getShortCutKey()) == null){
                commandController.getRepoControl().insertKey(entry);
            }
            else{ // If already set, then update
                commandController.getRepoControl().updateKey(entry);
            }
        }
        commandController.getRepoControl().applyData();
        System.out.println("Saved Command Line");
        System.out.println("==================================================");
    }


}
