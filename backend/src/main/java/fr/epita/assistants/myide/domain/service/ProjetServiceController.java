package fr.epita.assistants.myide.domain.service;

import java.nio.file.Paths;
import java.util.List;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport.Status;

@RestController
@CrossOrigin
public class ProjetServiceController {
    @Autowired
    private ProjectServ projectServ;
    
    @GetMapping("/ide")
    public ProjectServ projectServ(){
        return projectServ;
    }

    @GetMapping("/ide/load")
    public Project load(@RequestParam(value="path", defaultValue = ".") String path)
    {
        System.out.println("Load");
        Project_Entity project = (Project_Entity) projectServ.load(Paths.get(path));
        projectServ.setProject(project);
        return projectServ.getProject();
    }

    @GetMapping("/ide/files/open")
    public ExecReport open(@RequestParam(value="path", defaultValue = "./tmp") String path)
    {
        Project project = projectServ.getProject();
        String params = path;
        List<Feature> features = project.getFeatures();
        // Refactor prettier exec feature
        for (Feature feature : features)
        {
            if (feature.type().toString() == "OPEN")
            {
                ExecReport report = (ExecReport) feature.execute(project, params);
                System.out.println(report.getContent());
                return report;
            }
        }
        return new ExecReport(Status.ERROR, "Error");
    }

    @GetMapping("ide/files/save")
    public ExecReport save(@RequestParam(value="path", defaultValue = "./tmp") String path)
    {
        Project project = projectServ.getProject();
        String params = path;
        // TODO
        return new ExecReport(Status.ERROR, "Error"); 

    }

    @GetMapping("/ide/git/add")
    public ExecReport add(@RequestParam(value="path", defaultValue = "./temp") String path)
    {
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.ADD, path);
    }

    @GetMapping("/ide/git/commit")
    public ExecReport commit(@RequestParam(value="message", defaultValue = "") String message)
    {
        System.out.println("commit: " + message);
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.COMMIT, message);
    }

    @GetMapping("/ide/git/pull")
    public ExecReport pull()
    {
        System.out.println("pull");
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.PULL);
    }

    @GetMapping("/ide/git/push")
    public ExecReport push()
    {
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.PUSH);
    }

    // TODO
    // ide/files/getSyntaxHighlighting
    // ide/spotify

    @GetMapping("/ide/files/exec")
    public ExecReport exec(@RequestParam(value="path", defaultValue = "./temp") String path){
        System.out.println("here");
        Project_Entity project = projectServ.getProject();
        ExecReport report = (ExecReport) projectServ.execute(project, Mandatory.Features.Any.EXEC,path);
        return report;
    }

    @PostMapping("ide/settings")
    public ExecReport UpdateSettings(@RequestBody Settings settings){
        if (settings == null){
            return new ExecReport(Status.ERROR, "Error when parsing JSON");
        }
        try {
            projectServ.getProject().setSettings(settings);
        } catch (Exception e){
            return new ExecReport(Status.ERROR, "Couldn't write to settings file");
        }
        return new ExecReport(Status.SUCCESS);
    }

}
