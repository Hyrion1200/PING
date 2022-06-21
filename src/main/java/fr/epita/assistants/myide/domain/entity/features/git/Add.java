package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class Add extends Git_features {

    public Add() {
        super(Mandatory.Features.Git.ADD);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        StringBuilder args = new StringBuilder();
        for (Object param : params) {
            args.append(param).append(" ");
        }

        Git git = getGit(project);
        try {
            for (String arg : args.toString().split(" ")) {
                git.add().addFilepattern(arg).call();
            }

            return new ExecReport(ExecReport.Status.SUCCESS, "git add successful");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "failed to git add files: " + args);
    }
}