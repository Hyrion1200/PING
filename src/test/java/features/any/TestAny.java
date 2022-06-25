package features.any;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAny {
    private ProjectServ projectServ = new ProjectServ();

    @Test
    public void testCleanupRelative() throws IOException {
        String[] dirToCreate = {"ignored", "directory", "ignored/dir"};
        String[] fileToCreate = {"salut", "a.txt", "c", "ignored/a", "ignored/dir/b", "directory/a.txt", "directory/salut", "directory/b"};
        String[] toIgnore = {"salut", "a.txt", "ignored"};
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ, List.of(dirToCreate), List.of(fileToCreate), List.of(toIgnore));
        assertTrue(testLauncher.test(Mandatory.Features.Any.CLEANUP));
    }

    @Test
    public void testCleanupAbsoulte() throws IOException {
        String[] dirToCreate = {"ignored", "directory", "ignored/dir"};
        String[] fileToCreate = {"salut", "a.txt", "c", "ignored/a", "ignored/dir/b", "directory/a.txt", "directory/salut", "directory/b"};
        String[] toIgnore = {"salut", "a.txt", "ignored"};
        AnyTestClass testLauncher = new AnyTestClass(Paths.get(".").toAbsolutePath().toString(), projectServ, List.of(dirToCreate), List.of(fileToCreate), List.of(toIgnore));
        assertTrue(testLauncher.test(Mandatory.Features.Any.CLEANUP));
    }

    @Test
    public void testDistRelative() throws IOException {
        String[] dirToCreate = {"ignored", "directory", "ignored/dir"};
        String[] fileToCreate = {"salut", "a.txt", "c", "ignored/a", "ignored/dir/b", "directory/a.txt", "directory/salut", "directory/b"};
        String[] toIgnore = {"salut", "a.txt", "ignored"};
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ, List.of(dirToCreate), List.of(fileToCreate), List.of(toIgnore));
        assertTrue(testLauncher.test(Mandatory.Features.Any.DIST));
    }

    @Test
    public void testDistAbsolute() throws IOException {
        String[] dirToCreate = {"ignored", "directory", "ignored/dir"};
        String[] fileToCreate = {"salut", "a.txt", "c", "ignored/a", "ignored/dir/b", "directory/a.txt", "directory/salut", "directory/b"};
        String[] toIgnore = {"salut", "a.txt", "ignored"};
        AnyTestClass testLauncher = new AnyTestClass(Paths.get(".").toAbsolutePath().toString(), projectServ, List.of(dirToCreate), List.of(fileToCreate), List.of(toIgnore));
        assertTrue(testLauncher.test(Mandatory.Features.Any.DIST));
    }
}
