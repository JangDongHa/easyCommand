package options;

import Controller.CommandController;

import java.io.IOException;
import java.util.Scanner;

public class Delete implements Option{
    private CommandController commandController;
    private Scanner s;
    private String userInput;

    public Delete(CommandController commandController){
        this.commandController = commandController;
        s = new Scanner(System.in);
    }

    public void explain() throws IOException {
        System.out.println("********************Delete********************");
        System.out.println("==================================================");
        if (commandController.getRepoControl().deleteKey(commandController.getShortCutKey())){
            System.out.print("Find " + commandController.getShortCutKey() + " Command. Delete it?(Y/N) ");
            userInput = s.nextLine();

            if (userInput.equals("Y") || userInput.equals("y")){
                System.out.println("Delete Complete");
                commandController.getRepoControl().applyData();
            }
            else
                System.out.println("Delete Denied");
        }
        System.out.println("==================================================");
    }
}
