package helpers;

import classes.Major;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadEnrolled {
    public static HashMap<String, Major> readCsv(File f, Dictionary dictionary) throws FileNotFoundException {
        HashMap<String, Major> majors = new HashMap<>();
        File file = null;
        try {
            file = f;
        }
        catch (Exception e) {
        }
        Scanner read = new Scanner(file);
        read.nextLine();
        System.out.println("======================================");
        System.out.println("ReadEnrolled.readCsv");
        System.out.println("======================================");
        // Reads in all lines and adds majors.
        while(read.hasNextLine()) {
            String[] splitLine = read.nextLine().split(",");
            System.out.println("[" + splitLine[0] + ']');
            Major major = new Major(dictionary.getDictItem(splitLine[0]).strip(), dictionary.getDictEnroll(splitLine[0]));
            majors.put(splitLine[0].strip(), major);
            read.nextLine();
        }
        read.close();
        return majors;
    }
}
