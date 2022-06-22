package features;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {
	public static void createFile(String path, String content) throws IOException {
		File file = new File(Paths.get(path).toString());

		file.createNewFile();

		FileWriter fileWriter = new FileWriter(path);
		fileWriter.write(content);
		fileWriter.close();
	}

	public static void createDirectory(String path) throws IOException {
		if (!fileExists(path))
			Files.createDirectory(Paths.get(path));
	}

	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}

	public static boolean fileExists(String path) {
		return Files.exists(Paths.get(path));
	}

	public static boolean fileNotExists(String path) {
		return Files.notExists(Paths.get(path));
	}
}
