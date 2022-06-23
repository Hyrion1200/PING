package features.maven;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMaven {
    private ProjectServ projectServ = new ProjectServ();

    public boolean test_maven_feature(String path, Mandatory.Features.Maven feature, Object... params) {
        Project project = projectServ.load(Paths.get(path));
        Feature.ExecutionReport report = project.getFeature(feature).get().execute(project, params);
        return report.isSuccess();
    }

    @Test
    public void test_tree() {
        assertTrue(test_maven_feature("./", Mandatory.Features.Maven.TREE));
    }
    @Test
    public void test_tree_malformed() {
        assertFalse(test_maven_feature(".", Mandatory.Features.Maven.TREE, " -- h"));
    }

    @Test
    public void test_test() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.TEST));
    }
    @Test
    public void test_package() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.PACKAGE));
    }
    @Test
    public void test_clean() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.CLEAN));
    }
    @Test
    public void test_exec() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.EXEC, "help"));
    }
    @Test
    public void test_compile() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.COMPILE));
    }
    @Test
    public void test_install() {
        assertTrue(test_maven_feature(".", Mandatory.Features.Maven.INSTALL));
    }
}
