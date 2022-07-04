package fr.epita.assistants.myide.domain.service;


import static fr.epita.assistants.myide.domain.entity.Node.Types.FILE;
import static fr.epita.assistants.myide.domain.entity.Node.Types.FOLDER;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.epita.assistants.myide.domain.entity.aspects.Any;
import fr.epita.assistants.myide.domain.entity.aspects.Git;
import fr.epita.assistants.myide.domain.entity.aspects.Maven;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.stereotype.Service;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Node_Entity;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
@Service
public class ProjectServ implements ProjectService{
    public ProjectServ(){
        nodeservice = new NodeServ(1);
    }

    public Node get_nodes(File dir){
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
        Node rootNode = get_nodes(new File(root.toString()));
        Settings settings = null;
        for (Node node : rootNode.getChildren()){
            if (node.getPath().getFileName().toString().equals(".pingsettings")){
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    settings = mapper.readValue(node.getPath().toFile(), Settings.class);
                    System.out.println(settings);
                } catch (Exception e){
                    return null;
                }
            }
        }
        if (settings == null){
            settings = new Settings();
            settings.Theme = "DARK";
            settings.Langue = "FR";
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                FileWriter fw = new FileWriter(root + "/.pingsettings");
                String json = ow.writeValueAsString(settings);
                fw.write(json);
                fw.close();
            } catch (Exception e){
                e.printStackTrace();
                settings = null;
            }
        }
        return new Project_Entity(rootNode,get_aspect(rootDir),settings);
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

    public Node getNode(Path path){
        Node node = project.getRootNode();

        boolean found = false;
        Iterator<Path> elms = path.iterator();
        Iterator<Path> rootPath = node.getPath().iterator();
        while(elms.hasNext() && rootPath.hasNext()){
            if (!elms.next().equals(rootPath.next()))
                return null;
        }

        if (!elms.hasNext())
            return node;

        while(elms.hasNext()){
            Path cur = elms.next();
            for (Node child : node.getChildren()){
                if (child.getPath().getFileName().equals(cur)){
                    if (elms.hasNext()){
                        found = true;
                        node = child;
                        break;
                    }
                    else {
                        return child;
                    }
                }
            }
            if (!found)
                return null;
            found = false;
        }

        return null;
    }

    private NodeService nodeservice;
    private Project_Entity project;
}
