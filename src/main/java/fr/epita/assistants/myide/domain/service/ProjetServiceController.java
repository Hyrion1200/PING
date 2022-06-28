package fr.epita.assistants.myide.domain.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport.Status;

@RestController
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

    }

    //TODO

    // ide/files/save

    // ide/files/getSyntaxHighlighting

    // ide/files/execJs
    
    // ide/files/execPy

    // ide/git/add

    // ide/git/commit

    // ide/git/pull

    // ide/git/push

    // ide/spotify

    // ide/settings

}
