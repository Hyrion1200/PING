package services;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Aspects;
import fr.epita.assistants.myide.domain.entity.aspects.Git;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class TestProjectService {
    @Test
    public void TestLoad(){
        try {
            Files.createDirectories(Paths.get("./Test"));
            Files.createFile(Paths.get("./Test/testFile"));
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize test");
        }

        Project proj = ps.load(Paths.get("./Test"));
        Node node = proj.getRootNode();
        assert node.getPath().toString().equals("./Test");
        assert node.getChildren().size() == 1;
        assert node.getChildren().get(0).getPath().toString().equals("./Test/testFile");

        try {
            FileUtils.deleteDirectory(new File("./Test"));
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to finalize test");
        }
    }
    private void testGit(){
        try {
            Files.createDirectories(Paths.get("./TestGit"));
            Files.createFile(Paths.get("./TestGit/.git"));
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize test");
        }

        Project proj = ps.load(Paths.get("./TestGit"));
        Set<Aspect> as = proj.getAspects();
        for (Aspect a : as){
            assert a.getClass() == Git.class || a.getClass() == Any.class;
        }

        try {
            FileUtils.deleteDirectory(new File("./TestGit"));
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to finalize test");
        }
    }

    private void testMaven(){
        try {
            Files.createDirectories(Paths.get("./TestMaven"));
            Files.createFile(Paths.get("./TestMaven/pom.xml"));
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize test");
        }

        Project proj = ps.load(Paths.get("./TestMaven"));
        Set<Aspect> as = proj.getAspects();
        for (Aspect a : as){
            assert a.getClass() == Maven.class || a.getClass() == Any.class;
        }

        try {
            FileUtils.deleteDirectory(new File("./TestMaven"));
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to finalize test");
        }
    }
    @Test
    public void TestAspect(){
        testMaven();
        testGit();
    }

    ProjectServ ps = new ProjectServ();
}
