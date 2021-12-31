package options;

import Controller.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class Execute implements Option{
    private CommandController commandController;

    public Execute(CommandController commandController){
        this.commandController = commandController;
    }

    @Override
    public void explain() throws IOException {
        String s;
        Process p;
        try {
            Map.Entry<String, ArrayList<String>> entry = commandController.getRepoControl().selectKey(commandController.getShortCutKey());
            ArrayList<String> cmds = entry.getValue();
            String[] cmd = new String[cmds.size()];
            cmd = cmds.toArray(cmd);

            p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println(s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            System.out.println("Can't Find ShortcutKey");
            System.out.println(e);
        }
    }
}
