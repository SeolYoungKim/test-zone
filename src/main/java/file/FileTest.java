package file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(File.listRoots()));
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.pathSeparator);
        System.out.println(File.separatorChar);
        System.out.println(File.separator);

        FileFilter fileFilter = file -> file.getName().endsWith(".java");
        FilenameFilter filenameFilter = (dir, name) -> name.endsWith(".java");

        // JDK7 이상부터는 java.nio.file.Files를 사용하는게 더 효과적임
        List<Path> paths = Files.list(Path.of("/Users/seolyoungkim/Desktop/study/test-zone/test-zone/src/main"))
                .toList();

        System.out.println(paths);
        for (Path path : paths) {
            System.out.println(path.getFileName());
            System.out.println(path.getFileSystem());
            System.out.println(path.getRoot());
            System.out.println(path.getParent());
        }
    }
}
