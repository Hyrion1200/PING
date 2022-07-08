package fr.epita.assistants.myide.domain.service;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;
import java.util.Optional;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport.Status;
import fr.epita.assistants.myide.domain.entity.Node;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@CrossOrigin
public class ProjetServiceController {
    @Autowired
    private ProjectServ projectServ;

    @GetMapping("/ide")
    public ProjectServ projectServ() {
        return projectServ;
    }

    @GetMapping("/ide/load")
    public ExecReport load(@RequestParam(value = "path", defaultValue = ".") String path) {
        System.out.println("Load");
        Project_Entity project = (Project_Entity) projectServ.load(Paths.get(path));
        if (project == null)
            return new ExecReport(Status.ERROR, "Failed to load project at: " + path);
        projectServ.setProject(project);
        return new ExecReport(Status.SUCCESS, projectServ.getProject());
    }

    @GetMapping("/ide/watch")
    public SseEmitter watch() {
        if (this.projectServ.getProject() == null)
            return null;

        if (this.projectServ.getSseEmitter() == null)
            this.projectServ.setSseEmitter(new SseEmitter(-1L));

        try {
            this.projectServ.getSseEmitter().send("{ \"keepAlive\": \"beAlive\"}");
        } catch (IOException e) {
            System.err.println("Error connecting to SseEmitter");
            e.printStackTrace();
        }

        return this.projectServ.getSseEmitter();
    }

    @Scheduled(fixedRate = 30000)
    public void keepAlive() {
        if (this.projectServ.getSseEmitter() != null) {
            try {
                this.projectServ.getSseEmitter().send("{ \"keepAlive\": \"beAlive\"}");
            } catch (IOException e) {
                System.err.println("Error sending keep alive");
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/ide/files/open")
    public ExecReport open(@RequestParam(value = "path", defaultValue = "./tmp") String path) {
        System.out.println(path);
        Project project = projectServ.getProject();
        String params = path;
        List<Feature> features = project.getFeatures();
        // Refactor prettier exec feature
        for (Feature feature : features) {
            if (feature.type().toString() == "OPEN") {
                ExecReport report = (ExecReport) feature.execute(project, params);
                return report;
            }
        }
        return new ExecReport(Status.ERROR, "Error");
    }

    @PostMapping("ide/files/save")
    public ExecReport save(@RequestParam(value = "path", defaultValue = "./tmp") String path,
            @RequestBody Optional<String> content) {
        String actual_content = "";
        if (content.isPresent()) {
            actual_content = content.get();
        }

        try {
            Project project = projectServ.getProject();
            String params = path;
            Node node = projectServ.get_nodes(new File(path));
            projectServ.getNodeService().update(node, actual_content);
        } catch (Exception e) {
            return new ExecReport(Status.ERROR, "Couldn't save file");
        }
        return new ExecReport(Status.SUCCESS, content, "Success saving file");

    }

    @GetMapping("/ide/git/add")
    public ExecReport add(@RequestParam(value = "path", defaultValue = "./temp") String path) {
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.ADD, path);
    }

    @GetMapping("/ide/git/commit")
    public ExecReport commit(@RequestParam(value = "message", defaultValue = "") String message) {
        System.out.println("commit: " + message);
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.COMMIT, message);
    }

    @GetMapping("/ide/git/pull")
    public ExecReport pull(@RequestParam(value = "user", defaultValue = "") String user,
            @RequestParam(value = "password", defaultValue = "") String password) {
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.PULL, user, password);
    }

    @GetMapping("/ide/git/push")
    public ExecReport push(@RequestParam(value = "user", defaultValue = "") String user,
            @RequestParam(value = "password", defaultValue = "") String password) {
        Project_Entity project = projectServ.getProject();
        return (ExecReport) projectServ.execute(project, Mandatory.Features.Git.PUSH, user, password);
    }

    // TODO
    // ide/files/getSyntaxHighlighting
    // ide/spotify

    @GetMapping("/ide/files/exec")
    public ExecReport exec(@RequestParam(value = "path", defaultValue = "./temp") String path) {
        System.out.println(path);
        System.out.println(Paths.get(path).toAbsolutePath());
        Project_Entity project = projectServ.getProject();
        ExecReport report = (ExecReport) projectServ.execute(project, Mandatory.Features.Any.EXEC, path);
        return report;
    }

    @PostMapping("ide/settings")
    public ExecReport UpdateSettings(@RequestBody Settings settings) {
        if (settings == null) {
            return new ExecReport(Status.ERROR, "Error when parsing JSON");
        }
        try {
            projectServ.getProject().setSettings(settings);
        } catch (Exception e) {
            return new ExecReport(Status.ERROR, "Couldn't write to settings file");
        }
        return new ExecReport(Status.SUCCESS);
    }

    public ExecReport getSettings() {
        return new ExecReport(Status.SUCCESS, projectServ.getProject().getSettings());
    }

    @GetMapping("ide/setting")
    public ExecReport getSetting(@RequestParam(value = "name", defaultValue = "") String name) {
        Object setting = projectServ.getProject().getSettings().getSetting(name);
        if (setting == null) {
            return new ExecReport(Status.ERROR, "Setting not found");
        }
        return new ExecReport(Status.SUCCESS, setting);
    }

}
