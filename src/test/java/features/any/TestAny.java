package features.any;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAny {
    private ProjectServ projectServ = new ProjectServ();

    @Test
    @Order(1)
    public void testCleanupRelativeNonExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ);
        assertTrue(!testLauncher.test(Mandatory.Features.Any.CLEANUP, false));
    }

    @Test
    @Order(2)
    public void testCleanupRelativeExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ);
        assertTrue(testLauncher.test( Mandatory.Features.Any.CLEANUP, true));
    }

    @Test
    @Order(3)
    public void testCleanupExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(Path.of(".").toAbsolutePath().toString(), projectServ);
        assertTrue(testLauncher.test( Mandatory.Features.Any.CLEANUP, true));
    }

    @Test
    @Order(4)
    public void testCleanupNonExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(Path.of(".").toAbsolutePath().toString(), projectServ);
        assertTrue(!testLauncher.test( Mandatory.Features.Any.CLEANUP, false));
    }

    @Test
    @Order(5)
    public void testDistExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(Path.of(".").toAbsolutePath().toString(), projectServ);
        assertTrue(testLauncher.test( Mandatory.Features.Any.DIST, true));
    }

    @Test
    @Order(6)
    public void testDistNonExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(Path.of(".").toAbsolutePath().toString(), projectServ);
        assertTrue(!testLauncher.test( Mandatory.Features.Any.DIST, false));
    }

    @Test
    @Order(7)
    public void testDistRelativePathNonExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ);
        assertTrue(!testLauncher.test( Mandatory.Features.Any.DIST, false));
    }

    @Test
    @Order(8)
    public void testDistRelativePathExistingIgnore() throws IOException {
        AnyTestClass testLauncher = new AnyTestClass(".", projectServ);
        assertTrue(testLauncher.test( Mandatory.Features.Any.DIST, true));
    }
}
