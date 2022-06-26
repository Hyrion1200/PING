package services;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import fr.epita.assistants.myide.domain.service.NodeServ;
import fr.epita.assistants.myide.domain.service.NodeService;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class TestNodeService {
    NodeService ns = new NodeServ(1);

    @Test
    public void TestMove(){
        Node_Entity root = new Node_Entity(Paths.get("."), Node.Types.FOLDER , new ArrayList<>());
        Node folder = ns.create(root,"NodeServiceTest",Node.Types.FOLDER);
        Node folder2 = ns.create(root,"NodeServiceTests",Node.Types.FOLDER);
        Node file = ns.create(folder,"testFile",Node.Types.FILE);
        Path path = Path.of("./NodeServiceTest/testFile");
        assert Files.exists(path);
        ns.move(file,folder2);
        assert !Files.exists(path);
        assert Files.exists(Paths.get("./NodeServiceTests/testFile"));
        ns.delete(folder);
        ns.delete(folder2);
    }

    @Test
    public void TestMoveDirectory(){
        Node_Entity root = new Node_Entity(Paths.get("."), Node.Types.FOLDER , new ArrayList<>());
        Node folder = ns.create(root,"NodeServiceTest",Node.Types.FOLDER);
        Node folder2 = ns.create(root,"NodeServiceTests",Node.Types.FOLDER);
        Node file = ns.create(folder,"testFile",Node.Types.FILE);
        Path path = Path.of("./NodeServiceTest/testFile");
        assert Files.exists(path);
        ns.move(folder,folder2);
        assert !Files.exists(Paths.get("./NodeServiceTest"));
        assert Files.exists(Paths.get("./NodeServiceTests/NodeServiceTest/testFile"));
        ns.delete(folder2);
    }

    @Test
    public void TestCreateAndDelete(){
        Node_Entity folder = new Node_Entity(Paths.get("."), Node.Types.FOLDER , new ArrayList<>());
        Node n = ns.create(folder,"test",Node.Types.FILE);
        Path path = Path.of("./test");
        assert Files.exists(path);
        ns.delete(n);
        assert !Files.exists(path);
    }

    @Test
    public void TestUpdate(){
        Node_Entity folder = new Node_Entity(Paths.get("."), Node.Types.FOLDER , new ArrayList<>());
        Node n = ns.create(folder,"testFile",Node.Types.FILE);
        try {
            FileWriter testFile = new FileWriter("./testFile");
            testFile.write("Hello Hugo");
            testFile.flush();
            testFile.close();
            String str = "World!";
            ns.update(n, 6, 10,str.getBytes(StandardCharsets.US_ASCII));
            String content = Files.readString(Paths.get("./testFile"));
            assert content.compareTo("Hello World!") == 0;
            ns.delete(n);
        }catch (IOException e){
            System.err.println("failed to write  to testFile");;
        }

    }
}
