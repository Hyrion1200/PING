package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fr.epita.assistants.myide.domain.entity.Node.Types.FILE;
import static fr.epita.assistants.myide.domain.entity.Node.Types.FOLDER;

public class ProjectServ implements ProjectService{
    ProjectServ(){
        nodeservice = new NodeServ();
    }

    private Node get_nodes(File dir){
        Node_Entity node ;
        List<File> parents = new ArrayList<>();
        if (dir.isDirectory()){
            node = new Node_Entity(Paths.get(dir.getPath()), FOLDER, new ArrayList<Node>());
            for (File cur : dir.listFiles()){
                if (cur.isDirectory()){
                    node.addChildren(get_nodes(cur));
                }
                else {
                    node.addChildren(new Node_Entity(Paths.get(dir.getPath()), FILE, null));
                }
            }
        }
        else {
            node = new Node_Entity(Paths.get(dir.getPath()), FILE, null);
        }
        return node;
    }

    private Set<Aspect> get_aspect(File root){
        //TODO (waiting for type architecture)
        return null;
    }
    @Override
    public Project load(Path root) {
        File rootDir = new File(root.toString());
        return new Project_Entity(root.toString(),get_nodes(new File(root.toString())),get_aspect(rootDir));
       }
    @Override
    public Feature.ExecutionReport execute(Project project, Feature.Type featureType, Object... params) {
        
    }

    @Override
    public NodeService getNodeService() {
        return nodeservice;
    }

    private NodeService nodeservice;
}
