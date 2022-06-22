package fr.epita.assistants.unit_tests.features.git;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class TestGit {
    private ProjectServ projectServ = new ProjectServ();

    public boolean testGitAdd(String path, Object ...params) {
        Project proj = projectServ.load(Paths.get(path));
        if (proj.getFeature(Mandatory.Features.Git.ADD).isPresent()) {
            Feature.ExecutionReport report = proj.getFeature(Mandatory.Features.Git.ADD).get().execute(proj, params);
            return report.isSuccess();
        }
        else {
            System.out.println("feature not found");
            return false;
        }
    }

    public boolean testGitCommit(String path, Object ...params) {
        Project proj = projectServ.load(Paths.get(path));
        if (proj.getFeature(Mandatory.Features.Git.COMMIT).isPresent()) {
            Feature.ExecutionReport report = proj.getFeature(Mandatory.Features.Git.COMMIT).get().execute(proj, params);
            return report.isSuccess();
        }
        else {
            System.out.println("feature not found");
            return false;
        }
    }

    public boolean testGitPush(String path, Object ...params) {
        Project proj = projectServ.load(Paths.get(path));
        if (proj.getFeature(Mandatory.Features.Git.PUSH).isPresent()) {
            Feature.ExecutionReport report = proj.getFeature(Mandatory.Features.Git.PUSH).get().execute(proj, params);
            return report.isSuccess();
        }
        else {
            System.out.println("feature not found");
            return false;
        }
    }

    public boolean testGitPull(String path, Object ...params) {
        Project proj = projectServ.load(Paths.get(path));
        if (proj.getFeature(Mandatory.Features.Git.PULL).isPresent()) {
            Feature.ExecutionReport report = proj.getFeature(Mandatory.Features.Git.PULL).get().execute(proj, params);
            return report.isSuccess();
        }
        else {
            System.out.println("feature not found");
            return false;
        }
    }

    @Test
    public void testGitAdd() {
        assertTrue(testGitAdd("/home/nicolas/s6/test_ping/", "salut"));
    }

    @Test
    public void testGitCommit() { assertTrue(testGitCommit("/home/nicolas/s6/test_ping/", "test salut")); }

    @Test
    public void testGitAddMany() { assertTrue(testGitAdd("/home/nicolas/s6/test_ping/", "salut2", "salut3", "salut4")); }

    @Test
    public void testGitCommitMany() { assertTrue(testGitCommit("/home/nicolas/s6/test_ping/", "commit of ", "salut2", "salut3", "salut4"));}

    @Test
    public void testGitPush() {
        assertTrue(testGitPush("/home/nicolas/s6/test_ping/", "test salut"));
    }

    @Test
    public void testGitPull() { assertTrue(testGitPull("/home/nicolas/s6/test_ping/", "salut"));}
}
