package fr.epita.assistants.myide.domain.service;

import ch.qos.logback.core.util.FileUtil;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import org.apache.commons.io.FileUtils;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            RandomAccessFile file = new RandomAccessFile(node.getPath().toFile(), "rw");
            file.seek(from);
            file.write(insertedContent);
            return node;
        }
        catch (FileNotFoundException e){
            throw new IllegalArgumentException("File could not be opened");
        }
        catch (IOException e){
            throw new IllegalArgumentException("from is less than 0 or I/O error");
        }
    }

    @Override
    public boolean delete(Node node) {
        if (node.getType() == FOLDER){
            List<Node> children = node.getChildren();
            for (Node child : children){
                this.delete(node);
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
            throw new IllegalArgumentException("IO Error");
        }
        Node NewNode = new Node_Entity( paths,type,null);
        return NewNode;
    }

    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {
        if (destinationFolder.getType() != FOLDER)
            throw new IllegalArgumentException("Destination is not a folder");
        try{
            if (nodeToMove.getType() != FILE){
                List<Node> children = nodeToMove.getChildren();
                for (Node child : children){
                    this.move(child,destinationFolder);
                }
            }
            else {
                Files.move(nodeToMove.getPath(), destinationFolder.getPath(), REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new IllegalArgumentException("IO error when moving a file");
        }
        String newpath = destinationFolder.getPath().toString() + nodeToMove.getPath().getFileName().toString();
        return new Node_Entity(Paths.get(newpath),nodeToMove.getType(),nodeToMove.getChildren());
    }
}
