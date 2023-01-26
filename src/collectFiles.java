import java.io.File;
import java.util.ArrayList;

public class collectFiles {
    public static ArrayList<File> collectFiles(String directory) {
        ArrayList<File> allFiles = new ArrayList<>();
        File root = null;
        try {
            root = new File(directory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // File[] contents = root.listFiles();
        // TODO: Figure out a recursive way to traverse files. Tree?
        tree(root);

        return null;
    }

    public static void tree(File f) {
        File file = f;

        if (!file.isDirectory()) {
            System.out.println(f.getName());
            return;
        }

        String files[] = file.list();
        for (int i = 0; i < files.length; i++) {
            tree(new File(f.getName() + File.separator + files[i]));
            //System.out.println(f.getName() + File.separator + files[i]);
        }
    }
}
