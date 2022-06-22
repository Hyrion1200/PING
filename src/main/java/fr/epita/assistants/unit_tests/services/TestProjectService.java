package fr.epita.assistants.unit_tests.services;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Aspects;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Set;

public class TestProjectService {
    @Test
    public void TestLoad(){
        Project proj = ps.load(Paths.get("./Test"));
        Node node = proj.getRootNode();
        assert node.getPath().toString().equals("./Test");
        assert node.getChildren().size() == 1;
        assert node.getChildren().get(0).getPath().toString().equals("./Test/testFile");
    }

    private void testMaven(){
        Project proj = ps.load(Paths.get("./TestMaven"));
        Set<Aspect> as = proj.getAspects();
        for (Aspect a : as){
            assert a.getClass() == Maven.class || a.getClass() == Any.class;
        }
    }
    @Test
    public void TestAspect(){
        testMaven();
    }

    ProjectServ ps = new ProjectServ();
}
