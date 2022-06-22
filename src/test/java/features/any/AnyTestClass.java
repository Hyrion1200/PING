package features.any;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnyTestClass {
    public String path;

    AnyTestClass(String path) {
        this.path = path;
    }
    public void createFile(String content) throws IOException {
        File file = new File(Paths.get(path).toString());

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(content);
        fileWriter.close();
    }

    public void createDirectory(String path) throws IOException {
        if (!fileExists(path))
            Files.createDirectory(Paths.get(path));
    }

    public boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    public boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public boolean fileNotExists(String path) {
        return Files.notExists(Paths.get(path));
    }
    public void before() {
        createFile(path + "/" + "salut", "");
        createFile(path + "/" + "bonjour", "");
        createFile(path + "/" + "a.txt", "");

        createDirectory(path + "/" + "directory");
        createDirectory(path + "/" + "directory/other");

        createFile(path + "/" + "directory/salut", "");
        createFile(path + "/" + "directory/other/bonjour", "");
    }
}
