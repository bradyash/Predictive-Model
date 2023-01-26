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

        File[] contents = root.listFiles();
        // TODO: Figure out a recursive way to traverse files. Tree?

        return null;
    }
}
