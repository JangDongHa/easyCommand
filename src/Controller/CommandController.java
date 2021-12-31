package Controller;

import lombok.Getter;
import lombok.Setter;
import options.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class CommandController {
    private RepoControl repoControl;
    private String shortCutKey;
    private String option;
    private HashMap<String, ArrayList<String>> dataMap;
    private String delimeter = "---";
    private String commentDeli = "###";
    private Option userOption;

    public CommandController(String[] args) throws IOException {
        try{ // If user input wrong format at args
            shortCutKey = args[0];
            option = args[1];
            dataMap = new HashMap<>();
            repoControl = new RepoControl(this);
            readRepo();

            switch (option){
                case "-c":
                    userOption = new Create(this);
                    break;
                case "-r":
                    userOption = new Read(this);
                    break;
                case "-u":
                    userOption = new Create(this);
                    break;
                case "-d":
                    userOption = new Delete(this);
                    break;
                case "-e":
                    userOption = new Execute(this);
                    break;
            }
            userOption.explain();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    private void readRepo() throws IOException {
        File file = new File("/Users/dong/IdeaProjects/easyCommand/src/repo/repo.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String oneLineData; // All data in one line
        String[] oneLineDataArr;
        String keyData;
        ArrayList<String> valueData = new ArrayList<>(); // Listing data in one line
        while ((oneLineData = bufferedReader.readLine()) != null){
            oneLineDataArr = oneLineData.split(delimeter);
            keyData = oneLineDataArr[0];
            for (int i = 1; i < oneLineDataArr.length; i++){
                valueData.add(oneLineDataArr[i]);
            }
            dataMap.put(keyData, valueData);
            keyData = null;
            valueData = new ArrayList<>();
        }
    }

}
