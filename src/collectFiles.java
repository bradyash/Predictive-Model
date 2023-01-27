import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class collectFiles {
    public static ArrayList<File> collectFiles(String directory) {
        // Initialize files, try to grab the root directory
        ArrayList<File> allFiles = new ArrayList<>();
        File root = null;
        try {
            root = new File(directory);
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
            //System.out.println(f.getName());
            allFiles.add(file);
            return;
        }

        String files[] = file.list();
        for (int i = 0; i < files.length; i++) {
            tree(new File(f.getPath() + File.separator + files[i]), allFiles);
        }
    }
}
