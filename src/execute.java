import Controller.CommandController;

import java.io.*;

public class execute {
    public static void main(String[] args) throws IOException {
        // default Setting
        // 1. shortCut Keys, 2. option

        if (args[0].equals("-r")){
            args = new String[2];
            args[0] = "";
            args[1] = "-r";
        }
        else if (args.length < 2){
            System.out.println("Need to put command [shortcutKey] [-option]");
            return;
        }


        //System.out.println(System.getProperty("user.dir"));
        CommandController commandController = new CommandController(args);






    }
}
