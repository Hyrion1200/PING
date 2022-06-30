package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Maven_features extends Features {
    private String name;

    public Maven_features(Feature.Type type, String str) {
        super(type);
        this.name = str;
    }

    Maven_features(Feature.Type type) {
        super(type);
    }

    public ExecutionReport execute(Project project, Object... params) {
        // Execute mvn clean
        StringBuilder command = new StringBuilder(name);
        for (Object object : params) {
            command.append(object.toString());
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            ArrayList<String> cmds = new ArrayList<>();
            cmds.add("mvn");
            cmds.add(name);
            for (var str:params) {
                cmds.add(str.toString());
            }
            processBuilder.command(cmds).directory(project.getRootNode().getPath().toFile());
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0)
                return new ExecReport(ExecReport.Status.SUCCESS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Couldn't execute maven command");
    }
}
