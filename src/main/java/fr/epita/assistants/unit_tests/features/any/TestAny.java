package fr.epita.assistants.unit_tests.features.any;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class TestAny {
    private ProjectServ projectServ = new ProjectServ();

    public boolean testDist(String path) {
        Project project = projectServ.load(Paths.get(path));
        Feature.ExecutionReport report = project.getFeature(Mandatory.Features.Any.DIST).get().execute(project);
        return report.isSuccess();
    }
    @Test
    public void testDistExistingIgnore() {
        assertTrue(testDist("/home/mthdqe/Epita/PING"));
    }

    @Test
    public void testDistNonExistingIgnore() {
        assertTrue(!testDist("/home/mthdqe/Epita"));
    }
}
