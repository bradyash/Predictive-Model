package helpers;

import classes.Major;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

            /**Takes a Dictionary input, and outputs a Hashmap of Majors*/
public class ReadEnrolled {
    public static HashMap<String, Major> readCsv(Dictionary dictionary) throws FileNotFoundException {
        HashMap<String, Major> majors = new HashMap<>();
        // Reads in all lines and adds majors.
        for (String s : dictionary.getDictionary().keySet()) {
            //ArrayList<String> vals = dictionary.getDictionary().get(s);
            Major temp = new Major(dictionary.getDictItem(s), dictionary.getDictEnroll(s));
            majors.put(s, temp);
        }
        return majors;
    }
}
