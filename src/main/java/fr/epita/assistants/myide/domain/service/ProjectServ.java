package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.*;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Aspects;
import fr.epita.assistants.myide.domain.entity.aspects.Git;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.epita.assistants.myide.domain.entity.Node.Types.FILE;
import static fr.epita.assistants.myide.domain.entity.Node.Types.FOLDER;

public class ProjectServ implements ProjectService{
    public ProjectServ(){
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
                    node.addChildren(new Node_Entity(Paths.get(cur.getPath()), FILE, new ArrayList<>()));
                }
            }
        }
        else {
            node = new Node_Entity(Paths.get(dir.getPath()), FILE, new ArrayList<>());
        }
        return node;
    }

    private Set<Aspect> get_aspect(File root){
        Set<Aspect> aspects = new HashSet<>();
        aspects.add(new Any());
        if (root.isDirectory()){
            for (File cur : root.listFiles()){
                if (cur.getName().compareTo(".git") == 0){
                    aspects.add(new Git());
                }
                if (cur.getName().compareTo("pom.xml") == 0){
                    aspects.add(new Maven());
                }
            }
        }
        return aspects;
    }
    @Override
    public Project load(Path root) {
        File rootDir = new File(root.toString());
        return new Project_Entity(get_nodes(new File(root.toString())),get_aspect(rootDir));
       }
    @Override
    public Feature.ExecutionReport execute(Project project, Feature.Type featureType, Object... params) {
        if (project.getFeature(featureType).isPresent())
            return project.getFeature(featureType).get().execute(project,params);
        return new ExecReport(ExecReport.Status.ERROR, "Not implemented yet");
    }

    @Override
    public NodeService getNodeService() {
        return nodeservice;
    }

    private NodeService nodeservice;
}
