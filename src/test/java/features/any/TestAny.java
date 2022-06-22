package features.any;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.apache.commons.compress.utils.FileNameUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAny {
    private ProjectServ projectServ = new ProjectServ();



    public boolean test(String path, Mandatory.Features.Any feat ,boolean ignore) {
        try {
            if (ignore)
                createFile(path + "/" + ".myideignore", "salut\nbonjour\na.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Project project = projectServ.load(Paths.get(path));
        Feature.ExecutionReport report = project.getFeature(feat)
                .get().execute(project);

        if (report.isSuccess())
        {
            if (feat == Mandatory.Features.Any.DIST)
            {
                String zipName = FileNameUtils.getBaseName(path) + ".zip";
                assertTrue(fileExists(zipName));
                deleteFile(zipName);
            }
            assertTrue(fileNotExists(path + "/" + "salut"));
            assertTrue(fileNotExists(path + "/" + "bonjour"));
            assertTrue(fileNotExists(path + "/" + "a.txt"));
            assertTrue(fileNotExists(path + "/" + "directory/salut"));
            assertTrue(fileNotExists(path + "/" + "directory/other/bonjour"));
        }

        assertTrue((!ignore || fileExists(path + "/" + ".myideignore")));
        assertTrue(fileExists(path + "/" + "directory"));
        assertTrue(fileExists(path + "/" + "directory/other"));

        return report.isSuccess();
    }

    @Test
    @Order(1)
    public void testCleanupRelativeNonExistingIgnore() {
        assertTrue(!test(".", Mandatory.Features.Any.CLEANUP, false));
    }

    @Test
    @Order(2)
    public void testCleanupRelativeExistingIgnore() {
        assertTrue(test(".", Mandatory.Features.Any.CLEANUP, true));
    }

    @Test
    @Order(3)
    public void testCleanupExistingIgnore() {
        assertTrue(test("/home/mthdqe/Epita/PING", Mandatory.Features.Any.CLEANUP, true));
    }

    @Test
    @Order(4)
    public void testCleanupNonExistingIgnore() {
        assertTrue(!test("/home/mthdqe/Epita/PING", Mandatory.Features.Any.CLEANUP, false));
    }

    @Test
    @Order(5)
    public void testDistExistingIgnore() {
        assertTrue(test("/home/mthdqe/Epita/PING", Mandatory.Features.Any.DIST, true));
    }

    @Test
    @Order(6)
    public void testDistNonExistingIgnore() {
        assertTrue(!test("/home/mthdqe/Epita/PING", Mandatory.Features.Any.DIST, false));
    }

    @Test
    @Order(7)
    public void testDistRelativePathNonExistingIgnore() {
        assertTrue(!test(".", Mandatory.Features.Any.DIST, false));
    }

    @Test
    @Order(8)
    public void testDistRelativePathExistingIgnore() {
        assertTrue(test(".", Mandatory.Features.Any.DIST, true));
    }
}
