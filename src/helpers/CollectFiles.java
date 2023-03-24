package helpers;

import java.io.File;
import java.util.ArrayList;

public class CollectFiles {
    public static ArrayList<File> collectFiles(File directory) {
        // Initialize files, try to grab the root directory
        ArrayList<File> allFiles = new ArrayList<>();
        File root = null;
        try {
            root = directory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // call tree with the root directory
        tree(root, allFiles);

        return allFiles;
    }

    // recursively traverse directories to find all .csv files.
    public static void tree(File f, ArrayList<File> allFiles) {
        File file = f;

        if (!file.isDirectory()) {
            allFiles.add(file);
            return;
        }

        String files[] = file.list();
        for (int i = 0; i < files.length; i++) {
            //System.out.println(f.getPath() + File.separator + files[i]);
            tree(new File(f.getPath() + File.separator + files[i]), allFiles);
        }
    }
}
