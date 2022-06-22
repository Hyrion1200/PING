package fr.epita.assistants.myide.domain.service;

import ch.qos.logback.core.util.FileUtil;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import org.apache.commons.io.FileUtils;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.myide.domain.entity.Node.Types.FILE;
import static fr.epita.assistants.myide.domain.entity.Node.Types.FOLDER;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class NodeServ implements NodeService{


    @Override
    public Node update(Node node, int from, int to, byte[] insertedContent) {
        if (node.getType() == FOLDER)
        {
            throw new IllegalArgumentException("Node is of type folder, file expected");
        }
        try {
            String content = Files.readString(node.getPath());
            FileWriter fw = new FileWriter(node.getPath().toString());
            String insert = new String(insertedContent);
            String new_content = content.substring(0,from) + insert + content.substring(to);
            fw.write(new_content);
            fw.flush();
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return node;
    }

    @Override
    public boolean delete(Node node) {
        if (node.getType() == FOLDER){
            List<Node> children = node.getChildren();
            for (Node child : children){
                this.delete(child);
            }
            try{
                FileUtils.deleteDirectory(new File(node.getPath().toString()));
                return true;
            }
            catch (IOException e){
                return false;
            }
        }
        File file = new File(node.getPath().toString());
        return file.delete();
    }

    @Override
    public Node create(Node folder, String name, Node.Type type) {
        String path = folder.getPath().toString() + "/" + name;
        Path paths = Path.of(path);
        try {
            if (type == FOLDER)
                Files.createDirectories( paths);
            else
                Files.createFile(paths);
        }
        catch (IOException e){
            e.printStackTrace();
            throw new IllegalArgumentException("IO Error");
        }
        Node NewNode = new Node_Entity( paths,type,new ArrayList<Node>());
        folder.getChildren().add(NewNode);
        return NewNode;
    }

    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {

        if (destinationFolder.getType() != FOLDER)
            throw new IllegalArgumentException("Destination is not a folder");
        try {
            String oldpath = nodeToMove.getPath().toString();
            String newpath = destinationFolder.getPath().toString() + "/" + nodeToMove.getPath().getFileName().toString();
            Node_Entity nde = (Node_Entity) nodeToMove;
            nde.setPath(Paths.get(newpath));
            if (nodeToMove.getType() != FILE) {
                List<Node> children = nodeToMove.getChildren();
                Files.createDirectories(nodeToMove.getPath());
                for (Node child : children) {
                    this.move(child, nodeToMove);
                }
                FileUtils.deleteDirectory(new File(oldpath));
            } else {
                File file = new File(oldpath);
                file.renameTo(new File(nde.getPath().toString()));
            }
        }
        catch (IOException e){
            e.printStackTrace();
            throw new IllegalArgumentException("failed to create or delete directory while moving");
        }

        return nodeToMove;
    }
}
