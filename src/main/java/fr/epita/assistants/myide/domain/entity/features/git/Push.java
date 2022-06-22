package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public
class Push extends Git_features {
    public Push() {
        super(Mandatory.Features.Git.PUSH);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        // DONE

        Git git = getGit(project);
        try {
            PushCommand pushCommand = git.push();
            pushCommand.add("main");
            pushCommand.setRemote("origin");
            // if credential:
            // pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("username", "password"));
            pushCommand.call();
            return new ExecReport(ExecReport.Status.SUCCESS, "Git push successful");

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Git push failed");
    }
}