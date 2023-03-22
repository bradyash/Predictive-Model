package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private HashMap<String, ArrayList<String>> dictionary;
    //Figure out how to make a dictionary here, with helper methods

    public Dictionary() {
        dictionary = new HashMap<>();
    }
    public Boolean checkDictionary(String s) {
        if (dictionary.containsKey(s)) {
            return true;
        }
        return false;
    }

    public String getDictItem(String s) {
        ArrayList<String> name = dictionary.get(s);
        if (name == null) {
            return "SHit outta luck homes";
        }
        String majorName = s + " - " + name.get(0);
        return majorName;
    }

    public Integer getDictEnroll(String s) {
        ArrayList<String> i = dictionary.get(s);
        if (i == null) {
            return -100;
        }
        return Integer.parseInt(i.get(1));
    }


    public void importDictionary(File f) throws IOException {
        File file = null;
        try {
            file = f;
        }
        catch (Exception e) {
            System.out.println("ERROR: Dictionary File not Found");
            System.exit(1);
        }
        BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        reader.readLine();
        String read = reader.readLine();

        while(read != null) {
            String[] split = read.split(",");
            ArrayList<String> nameNumber = new ArrayList<>();
            nameNumber.add(split[1]);
            nameNumber.add(split[2]);
            System.out.println(split[0] + " " + nameNumber.get(0) + " " + nameNumber.get(1));
            dictionary.put(split[0],nameNumber);
            if ("WHEM".equals(split[0])) {
                break;
            }
            read = reader.readLine();
        }
    }
}
