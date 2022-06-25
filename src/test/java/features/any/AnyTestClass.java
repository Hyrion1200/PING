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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AnyTestClass {
    private String path;
    private ProjectServ projServ;
    private List<String> dirsToCreate;
    private List<String> filesToCreate;
    private List<String> filesToIgnore;

    public AnyTestClass(String path, ProjectServ projServ, List<String> dirsToCreate, List<String> filesToCreate, List<String> filesToIgnore) {
        this.path = path;
        this.projServ = projServ;
        this.dirsToCreate = dirsToCreate;
        this.filesToCreate = filesToCreate;
        this.filesToIgnore = filesToIgnore;
    }

    private boolean containsFile(String fileStrPath, List<String> ignored) {
        File file = new File(fileStrPath);

        while (file != null) {
            if (ignored.contains(file.getName()))
                return true;
            file = file.getParentFile();
        }

        return false;
    }

    private void before() throws IOException {
        for (String dirToCreate : dirsToCreate)
            TestUtils.createDirectory(dirToCreate);

        for (String fileToCreate : filesToCreate)
            TestUtils.createFile(path + "/" + fileToCreate, "");

        String ignoreContent = "";

        for (String fileToIgnore : filesToIgnore)
            ignoreContent = ignoreContent.concat(fileToIgnore + "\n");

        TestUtils.createFile(path + "/.myideignore", ignoreContent);
    }

    public boolean test(Mandatory.Features.Any feat) throws IOException{
        Project project = this.projServ.load(Paths.get(path));

        this.before();

        Feature.ExecutionReport report = project.getFeature(feat)
                .get().execute(project);

        if (report.isSuccess())
        {
            if (feat == Mandatory.Features.Any.DIST)
            {
                String zipName = Paths.get(path).toAbsolutePath().getParent().toAbsolutePath().toString() + "/" + FileNameUtils.getBaseName(path) + ".zip";
                assertTrue(TestUtils.fileExists(zipName));
            }

            for (String file : filesToCreate) {
                System.out.println(file);
                if (containsFile(file, filesToIgnore)) {
                    assertTrue(TestUtils.fileNotExists(path + "/" + file));
                }

                else {
                    assertTrue(TestUtils.fileExists(path + "/" + file));
                }
            }
        }

        this.after();

        return report.isSuccess();
    }

    private void after() throws IOException {
        for (String file : filesToCreate)
            TestUtils.deleteFile(path + "/" + file);

        for (String dir : dirsToCreate)
            TestUtils.deleteFile(path + "/" + dir);

        TestUtils.deleteFile(path + "/.myideignore");

        TestUtils.deleteFile("../" + FileNameUtils.getBaseName(path) + ".zip");
    }
}
