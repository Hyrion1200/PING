package fr.epita.assistants.myide.domain.service;


import static fr.epita.assistants.myide.domain.entity.Node.Types.FILE;
import static fr.epita.assistants.myide.domain.entity.Node.Types.FOLDER;
import fr.epita.assistants.myide.domain.entity.*;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Aspects;
import fr.epita.assistants.myide.domain.entity.aspects.Git;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Git;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
@Service
public class ProjectServ implements ProjectService{
    public ProjectServ(){
        nodeservice = new NodeServ(1);
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
        System.out.println(root.toAbsolutePath());
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

    public void setProject(Project_Entity project)
    {
        this.project = project;
    }

    public Project_Entity getProject()
    {
        return this.project;
    }

    private NodeService nodeservice;
    private Project_Entity project;
}
