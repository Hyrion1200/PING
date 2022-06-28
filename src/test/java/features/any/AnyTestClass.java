package features.any;

import features.TestUtils;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.maven.Test;
import fr.epita.assistants.myide.domain.service.ProjectServ;

import org.apache.commons.compress.utils.FileNameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class AnyTestClass {
    private String path;
    private ProjectServ projServ;
    public AnyTestClass(String path, ProjectServ projServ) {
        this.path = path;
        this.projServ = projServ;
    }

    private void before() throws IOException {
        TestUtils.createFile(path + "/" + "salut", "");
        TestUtils.createFile(path + "/" + "bonjour", "");
        TestUtils.createFile(path + "/" + "a.txt", "");

        TestUtils.createDirectory(path + "/" + "directory");
        TestUtils.createDirectory(path + "/" + "directory/other");

        TestUtils.createFile(path + "/" + "directory/salut", "");
        TestUtils.createFile(path + "/" + "directory/other/bonjour", "");
    }

    public boolean test(Mandatory.Features.Any feat, boolean ignore) throws IOException{
        Project project = this.projServ.load(Paths.get(path));

        this.before();

        try {
            if (ignore)
                TestUtils.createFile(path + "/" + ".myideignore", "salut\nbonjour\na.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Feature.ExecutionReport report = project.getFeature(feat)
                .get().execute(project);

        if (report.isSuccess())
        {
            if (feat == Mandatory.Features.Any.DIST)
            {
                String zipName = FileNameUtils.getBaseName(path) + ".zip";
                assertTrue(TestUtils.fileExists(zipName));
                TestUtils.deleteFile(zipName);
            }
            assertTrue(TestUtils.fileNotExists(path + "/" + "salut"));
            assertTrue(TestUtils.fileNotExists(path + "/" + "bonjour"));
            assertTrue(TestUtils.fileNotExists(path + "/" + "a.txt"));
            assertTrue(TestUtils.fileNotExists(path + "/" + "directory/salut"));
            assertTrue(TestUtils.fileNotExists(path + "/" + "directory/other/bonjour"));
        }

        assertTrue((!ignore || TestUtils.fileExists(path + "/" + ".myideignore")));
        assertTrue(TestUtils.fileExists(path + "/" + "directory"));
        assertTrue(TestUtils.fileExists(path + "/" + "directory/other"));

        this.after();

        return report.isSuccess();
    }

    private void after() throws IOException {
        TestUtils.deleteFile(path + "/" + "salut");
        TestUtils.deleteFile(path + "/" + "bonjour");
        TestUtils.deleteFile(path + "/" + "a.txt");
        TestUtils.deleteFile(path + "/" + "directory/salut");
        TestUtils.deleteFile(path + "/" + "directory/other/bonjour");
        TestUtils.deleteFile(path + "/" + "directory/other");
        TestUtils.deleteFile(path + "/" + "directory");
        TestUtils.deleteFile(FileNameUtils.getBaseName(path) + ".zip");
        TestUtils.deleteFile(path + "/" + ".myideignore");
    }
}
