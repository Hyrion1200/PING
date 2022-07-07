package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Add extends Git_features {

    public Add() {
        super(Mandatory.Features.Git.ADD);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        StringBuilder args = new StringBuilder();
        for (Object param : params) {
            String root = project.getRootNode().getPath().toString();
            String path = (String) param;

            String relative = new File(root).toURI().relativize(new File(path).toURI()).getPath();

            args.append(relative).append(" ");
        }

        Git git = getGit(project);
        try {
            StringBuilder res = new StringBuilder("File added:\n");
            for (String arg : args.toString().split(" ")) {
                System.out.println(arg);
                git.add().addFilepattern(arg).call();
                res.append(arg).append("\n");
            }
            return new ExecReport(ExecReport.Status.SUCCESS, res.toString(), "git add successful");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "failed to git add files: " + args);
    }
}